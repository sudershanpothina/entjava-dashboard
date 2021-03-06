<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="head.jsp"%>
<body>
<%@include file="teamDescription.jsp"%>
<%@include file="navigationTeam.jsp"%>
<div class="container">
    <h2>Applications</h2>
    <c:if test="${isAdmin != null}">
        <a href="applications?action=add">Add application</a>
    </c:if>
    <c:if test="${action != null}">
    <form action="applications" method="post">
        <div class="form-group">
            <c:if test="${application != null}">
                <input type="hidden" name="id" value="<c:out value='${application.id}' />" />
            </c:if>
            <label for="name">Name</label>
            <input type="TEXT" class="form-control" id="name" name="name" value="<c:out value='${application.name}' />">
            <label for="description">Description</label>
            <input type="TEXT" class="form-control" id="description" name="description" value="<c:out value='${application.description}'/>">
            <input type="submit" value="Add">
        </div>
    </form>
    </c:if>
    <c:if test="${applications != null}">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="application" items="${applications}">
        <c>
            <td>${application.name}</td>
            <td>${application.description}</td>
            <td><a href="applications?application_id=<c:out value='${application.id}&action=error'/>">Use Application</a></td>
            <td><a href="errors?application_id=<c:out value='${application.id}' />">Errors</a></td>
            <c:if test="${isAdmin != null}">
                <td><a href="applications?application_id=<c:out value='${application.id}&action=edit' />">Edit</a></td>
                <td><a href="applications?application_id=<c:out value='${application.id}&action=delete' />">Delete</a></td>
            </c:if>
        </tr>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
</div>
<%@include file="footer.jsp"%>
</body>
</html>