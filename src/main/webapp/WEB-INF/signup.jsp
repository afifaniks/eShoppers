<%--
  Created by IntelliJ IDEA.
  Author    :     Afif Al Mamun
  Web       :     https://afifaniks.me
  Date      :     3/23/2021
  Time      :     5:54 PM
--%>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navigation.jsp"%>

<div class="container">
    <br>
    <h2>Sign Up</h2>
    <hr class="mb-4">

    <form role="form" action="<c:url value="/signup"/>" method="POST">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="" required minlength="4" maxlength="32"/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com" required minlength="5" maxlength="70"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required minlength="6" maxlength="20"/>
        </div>
        <div class="form-group">
            <label for="passwordConfirmed">Confirm Password</label>
            <input type="password" class="form-control" id="passwordConfirmed" name="passwordConfirmed" required minlength="6" maxlength="20"/>
        </div>
        <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="" required minlength="4" maxlength="32"/>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" required minlength="4" maxlength="32"/>
        </div>

        <hr class="mb-4">
        <div class="form-group">
            <button class="btn btn-primary btn-lg btn" type="submit">Signup</button>
        </div>
    </form>
</div>

<%@include file="includes/footer.jsp"%>
