/*
    Example of a multi-line comment just like in C#/Java
*/

// Single line comment

/**
 * Functions start with the word function.
 * They don't have a return type and the naming convention is camel-case.
 */
function variables() {
  // Declares a variable where the value cannot be changed
  const DAYS_PER_WEEK = 7; // const (similar to FINAL in Java) - value cannot be changed
  // console.log is the JavaScript version of System.out.println() in Java
  // If what you want to display using console.log contains a variable: 
  //    1. Enclose what you want to display in back-ticks (`)
  //    2. Put the variable you want displayed inside ${}
  //    
  // semi-colons at the end of a statement are usually optional in JavaScript
  console.log(`There are ${DAYS_PER_WEEK} days in a week`);

  // Declares a variable whose value can be changed
  let daysPerMonth = 31; // let - value may be changed
  console.log(`There are ${daysPerMonth} days this month`);

  // Declares a variable that will always be an array
  // To declare an array in JavaScript, code the name = []
  const WEEKDAYS = [
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday"
  ];
  // console.table displays an array as a table with the indexes and values
  console.table(WEEKDAYS);
}

/**
 * Functions can also accept parameters.
 * Notice the parameters do not have types.
 * 
 * JavaDoc is a standard way of documenting code.
 * JavaDoc is comments placed before a function to describe it
 * 
 * @words - annotations in JavaDoc to describe parts of a function
 * @param - identifies parameters passed to the function in JavaDoc notation
 * 
 * @param {Number} param1 The first number to display
 * @param {Number} param2 The second number to display
 */
function printParameters(param1, param2) {
  console.log(`The value of param1 is ${param1}`);
  console.log(`The value of param2 is ${param2}`);
}

/**
 * JavaScript has two different types of equality
 * 
 * Compares two values x and y.
 * == is loose equality - ignores the type of data and looks only at the value
 * === is strict equality - requires both the type and value to be equal
 * 
 * 1 ==  '1' - true because the values both represent 1
 * 1 === '1' - false because they are not the same type (number and string)
 * 
 * @param {Object} x
 * @param {Object} y
 */
function equality(x, y) {
  console.log(`x is ${typeof x}`); // typeof returns the type of the value it is given
  console.log(`y is ${typeof y}`);

  console.log(`x == y : ${x == y}`); // true
  console.log(`x === y : ${x === y}`); // false
}

/**
 * Each value is inherently truthy or falsy
 * Every value in JavaScript can be evaluated to true or false when used in a conditional statement
 * 
 * false, 0, '', null, undefined, and NaN (Not a Number) are always falsy
 * everything else is always truthy
 * 
 * @param {Object} x The object to check for truthy or falsy,
 */
function falsy(x) {
  if (x) { // Use whatever is passed as a condition in an if statement
    console.log(`${x} is truthy`);
  } else {
    console.log(`${x} is falsy`);
  }
}

/**
 *  Objects are simple key-value pairs (NOT instance of a class like in Java)
 * JSON objects - JavaScript Object Notation
 * 
 * an element in a JavaSctipt object - attribute name : value
 * 
    - values can be primitive data types
    - values can be arrays
    - or they can be functions
*/
function objects() {
  const person = {           // attributes of a JavaScript object are enclosed in braces, just like JSON
    firstName: "Bill",       // code a comma (,) between elements
    lastName: "Lumbergh",
    age: 42,
    employees: [             // an attribute of a JavaScript object may be an array
      "Peter Gibbons",
      "Milton Waddams",
      "Samir Nagheenanajar",
      "Michael Bolton"
    ],
    // define a toString() function for the object
    toString : function() { // identify the attribute as type function()
      return `${this.lastName}, ${this.firstName} (${this.age})` // this. references the current object, like in Java
    }
  }

  // Log the object
  console.table(person);
  console.log(person);

  // Log the first and last name
  // to reference an attribute in an object: objectName.attributeName
  console.log(`${person.firstName} ${person.lastName}`);

  // Log each employee
  // an employee is an element of an array in the person object
  // to process each employee we need to loop through that array
  for (let i=0; i < person.employees.length; i++) {  // same syntax as Java, but using let instead of int
      console.log(`Employee #${i+1}: ${person.employees[i]}`)
  }

   
  // We just want to get the person's name and age when we reference the object
  // Now we get all the data in person displayed when we reference it
  //
  // In Java we would define a toString() for the class to control what data was given for an object
  // We do the same thing in JavaScript - define a toString() for the object
  console.log(person.toString()); // call a function defined in the object using the object name
}
/*
########################
Function Overloading
########################

Function Overloading is not available in Javascript. If you declare a
function with the same name, more than one time in a script file, the
earlier ones are replaced and the most recent one will be used.
*/

function Add(num1, num2) {
  return num1 + num2;
}

// This version of Add replaces the earlier version
function Add(num1, num2, num3) {
  return num1 + num2 + num3;
}

/*
########################
Math Library
########################

A built-in `Math` object has properties and methods for mathematical constants and functions.
*/

function mathFunctions() {
  console.log("Math.PI : " + Math.PI);
  console.log("Math.LOG10E : " + Math.LOG10E);
  console.log("Math.abs(-10) : " + Math.abs(-10));
  console.log("Math.floor(1.99) : " + Math.floor(1.99));
  console.log("Math.ceil(1.01) : " + Math.ceil(1.01));
  console.log("Math.random() : " + Math.random());
}

/*
########################
String Methods
########################

The string data type has a lot of properties and methods similar to strings in Java/C#
*/

function stringFunctions(value) {
  console.log(`.length -  ${value.length}`);
  console.log(`.endsWith('World') - ${value.endsWith("World")}`);
  console.log(`.startsWith('Hello') - ${value.startsWith("Hello")}`);
  console.log(`.indexOf('Hello') - ${value.indexOf("Hello")}`);
  console.log(`.substr(2, 3) - ${value.substr(2, 3)}`);       // (start-index, length)    start at start-index, read for 3 characters 
  console.log(`.substring(2, 3) - ${value.substring(2, 3)}`); // (start-index, end-index) start at start-index, up to but not including the end-index
  
  /*
    Other Methods
        - split(string)
        - toLowerCase()
        - trim()
        - https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String
    */
}

/*
#########################
Array Processing Examples
#########################

JavaScript has a library of functions to make using arrays easier
*/
function arrayFunctions() {
  let stooges = [ // Define an array
    "Moe",
    "Larry",
    "Curly"
  ]
  console.table(stooges); // Display the array as a table

  stooges.push("Shemp"); // Add an element to the end of the array, similarly to ArrayLists
  console.table(stooges);

  stooges.unshift("Curly Joe"); // Add an element to the start of the array
  console.table(stooges);

  stooges.splice(3, 0, "Groucho", "Chico", "Harpo"); // Remove 0 elements, insert new elements at index 3
  console.table(stooges);

  stooges.splice(3, 1); // Remove the element at index 3 (index, #-of-elements-to-remove)
  console.table(stooges);

  stooges.splice(3, 2); // Remove 2 elements at index 3 (index, #-of-elements-to-remove)
  console.table(stooges);

  stooges.shift(); // Remove one element at the start of the array
  console.table(stooges);

  stooges.pop(); // Remove one element from the end of the array
  console.table(stooges);

  stooges.splice(stooges.indexOf("Larry"), 1); // Find index of "Larry" and remove the element at that index
  console.table(stooges);

  let marxBros = ["Groucho", "Chico", "Harpo"]; // Define a new array

  let oldFunnyGuys = stooges.concat(marxBros); // Combine the stooges array and marxBros array into one
  console.table(oldFunnyGuys);
}