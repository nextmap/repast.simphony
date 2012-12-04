/**
 * 
 */
package repast.simphony.statecharts.sheets;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import repast.simphony.statecharts.scmodel.AbstractState;
import repast.simphony.statecharts.scmodel.LanguageTypes;
import repast.simphony.statecharts.scmodel.StatechartPackage;

/**
 * Manages the 3 language buttons.
 * 
 * @author Nick Collier
 */
public class LanguageButtonsGroup {

  private Button btnJava, btnRelogo, btnGroovy;
  private LanguageTypes selectedType;

  public LanguageButtonsGroup(Button btnJava, Button btnRelogo, Button btnGroovy) {
    this.btnGroovy = btnGroovy;
    this.btnJava = btnJava;
    this.btnRelogo = btnRelogo;
    
    addListener();
  }
  
  private void initLanguageButton() {
    btnJava.setSelection(selectedType.equals(LanguageTypes.JAVA));
    btnRelogo.setSelection(selectedType.equals(LanguageTypes.RELOGO));
    btnGroovy.setSelection(selectedType.equals(LanguageTypes.GROOVY));
  }
  
  public void bindModel(EMFDataBindingContext context, EObject eObject) {
    selectedType = ((AbstractState) eObject).getLanguage();
    initLanguageButton();

    UpdateValueStrategy targetToModel = new UpdateValueStrategy();
    BooleanToLanguageConverter converter = new BooleanToLanguageConverter();
    targetToModel.setConverter(converter);

    context.bindValue(
        WidgetProperties.selection().observe(btnJava),
        EMFEditProperties.value(TransactionUtil.getEditingDomain(eObject),
            StatechartPackage.Literals.ABSTRACT_STATE__LANGUAGE).observe(eObject), targetToModel,
        null);

    context.bindValue(
        WidgetProperties.selection().observe(btnGroovy),
        EMFEditProperties.value(TransactionUtil.getEditingDomain(eObject),
            StatechartPackage.Literals.ABSTRACT_STATE__LANGUAGE).observe(eObject), targetToModel,
        null);

    context.bindValue(
        WidgetProperties.selection().observe(btnRelogo),
        EMFEditProperties.value(TransactionUtil.getEditingDomain(eObject),
            StatechartPackage.Literals.ABSTRACT_STATE__LANGUAGE).observe(eObject), targetToModel,
        null);

  }
  
  private void addListener() {
    SelectionListener listener = new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        if (e.getSource().equals(btnJava))
          selectedType = LanguageTypes.JAVA;
        else if (e.getSource().equals(btnRelogo))
          selectedType = LanguageTypes.RELOGO;
        else if (e.getSource().equals(btnGroovy))
          selectedType = LanguageTypes.GROOVY;
      }
    };

    btnJava.addSelectionListener(listener);
    btnRelogo.addSelectionListener(listener);
    btnGroovy.addSelectionListener(listener);
  }

  private class BooleanToLanguageConverter implements IConverter {

    @Override
    public Object getFromType() {
      return Boolean.class;
    }

    @Override
    public Object getToType() {
      return LanguageTypes.class;
    }

    @Override
    public Object convert(Object fromObject) {
      return selectedType;
    }
  }

}
