<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand">Menu</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="teamPage">Home</a></li>
                <li><a href="applications">Applications</a></li>
                <li><a href="#">Errors</a></li>
                <c:if test="${isAdmin != null}">
                    <li><a href="teamPage">Add Member</a> </li>
                </c:if>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>