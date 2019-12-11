<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%@include file="head.jsp"%>
<body>
<%@include file="teamDescription.jsp"%>
<%@include file="navigationTeam.jsp"%>
<div class="container">
    <h2>Errors</h2>
    <c:if test="${errors != null}">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Message</th>
            <th scope="col">Description</th>
            <th scope="col">Timestamp</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="error" items="${errors}">
        <tr>
            <td>${error.message}</td>
            <td>${error.description}</td>
            <td>${error.dttm}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
</div>
<%@include file="footer.jsp"%>
</body>
</html>