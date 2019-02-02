/* Created by RyanHarper on 12/8/16.*/
(function() {
    "use strict";

    var simonSavedMoves = []; // empty array for Simon.
    var playerSavedMoves = []; // empty array for Player.
    var squares = $(".square"); // JQuery searches for ".square" and registers it as an array of div classes, 0, 1, 2, 3.
    var mySelectedSquare; // will be used to represent the square I select.
    var level = 1; // starts the game off at round 1.
    var enableClicks = false; // gateway for enabling clicks, yes or no.
    var highScoreHeader = document.getElementById('highScoreHeader');

    // ===================================== GENERATE RANDOM NUMBER FUNCTION ===========================================

    var generateRandomSquare = function () {
        return squares[Math.floor(Math.random() * 4)]; // generates a random number between 0 and 3 corresponding to a square.
    };

    // ====================================== PUSH'S A SQUARE TO SIMON'S SAVED MOVES ===================================

    function assignSquare(square) {
        simonSavedMoves.push(square);
        fadeSquare(square);
    }

    // ======================================= FADING ANIMATION ========================================================

    function fadeSquare(square) {
        $(square).fadeTo(200, 1);
        $(square).fadeTo(200, 0.6);
    }

    function fadeSquareHardMode(square) {
        $(square).fadeTo(125, 1);
        $(square).fadeTo(125, 0.6);
    }

    // ======================== CLICKING SQUARES, SAVING SELECTED SQUARE TO PLAYER MOVES, AND COMPARING  ===============

    $(".square").click(function () {
        if(enableClicks == true) {
            mySelectedSquare = this; // when I click on a square it will read This square.
            playerSavedMoves.push(mySelectedSquare); // it takes that square and pushes it to the array to be used later to compare
            compareArrays();
        }
    });

    // ============= COMPARE ARRAYS ============================

    var i = 0;
    function compareArrays() {
        if(playerSavedMoves[i] == simonSavedMoves[i]) { // if the indices of the array's match
            fadeSquare(mySelectedSquare); // fade my square
            i++; // then go on to the next index;
            if (playerSavedMoves.length == simonSavedMoves.length) { // keep going until the length of array's match
                wonTheRound();
            }
        } else {
            lostTheRound();
        }
    }

    // ================WINNING OR LOSING THE ROUNDS ============

    function wonTheRound() {
        i = 0;
        setTimeout(updateLevelCounter, 750);
        levelUp();
        enableClicks = false;
        setTimeout(sequenceFades, 750);
        playerSavedMoves = [];
    }

    function lostTheRound() {
        $('.square').addClass('animated wobble');
        if (select == "hard") {
            $('.square').addClass('losingBackground');
        }
        $('#play').addClass('animated infinite pulse');
        resetLevel();
        updateLevelCounter();
        resetArrays();
    }

    // ==================================== NEXT SEQUENCE AND FADING SPEED =============================================
    // this gives Simon different timeouts for each subsequent square, in a way "waiting" for the next square to finish fading.

    function sequenceFades() {

        simonSavedMoves.forEach(function(square, index) {
            if (select == "hard") {
                setTimeout(function () { // Hard Mode
                    fadeSquareHardMode(square);
                }, (300 * index));
                if (index == simonSavedMoves.length - 1) {
                    setTimeout(function() {
                        assignSquare(generateRandomSquare());
                        enableClicks = true;
                    }, 300 * index + 300);
                    setTimeout(function() {
                        assignSquare(generateRandomSquare());
                        enableClicks = true;
                    }, 300 * index + 600);
                }
            } else {
                setTimeout(function () { // Normal Mode
                    fadeSquare(square);
                }, (500 * index));
                if(index == simonSavedMoves.length - 1) {
                    setTimeout(function() {
                        assignSquare(generateRandomSquare());
                        enableClicks = true;
                    }, 500 * index + 500);
                }
            }
        });
        updateHighScore();
    }

    //================================== LEVEL UP  ======  RESET LEVEL =================================================

    function levelUp() {
        level++;
    }

    function updateLevelCounter() {
        document.getElementById("level").innerHTML = "Round: " + level;
    }

    function resetLevel() {
        level = 1;
    }

    function resetArrays() {
        playerSavedMoves = [];
        simonSavedMoves = [];
    }

    // =================================== DIFFERENT GAME MODES ========================================================

    var select;
    function gameModes() {
        select = $("select").val();
        if (select == "hard") {
            resetLevel();
            $('.square').attr("id", "hardBackground");
        } else {
            $('.square').removeAttr("id", "hardBackground");
        }
    }

    function switchGameModes() {
        $('.square').removeClass('losingBackground animated wobble');
        setTimeout(function(){
        }, 100);
        $('#play').removeClass('animated infinite pulse');
        setTimeout(function(){
            assignSquare(generateRandomSquare())
        }, 200);
    }

    var userHighScore = 1; // user high score starts at 1.
    function updateHighScore() {
        if (userHighScore <= level) {
            userHighScore = level;
            highScoreHeader.innerHTML = "High Score: " + userHighScore;
        } else if (userHighScore > level) {
            highScoreHeader.innerHTML = "High Score: " + userHighScore;
        }
    }
    updateHighScore();

    // ===================================== PLAY BUTTON TO START GAME =================================================

    $("#play").click(function  () {
        alert("Simon's Turn!");
        enableClicks = true;
        resetArrays();
        resetLevel();
        gameModes();
        updateLevelCounter();
        switchGameModes();
    });

    $("#form1").submit(function(e){
        e.preventDefault();
    })

})();

// } else if (select == "getWeird") {
//
//
//     $('.red').animate({
//         left: "+=210"
//     },1000).animate({
//         top: "+=210"
//     },1000).animate({
//         left: "-=210"
//     },1000).animate({
//         top: "-=210"
//     });
//
//     $('.green').animate({
//         top: "+=210"
//     },1000).animate({
//         left: "-=210"
//     },1000).animate({
//         top: "-=210"
//     },1000).animate({
//         left: "+=210"
//     });
//
//     $('.yellow').animate({
//         left: "-=210"
//     },1000).animate({
//         top: "-=210"
//     },1000).animate({
//         left: "+=210"
//     },1000).animate({
//         top: "+=210"
//     });
//
//     $('.blue').animate({
//         top: "-=210"
//     },1000).animate({
//         left: "+=210"
//     },1000).animate({
//         top: "+=210"
//     },1000).animate({
//         left: "-=210"
// //     })
//
