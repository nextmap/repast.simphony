/**
 */
package repast.simphony.statecharts.scmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Machine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link repast.simphony.statecharts.scmodel.StateMachine#getStates <em>States</em>}</li>
 *   <li>{@link repast.simphony.statecharts.scmodel.StateMachine#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link repast.simphony.statecharts.scmodel.StateMachine#getAgentType <em>Agent Type</em>}</li>
 *   <li>{@link repast.simphony.statecharts.scmodel.StateMachine#getPackage <em>Package</em>}</li>
 *   <li>{@link repast.simphony.statecharts.scmodel.StateMachine#getClassName <em>Class Name</em>}</li>
 *   <li>{@link repast.simphony.statecharts.scmodel.StateMachine#getLanguage <em>Language</em>}</li>
 *   <li>{@link repast.simphony.statecharts.scmodel.StateMachine#getNextID <em>Next ID</em>}</li>
 *   <li>{@link repast.simphony.statecharts.scmodel.StateMachine#getId <em>Id</em>}</li>
 *   <li>{@link repast.simphony.statecharts.scmodel.StateMachine#getUuid <em>Uuid</em>}</li>
 *   <li>{@link repast.simphony.statecharts.scmodel.StateMachine#getPriority <em>Priority</em>}</li>
 * </ul>
 * </p>
 *
 * @see repast.simphony.statecharts.scmodel.StatechartPackage#getStateMachine()
 * @model
 * @generated
 */
public interface StateMachine extends EObject {
  /**
   * Returns the value of the '<em><b>States</b></em>' containment reference list.
   * The list contents are of type {@link repast.simphony.statecharts.scmodel.AbstractState}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>States</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>States</em>' containment reference list.
   * @see repast.simphony.statecharts.scmodel.StatechartPackage#getStateMachine_States()
   * @model containment="true"
   * @generated
   */
  EList<AbstractState> getStates();

  /**
   * Returns the value of the '<em><b>Transitions</b></em>' containment reference list.
   * The list contents are of type {@link repast.simphony.statecharts.scmodel.Transition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Transitions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transitions</em>' containment reference list.
   * @see repast.simphony.statecharts.scmodel.StatechartPackage#getStateMachine_Transitions()
   * @model containment="true"
   * @generated
   */
  EList<Transition> getTransitions();

  /**
   * Returns the value of the '<em><b>Agent Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Agent Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Agent Type</em>' attribute.
   * @see #setAgentType(String)
   * @see repast.simphony.statecharts.scmodel.StatechartPackage#getStateMachine_AgentType()
   * @model
   * @generated
   */
  String getAgentType();

  /**
   * Sets the value of the '{@link repast.simphony.statecharts.scmodel.StateMachine#getAgentType <em>Agent Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Agent Type</em>' attribute.
   * @see #getAgentType()
   * @generated
   */
  void setAgentType(String value);

  /**
   * Returns the value of the '<em><b>Package</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Package</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Package</em>' attribute.
   * @see #setPackage(String)
   * @see repast.simphony.statecharts.scmodel.StatechartPackage#getStateMachine_Package()
   * @model
   * @generated
   */
  String getPackage();

  /**
   * Sets the value of the '{@link repast.simphony.statecharts.scmodel.StateMachine#getPackage <em>Package</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Package</em>' attribute.
   * @see #getPackage()
   * @generated
   */
  void setPackage(String value);

  /**
   * Returns the value of the '<em><b>Class Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Class Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Class Name</em>' attribute.
   * @see #setClassName(String)
   * @see repast.simphony.statecharts.scmodel.StatechartPackage#getStateMachine_ClassName()
   * @model
   * @generated
   */
  String getClassName();

  /**
   * Sets the value of the '{@link repast.simphony.statecharts.scmodel.StateMachine#getClassName <em>Class Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class Name</em>' attribute.
   * @see #getClassName()
   * @generated
   */
  void setClassName(String value);

  /**
   * Returns the value of the '<em><b>Language</b></em>' attribute.
   * The literals are from the enumeration {@link repast.simphony.statecharts.scmodel.LanguageTypes}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Language</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Language</em>' attribute.
   * @see repast.simphony.statecharts.scmodel.LanguageTypes
   * @see #setLanguage(LanguageTypes)
   * @see repast.simphony.statecharts.scmodel.StatechartPackage#getStateMachine_Language()
   * @model
   * @generated
   */
  LanguageTypes getLanguage();

  /**
   * Sets the value of the '{@link repast.simphony.statecharts.scmodel.StateMachine#getLanguage <em>Language</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Language</em>' attribute.
   * @see repast.simphony.statecharts.scmodel.LanguageTypes
   * @see #getLanguage()
   * @generated
   */
  void setLanguage(LanguageTypes value);

  /**
   * Returns the value of the '<em><b>Next ID</b></em>' attribute.
   * The default value is <code>"0"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Next ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Next ID</em>' attribute.
   * @see #setNextID(int)
   * @see repast.simphony.statecharts.scmodel.StatechartPackage#getStateMachine_NextID()
   * @model default="0"
   * @generated
   */
  int getNextID();

  /**
   * Sets the value of the '{@link repast.simphony.statecharts.scmodel.StateMachine#getNextID <em>Next ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Next ID</em>' attribute.
   * @see #getNextID()
   * @generated
   */
  void setNextID(int value);

  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see repast.simphony.statecharts.scmodel.StatechartPackage#getStateMachine_Id()
   * @model
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link repast.simphony.statecharts.scmodel.StateMachine#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Uuid</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Uuid</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Uuid</em>' attribute.
   * @see #setUuid(String)
   * @see repast.simphony.statecharts.scmodel.StatechartPackage#getStateMachine_Uuid()
   * @model
   * @generated
   */
  String getUuid();

  /**
   * Sets the value of the '{@link repast.simphony.statecharts.scmodel.StateMachine#getUuid <em>Uuid</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Uuid</em>' attribute.
   * @see #getUuid()
   * @generated
   */
  void setUuid(String value);

  /**
   * Returns the value of the '<em><b>Priority</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Priority</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Priority</em>' attribute.
   * @see #setPriority(double)
   * @see repast.simphony.statecharts.scmodel.StatechartPackage#getStateMachine_Priority()
   * @model
   * @generated
   */
  double getPriority();

  /**
   * Sets the value of the '{@link repast.simphony.statecharts.scmodel.StateMachine#getPriority <em>Priority</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Priority</em>' attribute.
   * @see #getPriority()
   * @generated
   */
  void setPriority(double value);

} // StateMachine
