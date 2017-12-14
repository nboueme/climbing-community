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

        <div>
            <img class="user-img option-cursor" src="${ sessionScope.user.imageUrl }" data-toggle="collapse" data-target=".collapse-delete-picture">
            <c:if test="${ sessionScope.user.imageUrl != '/image/user/user-0.png' }">
                <a class="option-cursor collapse collapse-delete-picture" onclick="$('.delete-picture').submit();">
                    <span class="glyphicon glyphicon-minus"></span> Supprimer
                </a>

                <form hidden method="post" action="/account/${ sessionScope.user.id }/picture-delete" class="form-horizontal delete-picture">
                    <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                </form>
            </c:if>
        </div>

        <form method="post" action="/account/${ sessionScope.user.id }" enctype="multipart/form-data" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-2" for="pseudo">Pseudo :</label>
                <div class="col-sm-10">
                    <input required type="text" class="form-control" name="pseudo" id="pseudo" placeholder="New pseudo" value="${ sessionScope.user.pseudo }"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email :</label>
                <div class="col-sm-10">
                    <input required type="email" class="form-control" name="email" id="email" placeholder="New email" value="${ sessionScope.user.email }"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="last_password">Ancien mot de passe :</label>
                <div class="col-sm-10">
                    <input required type="password" class="form-control" name="last_password" id="last_password" placeholder="Last password"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="new_password">Nouveau mot de passe :</label>
                <div class="col-sm-10">
                    <input required type="password" class="form-control" name="new_password" id="new_password" placeholder="New password"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="picture">Photo de profil :</label>
                <div class="col-sm-10">
                    <input type="file" name="picture" id="picture"/>
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
