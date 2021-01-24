<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>


<html>
<head>
    <title>Создать станции</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>const myContextPath = "${pageContext.request.contextPath}"</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/create-station.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/create-connection.js"></script>

    <sec:csrfMetaTags />
    <meta charset="UTF-8">
</head>
<body>
<%@ include file="nav.jsp"%>

<div class="row offset-2 mt-5">

    <div class="col-4 mr-5">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title text-center">Создать станцию</h5>
                <p class="card-text">
                    <input id="stationName" class="form-control" placeholder="Станция" type="text"/>

                </p>
                <input id="myButton" class="btn btn-info rounded-0 m-2" type="button" value="Добавить">
                <span id="createSuccess" class="font-weight-light text-info"><span id="successText"></span></span>

            </div>
        </div>
    </div>

    <div class="col-4 ml-5">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title text-center">Создать связь между станциями</h5>
                <p class="card-text">
                    <input id ="fromStation" class="form-control mb-2" placeholder="От" type="text">
                    <input id ="toStation" class="form-control" placeholder="До" type="text">
                </p>

                <input id="myButtonPath" class="btn btn-info rounded-0 mt-2 mb-2" type="button" value="Добавить">
                <div>
                <span id="createSuccessConnection" class="font-weight-light text-info"><span id="successTextConnection"></span></span>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
