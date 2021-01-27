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
        let json = {'seats': seats, 'route': route, 'date': date, 'time': time};

        $.ajax({
            url: myContextPath + "/employee/train/createTrain",
            type: 'POST',
            data: JSON.stringify(json),
            contentType: 'application/json',
            dataType: 'json',
            success: function (response) {
                $('#availableSeats').val("");
                $('#routeNumber').val("");
                $('#timetableDate').val("");
                $('#timetableTime').val("");
                $('#successTextConnection').text(response.message);

            },
            error: function(xhr, status, error) {
                alert(xhr.responseText);
            }

        })
    })
})