<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="../_include/html_head.jsp" %>
    <title>${application.name} - Mon compte</title>
</head>
<body>

<%@ include file="../_include/header.jsp" %>

<div class="container">
    <h1>Mon compte</h1>

    <c:if test="${ !empty sessionScope.user }">
        <p>Utilisateur créée le ${ sessionScope.user.createdAt }</p>
        <c:if test="${ !empty sessionScope.user.updatedAt }">
            <p>Utilisateur mis-à-jour le ${ sessionScope.user.updatedAt }</p>
        </c:if>

        <form method="post" action="/account/${ sessionScope.user.id }" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-2" for="pseudo">Pseudo :</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="pseudo" id="pseudo" placeholder="New pseudo" value="${ sessionScope.user.pseudo }"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email :</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" name="email" id="email" placeholder="New email" value="${ sessionScope.user.email }"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="lastPassword">Ancien mot de passe :</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" name="lastPassword" id="lastPassword" placeholder="Last password"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="newPassword">Nouveau mot de passe :</label>
                <div class="col-sm-10">
                    <input type="password" required class="form-control" name="newPassword" id="newPassword" placeholder="New password"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Modifier</button>
                </div>
            </div>
        </form>

        <form method="post" action="/account/${ sessionScope.user.id }/delete">
            <button type="submit" class="btn btn-danger btn-xs">
                <span class="glyphicon glyphicon-remove"></span> Supprimer le compte
            </button>
        </form>
    </c:if>
</div>

<%@ include file="../_include/footer.jsp" %>

</body>
</html>
