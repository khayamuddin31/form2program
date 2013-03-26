package org.openmrs.module.form2program.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.EncounterType;
import org.openmrs.Program;
import org.openmrs.User;
import org.openmrs.api.EncounterService;
import org.openmrs.api.ProgramWorkflowService;
import org.openmrs.api.context.Context;
import org.openmrs.module.form2program.Form2ProgramCondition;
import org.openmrs.module.form2program.Form2ProgramMap;
import org.openmrs.module.form2program.Form2ProgramService;
import org.openmrs.web.WebConstants;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * This controller backs and saves the printing module printer settings
 * 
 */
public class Form2ProgramConditionListController extends SimpleFormController {
	
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
     * Get the ProgramWorkflowService from Context
     * @return ProgramWorkflowService
     */
    private ProgramWorkflowService getProgramWorkflowService() {
    	return (ProgramWorkflowService)Context.getProgramWorkflowService();
    }
    
    /**
     * Get the EncounterService from Context
     * @return EncounterService
     */
    private EncounterService getEncounterService() {
    	return (EncounterService)Context.getEncounterService();
    }
     
    	
    @Override
	protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse response, Object object, BindException exceptions) throws Exception {

    	return new ModelAndView(new RedirectView(getSuccessView()));						
    	
    }

    @Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	List<Form2ProgramCondition> form2ProgramConditions = new ArrayList<Form2ProgramCondition>();    	
    	if (Context.isAuthenticated()) {
    		form2ProgramConditions = getForm2ProgramService().getConditions(null);
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
		
		return map;
	}

	
	
	
}