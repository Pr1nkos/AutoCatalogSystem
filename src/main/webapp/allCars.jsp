<%@ page import="ru.pr1nkos.autocatalogsystem.model.Car" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru-Ru">
<head>
    <title>Автосалон Серафима</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css">
    <link rel="stylesheet" type="text/css" href="../css/button.css">
</head>
<body>
<a href="../index.jsp">
    <div class="header">
        <h1>Автосалон Серафима</h1>
    </div>
</a>
<div id="clock"></div>

<div class="container">

    <div id="carContainer">
        <h1>Список машин:</h1>
        <div style="display: flex; flex-wrap: wrap;">
            <% for (Car car : (List<Car>) request.getAttribute("cars")) { %>
            <div class="car">
                <img src="<%= car.getImageURL() %>" alt="<%= car.getBrand() %> <%= car.getModel() %>">
                <p><strong>ID: <%= car.getId() %>
                </strong></p>
                <p><strong><%= car.getBrand() %> <%= car.getModel() %>
                </strong></p>
                <p>Дата производства: <%= car.getProductionDate() %>
                </p>
                <p>Цена: <%= car.getPrice() %> руб.</p>
                <p>Тип: <%= car.getType() %>
                </p>
                <p>Страна производитель: <%= car.getCountry() %>
                </p>
                <form action="../controller/CarController?action=delete&carId=<%= car.getId() %>"
                      method="post">
                    <button class="delete-button-style" type="submit">Удалить</button>
                </form>
            </div>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
