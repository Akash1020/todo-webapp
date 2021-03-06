<%@ include file="common/header.jspf"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container">
    <br />
    <div class="card">
        <div class="card-header">
            <h2 class="form-signin-heading">Create your account</h2>
        </div>
        <div class="card-body">
            <form:form method="POST" modelAttribute="userForm" class="form-signin">
                <span class="text-warning">${error}</span>
                <br />
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="firstName" class="form-control" placeholder="First Name" required="required" />
                    <form:errors path="firstName" cssClass="text-warning"></form:errors>
                </div>

                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="lastName" class="form-control" placeholder="Last Name" required="required" />
                    <form:errors path="lastName" cssClass="text-warning"></form:errors>
                </div>

                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="email" class="form-control" placeholder="Email" required="required" autofocus="true" />
                    <form:errors path="email" cssClass="text-warning"></form:errors>
                </div>

                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Password" required="required"></form:input>
                    <form:errors path="password" cssClass="text-warning"></form:errors>
                </div>

                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="passwordConfirm" class="form-control" placeholder="Confirm your password"></form:input>
                    <form:errors path="passwordConfirm" cssClass="text-warning"></form:errors>
                </div>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                <h4 class="text-center"><a href="${contextPath}/login">Have account? Login</a></h4>
            </form:form>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf"%>