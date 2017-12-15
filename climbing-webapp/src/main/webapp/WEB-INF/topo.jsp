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

    <!-- Topo CREATE -->
    <c:if test="${ !empty sessionScope.user }">
        <p>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                <span class="glyphicon glyphicon-plus"></span> Ajouter un topo
            </button>
        </p>
        <form:form method="post" action="/topo" class="form-horizontal collapse collapse-menu" modelAttribute="topo">
            <div class="form-group">
                <form:label class="control-label col-sm-2" path="name">Nom :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="name" placeholder="Enter a topo name"/>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="description">Description :</form:label>
                <div class="col-sm-10">
                    <form:textarea class="form-control" path="description" placeholder="Enter a topo description"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
                </div>
            </div>
        </form:form>
    </c:if>

    <!-- Topo READ -->
    <table class="table table-bordered table-striped table-condensed">
        <thead>
        <tr>
            <th>Topo</th>
            <th>Description</th>
            <th>Spots</th>
            <c:if test="${ !empty sessionScope.user }">
                <th>Options</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="topo" items="${ topoList }">
            <tr>
                <td><a href="${pageContext.request.contextPath}/topo/${ topo.publicationId }"><c:out value="${ topo.name }" /></a></td>
                <td><c:out value="${ topo.description }"/></td>
                <td><c:out value="${ topo.spotsNumber }"/></td>

                <c:if test="${ !empty sessionScope.user }">
                    <td class="text-center">
                        <c:if test="${ sessionScope.user.role == 'admin' }">
                            <form:form method="post" action="/topo/${topo.publicationId}/delete" class="publication-delete${ topo.publicationId }" modelAttribute="topo">
                                <input hidden name="picture" title="picture" value="${ topo.imageUrl }" />
                            </form:form>
                        </c:if>

                        <div class="btn-group btn-group-xs">
                            <a title="Modify" class="btn btn-primary" data-toggle="modal" data-target=".modal-menu${ topo.publicationId }" data-backdrop="static" data-keyboard="false">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </a>

                            <!-- Topo DELETE -->
                            <c:if test="${ sessionScope.user.role == 'admin' }">
                                <a title="Delete" class="btn btn-danger" onclick="$('.publication-delete${ topo.publicationId }').submit();">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </a>
                            </c:if>
                        </div>
                    </td>
                </c:if>
            </tr>

            <!-- Topo UPDATE -->
            <c:if test="${ !empty sessionScope.user }">
                <div class="modal fade modal-menu${ topo.publicationId }">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Mise à jour</h4>
                            </div>

                            <div class="modal-body">
                                <form:form method="post" action="/topo/${topo.publicationId}/update" enctype="multipart/form-data" class="form-horizontal publication-update" modelAttribute="topo">
                                    <div class="form-group">
                                        <label for="name_update">Nom :</label>
                                        <form:input type="text" class="form-control" path="name" id="name_update" placeholder="Enter a topo name" value="${ topo.name }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="description_update">Description :</label>
                                        <textarea class="form-control" name="description" id="description_update" placeholder="Enter a topo description">${ topo.description }</textarea>
                                    </div>

                                    <div class="form-group">
                                        <label for="picture_update">Photo du topo :</label>
                                        <input type="file" name="file" id="picture_update"/>
                                    </div>
                                    <input hidden name="currentPicture" title="picture" value="${ topo.imageUrl }" />
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
