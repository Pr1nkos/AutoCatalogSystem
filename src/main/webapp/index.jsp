<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Автосалон Серафима</title>
    <style>
        /* Стили для всплывающих окон */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        /* Стили для плитки изображений */
        .car img {
            float: left;
            margin: 10px;
            width: 200px;
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            // Загрузка содержимого CarServlet в контейнер #carContainer
            $.get("AllCars", data => {
                $("#carContainer").html(data);
            });
        });

        // Функция для открытия всплывающего окна добавления машины
        function openAddCarModal() {
            $("#addCarModal").css("display", "block");
        }

        // Функция для открытия всплывающего окна удаления машины
        function openDeleteCarModal() {
            $("#deleteCarModal").css("display", "block");
        }

        // Функция для закрытия всплывающих окон
        function closeModal() {
            $(".modal").css("display", "none");
        }
    </script>
</head>
<body>
<h1>Автосалон Серафима</h1>
<br/>
<!-- Кнопка для вызова всплывающего окна добавления машины -->
<button onclick="openAddCarModal()">Добавить машину</button>
<!-- Кнопка для вызова всплывающего окна удаления машины -->
<button onclick="openDeleteCarModal()">Удалить машину</button>
<div id="carContainer">
</div>


<!-- Всплывающее окно добавления машины -->

<div id="addCarModal" class="modal">
    <div class="modal-content">
        <!-- Форма добавления машины -->
        <h2>Добавление новой машины</h2>
        <form action="AddCarServlet" method="post" enctype="multipart/form-data">
            Марка: <input type="text" name="brand"><br>
            Модель: <input type="text" name="model"><br>
            Дата производства: <input type="date" name="productionDate"><br>
            Цена: <input type="number" name="price"><br>
            Тип: <input type="text" name="type"><br>
            Страна производитель: <input type="text" name="country"><br>
            <%--            TODO Сделать логику загрузки изображения--%>
            Изображение: <input type="file" name="image"><br>
            <input type="submit" value="Добавить">
        </form>
        <button onclick="closeModal()">Закрыть</button>
    </div>
</div>

<!-- Всплывающее окно удаления машины -->
<div id="deleteCarModal" class="modal">
    <div class="modal-content">
        <!-- Форма удаления машины -->
        <h2>Удаление машины</h2>
        <form action="DeleteCarServlet" method="post">
            Удалить по идентификатору:<input type="text" name="carId"><br>
            <input type="submit" value="Удалить">
        </form>
        <button onclick="closeModal()">Закрыть</button>
    </div>
</div>

</body>
</html>
