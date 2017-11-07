<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <table class="table table-bordered table-striped table-condensed">
            <thead>
                <tr>
                    <th>Site</th>
                    <th>Hauteur</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="spot" items="${ spotList }">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/climbing/sector"><c:out value="${ spot.name }" /></a></td>
                        <td>?</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="_include/footer.jsp" %>

</body>
</html>
