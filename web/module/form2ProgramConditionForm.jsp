<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Manage Programs" otherwise="/login.htm" redirect="/module/form2program/form2Program.form" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/programs/localHeader.jsp" %>

<style type="text/css">
.inlineForm {
    padding: 0px;
    margin: 0px;
    display: inline;
}
.bold {
    font-weight: bold;
}
</style>

<c:choose>
    <c:when test="${param.conditionId != null}">
        <h2><spring:message code="form2program.condition.edit" arguments="${param.conditionId}" />&nbsp;<spring:message code="form2program.condition.for" /></h2>
    </c:when>
    <c:otherwise>
        <h2><spring:message code="form2program.condition.new" />&nbsp;<spring:message code="form2program.condition.for" /></h2>
    </c:otherwise>
</c:choose>

<%--<c:if test="${param.conditionId != null}"> --%>
    <form class="inlineForm" id="jumpForm" action="" method="post">
        <input type="hidden" name="jumpAction" id="jumpAction" value="previous"/>
        <a href="#previousCondition" id="previousCondition" valign="middle" accesskey="," onclick="return jumpToCondition('previous')"><spring:message code="general.previous"/></a>
            |
        <a href="form2ProgramCondition.htm?conditionId=${param.conditionId}" id="viewCondition" accesskey="v"><spring:message code="general.view"/></a>
            |
        <a href="#nextCondition" id="nextCondition" valign="middle" accesskey="." onclick="return jumpToCondition('next')"><spring:message code="general.next"/></a>
            |
    </form>
<a href="form2ProgramCondition.form" id="newCondition" valign="middle"><spring:message code="general.new"/></a>
<%-- </c:if> --%>

<br />
<br />

<form method="post" action="" name="f2pFormManage" >
        <table cellspacing="0" cellpadding="2">
            <c:forEach var="f2p" items="${form2program}" varStatus="pVarStat">
            <tr>
                <th align=left>
                    <spring:message code="form2program.condition.conditionId" />
                </th>
                <td align=left>
                    <c:choose>
                        <c:when test="${f2p.conditionId != null }">
                            ${f2p.conditionId}
                        </c:when>
                        <c:otherwise>
                            &nbsp;
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <th align=left>
                    <spring:message code="Concept" />
                </th>
                <td align=left>
                    <input type="hidden" id="conceptId" name="conceptId" value="${param.conceptId}" /> 
                    <openmrs_tag:conceptField formFieldName='conceptIdForm' searchLabel='Concept' initialValue='${f2p.conceptId}' onSelectFunction='setConcept' />
                </td>
            </tr>
            <tr>
                <th align=left>
                    <spring:message code="Concept.datatype" />
                </th>
                <td align=left>
                    <input type="hidden" id="datatype" name="datatype" value="${param.datatype}" />
                    <c:forEach var="dt" items="${datatypes}">
                        <item class="bold" name="datatypeItem" id="${dt.hl7Abbreviation}" >${dt.name}</item>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <th align=left> 
                    <spring:message code="form2program.condition.operator" />
                </th>
                <td align=left>
                    <input type="text" id="operator" name="operator" value="${f2p.operator}" />
                </td>
            </tr>
            <tr name="dtRow" id="CWE" >
                <th align=left> 
                    <spring:message code="Obs.valueCoded" />
                </th>
                <td align=left>
                    <input type="text" id="valueCoded" name="valueCoded" value="${f2p.valueCoded}" />
                </td>
            </tr>
            <tr name="dtRow" id="ZZ">
                <th align=left> 
                    <spring:message code="Obs.valueDrug" />
                </th>
                <td align=left>
                    <input type="text" id="valueDrug" name="valueDrug" value="${f2p.valueDrug}" />
                </td>
            </tr>
            <tr name="dtRow" id="DT,TM,TS">
                <th align=left> 
                    <spring:message code="Obs.valueDatetime" />
                </th>
                <td align=left>
                    <input type"text" id="valueDatetime" name="valueDatetime" value="${f2p.valueDatetime}" />
                </td>
            </tr>
            <tr name="dtRow" id="NM,BIT">
                <th align=left> 
                    <spring:message code="Obs.valueNumeric" />
                </th>
                <td align=left>
                    <input type="text" id="valueNumeric" name="valueNumeric" value="${f2p.valueNumeric}" />
                </td>
            </tr>
            <tr id="null">
                <th align=left> 
                    <spring:message code="Obs.valueModifier" />
                </th>
                <td align=left>
                    <input type="text" id="valueModifier" name="valueModifier" value="${f2p.valueModifier}" />
                </td>
            </tr>
            <tr name="dtRow" id="ST">
                <th align=left> 
                    <spring:message code="Obs.valueText" />
                </th>
                <td align=left>
                    <input type="text" id="valueText" name="valueText" value="${f2p.valueText}" />
                </td>
            </tr>
            </c:forEach>
        <tr>
                <td align=left>
            &nbsp;
                </td>
        </tr>
        <tr>
                <td align=left>
            <input type="submit" name="action" value="<spring:message code='general.save' />" />&nbsp;
            <input type="submit" name="action" value="<spring:message code='general.cancel' />" />&nbsp;
            <input type="submit" name="action" value="<spring:message code='general.delete' />" />&nbsp;
                </td>
        </tr>
        </table>
        
</form>
<br/>

<script type="text/javascript">

    function setConcept(concept) {
        var datatype = concept.hl7Abbreviation;
        var datatypes = document.getElementsByName("datatypeItem");
        var currentDatatype = document.getElementById("datatype");
        var currentConcept = document.getElementById("conceptId");
        alert(currentConcept.value);
        currentConcept.value = concept.conceptId;
        for (var i=0; i<datatypes.length; i++) {
            if (datatypes[i].id == datatype) {
                datatypes[i].style.display = "";
                currentDatatype.value = datatypes[i].id;
            }    
            else {
                datatypes[i].style.display = "none";
            }
        }
        var rows = document.getElementsByName("dtRow");
        for (var i=0; i<rows.length; i++) {
            if (rows[i].id.indexOf(datatype) != -1) {
                rows[i].style.display = "";
            }
            else {
                rows[i].style.display = "none";
            }
        }
        // alert(currentDatatype.value);
        // alert(currentConcept.value);
    }

</script>

<%@ include file="/WEB-INF/template/footer.jsp" %>

