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

// Функция для обновления времени на часах
function updateClock() {
    let now = new Date();
    let hours = now.getHours();
    let minutes = now.getMinutes();
    let seconds = now.getSeconds();

    hours = (hours < 10) ? "0" + hours : hours;
    minutes = (minutes < 10) ? "0" + minutes : minutes;
    seconds = (seconds < 10) ? "0" + seconds : seconds;

    // Обновляем содержимое элемента с идентификатором clock
    document.getElementById("clock").innerHTML = hours + ":" + minutes + ":" + seconds;
}

setInterval(updateClock, 1000);
updateClock();
