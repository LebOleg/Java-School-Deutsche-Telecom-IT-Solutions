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

        $.ajax({
            url: myContextPath + "/employee/route/addPath",
            method: 'POST',
            data: {routeName: routeName, routFromStation : routFromStation, routToStation:routToStation, travelTime:travelTime
            },
            success: function (response) {
                response = decodeURI(response).replace( /\+/g, ' ' );
                $('#routeFromStation').val("");
                $('#routeToStation').val("");
                $('#travelTime').val("");
                $('#successTextRoute').text(response);
            },
        })
    })
})