<%--
  Created by IntelliJ IDEA.
  User: imk
  Date: 22/09/2025
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>imk</title>
</head>
<body>
<h1> welcome to imk app!</h1>

<button type="button" onclick="location.href='<%= request.getContextPath() %>/user/list'">Liste des utilisateurs</button>
<button type="button" onclick="location.href='<%= request.getContextPath() %>/user/add'">Ajouter un utilisateur</button>
<button type="button" onclick="location.href='<%= request.getContextPath() %>/userage'">Recherche par âge</button>

</body>
</html>
