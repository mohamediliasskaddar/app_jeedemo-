<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: imk
  Date: 22/09/2025
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/user-form.css">

</head>
<body>
<form action="${action == 'edit' ? 'edit' : 'add'}" method="post">
    <c:if test="${action == 'edit'}">
        <input type="hidden" name="id" value="${user.id}">
    </c:if>
    Nom: <input type="text" name="name" value="${user.name}"><br>
    Email: <input type="email" name="email" value="${user.email}"><br>
    Âge: <input type="number" name="age" value="${user.age}"><br>
    <button type="submit">Enregistrer</button>
</form>

</body>
</html>
