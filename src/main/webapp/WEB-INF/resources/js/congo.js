document.getElementsByClassName("duration").innerHTML = function (sec) {
	minutes = sec / 60;
	seconds = sec % 60;
	return minutes + "m" + seconds + "s";
);