<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Manage Programs" otherwise="/login.htm" redirect="/module/form2program/form2ProgramCondition.list" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/programs/localHeader.jsp" %>


<h2><spring:message code="form2program.condition.list.title"/></h2>

<a href="form2ProgramCondition.form" name="addCondition" /><spring:message code="form2program.condition.add" />
<br />
<br />

<b class="boxHeader"><spring:message code="form2program.condition.list.title"/></b>
<form method="post" action="" name="f2pFormManage" >
<div class="box">
        <input type="hidden" name="programAction" id="programAction" value=""/>
        <input type="hidden" name="encounterTypeAction" id="encounterTypeAction" value=""/>
        <c:set var="needUpdate" value='false'/>
        <table cellspacing="0" cellpadding="2">
            <c:forEach var="f2p" items="${form2program}" varStatus="pVarStat">
            <tr>
                <td valign=top>
                    <a href="form2ProgramCondition.htm?conditionId=${f2p.conditionId}" name="viewCondition" >${f2p.conditionId}&nbsp; - &nbsp;${f2p.conceptId.name}
                </td>
        </a>
                <c:if test="${f2p.operator != 'null'}">
                <td valign=top> &nbsp;&nbsp;
                    ${f2p.operator}
                </td>
                </c:if>
                <c:if test="${f2p.valueCoded.length > 0}">
                <td valign=top> &nbsp;&nbsp;
                    ${f2p.valueCoded}
                </td>
                </c:if>
                <c:if test="${f2p.valueDrug.length > 0}">
                <td valign=top> &nbsp;&nbsp;
                    ${f2p.valueDrug}
                </td>
                </c:if>
                <c:if test="${f2p.valueDatetime != 'null'}">
                <td valign=top> &nbsp;&nbsp;
                    ${f2p.valueDatetime}
                </td>
                </c:if>
                <c:if test="${f2p.valueNumeric != ''}">
                <td valign=top> &nbsp;&nbsp;
                    ${f2p.valueNumeric}
                </td>
                </c:if>
                <c:if test="${f2p.valueModifier != 'null'}">
                <td valign=top> &nbsp;&nbsp;
                    ${f2p.valueModifier}
                </td>
                </c:if>
                <c:if test="${f2p.valueText != 'null'}">
                <td valign=top> &nbsp;&nbsp;
                    ${f2p.valueText}
                </td>
                </c:if>
            </tr>
            </c:forEach>
        </table>
</div>
</form>
<br/>

<script type="text/javascript">

</script>
<%@ include file="/WEB-INF/template/footer.jsp" %>
