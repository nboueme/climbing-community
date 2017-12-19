<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="../_include/html_head.jsp" %>
    <title>${application.name} - Inscription</title>
</head>
<body>

<%@ include file="../_include/header.jsp" %>

<div class="container">
    <h1>Inscription</h1>

    <form method="post" action="inscription" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="pseudo">Pseudo :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="pseudo" id="pseudo" placeholder="Enter pseudo"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email :</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" name="email" id="email" placeholder="Enter email"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Mot de passe :</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="password" id="password" placeholder="Enter password"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Inscription</button>
            </div>
        </div>
    </form>
</div>

<%@ include file="../_include/footer.jsp" %>

</body>
</html>
