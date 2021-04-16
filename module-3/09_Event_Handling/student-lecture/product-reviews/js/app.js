const name = 'Cigar Parties for Dummies';
let description = 'Host and plan the perfect cigar party for all of your squirrelly friends.';
let reviews = [
  {
    reviewer: 'Malcolm Gladwell',
    title: 'What a book!',
    review:
      "It certainly is a book. I mean, I can see that. Pages kept together with glue (I hope that's glue) and there's writing on it, in some language.",
    rating: 3
  }
];

/**
 * Add our product name to the page title
 * Get our page page title by the id and the query the .name selector
 * once you have the element you can add the product name to the span.
 */
function setPageTitle() {
  const pageTitle = document.getElementById('page-title');
  pageTitle.querySelector('.name').innerText = name;
}

/**
 * Add our product description to the page.
 */
function setPageDescription() {
  document.querySelector('.description').innerText = description;
}

/**
 * I will display all of the reviews in the reviews array
 */
function displayReviews() {
  // Use the template defined in the html to add the product review to the DOM
  if ('content' in document.createElement('template')) { // if the html contains a template
    reviews.forEach((review) => {                        //    we are going to use it
      displayReview(review);
    });
  } else { // if there is no template in the html, display a message to the user
    console.error('Your browser does not support templates');
  }
}

/**
 *
 * @param {Object} review The review to display
 */
function displayReview(review) {
  // Add a product review to the DOM using the pre-defined template in the html
  const main = document.getElementById('main'); // get a reference to where we want to add the product review
  const tmpl = document.getElementById('review-template').content.cloneNode(true); // make a copy of the template
                                                                                   //      called 'review-template'
  // Now that we have a copy of the template and a reference to that copy - we just assign data to elements
  tmpl.querySelector('h4').innerText = review.reviewer; // Assign the reviewer property of current element
  tmpl.querySelector('h3').innerText = review.title;    // Assign the title property of current element
  tmpl.querySelector('p').innerText = review.review;    // Assign the review property of current element
  // there will always be 1 star because it is a part of the template
  for (let i = 1; i < review.rating; ++i) { // Loop to add any additional star images
    const img = tmpl.querySelector('img').cloneNode(); // Make a copy of the img already in the template
    tmpl.querySelector('.rating').appendChild(img);    // Add it to the div containing the stars
  }
  main.appendChild(tmpl); // Add the new product review <div> to the DOM
}

// LECTURE STARTS HERE ---------------------------------------------------------------

// wait until the DOM is fully created before trying to manipulate it
// because if the DOM is not fully created, the stuff we're adding may not be added

// element-for-listener(event-to-handle, function-to-handle-event)
document.addEventListener('DOMContentLoaded', () =>
{ // Wait for the DOMContentLoaded event before we run the setup functions
  setPageTitle(); // set the product reviews page title
  setPageDescription(); // set the product reviews page description
  displayReviews(); // display all of the product reviews on our page
// any additional event listeners will be added, because they also need to wait for the DOM to load
  const theDescription = document.querySelector('.description'); // get a reference to the class='description' element
  theDescription.addEventListener('click', (event) => {
    // call helper method with the element that was clicked
    // .target is an attribute in the event object containing
    //     a reference to the element that had the event
    toggleDescriptionEdit(event.target);
  });
// We want to save the text entered in the textBox to the description when the user presses enter
// add an event listener to the textBox to react on an enter press
// add an event listener to the textBox to react on an escape press
  const theTextBox = document.getElementById('inputDesc'); // get a reference to the textBox
  theTextBox.addEventListener('keyup', (event) => { // keyup is when a user releases the key that was pressed
    // check to see if key pressed was enter - event.key is the key pressed
    if(event.key === 'Enter') {
      exitDescriptionEdit(event.target, true);
    }
    if(event.key === 'Escape') {
      exitDescriptionEdit(event.target, false);
    }
  });
  // When the user clicks the 'Add Review' button, display the form to get the review data
  const addReviewBtn = document.getElementById('btnToggleForm'); // get a reference to the Add Review button
  addReviewBtn.addEventListener('click', () => {                    // listen for the click on Add Review
    showHideForm();
  });
  // When the user clicks the 'Save Review' button, we need to save the review - duh
  const saveReviewBtn = document.getElementById('btnSaveReview');
  saveReviewBtn.addEventListener('click', () => {
    event.preventDefault(); // disable the default behavior of a submit button because we want to handle it
    saveReview();
  });

}); // end of event listener for the document

/**
 * Run this method when a click event on the description is fired and swap out the description for a text box.
 *
 * Swap the description on the page with a text box so the user can enter a new description
 * 
 * @param {elem} element that was clicked
 */
function toggleDescriptionEdit(elem) { // receive the element that was clicked as an argument
// ****************************************** DON'T CODE LIKE THIS - just an example of how to use nextElementSibling
  const textBox = elem.nextElementSibling;          // get a reference to the next sibling of the element that was clicked
// const textBox = elem.getElementById('inputDesc') - you should code it like this

  textBox.value = description;
  textBox.classList.remove('d-none'); // allow the textBox to display - remove the d-none class
// .classList contains all the classes assigned to an element - you can manipulate what is in the list
  elem.classList.add('d-none');       // hide the element that was clicked - add the d-none class
  textBox.focus();                    // put the cursor in the textBox - focus moves the cursor to where you want it
}

/**
 * Take an event on the text box and set the description to the contents
 * of the text box and then hide the text box and show the description.
 * 
 * Get us out of the textBox and save or discard the contents
 *
 * @param {textBox} textBox
 * @param {Boolean} save should we save the description text
 */
function exitDescriptionEdit(textBox, save) { // receives the clicked-element, and a boolean to save or not
                                              // if the boolean is true - save the new text, if false - discard it
//******************************************** DON'T CODE LIKE THIS - just another example
  let desc = textBox.previousElementSibling;  // get a reference to the sibling before the one that had the event
// let desc = textBox.querySelector('description'); - it would be coded like this
  if (save) {                       // if the boolean was true
    desc.innerText = textBox.value; // set the text in the previous sibling to the text that was entered
  }                                 // if the boolean was false - do nothing
  textBox.classList.add('d-none'); // hide the textBox - add the d-none class
  desc.classList.remove('d-none'); // allow the class='description' to display - remove the d-none class
}

/**
 * I will show / hide the add review form
 */
function showHideForm() {
  const form = document.querySelector('form'); // reference to form
  const btn = document.getElementById('btnToggleForm'); // reference to button

  if (form.classList.contains('d-none')) {   // if the form is hidden:
    form.classList.remove('d-none');         //   display it
    btn.innerText = 'Hide Form';             //   change the button to say 'Hide Form'
    document.getElementById('name').focus(); //   put the cursor in the 'name' field
  } else {                                   // if the form is displayed:
    resetFormValues();                       //   reset the form values
    form.classList.add('d-none');            //   hide the form
    btn.innerText = 'Add Review';            //   change the button to say 'Add Review'
  }
}


/**
 * I will reset all of the values in the form.
 */
function resetFormValues() {
  const form = document.querySelector('form');   // get a reference to the form
  const inputs = form.querySelectorAll('input'); // get an array of references to all <input> elements
  inputs.forEach((input) => {                    // loop through each input element
    input.value = '';                            // set the value to be empty
  });
  document.getElementById('rating').value = 1;   // resetting the rating to 1 star
  document.getElementById('review').value = '';  // resetting the review to be empty
}

/**
 * I will save the review that was added using the add review from
 */
function saveReview() {
  // Get the information entered by the user
  const theName = document.getElementById('name');
  const theTitle = document.getElementById('title');
  const theRating = document.getElementById('rating');
  const theReview = document.getElementById('review');

  const newReview = {
    reviewer: theName.value,
    title: theTitle.value,
    rating: theRating.value,
    review: theReview.value
  };

  reviews.push(newReview);

  displayReview(newReview);

  showHideForm();
}
