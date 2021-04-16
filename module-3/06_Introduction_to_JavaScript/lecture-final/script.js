/*
    Example of a multi-line comment just like in C#/Java
*/

// Single line comment just like C#/Java

/**
 * Functions start with the word function.
 * They don't have a return type and the naming convention is camel-case.
 */
function variables() {
  // Declares a variable where the value cannot be changed
  // Use const to define a variable whose value cannot be changed
  const DAYS_PER_WEEK = 7;  

  // console.log() is the JavaScript version of System.out.println() in Java
  // If what you want to display using console.log() contains a variable:
  //
  //      1. Enclose what you want to display in back-ticks (`)
  //      2. Put the variable you want displayed inside ${}
  //
  // semi-colons at the end of a statement are usually optional in JavaScript

  console.log(`There are ${DAYS_PER_WEEK} days in a week`);

  // Declares a variable those value can be changed
  // use the let statement to define a variable whose content maybe changed

  let daysPerMonth = 31;

  console.log(`There are ${daysPerMonth} days this month`)

  // Declares a variable that will always be an array
  // To declare an array in JavaScript code name = []

  const WEEKDAYS = [
                   "Monday",
                   "Tuesday",
                   "Wednesday",
                   "Thursday",
                   "Friday",
                   "Saturday",
                   "Sunday"
                  ]

    console.table(WEEKDAYS)  // Display the array as a table
}

/**
 * Functions can also accept parameters.
 * Notice the parameters do not have types.
 * 
 *  JavaDoc is a standard way of documenting code.
 *  JavaDoc is comments placed before a function to describe it
 *  @words - annotations in JavDoc to describes parts of a function
 * 
 *  @param - identifies parameters passed to the function in JavaDoc notation
 * 
 * @param {Number} param1 The first number to display
 * @param {Number} param2 The second number to display
 */
function printParameters(param1, param2) {
  console.log(`The value of param1 is ${param1}`);
  console.log(`The value of param2 is ${param2}`);
}

/**
 * JavaScript has TWO types of equality
 * 
 * Compares two values x and y.
 * ==  is loose equality  - ignores the type of data - only looks at value
 * === is strict equality - requires both the type and value be the same
 * 
 * 1 ==  '1' - true  - values represents teh value one
 * 1 === '1' - false - types don't match (Number and String)
 * 
 * @param {Object} x
 * @param {Object} y
 */
function equality(x, y) {
  console.log(`x is ${typeof x}`);  // typeof returns the type of the value it is given
  console.log(`y is ${typeof y}`);

  console.log(`x == y : ${x == y}`); // true
  console.log(`x === y : ${x === y}`); // false
}

/**
 * Each value is inherently truthy or falsy.
 * 
 * Every value in JavaScript can be evaluated to true or false 
 * when used in a conditional statement
 * 
 * NaN - Not-a-Number - usually the result of arithmetic with non-numeric data
 * 
 * false, 0, '', null, undefined, and NaN are always falsy (false)
 * everything else is always truthy (true)
 * 
 * @param {Object} x The object to check for truthy or falsy,
 */
function falsy(x) {
  if (x) {  // Use whatever is passed a the condition in an if statement
    console.log(`${x} is truthy/true`);
  } else {
    console.log(`${x} is falsy/false`);
  }
}

/**
 *  Objects are simple key-value pairs (NOT instance of a class like in Java)
    JavaScript objects are JSON objects - JavaScriptObjectNotation

    an element in a JavaScript object: 
          attribute-name : value

    - values can be primitive data types
    - values can be arrays
    - or they can be functions
*/
function objects() {
  const person = {         // attributes of a JavaScript object are enclosed in {}
    firstName: "Bill",     // code a comma (,) between elements
    lastName: "Lumbergh",
    age: 42,
    employees: [           // an attribute in a JavaScript object maybe an array
      "Peter Gibbons",
      "Milton Waddams",
      "Samir Nagheenanajar",
      "Michael Bolton"
    ],
    // define a called meatball function for the object to format some it's data
    meatball : function() {   // identify the attribute as type function()
      return `${this.lastName}, ${this.firstName} (${this.age})`  // this. reference the current object
    }
  };

  // Log the object
  console.log(person)
  console.table(person);

  // Log the first and last name
  // To reference an attribute in an object: objectName.attributeName
  console.log(`Person's name: ${person.firstName} ${person.lastName}`)

  // Log each employee
  // an employee is an element of an array in the person object
  // to process each employee we need to loop through that array
  // in Java we'd use a for loop
  // we do the same thing in JavaScript - syntax/format is the same as in Java

  for (let i=0; i < person.employees.length; i++) {
    console.log(`Employee #${i+1}: ${person.employees[i]}`)
  }

  // We just want to get the person's name and age when reference the object
  // Now we get all the data in person displayed when we reference it
  // 
  // In Java we would define a toString() for the class to control what data was given for an object
  // We do the same thing in JavaScript - define a toString() method for the object
  console.log(person.meatball());  // Call a function defined in the object  using the object name


}

/*
########################
Function Overloading
########################

Function Overloading is not available in Javascript.

If you declare a function with the same name, more than one time in a script file,
the earlier ones are overriden/replaced and the most recent one will be used.
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
They are all called: Math.xxxxx

A link to a summary of all of them will be put in the class Google Doc
*/

function mathFunctions() {
  console.log("Math.PI : " + Math.PI);
  console.log("Math.LOG10E : " + Math.LOG10E);
  console.log("Math.abs(-10) : " + Math.abs(-10));
  console.log("Math.floor(1.99) : " + Math.floor(1.99));  // Round down to an integer value
  console.log("Math.ceil(1.01) : " + Math.ceil(1.01));    // Round up to an integer value
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
  console.log(`.indexOf('Hello') - ${value.indexOf("Hello")}`);  // index of where the word starts in the string
  console.log(`.substr(2,3) - ${value.substr(2,3)}`)             // .substr(start-index, length)
  console.log(`.substring(2,3) - ${value.substring(2,3)}`)       // .substring(start-index, end-index)
                                                                 //     up to, but including the char at end-index

  /*
    Other Methods
        - split(string)
        - substr(number, number)
        - substring(number, number)
        - toLowerCase()
        - trim()
        - https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String
    */
}

/*
#########################
Array Processing Examples
#########################

JavaScript has a library of functions to make using Arrays easier
*/
function arrayFunctions() {
  let stooges = [    // Define an array
                "Moe",
                "Larry",
                "Curly"
                ]

  console.table(stooges)  // Display the array as a table

  stooges.push("Shemp")   // Add an element to end of the array
  console.table(stooges)  // Display the array as a table

  stooges.unshift("Curly Joe")  // Add an element to start of the array
  console.table(stooges)        // Display the array as a table

  stooges.splice(3,0,"Groucho", "Chico", "Harpo")  // Remove 0 elements and insert new elements at index 3
  console.table(stooges)                           // Display the array as a table

  stooges.splice(3,1)     // Remove the element at index 3
  console.table(stooges)  // Display the array as a table
  
  stooges.splice(3,2)     // Remove two elements at index 3
  console.table(stooges)  // Display the array as a table

  stooges.shift()         // Remove one element at the start of the array (shift elements up 1 position)
  console.table(stooges)  // Display the array as a table

  stooges.pop()           // Remove the last element from the array
  console.table(stooges)  // Display the array as a table

 stooges.splice(stooges.indexOf("Larry"),1)  // Find index of "Larry" and remove the element at that index
 console.table(stooges)                      // Display the array as a table
  
 let marxBros = ["Groucho", "Chico", "Harpo"]  // Define a new array

 let oldFunnyGuys = stooges.concat(marxBros)   // combine the stooges array and marxBros array into one
 console.table(oldFunnyGuys)

} 
