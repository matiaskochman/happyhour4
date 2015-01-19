<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- main container -->
<div class="content">
    
    <!-- settings changer -->
    <div class="skins-nav">
        <a href="#" class="skin first_nav selected">
            <span class="icon"></span><span class="text">Default</span>
        </a>
        <a href="#" class="skin second_nav" data-file="css/compiled/skins/dark.css">
            <span class="icon"></span><span class="text">Dark skin</span>
        </a>
    </div>
    
    <div id="pad-wrapper" class="users-list">
        <div class="row header">
            <h3>Users</h3>
            <div class="col-md-10 col-sm-12 col-xs-12 pull-right">
                <input type="text" class="col-md-5 search" placeholder="Type a user's name...">
                
                <!-- custom popup filter -->
                <!-- styles are located in css/elements.css -->
                <!-- script that enables this dropdown is located in js/theme.js -->
                <div class="ui-dropdown">
                    <div class="head" data-toggle="tooltip" title="Click me!">
                        Filter users
                        <i class="arrow-down"></i>
                    </div>  
                    <div class="dialog">
                        <div class="pointer">
                            <div class="arrow"></div>
                            <div class="arrow_border"></div>
                        </div>
                        <div class="body">
                            <p class="title">
                                Show users where:
                            </p>
                            <div class="form">
                                <select>
                                    <option>Name</option>
                                    <option>Email</option>
                                    <option>Number of orders</option>
                                    <option>Signed up</option>
                                    <option>Last seen</option>
                                </select>
                                <select>
                                    <option>is equal to</option>
                                    <option>is not equal to</option>
                                    <option>is greater than</option>
                                    <option>starts with</option>
                                    <option>contains</option>
                                </select>
                                <input type="text" class="form-control" />
                                <a class="btn-flat small">Add filter</a>
                            </div>
                        </div>
                    </div>
                </div>

                <a href="new-user.html" class="btn-flat success pull-right">
                    <span>&#43;</span>
                    NEW USER
                </a>
            </div>
        </div>

        <!-- Users table -->
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th class="col-md-4 sortable">
                                Name
                            </th>
                            <th class="col-md-3 sortable">
                                <span class="line"></span>Signed up
                            </th>
                            <th class="col-md-2 sortable">
                                <span class="line"></span>Total spent
                            </th>
                            <th class="col-md-3 sortable align-right">
                                <span class="line"></span>Email
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                    <!-- row -->
                    <tr class="first">
                        <td>
                        <%--
                        <!-- 
                           	<img src="/happyhour2/bootstrap/img/contact-img.png"  alt="contact" class="img-circle avatar hidden-phone" />
                         -->
                         --%>
                            <a href="user-profile.html" class="name">Alejandra Galvan Castillo</a>
                            <span class="subtext">Graphic Design</span>
                        </td>
                        <td>
                            Mar 13, 2012
                        </td>
                        <td>
                            $ 4,500.00
                        </td>
                        <td class="align-right">
                            <a href="#">alejandra@canvas.com</a>
                        </td>
                    </tr>
					<c:forEach items="${usuarios}" var="usuario">
	                    <tr>
	                        <td>
	                        <%--
	                        <!--
	                           	<img src="/happyhour2/bootstrap/img/contact-img.png"  alt="contact" class="img-circle avatar hidden-phone" />
	                           	-->
	                         --%>
	                            <a href="user-profile.html" class="name">${usuario.userName}</a>
	                            <span class="subtext">Graphic Design</span>
	                        </td>
	                        <td>
	                            Jun 03, 2012
	                        </td>
	                        <td>
	                            $ 549.99
	                        </td>
	                        <td class="align-right">
	                            <a href="#">${usuario.email}</a>
	                        </td>
	                    </tr>
					</c:forEach>                    

                    </tbody>
                </table>
            </div>                
        </div>
        <spring:url value="/usuarios/list?page=2&amp;size=${empty param.size ? 10 : param.size}" var="usuarios_list_url_2"/>
        <spring:url value="/usuarios/list?page=3&amp;size=${empty param.size ? 10 : param.size}" var="usuarios_list_url_3"/>
        <spring:url value="/usuarios/list?page=4&amp;size=${empty param.size ? 10 : param.size}" var="usuarios_list_url_4"/>
        <spring:url value="/usuarios/list?page=5&amp;size=${empty param.size ? 10 : param.size}" var="usuarios_list_url_5"/>
        <ul class="pagination pull-right">
            <li><a href="#">&#8249;</a></li>
            <li class="active">
            	<a href="#">1</a>
            </li>
            <li>
            	<a href="${usuarios_list_url_2}">2</a>
            </li>
            <li>
            	<a href="${usuarios_list_url_3}">3</a>
            </li>
            <li>
            	<a href="${usuarios_list_url_4}">4</a>
            </li>
            <li>
            	<a href="${usuarios_list_url_5}">5</a>
            </li>
            <li>
            	<a href="#">&#8250;</a>
            </li>
        </ul>
        <!-- end users table -->
    </div>
</div>
<!-- end main container -->
<jsp:directive.include file="/bootstrap/lib/user_list_js_imports.jsp" />