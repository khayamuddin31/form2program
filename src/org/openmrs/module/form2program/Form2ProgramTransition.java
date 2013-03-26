package org.openmrs.module.form2program;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.ProgramWorkflowState;


public class Form2ProgramTransition implements Serializable {

	public static final Log log = LogFactory.getLog(Form2ProgramTransition.class);

	public static final long serialVersionUID = 2742602L;

	private ProgramWorkflowState currentState;
	private Form2ProgramCondition condition;
	private ProgramWorkflowState nextState;

	public Form2ProgramTransition( ) { }
				
	public void setCurrentState(ProgramWorkflowState currentState) {
		this.currentState = currentState;
	}
	
	public ProgramWorkflowState getCurrentState( ) {
		return this.currentState;
	}
	
	public void setCondition(Form2ProgramCondition condition) {
		this.condition = condition;
	}
	
	public Form2ProgramCondition getCondition( ) {
		return this.condition;
	}
	
	public void setNextState(ProgramWorkflowState nextState) {
		this.nextState = nextState;
	}

	public ProgramWorkflowState getNextState( ) {
		return this.nextState;
	}
		
		
}