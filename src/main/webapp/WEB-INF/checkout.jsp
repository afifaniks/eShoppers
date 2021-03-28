<%--
  Created by IntelliJ IDEA.
  Author    :     Afif Al Mamun
  Web       :     https://afifaniks.me
  Date      :     3/28/2021
  Time      :     1:11 PM
--%>

<%@include file="includes/header.jsp"%>
<%@include file="includes/navigation.jsp"%>

<div class="container">
    <h3>
        Your Cart
    </h3>
    <div class="row">
        <table class="table table-hover">
            <thead>
            <tr>
                <th class="w-50">Name - Description</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cartItem" items="${cart.cartItems}">
                <tr>
                    <td>
                        <c:out value="${cartItem.product.name}"/>
                        -
                        <c:out value="${cartItem.product.description}"/>
                    </td>
                    <td>
                        <div class="btn-group" role="group">
                            <a class="btn btn-outline-warning" onclick="doAction(${cartItem.product.getId()}, 'remove')">-</a>
                            <button type="button" class="btn">
                                <c:out value="${cartItem.qty}" />
                            </button>
                            <a class="btn btn-outline-success" onclick="doAction(${cartItem.product.getId()}, 'add')">+</a>
                        </div>
                    </td>
                    <td>
                        $ <c:out value="${cartItem.price}" />
                    </td>
                    <td>
                        <a onclick="doAction(${cartItem.product.getId()}, 'remove')" class="btn btn-outline-warning">
                            Remove
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td>
                    <h4>Subtotal
                    (<c:out value="${cart.totalItem}" /> items)</h4>
                </td>
                <td></td>
                <td>
                    <a href="<c:url value="/"/>" class="btn btn-outline-success text-left">
                        Continue Shopping
                    </a>
                </td>
                <td>
                    <a href="<c:url value="#"/>" class="btn btn-success text-right">
                        Proceed To Checkout
                    </a>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/checkoutActions.js"></script>
</div>
<%@include file="includes/footer.jsp"%>
