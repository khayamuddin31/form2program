package org.openmrs.module.form2program;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.Drug;


public class Form2ProgramCondition {

	public static final Log log = LogFactory.getLog(Form2ProgramCondition.class);

	//public static final long serialVersionUID = 2742601L;

	private Integer conditionId;
	private Concept conceptId;
	private Concept valueCoded;
	private Drug valueDrug;
	private Date valueDatetime;
	private Double valueNumeric;
	private String valueModifier;
	private String valueText;
	private String operator;
	
	public Form2ProgramCondition( ) { }
	
	public void setConditionId(Integer conditionId) {
		this.conditionId = conditionId;
	}
	
	public Integer getConditionId( ) {
		return this.conditionId;
	}
	
	public void setConceptId(Concept conceptId) {
		this.conceptId = conceptId;
	}
	
	public Concept getConceptId( ) {
		return this.conceptId;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getOperator( ) {
		return this.operator;
	}
	
	public void setValueCoded(Concept valueCoded) {
		this.valueCoded = valueCoded;
	}
	
	public Concept getValueCoded( ) {
		return this.valueCoded;
	}
	
	public void setValueDrug(Drug valueDrug) {
		this.valueDrug = valueDrug;
	}
	
	public Drug getValueDrug( ) {
		return this.valueDrug;
	}

	public void setValueDatetime(Date valueDatetime) {
		this.valueDatetime = valueDatetime;
	}
	
	public Date getValueDatetime( ) {
		return this.valueDatetime;
	}
	
	public void setValueNumeric(Double valueNumeric) {
		this.valueNumeric = valueNumeric;
	}
	
	public Double getValueNumeric( ) {
		return this.valueNumeric;
	}

	public void setValueModifier(String valueModifier) {
		this.valueModifier = valueModifier;
	}
	
	public String getValueModifier( ) {
		return this.valueModifier;
	}

	public void setValueText(String valueText) {
		this.valueText = valueText;
	}
	
	public String getValueText( ) {
		return this.valueText;
	}
		
}