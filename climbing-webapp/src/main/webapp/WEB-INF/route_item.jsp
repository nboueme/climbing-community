<%--suppress XmlDuplicatedId --%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="_include/html_head.jsp" %>
    <title>${application.name} - Voie item</title>
</head>
<body>

<%@ include file="_include/header.jsp" %>

<div class="container">
    <h1>${ route.name }</h1>

    <!-- Length CREATE -->
    <c:if test="${ !empty sessionScope.user && route.typeRoute == 'route' }">
        <p>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                <span class="glyphicon glyphicon-plus"></span> Ajouter une voie
            </button>
        </p>
        <form:form method="post" action="/climbing/route/${route.publicationId}/${route.sectorId}" class="form-horizontal collapse collapse-menu" modelAttribute="length">
            <div class="form-group">
                <form:label class="control-label col-sm-2" path="name">Nom :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="name" placeholder="Enter a length name"/>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="height">Hauteur :</form:label>
                <div class="col-sm-10">
                    <form:input type="number" class="form-control" path="height" placeholder="Enter a length height" min="0" max="60"/>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="quotation">Cotation :</form:label>
                <div class="col-sm-10">
                    <form:select path="quotation">
                        <c:forEach var="quotation_range" begin="3" end="9">
                            <form:option value="${ quotation_range }a">${ quotation_range }a</form:option>
                            <form:option value="${ quotation_range }b">${ quotation_range }b</form:option>
                            <form:option value="${ quotation_range }c">${ quotation_range }c</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="latitude">Latitude :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="latitude" placeholder="Enter a length latitude" value="44.500052" />
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="longitude">Longitude :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="longitude" placeholder="Enter a length longitude" value="5.933146" />
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="pointsNumber">Nombre de points :</form:label>
                <div class="col-sm-10">
                    <form:input type="number" class="form-control" path="pointsNumber" placeholder="Enter a length points number" min="0" max="60"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
                </div>
            </div>
        </form:form>
    </c:if>

    <p>${ route.name } -> ${ route.height } mètres, ${ route.pointsNumber } points, coté ${ route.quotation }</p>

    <c:if test="${ listLength.size() > 0 }">
        <h4>Liste des longueurs</h4>

        <!-- Length READ -->
        <table class="table table-bordered table-striped table-condensed">
            <thead>
            <tr>
                <th>Voie</th>
                <th>Hauteur</th>
                <th>Points</th>
                <th>Cotation</th>
                <c:if test="${ !empty sessionScope.user }">
                    <th>Options</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="length" items="${ listLength }">
                <tr>
                    <td><c:out value="${ length.name }" /></td>
                    <td><c:out value="${ length.height }" /></td>
                    <td><c:out value="${ length.pointsNumber }" /></td>
                    <td><c:out value="${ length.quotation }" /></td>

                    <c:if test="${ !empty sessionScope.user }">
                        <td class="text-center">
                            <c:if test="${ sessionScope.user.role == 'admin' }">
                                <form:form method="post" action="/climbing/route/${route.publicationId}/${length.publicationId}/delete" class="publication-delete${ length.publicationId }" modelAttribute="length"/>
                            </c:if>

                            <div class="btn-group btn-group-xs">
                                <a title="Modify" class="btn btn-primary" data-toggle="modal" data-target=".modal-menu${ length.publicationId }" data-backdrop="static" data-keyboard="false">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </a>

                                <!-- Length DELETE -->
                                <c:if test="${ sessionScope.user.role == 'admin' }">
                                    <a title="Delete" class="btn btn-danger" onclick="$('.publication-delete${ length.publicationId }').submit();">
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </a>
                                </c:if>
                            </div>
                        </td>
                    </c:if>
                </tr>

                <!-- Length UPDATE -->
                <c:if test="${ !empty sessionScope.user }">
                    <div class="modal fade modal-menu${ length.publicationId }">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Mise à jour</h4>
                                </div>

                                <div class="modal-body">
                                    <form:form method="post" action="/climbing/route/${route.publicationId}/${length.publicationId}/update" class="form-horizontal publication-update" modelAttribute="length">
                                        <div class="form-group">
                                            <label for="name_update">Nom :</label>
                                            <form:input type="text" class="form-control" path="name" id="name_update" placeholder="Enter a route name" value="${ length.name }" />
                                        </div>

                                        <div class="form-group">
                                            <label for="height_update">Hauteur :</label>
                                            <form:input type="number" class="form-control" path="height" id="height_update" placeholder="Enter a route height" min="0" max="60" value="${ length.height }"/>
                                        </div>

                                        <div class="form-group">
                                            <label for="quotation_update">Cotation :</label>
                                            <form:select path="quotation" id="quotation_update">
                                                <form:option value="${ length.quotation }">--</form:option>
                                                <c:forEach var="quotation_range" begin="3" end="9">
                                                    <form:option value="${ quotation_range }a">${ quotation_range }a</form:option>
                                                    <form:option value="${ quotation_range }b">${ quotation_range }b</form:option>
                                                    <form:option value="${ quotation_range }c">${ quotation_range }c</form:option>
                                                </c:forEach>
                                            </form:select>
                                        </div>

                                        <div class="form-group">
                                            <label for="latitude_update">Latitude :</label>
                                            <form:input type="text" class="form-control" path="latitude" id="latitude_update" placeholder="Enter a route latitude" value="${ length.latitude }" />
                                        </div>

                                        <div class="form-group">
                                            <label for="longitude_update">Longitude :</label>
                                            <form:input type="text" class="form-control" path="longitude" id="longitude_update" placeholder="Enter a route longitude" value="${ length.longitude }" />
                                        </div>

                                        <div class="form-group">
                                            <label for="points_number_update">Nombre de points :</label>
                                            <form:input type="number" class="form-control" path="pointsNumber" id="points_number_update" placeholder="Enter a route points number" min="0" max="60" value="${ length.pointsNumber }"/>
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
    </c:if>

    <c:if test="${ route.typeRoute == 'route' }">
        <%@include file="_include/comments.jsp"%>
    </c:if>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
