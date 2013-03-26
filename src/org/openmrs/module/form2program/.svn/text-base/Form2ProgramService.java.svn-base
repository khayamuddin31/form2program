package org.openmrs.module.form2program;

import java.util.Date;
import java.util.List;

import org.openmrs.EncounterType;
import org.openmrs.Patient;
import org.openmrs.Program;
import org.openmrs.ProgramWorkflowState;
import org.openmrs.User;
import org.openmrs.module.form2program.db.Form2ProgramDAO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface Form2ProgramService {

	public void setForm2ProgramDAO(Form2ProgramDAO dao);

	public void createForm2ProgramMap(Form2ProgramMap form2ProgramMap);
	
	public void deleteForm2ProgramMap(Form2ProgramMap form2ProgramMap);
	
	public void updateForm2ProgramMap(Form2ProgramMap form2ProgramMap);

	public void createForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition);
	
	public void deleteForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition);
	
	public void updateForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition);
	
	public void createForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition);
	
	public void deleteForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition);
	
	public void updateForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition);
		
	@Transactional(readOnly=true)
	public List<Form2ProgramMap> getForm2ProgramMaps();
	
	@Transactional(readOnly=true)
	public List<Form2ProgramMap> getForm2ProgramMaps(Program program);

	@Transactional(readOnly=true)
	public List<Form2ProgramMap> getForm2ProgramMaps(EncounterType encounterType);
	
	@Transactional(readOnly=true)
	public Form2ProgramMap getForm2ProgramMap(Program program, EncounterType encounterType);
	
	@Transactional(readOnly=true)
	public boolean existsForm2ProgramMap(Program program, EncounterType encounterType);
	
	public void setPatientProgram(Patient patient);
	
	public int applyForm2ProgramMap(Program program, User user, Date day);
	
	public int applyForm2ProgramMap(Program program, EncounterType encounterType, User user, Date day);
	
	public List<Program> getPrograms( );
	
	public List<EncounterType> getEncounterTypes(Program program);
	
	public boolean isApplied(Program program, EncounterType encounterType);
	
	public List<Form2ProgramCondition> getConditions(Integer conditionId);
	
	public List<Form2ProgramTransition> getTransitions(ProgramWorkflowState currentState, Form2ProgramCondition condition );
	
}