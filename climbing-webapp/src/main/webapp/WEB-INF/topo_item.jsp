<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="_include/html_head.jsp" %>
    <title>${application.name} - Topo item</title>
</head>
<body>

<%@ include file="_include/header.jsp" %>

<div class="container">
    <h1>${ topo.name }</h1>

    <c:if test="${ !empty sessionScope.user }">
        <p>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                <span class="glyphicon glyphicon-pencil"></span> Modifier le topo
            </button>
            <c:if test="${ sessionScope.user.role == 'admin' }">
                <form method="post" action="/topo/${ topo.publicationId }/delete">
                    <button type="submit" class="btn btn-danger btn-xs">
                        <span class="glyphicon glyphicon-remove"></span> Supprimer le topo
                    </button>
                </form>
            </c:if>
        </p>
        <form method="post" action="/topo/${ topo.publicationId }" class="form-horizontal collapse collapse-menu">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Nom :</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name" id="name" placeholder="Enter a topo name" value="${ topo.name }" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="description">Description :</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="description" id="description" placeholder="Enter a topo description">${ topo.description }</textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-ok"></span> Modifier
                    </button>
                </div>
            </div>
        </form>
    </c:if>

    <p>${ topo.description }</p>

    <%@include file="_include/comments.jsp"%>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
