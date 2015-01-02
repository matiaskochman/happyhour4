<spring:url value="/bootstrap/css/bootstrap/bootstrap.css" var="bootstrap_css_url" />
<spring:url value="/bootstrap/css/bootstrap/bootstrap-overrides.css" var="bootstrap_overrides_css_url" />
<spring:url value="/bootstrap/css/lib/font-awesome.css" var="font_awesome_css" />
<spring:url value="/bootstrap/css/compiled/layout.css" var="layout_css" />
<spring:url value="/bootstrap/css/compiled/elements.css" var="elements_css" />
<spring:url value="/bootstrap/css/compiled/icons.css" var="icons_css" />
<spring:url value="/bootstrap/css/compiled/signin.css" var="signin_css" />
<spring:url value="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" var="css_google_font_open_sans" />

<!-- bootstrap -->
<link href="${bootstrap_css_url}" rel="stylesheet" />
<link href="${bootstrap_overrides_css_url}" type="text/css" rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="${layout_css}" />
<link rel="stylesheet" type="text/css" href="${elements_css}" />
<link rel="stylesheet" type="text/css" href="${icons_css}" />

<!-- libraries -->
<link rel="stylesheet" type="text/css" href="${font_awesome_css}" />

<!-- this page specific styles -->
<link rel="stylesheet" href="${signin_css}" type="text/css" media="screen" />

<!-- open sans font -->
<link href='${css_google_font_open_sans}' rel='stylesheet' type='text/css' />

<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
