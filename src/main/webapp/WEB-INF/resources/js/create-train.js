$(document).ready(function () {
    $(document).on('click', '#myButtonTrain', function () {
        let seats = $('#availableSeats').val();
        let route = $('#routeNumber').val();
        let date = $('#timetableDate').val();
        let time = $('#timetableTime').val();

        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
        let csrfToken = $("meta[name='_csrf']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        });

        $.ajax({
            url: myContextPath + "/employee/train/createTrain",
            method: 'POST',
            data: {seats : seats, route : route, date: date, time: time},
            success: function (response) {
                response = decodeURI(response).replace( /\+/g, ' ' );
                $('#availableSeats').val("");
                $('#routeNumber').val("");
                $('#timetableDate').val("");
                $('#timetableTime').val("");
                $('#successTextConnection').text(response);

            },
            error: function(xhr, status, error) {
                alert(xhr.responseText);
            }

        })
    })
})