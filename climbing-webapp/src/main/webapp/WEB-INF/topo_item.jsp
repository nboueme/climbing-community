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

    <c:if test="${ !empty sessionScope.user && sessionScope.user.role == 'admin' }">
        <form method="post" action="/topo/${ topo.publicationId }/delete">
            <button type="submit" class="btn btn-danger btn-xs">
                <span class="glyphicon glyphicon-remove"></span> Supprimer le topo
            </button>
        </form>
    </c:if>

    <p>${ topo.description }</p>

    <%@include file="_include/comments.jsp"%>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
