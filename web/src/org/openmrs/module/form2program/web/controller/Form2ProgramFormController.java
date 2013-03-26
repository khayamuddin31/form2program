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
public class Form2ProgramFormController extends SimpleFormController {
	
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

    	HttpSession httpSession = req.getSession();
		MessageSourceAccessor msa = getMessageSourceAccessor();
		Form2ProgramService form2ProgramService = getForm2ProgramService();
		Program program;
		EncounterType encounterType;
		User creator = Context.getAuthenticatedUser();
		Date today = new java.util.Date();    	
		
		String action = req.getParameter("action");
		String update = req.getParameter("update");
		String delete = req.getParameter("delete");
		String programAction = req.getParameter("programAction");
		String encounterTypeAction = req.getParameter("encounterTypeAction");
		String testOut = programAction + " " + encounterTypeAction;
		if (null != update) {
//			httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "update" + testOut);
			String selectedProgram = req.getParameter("programAction");
			if ("".equals(selectedProgram)) {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "form2program.error.makeSelection");
			}
			Integer programId = Integer.valueOf(selectedProgram);
			program = getProgramWorkflowService().getProgram(programId);
			if (form2ProgramService.applyForm2ProgramMap(program, creator, today) > 0) {
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "form2program.update.success");
			}
			else {
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "form2program.update.none");				
			}
		}
		else if (null != delete) {
//			httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "delete" + testOut);
			String selectedEncounterType = req.getParameter("encounterTypeAction");
			String selectedProgram = req.getParameter("programAction");
			if ("".equals(selectedEncounterType) || "".equals(selectedProgram)) {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "form2program.error.makeSelection");
			}
			if (selectedProgram.contains("-1")) {
				return new ModelAndView(new RedirectView(getSuccessView()));
			}
			Integer encounterTypeId = Integer.valueOf(selectedEncounterType);
			Integer programId = Integer.valueOf(selectedProgram);
			program = getProgramWorkflowService().getProgram(programId);
			encounterType = getEncounterService().getEncounterType(encounterTypeId);			
    		if (form2ProgramService.existsForm2ProgramMap(program, encounterType))
    			form2ProgramService.deleteForm2ProgramMap(form2ProgramService.getForm2ProgramMap(program, encounterType));
    		else 
    			httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR , "form2program.error.notExists");
		}
		else if (null != action) {
			String selectedEncounterType = req.getParameter("selectEncounterTypeId");
			String selectedProgram = req.getParameter("selectProgramId");
			if (("" == selectedEncounterType) || ("" == selectedProgram)) {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "form2program.error.makeSelection");
		    	return new ModelAndView(new RedirectView(getSuccessView()));						
			}
			Integer encounterTypeId = Integer.valueOf(selectedEncounterType);
			Integer programId = Integer.valueOf(selectedProgram);
			program = getProgramWorkflowService().getProgram(programId);
			encounterType = getEncounterService().getEncounterType(encounterTypeId);			
    		if (form2ProgramService.existsForm2ProgramMap((Program)getProgramWorkflowService().getProgram(programId), encounterType)) {
    			httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "form2program.error.alreadyExists");
    			return new ModelAndView(new RedirectView(getSuccessView()));
    		}
    		Form2ProgramMap f2pMap = new Form2ProgramMap(getProgramWorkflowService().getProgram(programId), encounterType, creator, today);
    		form2ProgramService.createForm2ProgramMap(f2pMap);
		}
		else {
			httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "none of above" + testOut);
	    	return new ModelAndView(new RedirectView(getSuccessView()));						
		}
    	return new ModelAndView(new RedirectView(getSuccessView()));						
    	
    }

    @Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	List<Form2ProgramMap> form2ProgramMaps = new ArrayList<Form2ProgramMap>();    	
    	if (Context.isAuthenticated()) {
    		form2ProgramMaps = getForm2ProgramService().getForm2ProgramMaps();
    	}
	return form2ProgramMaps;
	
		
	}

	@Override
	protected Map referenceData(HttpServletRequest arg0) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<Program, Boolean> programApplied = new HashMap<Program, Boolean>();
		Map<Program, Integer> programCardinality = new HashMap<Program, Integer>();
		Map<Program, List<EncounterType>> programEncounterType = new LinkedHashMap<Program, List<EncounterType>>();
		Map<Program, List<Form2ProgramMap>> programMap = new LinkedHashMap<Program, List<Form2ProgramMap>>();
		Form2ProgramService form2ProgramService = getForm2ProgramService();
		List<Program> programList = form2ProgramService.getPrograms();
		for (Program p : programList) {
			programApplied.put(p, Boolean.valueOf(form2ProgramService.isApplied(p, null)));
			programCardinality.put(p, Integer.valueOf(form2ProgramService.getEncounterTypes(p).size()));
			List<EncounterType> eList = new ArrayList<EncounterType>();
			eList.addAll(form2ProgramService.getEncounterTypes(p));
			programEncounterType.put(p, eList);
			List<Form2ProgramMap> fList = new ArrayList<Form2ProgramMap>();
			fList.addAll(form2ProgramService.getForm2ProgramMaps(p));
			Collections.sort(fList);
			programMap.put(p, fList);
		}
		
		map.put("programApplied", programApplied);
		map.put("programCardinality", programCardinality);
		map.put("programEncounterType", programEncounterType);
		map.put("programMap", programMap);
		
		return map;
	}

	
	
	
}