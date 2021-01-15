<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Fill your data</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
<div class="col-md-8 offset-md-2">
<h6 class="text-center font-weight-light pt-4">Ticket</h6>

    <form:form action="" method="post" modelAttribute="ticketDTO">
        <div class="card">

          <h6 class="card-title text-center pt-2"> №${ticketDTO.train}</h6>
            <form:input path="train" type="hidden" value="${ticketDTO.train}"/>

        <p></p>
        <div class="card-body">
        <div class="row d-flex justify-content-center">
            <div>

                <div>${ticketDTO.departureTime}
                    <form:input path="departureTime" type="hidden" value="${ticketDTO.departureTime}"/>
                </div>
                <div  class="row d-flex justify-content-center font-weight-bold">${ticketDTO.sourceStation}
                    <form:input path="sourceStation" type="hidden" value="${ticketDTO.sourceStation}"/>
                </div>

            </div>

            <div class="col-10">
                <hr>
            </div>

            <div>
                <div>${ticketDTO.arrivalTime}
                    <form:input path="arrivalTime" type="hidden" value="${ticketDTO.arrivalTime}"/>
                </div>
                <div  class="row d-flex justify-content-center font-weight-bold"> ${ticketDTO.destinationStation}
                    <form:input path="destinationStation" type="hidden" value="${ticketDTO.destinationStation}"/>
                </div>
            </div>
        </div>
        <p></p>
        <span class="badge badge-info badge-pill mt-3">available ${ticketDTO.availableSeats} seats </span>
        <form:input path="availableSeats" type="hidden" value="${ticketDTO.availableSeats}"/>
    </form:form>
        </div>
        </div>
</div>
</div>

</body>
</html>
