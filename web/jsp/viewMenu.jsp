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
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title>View Menu</title>
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
            </ul>

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

        <table class="table table-bordered" style="text-align: center" id="teaTable">
            <thead>
            <tr>
                <th style="text-align: center">Name</th>
                <th style="text-align: center">Tea/Lunch/Standard/Takeaway</th>
                <th style="text-align: center">Today's Menu</th>
                <th style="text-align: center">View/Edit Menu</th>
            </tr>
            </thead>
            <tbody>
<c:if test="${not empty itemList}">

    <c:forEach items="${itemList}" var="itemLists">
            <tr>
                <td>${itemLists.name}</td>
                <td>
                    ${itemLists.time}
                </td>
<td>
                    <c:choose>
                    <c:when test="${itemLists.today==true}">
                        <input type="checkbox" name="myTextEditBox" id="${itemLists.id}" onclick="onView(this.id)" value="" checked/>
                    </c:when>
                    <c:when test="${itemLists.today==false}">
                        <input type="checkbox" name="myTextEditBox" id="${itemLists.id}" onclick="onView(this.id)" value="" />
                    </c:when>
                    </c:choose>

                </td>
                <td>

                    <a type="button" href="${itemLists.id}/edititem" class="btn button-info" style="background-color: #4580e0;  color: white">view</a>

                </td>

            </tr>

</c:forEach>
    </c:if>
            </tbody>
        </table>


    </div>


</div>


</body>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script>
    function onView(id) {

        console.log(document.getElementById(id).checked);
        $.ajax({
            type: "post",
            url: "/check",
            datatype: 'json',
            data: {
                id: id,
                checked: document.getElementById(id).checked

            },

            success: function (response) {

alert("Successfully Changed");
            },
            error: function () {
                alert('Error while request..');
            }
        });
    }
</script>

<!-- Bootstrap Core JavaScript -->
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</html>
