package org.openmrs.module.form2program.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.EncounterType;
import org.openmrs.Program;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.form2program.Form2ProgramMap;
import org.openmrs.module.form2program.Form2ProgramService;
import org.openmrs.test.BaseModuleContextSensitiveTest;

/**
 * TODO: This test case needs updated to openmrs 1.3.0 code.
 *
 * @author bmckown
 */
public class Form2ProgramServiceTest extends BaseModuleContextSensitiveTest {
	
	//private Log log = LogFactory.getLog( );

	//@Override
	protected void onSetUpBeforeTransaction() throws Exception {
	  //super.onSetUpBeforeTransaction();
      
	  authenticate();
	}
	
	/**
	 * @throws Exception
	 */
	public void testForm2ProgramService() throws Exception {
		
		//startup();
		authenticate();

		// Get Form2ProgramService.
		Form2ProgramService service = (Form2ProgramService)Context.getService(Form2ProgramService.class);		
		// Get all programs.
		List<Program> programs = new ArrayList<Program>();
		programs.addAll(Context.getProgramWorkflowService().getPrograms());
		// Get all encounter types.
		List<EncounterType> encounterTypes = new ArrayList<EncounterType>();
		encounterTypes.addAll(Context.getEncounterService().getEncounterTypes());
		// Get User and today's date.
		//User superuser = (Context.getUserService().getUser(Integer.valueOf(1)));
		User user = Context.getAuthenticatedUser();
		Date today = new java.util.Date();
		// Create and save a Form2ProgramMap 
		Form2ProgramMap form2ProgramMap = new Form2ProgramMap();
		if (!programs.isEmpty() && !encounterTypes.isEmpty()) {
			form2ProgramMap.setProgram(programs.get(0));
			form2ProgramMap.setCreator(user);
			form2ProgramMap.setDateCreated(today);
			form2ProgramMap.setChangedBy(user);
			form2ProgramMap.setDateChanged(today);
			service.createForm2ProgramMap(form2ProgramMap);
			Form2ProgramMap test = service.getForm2ProgramMap(programs.get(0), encounterTypes.get(0));
			// TODO: Is System.out really what I want to use to test this?
			System.out.println("Program id: " + test.getProgram().getProgramId());
			System.out.println("EncounterType id: " + test.getEncounterType().getEncounterTypeId());
			// Finish by deleting the Form2ProgramMap.
			service.deleteForm2ProgramMap(form2ProgramMap);
		}
		
		//shutdown();
	}
	
}

