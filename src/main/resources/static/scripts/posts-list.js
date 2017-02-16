/**
 * Created by RyanHarper on 2/15/17.
 */
(function() {

    var request = $.ajax({
        url: '/posts.json'
    });

    request.done(function (posts) { // the http response-> an array of JSON objects -> posts
        console.log(posts);

        var i, html = '';
        for(i = 0; i < posts.length; i++) {
            html += '<div>' +
                '<h2 style="text-align: center">' + posts[i].title + '</h2>' +
                '<h3 style="text-align: left">' + posts[i].body + '</h3>' +

                // '<img src="/uploads/' + posts[i].image + '" alt="No image"/>' +

                '<a class="btn btn-default text-align: center" href="/posts/' + posts[i].id + '">Show</a>' +
                '<h4 style="text-align: left">Created by: <a href="/users/' + posts[i].user.id + '">'
                + posts[i].user.username + '</a></h4>' +
                '</div>';
        }

        $('#load-posts').html(html);

    });

    request.fail(function (e) {
        alert(e);
    })

})();
