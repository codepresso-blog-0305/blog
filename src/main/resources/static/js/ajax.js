$(function(){
    var session_id = $.cookie('id');
    if(session_id) {
        $("#logout-menu").show();
        $("#login-menu").hide();
        $("#signup-menu").hide();
    } else {
        $("#logout-menu").hide();
        $("#login-menu").show();
        $("#signup-menu").show();
    }

    $(document).on("click","#logout-menu",function(){
        var session_id = $.cookie('id');

        $.ajax({
            method: "DELETE",
            url: "/user/session",
            data: {
                "id": session_id,
            }
        })
        .done(function(response) {
            document.cookie = "id=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            window.location.href = "/";
        });
    });

    $("#signup-button").click(function(){
        var name = $("#signup-name").val();
        var email = $("#signup-email").val();
        var password = $("#signup-password").val();

        $.ajax({
            method: "POST",
            url: "/user",
            data: JSON.stringify({
                "name": name,
                "email": email,
                "password": password
            }),
            contentType: "application/json"
        })
        .done(function(response) {
            window.location.href="/";
        })
        .fail(function(response) {
            alert("입력 정보를 확인해주세요.")
        });
    });

    $("#login-button").click(function(){
        var email = $("#login-email").val();
        var password = $("#login-password").val();

        $.ajax({
            method: "POST",
            url: "/user/login",
            data: JSON.stringify({
                "email": email,
                "password": password
            }),
            contentType: "application/json"
        })
        .done(function(response) {
            window.location.href="/";
        })
        .fail(function(response) {
            alert("입력 정보를 확인해주세요.")
        });
    });

    $("#more").click(function(){
        var next_page = parseInt($(this).attr("current-page")) + 1;

        $.ajax({
            method: "GET",
            url: "/post",
            data: {"page": next_page}
        })
        .done(function(response) {
            for(var post of response) {
                $("#more-posts").append("<div class=\"post-preview\">" +
                    "<a href=\"/post/" + post.id + "\">" +
                    "<h2 class=\"post-title\">" +
                    post.title +
                    "</h2>\n" +
                    "<h3 class=\"post-subtitle\">" +
                    post.content +
                    "</h3></a><p class=\"post-meta\">Posted by " +
                    post.name +
                    "</p></div><hr class=\"my-4\" />");
            }
        });
        $(this).attr("current-page", next_page);
    });

    $("#create_button").click(function(){
        var session_id = $.cookie('id');
        $.ajax({
            method: "GET",
            url: "/user/session",
            data: {"id": session_id }
        }).done(function(response) {
            console.log(response);
        });

        var title = $("#post-title").val();
        var username = $("#post-username").val();
        var content = $("#post-content").val();

        $.ajax({
            method: "POST",
            url: "/post",
            data: JSON.stringify({
                "title": title,
                "username": username,
                "content": content
            }),
            contentType: "application/json"
        })
        .done(function(response) {
            console.log("Post creation success!");
            window.location.href = "/";
        });
    });

    $("#edit_button").click(function(){
        var id = $("#edit-post-id").val();
        var title = $("#edit-post-title").val();
        var content = $("#edit-post-content").val();

        $.ajax({
            method: "PUT",
            url: "/post",
            data: JSON.stringify({
                "id": id,
                "title": title,
                "content": content
            }),
            contentType: "application/json"
        })
        .done(function(response) {
            console.log("Post creation success!");
            window.location.href = "/post/" + id;
        });
    });

    $("#more-comment-button").click(function(){
        var next_page = parseInt($(this).attr("current-comment-page")) + 1;
        var post_id = parseInt($("#post-id").val());
        $.ajax({
            method: "GET",
            url: "/comment",
            data: {
                "page": next_page,
                "post_id": post_id
            }
        })
        .done(function(response) {
            for(var comment of response) {
                $("#more-comments").append("<div class=\"comment_text\"><div class=\"etc\">" +
                    "<div class=\"name\">" +
                    comment.username +
                    "</div></div><p>" +
                    comment.content +
                    "</p><div class=\"edit_btns\">" +
                    "<button class=\"comment-edit-form-button\">수정</button>" +
                    "<button class=\"comment-delete-button\">삭제</button></div>" +
                    "<textarea class=\"edit comment-edit\" name=\"\" id=\"edit2\" cols=\"30\" rows=\"10\" placeholder=\"댓글을 입력해주세요\">" +
                    comment.content +
                    "</textarea><div class=\"save_btns comment-edit\">" +
                    "<button class=\"comment-edit-cancel-button\">취소</button>" +
                    "<button class=\"save comment-edit-button\">저장하기</button></div>" +
                    "<input type=\"hidden\" class=\"comment-id\" value=\"" +
                    comment.id +
                    "\"></div>\"");
            }

            $(".comment-edit").hide();
        });
        $(this).attr("current-comment-page", next_page);
    });

    $("#comment-save-button").click(function(){
        var username = $("#comment-username").val();
        var content = $("#comment-content").val();
        var post_id = $("#post-id").val();

        $.ajax({
            method: "POST",
            url: "/comment",
            data: JSON.stringify({
                "postId": post_id,
                "username": username,
                "content": content
            }),
            contentType: "application/json"
        })
        .done(function(response) {
            window.location.reload();
        });
    });

    $(document).on("click",".comment-edit-button",function(){
        var id = $(this).parent().parent().children(".comment-id").val();
        var content = $(this).parent().parent().children(".comment-edit").val();

        console.log("id = " + id);
        console.log("content = " + content);
        $.ajax({
            method: "PUT",
            url: "/comment",
            data: JSON.stringify({
                "id": id,
                "content": content
            }),
            contentType: "application/json"
        })
        .done(function(response) {
            window.location.reload();
        });
    });

    $(document).on("click",".comment-edit-form-button",function(){
        $(this).closest(".comment_text").find(".comment-edit").show();
    });

    $(".comment-edit").hide();

    $(document).on("click",".comment-edit-cancel-button",function(){
        $(this).closest(".comment_text").find(".comment-edit").hide();
    });

    $(document).on("click",".comment-delete-button",function(){
        var id = $(this).parent().parent().children(".comment-id").val();

        console.log("id = " + id);
        $.ajax({
            method: "DELETE",
            url: "/comment",
            data: {
                "id": id,
            }
        })
        .done(function(response) {
            window.location.reload();
        });
    });
});