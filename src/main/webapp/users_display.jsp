<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%@include file="head.jsp"%>
<body>
<%@include file="teamDescription.jsp"%>
<%@include file="navigationTeam.jsp"%>
<div class="container">
    <h2>Users</h2>
    <c:if test="${action == null}">
        <a href="users?action=add">Add User</a>
    </c:if>
    <c:if test="${action != null}">
        <div class="row">
            <form action="users" method="post">
                <div>
                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                        <input type="hidden" name="imageUrl" value="<c:out value='${user.imageUrl}' />" />
                    </c:if>
                        <div class="form-group">
                            <label for="firstName">FirstName</label>
                            <input type="TEXT" class="form-control" size="35" id="firstName" name="firstName" value="<c:out value='${user.firstName}' />">
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
                            <label for="lastName">LastName</label>
                            <input type="TEXT" class="form-control" id="lastName" name="lastName" value="<c:out value='${user.lastName}'/>">
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
                            <label for="userName">UserName</label>
                            <input type="TEXT" class="form-control" id="userName" name="userName" value="<c:out value='${user.userName}'/>">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="PASSWORD" class="form-control" id="password" name="password" value="<c:out value='${user.password}'/>">
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
                            <label for="dob">Date Of Birth</label>
                            <input type="DATE" class="form-control" id="dob" name="dob" required pattern="\d{4}-\d{2}-\d{2}" placeholder="2019-12-06" value="<c:out value='${user.dob}'/>">
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
                            <label for="role">Role</label>
                            <input type="TEXT" class="form-control" id="role" name="role" value="<c:out value='${user.role}'/>">
                            <input type="submit" value="Add">
                        </div>
                </div>
            </form>
        </div>
    </c:if>
    <c:if test="${users != null}">
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">FirstName</th>
                    <th scope="col">LastName</th>
                    <th scope="col">UserName</th>
                    <th scope="col">Date of Birth</th>
                    <th scope="col">Role</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.userName}</td>
                    <td>${user.dob}</td>
                    <td>${user.role}</td>
                    <td><a href="users?user_id=<c:out value='${user.id}&action=edit' />">Edit</a></td>
                    <td><a href="users?user_id=<c:out value='${user.id}&action=delete' />">Delete</a></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</div>
<%@include file="footer.jsp"%>
</body>
</html>