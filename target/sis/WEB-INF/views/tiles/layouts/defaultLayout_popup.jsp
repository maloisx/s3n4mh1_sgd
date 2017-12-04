<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="alert alert-info" role="alert" align="left">
  <h4>${requestScope['title_pag']}</h4>
</div>

<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="javascript_final_general" />


