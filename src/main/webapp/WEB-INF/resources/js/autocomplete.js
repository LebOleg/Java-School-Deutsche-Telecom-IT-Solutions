$(document).ready(function() {
    $("#search").keyup(function() {

        let searchText = $(this).val();
        if(searchText!='') {
            let csrfHeader = $("meta[name='_csrf_header']").attr("content");
            let csrfToken = $("meta[name='_csrf']").attr("content");
            $(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            });
            let json = {'partName' : searchText};

            $.ajax({
                url: myContextPath + "/autofill",
                type: 'post',
                data: JSON.stringify(json),
                contentType: 'application/json',
                dataType:'json',
                success: function (response){
                    $("#show-list").html(response.station);
                    document.getElementById("show-list").style.display = "inline";
                }
            });
        } else {
            document.getElementById("show-list").style.display = "none";
        }
    });
    $(document).on('click', 'a#show-list', function (){
        $("#search").val($(this).text());
        document.getElementById("show-list").style.display = "none";
    });
});