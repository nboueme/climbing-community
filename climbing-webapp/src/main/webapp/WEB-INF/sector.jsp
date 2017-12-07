<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="_include/html_head.jsp" %>
    <title>${application.name} - Secteur</title>
</head>
<body>

<%@ include file="_include/header.jsp" %>

<div class="container">
    <h1>Liste des secteurs</h1>

    <!-- Sector CREATE -->
    <c:if test="${ !empty sessionScope.user }">
        <p>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                <span class="glyphicon glyphicon-plus"></span> Ajouter un secteur
            </button>
        </p>
        <form method="post" action="${ spotId }" class="form-horizontal collapse collapse-menu">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Nom :</label>
                <div class="col-sm-10">
                    <input required type="text" class="form-control" name="name" id="name" placeholder="Enter a sector name" value="Les mines de la Moria" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="height">Hauteur :</label>
                <div class="col-sm-10">
                    <input required type="number" class="form-control" name="height" id="height" placeholder="Enter a sector height" min="0" max="60"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
                </div>
            </div>
        </form>
    </c:if>

    <!-- Sector READ -->
    <table class="table table-bordered table-striped table-condensed">
        <thead>
        <tr>
            <th>Secteur</th>
            <th>Hauteur</th>
            <c:if test="${ !empty sessionScope.user }">
                <th>Options</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="sector" items="${ sectorList }">
            <tr>
                <td><a href="${pageContext.request.contextPath}/climbing/${ sector.spotId }/sector/${ sector.publicationId }"><c:out value="${ sector.name }" /></a></td>
                <td><c:out value="${ sector.height == 0 ? 'hauteur inconnue' : sector.height }" /></td>

                <c:if test="${ !empty sessionScope.user }">
                    <td class="text-center">
                        <c:if test="${ sessionScope.user.role == 'admin' }">
                            <form hidden method="post" action="${ sector.spotId }/sector/${ sector.publicationId }/delete" class="publication-delete${ sector.publicationId }"></form>
                        </c:if>

                        <div class="btn-group btn-group-xs">
                            <a title="Modify" class="btn btn-primary" data-toggle="modal" data-target=".modal-menu${ sector.publicationId }" data-backdrop="static" data-keyboard="false">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </a>

                            <!-- Sector DELETE -->
                            <c:if test="${ sessionScope.user.role == 'admin' }">
                                <a title="Delete" class="btn btn-danger" onclick="$('.publication-delete${ sector.publicationId }').submit();">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </a>
                            </c:if>
                        </div>
                    </td>
                </c:if>
            </tr>

            <!-- Sector UPDATE -->
            <c:if test="${ !empty sessionScope.user }">
                <div class="modal fade modal-menu${ sector.publicationId }">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Mise à jour</h4>
                            </div>

                            <div class="modal-body">
                                <form method="post" action="${ sector.spotId }/sector/${ sector.publicationId }/update" class="form-horizontal publication-update">
                                    <div class="form-group">
                                        <label for="name_update">Nom :</label>
                                        <input type="text" class="form-control" name="name" id="name_update" placeholder="Enter a topo name" value="${ sector.name }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="height_update">Hauteur :</label>
                                        <input required type="number" class="form-control" name="height" id="height_update" placeholder="Enter a spot height" min="0" max="60" value="${ sector.height }"/>
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" onclick="$('.publication-update').submit();">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
        </tbody>
    </table>

    <%@include file="_include/comments.jsp"%>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
