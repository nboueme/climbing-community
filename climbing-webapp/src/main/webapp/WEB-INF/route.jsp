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
        <form method="post" action="${ publicationId }" class="form-horizontal collapse collapse-menu">
            <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />

            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Nom :</label>
                <div class="col-sm-10">
                    <input required type="text" class="form-control" name="name" id="name" placeholder="Enter a route name" value="Les mines de la Moria" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="height">Hauteur :</label>
                <div class="col-sm-10">
                    <input required type="number" class="form-control" name="height" id="height" placeholder="Enter a route height" min="0" max="60"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="quotation">Cotation :</label>
                <div class="col-sm-10">
                    <select name="quotation" id="quotation">
                        <c:forEach var="quotation_range" begin="3" end="9">
                            <option value="${ quotation_range }a">${ quotation_range }a</option>
                            <option value="${ quotation_range }b">${ quotation_range }b</option>
                            <option value="${ quotation_range }c">${ quotation_range }c</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="latitude">Latitude :</label>
                <div class="col-sm-10">
                    <input required type="text" class="form-control" name="latitude" id="latitude" placeholder="Enter a route latitude" value="44.500052" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="longitude">Longitude :</label>
                <div class="col-sm-10">
                    <input required type="text" class="form-control" name="longitude" id="longitude" placeholder="Enter a route longitude" value="5.933146" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="points_number">Nombre de points :</label>
                <div class="col-sm-10">
                    <input required type="number" class="form-control" name="points_number" id="points_number" placeholder="Enter a route points number" min="0" max="60"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
                </div>
            </div>
        </form>
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
                            <form hidden method="post" action="/climbing/route/${ route.publicationId }/delete" class="publication-delete${ route.publicationId }">
                                <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                            </form>
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
                                <form method="post" action="/climbing/route/${ route.publicationId }/update" class="form-horizontal publication-update">
                                    <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />

                                    <div class="form-group">
                                        <label for="name_update">Nom :</label>
                                        <input required type="text" class="form-control" name="name" id="name_update" placeholder="Enter a route name" value="${ route.name }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="height_update">Hauteur :</label>
                                        <input required type="number" class="form-control" name="height" id="height_update" placeholder="Enter a route height" min="0" max="60" value="${ route.height }"/>
                                    </div>

                                    <div class="form-group">
                                        <label for="quotation_update">Cotation :</label>
                                        <select name="quotation" id="quotation_update">
                                            <option value="${ route.quotation }">--</option>
                                            <c:forEach var="quotation_range" begin="3" end="9">
                                                <option value="${ quotation_range }a">${ quotation_range }a</option>
                                                <option value="${ quotation_range }b">${ quotation_range }b</option>
                                                <option value="${ quotation_range }c">${ quotation_range }c</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="latitude_update">Latitude :</label>
                                        <input required type="text" class="form-control" name="latitude" id="latitude_update" placeholder="Enter a route latitude" value="${ route.latitude }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="longitude_update">Longitude :</label>
                                        <input required type="text" class="form-control" name="longitude" id="longitude_update" placeholder="Enter a route longitude" value="${ route.longitude }" />
                                    </div>

                                    <div class="form-group">
                                        <label for="points_number_update">Nombre de points :</label>
                                        <input required type="number" class="form-control" name="points_number" id="points_number_update" placeholder="Enter a route points number" min="0" max="60" value="${ route.pointsNumber }"/>
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
