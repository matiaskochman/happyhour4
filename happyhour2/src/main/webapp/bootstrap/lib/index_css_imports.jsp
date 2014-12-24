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
