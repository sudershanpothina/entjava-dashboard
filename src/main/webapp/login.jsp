<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="head.jsp"%>
<body>
    <%@include file="mainDescription.jsp"%>
    <%@include file="navigation.jsp"%>
    <%@include file="mainContent.jsp"%>
    <form action="j_security_check" method="post">
        <div class="form-group">
            <label for="j_username">User name</label>
            <input type="TEXT" class="form-control" id="j_username" placeholder="jpaul" name="j_username">
            <label for="j_password">Password</label>
            <input type="PASSWORD" class="form-control" id="j_password" name="j_password">
            <input type="submit" value="Log In">
        </div>
    </form>
    <%@include file="footer.jsp"%>
</body>
</html>


