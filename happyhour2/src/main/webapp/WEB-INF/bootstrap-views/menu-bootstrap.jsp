<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<div id="sidebar-nav">
       <ul id="dashboard-menu">
           <li>                
				<spring:url value="/" var="index_url"/>
               <a href="${index_url}">
                   <i class="icon-home"></i>
                   <span>Home</span>
               </a>
           </li>
           <%--
           <li>
               <a href="chart-showcase.html">                    
                   <i class="icon-signal"></i>
                   <span>Charts</span>
               </a>
           </li>
            --%>
           <li>
               <a class="dropdown-toggle" href="#">
                   <i class="icon-group"></i>
                   <span>Users</span>
                   <i class="icon-chevron-down"></i>
               </a>
               <ul class="submenu">
               		<spring:url value="/usuarios/list?page=1&amp;size=${empty param.size ? 10 : param.size}" var="usuarios_list_url"/>
                   <li><a href="${usuarios_list_url}">User list</a></li>
                   <li><a href="new-user.html">New user form</a></li>
                   <li><a href="user-profile.html">User profile</a></li>
               </ul>
           </li>
           <%--
           <li>
               <a class="dropdown-toggle" href="#">
                   <i class="icon-edit"></i>
                   <span>Forms</span>
                   <i class="icon-chevron-down"></i>
               </a>
               <ul class="submenu">
                   <li><a href="form-showcase.html">Form showcase</a></li>
                   <li><a href="form-wizard.html">Form wizard</a></li>
               </ul>
           </li>
           <li class="active">
               <a href="gallery.html">
                   <div class="pointer">
                       <div class="arrow"></div>
                       <div class="arrow_border"></div>
                   </div>
                   <i class="icon-picture"></i>
                   <span>Gallery</span>
               </a>
           </li>
           <li>
               <a href="calendar.html">                    
                   <i class="icon-calendar-empty"></i>
                   <span>Calendar</span>
               </a>
           </li>
            --%>
           <%--
           <li>
               <a class="dropdown-toggle" href="tables.html">
                   <i class="icon-th-large"></i>
                   <span>Tables</span>
                   <i class="icon-chevron-down"></i>
               </a>
               <ul class="submenu">
                   <li><a href="tables.html">Custom tables</a></li>
                   <li><a href="datatables.html">DataTables</a></li>
               </ul>
           </li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
           <li>
               <a class="dropdown-toggle ui-elements" href="#">
                   <i class="icon-code-fork"></i>
                   <span>UI Elements</span>
                   <i class="icon-chevron-down"></i>
               </a>
               <ul class="submenu">
                   <li><a href="ui-elements.html">UI Elements</a></li>
                   <li><a href="icons.html">Icons</a></li>
               </ul>
           </li>
           </sec:authorize>
           <li>
               <a href="personal-info.html">
                   <i class="icon-cog"></i>
                   <span>My Info</span>
               </a>
           </li>
           <li>
               <a class="dropdown-toggle" href="#">
                   <i class="icon-share-alt"></i>
                   <span>Extras</span>
                   <i class="icon-chevron-down"></i>
               </a>
               <ul class="submenu">
                   <li><a href="code-editor.html">Code editor</a></li>
                   <li><a href="grids.html">Grids</a></li>
                   <li><a href="signin.html">Sign in</a></li>
                   <li><a href="signup.html">Sign up</a></li>
               </ul>
           </li>
            --%>
       </ul>
  </div>
