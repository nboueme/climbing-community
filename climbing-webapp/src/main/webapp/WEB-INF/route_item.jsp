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
    <p>${ route.name } -> ${ route.height } mètres, ${ route.pointsNumber } points, coté ${ route.quotation }</p>

    <c:if test="${ listLength.size() > 0 }">
        <h4>Liste des longueurs</h4>
        <ul>
            <c:forEach var="length" items="${ listLength }">
                <li>${ length.name } -> ${ length.height } mètres, ${ length.pointsNumber } points, coté ${ length.quotation }</li>
            </c:forEach>
        </ul>
    </c:if>

    <%@include file="_include/comments.jsp"%>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
