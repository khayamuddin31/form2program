package org.openmrs.module.form2program.db;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.openmrs.EncounterType;
import org.openmrs.Program;
import org.openmrs.ProgramWorkflowState;
import org.openmrs.User;
import org.openmrs.module.form2program.Form2ProgramCondition;
import org.openmrs.module.form2program.Form2ProgramMap;
import org.openmrs.module.form2program.Form2ProgramTransition;


public interface Form2ProgramDAO {

	public void setSessionFactory(SessionFactory sessionFactory);
	
	public void createForm2ProgramMap(Form2ProgramMap form2ProgramMap);
	
	public void deleteForm2ProgramMap(Form2ProgramMap form2ProgramMap);
	
	public void updateForm2ProgramMap(Form2ProgramMap form2ProgramMap);
	
	public void createForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition);
	
	public void deleteForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition);
	
	public void updateForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition);
	
	public void createForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition);
	
	public void deleteForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition);
	
	public void updateForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition);
	
	public List<Form2ProgramMap> getForm2ProgramMaps( );
	
	public List<Form2ProgramMap> getForm2ProgramMaps(Program program);

	public List<Form2ProgramMap> getForm2ProgramMaps(EncounterType encounterType);
	
	public List<Form2ProgramMap> getForm2ProgramMap(Program program, EncounterType encounterType);
	
	public int applyForm2ProgramMap(Program program, User user, Date day);
	
	public int applyForm2ProgramMap(Program program, EncounterType encounterType, User user, Date day);

	public boolean isApplied(Program program, EncounterType encounterType);

	public List<Program> getPrograms( );
	
	public List<EncounterType> getEncounterTypes(Program program);
	
	public List<Form2ProgramCondition> getConditions(Integer conditionId );
	
	public List<Form2ProgramTransition> getTransitions(ProgramWorkflowState currentState, Form2ProgramCondition condition);
}
