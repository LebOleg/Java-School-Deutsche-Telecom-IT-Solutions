$(document).ready(function () {
    $(document).on('click', '#myButtonPath', function () {
        let fromStation = $('#fromStation').val();
        let toStation = $('#toStation').val();
        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
        let csrfToken = $("meta[name='_csrf']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        });
        let json = {'fromStation':fromStation, 'toStation':toStation};
        $.ajax({
            url: myContextPath + "/employee/station/createConnection",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: "application/json",
            success: function (response) {
                $('#fromStation').val("");
                $('#toStation').val("");
                $('#successTextConnection').text(response.message);
            }

        })
    })
})