/**
 * 
 */
package repast.simphony.statecharts.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.PopupBarEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.tools.PopupBarTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.swt.graphics.Image;

import repast.simphony.statecharts.providers.StatechartElementTypes;

/**
 * 
 * @author Nick Collier
 */
public class StateMachinePopupBarTool extends PopupBarTool {

  private Image image;
  private String description;

  public StateMachinePopupBarTool(PopupBarEditPolicy policy, IElementType elementType,
      String description) {
    super(policy.getHost(), elementType);

    this.image = StatechartElementTypes.getImage(elementType);
    this.description = description;
  }

  public Image getImage() {
    return image;
  }

  public String getDescription() {
    return description;
  }

}
