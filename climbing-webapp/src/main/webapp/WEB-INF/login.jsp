<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="_include/html_head.jsp" %>
    <title>${application.name} - Connexion</title>
</head>
<body>

<%@ include file="_include/header.jsp" %>

<div class="container">
    <h1>Connexion</h1>

    <form:form method="post" action="login" class="form-horizontal" modelAttribute="getUser">
        <div class="form-group">
            <form:label class="control-label col-sm-2" path="pseudo">Email address or username :</form:label>
            <div class="col-sm-10">
                <form:input type="text" class="form-control" path="pseudo" placeholder="Enter email" value="lotr_gandalf@gmail.com" />
            </div>
        </div>

        <div class="form-group">
            <form:label class="control-label col-sm-2" path="password">Password :</form:label>
            <div class="col-sm-10">
                <form:input type="password" class="form-control" path="password" placeholder="Enter password" value="lotr_gandalf" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label><input type="checkbox" name="remember" checked="checked"/> Remember me</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-user"></span> Connexion</button>
            </div>
        </div>
    </form:form>

    <p>Not a member yet? <a href="inscription">Register for free</a> in 30 seconds.</p>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
