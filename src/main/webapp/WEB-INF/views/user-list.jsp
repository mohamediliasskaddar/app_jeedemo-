<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>

<html>
<head>
    <title>Liste des utilisateurs</title>
</head>
<body>
<h2>Liste des utilisateurs</h2>

<a href="add">Ajouter un utilisateur</a><br><br>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Email</th>
        <th>Âge</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.email}</td>
            <td>${u.age}</td>
            <td>
                <a href="edit?id=${u.id}">Modifier</a> |
                <a href="delete?id=${u.id}" onclick="return confirm('Supprimer cet utilisateur ?');">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

<%--a jsp files it heritate akl form servel basicly it sa servlet,--%>
<%--script, style, scriptlet,--%>
