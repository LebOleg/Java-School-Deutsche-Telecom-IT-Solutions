$(document).ready(function () {
    $(document).on('click', '#myButtonTrain', function () {
        let seats = $('#availableSeats').val();
        let trainQuantity = $('#trainQuantity').val();
        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
        let csrfToken = $("meta[name='_csrf']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        });

        $.ajax({
            url: myContextPath + "/createTrain",
            method: 'POST',
            data: {seats : seats, trains : trainQuantity},
            success: function (response) {
                $('#availableSeats').val("");
                $('#trainQuantity').val("");

                if (response == 'true') {
                    $('#successTextConnection').text('Поезда добавлены');
                } else {

                    $('#successTextConnection').text('Поезда не добавлены');
                }

            },
            error: function(xhr, status, error) {
                alert(xhr.responseText);
            }

        })
    })
})