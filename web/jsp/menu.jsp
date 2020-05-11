<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Home</title>

    <!-- Bootstrap Core CSS -->

    <!-- Custom CSS -->
    <link href="<spring:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/css/3-col-portfolio.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/css/flipclock.css"/>" rel="stylesheet">
    <script src="<spring:url value="/assets/js/jquery.js"/>"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>


        .crop {
            height: 250px;
            width: 250px;
            overflow: hidden;
        }

        .crop img {
            height: auto;
            width: 250px;
        }
    </style>


    <style>
        .disabled {
            pointer-events: none;
            cursor: default;
        }
    </style>

</head>

<body>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top " style="background-color:#000b54" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a style="color:white" class="navbar-brand" href="/menu"><sec:authentication
                    property="principal.username"/></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a style="color: white" data-toggle="modal" href="#myModal">View Balance</a>
                </li>
                <li>
                    <a style="color: white" href="/history">History</a>
                </li>

                <sec:authorize access="hasRole('ROLE_CHEF') and isAuthenticated()">
                    <li>
                        <a style="color: white" href="/orders">Orders</a>
                    </li>

                    <li>
                        <a style="color: white" href="/additem">Add Menu Item</a>
                    </li>
                    <li>
                        <a style="color: white" href="/quantities">Quantities</a>

                    </li>
                    <li>
                        <a style="color: white" href="/viewmenu">View Menu</a>
                    </li>
                    <li>
                        <a style="color: white" href="/alltimestats">Statistics</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                    <li>
                        <a style="color: white" href="/register">Add User</a>
                    </li>
                    <li>
                        <a style="color: white" href="/factoryhistory">Factory History</a>
                    </li>
                    <li>
                        <a style="color: white" href="/alltimestats">Statistics</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_LINDA') and isAuthenticated()">
                    <li>
                        <a style="color: white" href="/balance">Change Balance</a>
                    </li>
                    <li>
                        <a style="color: white" href="/factoryhistory">Factory History</a>

                    </li>

                    <li>
                        <a style="color: white" href="/totalspent">Total Spent</a>

                    </li>

                    <li>
                        <a style="color: white" href="/alltimestats">Statistics</a>
                    </li>
                </sec:authorize>
                <li>
                    <a style="color: white" href="/logout">Logout</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Page Content -->
<div class="container">

    <!-- Page Header -->
    <div class="row">
        <div class="col-md-12">
            <div class="col-md-6 ">
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center">Standard Menu Countdown</div>
                    <div class="clock" style="margin:2em;"></div>
                    <div class="message"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center">Tea & Lunch Menu Countdown</div>
                    <div class="clock1" style="margin:2em;"></div>
                    <div class="message"></div>
                </div>
            </div>


        </div>
    </div>
    <!-- /.row -->

    <!-- Projects Row -->
    <div class="row">
        <div class="col-md-3 portfolio-item">
            <a href="/tea" id="tea">
                <img class="crop img-responsive" src="/assets/images%20for%20canteen/sandwich.png" alt="">
            </a>
            <h3>
                <a href="/tea" id="teatext">Tea Menu</a>
            </h3>
            <p>Mainly toasted sandwiches and snacks. Place order before 8.</p>
        </div>
        <div class="col-md-3 portfolio-item">
            <a href="/lunch" id="lunch">
                <img class="crop img-responsive" src="/assets/images%20for%20canteen/macandcheese.jpg" alt="">
            </a>
            <h3>
                <a href="/lunch" id="lunchtext">Lunch Menu</a>
            </h3>
            <p>Wide Variety of freshly prepared dishes. Place order before 8.30. </p>
        </div>
        <div class="col-md-3 portfolio-item">
            <a href="/standard" id="standard">
                <img class="crop img-responsive" src="/assets/images%20for%20canteen/cheese.jpg" alt="">
            </a>
            <h3>
                <a href="/standard" id="standardtext">Standard Menu</a>
            </h3>
            <p>Mainly toasted sandwiches and snacks. Place order before 10.30.</p>
        </div>
        <div class="col-md-3 portfolio-item">
            <a href="/takeaway" id="takeaway">
                <img class="crop img-responsive" src="/assets/images%20for%20canteen/chips1.jpg" alt="">
            </a>
            <h3>
                <a href="/takeaway" id="takeawaytext">Takeaway Menu</a>
            </h3>
            <p>Leftovers that the chef is willing to sell.</p>
        </div>
    </div>
    <!-- /.row -->
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"></button>

                    <h4 class="modal-title">Balance of <sec:authentication property="principal.username"/></h4>
                </div>
                <div class="modal-body">

                    <h1>Balance: ${balance}</h1>


                </div>
                <p></p>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                </div>
            </div>

        </div>
    </div>

    <!-- /.row -->

    <hr>
    <input type="hidden" id="timeLunch" value="${timeLunch}">
    <input type="hidden" id="timeTea" value="${timeTea}">

</div>
<script type="text/javascript">
    var clock;

    $(document).ready(function () {
        var clock;
        var clock2;

        clock = $('.clock').FlipClock({
            clockFace: 'HourlyCounter',
            autoStart: false,
            heading: 'LunchCountDown',
            callbacks: {
                stop: function () {
                    if ("<sec:authentication property="principal.username"/>" == "Ronny&#32;Pelucci" && "<sec:authentication property="principal.username"/>" == "Russell&#32;Gwynn" && "<sec:authentication property="principal.username"/>" == "John&#32;Pelucci" && "<sec:authentication property="principal.username"/>" == "Sanjay&#32;Sookdeo") {


                    } else {
                        document.getElementById("standard").className = "disabled";
                        document.getElementById("standardtext").className = "disabled";
                        $('.disabled').click(function (e) {
                            e.preventDefault();
                        });
                    }
                }
            }
        });

        clock2 = $('.clock1').FlipClock({
            clockFace: 'HourlyCounter',
            autoStart: false,
            heading: 'LunchCountDown',
            callbacks: {
                stop: function () {
                    if ("<sec:authentication property="principal.username"/>" != "Ronny&#32;Pelucci" && "<sec:authentication property="principal.username"/>" != "Russell&#32;Gwynn" && "<sec:authentication property="principal.username"/>" != "Guest1" && "<sec:authentication property="principal.username"/>" != "Guest2" && "<sec:authentication property="principal.username"/>" != "Guest3" && "<sec:authentication property="principal.username"/>" != "John&#32;Pelucci" && "<sec:authentication property="principal.username"/>" != "Sanjay&#32;Sookdeo") {

                        document.getElementById("tea").className = "disabled";
                        document.getElementById("lunch").className = "disabled";
                        document.getElementById("lunchtext").className = "disabled";
                        document.getElementById("teatext").className = "disabled";


                        $('.disabled').click(function (e) {
                            e.preventDefault();
                        });
                    } else {

                    }
                }
            }
        });


        clock2.setTime(Number(document.getElementById("timeTea").value) - 1);
        clock.setTime(Number(document.getElementById("timeLunch").value) - 1);

        clock2.setCountdown(true);
        clock2.start();

        clock.setCountdown(true);
        clock.start();
        if ("<sec:authentication property="principal.username" />" != "Guest") {
            $("#myModal").modal();
        }
    });
</script>


<script>

</script>
<!-- /.container -->

<!-- jQuery -->
<script src="<spring:url value="/assets/js/flipclock.js"/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>

</body>

</html>
