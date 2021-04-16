/* Initial Steps Needed to Develop the Math Problem Group Exercise
(format as JavaScript comments so we can copy into the code)
 Define any shared data required by the app 
 Score
 Set of problems
 Each problem has:
    Left-side number for the multiple
    Right-side number
    Current answer
    Set of answers
*/

let score          = 0;          // hold the score for the current set of problems for the user
let setOfProblems  = []; // hold the set of problems to be presented to the user
let currentProblem = 0; // the number of the problem the user is currently looking at

/* Generate a problem set containing 10 problems */
function generateProblemSet() {
    for(let i=0; i < 10; i++) {
        // randomly generate a single multiplication problem
        // define a problem to hold the values we generate
        let problem = {
            left:  getRandomNumber(10),          // should be a random value between 0 and 9
            right: getRandomNumber(10),         // should be a random value between 0 and 9
            correctAnswer: null
        }

        problem.correctAnswer = problem.left * problem.right; // calculate the correct answer based on the random values
        // we aren't worried if the correct answer happens to be the randomly generated ones
        // randomize the elements so the correct answer is not always first
        let setOfAnswers = shuffleArray([problem.correctAnswer, getRandomNumber(82), getRandomNumber(82), getRandomNumber(82)]);
        
        // add the problem and setOfAnswers objects to the problem set as an object
        setOfProblems.push({problem, setOfAnswers}); // each array element has two attributes in an object
    }
}

/* Show the problem to the user */
function displayProblem() { // add a single problem to the DOM so it displays
    const theProblem = document.getElementById('problem'); // get a reference to where the expression is going in the DOM
    // expression elements(left & right values) are in an element in an array (setOfProblems)
    const probExpression = `${setOfProblems[currentProblem].problem.left} * ${setOfProblems[currentProblem].problem.right}`;
    // add the problem expression to the DOM
    theProblem.querySelector('div.expression').innerText = probExpression;

}

/* Show the answers to the user */
function displayAnswers() {
    const theAnswers    = document.getElementById('answers');   // get a reference to the answers
    const listOfAnswers = theAnswers.querySelector('ul');    // get to the list
    const allAnswers    = listOfAnswers.querySelectorAll('li'); // select all of the list items

    // to make the code easier to read - extract the answer array from the problem
    const answerChoices = setOfProblems[currentProblem].setOfAnswers;
    
    // we have all of the list items to hold the answers in an array called allAnswers
    // go through the array of list items and assign each one one of the answer choices
    for(let i=0; i < allAnswers.length; i++) {
        allAnswers[i].innerText = answerChoices[i]; // assign the answerChoice the corresponding element in <li>
    }
}

/* allow user to click on one of the answers displayed */
/* Update the problem count when the user selects an answer */
/* Determine if the answer chosen was correct */
/* update score if answer was correct */
/* Move/change to the next problem when user chooses and answer */
/* Reset when the user wants to start over   */

/**
 * Utility function to generate a random number based on max
 * @param {number} max
 */
 function getRandomNumber(max) {
    return Math.floor(Math.random() * Math.floor(max));
}
  
/**
 * Utility function to shuffle the items in an array
 * @param {object} arr
 */
function shuffleArray(arr) {
  return arr.sort(function (a, b) { return Math.random() - 0.5 })
}

// After the DOM has been fully loaded, we want to start displaying and setting up our app 
document.addEventListener('DOMContentLoaded', () => {

  // add event listeners to the <li> tags to make them clickable
  let answerListItems = document.querySelectorAll('#answers ul li');

  answerListItems.forEach((aListItem) => {
    aListItem.addEventListener('click', (event) => {
        const answerPicked  = parseInt(event.currentTarget.innerText); // extract the value from the <li> clicked
        const correctAnswer = parseInt(setOfProblems[currentProblem].problem.correctAnswer); // extract the correct answer
        if(answerPicked === correctAnswer) {
            score++;
        }
    });
  });

  generateProblemSet(); // generate a problem set
  displayProblem();     // display a problem
  displayAnswers();

});