<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chett_000
  Date: 2018/01/24
  Time: 9:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<spring:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/font-awesome/css/font-awesome.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/dist/css/bootstrap-datepicker.css"/>" rel="stylesheet">

    <title>Total Spent by Staff</title>
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
                    <a style="color: white" href="/balance">Change Balance</a>
                </li>
            </ul>


        </div>
        <!--<div class="col-md-sxxsx8" style="align-content: center">
            <i class="fa fa-shopping-cart pull-right" style="color: grey; "></i>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<h3></h3>
<h3></h3>
<h3></h3>
<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <h1 class="page-header">
                <h3>Total Spent</h3>
            </h1>
            <form class="form-horizontal" name="totalspent" id="totalspent" method="post" action="/totalspent"
                  enctype="multipart/form-data">

                <div class="col-lg-5">
                    <div class="input-group input-daterange">
                        <input type="text" name="before" class="form-control">
                        <div class="input-group-addon">To</div>
                        <input type="text" name="after" class="form-control">
                    </div>

                </div>
                <button type="submit" name="submit" class="btn btn-default" data-dismiss="modal">
                    Search
                </button>
            </form>

        </div>
    </div>
    <div class="row">
        <p></p>
        <div class="col-lg-6">
            <table class="table table-bordered" style="text-align: center" id="teaTable">
                <thead>
                <tr>
                    <th style="text-align: center">Date Between</th>

                    <th style="text-align: center">Name</th>

                    <th style="text-align: center">Total Spent</th>

                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty spentList}">

                    <c:forEach items="${spentList}" var="List">
                        <tr>

                            <th>${dateBefore}-${dateAfter}</th>
                            <td>${List.key}</td>
                            <td>R${List.value}</td>

                        </tr>
                    </c:forEach>
                </c:if>

                </tbody>
            </table>
        </div>
    </div>

</div>
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script src="<spring:url value="/assets/dist/js/bootstrap-datepicker.js"/>"></script>

<script type="text/javascript">
    $('.input-daterange input').each(function () {
        $(this).datepicker({
            format: 'dd/mm/yyyy'

        });
    })
</script>

</body>

</html>
