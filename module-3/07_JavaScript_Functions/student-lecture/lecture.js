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
  return num1 * num2;
}

/**
 * This version makes sure that no parameters are ever missing. If
 * someone calls this function without parameters, we default the
 * values to 0. However, it is impossible in JavaScript to prevent
 * someone from calling this function with data that is not a number.
 * Call this function multiplyNoUndefined
 * 
 * We can supply default values to parameters that are missing
 * Simply code the parameterName=defaultValue
 *
 * @param {number} [firstParameter=0] the first parameter to multiply
 * @param {number} [secondParameter=0] the second parameter to multiply
 */

function multiplyNoUndefined(num1=0, num2=0) {
  console.log(`1st Parameter: ${num1}`);
  console.log(`2nd Parameter: ${num2}`);
  return num1 * num2;
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
    return secondParameter * 2;
  }

  //this only runs if firstParameter is NOT 0
  console.log("Returning firstParameter + secondParameter.");
  return firstParameter + secondParameter;
}

/**
 * Scope is defined as where a variable is available to be used.
 *
 * If a variable is declare inside of a block, it will only exist in
 * that block and any block underneath it. Once the block that the
 * variable was defined in ends, the variable disappears.
 */
function scopeTest() {
  // This variable will always be in scope in this function - defined in the function block
  let inScopeInScopeTest = true;

  { // scopedToBlock is created here, available inside of this block
    // this variable lives inside this block and doesn't exist outside of the block
    let scopedToBlock = inScopeInScopeTest; // since this is the same name as a var variable, it overrides the var
  } // scopedToBlock is destroyed here, no longer available to use

  // scopedToBlock doesn't exist here so an error will be thrown
  if (inScopeInScopeTest && scopedToBlock) {
    console.log("This won't print!");
  }


  // {
      //  var defines a variable with function scope regardless of where it is defined
  // var scopedToBlock = 1; // This variable is available anywhere in the function
  // }
}

function createSentenceFromUser(name, age, listOfQuirks = [], separator = ', ') {
  // .join() takes the elements in an array and makes them a string with the separator between them
  let description = `${name} is currently ${age} years old. Their quirks are: `;
  return description + listOfQuirks.join(separator);
}

/**
 * Takes an array and, using an anonymous function, generates their sum.
 *
 * .reduce() - returns an array reduced/converted to a single value using a function as an argument
 *           - goes through the array one element at a time (implied for-each loop)
 *                passing the current element to the function used as the argument
 * 
 *    syntax:  arrayName.reduce(anonymous-function(reducer, element-in-array))
 * 
 *             The reducer will hold the value from the previous call to the anonymous function (result)
 *         
 * 
 * @param {number[]} numbersToSum numbers to add up
 * @returns {number} sum of all the numbers
 */
function sumAllNumbers(numbersToSum) {
  // the variable sum will contain the sum of all the numbers in the array
  // the anonymous function will change the value in sum each time it's called
  // the variable aNumber will be the current element in the array passed to the anonymous function
  return numbersToSum.reduce((sum, aNumber) => {
                                                return sum += aNumber;
                                               });
              // this can also be done in these forms:
              // numbersToSum.reduce((sum, aNumber) => {return sum += aNumber});
              // numbersToSum.reduce(
              //                     (sum, aNumber) => {return sum += aNumber}
              //                    );

/* Alternate solution to achieve the same result without using .reduce():

   let sum = 0;
   for(let i=0; i < numbersToSum.length; i++) {
     sum += numbersToSum[i];
   }
   return sum;
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
 * Use the .filter() function when you want to select elements from an array into a new array
 * 
 * .filter() will return an array of elements that a callback function said should be in the the new array
 * 
 * .filter() will go through the array one element at a time passing the current element to the callback function
 * 
 * syntax: arrayName.filter(callback-function(anElement)) - the callback argument is the current element
 *                                                        - the callback returns true if element should be included in new array
 */
function allDivisibleByThree(numbersToFilter) {
  // return an array of only the numbers divisible by 3 in the argument array
  // Use .filter() to go through the array one element at a time asking the callback if we want it
  return numbersToFilter.filter((anElement) => {
                                                if(anElement % 3 === 0) { // if anElement is divisble by 3
                                                  return true;              // tell filter to add it to the new array
                                                }
                                                else {                    // otherwise
                                                  return false;             // tell filter NOT to add it
                                                }
                                               });
/*
  Alternate Solutions:

  return numbersToFilter.filter((anElement) => {anElement % === 0});

-----------------------------------------------------------------------------

  divisThree = [];
  divisThree = numbersToFilter.filter((anElement) => {anElement % === 0});
  return divisThree;

-----------------------------------------------------------------------------  

  let divisThree = [];
  for(let i=0; i < numbersToFilter; i++) {
    if(numbersToFilter[i] % 3 === 0) {
      divisThree.push(numbersToFilter[i]);
    }
    return divisThree;
  }
*/

/*
  Array functions may use named functions as callbacks as well as anonymous functions

  function allDivisibleByThree(numbersToFilter) {
    return numbersToFilter.filter(isDivisByThree(anElement));
  }
  function isDivisByThree(currentElement) {
    return currentElement % 3 === 0;
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

function mapArrayFunctionExampleFromBook(numbersToSquare = [1,2,3,4]) {
// create a new array containing the squares of all the values in another array
// use .map() to create a new array whose elements are the result of processing elements in another array
  console.log(`Array calling map to create new array with values squared: `)
  console.table(numbersToSquare);

// call the .map() function for each element in the array to have the elements in the array squared
  let squaredNumbers = numbersToSquare.map((number) => { // pass the current element to the anonymous function 
                                                        return number * number; // the anon-func will square and store
                                                       });                      // the value in an element in a new array

  console.log(`Array returned from map with values in passed array squared: `)
  console.table(squaredNumbers);
}


function forEachExample(anArray = ["Jason", "D", "Avery", "Jeff", "Danny", "Anne"]) {
  // forEach() is the JavaScript version of a for-each loop in Java
  anArray.forEach((anElement) => {
                                  console.log(anElement);
                                 });
}