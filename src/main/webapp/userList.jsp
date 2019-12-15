 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <html>
    <%@include file="head.jsp"%>
    <body>
    <%@include file="teamDescription.jsp"%>
    <%@include file="navigationTeam.jsp"%>
    <div class="container">
        <h2>Team Members</h2>
            <c:forEach var="user" items="${users}">
        <div class="row">
            <div class="col-sm-4">
                <img class="fakeimg1" style="max-height: 50%; max-width: 50%;" src="${user.imageUrl}"/>
            </div>
            <div class="col-sm-8">
                <h5>Name: ${user.firstName} ${user.lastName}</h5>
                <p>Date of Birth: ${user.dob}</p>
                <p>Role: ${user.role}</p>
            </div>
        </div>
            </c:forEach>
    </div>
    <%@include file="footer.jsp"%>
    </body>
    </html>