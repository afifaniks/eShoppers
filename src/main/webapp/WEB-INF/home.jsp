<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="me.afifaniks.shoppingcart.dto.ProductDTO" %>
<%--
  Created by IntelliJ IDEA.
  Author    :     Afif Al Mamun
  Web       :     https://afifaniks.me
  Date      :     3/23/2021
  Time      :     5:18 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Products</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="<c:url value="/"/>">eShoppers</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/"/>">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">About</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="jumbotron m-1">
        <h1>The eShoppers welcomes you!</h1>
        <img src="<c:url value="/image/cart.png"/>" style="height: 200px" class="mt-2" alt="eShoppers"/>
    </div>
</div>
<div class="container">
    <div class="row mt-4">
        <c:forEach var="product" items="${products}">
            <div class="col-sm-6 col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">
                            <c:out value="${product.getName()}"/>
                        </h5>
                        <p class="card-text">
                            <c:out value="${product.getDescription()}"/>
                        </p>
                        <p class="card-text">
                            Price:
                            <c:out value="${product.getPrice()}"/>
                            $
                        </p>
                        <a class="card-link btn btn-outline-info">
                            Add to Cart
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<footer class="blockquote-footer mt-auto py-3 fixed-bottom">
    <div class="container">
        <span class="text-muted">
            Copyright &copy; Afif, 2020
        </span>
    </div>
</footer>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
