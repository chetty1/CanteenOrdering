<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <link href="<spring:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/font-awesome/css/font-awesome.css"/>" rel="stylesheet">
    <script type="text/javascript">
        window.onload = function () {
            var chart = new CanvasJS.Chart("chartContainer", {
                title: {
                    text: "Bar Graph Showing food frequency"
                },
                data: [{
                    type: "column",
                    dataPoints: [


                    <c:if test="${not empty statList}">

                    <c:forEach items="${statList}" var="statLists">

                        { y: ${statLists.value}, label: "${statLists.key}" },

                    </c:forEach>
                    </c:if>
                    ]
                }]
            });
            chart.render();
        }
    </script>
    <script src="<spring:url value="/assets/canvasjs.min.js"/>"></script>
    <title>Statistics</title>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top"style="background-color:#000b54" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" style="color:white;" href="/menu"><sec:authentication property="principal.username" /></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a style="color: white" href="/menu">Home</a>
                </li>

                <li>
                    <a style="color: white" href="/teastats">Tea Stats</a>
                </li>
                <li>
                    <a style="color: white" href="/lunchstats">Lunch Stats</a>
                </li>


            </ul>


        </div>
        <!--<div class="col-md-8" style="align-content: center">
            <i class="fa fa-shopping-cart pull-right" style="color: grey; "></i>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<h1 class="page-header"></h1>
<p></p>


<div id="chartContainer" style="height: 400px; width: 100%;"></div>

</body>

</html>