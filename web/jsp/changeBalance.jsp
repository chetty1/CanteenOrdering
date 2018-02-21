<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: chett_000
  Date: 2017/05/10
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<spring:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/font-awesome/css/font-awesome.css"/>" rel="stylesheet">

    <title>Change Balance</title>

    <style>


        #custom-search-input {
            margin: 0;
            margin-top: 10px;
            padding: 0;
        }

        #custom-search-input .search-query {
            padding-right: 3px;
            padding-right: 4px \9;
            padding-left: 3px;
            padding-left: 4px \9;
            /* IE7-8 doesn't have border-radius, so don't indent the padding */

            margin-bottom: 0;
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
        }

        #custom-search-input button {
            border: 0;
            background: none;
            /** belows styles are working good */
            padding: 2px 5px;
            margin-top: 2px;
            position: relative;
            left: -28px;
            /* IE7-8 doesn't have border-radius, so don't indent the padding */
            margin-bottom: 0;
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
            color: #D9230F;
        }

        .search-query:focus + button {
            z-index: 3;
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
            <a class="navbar-brand" style="color:white;" href="/menu"><sec:authentication
                    property="principal.username"/></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">

                <li>
                    <a style="color: white" href="/menu">Menu</a>
                </li>
                <li>
                    <a style="color: white" href="/history">View History</a>
                </li>
                <li>
                    <a style="color: white" href="/factoryhistory">Factory History</a>
                </li>
                <li>
                    <a style="color: white" href="/totalspent">Total Spent</a>

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
        <div class="col-lg-12">
            <h1 class="page-header">
                <h3></h3>

            </h1>
        </div>
        <p></p>
        <div class="row">

            <form method="post" action="/balance" enctype="multipart/form-data">

                <div id="custom-search-input">
                    <div class="input-group col-lg-3" style="left: 1%">
                        <input type="text" name="search" class="  search-query form-control" id="search"
                               placeholder="Search"/>

                        <span class="input-group-btn">

                                    <button class="btn btn-danger" type="button">
                                        <span class=" glyphicon glyphicon-search"></span>
                                    </button>
                                </span>

                    </div>

                </div>
            </form>

        </div>
        <button type="button" class="btn btn-primary" onclick="correctBalance()">Correct all Balances to R500</button>

        <c:if test="${not empty balanceList}">

            <c:forEach items="${balanceList}" var="balanceList">
                <h3>
                    <a data-toggle="modal" href="#myModal${balanceList.id}">${balanceList.name}</a>
                </h3>

                <div id="myModal${balanceList.id}" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"></button>

                                <h4 class="modal-title">Change Balance of ${balanceList.name}</h4>
                            </div>
                            <div class="modal-body">

                                <small>Amount:</small>

                                <input type="number" name="emailid" id="amount${balanceList.id}" value="">


                            </div>
                            <p></p>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" id="${balanceList.id}"
                                        onclick="save(this.id)" data-dismiss="modal">Save
                                </button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                            </div>
                        </div>

                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>


</div>


</body>
<script>
    function save(id) {
        $.ajax({
            type: "post",
            url: "/change",
            datatype: 'json',
            data: {
                id: id,
                amount: document.getElementById("amount" + id).value
            },

            success: function (response) {
                console.log(response);

            },
            error: function () {
                alert('Error while request..');
            }
        });
    }
</script>

<script>
    function correctBalance() {
        $.ajax({
            type: "post",
            url: "/correct",
            datatype: 'json',


            success: function (response) {
                alert('Success')
            },
            error: function () {
                alert('Error while request..');
            }
        });
    }
</script>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</html>
