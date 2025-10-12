<%--
  Created by IntelliJ IDEA.
  User: imk
  Date: 26/09/2025
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Recherche par âge</title>
</head>
<body>
<h2>Entrer un âge pour afficher les utilisateurs correspondants</h2>
<form method="post" action="userage">
    l'age : <input type="number" name="age" >
    <button type="submit"> valider  </button>
</form>
<c:if test="${ not empty  usersAge}">
    <h2> users list with age </h2>
    <table border="1">
        <tr>
            <th>id</th>
            <th>user name </th>
            <th>email </th>
            <th>actions</th>
        </tr>

        <c:forEach var="u" items="${usersAge}">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.name}</td>
                    <td>${u.email}</td>
                    <td>
                        <a href="delete?id=${u.id}" onclick="return confirm('Supprimer cet utilisateur ?');">Supprimer</a>
                    </td>
                </tr>

             </c:forEach>
    </table>
</c:if>
<c:if test="${empty usersAge }">
    <p>no user found with this age </p>
</c:if>
</body>

<p> <strong> all are reserved btw hh </strong></p>
</body>
</html>
