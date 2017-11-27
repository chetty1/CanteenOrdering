<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: chett_000
  Date: 2017/06/02
  Time: 11:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/3-col-portfolio.css" rel="stylesheet">
    <link href="/assets/css/flipclock.css" rel="stylesheet">
    <script src="/assets/js/jquery.js"></script>
    <title>Register</title>


    <style>

        body {
            background: #fff;
            font-family: 'Roboto', sans-serif;
            color: #333;
            line-height: 22px;
        }

        h1, h2, h3, h4, h5, h6 {
            font-family: 'Roboto Condensed', sans-serif;
            font-weight: 400;
            color: #111;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        h1, h2, h3 {
            text-transform: uppercase;
        }

        input .hello {
            position: absolute;
            top: 0;
            right: 0;
            margin: 0;
            padding: 0;
            font-size: 12px;
            cursor: pointer;
            opacity: 1;
            filter: alpha(opacity=1);
        }

        .form-inline .form-group {
            margin-left: 0;
            margin-right: 0;
        }

        .control-label {
            color: #333333;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color:#000b54" role="navigation">
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
            <a class="navbar-brand" style="color:white;" href="/menu"></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a style="color: white" href="/menu">Menu</a>
                </li>
                <li>
                    <a style="color: white" href="/login">Logout</a>
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

<div class="container">
    <div class="row">

        <section>
            <h1 class="entry-title"><span>Register User</span></h1>
            <hr>
            <form class="form-horizontal" method="post" name="signup" id="signup" enctype="multipart/form-data">
                <div class="form-group">

                    <label class="control-label col-sm-3">Name</label>
                    <div class="col-md-8 col-sm-9">
                        <div class="input-group">
                            <input type="name" style="width: 250px" class="form-control" name="emailid" id="name">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Username</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input type="text" style="width: 250px" class="form-control" name="mem_name" id="username">

                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-sm-3"> Role</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group" style="padding-top: 8px">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" onclick="yolo1(this.id)" name="ramdio"
                                       id="ROLE_STAFF" value="">
                                Office Staff
                            </label>

                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" onclick="yolo1(this.id)" name="ramdio"
                                       id="ROLE_FLOOR_STAFF" value="">
                                Floor Staff
                            </label>

                            <input type="hidden" id="role" name="time" value="">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3">Type</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group" style="padding-top: 8px">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" onclick="yolo(this.id)" name="radio"
                                       id="ROLE_CHEF" value="option1">
                                Chef
                            </label>

                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" onclick="yolo(this.id)" name="radio"
                                       id="ROLE_LINDA" value="option2">
                                Accounts
                            </label>

                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" onclick="yolo(this.id)" name="radio"
                                       id="ROLE_ADMIN" value="option2">
                                Admin
                            </label>
                            <input type="hidden" id="type" name="time" value="">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Password</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input type="name" style="width: 250px" class="form-control" name="mem_name" id="password">

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Confirm Password</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input type="name" style="width: 250px" class="form-control" name="mem_name"
                                   id="Confirmpassword">

                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-10">
                        <input type="button" value="Save" onclick="onSave()" class="btn btn-primary">
                    </div>
                </div>

            </form>
        </section>
    </div>
</div>
</div>
</div>
</div>


</body>
<script>
    function onSave() {

        $.ajax({
            type: "post",
            url: "/reguser",
            datatype: 'json',
            data: {
                name: document.getElementById("name").value,
                username: document.getElementById("username").value,
                password: document.getElementById("password").value,
                confpassword: document.getElementById("Confirmpassword").value,
                type: document.getElementById("type").value,
                role: document.getElementById("role").value

            },

            success: function (response) {
                alert("User Successfully Added");
                document.getElementById("name").value = "";
                document.getElementById("username").value = "";
                document.getElementById("password").value = "";
                document.getElementById("Confirmpassword").value = "";
                document.getElementById("role").value = "",
                        document.getElementById("type").value = "",
                        document.getElementById("ROLE_STAFF").checked = false,

                        document.getElementById("ROLE_FLOOR_STAFF").checked = false,

                        document.getElementById("ROLE_ADMIN").checked = false,

                        document.getElementById("ROLE_CHEF").checked = false,

                        document.getElementById("ROLE_LINDA").checked = false

            },
            error: function () {
                alert('Passwords Dont Match');
            }
        });

        console.log(document.getElementById("username").value)
    }
</script>
<script>
    function yolo(id) {

        document.getElementById("type").value = id;


        console.log(document.getElementById("type").value);

    }
</script>
<script>
    function yolo1(id) {

        document.getElementById("role").value = id;


        console.log(document.getElementById("role").value);

    }
</script>
<script>
    $(document).ready(function () {


        var client;

        client = new Paho.MQTT.Client("192.168.0.3", Number(8900), Math.round(Math.random() * 1000).toString());
        // set callback handlers
        client.onConnectionLost = onConnectionLost;
        client.onMessageArrived = onMessageArrived;

        // connect the client
        client.connect({onSuccess: onConnect});


        // called when the client connects
        function onConnect() {
            // Once a connection has been made, make a subscription and send a message.
            console.log("onConnect");
            client.subscribe("rfid");

        }

        // called when the client loses its connection
        function onConnectionLost(responseObject) {
            if (responseObject.errorCode !== 0) {
                console.log("onConnectionLost:" + responseObject.errorMessage);
            }
        }

        // called when a message arrives
        function onMessageArrived(message) {
            console.log("onMessageArrived:" + message.payloadString);

            document.getElementById("password").value = message.payloadString;
            document.getElementById("Confirmpassword").value = message.payloadString;

        }
    });

</script>
<script src="<spring:url value="/assets/js/mqttws31.js"/>"></script>

<script src="/assets/js/bootstrap.js"></script>
</html>
