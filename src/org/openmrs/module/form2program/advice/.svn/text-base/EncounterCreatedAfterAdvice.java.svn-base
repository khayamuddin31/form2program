package org.openmrs.module.form2program.advice;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Patient;
import org.openmrs.api.ProgramWorkflowService;
import org.openmrs.api.context.Context;
import org.openmrs.module.form2program.Form2ProgramMap;
import org.openmrs.module.form2program.Form2ProgramService;
import org.springframework.aop.AfterReturningAdvice;

public class EncounterCreatedAfterAdvice implements AfterReturningAdvice {

	private Log log = LogFactory.getLog(this.getClass());

	private int count = 0;

	private Form2ProgramService getForm2ProgramService() {
		return (Form2ProgramService)Context.getService(Form2ProgramService.class);
	}
	
	private ProgramWorkflowService getProgramWorkflowService() {
		//TODO: checkPermission
		return Context.getProgramWorkflowService();
	}
	
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		// log.debug("Method: " + method.getName() + ". After advice called " + (++count) + " time(s) now.");
		if (method.getName().endsWith("encounterCreated")) {
			//log.debug("METHOD: " + method.getName());
			//log.debug("PARAMETER_TYPE: " + method.getParameterTypes().getClass().getName().toString());
			//log.debug("RETURN: " + returnValue.toString());
			for (int i = 0; i < args.length; i++) {
				//log.debug("ARGS[" + i + "]: " + args[i].toString());
			}
			Form2ProgramService f2pService = this.getForm2ProgramService();
			//log.error("TARGET: " + target.toString());
			java.util.Date today = new java.util.Date();
			Encounter encounter = (Encounter)args[0];
			Patient patient = encounter.getPatient();
			EncounterType encounterType = encounter.getEncounterType();
//			List<Form2ProgramMap> form2ProgramMaps = getForm2ProgramService().getForm2ProgramMaps(encounterType);
			List<Form2ProgramMap> form2ProgramMaps = getForm2ProgramService().getForm2ProgramMaps();
			for (Form2ProgramMap fpm : form2ProgramMaps) {
				// TODO: Check HibernateProgramWorkflowDAO fromDate and toDate to make sure they are not reversed.
				if (f2pService.existsForm2ProgramMap(fpm.getProgram(), encounterType))
					if (!this.getProgramWorkflowService().isInProgram(patient, fpm.getProgram(), today, null)) {
						this.getProgramWorkflowService().enrollPatientInProgram(patient, fpm.getProgram(), encounter.getEncounterDatetime(), null, encounter.getCreator());
				}
			}
		}
	}
	
}