<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Accident</title>
</head>
<body>
    <nav class="navbar navbar-dark bg-secondary">
        <div class="container">
            <span class="navbar-brand mb-0 fs-3">CarAccident</span>
        </div>
    </nav>
    <div class="container">
        <a class="btn btn-secondary fs-5 m-4 float-end" href="<c:url value='/create'/>">Добавить инцидент</a>
        <table class="table table-bordered table-striped fs-6 mt-3">
            <thead>
            <tr>
                <th scope="col">Нарушение</th>
                <th scope="col">Описание</th>
                <th scope="col">Адрес</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="accident" items="${listAccident}">
                <tr>
                    <td>${accident.name}</td>
                    <td>${accident.text}</td>
                    <td>${accident.address}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
