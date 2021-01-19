$(document).ready(function () {
    $(document).on('click', '#myButtonRoute', function () {
        let routeName = $('#routeNumber').val();
        let routFromStation = $('#routeFromStation').val();
        let routToStation = $('#routeToStation').val();
        let travelTime = $('#travelTime').val();
        alert('r');
        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
        let csrfToken = $("meta[name='_csrf']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        });

        $.ajax({
            url: myContextPath + "/addPath",
            method: 'POST',
            data: {routeName: routeName, routFromStation : routFromStation, routToStation:routToStation, travelTime:travelTime
            },
            success: function (response) {
                $('#routeFromStation').val("");
                $('#routeToStation').val("");
                $('#travelTime').val("");

                if (response == 'true') {
                    $('#successTextRoute').text('Путь добавлен');
                } else {

                    $('#successTextRoute').text('Путь не добавлен');
                }

            },
            error: function(xhr, status, error) {
                alert(xhr.responseText);
            }

        })
    })
})