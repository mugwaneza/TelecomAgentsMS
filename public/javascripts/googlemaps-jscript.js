
$('#plot_marker').click(function () {

//        var ioId = document.getElementById('namesId').value;
//        alert(ioId);

    $.post('/dashboard/names/DateString', $('mapform').serialize(), function (data) {

            //success
        },
        'json' // I expect a JSON response
    );
});


$('#find_calender').click(function () {

//            var ioId = document.getElementById('calendar').value;
//        alert(ioId);
$.post('/all/month', $('calendar_forrm').serialize(), function (data) {

            //success
        },
        'json' // I expect a JSON response
    );
});


//
//  $('#names').change(function(){
//
//    $.post('/fuel/driver', $('#fuelForm').serialize(), function (data) {
//         // success
//         },
//        'json' // I expect a JSON response
//    );
//
//  });



//var latidudes_from_db = Array.prototype.map.call(
//    document.querySelectorAll(".a"),
//    function(input) {
//        return input.value;
//    }
//);
//var longitudes_from_db = Array.prototype.map.call(
//    document.querySelectorAll(".b"),
//    function(input) {
//        return input.value;
//    }
//);


// Get all coordinates from interface and pass it to an array
var latidudes_from_db = Array.from(document.querySelectorAll(".a"), input => input.value);
var longitudes_from_db = Array.from(document.querySelectorAll(".b"), input => input.value);

strings = latidudes_from_db.map((lat, i) => [lat, longitudes_from_db[i]].join());


//console.log(strings.join('\n'));
//
////alert(strings);
//
var newArray = JSON.parse("[" + strings + "]");
//
//console.log("markers", markers);

var markers = [];
var obj = {};

newArray.map(function(marker, index){
    if (index % 2 == 0) {
        obj.latitude = marker
    } else {
        obj.longitude = marker
    }

    if (Object.keys(obj).length == 2) {
        markers.push(obj);
        obj = {}
    }
})
console.log(markers)


window.onload = function () {
    var mapOptions = {
        center: new google.maps.LatLng(-1.935114, 30.082111), //set map center
        zoom: 17, //set zoom level to 17
        mapTypeId: google.maps.MapTypeId.ROADMAP //set map type to road map
    };

    var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
    var infoWindow = new google.maps.InfoWindow();

//    map.setMap(map); //set the map to be used by marker
//    map.setPosition(map.getCenter()); //set position of marker equal to the current center of the map
    map.setZoom(17);


    var lat_lng = new Array();
    var latlngbounds = new google.maps.LatLngBounds();
    for (i = 0; i < markers.length; i++) {
        var data = markers[i]
        var mapcontainer = document.getElementById('map_container');

        var myLatlng = new google.maps.LatLng(data.latitude, data.longitude);
        lat_lng.push(myLatlng);
        var marker = new google.maps.Marker({
            position: myLatlng,
            map: map,
            title: data.title
        });
        latlngbounds.extend(marker.position);
        (function (marker, data) {
            google.maps.event.addListener(marker, "click", function (e) {
                infoWindow.setContent(data.description);
                infoWindow.open(map, marker);
            });
        })(marker, data);
    }
    map.setCenter(latlngbounds.getCenter());
    map.fitBounds(latlngbounds);

    //***********ROUTING****************//

    //Initialize the Path Array
    var path = new google.maps.MVCArray();

    //Initialize the Direction Service
    var service = new google.maps.DirectionsService();

    //Set the Path Stroke Color
    var poly = new google.maps.Polyline({ map: map, strokeColor: '#4986E7' });

    //Loop and Draw Path Route between the Points on MAP
    for (var i = 0; i < lat_lng.length; i++) {
        if ((i + 1) < lat_lng.length) {
            var src = lat_lng[i];
            var des = lat_lng[i + 1];
            path.push(src);
            poly.setPath(path);
            service.route({
                origin: src,
                destination: des,
                travelMode: google.maps.DirectionsTravelMode.DRIVING
            }, function (result, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    for (var i = 0, len = result.routes[0].overview_path.length; i < len; i++) {
                        path.push(result.routes[0].overview_path[i]);
                    }
                }
            });
        }
    }
}