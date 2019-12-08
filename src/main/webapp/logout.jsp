<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <%@include file="head.jsp"%>
    <body>
        <%@include file="mainDescription.jsp"%>
        <%@include file="navigation.jsp"%>
        <%@include file="mainContent.jsp"%>
        <c:if test="${request.getRemoteUser() != null}">
            User '<%=request.getRemoteUser()%>' has been logged out.
        </c:if>
            <% session.invalidate(); %>
            <br/><br/>
        <%@include file="footer.jsp"%>
    </body>
