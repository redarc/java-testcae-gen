package jcattestcaegen.wizards;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.dialogs.PackageSelectionDialog;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.SelectionDialog;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (java).
 */

public class JcatTCWizardPage extends WizardPage {
	private static final String LINESEPRATOR = System.getProperty("line.separator");
	private Text containerText;
	private Text packageText;
	private Text fileText;
	//private Text txtItem;
	private ISelection selection;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public JcatTCWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("JCAT TestCase Editor File");
		setDescription("This wizard creates 4 new files with *.java extension followed JCAT framework");
		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);
		label.setText("&Container:");

		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		label = new Label(container, SWT.NULL);
		label.setText("&File name:");

		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fileText.setLayoutData(gd);
		fileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		/*
		Composite container = new Composite(parent, SWT.NULL);
		FormLayout layout = new FormLayout();
		container.setLayout(layout);
		
		FormData data = new FormData();
		data.top = new FormAttachment(0, 0);
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.bottom = new FormAttachment(100, 0);
		
		txtItem = new Text(container, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		txtItem.setLayoutData(data);
		txtItem.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e){
				dialogChanged();
			}
		});
		*/
		initialize();
		dialogChanged();
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if(obj instanceof IPackageFragment){
				IPackageFragment pf = (IPackageFragment)obj;
				containerText.setText(pf.getPath().toOSString());
			}else if(obj instanceof ICompilationUnit){
				ICompilationUnit cu = (ICompilationUnit)obj;
				containerText.setText(cu.getPath().toOSString());
			}
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				containerText.setText(container.getFullPath().toString());
			}
		}
		fileText.setText("ttt.java");
	}

	private IJavaProject getCurrentJavaProject(){
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return null;
			Object obj = ssel.getFirstElement();
			if(obj instanceof IJavaElement){
				return ((IJavaElement)obj).getJavaProject();
			}
		}
		return null;
	}
	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowse() {
		/*
		IJavaProject javaProject = getCurrentJavaProject();
		if(javaProject == null){
			MessageDialog.openWarning(getShell(), "error","Please run this wizard in Java Project");
		}
		
		SelectionDialog dialog = null;
		try{
			dialog = JavaUI.createPackageDialog(getShell(), javaProject, IJavaElementSearchConstants.CONSIDER_REQUIRED_PROJECTS);
		}catch(JavaModelException el){
			MessageDialog.openWarning(getShell(), "error", el.getMessage());
			el.printStackTrace();
		}
		
		if(dialog.open() == Window.OK){
			IPackageFragment pck = (IPackageFragment)dialog.getResult()[0];
			if(pck != null){
				containerText.setText(pck.getElementName());
			}
		}
		*/
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
				"Select new file container");
		
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(((Path) result[0]).toString());
			}
		}
		
	}

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		IResource container = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(new Path(getContainerName()));
		String fileName = getFileName();

		if (getContainerName().length() == 0) {
			updateStatus("File container must be specified");
			return;
		}
		if (container == null
				|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
			updateStatus("File container must exist");
			return;
		}
		if (!container.isAccessible()) {
			updateStatus("Project must be writable");
			return;
		}
		if (fileName.length() == 0) {
			updateStatus("File name must be specified");
			return;
		}
		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("File name must be valid");
			return;
		}
		int dotLoc = fileName.lastIndexOf('.');
		if (dotLoc != -1) {
			String ext = fileName.substring(dotLoc + 1);
			if (ext.equalsIgnoreCase("java") == false) {
				updateStatus("File extension must be \"java\"");
				return;
			}
		}
		updateStatus(null);
		/*
		String strItems = txtItem.getText();
		if(strItems == null || strItems.trim().length() <= 0){
			updateStatus("Please input enum");
			return;
		}
		String[] itemArray = strItems.split(LINESEPRATOR);
		Set<String> set = new HashSet<String>();
		for(String item : itemArray){
			if(item == null || item.trim().length() <= 0){
				continue;
			}
			if(set.contains(item)){
				updateStatus("Duplicate item " + item);
				return;
			}
			updateStatus(null);
			set.add(item);
		}
		*/
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public String getContainerName() {
		return containerText.getText();
	}

	public String getFileName() {
		return fileText.getText();
	}
	
	public String getPackageName(){
		return packageText.getText();
	}
}