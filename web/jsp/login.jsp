<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title>Login</title>

    <!-- Bootstrap Core CSS -->

    <!-- Custom CSS -->
    <link href="<spring:url value="/assets/login.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/css/flipclock.css"/>" rel="stylesheet">
    <script src="<spring:url value="/assets/js/jquery.js"/>"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<div class="container">

    <!-- Page Header -->
    <div class="row">
        <div class="col-md-12">
            <div class="col-md-6 ">
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center">Standard countdown</div>
                    <div class="clock" style="margin:2em;"></div>
                    <div class="message"></div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center">Tea & Lunch countdown</div>
                    <div class="clock1" style="margin:2em;"></div>
                    <div class="message"></div>
                </div>
            </div>


        </div>
    </div>

        <div class="card card-container">

                <form wid action="${loginUrl}" class="form-signin" method="post">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p>Invalid username and password.</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            <p>You have been logged out successfully.</p>
                        </div>
                    </c:if>
                    <input  type="text" id="inputEmail" class="form-control" placeholder="Username" name="username"
                           required autofocus>
                    <input   type="password" id="inputPassword" class="form-control" placeholder="Password"
                           name="password" required>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <button   class="btn btn-lg btn-primary btn-block btn-signin" id="loginBtn" type="submit">Sign in
                    </button>
                </form><!-- /form -->

        </div>
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
                }
            }
        });

        clock2 = $('.clock1').FlipClock({
            clockFace: 'HourlyCounter',
            autoStart: false,
            heading: 'teaCountDown',
            callbacks: {
                stop: function () {
                }
            }
        });

            var client;

            client = new Paho.MQTT.Client("192.168.0.134", Number(8900), Math.round(Math.random()*1000).toString());
// set callback handlers
        client.onConnectionLost = onConnectionLost;
        client.onMessageArrived = onMessageArrived;

// connect the client
        client.connect({onSuccess:onConnect});


// called when the client connects
        function onConnect() {
            // Once a connection has been made, make a subscription and send a message.
            client.subscribe("rfid");
            console.log("onConnect");

        }

// called when the client loses its connection
        function onConnectionLost(responseObject) {
            if (responseObject.errorCode !== 0) {
                console.log("onConnectionLost:"+responseObject.errorMessage);
            }
        }

// called when a message arrives
        function onMessageArrived(message) {
            console.log("onMessageArrived:"+message.payloadString);

            $.ajax({
                type: "post",
                url: "/loginrfid",
                datatype:'json',
                data: {
                    rfid: message.payloadString,

                },

                success: function (response) {

document.getElementById("inputEmail").value=response.username;

                    document.getElementById("inputPassword").value=response.password;
$("#loginBtn").click();
                },
                error: function () {
                    alert('Error while request..');
                }
            });

        }





        clock2.setTime(Number(document.getElementById("timeTea").value)-1);
        clock.setTime(Number(document.getElementById("timeLunch").value)-1);


        clock2.setCountdown(true);
        clock2.start();

        clock.setCountdown(true);
        clock.start();


    });

</script>
</body>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>

<script  src="<spring:url value="/assets/js/mqttws31.js"/>"></script>

<script src="<spring:url value="/assets/login.js"/>"></script>
<script src="<spring:url value="/assets/js/flipclock.js"/>"></script>

</html>