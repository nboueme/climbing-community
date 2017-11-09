<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <table class="table table-bordered table-striped table-condensed">
        <thead>
        <tr>
            <th>Topo</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="topo" items="${ topoList }">
            <tr>
                <td><a href="${pageContext.request.contextPath}/topo/item?id=${ topo.publication_id }"><c:out value="${ topo.name }" /></a></td>
                <td><c:out value="${ topo.description }" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
