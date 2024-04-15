<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru-Ru">
<head>
    <meta charset="UTF-8">
    <title>Добавить машину</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css">
    <link rel="stylesheet" type="text/css" href="../css/addCar.css">
</head>
<body>
<a href="../index.jsp">
    <div class="header">
        <h1>Автосалон Серафима</h1>
    </div>
</a>
<h1>Добавить машину</h1>
<form class="addCarForm" action="CarController?action=add" method="post" enctype="multipart/form-data">
    <label for="brand">Марка:</label>
    <input type="text" id="brand" name="brand" required><br>

    <label for="model">Модель:</label>
    <input type="text" id="model" name="model" required><br>

    <label for="productionDate">Дата производства:</label>
    <input type="date" id="productionDate" name="productionDate" required><br>

    <label for="price">Цена:</label>
    <input type="number" id="price" name="price" required><br>

    <label for="type">Тип:</label>
    <input type="text" id="type" name="type" required><br>

    <label for="country">Страна производитель:</label>
    <input type="text" id="country" name="country" required><br>
    <div class="file-input">
        <label for="file">Выберите файл изображения</label>
        <input id="file" class="form-input" type="file" name="image" accept="image/jpeg"
               placeholder="Изображение">
    </div>
    <button type="submit">Добавить</button>
</form>
</body>
</html>
