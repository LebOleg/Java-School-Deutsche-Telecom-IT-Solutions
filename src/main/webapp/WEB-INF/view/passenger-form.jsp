<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<h5 class="text-center font-weight-light pt-4">Ticket</h5>

        <div class="card">

          <h6 class="card-title text-center pt-2"> №${ticketDTO.train}</h6>




        <p></p>
        <div class="card-body">
        <div class="row d-flex justify-content-center">
            <div>
                <div>${ticketDTO.departureTime}</div>

                <div  class="row d-flex justify-content-center font-weight-bold">${ticketDTO.sourceStation}</div>


            </div>

            <div class="col-10">
                <hr>
            </div>

            <div>
                <div>${ticketDTO.arrivalTime}</div>

                <div  class="row d-flex justify-content-center font-weight-bold"> ${ticketDTO.destinationStation}</div>

            </div>
        </div>
        <p></p>
        <span class="badge badge-info badge-pill mt-3">available ${ticketDTO.availableSeats} seats </span>

        </div>
        </div>

</div>
</div>

<div class="container">
    <div class="p-4">
        <h5 class="text-center font-weight-light" >Fill in forms</h5>
    </div>
    <div class="col-md-8 offset-md-2">
    <form:form class="form-horizontal" action="${pageContext.request.contextPath}/processPassengerForm" method="post" modelAttribute="passenger">
        <div class="row pb-2">
        <div class="col-4 form-group">
        <form:input id="name" class="form-control" placeholder="Name" type="text" path="name"/>
        </div>

        <div class="col-4 form-group">
            <form:input id="lastName" class="form-control" placeholder="Last Name" type="text" path="lastName"/>
        </div>

        <div class="col-4 form-group">
            <form:input id="middleName" class="form-control" placeholder="Middle Name" type="text" path="middleName"/>
        </div>
    </div>
        <div class="row pb-3">
            <div class="col-4 form-group">
                <label for="birth"><small>Birthday</small></label>
            <form:input id="birth" class="form-control" type="date" path="birthday"/>
            </div>

            <div class="col-4 form-group">
                <label for="birth"><small>&nbsp;</small></label>
                <form:input id="email" class="form-control" type="text" placeholder="Email" path="email"/>
            </div>

            <div class="col-4 form-group">
                <label for="birth"><small>&nbsp;</small></label>
                <form:input id="pasport" class="form-control" type="text" placeholder="Passport number" path="passportNumber"/>
                <form:input path="ticketDTO.train" type="hidden" value="${ticketDTO.train}" name="train"/>
                <form:input path="ticketDTO.departureTime" type="hidden" value="${ticketDTO.departureTime}"/>
                <form:input path="ticketDTO.sourceStation" type="hidden" value="${ticketDTO.sourceStation}"/>
                <form:input path="ticketDTO.arrivalTime" type="hidden" value="${ticketDTO.arrivalTime}"/>
                <form:input path="ticketDTO.dateTicket" type="hidden" value="${ticketDTO.dateTicket}"/>
                <form:input path="ticketDTO.destinationStation" type="hidden" value="${ticketDTO.destinationStation}"/>
                <form:input path="ticketDTO.availableSeats" type="hidden" value="${ticketDTO.availableSeats}"/>
            </div>

        </div>

        <div class="row">
            <div class="col-6 form-group">
                <input type="button" value="Cancel" class="btn btn-info rounded-0" onclick="goBack()">
            </div>

            <div class="col-6 form-group">
            <input type="submit" value="Buy" class="btn btn-info rounded-0" style="width: 25%; float: right">
            </div>
        </div>
    </form:form>
</div>
</div>

<script>
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>