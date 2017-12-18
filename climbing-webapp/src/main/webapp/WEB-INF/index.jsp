<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="_include/html_head.jsp" %>
    <title>${application.name} - Accueil</title>
</head>
<body>

<%@ include file="_include/header.jsp" %>

<div class="container">
    <h1><spring:message code="menu.home"/></h1>

    <h4>Liste des spots</h4>
    <c:if test="${spotList.size() > 0}">
        <table class="table table-bordered table-striped table-condensed">
            <thead>
            <tr>
                <th>Site</th>
                <th>Description</th>
                <th>Hauteur</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="spot" items="${ spotList }" varStatus="spotStatus">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/climbing/${ spot.publicationId }"><c:out value="${ spot.name }" /></a></td>
                    <td>${ spot.description }</td>
                    <td>${ spot.height }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <h4>Liste des topo</h4>
    <c:if test="${topoList.size() > 0}">
        <table class="table table-bordered table-striped table-condensed">
            <thead>
            <tr>
                <th>Topo</th>
                <th>Description</th>
                <th>Spots</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="topo" items="${ topoList }">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/topo/${ topo.publicationId }"><c:out value="${ topo.name }" /></a></td>
                    <td><c:out value="${ topo.description }"/></td>
                    <td><c:out value="${ topo.spotsNumber }"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
