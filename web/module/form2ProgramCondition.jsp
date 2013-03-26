<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Manage Programs" otherwise="/login.htm" redirect="/module/form2program/form2Program.htm" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/programs/localHeader.jsp" %>

<style type="text/css">
.inlineForm {
    padding: 0px;
    margin: 0px;
    display: inline;
}
</style>

<c:choose>
    <c:when test="${param.conditionId != null}">
    <h2><spring:message code="form2program.condition.view" />&nbsp;<spring:message code="form2program.condition.for" /></h2>
    </c:when>
    <c:otherwise>
        <h2><spring:message code="form2program.condition.none" /></h2>
    </c:otherwise>
</c:choose>

<c:if test="${param.conditionId != null}">
    <form class="inlineForm" id="jumpForm" action="" method="post">
        <input type="hidden" name="jumpAction" id="jumpAction" value="previous"/>
        <a href="#previousCondition" id="previousCondition" valign="middle" accesskey="," onclick="return jumpToCondition('previous')"><spring:message code="general.previous"/></a>
            |
        <a href="form2ProgramCondition.form?conditionId=${param.conditionId}" id="editCondition" accesskey="v"><spring:message code="general.edit"/></a>
            |
        <a href="#nextCondition" id="nextCondition" valign="middle" accesskey="." onclick="return jumpToCondition('next')"><spring:message code="general.next"/></a>
            |
    </form>
<a href="form2ProgramCondition.form" id="newCondition" valign="middle"><spring:message code="general.new"/></a>
</c:if>

<br />
<br />

<form method="post" action="" name="f2pFormManage" >
        <c:set var="needUpdate" value='false'/>
        <table cellspacing="0" cellpadding="2">
            <c:forEach var="f2p" items="${form2program}" varStatus="pVarStat">
            <tr>
                <th align=left>
                    <spring:message code="form2program.condition.conditionId" />
                </th>
                <td align=left>
                    ${f2p.conditionId}
                </td>
            </tr>
            <tr>
                <th align=left>
                    <spring:message code="Concept" />
                </th>
                <td align=left> 
                    ${f2p.conceptId.name}
                </td>
            </tr>
        <tr>
        <th align=left>
            <spring:message code="Concept.datatype" />
        </th>
        <td align=left>
            ${f2p.conceptId.datatype.name}
        </td>
            <c:if test="${f2p.operator != ''}">
            <tr>
                <th align=left> 
                    <spring:message code="form2program.condition.operator" />
                </th>
                <td align=left>
                    ${f2p.operator} 
                </td>
            </tr>
        </c:if>
        </tr>
            <c:if test="${f2p.valueCoded.length > 0}">
            <tr>
                <th align=left> 
                    <spring:message code="Obs.valueCoded" />
        </th>
        <td align=left>
                    ${f2p.valueCoded}
                </td>
            </tr>
            </c:if>
            <c:if test="${f2p.valueDrug.length > 0}">
            <tr>
                <th align=left> 
                    <spring:message code="Obs.valueDrug" />
                </th>
                <td align=left>
                    ${f2p.valueDrug}  
                </td>
            </tr>
        </c:if>
            <c:if test="${f2p.valueDatetime != ''}">
            <tr>
                <th align=left> 
                    <spring:message code="Obs.valueDatetime" />
                </th>
                <td align=left>
                    ${f2p.valueDatetime} 
                </td>
            </tr>
        </c:if>
            <c:if test="${f2p.valueNumeric != ''}">
            <tr>
                <th align=left> 
                    <spring:message code="Obs.valueNumeric" />
                </th>
                <td align=left>
                    ${f2p.valueNumeric} 
                </td>
            </tr>
        </c:if>
            <c:if test="${f2p.valueModifier.length > 0}">
            <tr>
                <th align=left> 
                    <spring:message code="Obs.valueModifier" />
                </th>
                <td align=left>
                    ${f2p.valueModifier} 
                </td>
            </tr>
        </c:if>
            <c:if test="${f2p.valueText != ''}">
            <tr>
                <th align=left> 
                    <spring:message code="Obs.valueText" />
                </th>
                <td align=left>
                    ${f2p.valueText} 
                </td>
            </tr>
        </c:if>
            </c:forEach>
        </table>
</form>
<br/>

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

    function jumpToConcept(value) {
    
    }

</script>
<%@ include file="/WEB-INF/template/footer.jsp" %>

