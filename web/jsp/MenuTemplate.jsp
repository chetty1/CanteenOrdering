<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>Menu</title>

    <!-- Bootstrap Core CSS -->

    <!-- Custom CSS -->
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/3-col-portfolio.css" rel="stylesheet">
    <link href="/assets/css/flipclock.css" rel="stylesheet">
    <script src="/assets/js/jquery.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


    <script type="text/javascript">

        var quantity = 0;

        $(document).ready(function () {

            $('.quantity-right-plus').click(function (e) {

                // Stop acting like a button
                e.preventDefault();
                // Get the field name
                quantity = parseInt($('#quantity').val());

                // If is not undefined

                $('#quantity').val(quantity + 1);


                // Increment

            });

            $('.quantity-left-minus').click(function (e) {
                // Stop acting like a button
                e.preventDefault();
                // Get the field name
                quantity = parseInt($('#quantity').val());

                // If is not undefined

                // Increment
                if (quantity > 0) {
                    $('#quantity').val(quantity - 1);
                }
            });


            if ($("#choose").text().trim() == "Choose Sandwich") {
                document.getElementById("buySandwich").disabled = true;
            }
            console.log($("#choose").text().trim());

            if ($("#chooseChips").text().trim() == "Choose Size") {
                document.getElementById("buyChips").disabled = true;
            }

        });

    </script>


    <script>
        function click2(id) {

            if (id == "large") {
                $("#chooseChips").text($("#" + id).text());
                $("#priceChips").text("R" + document.getElementById("priceInputLarge").value);
                document.getElementById("buyChips").disabled = false;
                document.getElementById("buyChips").setAttribute("name", "Large Chips");
                console.log(document.getElementById("buyChips").name);


            }
            else {
                $("#chooseChips").text($("#" + id).text());
                $("#priceChips").text("R" + document.getElementById("priceInputSmall").value);
                document.getElementById("buyChips").disabled = false;
                document.getElementById("buyChips").setAttribute("name", "Small Chips");
                console.log(document.getElementById("buyChips").name);

            }

        }
    </script>
    <script>

        function onBuy(id) {
            console.log(document.getElementById(id));
            $.ajax({
                type: "post",
                url: "${time}/clickbuy",
                datatype: 'json',
                data: {
                    Id: id,

                },

                success: function (response) {
                    console.log(response);
                },
                error: function () {
                    alert('Error while request..');
                }
            });

           // console.log(document.getElementById("buyChips").name);
          //  console.log(id);
            if(document.getElementById("buyChips")!=undefined) {

                if (document.getElementById("buyChips").name.trim() == id.trim()) {
                    $("#foodName").text(document.getElementById("buyChips").name + "\t has been added to your cart \n");
                    $("#myModal").modal();
                    // document.getElementById("buyChips").name = "";

                }
            }
            if (document.getElementById(id) == null && id != "Small Chips" && id != "Large Chips") {
                $("#foodName").text($("#name" + id).text() + "\t has been added to your cart \n");
                $("#myModal").modal();
                return;
            } else if (document.getElementById(id) != null && id != "Small Chips" && id != "Large Chips") {
                $("#foodName").text($("#name" + id).text() + "\t has been added to your cart \n");
                $("#myModal").modal();
            }
        }


        /* else{
         $("#foodName").text($("#name"+id).text() + "\t has been added to your cart \n");

         }
         $("#myModal").modal();

         }*/
    </script>
    <style>


        .crop {
            height: 300px;
            width: 400px;
            overflow: hidden;
        }

        .crop img {
            height: auto;
            width: 400px;
        }
    </style>

    <!-- <style>


         .crop1 {
             height: 450px;
             width: 380px;
             overflow: hidden;
         }
         .crop1  {
             height: auto;
             width: 350px;
         }
     </style>
 -->
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
                    <a style="color: white" data-toggle="modal" href="#viewBalance">View Balance</a>
                </li>
                <li>
                    <a style="color: white" href="/history">History</a>
                </li>

                <li>
                    <a style="color: white" href="/checkout">Checkout</a>
                </li>
                <sec:authorize access="hasRole('ROLE_CHEF') and isAuthenticated()">
                    <li>
                        <a style="color: white" href="/orders">Orders</a>
                    </li>

                    <li>
                        <a style="color: white" href="/additem">Add Menu Item</a>
                    </li>
                    <li>
                        <a style="color: white" href="/viewmenu">View Menu</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
                    <li>
                        <a style="color: white" href="/register">Add User</a>
                    </li>
                    <li>
                        <a style="color: white" href="/factoryhistory">Factory History</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_LINDA') and isAuthenticated()">
                    <li>
                        <a style="color: white" href="/balance">Change Balance</a>
                    </li>

                </sec:authorize>
                <li>
                    <a style="color: white" href="/${time1}">${time1}</a>
                </li>
                <li>
                    <a style="color: white" href="/${time2}">${time2}</a>
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

    </div>
    <!-- /.row -->

    <!-- Projects Row -->
    <div class="row">
        <c:if test="${takeaway==false}">

            <div class="crop1 col-md-4 portfolio-item">
                <a href="#">
                    <img class=" crop img-responsive" src="/assets/images%20for%20canteen/cheese.jpg" alt="">
                </a>
                <h3>
                    <a>Toasted Sandwiches</a>
                </h3>
                <p>Mainly toasted sandwiches and snacks.</p>


                <h3 id="price"></h3>

                <div class="dropup">
                    <button id="choose" class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                        Choose Sandwich
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <c:if test="${not empty sandwichList}">

                            <c:forEach items="${sandwichList}" var="sandwichList">

                                <li><a href='#' onclick="click1(this.id);"
                                       id="name${sandwichList.id}">${sandwichList.name}</a>
                                    <input type="hidden" id="price${sandwichList.id}" value="${sandwichList.price}">
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                    <button onclick="onBuy(this.name);" class="btn btn-info pull-right " name="" id="buySandwich"
                            style="background-color: #4580e0;border-color: #4580e0">Buy
                    </button>

                </div>

            </div>


            <!-- /.row -->

            <div class="crop1 col-md-4 portfolio-item">
                <a href="#">
                    <img class="crop img-responsive" src="/assets/images%20for%20canteen/chips1.jpg" alt="">
                </a>
                <h3>
                    <a>Chips</a>
                </h3>
                <p>Fried Chips.Availabile in either large or small.</p>


                <h3 id="priceChips"></h3>
                <div class="dropup">
                    <button id="chooseChips" class="btn btn-primary dropdown-toggle" type="button"
                            data-toggle="dropdown">
                        Choose Size
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a href='#' onclick="click2(this.id);" id="${largeChips}">${name}</a>
                            <input type="hidden" id="${largePriceid}" value="${largePrice}">
                        </li>
                        <li><a id="${smallChips}" onclick="click2(this.id);" href="#">${name2}</a>
                            <input type="hidden" id="${smallPriceid}" value="${smallPrice}">
                        </li>

                    </ul>
                    <button onclick="onBuy(this.name);" class="btn btn-info pull-right " id="buyChips"
                            style="background-color: #4580e0;border-color: #4580e0">Buy
                    </button>

                </div>
            </div>
        </c:if>
        <c:if test="${not empty foodlist}">

            <c:forEach items="${foodlist}" var="foodList">

                <div class="crop1 col-md-4 portfolio-item">
                    <a href="#">
                        <img class="crop img-responsive" src="/${foodList.picName}" alt="">
                    </a>
                    <h3>
                        <a id="name${foodList.id}">${foodList.name}</a>
                    </h3>
                    <p>${foodList.desc}<br/><br/><br/></p>

                    <button onclick="onBuy(this.id);" class="btn btn-info pull-right" id="${foodList.id}"
                            style="background-color: #4580e0;border-color: #4580e0">Buy
                    </button>
                    <h3 id="price${foodList.id}">R${foodList.price}</h3>


                </div>
            </c:forEach>
        </c:if>

        <!-- /.row -->

        <hr>
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"></button>

                        <h4 class="modal-title">Cart</h4>
                    </div>
                    <div class="modal-body">


                        <h3 id="foodName"></h3>

                        <div class="modal-footer">
                            <button type="button" onclick="checkout()" class="btn btn-default" data-dismiss="modal">
                                Checkout
                            </button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Continue</button>

                        </div>
                    </div>

                </div>
            </div>


        </div>


        <div id="viewBalance" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"></button>

                        <h4 class="modal-title">View Balance</h4>
                    </div>
                    <div class="modal-body">


                        <h3>Balance:R${balance} </h3>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Continue</button>

                        </div>
                    </div>

                </div>
            </div>


        </div>

    </div>
</div>

<script type="text/javascript">

    function click1(id) {
        console.log(id.substr(4));

        $("#choose").text($("#" + id).text());
        $("#price").text("R" + document.getElementById("price" + id.substr(4)).value);
        document.getElementById("buySandwich").disabled = false;
        console.log(document.getElementById("buySandwich").id);
        console.log(document.getElementById(id).id);
        document.getElementById("buySandwich").setAttribute("name", id.substr(4));

        console.log(document.getElementById("buySandwich").name);

    }
</script>
<script>
    function checkout() {
        window.location = "/checkout"
    }
</script>
<!-- /.container -->

<!-- jQuery -->
<script src="/assets/js/flipclock.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/assets/js/bootstrap.js"></script>

</body>

</html>
