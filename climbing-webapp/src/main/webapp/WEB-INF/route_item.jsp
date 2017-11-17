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
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
