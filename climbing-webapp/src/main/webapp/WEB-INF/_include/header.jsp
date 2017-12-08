<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">${application.name}</a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/">Accueil</a></li>
                <li><a href="${pageContext.request.contextPath}/climbing">Sites</a></li>
                <li><a href="${pageContext.request.contextPath}/topo">Topo</a></li>
                <li><a href="${pageContext.request.contextPath}/search">Recherche</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${ empty sessionScope.user }">
                        <li><a href="${pageContext.request.contextPath}/inscription"><span class="glyphicon glyphicon-user"></span> Créer un compte</a></li>
                        <li><a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> S’identifier</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="dropdown">
                            <a data-toggle="dropdown" href="">${ sessionScope.user.pseudo } <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/account/${ sessionScope.user.id }"><span class="glyphicon glyphicon-user"></span> Mon compte</a></li>
                                <li class="divider"></li>
                                <li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span> Se déconnecter</a></li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

    </div>
</nav>