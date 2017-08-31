<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>


<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>Checkout</title>

    <!-- Bootstrap Core CSS -->

    <!-- Custom CSS -->
    <link href="<spring:url value="/assets/css/bootstrap.css"/>" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<style>
    body {
        margin-top: 80px;
        margin-left: 25%;
    }
    @media (max-width: 450px)
     {
        body {
            margin-left: 0%;
        }
        .text-right{
text-align: center;
        }
        img{
            width: 0%;
            height: 0%;
        }
    }
</style>
<body>
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
            <a style="color:white" class="navbar-brand" href="/menu"><sec:authentication property="principal.username" /></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a style="color: white"  href="/menu">Menu</a>
                </li>
                <li>
                    <a style="color: white" data-toggle="modal" href="#balance">View Balance</a>
                </li>
                <li>
                    <a style="color: white"  href="/logout">Logout</a>
                </li>

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">
                        <div class="row">
                            <div class="col-md-6">
                                <h5><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</h5>
                            </div>
                            <div class="col-md-6">
                                <a type="button" class="btn btn-primary btn-sm btn-block" href="/menu">
                                    <span class="glyphicon glyphicon-share-alt"></span> Continue shopping
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
<div class="panel-body">

<c:if test="${not empty transactionList}">

    <c:forEach items="${transactionList}" var="transLists">
                    <div class="row">
                        <div class="col-md-2"><img class="img-responsive" src="${transLists.food.picName}">
                        </div>
                        <div class="col-md-4">
                            <h4 class="product-name"><strong>${transLists.food.name}</strong></h4><h4><small>Product description</small></h4>
                        </div>
                        <div class="col-md-6">
                            <div class="col-xs-6 text-right">
                                <h6><strong>R${transLists.food.price} <span class="text-muted">x</span></strong></h6>
                            </div>
                            <div class="col-xs-4">
                                <input type="text" class="form-control input-sm" value="${transLists.quantity}" disabled>
                            </div>
                            <div class="col-md-2">
                                <button type="button" href="/checkout" id="delete${transLists.id}" onclick="onDelete(this.id)" class="btn btn-link btn-xs">
                                    <span class="glyphicon glyphicon-trash"> </span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <hr>

        </c:forEach>
    </c:if>


                </div>
                <div class="panel-footer">
                    <div class="row text-center">
                        <div class="col-md-9">
                            <h4 class="text-right">Total <strong>R${total}</strong></h4>
                        </div>
                        <div class="col-md-3">

<sec:authorize access="hasRole('ROLE_STAFF') and isAuthenticated()">

<button type="button" onclick="onCheckout()" class="btn btn-success btn-block">
                                Checkout
                            </button>
    </sec:authorize>

                            <sec:authorize access="hasRole('ROLE_FLOOR_STAFF') and isAuthenticated()">

                                <h5 style="font-weight: 700">
                                    SWIPE CARD
                                </h5>

                            </sec:authorize>
</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>

                <h4 class="modal-title">Insufficient Funds</h4>
            </div>
            <div class="modal-body">

                <h4>Please Speak to Linda</h4>



            </div>
            <p></p>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-default" onclick="logout()" data-dismiss="modal">Logout</button>

            </div>
        </div>

    </div>
</div>

<div id="balance" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>

                <h4 class="modal-title">View Balance</h4>
            </div>
            <div class="modal-body">

                <h4>Balance: ${balance}</h4>



            </div>
            <p></p>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

            </div>
        </div>

    </div>


</div>
</body>


<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script>
    function logout(){
        window.location="/logout?logout";

    }
    </script>
<script>
    function onCheckout() {


        $.ajax({




            type: "post",
            url: "/clickcheckout",
            datatype:'json',
            data: {
                checkout: true,

            },

            success: function (response) {
                console.log(response);
                if(response==false){
                    $("#myModal").modal();
                }
                else{
                    alert("Order Successful click ok");
                    window.location="/login?logout"

                }

            },
            error: function () {
                alert('Error while request..');

            }
        });





    }
</script>

<script>
    function onDelete(id){

        $.ajax({
            type: "post",
            url: "/clickdelete",

            data: {
                Id: id,

            },

            success: function (response) {
                console.log(response);
                window.location="/checkout"

            },
            error: function () {
                window.location="/checkout"

                //alert('Try Again');
            }
        });



    }
</script>

<script>
    $(document).ready(function () {

        try {
            var client;

            client = new Paho.MQTT.Client("localhost", Number(8900), Math.round(Math.random() * 1000).toString());
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
                onCheckout();
            }
        }
        catch (err){

        }

    });

</script>
<script  src="<spring:url value="/assets/js/mqttws31.js"/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</html>


