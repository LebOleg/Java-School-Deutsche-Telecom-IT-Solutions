$(document).ready(function () {
    $(document).on('click', '#myButton', function () {
        let station = $('#stationName').val();
        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
        let csrfToken = $("meta[name='_csrf']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        });
        let json = {'station' : station};

        $.ajax({
            url: myContextPath + "/employee/station/createStation",
            type: 'post',
            data: JSON.stringify(json),
            contentType: 'application/json',
            dataType: 'json',
            success: function (response) {
                $('#stationName').val("");
                $('#successText').text(response.message);
            }
        })
    })
})