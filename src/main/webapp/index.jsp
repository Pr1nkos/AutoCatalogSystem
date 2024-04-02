<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru-Ru">
<head>
    <title>Автосалон Серафима</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="scripts.js"></script>
</head>
<body>
<div class="header">
    <h1>Автосалон Серафима</h1>
</div>
<div id="clock"></div>
<div class="button-container">
    <button class="button" onclick="openAddCarModal()">Добавить машину</button>
    <button class="button" onclick="openDeleteCarModal()">Удалить машину</button>
</div>

<div class="container">

    <div id="carContainer">
        <%--Здесь машины--%>
    </div>
</div>

<!-- Всплывающее окно добавления машины -->

<div id="addCarModal" class="modal">
    <div class="modal-content">
        <h2>Добавление новой машины</h2>
        <form action="AddCarServlet" method="post" enctype="multipart/form-data">
            <input class="form-input" type="text" name="brand" placeholder="Марка">
            <input class="form-input" type="text" name="model" placeholder="Модель">
            <input class="form-input" type="date" name="productionDate" placeholder="Дата производства">
            <input class="form-input" type="number" name="price" placeholder="Цена">
            <input class="form-input" type="text" name="type" placeholder="Тип">
            <input class="form-input" type="text" name="country" placeholder="Страна производитель">
            <input class="form-input" type="file" name="image" accept="image/*" placeholder="Изображение">
            <input class="form-input" type="submit" value="Добавить">
        </form>
        <button class="button" onclick="closeModal()">Закрыть</button>
    </div>
</div>

<!-- Всплывающее окно удаления машины -->
<div id="deleteCarModal" class="modal">
    <div class="modal-content">
        <h2>Удаление машины</h2>
        <form action="DeleteCarServlet" method="post">
            <input class="form-input" type="text" name="carId" placeholder="Удалить по идентификатору">
            <input class="form-input" type="submit" value="Удалить">
        </form>
        <button class="button" onclick="closeModal()">Закрыть</button>
    </div>
</div>

</body>
</html>
