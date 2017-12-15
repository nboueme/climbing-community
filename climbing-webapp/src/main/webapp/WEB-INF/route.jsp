<%--suppress XmlDuplicatedId --%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="_include/html_head.jsp" %>
    <title>${application.name} - Voie</title>
</head>
<body>

<%@ include file="_include/header.jsp" %>

<div class="container">
    <h1>Liste des voies</h1>

    <!-- Route CREATE -->
    <c:if test="${ !empty sessionScope.user }">
        <p>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                <span class="glyphicon glyphicon-plus"></span> Ajouter une voie
            </button>
        </p>
        <form:form method="post" action="/climbing/sector/${publicationId}" class="form-horizontal collapse collapse-menu" modelAttribute="route">
            <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="name">Nom :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="name" placeholder="Enter a route name"/>
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="height">Hauteur :</form:label>
                <div class="col-sm-10">
                    <form:input type="number" class="form-control" path="height" placeholder="Enter a route height" min="0" max="60"/>
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
                    <form:input type="text" class="form-control" path="latitude" placeholder="Enter a route latitude" value="44.500052" />
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="longitude">Longitude :</form:label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" path="longitude" placeholder="Enter a route longitude" value="5.933146" />
                </div>
            </div>

            <div class="form-group">
                <form:label class="control-label col-sm-2" path="pointsNumber">Nombre de points :</form:label>
                <div class="col-sm-10">
                    <form:input type="number" class="form-control" path="pointsNumber" placeholder="Enter a route points number" min="0" max="60"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
                </div>
            </div>
        </form:form>
    </c:if>

    <!-- Route READ -->
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
        <c:forEach var="route" items="${ routeList }">
            <tr>
                <td><a href="${pageContext.request.contextPath}/climbing/route/${ route.publicationId }"><c:out value="${ route.name }" /></a></td>
                <td><c:out value="${ route.height }" /></td>
                <td><c:out value="${ route.pointsNumber }" /></td>
                <td><c:out value="${ route.quotation }" /></td>

                <c:if test="${ !empty sessionScope.user }">
                    <td class="text-center">
                        <c:if test="${ sessionScope.user.role == 'admin' }">
                            <form:form method="post" action="/climbing/${route.sectorId}/route/${ route.publicationId }/delete" class="publication-delete${ route.publicationId }" modelAttribute="route"/>
                        </c:if>

                        <div class="btn-group btn-group-xs">
                            <a title="Modify" class="btn btn-primary" data-toggle="modal" data-target=".modal-menu${ route.publicationId }" data-backdrop="static" data-keyboard="false">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </a>

                            <!-- Route DELETE -->
                            <c:if test="${ sessionScope.user.role == 'admin' }">
                                <a title="Delete" class="btn btn-danger" onclick="$('.publication-delete${ route.publicationId }').submit();">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </a>
                            </c:if>
                        </div>
                    </td>
                </c:if>
            </tr>

            <!-- Route UPDATE -->
            <c:if test="${ !empty sessionScope.user }">
                <div class="modal fade modal-menu${ route.publicationId }">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Mise à jour</h4>
                            </div>

                            <div class="modal-body">
                                <form:form method="post" action="/climbing/${route.sectorId}/route/${route.publicationId}/update" class="form-horizontal publication-update" modelAttribute="route">
                                    <div class="form-group">
                                        <label for="name_update">Nom :</label>
                                        <form:input type="text" class="form-control" path="name" id="name_update" placeholder="Enter a route name" value="${ route.name }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="height_update">Hauteur :</label>
                                        <form:input type="number" class="form-control" path="height" id="height_update" placeholder="Enter a route height" min="0" max="60" value="${ route.height }"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="quotation_update">Cotation :</label>
                                        <form:select path="quotation" id="quotation_update">
                                            <form:option value="${ route.quotation }">--</form:option>
                                            <c:forEach var="quotation_range" begin="3" end="9">
                                                <form:option value="${ quotation_range }a">${ quotation_range }a</form:option>
                                                <form:option value="${ quotation_range }b">${ quotation_range }b</form:option>
                                                <form:option value="${ quotation_range }c">${ quotation_range }c</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div>

                                    <div class="form-group">
                                        <label for="latitude_update">Latitude :</label>
                                        <form:input type="text" class="form-control" path="latitude" id="latitude_update" placeholder="Enter a route latitude" value="${ route.latitude }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="longitude_update">Longitude :</label>
                                        <form:input type="text" class="form-control" path="longitude" id="longitude_update" placeholder="Enter a route longitude" value="${ route.longitude }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="points_number_update">Nombre de points :</label>
                                        <form:input type="number" class="form-control" path="pointsNumber" id="points_number_update" placeholder="Enter a route points number" min="0" max="60" value="${ route.pointsNumber }"/>
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

    <%@include file="_include/comments.jsp"%>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
