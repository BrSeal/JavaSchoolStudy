$(function () {
    const closeForm = function (selector) {
        $(selector).css("display", "none")
        $("buttons").css("display", "block");
    }

    const openForm = function (selector) {
        $(selector).css("display", "block")
        $("buttons").css("display", "none");
    }


    $("#show-get-form").click(function () {
        openForm("#get-form")
    });

    $("#show-insert-form").click(function () {
        openForm("#insert-form")
    });

    $("#save-task").click(function () {
        let data = document.getElementById('insert-in').value;
            $.ajax({
                method: "POST",
                url: '/tasks/add',
                data: data,
                success: function () {
                   alert("insert"+data);
                },
                error: function () {
                    alert("Error!");
                }
            });
            closeForm("#insert-form")
        return false;
    });

    $("#button-get").click(function () {
        var val = document.getElementById('get-in').value;
        $.ajax({
            method: "GET",
            url: '/get/' + taskId,
            success: function () {
                alert("get"+val);
            },
            error: function () {
                alert("Error!");
            }
        });
        closeForm("#get-form")
        return false;
    });
});