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
    <title>Quantities</title>

    <style>
        button{
            height: 25px;
        }
        td{
            font-size: 25px;
        }
    </style>
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
                    <a style="color: white" href="/orders">Orders</a>
                </li>

                <li>
                    <a style="color: white" href="/additem">Add Menu Item</a>
                </li>
                <li>
                    <a style="color: white" href="/viewmenu">View Menu</a>
                </li>
                <li>
                    <a style="color: white" href="/takeawayOrders">Takeaway</a>
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
            <div class="col-lg-4">
                <h1 class="page-header">
                    <h3>Tea Orders</h3>
                </h1>


                <table class="table table-bordered" style="text-align: center" id="teaTable">

                    <tbody>

                    <c:if test="${not empty teaList}">

                        <c:forEach items="${teaList}" var="teaLists">

                            <tr >
                                <td>${teaLists.key}</td>
                                <td>${teaLists.value}</td>

                            </tr>

                        </c:forEach>
                    </c:if>

                    </tbody>
                </table>
            </div>

            <div class="col-lg-4">
                <h1 class="page-header">
                    <h3>Lunch Orders</h3>
                </h1>


                <table class="table table-bordered" style="text-align: center; " id="lunchTable">

                    <tbody>


                    <c:if test="${not empty lunchList}">

                        <c:forEach items="${lunchList}" var="lunchLists">

                            <tr  >
                                <td>${lunchLists.key}</td>
                                <td>${lunchLists.value}</td>

                            </tr>

                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>

            <div class="col-lg-4">
                <h1 class="page-header">
                    <h3>Takeaway Orders</h3>
                </h1>


                <table class="table table-bordered" style="text-align: center; " id="takeawayTable">

                    <tbody>


                    <c:if test="${not empty takeawayList}">

                        <c:forEach items="${takeawayList}" var="takeawayhLists">

                            <tr  >
                                <td>${takeawayhLists.key}</td>
                                <td>${takeawayhLists.value}</td>

                            </tr>

                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>

        </div>

    </div>
</div>
</body>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>



<!-- Bootstrap Core JavaScript -->
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</html>
