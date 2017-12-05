<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="../_include/html_head.jsp" %>
    <title>${application.name} - Connexion</title>
</head>
<body>

<%@ include file="../_include/header.jsp" %>

<div class="container">
    <h1>Connexion</h1>

    <form method="post" action="login" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email address :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="email" id="email" placeholder="Enter email" value="lotr_gandalf@gmail.com" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password :</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="password" id="password" placeholder="Enter password" value="lotr_gandalf" />
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
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-log-in"></span> Connexion</button>
            </div>
        </div>
    </form>

    <p>Not a member yet? <a href="inscription">Register for free</a> in 30 seconds.</p>
</div>

<%@ include file="../_include/footer.jsp" %>

</body>
</html>
