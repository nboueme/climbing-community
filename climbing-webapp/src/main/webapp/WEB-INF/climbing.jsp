<%--suppress XmlDuplicatedId --%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="_include/html_head.jsp" %>
    <title>${application.name} - Sites</title>
</head>
<body>

<%@ include file="_include/header.jsp" %>

<div class="container">
    <h1>Liste des sites</h1>

    <!-- Spot CREATE -->
    <c:if test="${ !empty sessionScope.user }">
        <p>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                <span class="glyphicon glyphicon-plus"></span> Ajouter un spot
            </button>
        </p>
        <form:form method="post" action="/climbing" class="form-horizontal collapse collapse-menu" modelAttribute="spot">
            <div class="form-group">
                <form:label class="control-label col-sm-2" path="name">Nom :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="name" placeholder="Enter a spot name"/>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="description">Description :</form:label>
                <div class="col-sm-10">
                    <form:textarea class="form-control" path="description" placeholder="Enter a spot description"/>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="height">Hauteur :</form:label>
                <div class="col-sm-10">
                    <form:input type="number" class="form-control" path="height" placeholder="Enter a spot height" min="0" max="60"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
                </div>
            </div>
        </form:form>
    </c:if>

    <!-- Spot READ -->
    <table class="table table-bordered table-striped table-condensed">
        <thead>
        <tr>
            <th>Site</th>
            <th>Description</th>
            <th>Hauteur</th>
            <c:if test="${ !empty sessionScope.user }">
                <th>Options</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="spot" items="${ spotList }" varStatus="spotStatus">
            <tr>
                <td><a href="${pageContext.request.contextPath}/climbing/${ spot.publicationId }"><c:out value="${ spot.name }" /></a></td>
                <td>${ spot.description }</td>
                <td>${ spot.height }</td>

                <c:if test="${ !empty sessionScope.user }">
                    <td class="text-center">
                        <c:if test="${ sessionScope.user.role == 'admin' }">
                            <form:form method="post" action="/climbing/${ spot.publicationId }/delete" class="publication-delete${ spot.publicationId }" modelAttribute="spot"/>
                        </c:if>

                        <div class="btn-group btn-group-xs">
                            <a title="Modify" class="btn btn-primary" data-toggle="modal" data-target=".modal-menu${ spot.publicationId }" data-backdrop="static" data-keyboard="false">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </a>

                            <!-- Spot DELETE -->
                            <c:if test="${ sessionScope.user.role == 'admin' }">
                                <a title="Delete" class="btn btn-danger" onclick="$('.publication-delete${ spot.publicationId }').submit();">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </a>
                            </c:if>
                        </div>
                    </td>
                </c:if>
            </tr>

            <!-- Spot UPDATE -->
            <c:if test="${ !empty sessionScope.user }">
                <div class="modal fade modal-menu${ spot.publicationId }">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Mise à jour</h4>
                            </div>

                            <div class="modal-body">
                                <form:form method="post" action="/climbing/${ spot.publicationId }/update" class="form-horizontal publication-update" modelAttribute="spot">
                                    <div class="form-group">
                                        <label for="name_update">Nom :</label>
                                        <form:input type="text" class="form-control" path="name" id="name_update" placeholder="Enter a spot name" value="${ spot.name }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="description_update">Description :</label>
                                        <textarea class="form-control" name="description" id="description_update" placeholder="Enter a topo description">${ spot.description }</textarea>
                                    </div>

                                    <div class="form-group">
                                        <label for="height_update">Hauteur :</label>
                                        <form:input type="number" class="form-control" path="height" id="height_update" placeholder="Enter a spot height" min="0" max="60" value="${ spot.height }"/>
                                    </div>
                                </form:form>
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
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
