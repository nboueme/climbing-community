<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="_include/html_head.jsp" %>
    <title>${application.name} - Inscription</title>
</head>
<body>

<%@ include file="_include/header.jsp" %>

<div class="container">
    <h1>Inscription</h1>

    <form:form method="post" action="inscription" class="form-horizontal" modelAttribute="addUser">
        <div class="form-group">
            <form:label class="control-label col-sm-2" path="pseudo">Pseudo :</form:label>
            <div class="col-sm-10">
                <form:input type="text" class="form-control" path="pseudo" placeholder="Enter pseudo" value="Gandalfou" />
            </div>
        </div>

        <div class="form-group">
            <form:label class="control-label col-sm-2" path="email">Email :</form:label>
            <div class="col-sm-10">
                <form:input type="email" class="form-control" path="email" placeholder="Enter email" value="lotr_gandalfou@gmail.com" />
            </div>
        </div>

        <div class="form-group">
            <form:label class="control-label col-sm-2" path="password">Mot de passe :</form:label>
            <div class="col-sm-10">
                <form:input type="password" class="form-control" path="password" placeholder="Enter password" value="lotr_gandalfou" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Inscription</button>
            </div>
        </div>
    </form:form>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
