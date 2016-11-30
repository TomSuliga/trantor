
$('#btnTest1').on('click', btnTest1);

function btnTest1() {
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