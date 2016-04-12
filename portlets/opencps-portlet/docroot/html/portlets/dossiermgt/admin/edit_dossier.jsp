
<%
/**
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
%>
<%@ include file="../init.jsp"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="org.opencps.dossiermgt.search.DossierTemplateDisplayTerms"%>
<%@page import="org.opencps.dossiermgt.util.DossierMgtUtil"%>

<%
	long dossierTemplateId = ParamUtil.getLong(request, DossierTemplateDisplayTerms.DOSSIERTEMPLATE_DOSSIERTEMPLATEID);
	
	String [] dossierTemplateSections = null;
	
	if(dossierTemplateId == 0) {
		dossierTemplateSections = new String [1];
		dossierTemplateSections[0] = "edit_dossier_template";
	} else {
		dossierTemplateSections = new String[3];
		dossierTemplateSections[0] = "edit_dossier_template";
		dossierTemplateSections[1] = "edit_dossier_template";
		dossierTemplateSections[2] = "edit_dossier_template";
	}
	
	String[][] categorySections = {dossierTemplateSections};
%>

<portlet:actionURL name="updateDossier" var="updateDossierURL" >

</portlet:actionURL>


<liferay-util:buffer var="htmlTop">
	<liferay-ui:icon iconCssClass="icon-home" />
</liferay-util:buffer>

<liferay-util:buffer var="htmlBot">

</liferay-util:buffer>

<aui:form name="fm" 
	method="post" 
	action="<%=updateDossierURL.toString() %>">
	<liferay-ui:form-navigator 
		backURL="<%= currentURL %>"
		categoryNames= "<%= DossierMgtUtil._DOSSIER_CATEGORY_NAMES %>"	
		categorySections="<%=categorySections %>" 
		htmlBottom="<%= htmlBot %>"
		htmlTop="<%= htmlTop %>"
		jspPath='<%=templatePath + "dossier_common/" %>'
		>	
	</liferay-ui:form-navigator>
	<%-- <aui:input 
		name="<%=WorkingUnitDisplayTerms.WORKINGUNIT_ID %>" 
		value="<%=String.valueOf(workingUnitId) %>"
		type="hidden"
	></aui:input> --%>
</aui:form>