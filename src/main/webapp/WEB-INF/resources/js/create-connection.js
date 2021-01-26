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
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            success: function (response) {
                response = decodeURI(response).replace( /\+/g, ' ' );
                $('#fromStation').val("");
                $('#toStation').val("");

                $('#successTextConnection').text(response);
            }

        })
    })
})