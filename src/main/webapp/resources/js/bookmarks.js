base_url = 'http://localhost:8080/bookmark';

function currentUser() {
    $.ajax({
        url: base_url + '/current',
        method: "GET",
        success: function (data) {
            if (data !== null) {
                closeDialog(data.name);
            }
        },
        error: function (e) {
            console.log("ERROR: ", e);
        }
    });
}

function addBookmark() {
    var bookmarkUrl = $("#bookmark-url").val();
    var bookmarkName = $("#bookmark-name").val();

    $.ajax({
        url: base_url + "/add-bookmark",
        method: "POST",
        data: {"name": bookmarkName, "url": bookmarkUrl},
        success: function (data) {
            location.reload();
        },
        error: function (error) {
            console.log("ERROR: ", error);
        }
    });

}

function closeDialog(name) {
    $('#logout-item').show();
    $('#user-item').show();
    $('#user-name').html("Hi " + name);
    $('#add-bookmark').show();
}