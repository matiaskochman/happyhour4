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

    <div id="pad-wrapper">
        <!-- morris stacked chart -->
        <div class="row">
            <div class="col-md-12">
                <h4 class="title">Morris.js stacked</h4>
            </div>
            <div class="col-md-12">
                <h5>Quarterly Apple iOS device unit sales</h5>
                <br>
                <div id="hero-area" style="height: 250px;"></div>
            </div>
        </div>

        <!-- morris graph chart -->
        <div class="row section">
            <div class="col-md-12">
                <h4 class="title">Morris.js <small>Monthly growth</small></h4>
            </div>
            <div class="col-md-12 chart">                        
                <div id="hero-graph" style="height: 230px;"></div>
            </div>
        </div>

        <!-- jQuery flot chart -->
        <div class="row section">
            <div class="col-md-12">
                <h4 class="title pull-left">
                    jQuery Flot <small>Monthly growth</small>                        
                </h4>
                <div class="btn-group pull-right">
                    <button class="glow left">DAY</button>
                    <button class="glow middle active">MONTH</button>
                    <button class="glow right">YEAR</button>
                </div>
            </div>
            <div class="span12">
                <div id="statsChart"></div>
            </div>
        </div>

        <!-- morris bar & donut charts -->
        <div class="row section">
            <div class="col-md-12">
                <h4 class="title">Morris.js</h4>
            </div>
            <div class="col-md-6 chart">
                <h5>Devices sold</h5>
                <div id="hero-bar" style="height: 250px;"></div>
            </div>
            <div class="col-md-5 chart">
                <h5>Month traffic</h5>
                <div id="hero-donut" style="height: 250px;"></div>    
            </div>
        </div>

        <!-- jQuery knobs -->
        <div class="row section">
            <div class="col-md-12">
                <h4 class="title">jQuery Knob</h4>
            </div>
            <div class="row">
                <div class="col-md-3">     
                    <input type="text" value="50" class="knob second" data-thickness=".3" data-inputColor="#333" data-fgColor="#30a1ec" data-bgColor="#d4ecfd" data-width="140">
                </div>
                <div class="col-md-3">
                    <input type="text" value="75" class="knob second" data-thickness=".3" data-inputColor="#333" data-fgColor="#8ac368" data-bgColor="#c4e9aa" data-width="140">
                </div>
                <div class="col-md-3">
                    <input type="text" value="35" class="knob second" data-thickness=".3" data-inputColor="#333" data-fgColor="#5ba0a3" data-bgColor="#cef3f5" data-width="140">
                </div>
                <div class="col-md-3">
                    <input type="text" value="85" class="knob second" data-thickness=".3" data-inputColor="#333" data-fgColor="#b85e80" data-bgColor="#f8d2e0" data-width="140">
                </div>
            </div>
        </div>
    </div>

</div>
<!-- end main container -->