$(document).ready(function () {
    $(document).on('click', '#myButtonPath', function () {
        let fromStation = $('#fromStation').val();
        let toStation = $('#toStation').val();
        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
        let csrfToken = $("meta[name='_csrf']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        });

        $.ajax({
            url: myContextPath + "/employee/station/createConnection",
            method: 'POST',
            dataType: "text",
            data: {fromStation : fromStation, toStation: toStation},
            success: function (response) {
                $('#fromStation').val("");
                $('#toStation').val("");

                if(response == "true") {
                $('#successTextConnection').text('Связь добавлена');
            } else {
                    $('#successTextConnection').text('Неверный формат данных или связь уже существует');
                }
            }

        })
    })
})