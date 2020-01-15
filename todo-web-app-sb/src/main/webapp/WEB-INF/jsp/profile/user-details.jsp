<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>
<div class="container">
    <div class="row">
        <br />
        <div class="col-md-6 col-md-offset-3 ">
            <div class="panel panel-primary">
                <br />
                <div class="panel-heading">Profile Details</div>
                <div class="panel-body">
                    Name: ${profile.firstName} ${profile.lastName}
                    <br />
                    Email: ${profile.email}
                    <br />
                    Password: <a href="<c:url value="/update-password"/>" class="btn btn-success">Change Password</a>
                </div>
            </div>
        </div>
    </div>
</div>