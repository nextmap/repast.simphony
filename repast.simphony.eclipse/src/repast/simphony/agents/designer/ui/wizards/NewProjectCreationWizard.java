/*
 * Copyright (c) 2003, Alexander Greif All rights reserved. (Adapted by Michael
 * J. North for Use in Repast Simphony from Alexander Greif�s Flow4J-Eclipse
 * (http://flow4jeclipse.sourceforge.net/docs/index.html), with Thanks to the
 * Original Author) (Michael J. North�s Modifications are Copyright 2007 Under
 * the Repast Simphony License, All Rights Reserved)
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. * Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. * Neither the name of the Flow4J-Eclipse project nor
 * the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package repast.simphony.agents.designer.ui.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import repast.simphony.agents.base.Util;
import repast.simphony.agents.designer.core.AgentBuilderPlugin;
import repast.simphony.agents.designer.core.WorkspaceRunnableAdapter;
import repast.simphony.agents.designer.ui.AgentBuilderPluginImages;

/**
 * 
 * Class NewProjectCreationWizard TODO
 * 
 * @author agreif (Adapted by Michael J. North for Use in Repast Simphony from
 *         Alexander Greif�s Flow4J-Eclipse
 *         (http://flow4jeclipse.sourceforge.net/docs/index.html), with Thanks
 *         to the Original Author)
 * 
 */
@SuppressWarnings("unchecked")
public class NewProjectCreationWizard extends BasicNewResourceWizard implements INewWizard {

	public WizardNewProjectCreationPage mainPage;
	public NewProjectCreationWizardPageJ javaPage;
	private IConfigurationElement configElement;
	public IProject newProject;
	public boolean configureGroovyAndVisualEditing = true;

	/**
	 * Creates the project creation wizard.
	 * 
	 */
	public NewProjectCreationWizard() {
		super();
//		this.showScoreFileContainerSelector = false;
		setWindowTitle(AgentBuilderPlugin
				.getResourceString("Wizard_NewProject.windowTitle"));
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(AgentBuilderPlugin
				.getResourceString("Wizard_NewProject.windowTitle"));
		setDefaultPageImageDescriptor(AgentBuilderPluginImages.DESC_WIZBAN_NewAgent4JPROJECT);
	}

	/**
	 * Adds the wizard pages
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {

		mainPage = new WizardNewProjectCreationPage("WizardPage_NewProject");
		mainPage.setTitle(AgentBuilderPlugin
				.getResourceString("WizardPage_NewProject.pageTitle"));
		mainPage.setDescription(AgentBuilderPlugin
				.getResourceString("WizardPage_NewProject.pageDescription"));
		mainPage
				.setImageDescriptor(AgentBuilderPluginImages.DESC_WIZBAN_NewAgent4JPROJECT);
		this.addPage(mainPage);

		// the java wizard page
		javaPage = new NewProjectCreationWizardPageJ(mainPage);
		this.addPage(javaPage);

		super.addPages();

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public String getDefaultBaseDir() {
		return "../" + mainPage.getProjectName();
	}

	/*
	 * Finishes the Wizard and creates the project.
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		IWorkspaceRunnable op = new IWorkspaceRunnable() {
			// IRunnableWithProgress op= new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws /* InvocationTargetException, InterruptedException, */CoreException,
					OperationCanceledException {
				try {
					// TODO replace contextPage with new or delete this equivalent
//					if ((contextPage) != null
//							&& (contextPage.getFullyConfigureField() != null)) {
//						configureGroovyAndVisualEditing = contextPage
//								.getFullyConfigureField().getSelection();
//					}
					finishPage(monitor);
//					NewProjectCreationWizard.super.performFinish();
					monitor.done();
				} catch (InterruptedException e) {
					throw new OperationCanceledException(e.getMessage());
				} catch (CoreException e) {
					AgentBuilderPlugin.log(e);
				}
			}
		};
		try {
			getContainer().run(false, true, new WorkspaceRunnableAdapter(op));
		} catch (InvocationTargetException e) {
			AgentBuilderPlugin.log(e);
			AgentBuilderPlugin.message("Creation of element failed.");
			return false;
		} catch (InterruptedException e) {
			AgentBuilderPlugin.log(e);
			AgentBuilderPlugin.message("Creation of element failed.");
			return false;
		}
		return true;
	}

	/**
	 * Creates the java projects and add the Repast Simphony Nature
	 * 
	 * @param monitor
	 * @throws InterruptedException
	 * @throws CoreException
	 */
	protected void finishPage(IProgressMonitor monitor)
			throws InterruptedException, CoreException {
		monitor.beginTask("", 10); //$NON-NLS-1$
		javaPage.performFinish(monitor);

		IJavaProject javaProject = javaPage.getJavaProject();
		BasicNewProjectResourceWizard.updatePerspective(configElement);

		IClasspathEntry list[] = javaProject.getRawClasspath();
		IPath srcPath = null;
		for (IClasspathEntry entry : list) {
			if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				srcPath = entry.getPath();
			}
		}
		if (srcPath != null) {

//			String scenarioDirectory = this.contextPage.getModelPackage()
//					+ ".rs";
			
			// TODO the inputs on the legacy score context page are just replaced
			//      with the project name here.  If we want additional customization
			//      for model and package names, new gui elements need to be created.
			
			// TODO make sure packagename string is formatted properly (no spaces).
			
			String scenarioDirectory = mainPage.getProjectName()	+ ".rs";

			String mainDataSourcePluginDirectory = AgentBuilderPlugin
					.getPluginInstallationDirectory();

			String[][] variableMap = {
					{ "%MODEL_NAME%", mainPage.getProjectName() },
					{ "%PROJECT_NAME%", javaProject.getElementName() },
					{ "%SCENARIO_DIRECTORY%", scenarioDirectory },
					{ "%PACKAGE%", mainPage.getProjectName() },
					{ "%REPAST_SIMPHONY_INSTALL_BUILDER_PLUGIN_DIRECTORY%",
							mainDataSourcePluginDirectory } };
			IFolder srcFolder = javaProject.getProject().getFolder(
					srcPath.removeFirstSegments(1));
//			IFolder packageFolder = srcFolder.getFolder(this.contextPage
//					.getPackage());
			IFolder packageFolder = srcFolder.getFolder(mainPage.getProjectName().replace(" ", "_"));
			packageFolder.create(true, false, monitor);

			if (this.configureGroovyAndVisualEditing) {
				Util.copyFileFromPluginInstallation("ModelInitializer.agent",
						packageFolder, "ModelInitializer.agent", variableMap,
						monitor);
				Util.copyFileFromPluginInstallation("ModelInitializer.txt",
						packageFolder, "ModelInitializer.groovy", variableMap,
						monitor);
			}

			IFolder newFolder = srcFolder.getFolder("../docs");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			Util.copyFileFromPluginInstallation("docs/ReadMe.txt", newFolder,
					"ReadMe.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation("docs/index.html", newFolder,
					"index.html", variableMap, monitor);
			
			// for distributed batch (see SIM-459)
			newFolder = srcFolder.getFolder("../transferFiles");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			
			// for distributed batch (see SIM-459)			
			newFolder = srcFolder.getFolder("../output");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);

			newFolder = srcFolder.getFolder("../freezedried_data");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			Util.copyFileFromPluginInstallation("freezedried_data/ReadMe.txt",
					newFolder, "ReadMe.txt", variableMap, monitor);

			newFolder = srcFolder.getFolder("../icons");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			Util.copyFileFromPluginInstallation("icons/ReadMe.txt", newFolder,
					"ReadMe.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation("icons/model.png", newFolder,
					"model.png", variableMap, monitor);
			Util.copyFileFromPluginInstallation("icons/model.bmp", newFolder,
					"model.bmp", variableMap, monitor);

			newFolder = srcFolder.getFolder("../installer");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			Util.copyFileFromPluginInstallation(
					"installer/installation_components.xml", newFolder,
					"installation_components.xml", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"installer/shortcuts_Windows.xml", newFolder,
					"shortcuts_Windows.xml", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"installer/shortcuts_Windows_Registry.xml", newFolder,
					"shortcuts_Windows_Registry.xml", variableMap, monitor);
			Util.copyFileFromPluginInstallation("installer/shortcuts_Xnix.xml",
					newFolder, "shortcuts_Xnix.xml", variableMap, monitor);
			Util.copyFileFromPluginInstallation("installer/splash_screen.png",
					newFolder, "splash_screen.png", monitor);
			Util.copyFileFromPluginInstallation("installer/start_model.bat",
					newFolder, "start_model.bat", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"installer/start_model.command", newFolder,
					"start_model.command", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"installer/installation_coordinator.xml", newFolder,
					"installation_coordinator.xml", variableMap, monitor);

			newFolder = srcFolder.getFolder("../repast-licenses");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/apache-license.txt", newFolder,
					"apache-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/asm-license.txt", newFolder,
					"asm-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/axion-license.txt", newFolder,
					"axion-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/binding-license.txt", newFolder,
					"binding-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/common-public-license.txt", newFolder,
					"common-public-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/concurrent-license.pdf", newFolder,
					"concurrent-license.pdf", variableMap, monitor);
			Util.copyFileFromPluginInstallation("repast-licenses/CPL.txt",
					newFolder, "CPL.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/forms-license.txt", newFolder,
					"forms-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/geotools-license.txt", newFolder,
					"geotools-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/groovy-license.txt", newFolder,
					"groovy-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/hsqldb-license.txt", newFolder,
					"hsqldb-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/jakarta-commons-collections-license.txt",
					newFolder, "jakarta-commons-collections-license.txt",
					variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/jaxen-license.txt", newFolder,
					"jaxen-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/jh-license.txt", newFolder,
					"jh-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/jide-oss-license.txt", newFolder,
					"jide-oss-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/jmatlink-license.txt", newFolder,
					"jmatlink-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/jmf-license.txt", newFolder,
					"jmf-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/jmock-license.txt", newFolder,
					"jmock-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/jscience-license.txt", newFolder,
					"jscience-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/jsp-servlet-api-license.txt", newFolder,
					"jsp-servlet-api-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/jts-license.txt", newFolder,
					"jts-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/jung-license.txt", newFolder,
					"jung-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation("repast-licenses/lgpl-2.1.txt",
					newFolder, "lgpl-2.1.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/LICENSE-jgoodies.txt", newFolder,
					"LICENSE-jgoodies.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/log4j-license.txt", newFolder,
					"log4j-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/mitre-relogo-import-wizard-license.txt", newFolder,
					"mitre-relogo-import-wizard-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/MPL-license.txt", newFolder,
					"MPL-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/msql-connector-license.txt", newFolder,
					"msql-connector-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/mx4j-license.txt", newFolder,
					"mx4j-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/openforecast-license.txt", newFolder,
					"openforecast-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/piccolo-license.txt", newFolder,
					"piccolo-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/proactive-license.txt", newFolder,
					"proactive-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/repast-license.txt", newFolder,
					"repast-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/saxpath-license.txt", newFolder,
					"saxpath-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/swingx-license.txt", newFolder,
					"swingx-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/table-layout-license.txt", newFolder,
					"table-layout-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/violinstrings-license.txt", newFolder,
					"violinstrings-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/wizard-license.txt", newFolder,
					"wizard-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/xpp3-license.txt", newFolder,
					"xpp3-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/xstream-license.txt", newFolder,
					"xstream-license.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/license_apache.txt", newFolder,
					"license_apache.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/license_apache11.txt", newFolder,
					"license_apache11.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/license_flow4j.txt", newFolder,
					"license_flow4j.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/license_flow4J-eclipse.txt", newFolder,
					"license_flow4J-eclipse.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation(
					"repast-licenses/license_xstream.txt", newFolder,
					"license_xstream.txt", variableMap, monitor);

			newFolder = srcFolder.getFolder("../launchers");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			Util.copyFileFromPluginInstallation("launchers/ReadMe.txt",
					newFolder, "ReadMe.txt", variableMap, monitor);

			// ************************************************************************
			// * *
			// * Create Launch Configurations *
			// * *
			// ************************************************************************
			Util.createLaunchConfigurations(javaProject, newFolder,
					scenarioDirectory);

			newFolder = srcFolder.getFolder("../batch");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			Util.copyFileFromPluginInstallation("batch/ReadMe.txt", newFolder,
					"ReadMe.txt", variableMap, monitor);
			Util.copyFileFromPluginInstallation("batch/batch_params.xml",
					newFolder, "batch_params.xml", variableMap, monitor);

			newFolder = srcFolder.getFolder("../integration");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			Util.copyFileFromPluginInstallation("integration/ReadMe.txt",
					newFolder, "ReadMe.txt", variableMap, monitor);

			newFolder = srcFolder.getFolder("../lib");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			Util.copyFileFromPluginInstallation("lib/ReadMe.txt", newFolder,
					"ReadMe.txt", variableMap, monitor);

			newFolder = srcFolder.getFolder("../misc");
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			Util.copyFileFromPluginInstallation("misc/ReadMe.txt", newFolder,
					"ReadMe.txt", variableMap, monitor);

			newFolder = srcFolder.getFolder("../" + scenarioDirectory);
			if (!newFolder.exists())
				newFolder.create(true, false, monitor);
			Util.copyFileFromPluginInstallation("package.rs/scenario.xml",
					newFolder, "scenario.xml", variableMap, monitor);
			Util.copyFileFromPluginInstallation("package.rs/user_path.xml",
					newFolder, "user_path.xml", variableMap, monitor);
			Util.copyFileFromPluginInstallation("package.rs/context.xml",
					newFolder, "context.xml", variableMap, monitor);
			Util.copyFileFromPluginInstallation("package.rs/parameters.xml",
					newFolder, "parameters.xml", variableMap, monitor);

			newFolder = srcFolder.getFolder("../license.txt");
			Util.copyFileFromPluginInstallation("license.txt", newFolder, "",
					variableMap, monitor);
			
			newFolder = srcFolder.getFolder("../MessageCenter.log4j.properties");
			Util.copyFileFromPluginInstallation("MessageCenter.log4j.properties", newFolder, "",
					variableMap, monitor);

			newFolder = srcFolder.getFolder("../model_description.txt");
			Util.copyFileFromPluginInstallation("model_description.txt",
					newFolder, "", variableMap, monitor);

			AgentBuilderPlugin.addRepastSimphonyNature(
					javaProject.getProject(),
					new SubProgressMonitor(monitor, 1), false,
					this.configureGroovyAndVisualEditing);

			try {
				selectAndReveal(
						javaProject.findPackageFragment(
								packageFolder.getFullPath())
								.getCorrespondingResource(), 
								this.getWorkbench().getActiveWorkbenchWindow());
			} catch (Exception e) {
			}

		}

		monitor.done();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	public void resetProjectName() {

		// TODO wtf?
//		if ((!this.showScoreFileContainerSelector)
//				&& (this.contextPage != null)) {
//			this.contextPage.resetProjectName(this.getDefaultBaseDir());
//		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IWizard#performCancel()
	 */
	public boolean performCancel() {
		javaPage.performCancel();
		return super.performCancel();
	}

	public void setWindowTitle(String title) {
		super.setWindowTitle(title);
	}

}
