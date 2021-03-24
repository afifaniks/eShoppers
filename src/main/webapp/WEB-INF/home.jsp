<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Author    :     Afif Al Mamun
  Web       :     https://afifaniks.me
  Date      :     3/23/2021
  Time      :     5:18 PM
--%>

<%@include file="includes/header.jsp"%>
<%@include file="includes/navigation.jsp"%>
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
<%@include file="includes/footer.jsp"%>
