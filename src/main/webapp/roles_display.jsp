<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%@include file="head.jsp"%>
<body>
<%@include file="teamDescription.jsp"%>
<%@include file="navigationTeam.jsp"%>
<div class="container">
    <h2>Users</h2>

    <a href="users?action=add">Add User</a>

    <c:if test="${action != null}">
    <form action="users" method="post">
        <div class="form-group">
            <c:if test="${user != null}">
                <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                <input type="hidden" name="imageUrl" value="<c:out value='${user.imageUrl}' />" />
            </c:if>
            <label for="firstName">FirstName</label>
            <input type="TEXT" class="form-control" id="firstName" name="firstName" value="<c:out value='${user.firstName}' />">
            <label for="lastName">LastName</label>
            <input type="TEXT" class="form-control" id="lastName" name="lastName" value="<c:out value='${user.lastName}'/>">
            <label for="userName">UserName</label>
            <input type="TEXT" class="form-control" id="userName" name="userName" value="<c:out value='${user.userName}'/>">
            <label for="dob">Date Of Birth</label>
            <input type="DATE" class="form-control" id="dob" name="dob" required pattern="\d{4}-\d{2}-\d{2}" placeholder="2019-12-06" value="<c:out value='${user.dob}'/>">
            <input type="submit" value="Add">
        </div>
    </form>
    </c:if>
    <div class="container"></div>
    <c:if test="${users != null}">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">FirstName</th>
            <th scope="col">LastName</th>
            <th scope="col">UserName</th>
            <th scope="col">Date of Birth</th>
            <th scope="col">Roles</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.userName}</td>
            <td>${user.dob}</td>
            <c:if test="${user.roles != null}">
                <td>
                    <ul>
                        <li><a href="roles?user_id=<c:out value='${user.id}&action=edit' />">Add/Edit Roles</a></li>
                        <c:forEach var="role" items="${user.roles}">
                            <li>${role}</li>
                        </c:forEach>
                    </ul>
                </td>
            </c:if>
            <td><a href="users?user_id=<c:out value='${user.id}&action=edit' />">Edit</a></td>
            <td><a href="users?user_id=<c:out value='${user.id}&action=delete' />">Delete</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
</div>
<%@include file="footer.jsp"%>
</body>
</html>