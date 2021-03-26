<%@taglib prefix="fun" uri="https://afifaniks.me/functions" %>
<%--
  Created by IntelliJ IDEA.
  Author    :     Afif Al Mamun
  Web       :     https://afifaniks.me
  Date      :     3/23/2021
  Time      :     11:58 PM
--%>
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
            <c:choose>
                <c:when test="${fun:isAuthenticated(pageContext.request)}">
                    <a class="nav-link" href="#" onclick="logOut()">
                        Logout (${fun:getCurrentUser(pageContext.request).firstName})
                    </a>
                    <script>
                        function logOut() {
                            document.getElementById("logoutForm").submit();
                        }
                    </script>
                    <form style="visibility: hidden" id="logoutForm" method="POST" action="<c:url value="/logout" />">
                    </form>
                </c:when>
                <c:otherwise>
                    <a class="nav-link" href="<c:url value="/login"/>">
                        Login
                    </a>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
