/*
 Constructor for the tooltip
 @ param options an object containing: marker(required), content(required) and cssClass(a css class, optional)
 @ see google.maps.OverlayView()
 */



function validaColorcito(parametro, variable) {

    if (parametro == 4) { // si es direccion del viento
        if (variable >= 338 & variable >= 360 || variable >= 0 & variable < 23) {
            var color = 'ico_vien000.png';
        } else if (variable >= 23 & variable < 68) {
            var color = 'ico_vien045.png';
        } else if (variable >= 67 & variable < 113) {
            var color = 'ico_vien090.png';
        } else if (variable >= 113 & variable < 158) {
            var color = 'ico_vien135.png';
        } else if (variable >= 158 & variable < 203) {
            var color = 'ico_vien180.png';
        } else if (variable >= 203 & variable < 248) {
            var color = 'ico_vien225.png';
        } else if (variable >= 248 & variable < 293) {
            var color = 'ico_vien270.png';
        } else if (variable >= 293 & variable < 338) {
            var color = 'ico_vien315.png';
        } else {
            var icoColor = 'ico_SD.png';
        }
    } else if (parametro == 1) {
/*	14/10/2015	
        if (variable > -1 && variable < 25)
            var color = 'ico_pp_00.png';
        else if (variable >= 25 && variable < 50)
            var color = 'ico_pp_25.png';
        else if (variable >= 50 && variable < 75)
            var color = 'ico_pp_50.png';
        else if (variable >= 75 && variable < 100)
            var color = 'ico_pp_75.png';
        else if (variable >= 100 && variable < 150)
            var color = 'ico_pp_100.png';
        else if (variable >= 150 && variable < 200)
            var color = 'ico_pp_150.png';
        else if (variable >= 200 && variable < 250)
            var color = 'ico_pp_200.png';
        else if (variable >= 250 && variable < 299)
            var color = 'ico_pp_250.png';
        else if (variable >= 300)
            var color = 'ico_pp_300.png';
        else
            var color = 'ico_SD.png';
			
			14/10/2015
*/		

	
        if (variable > -1 && variable < 1)
            var color = 'ico_pp_00.png';
        else if (variable >= 2 && variable < 10)
//            var color = 'ico_pp_25.png';
			var color = 'ico_pp_300.png';
        else if (variable >= 11 && variable < 20)
            var color = 'ico_pp_50.png';
        else if (variable >= 21 && variable < 30)
            var color = 'ico_pp_75.png';
        else if (variable >= 31 && variable < 40)
            var color = 'ico_pp_100.png';
        else if (variable >= 41 && variable < 50)
            var color = 'ico_pp_150.png';
        else if (variable >= 51 && variable < 60)
            var color = 'ico_pp_200.png';
        else if (variable >= 61 && variable < 70)
            var color = 'ico_pp_250.png';
        else if (variable >= 71)
            var color = 'ico_pp_300.png';
        else
            var color = 'ico_SD.png';			
    }
	
	 /*else if (parametro == 1) {
        if (variable > -1 && variable < 60)
            var color = 'ico-14.png';
        else if (variable > 59 && variable < 120)
            var color = 'ico-08.png';
        else if (variable > 119 && variable < 180)
            var color = 'ico-06.png';
        else if (variable > 179 && variable < 240)
            var color = 'ico04.png';
        else if (variable > 239 && variable < 300)
            var color = 'ico08.png';
        else if (variable > 299 && variable < 360)
            var color = 'ico12.png';
        else if (variable > 359 && variable < 420)
            var color = 'ico16.png';
        else if (variable > 419 && variable < 480)
            var color = 'ico18.png';
        else if (variable > 479 && variable < 540)
            var color = 'ico20.png';
        else if (variable > 539 && variable < 600)
            var color = 'ico24.png';
        else if (variable > 599)
            var color = 'ico30.png';
        else
            var color = 'ico_SD.png';
    }*/ else {
        if (variable > -24 && variable < -22)
            var color = 'ico-22.png';
        else if (variable > -22 && variable < -20)
            var color = 'ico-20.png';
        else if (variable > -20 && variable < -18)
            var color = 'ico-18.png';
        else if (variable > -18 && variable < -16)
            var color = 'ico-16.png';
        else if (variable > -16 && variable < -14)
            var color = 'ico-14.png';
        else if (variable > -14 && variable < -12)
            var color = 'ico-12.png';
        else if (variable > -12 && variable < -10)
            var color = 'ico-10.png';
        else if (variable > -10 && variable < -8)
            var color = 'ico-08.png';
        else if (variable > -8 && variable < -6)
            var color = 'ico-06.png';
        else if (variable > -6 && variable < -4)
            var color = 'ico-04.png';
        else if (variable > -4 && variable < -2)
            var color = 'ico-04.png';
        else if (variable > -2 && variable <= 0)
            var color = 'ico-02.png';
        else if (variable > 0 && variable < 2)
            var color = 'ico02.png';
        else if (variable > 2 && variable < 4)
            var color = 'ico04.png';
        else if (variable > 4 && variable < 6)
            var color = 'ico06.png';
        else if (variable > 6 && variable < 8)
            var color = 'ico08.png';
        else if (variable > 8 && variable < 10)
            var color = 'ico10.png';
        else if (variable > 10 && variable < 12)
            var color = 'ico12.png';
        else if (variable > 12 && variable < 14)
            var color = 'ico14.png';
        else if (variable > 14 && variable < 16)
            var color = 'ico16.png';
        else if (variable > 16 && variable < 18)
            var color = 'ico18.png';
        else if (variable > 18 && variable < 20)
            var color = 'ico20.png';
        else if (variable > 20 && variable < 22)
            var color = 'ico22.png';
        else if (variable > 22 && variable < 24)
            var color = 'ico24.png';
        else if (variable > 24 && variable < 26)
            var color = 'ico26.png';
        else if (variable > 26 && variable < 28)
            var color = 'ico28.png';
        else if (variable > 28 && variable < 30)
            var color = 'ico30.png';
        else if (variable > 30 && variable < 32)
            var color = 'ico32.png';
        else if (variable > 32 && variable < 100)
            var color = 'ico34.png';
        else
            var color = 'ico_SD.png';
    }


    return color;
}






function Tooltip(options) {

    // Now initialize all properties.
    this.marker_ = options.marker;
    this.content_ = options.content;
    this.map_ = options.marker.get('map');
    this.cssClass_ = options.cssClass || null;

    // We define a property to hold the content's
    // div. We'll actually create this div
    // upon receipt of the add() method so we'll
    // leave it null for now.
    this.div_ = null;

    //Explicitly call setMap on this overlay
    this.setMap(this.map_);
    var me = this;
    // Show tooltip on mouseover event.
    google.maps.event.addListener(me.marker_, 'mouseover', function () {
        me.show();
    });
    // Hide tooltip on mouseout event.
    google.maps.event.addListener(me.marker_, 'mouseout', function () {
        me.hide();
    });
}
// Now we extend google.maps.OverlayView()
Tooltip.prototype = new google.maps.OverlayView();

// onAdd is one of the functions that we must implement, 
// it will be called when the map is ready for the overlay to be attached.
Tooltip.prototype.onAdd = function () {

    // Create the DIV and set some basic attributes.
    var div = document.createElement('DIV');
    div.style.position = "absolute";
    // Hide tooltip
    div.style.visibility = "hidden";
    if (this.cssClass_)
        div.className += " " + this.cssClass_;

    //Attach content to the DIV.
    div.innerHTML = this.content_;

    // Set the overlay's div_ property to this DIV
    this.div_ = div;

    // We add an overlay to a map via one of the map's panes.
    // We'll add this overlay to the floatPane pane.
    var panes = this.getPanes();
    panes.floatPane.appendChild(this.div_);

}
// We here implement draw
Tooltip.prototype.draw = function () {

    // Position the overlay. We use the position of the marker
    // to peg it to the correct position, just northeast of the marker.
    // We need to retrieve the projection from this overlay to do this.
    var overlayProjection = this.getProjection();

    // Retrieve the coordinates of the marker
    // in latlngs and convert them to pixels coordinates.
    // We'll use these coordinates to place the DIV.
    var ne = overlayProjection.fromLatLngToDivPixel(this.marker_.getPosition());

    // Position the DIV.
    var div = this.div_;
    div.style.left = ne.x + 'px';
    div.style.top = ne.y + 'px';

}
// We here implement onRemove
Tooltip.prototype.onRemove = function () {
    this.div_.parentNode.removeChild(this.div_);
}

// Note that the visibility property must be a string enclosed in quotes
Tooltip.prototype.hide = function () {
    if (this.div_) {
        this.div_.style.visibility = "hidden";
    }
}

Tooltip.prototype.show = function () {
    if (this.div_) {
        this.div_.style.visibility = "visible";
    }
}