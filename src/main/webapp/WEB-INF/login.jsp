<%--
  Created by IntelliJ IDEA.
  Author    :     Afif Al Mamun
  Web       :     https://afifaniks.me
  Date      :     3/25/2021
  Time      :     3:45 PM
--%>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navigation.jsp"%>

<div class="container">
    <br>
    <div class="row">
        <c:if test="${message != null}">
            <div class="alert alert-success block">
                    ${message}
            </div>
        </c:if>
    </div>
    <h2>Login</h2>
    <hr class="mb-4">
    <form role="form" action="<c:url value="/login"/>" method="POST">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" value="${user.username}" name="username" placeholder="" required minlength="4" maxlength="32"/>
            <c:if test="${errors.username != null}">
                <small class="text-danger">${errors.username}</small>
            </c:if>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required minlength="6" maxlength="20"/>
            <c:if test="${errors.password != null}">
                <small class="text-danger">${errors.password}</small>
            </c:if>
        </div>
        <c:if test="${notFound != null}">
            <small class="text-danger">${notFound}</small>
        </c:if>
        <hr class="mb-4">
        <div class="form-group">
            <button class="btn btn-primary btn-lg btn" type="submit">Login</button>
        </div>
    </form>
    <span>
        Don't have an account? <a class="btn-link" href="<c:url value="/signup"/>">Sign Up here</a>
    </span>
</div>
<%@include file="includes/footer.jsp"%>
