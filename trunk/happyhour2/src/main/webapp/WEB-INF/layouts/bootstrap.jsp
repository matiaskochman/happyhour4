<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	
	<head>
	
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<!-- 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=8" />
		
		<util:bootstrap-load-scripts />		
	 -->	
		<spring:url value="/bootstrap/css/bootstrap/bootstrap.css" var="bootstrap_css_url" />
		<spring:url value="/bootstrap/css/bootstrap/bootstrap-overrides.css" var="bootstrap_overrides_css_url" />
		<spring:url value="/bootstrap/css/lib/jquery-ui-1.10.2.custom.css" var="jquery_ui_css" />
		<spring:url value="/bootstrap/css/lib/font-awesome.css" var="font_awesome_css" />
		<spring:url value="/bootstrap/css/compiled/layout.css" var="layout_css" />
		<spring:url value="/bootstrap/css/compiled/elements.css" var="elements_css" />
		<spring:url value="/bootstrap/css/compiled/icons.css" var="icons_css" />
		<spring:url value="/bootstrap/css/compiled/index.css" var="index_css" />
		<spring:url value="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" var="css_google_api_1" />
		<spring:url value="http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic" var="css_google_api_2" />	
		
		<link rel="stylesheet" type="text/css" href="${bootstrap_css_url}" />
	 	<link rel="stylesheet" type="text/css" href="${bootstrap_overrides_css_url}" />
		<link rel="stylesheet" type="text/css" href="${jquery_ui_css}" />
		<link rel="stylesheet" type="text/css" href="${font_awesome_css}" />
		<link rel="stylesheet" type="text/css" href="${layout_css}" />
		<link rel="stylesheet" type="text/css" href="${elements_css}" />
		<link rel="stylesheet" type="text/css" href="${icons_css}" />
		<link rel="stylesheet" type="text/css" media="screen" href="${index_css}" />
	 
	    <!-- open sans font -->
	    <link href="${css_google_api_1}" rel='stylesheet' type='text/css' />
	
	    <!-- lato font -->
	    <link href="${css_google_api_2}" rel='stylesheet' type='text/css' />	
	
		
		<!-- scripts-->
		 
		<spring:url value="http://code.jquery.com/jquery-latest.js" var="jquery" />	
		<spring:url value="/bootstrap/js/bootstrap.min.js" var="bootstrap_js" />
		<spring:url value="/bootstrap/js/jquery-ui-1.10.2.custom.min.js" var="jquery_ui" />
		<spring:url value="/bootstrap/js/jquery.knob.js" var="jquery_knob" />
		<spring:url value="/bootstrap/js/jquery.flot.js" var="jquery_flot" />
		<spring:url value="/bootstrap/js/jquery.flot.stack.js" var="jquery_flot_stack" />
		<spring:url value="/bootstrap/js/jquery.flot.resize.js" var="jquery_flot_resize" />
		<spring:url value="/bootstrap/js/theme.js" var="theme" />
	 
		<script src="${jquery}" type="text/javascript"><!-- required for FF3 and Opera --></script>	  
		<script src="${bootstrap_js}" type="text/javascript"><!-- required for FF3 and Opera --></script>	  
		<script src="${jquery_ui}" type="text/javascript"><!-- required for FF3 and Opera --></script>	  
		<script src="${jquery_knob}" type="text/javascript"><!-- required for FF3 and Opera --></script>	  
		<script src="${jquery_flot}" type="text/javascript"><!-- required for FF3 and Opera --></script>	  
		<script src="${jquery_flot_stack}" type="text/javascript"><!-- required for FF3 and Opera --></script>	  
		<script src="${jquery_flot_resize}" type="text/javascript"><!-- required for FF3 and Opera --></script>	  
		<script src="${theme}" type="text/javascript"><!-- required for FF3 and Opera --></script>	  
	 
	 
	<script type="text/javascript">
        $(function () {

            // jQuery Knobs
            $(".knob").knob();



            // jQuery UI Sliders
            $(".slider-sample1").slider({
                value: 100,
                min: 1,
                max: 500
            });
            $(".slider-sample2").slider({
                range: "min",
                value: 130,
                min: 1,
                max: 500
            });
            $(".slider-sample3").slider({
                range: true,
                min: 0,
                max: 500,
                values: [ 40, 170 ],
            });

            

            // jQuery Flot Chart
            var visits = [[1, 50], [2, 40], [3, 45], [4, 23],[5, 55],[6, 65],[7, 61],[8, 70],[9, 65],[10, 75],[11, 57],[12, 59]];
            var visitors = [[1, 25], [2, 50], [3, 23], [4, 48],[5, 38],[6, 40],[7, 47],[8, 55],[9, 43],[10,50],[11,47],[12, 39]];

            var plot = $.plot($("#statsChart"),
                [ { data: visits, label: "Signups"},
                 { data: visitors, label: "Visits" }], {
                    series: {
                        lines: { show: true,
                                lineWidth: 1,
                                fill: true, 
                                fillColor: { colors: [ { opacity: 0.1 }, { opacity: 0.13 } ] }
                             },
                        points: { show: true, 
                                 lineWidth: 2,
                                 radius: 3
                             },
                        shadowSize: 0,
                        stack: true
                    },
                    grid: { hoverable: true, 
                           clickable: true, 
                           tickColor: "#f9f9f9",
                           borderWidth: 0
                        },
                    legend: {
                            // show: false
                            labelBoxBorderColor: "#fff"
                        },  
                    colors: ["#a7b5c5", "#30a0eb"],
                    xaxis: {
                        ticks: [[1, "JAN"], [2, "FEB"], [3, "MAR"], [4,"APR"], [5,"MAY"], [6,"JUN"], 
                               [7,"JUL"], [8,"AUG"], [9,"SEP"], [10,"OCT"], [11,"NOV"], [12,"DEC"]],
                        font: {
                            size: 12,
                            family: "Open Sans, Arial",
                            variant: "small-caps",
                            color: "#697695"
                        }
                    },
                    yaxis: {
                        ticks:3, 
                        tickDecimals: 0,
                        font: {size:12, color: "#9da3a9"}
                    }
                 });

            function showTooltip(x, y, contents) {
                $('<div id="tooltip">' + contents + '</div>').css( {
                    position: 'absolute',
                    display: 'none',
                    top: y - 30,
                    left: x - 50,
                    color: "#fff",
                    padding: '2px 5px',
                    'border-radius': '6px',
                    'background-color': '#000',
                    opacity: 0.80
                }).appendTo("body").fadeIn(200);
            }

            var previousPoint = null;
            $("#statsChart").bind("plothover", function (event, pos, item) {
                if (item) {
                    if (previousPoint != item.dataIndex) {
                        previousPoint = item.dataIndex;

                        $("#tooltip").remove();
                        var x = item.datapoint[0].toFixed(0),
                            y = item.datapoint[1].toFixed(0);

                        var month = item.series.xaxis.ticks[item.dataIndex].label;

                        showTooltip(item.pageX, item.pageY,
                                    item.series.label + " of " + month + ": " + y);
                    }
                }
                else {
                    $("#tooltip").remove();
                    previousPoint = null;
                }
            });
        });
    </script>	 
	 
	</head>
	
  	<body>
	    <tiles:insertAttribute name="header"/>
	    <tiles:insertAttribute name="menu" />   
    	<tiles:insertAttribute name="body" ignore="true" /> 
	</body>	
		
</html>