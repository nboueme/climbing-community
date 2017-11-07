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
        <p>${ topo.description }</p>
    </div>

    <%@ include file="_include/footer.jsp" %>

</body>
</html>
