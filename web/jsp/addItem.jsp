<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Add Item</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/3-col-portfolio.css" rel="stylesheet">
    <link href="/assets/css/flipclock.css" rel="stylesheet">
    <script src="/assets/js/jquery.js"></script>

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

        input.upload {
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

        <section>
            <h1 class="entry-title"><span>Edit/Add Menu Item</span></h1>
            <hr>
            <form class="form-horizontal" name="signup" id="signup" method="post" action="/additem"  enctype="multipart/form-data">
                <div class="form-group">
                    <label class="control-label col-sm-3">Name</label>
                    <div class="col-md-8 col-sm-9">
                        <div class="input-group">
                            <input type="name" class="form-control" name="emailid" id="itemname" value="">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Price</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input type="number" class="form-control" name="mem_name" id="price" value="">

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Menu Type</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group" style="padding-top: 8px">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" onclick="yolo(this.id)" name="radio" id="tea" value="option1" >
                                Tea
                            </label>

                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" onclick="yolo(this.id)" name="radio" id="lunch" value="option2">
                                Lunch
                            </label>
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" onclick="yolo(this.id)" name="radio" id="standard" value="option3" >
                                Standard
                            </label>
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" onclick="yolo(this.id)" name="radio" id="takeaway" value="option3" >
                                Takeaway
                            </label>
                            <input type="hidden" id="time" name="time" value="">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">is Sandwich</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group" style="padding-top: 8px">
                            <label class="form-check-label">
                                <input type="checkbox" onclick="onSandwich()" class="form-check-input" name="sandwich" id="isSandwich" value="option1" >
                                Sandwich
                            </label>
                            <input type="hidden" id="sandwich" name="sandwich1" value="standard">


                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-sm-3">Description </label>
                    <div class="col-md-5 col-sm-8">
                        <textarea class="form-control" name="desc" rows="5" id="comment"></textarea>
                    </div>
                </div>



                <div class="form-group" >
                    <label class="control-label col-sm-3">Photo <br>
                    </label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input  type="file" name="img" id="file" class="form-control upload" placeholder=""
                                   aria-describedby="file_upload">
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-10">
                        <input type="submit" name="submit"  type="button" value="Save" class="btn btn-primary">

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
    $(document).ready(function () {
    <c:if test="${pageContext.request.method=='POST'}">
    alert("Success");

    </c:if>
    });
</script>
<script src="/assets/js/bootstrap.js"></script>
<script>

    function yolo(id) {

            document.getElementById("time").value = id;


console.log(document.getElementById("time").value);

    }

</script>
<script>
    function  onSandwich() {
        document.getElementById("sandwich").value = "sandwich";
console.log(document.getElementById("sandwich").value);
    }
</script>
</html>
