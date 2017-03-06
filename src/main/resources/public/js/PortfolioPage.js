/**
 * Created by RyanHarper on 12/14/16.
 */
(function () {

    $(".sliding-link").click(function(e) {
        e.preventDefault();

        var aid = $(this).attr("href");
        setTimeout(function() {
            $('html,body').animate({scrollTop: $(aid).offset().top}, 1000)
        }, 100)
    });

    $(function(){
        // You used .myCarousel here.
        // That's the class selector not the id selector,
        // which is #myCarousel
        $('.carousel').carousel({
            interval: 5000
        });
    });

    $(window).scroll(function () {
        if ($(this).scrollTop() > 1000) {
            $('.scrollup').fadeIn();
        } else {
            $('.scrollup').fadeOut();
        }
    });

    $('.scrollup').click(function () {
        $("html, body").animate({
            scrollTop: 0
        }, 500);
        return false;
    });



})();

// <a href="#goto" class="sliding-link"> Link to div </a>
//
// <div id="goto"> I'm the div </div>