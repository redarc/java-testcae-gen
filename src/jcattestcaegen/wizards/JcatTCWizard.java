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
	
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(String containerName,
		                  String fileName,
		                  IProgressMonitor monitor)throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 4);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		
		final IFile file = container.getFile(new Path(fileName));
		try {
			InputStream stream = openContentStream();
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		monitor.worked(1);
		final IFile fileBuilder = container.getFile(new Path(fileName.concat("Builder")));
		try {
			InputStream stream = openContentStream();
			if (fileBuilder.exists()) {
				fileBuilder.setContents(stream, true, true, monitor);
			} else {
				fileBuilder.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		monitor.worked(2);
		
		final IFile fileModule = container.getFile(new Path(fileName.concat("Module")));
		try {
			InputStream stream = openContentStream();
			if (fileModule.exists()) {
				fileModule.setContents(stream, true, true, monitor);
			} else {
				fileModule.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		monitor.worked(3);
		
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
	
	/**
	 * We will initialize file contents with a sample text.
	 */

	private InputStream openContentStream() {
		String contents =
			"This is the initial file contents for *.java file that should be word-sorted in the Preview page of the multi-page editor";

		JcatTCGenArgs jargs = new JcatTCGenArgs();
		jargs.setClassName("SPSWP2464");
		jargs.setPkgName("com.redarc");
		
		JcatTCGen gen = new JcatTCGen();
		return new ByteArrayInputStream(gen.generate(jargs).getBytes());
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