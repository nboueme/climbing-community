<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="_include/html_head.jsp" %>
    <title>${application.name} - Topo</title>
</head>
<body>

<%@ include file="_include/header.jsp" %>

<div class="container">
    <h1>Liste des topo</h1>

    <c:if test="${ !empty sessionScope.user }">
        <p>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                <span class="glyphicon glyphicon-plus"></span> Ajouter un topo
            </button>
        </p>
        <form method="post" action="topo" class="form-horizontal collapse collapse-menu">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Nom :</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name" id="name" placeholder="Enter a topo name" value="Les mines de la Moria" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="description">Description :</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="description" id="description" placeholder="Enter a topo description">Top cool !!</textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
                </div>
            </div>
        </form>
    </c:if>

    <table class="table table-bordered table-striped table-condensed">
        <thead>
        <tr>
            <th>Topo</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="topo" items="${ topoList }">
            <tr>
                <td><a href="${pageContext.request.contextPath}/topo/${ topo.publicationId }"><c:out value="${ topo.name }" /></a></td>
                <td>
                    <c:out value="${ topo.description }"/><br>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
