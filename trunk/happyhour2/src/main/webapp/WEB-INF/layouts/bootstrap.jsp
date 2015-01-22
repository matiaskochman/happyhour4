<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<jsp:directive.include file="/bootstrap/lib/index_css_imports.jsp" />
		<link type="text/css" rel="stylesheet" href="<tiles:getAsString name="CSS_BODY"/>"/>		
	</head>
	
  	<body">
	    <tiles:insertAttribute name="header"/>
	    <tiles:insertAttribute name="menu" />   
    	<tiles:insertAttribute name="body" ignore="true" />
    	<tiles:insertAttribute name="scripts" ignore="true" />
		<script>
		 

			window.onbeforeunload = function(e) {
				
				$.ajax({
					  url: "resources/j_spring_security_logout",
					  context: document.body
					}).done(function() {
					  $( this ).addClass( "done" );
					});
				
			  return 'Texto de aviso';
			};		 
		</script>    	
	</body>	
		
</html>