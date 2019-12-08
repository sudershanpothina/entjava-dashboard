 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>
    <%@include file="head.jsp"%>
    <body>
    <%@include file="teamDescription.jsp"%>
    <%@include file="navigationTeam.jsp"%>
        <div class="container-fluid">
            <h2>All members of Team1 </h2>
                <table class="table table-bordered table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th> ID </th>
                            <th> First Name </th>
                            <th> Last Name </th>
                        </tr>
                    </thead>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                        </tr>
                    </c:forEach>
                </table>
            <h2>All applications of Team1 </h2>
            <table style="width:100%; border-spacing: 5px">
                <tr>
                    <th> ID </th>
                    <th> Name </th>
                    <th> Description </th>
                </tr>
                <c:forEach var="application" items="${applications}">
                    <tr>
                        <td>${application.id}</td>
                        <td>${application.name}</td>
                        <td>${application.description}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    <%@include file="footer.jsp"%>
    </body>
    </html>