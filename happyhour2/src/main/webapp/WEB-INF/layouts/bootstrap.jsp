<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<jsp:directive.include file="/bootstrap/lib/index_css_imports.jsp" />
		<jsp:directive.include file="/bootstrap/lib/user_list_css_imports.jsp" />
		
	</head>
	
  	<body>
	    <tiles:insertAttribute name="header"/>
	    <tiles:insertAttribute name="menu" />   
    	<tiles:insertAttribute name="body" ignore="true" /> 
	</body>	
		
</html>