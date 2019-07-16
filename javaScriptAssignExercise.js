



function C() {
	console.log("C");
}

function E(f) {
	console.log("E");	
	
	var f = F;
	//f();
}

var A = function() {
	console.log("A");
	
};

A();


var C;

function G() {
	console.log("G");
	

	var H = function() {
		console.log("H");
		I();
	};

	H();
}

var D = d;

function d() {
	console.log("D");
	E();
}



function I() {
	console.log("I");
	J();
	//J();
}

var B = function() {
	console.log("B");
	C();
};


B();
D();


var F = function() {
	console.log("F");
	G();
};

F();

function K2Z() {
var rest = "KLMNOPQRSTUVWXYZ".split("");
for (var i=0; i<rest.length; i++) {
	(function(i){
		// define the current function
			
			console.log(rest[i]);
			if (i < (rest.length-1)) {
				// TODO: call the next function\
			}
		
	})(i);
}
};

function J() {
	J = function() {
		console.log("J");
		K2Z();
	};
	J();
};

C = function() {
	console.log("C");
	D();
};