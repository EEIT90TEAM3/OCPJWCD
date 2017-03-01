/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function sayHello(){
    alert("Hello");
}

function geolocation(){
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        alert("Geolocation is not supported by this browser.");
    }
}

function showPosition(position) {
   alert("你的位置: " + position.coords.latitude +
    ", " + position.coords.longitude);
}

function geocoder(){
    var urlPath = 'https://maps.googleapis.com/maps/api/geocode/json?address=' 
            + $("#address").val();
    
    $.ajax({
        url: urlPath        
    }).done(doneHandler);
    
}

function doneHandler(result){
    alert(result.results[0].formatted_address);
    alert(result.results[0].geometry.location.lat + "," + result.results[0].geometry.location.lng);
}