<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <table class="table table-bordered table-striped table-condensed">
        <thead>
        <tr>
            <th>Secteur</th>
            <th>Hauteur</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="sector" items="${ sectorList }">
            <tr>
                <td><a href="${pageContext.request.contextPath}/climbing/route?id=${ sector.publication_id }"><c:out value="${ sector.name }" /></a></td>
                <td><c:out value="${ sector.height == 0 ? 'hauteur inconnue' : sector.height }" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
