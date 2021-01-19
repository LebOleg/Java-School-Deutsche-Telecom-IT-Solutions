$(document).ready(function () {
    $(document).on('click', '.timetable', function () {
        let button = $(this);
        let parentDiv = button.parent();
        let date = parentDiv.children('.timetableDate').val();
        let time = parentDiv.children('.timetableTime').val();
        let number = parentDiv.children('.selectNumber').val();
        let trainNumber = parentDiv.parent().children('.trainNumber').text();

        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
        let csrfToken = $("meta[name='_csrf']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        });

        $.ajax({
            url: myContextPath + "/processTimetable",
            method: 'POST',
            data: {number : number, data : date, time:time, train:trainNumber},
            success: function (response) {


                parentDiv.children('.successText').text("Запись добавлена");


            },
            error: function(xhr, status, error) {
                alert(xhr.responseText);
            }

        })
    })
})