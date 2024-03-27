<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - CarServlet</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            // Загрузка содержимого CarServlet в контейнер #carContainer
            $.get("AllCars", data => {
                $("#carContainer").html(data);
            });
        });
    </script>
</head>
<body>
<h1><%= "Автосалон Серафима" %>
</h1>
<br/>
<div id="carContainer"></div>
<h2>Добавление новой машины</h2>
<form action="AddCarServlet" method="post">
    Марка: <input type="text" name="brand"><br>
    Модель: <input type="text" name="model"><br>
    Дата производства: <input type="date" name="productionDate"><br>
    Цена: <input type="number" name="price"><br>
    Тип: <input type="text" name="type"><br>
    Страна производитель: <input type="text" name="country"><br>
    <input type="submit" value="Добавить">
</form>
<h2>Удаление машины</h2>
<form action="DeleteCarServlet" method="post">
    Удалить по идентификатору:<input type="text" name="carId"><br>
    <input type="submit" value="Удалить">
</form>
</body>
</html>