package org.openmrs.module.form2program.extension.html;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

public class AdminList extends AdministrationSectionExt {

	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}
	
	public String getTitle() {
		return "form2program.title";
	}
	
	public Map<String, String> getLinks() {
		
		Map<String, String> map = new LinkedHashMap<String, String>();

		map.put("module/form2program/form2Program.form", "form2program.form.title");
		map.put("module/form2program/form2ProgramCondition.list", "form2program.condition.list.title");
		//map.put("module/form2program/form2ProgramCondition.htm", "form2program.condition.view.title");
		//map.put("module/form2program/form2ProgramCondition.form", "form2program.condition.form.title");
        map.put("module/form2program/form2ProgramTransition.list", "form2program.transition.list.title");
		map.put("module/form2program/form2ProgramTransition.form", "form2program.transition.form.title");
		
		return map;
	}
	
}
