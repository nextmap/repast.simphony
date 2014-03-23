package repast.simphony.gis.visualization.engine;

import java.util.ArrayList;
import java.util.List;

import org.pietschy.wizard.WizardModel;
import org.pietschy.wizard.WizardStep;
import org.pietschy.wizard.models.Condition;

import repast.simphony.util.collections.Pair;
import repast.simphony.visualization.engine.AbstractVisualizationRegistryData;
import repast.simphony.visualization.engine.DisplayType;
import repast.simphony.visualization.gui.DisplayDescriptorFactory;
import repast.simphony.visualization.gui.DisplayWizardModel;
import repast.simphony.visualization.gui.GISStyleStep;

public class GISVisualizationRegistryData extends AbstractVisualizationRegistryData {

	public static final String TYPE = "GIS";
	
	public GISVisualizationRegistryData() {
		super(TYPE, new DisplayCreatorFactoryGIS());
	}

	@Override
	public Class<?>[] getDefaultStyles() {
		return null;  // 2D GIS uses SLD not style classes
	}

	@Override
	public Class<?> getStyleInterface() {
		return null;  // 2D GIS uses SLD not style classes
	}

	@Override
	public Class<?>[] getDefaultEdgeStyles() {
		return null;  // 2D GIS uses SLD not style classes
	}

	@Override
	public Class<?> getEdgeStyleInterface() {
		return null;  // 2D GIS uses SLD not style classes
	}

	@Override
	public List<Pair<WizardStep, Condition>> getDisplayWizardSteps() {
	  return GISDisplayWizardStepCreator.getDisplayWizardSteps();
	}
	
	@Override
	public DisplayDescriptorFactory getDescriptorFactory() {
		return new GISDisplayDescriptorFactory();
	}
}