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
    </c:if>

    <p>${ topo.description }</p>

    <!-- TopoHasSpot READ -->
    <c:if test="${ topoHasSpots.size() > 0 }">
        <p>${ topo.spotsNumber } spot(s)</p>
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

    <%@include file="_include/comments.jsp"%>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
