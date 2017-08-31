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
    <title>Orders</title>

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
        <thead>
        <tr>
            <th style="text-align: center">Name</th>
            <th style="text-align: center">Order</th>
            <th style="text-align: center">Quantity</th>
        </tr>
        </thead>
        <tbody>

        <c:if test="${not empty teaList}">

            <c:forEach items="${teaList}" var="teaLists">

                <tr>
                    <td>${teaLists.user.name}</td>
                    <td>${teaLists.food.name}</td>
                    <td>${teaLists.quantity}</td>
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


        <table class="table table-bordered" style="text-align: center" id="lunchTable">
            <thead >
            <tr>
                <th style="text-align: center">Name</th>
                <th style="text-align: center">Order</th>
                <th style="text-align: center">Quantity</th>
            </tr>
            </thead>
            <tbody>


<c:if test="${not empty lunchList}">

    <c:forEach items="${lunchList}" var="lunchLists">

        <tr>
                <td>${lunchLists.user.name}</td>
                <td>${lunchLists.food.name}</td>
                <td>${lunchLists.quantity}</td>
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


        <table class="table table-bordered" style="text-align: center" id="takeawayTable">
            <thead >
            <tr>
                <th style="text-align: center">Name</th>
                <th style="text-align: center">Order</th>
                <th style="text-align: center">Quantity</th>
            </tr>
            </thead>
            <tbody>


            <c:if test="${not empty takeawayList}">

                <c:forEach items="${takeawayList}" var="takeawayLists">

                    <tr>
                        <td>${takeawayLists.user.name}</td>
                        <td>${takeawayLists.food.name}</td>
                        <td>${takeawayLists.quantity}</td>
                    </tr>

                </c:forEach>
            </c:if>
            </tbody>
        </table>
            </div>
    </div>
</div>

</div>

</div>
</body>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script>
    $(function(){
        setInterval(oneSecondFunction, 1000);
    });

    function oneSecondFunction() {
        var d = new Date(); // for now
        // => 9
        if ((d.getHours()=="10")&&(d.getMinutes()=="15") && (d.getSeconds()=="00")){
            $("#teaTable tbody tr").remove();

        }
        else if((d.getHours()=="13")&&(d.getMinutes()=="30") && (d.getSeconds()=="00")){
            $("#lunchTable tbody tr").remove();

        }
        else if((d.getHours()=="16")&&(d.getMinutes()=="30") && (d.getSeconds()=="00")){
            $("#takeawayTable tbody tr").remove();

        }

    }



</script>

<script>
    var source = new EventSource("/liveorders");
    source.onmessage = function(event) {


    var data= JSON.parse(event.data); //$.parseJSON(event.data);
console.log(data);
        $.each(data, function(i, item) {
            if(item.time=="lunch"){
                var table = document.getElementById("lunchTable");
                var row = table.insertRow(-1);

// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
                var cell2 = row.insertCell(0);
                var cell3 = row.insertCell(1);
                var cell4 = row.insertCell(2);



// Add some text to the new cells:

                cell2.innerHTML = data[i].user.name;
                cell3.innerHTML= data[i].food.name;
                cell4.innerHTML= data[i].quantity;

            }
            else if (item.time=="tea"){
                var teatable = document.getElementById("teaTable");
                var tearow = teatable.insertRow(-1);

// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
                var teacell2 = tearow.insertCell(0);
                var teacell3 = tearow.insertCell(1);
                var teacell4 = tearow.insertCell(2);



// Add some text to the new cells:

                teacell2.innerHTML = data[i].user.name;
                teacell3.innerHTML= data[i].food.name;
                teacell4.innerHTML= data[i].quantity;

            }
            else if (item.time=="takeaway") {
                var ttable = document.getElementById("takeawayTable");
                var trow = ttable.insertRow(-1);

// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
                var tcell2 = trow.insertCell(0);
                var tcell3 = trow.insertCell(1);
                var tcell4 = trow.insertCell(2);



// Add some text to the new cells:

                tcell2.innerHTML = data[i].user.name;
                tcell3.innerHTML= data[i].food.name;
                tcell4.innerHTML= data[i].quantity;
            }
            });


// Create an empty <tr> element and add it to the 1st position of the table:

    }
</script>
<!-- Bootstrap Core JavaScript -->
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</html>
