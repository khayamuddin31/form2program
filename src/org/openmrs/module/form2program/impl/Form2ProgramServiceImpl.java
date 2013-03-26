package org.openmrs.module.form2program.impl;


import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.EncounterType;
import org.openmrs.Patient;
import org.openmrs.Program;
import org.openmrs.ProgramWorkflowState;
import org.openmrs.User;
import org.openmrs.api.ProgramWorkflowService;
import org.openmrs.api.context.Context;
import org.openmrs.module.form2program.Form2ProgramCondition;
import org.openmrs.module.form2program.Form2ProgramMap;
import org.openmrs.module.form2program.Form2ProgramService;
import org.openmrs.module.form2program.Form2ProgramTransition;
import org.openmrs.module.form2program.db.Form2ProgramDAO;
import org.springframework.transaction.annotation.Transactional;

/**
 * Printing-related services
 */
public class Form2ProgramServiceImpl implements Form2ProgramService {

	private Log log = LogFactory.getLog(this.getClass());
	
	private Form2ProgramDAO dao;
	
	/**
	 * Default constructor
	 */
	public Form2ProgramServiceImpl() { }

	private ProgramWorkflowService getProgramWorkflowService() {
		//TODO: create method to checkPrivilege
		return Context.getProgramWorkflowService();
    }
	
	public void setForm2ProgramDAO(Form2ProgramDAO dao) {
		this.dao = dao;
	}
	
	public void createForm2ProgramMap(Form2ProgramMap form2ProgramMap) {
		this.dao.createForm2ProgramMap(form2ProgramMap);
	}
	
	public void deleteForm2ProgramMap(Form2ProgramMap form2ProgramMap) {
		this.dao.deleteForm2ProgramMap(form2ProgramMap);
	}
	
	public void updateForm2ProgramMap(Form2ProgramMap form2ProgramMap) {
		this.dao.updateForm2ProgramMap(form2ProgramMap);
	}

	public void createForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition) {
		this.dao.createForm2ProgramCondition(form2ProgramCondition);
	}
	
	public void deleteForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition) {
		this.dao.deleteForm2ProgramCondition(form2ProgramCondition);
	}
	
	public void updateForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition) {
		this.dao.updateForm2ProgramCondition(form2ProgramCondition);
	}
	
	public void createForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition) {
		this.dao.createForm2ProgramTransition(form2ProgramTransition);
	}
	
	public void deleteForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition) {
		this.dao.deleteForm2ProgramTransition(form2ProgramTransition);
	}
	
	public void updateForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition) {
		this.dao.updateForm2ProgramTransition(form2ProgramTransition);
	}
	
	@Transactional(readOnly=true)
	public List<Form2ProgramMap> getForm2ProgramMaps() {
		return dao.getForm2ProgramMaps();
	}
	
	@Transactional(readOnly=true)
	public List<Form2ProgramMap> getForm2ProgramMaps(Program program) {
		return dao.getForm2ProgramMaps(program);
	}

	@Transactional(readOnly=true)
	public List<Form2ProgramMap> getForm2ProgramMaps(EncounterType encounterType) {
		return dao.getForm2ProgramMaps(encounterType);
	}
	
	@Transactional(readOnly=true)
	public Form2ProgramMap getForm2ProgramMap(Program program, EncounterType encounterType) {
		List<Form2ProgramMap> form2ProgramMaps = dao.getForm2ProgramMap(program, encounterType);
		if (form2ProgramMaps.isEmpty())
			return null;
		return form2ProgramMaps.get(0);
	}
	
	@Transactional(readOnly=true)
	public boolean existsForm2ProgramMap(Program program, EncounterType encounterType) {
		if (dao.getForm2ProgramMap(program, encounterType).isEmpty())
				return false;
		return true;
	}
		
	public void setPatientProgram(Patient patient) {
	}

	public int applyForm2ProgramMap(Program program, User user, Date day) {
		int rows = dao.applyForm2ProgramMap(program, user, day);
		return rows;
	}

	public int applyForm2ProgramMap(Program program, EncounterType encounterType, User user, Date day) {
		int rows = dao.applyForm2ProgramMap(program, encounterType, user, day);
		return rows;
	}
	
	public List<Program> getPrograms( ) {
		List<Program> programs = dao.getPrograms();
		return programs;
	}
	
	public List<EncounterType> getEncounterTypes(Program program) {
		List<EncounterType> encounterTypes = dao.getEncounterTypes(program);
		return encounterTypes;
	}
	
	public boolean isApplied(Program program, EncounterType encounterType) {
		return dao.isApplied(program, encounterType);
	}
	
	public List<Form2ProgramCondition> getConditions(Integer conditionId ) {
		return dao.getConditions(conditionId);
	}
	
	public List<Form2ProgramTransition> getTransitions(ProgramWorkflowState currentState, Form2ProgramCondition condition ) {
		return dao.getTransitions(currentState, condition);
	}
	
}
