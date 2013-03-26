<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Manage Programs" otherwise="/login.htm" redirect="/module/form2program/form2Transition.list" />

<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ include file="/WEB-INF/view/admin/programs/localHeader.jsp" %>

<script type="text/javascript">
	var djConfig = {debugAtAllCosts: false, isDebug: false };
</script>

<openmrs:htmlInclude file="/scripts/dojo/dojo.js" />
<%-- <openmrs:htmlInclude file="/admin/forms/formSchemaDesign.js" /> --%>

<style>
	.required {
		color: red;
	}
	a.delete {
		background: url(${pageContext.request.contextPath}/images/delete.gif) no-repeat center center;
		text-decoration: none;
		padding-left: 2px;
		cursor: pointer;
	}
	.disabled, .disabled * {
		color: gray;
		background-color: whitesmoke;
		}
		.disabled #formFieldTitle {
			background-color: whitesmoke;
		}
	div.dojoTree {
		overflow: hidden;
	}
	#editFormField {
		position: absolute;
		left: -1000px;
		top: 0px;
		background-color: white;
		z-index: 20;
		width: 500px;
		border: 2px solid lightgreen;
		padding: 1px;
		}
		#editFormField.disabled {
			border-color: gray;
		}
	
	#formFieldTitle {
		background-color: lightgreen;
		width: 100%;
	}
	#fieldWarning, #fieldWarningIframe {
		position: absolute;
		margin-left: 5%;
		margin-top: 7%;
		width: 90%;
		padding: 3px;
		}
		#fieldWarning {
			color: firebrick;
			border: 2px solid firebrick;
			text-align: center;
			z-index: 999;
			background-color: white;
		}
		#fieldWarningIframe {
			padding: 2px;
			z-index: 998;
			height: 50px;
		}
	span.fieldConceptHit {
		color: gray;
	}
	span.treeNodeRow div.dojoTree div.dojoTreeNode {
		display: inline;
	}
	.openmrsSearchTable tr td div {
		overflow: hidden;
	}
	#fieldSearchDiv {
		position: fixed;
		z-index: 10;
		background-color: white;
	}
	
</style>

<h2><spring:message code="form2program.transition.list.title"/></h2>
<br/>
<b class="boxHeader"><spring:message code="form2program.transition.list.title"/></b>
<div class=box>

        <script type="text/javascript">
            dojo.require("dojo.lang.*");
            dojo.require("dojo.widget.Tree");
            dojo.require("dojo.widget.TreeContextMenu");
        </script>

    <div class="dojo-Tree id="stateTree" title="Programs">
            <c:forEach var="program" items="${programs}" >
            <div class="dojo-TreeNode" title="${program.programId} ${program.concept.name}">
                <c:forEach var="workflow" items="${program.workflows}">
                <div class="dojo-TreeNode" title="${workflow.programWorkflowId} ${workflow.concept.name}">
                    <c:forEach var="state" items="${workflow.states}">
                        <div class="dojo-TreeNode" title="${state.programWorkflowStateId} ${state.concept.name}"></div>
                    </c:forEach>
                </div>    
                </c:forEach>
            </div>
            </c:forEach>
    </div>
</div>
