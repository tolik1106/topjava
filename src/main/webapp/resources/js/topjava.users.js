// $(document).ready(function () {
$(function () {
    makeEditable({
            ajaxUrl: "ajax/admin/users/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "email"
                    },
                    {
                        "data": "roles"
                    },
                    {
                        "data": "enabled"
                    },
                    {
                        "data": "registered"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ]
            })
        }
    );
});

function saveEnable() {
    $.ajax({
        type: "POST",
        url: context.ajaxUrl,
        data: {
            id: id,
            name: "",
            email: "",
            password: "",
            action: cheched,
        },
    }).done(function () {
        updateTable();
        successNoty("Updated");
    });
}

$(function () {
    $(":checkbox:checked").each(function () {
        $(this).parent().parent().css({"background-color": "yellow", "color": "red"});
    })
});

var id, cheched;
$(function () {
    $(":checkbox").each(function () {
        $(this).click(function (e) {
            id = $(this).parent().parent().attr("id");
            cheched = $(this).prop("checked") ? "true" : "false";
            saveEnable();
        })
    })
});

