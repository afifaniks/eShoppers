<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fun" uri="https://afifaniks.me/functions" %>
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
        <div class="row">
            <div class="col-6">
                <c:if test="${fun:isAuthenticated(pageContext.request)}">
                    <h2>Hello, <c:out value="${fun:getCurrentUser(pageContext.request).firstName}"/>!</h2>
                </c:if>
                <h1>The eShoppers welcomes you!</h1>
                <img src="<c:url value="/image/cart.png"/>" style="height: 200px" class="mt-2" alt="eShoppers"/>
            </div>
            <div class="col-6 mb-4">
                <c:if test="${cart != null && cart.cartItems.size() >0}">
                    <div class="card shadow-sm p-3 mb-5, bg-white">
                        <div class="card-header">
                            <h4>
                                ${fun:getCurrentUser(pageContext.request).firstName}'s Cart
                            </h4>
                        </div>
                        <div class="card-body">
                            <p>Total Item:
                                <span class="badge badge-pill badge-success">
                                    <c:out value="${cart.totalItem}"/>
                                </span>
                            </p>
                            <p>Total Price:
                                <span class="badge badge-pill badge-success">
                                    <c:out value="${cart.totalPrice}"/>
                                </span>
                            </p>
                            <p>
                                <a href="#" class="btn btn-outline-info">Checkout</a>
                            </p>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
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
                        <a class="card-link btn btn-outline-info"
                        onclick="addToCart(${product.id})">
                            Add to Cart
                        </a>
                        <form style="visibility: hidden"
                              method="post"
                              id="addToCart_${product.id}"
                              action="<c:url value="/add-to-cart?productId=${product.id}"/>">
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    function addToCart(productId) {
        let form = document.getElementById("addToCart_" + productId);
        form.submit();
    }
</script>
<%@include file="includes/footer.jsp"%>
