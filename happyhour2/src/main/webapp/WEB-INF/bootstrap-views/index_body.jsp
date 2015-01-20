<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>
	<div class="content">

        <div class="skins-nav">
            <a href="#" class="skin first_nav selected">
                <span class="icon"></span><span class="text">Default skin</span>
            </a>
            <a href="#" class="skin second_nav" data-file="css/compiled/skins/dark.css">
                <span class="icon"></span><span class="text">Dark skin</span>
            </a>
        </div>
        <!-- 
         -->

        <!-- upper main stats -->
        <div id="main-stats">
            <div class="row stats-row">
                <div class="col-md-3 col-sm-3 stat">
                    <div class="data">
                        <span class="number">2457</span>
                        visits
                    </div>
                    <span class="date">Today</span>
                </div>
                <div class="col-md-3 col-sm-3 stat">
                    <div class="data">
                        <span class="number">3240</span>
                        users
                    </div>
                    <span class="date">February 2013</span>
                </div>
                <div class="col-md-3 col-sm-3 stat">
                    <div class="data">
                        <span class="number">322</span>
                        orders
                    </div>
                    <span class="date">This week</span>
                </div>
                <div class="col-md-3 col-sm-3 stat last">
                    <div class="data">
                        <span class="number">$2,340</span>
                        sales
                    </div>
                    <span class="date">last 30 days</span>
                </div>
            </div>
        </div>
        <!-- 
         -->
        <!-- end upper main stats -->


            <!-- statistics chart built with jQuery Flot -->
        <div id="pad-wrapper">

            <div class="row chart">
                <div class="col-md-12">
                    <h4 class="clearfix pull-left">
                        Statistics                         
                    </h4>
                    <div class="btn-group pull-right">
                        <button class="glow left">DAY</button>
                        <button class="glow middle active">MONTH</button>
                        <button class="glow right">YEAR</button>
                    </div>
                </div>
                <div class="col-md-12">
                    <div style="padding: 0px; position: relative;" id="statsChart"><canvas height="250" width="1305" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1305px; height: 250px;" class="flot-base"></canvas><div style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;" class="flot-text flot-y-axis flot-y1-axis yAxis y1Axis"><div style="position: absolute; top: 219px; font: 400 12px &quot;Open Sans&quot;,sans-serif; color: rgb(157, 163, 169); left: 14px;">0</div><div style="position: absolute; top: 146px; font: 400 12px &quot;Open Sans&quot;,sans-serif; color: rgb(157, 163, 169); left: 7px;">50</div><div style="position: absolute; top: 73px; font: 400 12px &quot;Open Sans&quot;,sans-serif; color: rgb(157, 163, 169); left: 0px;">100</div><div style="position: absolute; top: 0px; font: 400 12px &quot;Open Sans&quot;,sans-serif; color: rgb(157, 163, 169); left: 0px;">150</div></div><div style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;" class="flot-text flot-x-axis flot-x1-axis xAxis x1Axis"><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 16px;">JAN</div><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 130px;">FEB</div><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 243px;">MAR</div><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 360px;">APR</div><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 473px;">MAY</div><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 590px;">JUN</div><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 707px;">JUL</div><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 819px;">AUG</div><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 936px;">SEP</div><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 1049px;">OCT</div><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 1163px;">NOV</div><div style="position: absolute; top: 233px; font: small-caps 400 12px Open Sans,Arial; color: rgb(105, 118, 149); left: 1280px;">DEC</div></div><canvas height="250" width="1305" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1305px; height: 250px;" class="flot-overlay"></canvas><div class="legend"><div style="position: absolute; width: 62px; height: 38px; top: 14px; right: 18px; background-color: rgb(255, 255, 255); opacity: 0.85;"> </div><table style="position:absolute;top:14px;right:18px;;font-size:smaller;color:#545454"><tbody><tr><td class="legendColorBox"><div style="border:1px solid #fff;padding:1px"><div style="width:4px;height:0;border:5px solid rgb(167,181,197);overflow:hidden"></div></div></td><td class="legendLabel">Signups</td></tr><tr><td class="legendColorBox"><div style="border:1px solid #fff;padding:1px"><div style="width:4px;height:0;border:5px solid rgb(48,160,235);overflow:hidden"></div></div></td><td class="legendLabel">Visits</td></tr></tbody></table></div></div>
                </div>
            </div>
            <!-- 
             -->
            <!-- end statistics chart -->

            <!-- UI Elements section -->
            <div class="row section ui-elements">
                <div class="col-md-12">
                    <h4>UI Elements</h4>
                </div>                
                <div class="col-md-5 knobs">
                    <div class="knob-wrapper">
                        <div style="display:inline;width:150px;height:200px;"><canvas width="150" height="200px"></canvas><input style="width: 79px; height: 50px; position: absolute; vertical-align: middle; margin-top: 50px; margin-left: -114px; border: 0px none; background: none repeat scroll 0% 0% transparent; font: bold 30px Arial; text-align: center; color: rgb(51, 51, 51); padding: 0px;" value="50" class="knob" data-thickness=".3" data-inputcolor="#333" data-fgcolor="#30a1ec" data-bgcolor="#d4ecfd" data-width="150" type="text"/></div>
                        <div class="info">
                            <div class="param">
                                <span class="line blue"></span>
                                Active users
                            </div>
                        </div>
                    </div>
                    <div class="knob-wrapper">
                        <div style="display:inline;width:150px;height:200px;"><canvas width="150" height="200px"></canvas><input style="width: 79px; height: 50px; position: absolute; vertical-align: middle; margin-top: 50px; margin-left: -114px; border: 0px none; background: none repeat scroll 0% 0% transparent; font: bold 30px Arial; text-align: center; color: rgb(51, 51, 51); padding: 0px;" value="75" class="knob second" data-thickness=".3" data-inputcolor="#333" data-fgcolor="#3d88ba" data-bgcolor="#d4ecfd" data-width="150" type="text"/></div>
                        <div class="info">
                            <div class="param">
                                <span class="line blue"></span>
                                % disk space usage
                            </div>
                        </div>
                    </div>                        
                </div>
                <div class="col-md-6 showcase">
                    <div class="ui-sliders">
                        <div aria-disabled="false" class="slider slider-sample1 vertical-handler ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all"><a style="left: 19.8397%;" class="ui-slider-handle ui-state-default ui-corner-all" href="#"></a></div>
                        <div aria-disabled="false" class="slider slider-sample2 ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all"><div style="width: 25.8517%;" class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min"></div><a style="left: 25.8517%;" class="ui-slider-handle ui-state-default ui-corner-all" href="#"></a></div>
                        <div aria-disabled="false" class="slider slider-sample3 ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all"><div style="left: 8%; width: 26%;" class="ui-slider-range ui-widget-header ui-corner-all"></div><a style="left: 8%;" class="ui-slider-handle ui-state-default ui-corner-all" href="#"></a><a style="left: 34%;" class="ui-slider-handle ui-state-default ui-corner-all" href="#"></a></div>
                    </div>
                    <div class="ui-group">
                        <a class="btn-flat inverse">Large Button</a>
                        <a class="btn-flat gray">Large Button</a>
                        <a class="btn-flat default">Large Button</a>
                        <a class="btn-flat primary">Large Button</a>
                    </div>                        

                    <div class="ui-group">
                        <a class="btn-flat icon">
                            <i class="tool"></i> Icon button
                        </a>
                        <a class="btn-glow small inverse">
                            <i class="shuffle"></i>
                        </a>
                        <a class="btn-glow small primary">
                            <i class="setting"></i>
                        </a>
                        <a class="btn-glow small default">
                            <i class="attach"></i>
                        </a>
                        <div class="ui-select">
                            <select>
                                <option selected="">Dropdown</option>
                                <option>Custom selects</option>
                                <option>Pure css styles</option>
                            </select>
                        </div>

                        <div class="btn-group">
                            <button class="glow left">LEFT</button>
                            <button class="glow right">RIGHT</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 
             -->
            <!-- end UI elements section -->

            <!-- table sample -->
            <!-- the script for the toggle all checkboxes from header is located in js/theme.js -->
            <div class="table-products section">
                <div class="row head">
                    <div class="col-md-12">
                        <h4>Products <small>Table sample</small></h4>
                    </div>
                </div>

                <div class="row filter-block">
                    <div class="col-md-8 col-md-offset-5">
                        <div class="ui-select">
                            <select>
                              <option>Filter users</option>
                              <option>Signed last 30 days</option>
                              <option>Active users</option>
                            </select>
                        </div>
                        <input class="search" type="text"/>
                        <a class="btn-flat new-product">+ Add product</a>
                    </div>
                </div>

                <div class="row">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th class="col-md-3">
                                    <input type="checkbox"/>
                                    Product
                                </th>
                                <th class="col-md-3">
                                    <span class="line"></span>Description
                                </th>
                                <th class="col-md-3">
                                    <span class="line"></span>Status
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- row -->
                            <tr class="first">
                                <td>
                                    <input type="checkbox"/>
                                    <div class="img">
										<spring:url value="/resources/bootstrap/img/table-img.png" var="table-img"/>
										<img src="${table-img}" class="display" alt="user" /> 								        
                                    </div>
                                    <a href="#">There are many variations </a>
                                </td>
                                <td class="description">
                                    if you are going to use a passage of Lorem Ipsum.
                                </td>
                                <td>
                                    <span class="label label-success">Active</span>
                                    <ul class="actions">
                                        <li><i class="table-edit"></i></li>
                                        <li><i class="table-settings"></i></li>
                                        <li class="last"><i class="table-delete"></i></li>
                                    </ul>
                                </td>
                            </tr>
                            <!-- 
                             -->
                            <!-- row -->
                            <tr>
                                <td>
                                    <input type="checkbox"/>
                                    <div class="img">
                                    <%--
										<spring:url value="/resources/bootstrap/img/table-img.png" var="table-img"/>
                                     --%>
										<img src="${table-img}" class="display" alt="user" /> 								        
                                    </div>
                                    <a href="#">Internet tend</a>
                                </td>
                                <td class="description">
                                    There are many variations of passages.
                                </td>
                                <td>
                                    <ul class="actions">
                                        <li><i class="table-edit"></i></li>
                                        <li><i class="table-settings"></i></li>
                                        <li class="last"><i class="table-delete"></i></li>
                                    </ul>
                                </td>
                            </tr>
                            <!-- 
                             -->
                            <!-- row -->
                            <tr>
                                <td>
                                    <input type="checkbox"/>
                                    <div class="img">
										<spring:url value="/resources/bootstrap/img/table-img.png" var="table-img"/>
										<img src="${table-img}" class="display" alt="user" /> 								        
                                    </div>
                                    <a href="#">Many desktop publishing </a>
                                </td>
                                <td class="description">
                                    if you are going to use a passage of Lorem Ipsum.
                                </td>
                                <td>
                                    <ul class="actions">
                                        <li><i class="table-edit"></i></li>
                                        <li><i class="table-settings"></i></li>
                                        <li class="last"><i class="table-delete"></i></li>
                                    </ul>
                                </td>
                            </tr>
                            <!-- 
                             -->
                            <!-- row -->
                            <tr>
                                <td>
                                    <input type="checkbox"/>
                                    <div class="img">
										<spring:url value="/resources/bootstrap/img/table-img.png" var="table-img"/>
										<img src="${table-img}" class="display" alt="user" /> 								        
                                    </div>
                                    <a href="#">Generate Lorem </a>
                                </td>
                                <td class="description">
                                    There are many variations of passages.
                                </td>
                                <td>
                                    <span class="label label-info">Standby</span>
                                    <ul class="actions">
                                        <li><i class="table-edit"></i></li>
                                        <li><i class="table-settings"></i></li>
                                        <li class="last"><i class="table-delete"></i></li>
                                    </ul>
                                </td>
                            </tr>
                            <!-- 
                             -->
                            <!-- row -->
                            <tr>
                                <td>
                                    <input type="checkbox"/>
                                    <div class="img">
										<spring:url value="/resources/bootstrap/img/table-img.png" var="table-img"/>
										<img src="${table-img}" class="display" alt="user" /> 								        
                                    </div>
                                    <a href="#">Internet tend</a>
                                </td>
                                <td class="description">
                                    There are many variations of passages.
                                </td>
                                <td>                                        
                                    <ul class="actions">
                                        <li><i class="table-edit"></i></li>
                                        <li><i class="table-settings"></i></li>
                                        <li class="last"><i class="table-delete"></i></li>
                                    </ul>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <ul class="pagination">
                    <li><a href="#">Â«</a></li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">Â»</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<jsp:directive.include file="/bootstrap/lib/index_js_imports.jsp" />
