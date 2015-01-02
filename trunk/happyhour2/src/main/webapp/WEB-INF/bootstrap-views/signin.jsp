<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html class="login-bg">
 <html>
<head>
	<title>Detail Admin - Sign in</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<jsp:directive.include file="/bootstrap/lib/signin_css_imports.jsp" />	
</head>
<body>


    <!-- background switcher -->
    <div class="bg-switch visible-desktop">
        <div class="bgs">
            <a href="#" data-img="landscape.jpg" class="bg active">
				<spring:url value="/resources/bootstrap/img/bgs/landscape.jpg" var="landscape"/>
				<img src="${landscape}" alt="background"/>
            </a>
            <a href="#" data-img="blueish.jpg" class="bg">
				<spring:url value="/resources/bootstrap/img/bgs/blueish.jpg" var="blueish"/>
				<img src="${blueish}" alt="background"/>
            </a>            
            <a href="#" data-img="7.jpg" class="bg">
				<spring:url value="/resources/bootstrap/img/bgs/7.jpg" var="siete"/>
				<img src="${siete}" alt="background"/>
            </a>
            <a href="#" data-img="8.jpg" class="bg">
				<spring:url value="/resources/bootstrap/img/bgs/8.jpg" var="ocho"/>
				<img src="${ocho}" alt="background"/>
            </a>
            <a href="#" data-img="9.jpg" class="bg">
				<spring:url value="/resources/bootstrap/img/bgs/9.jpg" var="nueve"/>
				<img src="${nueve}" alt="background"/>
            </a>
            <a href="#" data-img="10.jpg" class="bg">
				<spring:url value="/resources/bootstrap/img/bgs/10.jpg" var="diez"/>
				<img src="${diez}" alt="background"/>
            </a>
            <a href="#" data-img="11.jpg" class="bg">
				<spring:url value="/resources/bootstrap/img/bgs/11.jpg" var="once"/>
				<img src="${once}" alt="background"/>
            </a>
        </div>
    </div>


    <div class="login-wrapper">
        <a href="index.html">
			<spring:url value="/resources/bootstrap/img/logo-white.png" var="logo-white"/>
			<img src="${logo-white}" alt="logo"/>
        </a>

        <div class="box">
            <div class="content-wrap">
                <h6>Log in</h6>
                <%--
			    <spring:url value="/resources/j_spring_security_check" var="form_url" />
                 --%>
			    <spring:url value="/security_login" var="form_url" />
                
			    <form name="f" action="${fn:escapeXml(form_url)}" method="POST">
					<input id="j_username" type='text' name='j_username' class="form-control" placeholder="Username" />
        			<input id="j_password" type='password' name='j_password' class="form-control" placeholder="Your password" />
	                <a href="#" class="forgot">Forgot password?</a>
	                <div class="remember">
	                    <input id="remember-me" type="checkbox">
	                    <label for="remember-me">Remember me</label>
	                </div>
			        <input id="proceed" class="btn-glow primary login" type="submit" value="Log In" />
	                
	            </form>
            </div>
        </div>

        <div class="no-account">
            <p>Don't have an account?</p>
            <a href="signup.html">Sign up</a>
        </div>
    </div>
    
	<jsp:directive.include file="/bootstrap/lib/signin_js_imports.jsp" />

</body>
</html>