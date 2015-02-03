<!-- scripts -->
<spring:url value="http://code.jquery.com/jquery-latest.js" var="jquery" />	
<spring:url value="/bootstrap/js/bootstrap.min.js" var="bootstrap_js" />
<spring:url value="/bootstrap/js/theme.js" var="theme" />
<script src="${jquery}" type="text/javascript"><!-- required for FF3 and Opera --></script>	  
<script src="${bootstrap_js}" type="text/javascript"><!-- required for FF3 and Opera --></script>	  
<script src="${theme}" type="text/javascript"><!-- required for FF3 and Opera --></script>	  

<!-- pre load bg imgs -->
<script type="text/javascript">
    $(function () {
        // bg switcher
        var $btns = $(".bg-switch .bg");
        $btns.click(function (e) {
            e.preventDefault();
            $btns.removeClass("active");
            $(this).addClass("active");
            var bg = $(this).data("img");

            //$("html").css("background-image", "url('img/bgs/" + bg + "')");
            $("html").css("background-image", "url('/happyhour2/bootstrap/img/bgs/" + bg + "')");
        });

    });
</script>
    