/**
 * All named functions will have the function keyword and
 * a name followed by parentheses.
 * 
 * @returns {number} 1
 */
function returnOne() {
  return 1;
}

/**
 * Functions can also take parameters. These are just variables that are filled
 * in by whoever is calling the function.
 *
 * Also, we don't *have* to return anything from the actual function.
 *
 * @param {any} value the value to print to the console
 */
function printToConsole(value) {
  console.log(value);
}

/**
 * Write a function called multiplyTogether that multiplies two numbers together. But 
 * what happens if we don't pass a value in? What happens if the value is not a number?
 *
 * @param {number} firstParameter the first parameter to multiply
 * @param {number} secondParameter the second parameter to multiply
 */
function multiplyTogether(num1, num2) {
  let result = num1 * num2;
  return result;
  // alternate solution: return num1 + num2;
}
/**
 * This version makes sure that no parameters are ever missing. If
 * someone calls this function without parameters, we default the
 * values to 0. However, it is impossible in JavaScript to prevent
 * someone from calling this function with data that is not a number.
 * Call this function multiplyNoUndefined
 *
 * We can supply default values for parameters that are missing
 * Simply coded parameterName=defaultValue
 * 
 * @param {number} [firstParameter=0] the first parameter to multiply
 * @param {number} [secondParameter=0] the second parameter to multiply
 */
function multiplyNoUndefined(firstParameter=0, secondParameter=0 ){
  console.log(`1st Parameter: ${firstParameter}`)
  console.log(`2nd Parameter: ${secondParameter}`)
  return firstParameter * secondParameter;
}
/**
 * Functions can return earlier before the end of the function. This could be useful
 * in circumstances where you may not need to perform additional instructions or have to
 * handle a particular situation.
 * In this example, if the firstParameter is equal to 0, we return secondParameter times 2.
 * Observe what's printed to the console in both situations.
 * 
 * @param {number} firstParameter the first parameter
 * @param {number} secondParameter the second parameter
 */
function returnBeforeEnd(firstParameter, secondParameter) {
  console.log("This will always fire.");

  if (firstParameter == 0) {
    console.log("Returning secondParameter times two.");
    return secondParameter * 2;  // terminate the function and return to caller
  }

  //this only runs if firstParameter is NOT 0
  console.log("Returning firstParameter + secondParameter.");
  return firstParameter + secondParameter;  // terminate the function and return to caller
}

/**
 * Scope is defined as where a variable is available to be used.
 *
 * If a variable is declare with let inside of a block, it will only exist in
 * that block and any block underneath it. Once the block that the
 * variable was defined in ends, the variable disappears.
 */
function scopeTest() {
  // This variable will always be in scope in this function - defined in the function block
  let inScopeInScopeTest = true;

  { // scopedToBlock variable is created here
    // this variable lives inside this block and doesn't
    // exist outside of the block
    let scopedToBlock = inScopeInScopeTest;  
  } // scopedToBlock variable is destroyed here - no longer available to anyone

     // scopedToBlock doesn't exist here so an error will be thrown
     if (inScopeInScopeTest && scopedToBlock) {
        console.log("This won't print!");
    }
  
}

function createSentenceFromUser(name, age, listOfQuirks = [], separator = ', ') {
  let description = `${name} is currently ${age} years old. Their quirks are: `;
  // .join() takes the elements in an array and makes them a String
  //                   with the separator between them
  return description + listOfQuirks.join(separator);
}

/**
 * Takes an array and, using an anonymous function, generates
 * their sum.
 *
 *  .reduce() - return an array reduced/converted to a single value using a function as an argument
 *  .reduce() - goes through the array one element at a time (implied for-each loop) passing the current
 *              element to the function used as the argument
 * 
 *     syntax:   arrayName.reduce(anonymous-function(reducer, element-in-array))
 *
 *               the anonymous-function takes two parameters: reducer and elem-in-array
 * 
 *               the reducer will hold the value from the previous call to the anon-func
 *                   (result from the prior execution of the anon-func)
 * 
 * @param {number[]} numbersToSum numbers to add up
 * @returns {number} sum of all the numbers
 */

function sumAllNumbers(numbersToSum) {
  // the variable sum (reducer) will contain the sum of all the numbers in the array
  //
  // the anon-func will change the value in sum (reducer) each time it is called
  //
  // the variable aNumber will be the current element in the array passed to teh anon-func

  return numbersToSum.reduce((sum, aNumber)=> {
                                                return sum += aNumber;  // return the result of adding 
                                                                        //     the current array element
                                                                        //     to the current sum
                                               } );

// alternate solution: return numbersToSum.reduce((sum, aNumber) => {sum += aNumber})

// alternate solution: return numbersToSum.reduce(
//                                                (sum, aNumber) => {sum += aNumber}
//                                               )

/* To achieve the same result without .reduce() (what the above code is doing)
*
*  int sum = 0;
*  for (int i=0; i < numbersToSum.length; i++) {
*    sum += numberToSum[i];
*  }
*  return sum;
*/
}

/**
 * Takes an array and returns a new array of only numbers that are
 * multiples of 3
 *
 * @param {number[]} numbersToFilter numbers to filter through
 * @returns {number[]} a new array with only those numbers that are
 *   multiples of 3
 * 
 *  Use the .filter() when you want to select elements from an array into a new array
 * 
 *  .filter() will return an array of elements that a callback function said should be in the new array
 *                                    (a callback function is a function specified as a parameter
 *                                     to another function that THAT functions call as it processes)
 * 
 *  .filter() will go through the array one element at a time passing the current element to the callback function
 *                 (like a for-each in Java)
 * 
 *  the callback functions tells .filter() to place the current element in the new array
 *      by returning true - if the callback function returns false the element is not added to the new array
 * 
 *  syntax: arrayName.filter(callback-func(anElement)) - the callback function argument is the current element
 * 
 *                                                       the callback-func returns true if elem should be
 *                                                       included in the new                
 */
function allDivisibleByThree(numbersToFilter) {
// return an array of only the numbers divisible by 3 in the argument array

// Use .filter() to go through the array one element at a time asking the callback functionL do we want it?
return numbersToFilter.filter((anElement) => {
                                              if (anElement % 3 === 0) {  // if anElement is evenly divisible by 3
                                                return true;              //    tell filter to add to new array (we want it)
                                              }
                                              else {                      // if not...
                                                return false              //    tell filter not to add to new array (we don't want it)
                                              }
                                             })
/*
alternate solution: return numbersToFilter.filter((anElement) => {anElement % 3 === 0})

alternate solution: theNewArray = [];
                    theNewArray = numbersToFilter.filter((anElement) => {anElement % 3 === 0})
                    return theNewArray
*/

/*
  array functions may use named functions as callback functions as well an anonymous functions

  function allDivisibleByThree(numbersToFilter) {
    return numbersToFilter.filter(isDivisbleBy3(anElement));
  }
  function isDivisibleBy3(currentElement) {
    return currentElement % 3 === 0
  }
*/
  
}


/**
 * This example was added by Frank at the last minute and has no test.
 * Give the browser the html and use the inspect to to run it
 * 
 * The map function will return an array created from the elements passed to it
 * 
 * @param - None
 * @returns {array created from values passed to it}
 */

function mapArrayFunctionExampleFromBook(numbersToSquare = [1, 2, 3, 4]) {

// Create a new array containing the square of all the values in another array
// Use .map() to create a new array whose elements are result of processing elements in another array

console.log(`Array calling map to create new array with values squared: `)
console.table(numbersToSquare);

// Call the .map() function for each element in the array to have the elements in the array squared
// .map() returns a new array with the elements from  the array it's passed processed/changed (squared)

let squaredNumbers = numbersToSquare.map( (number) => { // pass the current element to the anon-func
    return number * number;                             //    the anon-func will square it and store
                                                        //    the value in an element in a new array
});

console.log(`Array returned from map with values in passed array squared: `)
console.table(squaredNumbers);
}
function forEachExample(anArray=["Jason","D","Avery","Jeff","Danny", "Anne"])
{
  // forEach() is the JavaScript version of the for-each look in Java
  anArray.forEach((anElement) => { 
    console.log(anElement)
    }) 
}
