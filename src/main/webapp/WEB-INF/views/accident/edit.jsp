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
<div class="container mt-5">
    <h3 class="text-center">Редактировать инцидент</h3>
    <form class="fs-5" action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
        <div class="mb-3">
            <label for="name" class="form-label">Нарушение</label>
            <input type="text" class="form-control" id="name" aria-describedby="emailHelp" name='name' value="${accident.name}">
        </div>
        <div class="mb-3">
            <label for="text" class="form-label">Описание</label>
            <input type="text" class="form-control" id="text" name='text' value="${accident.text}">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Адрес</label>
            <input type="text" class="form-control" id="address" name='address' value="${accident.address}">
        </div>
        <button type="submit" class="btn btn-secondary">Сохранить</button>
    </form>
</div>
</body>
</html>