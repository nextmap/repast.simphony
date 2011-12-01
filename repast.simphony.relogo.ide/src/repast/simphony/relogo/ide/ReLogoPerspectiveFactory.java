package repast.simphony.relogo.ide;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.ui.IPackagesViewPart;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.progress.IProgressConstants;

public class ReLogoPerspectiveFactory implements IPerspectiveFactory {

	public static final String ID_PROJECT_EXPLORER = "org.eclipse.ui.navigator.ProjectExplorer";

	@Override
	public void createInitialLayout(IPageLayout layout) {

		String editorArea = layout.getEditorArea();

		IFolderLayout folder = layout.createFolder(
				"left", IPageLayout.LEFT, (float) 0.25, editorArea); //$NON-NLS-1$
		IWorkbench wb = PlatformUI.getWorkbench();
		if (wb != null) {
			IWorkbenchWindow ww = wb.getActiveWorkbenchWindow();
			if (ww != null) {
				IWorkbenchPage wp = ww.getActivePage();
				if (wp != null) {
					IViewPart vp = wp.findView(JavaUI.ID_PACKAGES);
					if (vp != null && vp instanceof IPackagesViewPart) {
						IPackagesViewPart pvp = (IPackagesViewPart) vp;
						if (!pvp.isLinkingEnabled()) {
							IActionBars iabs = pvp.getViewSite()
									.getActionBars();
							IToolBarManager itbm = iabs.getToolBarManager();
							if (itbm instanceof ToolBarManager) {
								ToolBarManager tbm = (ToolBarManager) itbm;
								ToolBar tb = tbm.getControl();
								for (ToolItem ti : tb.getItems()) {
									Object o = ti.getData();
									if (o != null
											&& o instanceof ActionContributionItem) {
										ActionContributionItem aci = (ActionContributionItem) o;
										IAction a = aci.getAction();
										if (a != null
												&& a.getActionDefinitionId()
														.equals(IWorkbenchCommandConstants.NAVIGATE_TOGGLE_LINK_WITH_EDITOR)) {
											Action aa = (Action) a;
											aa.setChecked(true);
											a.run();
										}
									}
								}
							}
						}
					}
				}
			}
		}
		folder.addView(JavaUI.ID_PACKAGES);
		folder.addView(IPageLayout.ID_RES_NAV);

		IFolderLayout outputfolder = layout.createFolder(
				"bottom", IPageLayout.BOTTOM, (float) 0.75, editorArea); //$NON-NLS-1$

		outputfolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		outputfolder.addView(IPageLayout.ID_PROBLEM_VIEW);

		layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
		// layout.addActionSet("repast.simphony.eclipse.samplemodelsactionSet");
		// Workbench.getInstance().getActiveWorkbenchWindow().getActivePage().hideActionSet("org.eclipse.debug.ui.launchActionSet");

		// layout.addActionSet(JavaUI.ID_ACTION_SET);
		// layout.addActionSet(JavaUI.ID_ELEMENT_CREATION_ACTION_SET);
		// layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);

		// views - java
		layout.addShowViewShortcut(JavaUI.ID_PACKAGES);
		layout.addShowViewShortcut(JavaUI.ID_TYPE_HIERARCHY);
		layout.addShowViewShortcut(JavaUI.ID_SOURCE_VIEW);
		layout.addShowViewShortcut(JavaUI.ID_JAVADOC_VIEW);

		// views - debugging
		layout.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);

		// views - standard workbench
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
		layout.addShowViewShortcut(IProgressConstants.PROGRESS_VIEW_ID);
		layout.addShowViewShortcut(ID_PROJECT_EXPLORER);

		// new actions - Java project creation wizard
		layout.addNewWizardShortcut("repast.simphony.relogo.ide.new_project_wizard");
		layout.addNewWizardShortcut("repast.simphony.relogo.ide.new_turtle_wizard");
		layout.addNewWizardShortcut("repast.simphony.relogo.ide.new_link_wizard");
		layout.addNewWizardShortcut("repast.simphony.relogo.ide.new_patch_wizard");
		layout.addNewWizardShortcut("repast.simphony.relogo.ide.new_observer_wizard");
		layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewPackageCreationWizard"); //$NON-NLS-1$
		layout.addNewWizardShortcut("org.codehaus.groovy.eclipse.ui.groovyClassWizard");
		layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewClassCreationWizard"); //$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewInterfaceCreationWizard"); //$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewEnumCreationWizard"); //$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewAnnotationCreationWizard"); //$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewSourceFolderCreationWizard"); //$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");//$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");//$NON-NLS-1$
		layout.addNewWizardShortcut("org.eclipse.ui.editors.wizards.UntitledTextFileWizard");//$NON-NLS-1$
	}
}
