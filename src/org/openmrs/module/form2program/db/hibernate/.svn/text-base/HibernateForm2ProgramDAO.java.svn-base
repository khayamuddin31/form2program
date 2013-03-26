package org.openmrs.module.form2program.db.hibernate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.openmrs.EncounterType;
import org.openmrs.Program;
import org.openmrs.ProgramWorkflowState;
import org.openmrs.User;
import org.openmrs.module.form2program.Form2ProgramCondition;
import org.openmrs.module.form2program.Form2ProgramMap;
import org.openmrs.module.form2program.Form2ProgramTransition;
import org.openmrs.module.form2program.db.Form2ProgramDAO;

public class HibernateForm2ProgramDAO implements Form2ProgramDAO {

	protected final Log log = LogFactory.getLog(getClass());
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Hibernate session factory
	 */
	private SessionFactory sessionFactory;
	
	/**
	 * Default public constructor
	 */
	public HibernateForm2ProgramDAO() { }
	
	/**
	 * Set session factory
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) { 
		this.sessionFactory = sessionFactory;
	}
	
	public void createForm2ProgramMap(Form2ProgramMap form2ProgramMap) {
		this.sessionFactory.getCurrentSession().save(form2ProgramMap);
	}
	
	public void deleteForm2ProgramMap(Form2ProgramMap form2ProgramMap) {
		this.sessionFactory.getCurrentSession().delete(form2ProgramMap);
	}
	
	public void updateForm2ProgramMap(Form2ProgramMap form2ProgramMap) {
		this.sessionFactory.getCurrentSession().update(form2ProgramMap);
	}

	public void createForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition) {
		this.sessionFactory.getCurrentSession().save(form2ProgramCondition);
	}
	
	public void deleteForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition) {
		this.sessionFactory.getCurrentSession().delete(form2ProgramCondition);
	}
	
	public void updateForm2ProgramCondition(Form2ProgramCondition form2ProgramCondition) {
		this.sessionFactory.getCurrentSession().update(form2ProgramCondition);
	}
	
	public void createForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition) {
		this.sessionFactory.getCurrentSession().save(form2ProgramTransition);
	}
	
	public void deleteForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition) {
		this.sessionFactory.getCurrentSession().delete(form2ProgramTransition);
	}
	
	public void updateForm2ProgramTransition(Form2ProgramTransition form2ProgramTransition) {
		this.sessionFactory.getCurrentSession().update(form2ProgramTransition);
	}
	
	public List<Form2ProgramMap> getForm2ProgramMaps( ) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Form2ProgramMap.class, "f");
		criteria.addOrder(Order.asc("f.program"));
		criteria.addOrder(Order.asc("f.encounterType"));
		return (List<Form2ProgramMap>)criteria.list();
	}
	
	public List<Form2ProgramMap> getForm2ProgramMaps(Program program) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Form2ProgramMap.class, "f");
//		criteria.createAlias("encounterType", "e");
		criteria.add(Expression.eq("f.program", program));
		criteria.addOrder(Order.asc("f.encounterType"));
		List<Form2ProgramMap> form2programs = new ArrayList<Form2ProgramMap>();
		form2programs.addAll(criteria.list());
		return form2programs;	
	}
	
	public List<Program> getPrograms( ) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Form2ProgramMap.class);
		criteria.setProjection(Projections.distinct(Projections.property("program")));
		return (List<Program>)criteria.list();		
	}
	
	public List<EncounterType> getEncounterTypes(Program program) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Form2ProgramMap.class);
		criteria.add(Expression.eq("program", program));
		criteria.addOrder(Order.asc("encounterType"));
		return (List<EncounterType>)criteria.list();
	}

	public List<Form2ProgramMap> getForm2ProgramMaps(EncounterType encounterType) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Form2ProgramMap.class);
		criteria.add(Expression.eq("encounterType", encounterType));
		criteria.addOrder(Order.asc("program"));
		List<Form2ProgramMap> form2programs = new ArrayList<Form2ProgramMap>();
		form2programs.addAll(criteria.list());
		return form2programs;	
	}
	
	public boolean isApplied(Program program, EncounterType encounterType) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Form2ProgramMap.class);
		criteria.add(Expression.eq("program", program));
		if (encounterType != null)
			criteria.add(Expression.eq("encounterType", encounterType));
		criteria.add(Expression.ne("applied", true));
		// the following code throws a Hibernate exception
		// return !criteria.list().isEmpty();
		// so do this instead
		List<Form2ProgramMap> ret = criteria.list();
		if (ret == null)
			return false;
		return ret.isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see org.openmrs.module.form2program.db.Form2ProgramDAO#getForm2ProgramMap(org.openmrs.Program, org.openmrs.EncounterType)
	 */
	public List<Form2ProgramMap> getForm2ProgramMap(Program program, EncounterType encounterType) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Form2ProgramMap.class);
		criteria.add(Expression.eq("program", program));
		criteria.add(Expression.eq("encounterType", encounterType));
		criteria.addOrder(Order.asc("encounterType"));
		return criteria.list();
	}
	
	/*
	 * Apply Form2ProgramMap to patients that are already in the database who have not yet been enrolled in the program.
	 * This applies to all org.openmrs.EncounterType that are mapped to the @param program.
	 * 
	 * Enrollment date is the encounter datetime of a patient's first encounter with an encounter type mapped to 
	 * the @param program.
	 * 
	 * @see org.openmrs.module.form2program.db.Form2ProgramDAO#applyForm2ProgramMap(org.openmrs.Program, org.openmrs.User, java.util.Date)
	 */
	public int applyForm2ProgramMap(Program program, User user, java.util.Date day) {
        String query = "INSERT INTO patient_program " +
        "(patient_id, program_id, date_enrolled, creator, date_created, changed_by, date_changed) " +
        "SELECT DISTINCT encounter.patient_id, " + program.getProgramId() + ", encounter.encounter_datetime, " + user.getUserId() +
        ", '" + df.format(day) + "', " + user.getUserId() + ", '" + df.format(day) + "' FROM encounter WHERE encounter.encounter_type in  " +
        "(select form2program_map.encounter_type from form2program_map where form2program_map.program = " + program.getProgramId() + 
        ") AND encounter.patient_id not in (select patient_id from patient_program where patient_program.program_id = " + program.getProgramId() +
        " and patient_program.date_completed is null ) " + 
        "AND encounter.encounter_datetime = (select min(e.encounter_datetime) from encounter e where e.patient_id = encounter.patient_id and e.encounter_type in " + 
        "( select form2program_map.encounter_type from form2program_map where form2program_map.program = " + program.getProgramId() + " ) ) " +
        "GROUP BY encounter.patient_id ORDER BY encounter.encounter_datetime ASC";
	    	    
		int rows = 0;
		try {
			rows = this.sessionFactory.getCurrentSession().connection().prepareStatement(query).executeUpdate();
			String applied = "UPDATE form2program_map set applied = true where program = " + program.getProgramId().toString();
			this.sessionFactory.getCurrentSession().connection().prepareStatement(applied).executeUpdate();
		}
		catch (Exception e){
			log.error(e.getMessage());
		}
		return rows;
	}

	/*
	 * Apply Form2ProgramMap to patients that are already in the database who have not yet been enrolled in the program.
	 * This applies only to the @param org.openmrs.EncounterType that is mapped to the @param Program.
	 * 
     * Enrollment date is the encounter datetime of a patient's first encounter with an encounter type 
     * equal to @param encounterType.
	 * 
	 * (non-Javadoc)
	 * @see org.openmrs.module.form2program.db.Form2ProgramDAO#applyForm2ProgramMap(org.openmrs.Program, org.openmrs.EncounterType, org.openmrs.User, java.util.Date)
	 */
	public int applyForm2ProgramMap(Program program, EncounterType encounterType, User user, java.util.Date day) {
        String query = "INSERT INTO patient_program " +
        "(patient_id, program_id, date_enrolled, creator, date_created, changed_by, date_changed) " +
        "SELECT DISTINCT encounter.patient_id, " + program.getProgramId() + ", encounter.encounter_datetime, " + user.getUserId() +
        ", '" + df.format(day) + "', " + user.getUserId() + ", '" + df.format(day) + "' FROM encounter WHERE encounter.encounter_type = " + encounterType.getEncounterTypeId() + 
        " AND encounter.patient_id not in (select patient_id from patient_program where patient_program.program_id = " + program.getProgramId() +
        " and patient_program.date_completed is null ) " + 
        "AND encounter.encounter_datetime = (select min(e.encounter_datetime) from encounter e where e.patient_id = encounter.patient_id and e.encounter_type = " + 
        encounterType.getEncounterTypeId() + " ) GROUP BY encounter.patient_id ORDER BY encounter.encounter_datetime ASC";
	    
	int rows = 0;
	try {
		rows = this.sessionFactory.getCurrentSession().connection().prepareStatement(query).executeUpdate();
		String applied = "UPDATE form2program_map set applied = true where program = " + program.getProgramId().toString();
		this.sessionFactory.getCurrentSession().connection().prepareStatement(applied).executeUpdate();
	}
	catch (Exception e){
		log.error(e.getMessage());
	}
	return rows;
	}
	
	public List<Form2ProgramCondition> getConditions(Integer conditionId) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Form2ProgramCondition.class);
		if (conditionId != null)
			criteria.add(Expression.eq("conditionId", conditionId));
		return criteria.list();
	}
	
	public List<Form2ProgramTransition> getTransitions(ProgramWorkflowState currentState, Form2ProgramCondition condition) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Form2ProgramTransition.class);
		if (currentState != null)
			criteria.add(Expression.eq("currentState", currentState));
		if (condition != null)
			criteria.add(Expression.eq("condition", condition));
		return criteria.list();
	}
				
}
