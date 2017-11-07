<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <table class="table table-bordered table-striped table-condensed">
            <thead>
            <tr>
                <th>Voie</th>
                <th>Hauteur</th>
                <th>Points</th>
                <th>Cotation</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="route" items="${ routeList }">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/climbing/route/item"><c:out value="${ route.name }" /></a></td>
                        <td><c:out value="${ route.height }" /></td>
                        <td><c:out value="${ route.points_number }" /></td>
                        <td><c:out value="${ route.quotation }" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="_include/footer.jsp" %>

</body>
</html>