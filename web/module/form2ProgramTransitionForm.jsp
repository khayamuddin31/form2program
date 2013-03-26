<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Manage Programs" otherwise="/login.htm" redirect="/module/form2program/form2Transition.form" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/programs/localHeader.jsp" %>
<style type="text/css">
ul.petStyle {
	text-decoration: none;				
	display: block;				
}

ul.petStyle li {
	display: block;				
	text-decoration: none;				
}

</style>

<h2><spring:message code="form2program.form.title"/></h2>

<br />

<b class="boxHeader"><spring:message code="form2program.form.title"/></b>
<form method="post" action="" name="f2pFormManage" >
<div class="box">
	<c:if test="${fn:length(form2program) == 0}">
		<tr>
			<td colspan="5"><spring:message code="general.none"/></td>
		</tr>
	</c:if>
	<c:if test="${fn:length(form2program) != 0}">
		<input type="hidden" name="programAction" id="programAction" value=""/>
		<input type="hidden" name="encounterTypeAction" id="encounterTypeAction" value=""/>
		<c:set var="needUpdate" value='false'/>
		<table cellspacing="0" cellpadding="2">
			<tr>
				<th> <spring:message code="Program.program"/> </th>
				<th> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="Encounter.type"/> </th>
				<th></th>
			</tr>
			<c:forEach var="pet" items="${programMap}" varStatus="pVarStat">
			<tr>
				<td valign=top>
					<table>
							<tr>	
								<td>
									<b>${pet.key.programId}</b>&nbsp;&nbsp;${pet.key.concept.name}
								<td>
							</tr>
							<tr>	
								<td> 
								<c:forEach var="pa" items="${programApplied}" varStatus="aVarStat">
								<c:if test="${pa.key.programId == pet.key.programId}">
								<c:if test="${pa.value == 'false'}">
								<input type="submit" id="${pet.key.programId}" name="update" value="Update" onclick="setForm2ProgramMap('${pet.key.programId}',null)"/>
								</c:if>
								</c:if>
								</c:forEach>
								<td>
							</tr>
					</table>
				</td>
				<td valign=top>
					<c:forEach var="petVal" items="${pet.value}" varStatus="eVarStat">
					<table><tr><td>
					<input type="submit" value="X" id="${pet.key.programId}.{petVal.encounterType.name}" name="delete" class="closeButton" onclick="remove('${pet.key.programId}','${petVal.encounterType.encounterTypeId}')" />
					</td><td>
					&nbsp;&nbsp;${petVal.encounterType.name}
					<c:if test="${petVal.applied == 'false'}"><sup>*</sup><c:set var="needUpdate" value='true'/></c:if>
					</td></tr></table>
				</c:forEach>
				</td>
			</tr>
			<tr><td></td></tr>
			<tr><td>
			</td></tr>
			</c:forEach>
			<tr><td></td><td>
					<c:if test="${needUpdate}"><sup>*</sup><spring:message code="form2program.needUpdate"/></c:if>
			</td></tr>
		</table>
	</c:if>
</div>
</form>
<br/>
<form method="post" action="" name="f2pFormSubmit" >
<b class="boxHeader"><spring:message code="form2program.add"/></b>
	<div class=box>
		<table>	
				<th> <spring:message code="Program.program"/> </th>
				<th> <spring:message code="Encounter.type"/> </th>
				<th></th>
				<th></th>
		<tr>
		</tr>
		<tr>
			<td>
				<select name="selectProgramId">
					<option value=""></option>
					<openmrs:forEachRecord name="workflowProgram">
						<option value="${record.programId}">${record.concept.name}</option>
					</openmrs:forEachRecord>
				</select>
			</td>
			<td>
				<select name="selectEncounterTypeId">
					<option value=""></option>
					<openmrs:forEachRecord name="encounterType">
						<option value="${record.encounterTypeId}">${record.name}</option>
					</openmrs:forEachRecord>
				</select>
			</td>
			<td>
			</td>
			<td>
				<input type="submit" id="addAction" name="action" value="<spring:message code='form2program.add'/>" />
			</td>
			<td>
			</td>
		</tr>
	</table>
</div>
</form>


<script type="text/javascript">

	function remove(p,e) {
		var prog = document.getElementById("programAction");
		var enct = document.getElementById("encounterTypeAction");
		var confirmation = "<spring:message code='form2program.delete.confirm'/>";
		if (confirm(confirmation)) { 
			prog.value = p;
			enct.value = e;
			form.submit();
		}
		else {
			prog.value = -1;
			enct.value = -1;
		}
	}

	function setForm2ProgramMap(p,e) {
		var prog = document.getElementById("programAction");
		var enct = document.getElementById("encounterTypeAction");
		prog.value = p;
		enct.value = e;
		form.submit();
	}

</script>
<%@ include file="/WEB-INF/template/footer.jsp" %>
