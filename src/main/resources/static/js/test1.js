
//(function runFast() {
//	console.log('Running now.');
//}) ();

function h2clicked() {
	//const e = document.getElementById('myspan');
	//e.textContent = 'Safe <b>Bold 1</b>';  // Safe, escaped
	//e.innerText = 'Safe <b>Bold 2</b>'; // Safe, escaped
	//e.innerHTML = 'Safe <b>Bold 3</b>'; // not safe - not escaped
	
	const e = $('#myspan');
	e.text('Safe <b>Bold 4</b>'); // Safe, escaped
	e.html('Safe <b>Bold 5</b>'); // not safe - not escaped
}

$('document').ready(function() {
	console.log('Document ready');
});

//$('h2#h2title9').on('click', h2clicked);

$('#btnTest1').on('click', btnTest1);

//var t1 = document.getElementById('h2titleJS');
//t1.addEventListener('click', h2clicked);

var a1 = ['a','bb','ccc'];
var a2 = new Array('a','bb','ccc)');

var ob1 = {
	c:'red',
	n:22
};
Object.freeze(ob1);

function countNumLetters() {
	const str = arguments[0];
	const letters = str.split("");
	let lettersCount = {};
	
	for (let a of letters) {
		if (lettersCount[a] == undefined) {
			lettersCount[a] = 1;
		} else {
			lettersCount[a]++;
		}
	}
	for (let key in lettersCount) {
		console.log(key + ':' + lettersCount[key]);
	}
}

function btnTest1() {
	//test1();
	//test2();
	//test3();
	//test4();
	//test5();
	//test6('p1', 'p2', 'p3');
	test7();
}

function test7() {
	let a1 = [ 1,2,3 ];
	let a2 = [ 7,8,9 ];
	
	//Array.prototype.push.apply(a1,a2);
	[].push.apply(a1,a2);
	
	console.log('a1=' + a1);
}

function test6() {
	const a1 = Array.prototype.slice.call(arguments);
	const a2 = Array.from(arguments);
	const a3 = [...arguments];
	
	console.log('a1=' + a1); // a1=p1,p2,p3
	console.log('a2=' + a2); // a2=p1,p2,p3
	console.log('a3=' + a3); // a3=p1,p2,p3
}

function test1() {
	countNumLetters('abcdbcdcdd');
	// Add new max method to Array
	Array.prototype.max = function() {
		return Math.max.apply(Math,this);
	}
	console.log([2,12,11,10,1,4].max());
	
	// Shuffle an array
	var names = ['a','b','c','d','e','f'];
	console.log(names);
	names.sort(function() {return Math.random()-.5});
	console.log(names);
	
	console.log('id=' + $(this).attr('id') + ', a1[1]=' + a1[1] + ', a1.slice(1,2)=' + a1.slice(1,2));
	console.log('ob1.c=' + ob1.c + ', ob1.n=' + ob1.n);
	// var is function scope
	// let and const are block scope
	
	// console.log('neverDefined=' + neverDefined);
	// Uncaught ReferenceError: neverDefined is not defined
	
	console.log('bottomDefined=' + bottomDefined); 
	// bottomDefined=undefined
	
	// console.log('letDefined=' + letDefined);     
	// Uncaught ReferenceError: letDefined is not defined
	
	let letDefined = 234;
	var bottomDefined = 123;
	
	{
		let tempDefined = 456;
		console.log('tempDefined=' + tempDefined); // 456
	}
	// console.log('tempDefined=' + tempDefined); 
	// Uncaught ReferenceError: tempDefined is not defined
	
	{
		const constDefined = 567;
		console.log('constDefined=' + constDefined); // 567
	}
	// console.log('constDefined=' + constDefined); 
	// Uncaught ReferenceError: constDefined is not defined
}

function test2() {
	const names = ['Tom','Tommy','Thomas'];
	names.forEach(console.log);
}

function test3() {
	const names = [ 'Tom', 'Tommy', 'Thomas' ];
	console.log(names.join(','));
	console.log(names.join('|'));
	console.log(names.join(' '));
}

function test4() {
	const names = [ 'a', 'b', 'c', 'd', 'e', 'f' ];
	console.log(names[ Math.floor(Math.random()*names.length) ] );
	console.log(names[ Math.floor(Math.random()*names.length) ] );
	console.log(names[ Math.floor(Math.random()*names.length) ] );
}

function Student(first,last) {
	this.first = first;
	this.last = last;
}

function test5() {
	const student = new Student('Tom', 'Suliga');
	console.log(JSON.stringify(student));
	
	console.log('a ' + isNumber('a'));
	console.log('3 ' + isNumber('3'));
	console.log('3.7 ' + isNumber('3.7'));
	console.log('a1 ' + isNumber('a1'));
	console.log('1a ' + isNumber('1a'));
}

function isNumber(num) {
	return !isNaN(parseFloat(num));
}






