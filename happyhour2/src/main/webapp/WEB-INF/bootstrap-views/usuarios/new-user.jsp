<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    
    <div id="pad-wrapper" class="new-user">
        <div class="row header">
            <div class="col-md-12">
                <h3>Create a new user</h3>
            </div>                
        </div>

        <div class="row form-wrapper">
            <!-- left column -->
            <div class="col-md-9 with-sidebar">
      			<spring:url value="/usuarios/createUsuario" var="form_url"/>
				<form:form action="${form_url}" method="POST" class="form-horizontal" modelAttribute="usuario">
				<%--
                <form class="form-horizontal" role="form">
				 --%>
                    <div class="form-group">
                        <label for="inputName1" class="col-md-2 control-label">Name</label>
                        <div class="col-md-8">
                        	<%--
                            <input type="text" class="form-control" id="inputName1" placeholder="Name">
                        	 --%>
                            <form:input path="userName" id="nameInput" type="text" class="form-control" placeholder="Name"></form:input>
							<form:errors path="userName" cssclass="error"></form:errors>                            
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail1" class="col-md-2 control-label">Email</label>
                        <div class="col-md-8">
                            <form:input path="email" id="inputEmail1" type="text" class="form-control" placeholder="Email"></form:input>
							<form:errors path="email" cssclass="error"></form:errors>                            
                        <%--
                          <input type="text" class="form-control" id="inputEmail1" placeholder="Email">
                         --%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-2 control-label">Password</label>
                        <div class="col-md-8">
                            <form:input path="password" id="password" type="text" class="form-control" placeholder="Password"></form:input>
							<form:errors path="password" cssclass="error"></form:errors>                            
                        <%--
                          <input type="text" class="form-control" id="inputEmail1" placeholder="Email">
                         --%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userType" class="col-md-2 control-label">User Type</label>
                        <div class="col-md-8">
                            <form:select id="userType" path="authorityFormValue">
					           <form:option value="NONE" label="--- Select ---" />
					           <form:options items="${authorityList}" itemValue="value" itemLabel="label"/>
					         </form:select>
							<form:errors path="authorityFormValue" cssclass="error"></form:errors>                            
                        <%--
                          <input type="text" class="form-control" id="inputEmail1" placeholder="Email">
                         --%>
                        </div>
                    </div>
                    
              
                    
                        <%--
                    <div class="form-group">
                        <label for="inputPhone1" class="col-md-2 control-label">Phone</label>
                        <div class="col-md-8">
                          <input type="text" class="form-control" id="inputPhone1" placeholder="Phone">
                        </div>
                    </div>
                         --%>
                        <%--
                    <div class="form-group">
                        <label for="inputWebsite1" class="col-md-2 control-label">Website</label>
                        <div class="col-md-8">
                          <input type="text" class="form-control" id="inputWebsite1" placeholder="Website">
                        </div>
                    </div>
                         --%>
                        <%--
                    <div class="form-group">
                        <label for="inputAddress1" class="col-md-2 control-label">Address</label>
                        <div class="col-md-8">
                          <input type="text" class="form-control" id="inputAddress1" placeholder="Address">
                        </div>
                    </div>
                         --%>
                        <%--
                    <div class="form-group">
                        <label for="inputContent1" class="col-md-2 control-label">Content</label>
                        <div class="col-md-8">
                          <textarea id="inputContent1" class="form-control" placeholder="Content" rows="5"></textarea>
                        </div>
                    </div>
                         --%>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-8">
							<input id="proceed" type="submit" value="Create"/>
							<%--
                        	<button type="submit" class="btn btn-default">Create</button>
							 --%>
                        </div>
                    </div>
                    <%--
                </form>
                     --%>
				</form:form>           
                <!-- <form class="new_user_form">
                    <div class="col-md-12 field-box">
                        <label>Name:</label>
                        <input class="form-control" type="text" />
                    </div>
                    <div class="col-md-12 field-box">
                        <label>State:</label>
                        <div class="ui-select span5">
                            <select>
                                <option value="AK">Alaska</option>
                                <option value="HI">Hawaii</option>
                                <option value="CA">California</option>
                                <option value="NV">Nevada</option>
                                <option value="OR">Oregon</option>
                                <option value="WA">Washington</option>
                                <option value="AZ">Arizona</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-12 field-box">
                        <label>Company:</label>
                        <input class="col-md-9 form-control" type="text" />
                    </div>
                    <div class="col-md-12 field-box">
                        <label>Email:</label>
                        <input class="col-md-9 form-control" type="text" />
                    </div>
                    <div class="col-md-12 field-box">
                        <label>Phone:</label>
                        <input class="col-md-9 form-control" type="text" />
                    </div>
                    <div class="col-md-12 field-box">
                        <label>Website:</label>
                        <input class="col-md-9 form-control" type="text" />
                    </div>
                    <div class="col-md-12 field-box">
                        <label>Address:</label>
                        <div class="address-fields">
                            <input class="form-control" type="text" placeholder="Street address" />
                            <input class="small form-control" type="text" placeholder="City" />
                            <input class="small form-control" type="text" placeholder="State" />
                            <input class="small last form-control" type="text" placeholder="Postal Code" />
                        </div>
                    </div>
                    <div class="col-md-12 field-box textarea">
                        <label>Notes:</label>
                        <textarea class="col-md-9"></textarea>
                        <span class="charactersleft">90 characters remaining. Field limited to 100 characters</span>
                    </div>
                    <div class="col-md-11 field-box actions">
                        <input type="button" class="btn-glow primary" value="Create user">
                        <span>OR</span>
                        <input type="reset" value="Cancel" class="reset">
                    </div>
                </form> -->
            </div>

            <!-- side right column -->
            <!-- 
            <div class="col-md-3 form-sidebar">
                <div class="alert alert-info">
                    <i class="icon-lightbulb pull-left"></i>
                    Display a sidebar note over here
                </div>                        
                <h6>Sidebar text for instructions</h6>
                <p>Add multiple users at once</p>
                <p>Choose one of the following file types:</p>
                <ul>
                    <li><a href="#">Upload a vCard file</a></li>
                    <li><a href="#">Import from a CSV file</a></li>
                    <li><a href="#">Import from an Excel file</a></li>
                </ul>
            </div>
             -->
        </div>
    </div>
</div>
<!-- end main container -->
