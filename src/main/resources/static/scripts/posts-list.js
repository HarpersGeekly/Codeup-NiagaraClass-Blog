/**
 * Created by RyanHarper on 2/15/17.
 */

// THIS IS FOR INDEX FUNCTIONALITY ( SHOWS ALL POSTS ON MAIN PAGE )

(function() {

    var request = $.ajax({
        url: '/posts.json'
    });

    request.done(function (posts) { // the http response-> an array of JSON objects -> posts
        console.log(posts);

        var i, html = '', $div = $("<div>");
        for(i = 0; i < posts.length; i++) {
            var escapedBody = $div.text(posts[i].body).html();
            var escapeTitle = $div.text(posts[i].title).html();
            html += '<div>' +
                '<h2 style="text-align: left">' + escapeTitle + '</h2>' +
                '<h3 style="text-align: left">' + escapedBody + '</h3>' +

                // '<img src="/uploads/' + posts[i].image + '" alt="No image"/>' +

                '<a class="btn btn-default" href="/posts/' + posts[i].id + '">Show</a>' +
                '<h4 style="text-align: left">Created by: <a href="/users/' + posts[i].user.id + '">'
                + posts[i].user.username + '</a></h4>' +
                '</div>';
        }

        html += '';
        $('#load-posts').html(html);

    });

    request.fail(function (e) {
        alert(e);
    })

})();
