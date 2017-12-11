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
        <c:if test="${ sessionScope.user.role == 'admin' }">
            <form method="post" action="/topo/${ topo.publicationId }/delete">
                <button type="submit" class="btn btn-danger btn-xs">
                    <span class="glyphicon glyphicon-remove"></span> Supprimer le topo
                </button>
            </form>
        </c:if>

        <!-- TopoHasSpot CREATE -->
        <p>
            <button type="button" class="btn btn-primary btn-xs" data-toggle="collapse" data-target=".collapse-menu">
                <span class="glyphicon glyphicon-plus"></span> Lier un spot
            </button>
        </p>

        <form method="post" action="/topo-spot" class="form-horizontal collapse collapse-menu">
            <div class="form-group">
                <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                <input hidden name="topo_id" title="topo_id" value="${ topo.publicationId }" />

                <label for="spots">Nom :</label>
                <select name="spot" id="spots">
                    <c:forEach var="spot" items="${ notRelatedSpots }">
                        <option value="${ spot.publicationId }">${ spot.name }</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> Ajouter</button>
            </div>
        </form>

        <!-- UserHasTopo CREATE -->
        <c:if test="${ notRelatedUser == false }">
            <form hidden method="post" action="/user-topo/${ topo.publicationId }" class="user-topo-add">
                <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                <input hidden name="topo_id" title="topo_id" value="${ topo.publicationId }" />
            </form>
            <button type="button" class="btn btn-warning btn-xs" onclick="$('.user-topo-add').submit();">
                <span class="glyphicon glyphicon-ok"></span> Je possède ce topo
            </button>
        </c:if>
    </c:if>


    <h4>Description</h4>
    <p>${ topo.description }</p>

    <!-- TopoHasSpot READ -->
    <c:if test="${ topoHasSpots.size() > 0 }">
        <h4>${ topo.spotsNumber } spot${ topoHasSpots.size() > 1 ? 's' : '' }</h4>
        <ul>
            <c:forEach var="spot" items="${ topoHasSpots }">
                <li>
                    <a href="${pageContext.request.contextPath}/climbing/${ spot.topoHasSpot.publicationId }"><c:out value="${ spot.name }"/></a>

                    <!-- TopoHasSpot DELETE -->
                    <c:if test="${ sessionScope.user.role == 'admin' }">
                        <form hidden method="post" action="/topo-spot/${ spot.topoHasSpot.publicationId }/delete" class="spot-delete${ spot.topoHasSpot.publicationId }">
                            <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                            <input hidden name="topo_id" title="topo_id" value="${ topo.publicationId }" />
                        </form>
                        <span class="comment-point"> · </span>
                        <a title="Delete" class="option-cursor" onclick="$('.spot-delete${ spot.topoHasSpot.publicationId }').submit();">
                            <span class="glyphicon glyphicon-remove"></span>
                        </a>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <!-- UserHasTopo READ -->
    <c:if test="${ userHasTopos.size() > 0 }">
        <h4>Topo disponibles à l'emprunt</h4>
        <table class="table table-bordered table-striped table-condensed">
            <thead>
            <tr>
                <th>Grimpeur</th>
                <th>Disponibilité</th>
                <th>Date d'emprunt</th>
                <th>Date de retour</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="userHasTopo" items="${ userHasTopos }">
                <tr>
                    <td><c:out value="${ userHasTopo.pseudo }"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${ !empty sessionScope.user && userHasTopo.id == sessionScope.user.id }">
                                <a title="Modify" class="option-cursor" data-toggle="modal" data-target=".modal-menu" data-backdrop="static" data-keyboard="false">
                                    <c:out value="${ userHasTopo.topo.loaned == true ? 'emprunté' : 'disponible' }"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${ userHasTopo.topo.loaned == true ? 'emprunté' : 'disponible' }"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:if test="${ !empty userHasTopo.topo.borrowingDateString }"><c:out value="${ userHasTopo.topo.borrowingDateString }"/></c:if></td>
                    <td><c:if test="${ !empty userHasTopo.topo.returnDateString }"><c:out value="${ userHasTopo.topo.returnDateString }"/></c:if></td>
                </tr>

                <!-- UserHasTopo UPDATE -->
                <c:if test="${ !empty sessionScope.user && userHasTopo.id == sessionScope.user.id }">
                    <div class="modal fade modal-menu">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Mise à jour</h4>
                                </div>

                                <div class="modal-body">
                                    <!-- UserHasTopo DELETE -->
                                    <form hidden method="post" action="/user-topo/${ topo.publicationId }/delete" class="publication-delete">
                                        <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                                    </form>

                                    <form method="post" action="/user-topo/${ topo.publicationId }/update" class="form-horizontal publication-update">
                                        <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />

                                        <div class="checkbox">
                                            <b>Disponibilité : </b>
                                            <label>
                                                <input type="checkbox" name="loaned" value="${ userHasTopo.topo.loaned }" ${ userHasTopo.topo.loaned ? 'checked' : '' }/>
                                                est emprunté
                                            </label>
                                        </div>

                                        <div class="form-group">
                                            <label for="borrowing_date_update">Date d'emprunt :</label>
                                            <input type="date" class="form-control" name="borrowing_date" id="borrowing_date_update"/>
                                        </div>

                                        <div class="form-group">
                                            <label for="return_date_update">Date de retour :</label>
                                            <input type="date" class="form-control" name="return_date" id="return_date_update"/>
                                        </div>
                                    </form>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-danger" onclick="$('.publication-delete').submit();">Delete</button>
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

    <%@include file="_include/comments.jsp"%>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
