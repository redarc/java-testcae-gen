package jcattestcaegen.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;

import java.io.*;

import jcat.testcase.gen.JcatTCBuilderGen;
import jcat.testcase.gen.JcatTCGen;
import jcat.testcase.gen.JcatTCGenArgs;
import jcat.testcase.gen.JcatTCModuleGen;

import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;

/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "java". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class JcatTCWizard extends Wizard implements INewWizard {
	private JcatTCWizardPage page;
	private ISelection selection;

	/**
	 * Constructor for JcatTCWizard.
	 */
	public JcatTCWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		page = new JcatTCWizardPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}
	
	private void generatorJcatTC(IContainer container,
                                 String fileName,
                                 String content,
                                 IProgressMonitor monitor) throws CoreException{
		
		//TODO replace stream
		final IFile file = container.getFile(new Path(fileName.concat(".java")));
		try {
			InputStream stream = new ByteArrayInputStream(content.getBytes());
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(String containerName,
		                  String testCaseName,
		                  IProgressMonitor monitor)throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + testCaseName, 4);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(testCaseName.concat("Test.java")));
		
		JcatTCGenArgs jargs = new JcatTCGenArgs();
		jargs.setClassName(testCaseName.concat("Test"));
		jargs.setPkgName("com.ericsson.msr.tests." + testCaseName);
		JcatTCGen gen = new JcatTCGen();
		generatorJcatTC(container, testCaseName.concat("Test"), gen.generate(jargs), monitor);
		monitor.worked(1);
		
		JcatTCGenArgs tcbuilderargs = new JcatTCGenArgs();
		tcbuilderargs.setClassName(testCaseName.concat("TestModuleBuilder"));
		tcbuilderargs.setPkgName("com.ericsson.msr.tests." + testCaseName);
		JcatTCBuilderGen buildergen = new JcatTCBuilderGen();
		generatorJcatTC(container, testCaseName.concat("TestModuleBuilder"), buildergen.generate(tcbuilderargs), monitor);
		monitor.worked(2);
		
		JcatTCGenArgs jmoduleargs = new JcatTCGenArgs();
		jmoduleargs.setClassName(testCaseName.concat("TestModule"));
		jmoduleargs.setPkgName("com.ericsson.msr.tests." + testCaseName);
		JcatTCModuleGen modulegen = new JcatTCModuleGen();
		generatorJcatTC(container, testCaseName.concat("TestModule"), modulegen.generate(jmoduleargs), monitor);
		monitor.worked(3);
		
		//Default open testcase.java
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
		monitor.worked(4);
	}
	
	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "jcat-testcae-gen", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}