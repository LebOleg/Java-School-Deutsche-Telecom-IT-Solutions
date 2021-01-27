$(document).ready(function () {
    $(document).on('click', '#myButtonRoute', function () {
        let routeName = $('#routeNumber').val();
        let routFromStation = $('#routeFromStation').val();
        let routToStation = $('#routeToStation').val();
        let travelTime = $('#travelTime').val();
        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
        let csrfToken = $("meta[name='_csrf']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        });
        let json = {'routeName' : routeName, 'routFromStation':routFromStation, 'routToStation':routToStation, 'travelTime':travelTime}

        $.ajax({
            url: myContextPath + "/employee/route/addPath",
            type: 'POST',
            data: JSON.stringify(json),
            contentType: 'application/json',
            dataType: 'json',
            success: function (response) {
                $('#routeFromStation').val("");
                $('#routeToStation').val("");
                $('#travelTime').val("");
                $('#successTextRoute').text(response.message);
            },
        })
    })
})