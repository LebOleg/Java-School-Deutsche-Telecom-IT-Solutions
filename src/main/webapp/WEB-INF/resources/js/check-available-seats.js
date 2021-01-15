$(document).ready(function () {
    $(document).on('click', '.myButton', function () {

        let button = $(this);
        let trainNumber =$(button).parent().find('.trains').text();
        let form = $(button).closest('form.MyForm');

        let csrfHeader = $("meta[name='_csrf_header']").attr("content");
        let csrfToken = $("meta[name='_csrf']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        });

        $.ajax({
            url: myContextPath + "/checkAvailableSeats",
            method: 'POST',
            data: {query : trainNumber},
            success: function (response) {
                $(form).find('.seats').text('available ' + response + ' seats');
                $(form).find('.myInput').val(response);
                if (response == 0) {
                    alert("No seats");
                } else {
                    form.submit();
                }
            }
        })
    })
})