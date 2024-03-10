<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title></title>

</head>

<body>
<button type="button" onclick="druck()">"Three Rings for the Elven-kings under the sky,
    Seven for the Dwarf-lords in their halls of stone,
    Nine for Mortal Men doomed to die,
    One for the Dark Lord on his dark throne
    In the Land of Mordor where the Shadows lie.
    One Ring to rule them all, One Ring to find them,
    One Ring to bring them all and in the darkness bind them
    In the Land of Mordor where the Shadows lie."</button>
<div id="ramen"></div>
<p id="ansage">..</p>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script>
    var ramen = document.getElementById("ramen");
    for (var l = 0; l < 3; l++) {
        for (var k = 0; k < 3; k++) {
            for (var i = 0; i < 3; i++) {
                for (var j = 0; j < 3; j++) {
                    ramen.innerHTML += '<button type="button" id="' + (i + l * 3 + 1) + ' ' + (j + k * 3 + 1) + '" onclick="input(' + (i + l * 3 + 1) + ',' + (j + k * 3 + 1) + ')" style="padding:20px">_</button>'
                }
                ramen.innerHTML += '<span style="margin:10px"></span>';
            }
            ramen.innerHTML += "<br>";
        }
        ramen.innerHTML += "<br>";

    }
    var spieler=false;
    var zug;
    websocket = new WebSocket("ws://localhost:8888/test");

    function druck() {
        console.log("Druck");
        websocket = new WebSocket("ws://localhost:8888/test");
        websocket.onopen = (event) => {
            websocket.send("Smeagol");
            console.log("gesenddet");
        };
        websocket.onmessage = function (event) {
            console.log("Received message: ", event.data);
            // You can process the message received from the server here
        };

        console.log("fertig");
    }
    websocket.onmessage = function (event) {
        console.log("Received message: ", event.data);
        if(parseInt(event.data)>1){
             var a=document.getElementById(zug);
             a.innerHTML=spieler?"0":"x";
             if(spieler){
                console.log("player 2 won (0)");
                document.getElementById("ansage").innerHTML="player 2 won (0)";
             }else{
                console.log("player 1 won (x)");
                document.getElementById("ansage").innerHTML="player 1 won (x)";
             }
             console.log("we have a winner: player",event.data-2,spieler);
        }else{
        if(event.data!=(spieler?1:0)){
            var a=document.getElementById(zug);
            a.innerHTML=spieler?"0":"x";
            spieler=!spieler;
        }
        console.log(spieler+" spieler");
        }
        // You can process the message received from the server here
    };
    function input(a, b) {
        zug= a + " " + b;
        websocket.send(a + " " + b);
        console.log(a + " " + b);
    }
</script>
</body>

</html>