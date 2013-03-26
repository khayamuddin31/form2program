package org.openmrs.module.form2program.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptDatatype;
import org.openmrs.Drug;
import org.openmrs.Program;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.form2program.Form2ProgramCondition;
import org.openmrs.module.form2program.Form2ProgramMap;
import org.openmrs.module.form2program.Form2ProgramService;
import org.openmrs.web.WebConstants;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * This controller backs and saves the printing module printer settings
 * 
 */
public class Form2ProgramConditionFormController extends SimpleFormController {
	
    /** Logger for this class and subclasses */
    protected final Log log = LogFactory.getLog(getClass());
    
    /**
     * Get the printing service from the Context
     * @return Form2ProgramService
     */
    private Form2ProgramService getForm2ProgramService() {
    	return (Form2ProgramService)Context.getService(Form2ProgramService.class);
    }
        
    /**
     * Get the EncounterService from Context
     * @return EncounterService
     */
    private ConceptService getConceptService() {
    	return (ConceptService)Context.getConceptService();
    
    }
     
    	
    @Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object object, BindException exceptions) throws Exception {
    	HttpSession httpSession = request.getSession();
    	MessageSourceAccessor msa = getMessageSourceAccessor();
    	String action = request.getParameter("action");
    	
    	if (msa.getMessage("general.save").equals(action)) {

    		Form2ProgramCondition condition = new Form2ProgramCondition();
        	try {
        	    Integer conceptId = ServletRequestUtils.getIntParameter(request, "conceptId");
                Concept concept = getConceptService().getConcept(conceptId);
                condition.setConceptId(concept);
        	}
        	catch (Exception e) {
                httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "form2program.condition.error.concept");
                return this.showForm(request, response, exceptions);        	    
        	}
        	try {
        	    String operator = ServletRequestUtils.getStringParameter(request, "operator");
                condition.setOperator(operator);
        	}
        	catch (Exception e) {
        	}
        	try {
        	    Integer valueCoded = ServletRequestUtils.getIntParameter(request, "valueCoded");
                Concept coded = getConceptService().getConcept(valueCoded);
                condition.setValueCoded(coded);
        	}
        	catch (Exception e){
        	}
        	try {
        	    Integer valueDrug = ServletRequestUtils.getIntParameter(request, "valueDrug");
                Drug drug = getConceptService().getDrug(valueDrug);
                condition.setValueDrug(drug);
        	}
        	catch (Exception e) {
        	}
        	try {
        	    String stringDatetime = ServletRequestUtils.getStringParameter(request, "valueDatetime");
                java.util.Date valueDatetime = Context.getDateFormat().parse(stringDatetime);
                condition.setValueDatetime(valueDatetime);
        	}
        	catch (Exception e) {
        	}
        	try {
        	    Double valueNumeric = ServletRequestUtils.getDoubleParameter(request, "valueNumeric");
                condition.setValueNumeric(valueNumeric);
        	}
        	catch (Exception e) {
        	}
        	try {
        	    String valueModifier = ServletRequestUtils.getStringParameter(request, "valueModifier");
                condition.setValueModifier(valueModifier);
        	}
        	catch (Exception e) {
        	}
        	try {
        	    String valueText = ServletRequestUtils.getStringParameter(request, "valueText");
                condition.setValueText(valueText);
        	}
        	catch (Exception e) {
        	}
        	
    		getForm2ProgramService().createForm2ProgramCondition(condition);
    		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "form2program.condition.save.success");
    		// TODO: Probably need to redirect to the form2programConditionList page.
    		return new ModelAndView(new RedirectView(getSuccessView()));
    	}
    	if (msa.getMessage("general.cancel").equals(action)) {
    		// TODO: Probably need to redirect to the form2programConditionList page.
    		return new ModelAndView(new RedirectView(getSuccessView()));
    	}
    	if (msa.getMessage("general.delete").equals(action)) {
        	Integer conditionId = ServletRequestUtils.getIntParameter(request, "conditionId");
    		List<Form2ProgramCondition> conditions = getForm2ProgramService().getConditions(conditionId);
    		getForm2ProgramService().deleteForm2ProgramCondition(conditions.get(0));
    		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "form2program.condition.delete.success");
    		// TODO: Probably need to redirect to the form2programConditionList page.
    		return new ModelAndView(new RedirectView(getSuccessView()));
    	}

    	return new ModelAndView(new RedirectView(getSuccessView()));						
    	
    }

    @Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	List<Form2ProgramCondition> form2ProgramConditions = new ArrayList<Form2ProgramCondition>();
    	Integer condition = -1;
    	try {
            condition = ServletRequestUtils.getIntParameter(request, "conditionId");
    	}
    	catch (ServletRequestBindingException srbe){
    	    // nevermind
    	}
    	if (Context.isAuthenticated()) {
    		if (null != condition)
    			form2ProgramConditions = getForm2ProgramService().getConditions(condition);
    		else {
    			// TODO: Does this redirect to the 'create new condition' page?
    			//return new ModelAndView(new RedirectView(getSuccessView()));
    		    form2ProgramConditions.add(new Form2ProgramCondition());
    		}
    	}
    	return form2ProgramConditions;		
	}

	@Override
	protected Map referenceData(HttpServletRequest arg0) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<Program, List<Form2ProgramMap>> programMap = new LinkedHashMap<Program, List<Form2ProgramMap>>();
		Form2ProgramService form2ProgramService = getForm2ProgramService();
		List<Program> programList = form2ProgramService.getPrograms();
		for (Program p : programList) {
			List<Form2ProgramMap> fList = new ArrayList<Form2ProgramMap>();
			fList.addAll(form2ProgramService.getForm2ProgramMaps(p));
			Collections.sort(fList);
			programMap.put(p, fList);
		}
		
		map.put("programMap", programMap);
		//map.put("datatypes", (List<ConceptDatatype>)getConceptService().getConceptDatatypes());
		
		return map;
	}
	
}