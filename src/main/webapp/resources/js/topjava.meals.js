$(function () {
    makeEditable({
        ajaxUrl: "ajax/meals/",
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
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
                    "desc"
                ]
            ]
        })
    });
});

function updateWithFilter() {
    $.ajax({
        type: "GET",
        url: context.ajaxUrl + "filter",
        data: {
            startDate: startDate,
            endDate: endDate,
            startTime: startTime,
            endTime: endTime
        }
    }).done(function (data) {
        context.datatableApi.clear().rows.add(data).draw();
    });
}

function dropFilter() {
    $.ajaxSetup({cache: true});
    startDate = null;
    endDate = null;
    startTime = null;
    endTime = null;
    updateTable();
};

function filter() {
    $.ajaxSetup({cache: true});
    setFilter();
    $.ajax({
        type: "GET",
        url: context.ajaxUrl + "filter",
        data: {
            startDate: startDate,
            endDate: endDate,
            startTime: startTime,
            endTime: endTime
        }
    }).done(function (data) {
        context.datatableApi.clear().rows.add(data).draw();
    });
};

function setFilter() {
    startDate = $('input[name="startDate"]').val(),
        endDate = $('input[name="endDate"]').val(),
        startTime = $('input[name="startTime"]').val(),
        endTime = $('input[name="endTime"]').val()
}



