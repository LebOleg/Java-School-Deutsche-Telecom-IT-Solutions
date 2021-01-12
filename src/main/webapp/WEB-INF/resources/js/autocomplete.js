$(document).ready(function() {
    $("#search").keyup(function() {

        let searchText = $(this).val();
        if(searchText!='') {
            let csrfHeader = $("meta[name='_csrf_header']").attr("content");
            let csrfToken = $("meta[name='_csrf']").attr("content");
            $(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            });

            $.ajax({
                url: myContextPath + "/autofill",
                method: 'POST',
                data: {query: searchText},
                success: function (response){
                    $("#show-list").html(response);
                    document.getElementById("show-list").style.display = "inline";
                }
            });
        } else {
            document.getElementById("show-list").style.display = "none";
        }
    });
    $(document).on('click', 'a', function (){
        $("#search").val($(this).text());
        document.getElementById("show-list").style.display = "none";
    });
});