package org.openmrs.module.form2program;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.EncounterType;
import org.openmrs.Program;
import org.openmrs.User;


public class Form2ProgramMap implements java.io.Serializable, Comparable<Form2ProgramMap> {

	public static final Log log = LogFactory.getLog(Form2ProgramMap.class);

	public static final long serialVersionUID = 2742601L;

	private Program program;
	private EncounterType encounterType;
	private User creator;
	private Date dateCreated;
	private User changedBy;
	private Date dateChanged;
	private Boolean applied = false;

	public Form2ProgramMap( ) { }
	
	public Form2ProgramMap(Program program, EncounterType encounterType){
		this.program = program;
		this.encounterType = encounterType;
	}
	
	public Form2ProgramMap(Program program, EncounterType encounterType, User creator){
		this.program = program;
		this.encounterType = encounterType;
		this.creator = creator;
		this.changedBy = creator;
		java.util.Date today = new java.util.Date();
		this.dateCreated = today;
		this.dateChanged = today;
	}

	public Form2ProgramMap(Program program, EncounterType encounterType, User creator, java.util.Date dateCreated){
		this.program = program;
		this.encounterType = encounterType;
		this.creator = creator;
		this.changedBy = creator;
		this.dateCreated = dateCreated;
		this.dateChanged = dateCreated;
	}

	public Form2ProgramMap(Program program, EncounterType encounterType, Boolean applied, User creator, java.util.Date dateCreated){
		this.program = program;
		this.encounterType = encounterType;
		this.applied = applied;
		this.creator = creator;
		this.changedBy = creator;
		this.dateCreated = dateCreated;
		this.dateChanged = dateCreated;
	}
		
	/** 
	 * Compares two objects for similarity
	 * 
	 * @param obj
	 * @return boolean true/false whether or not they are the same objects
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Form2ProgramMap) {
			Form2ProgramMap p = (Form2ProgramMap)obj;
			boolean ret = true;
			if (program != null && p.getProgram() != null)
				ret = ret && program.equals(p.getProgram());
			if (encounterType != null && p.getEncounterType() != null)
				ret = ret && encounterType.equals(p.getEncounterType());
			return ret;
		}
		return false;
	}
	
	public int compareTo(Form2ProgramMap f) {
		return (this.encounterType.getName()).compareTo(f.encounterType.getName());
	}
	
	public int hashCode() {
		if (this.getProgram() == null && this.getEncounterType() == null) return super.hashCode();
		int hash = 5;
		if (getProgram() != null)
			hash += 31 * hash + this.getProgram().hashCode();
		if (getEncounterType() != null)
			hash += 31 * hash + this.getEncounterType().hashCode();
		return hash;
	}
	
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	public Program getProgram( ) {
		return this.program;
	}
	
	public void setEncounterType(EncounterType encounterType) {
		this.encounterType = encounterType;
	}
	
	public EncounterType getEncounterType( ) {
		return this.encounterType;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getCreator( ) {
		return this.creator;
	}
	
	public void setDateCreated(java.util.Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public java.util.Date getDateCreated( ) {
		return this.dateCreated;
	}
	
	public void setChangedBy(User changedBy) {
		this.changedBy = changedBy;
	}
	
	public User getChangedBy( ) {
		return this.changedBy;
	}
	
	public void setDateChanged(java.util.Date dateChanged) {
		this.dateChanged = dateChanged;
	}
	
	public java.util.Date getDateChanged( ) {
		return this.dateChanged;
	}
	
	public void setApplied(Boolean applied) {
		this.applied = applied;
	}
	
	public Boolean getApplied( ) {
		return this.applied;
	}
	
	public Boolean isApplied( ) {
		return this.applied;
	}
	
}