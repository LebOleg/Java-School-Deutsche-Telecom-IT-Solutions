<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <script src="jquery/jquery.js"></script>
    <script type="text/javascript" src='js/bootstrap.min.js'></script>
    <link rel="stylesheet" href="css/bootstrap.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">


    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/nav.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/logout.js"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-navbar" >
    <a class="navbar-brand" href="<%= request.getContextPath()%>/">SBBProject</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">

    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<%= request.getContextPath()%>/">Главная</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="<%= request.getContextPath()%>/searchStation">Расписание</a>
            </li>
            <sec:authorize access="isAuthenticated()">
            <li class="nav-item active">
                <a class="nav-link" href="#">Мои билеты</a>
            </li>
            </sec:authorize>

<sec:authorize access="hasRole('EMPLOYEE')">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle"  href="#" style="background-color: black; border: none; color: white"  id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Управление станциями
                    </a>

                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink" style="z-index: 999">
                        <a class="dropdown-item" href="<%= request.getContextPath()%>/employee/station/getStationCreateForm">Добавить</a>
                        <a class="dropdown-item" href="<%= request.getContextPath()%>/employee/station/showRailway">Посмотреть схему</a>
                        <a class="dropdown-item" href="<%= request.getContextPath()%>/employee/route/getRouteNumberForm">Добавит маршрут</a>
                    </div>
                </li>


            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle"  href="#" style="background-color: black; border: none; color: white"  id="dropdownMenuLink2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Управление поездами
                </a>

                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink2" style="z-index: 999">
                    <a class="dropdown-item" href="<%= request.getContextPath()%>/employee/train/getCreateTrainForm">Добавить</a>
                    <a class="dropdown-item" href="<%= request.getContextPath()%>/employee/train">Список поездов</a>
                    <a class="dropdown-item" href="<%= request.getContextPath()%>/employee/timetable/addTimetable">Назначить маршрут</a>
                </div>
            </li>
</sec:authorize>
        </ul>




        <ul class="nav navbar-nav ml-auto">
            <sec:authorize access="!isAuthenticated()">
            <li class="nav-item active">
                <a class="nav-link" href="<%= request.getContextPath()%>/registration/showRegistrationForm"><span class="fas fa-user"></span> Регистрация</a>
            </li>



            <li class="nav-item active">
                <a class="nav-link" href="<%= request.getContextPath()%>/showLoginPage"><span class="fas fa-sign-in-alt"></span> Вход</a>
            </li>
</sec:authorize>
            <sec:authorize access="isAuthenticated()">


                <li class="nav-item active">

                    <a id="logout" href="#" class="nav-link"  onclick="logout()"><i class="fas fa-sign-out-alt"></i> Выход</a>

                </li>
            </sec:authorize>
        </ul>
    </div>
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
    </form:form>
</nav>


</body>
</html>
