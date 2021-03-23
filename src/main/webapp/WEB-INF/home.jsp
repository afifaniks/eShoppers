<%@ page import="java.util.List" %>
<%@ page import="me.afifaniks.shoppingcart.dto.ProductDTO" %><%--
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
</head>
<body>
<%
    List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
%>

<table>
    <thead>
        <tr>
            <th>Name Y</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
    </thead>
    <tbody>
        <% for (ProductDTO product: products) {%>
            <tr>
                <td>
                    <%= product.getName()%>
                </td>
                <td>
                    <%= product.getDescription()%>
                </td>
                <td>
                    <%= product.getPrice()%>
                </td>
            </tr>
        <%}%>
    </tbody>
</table>
</body>
</html>
