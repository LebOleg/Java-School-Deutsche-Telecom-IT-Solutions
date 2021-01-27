$(document).ready(function () {
    $(document).on('click', '.myButton', function () {

        let button = $(this);
        let trainNumber =$(button).parent().find('.trains').val()
        let form = $(button).closest('form.MyForm');

        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
        let csrfToken = $("meta[name='_csrf']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        });
        let json = {'train' : trainNumber};

        $.ajax({
            url: myContextPath + "/ticket/checkAvailableSeats",
            type: 'post',
            data: JSON.stringify(json),
            contentType: 'application/json',
            dataType: 'json',
            success: function (response) {
                $(form).find('.seats').text('Свободно ' + response.seats + ' мест');
                $(form).find('.myInput').val(response.seats);
                if (response.seats == 0) {
                    $('#zeroTicket').text("Билетов нет");
                } else {
                    form.submit();
                }
            }
        })
    })
})