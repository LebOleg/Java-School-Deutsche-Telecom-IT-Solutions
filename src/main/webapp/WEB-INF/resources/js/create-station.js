$(document).ready(function () {
    $(document).on('click', '#myButton', function () {
        let station = $('#stationName').val();
        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
        let csrfToken = $("meta[name='_csrf']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        });

        $.ajax({
            url: myContextPath + "/employee/station/createStation",
            method: 'POST',
            data: {query :station},
            success: function (response) {
                    response = decodeURI(response).replace( /\+/g, ' ' );
                    $('#stationName').val("");
                    $('#successText').text(response);
            }

        })
    })
})