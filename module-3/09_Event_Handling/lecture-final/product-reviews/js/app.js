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
  // Use the template already defined in the html to add the product review to the DOM
  if ('content' in document.createElement('template')) {  // if the html contains a template
                                                          //    we are going use it
    reviews.forEach((review) => {  // Loop through the array of reviews sending each review to anon-func
      displayReview(review);       //     call the displayReview function with the current review
    });
  } else {   // if there is no template in the html - display a message to that effect and do nothing else
    console.error('Your browser does not support templates');
  }
}

/**
 *
 * @param {Object} review The review to display
 */
function displayReview(review) {
  // Add a product review to the DOM using the pre-defined template in the html
  const main = document.getElementById('main');   // Get a reference to where we want to add the product review in the DOM
  const tmpl = document.getElementById('review-template').content.cloneNode(true); // Make a copy/clone of the template
                                                                                   //   called 'review-template'
  // Now that we a copy of the template an a reference to that copy - we just assign data to elements
  tmpl.querySelector('h4').innerText = review.reviewer;   // Assign the reviewer property of current element
  tmpl.querySelector('h3').innerText = review.title;      // Assign the title property of current element
  tmpl.querySelector('p').innerText = review.review;      // Assign the review property of current element
  // there will always be 1 star because it is a part of the template
  for (let i = 1; i < review.rating; ++i) {               // loop to add any additional star images
    const img = tmpl.querySelector('img').cloneNode();    //     Make a copy of the img already in the template
    tmpl.querySelector('.rating').appendChild(img);       //     Add it to div containing the stars
  }
  main.appendChild(tmpl);    // Add the new product review <div> to the DOM
}

// LECTURE STARTS HERE ---------------------------------------------------------------

// Wait until the DOM is fully created before trying to manipulate it
// because if DOM is not fully created the stuff we're adding may not be added

// element-for-the-listener(event-to handle, function-to-handle-the-event)
document.addEventListener('DOMContentLoaded', () => 
  {  // Wait for the DOMContentLoaded event before we run the setup up page functions
     setPageTitle();        // set the product reviews page title
     setPageDescription();  // set the product reviews page description
     displayReviews();      // display all of the product reviews on our page

     // any additional event listeners will be added, because they need to wait until the DOM is fully loaded too                                                   
     const theDescription = document.querySelector('.description')  // Get a reference to class='description' element                                             
     theDescription.addEventListener('click', (event) => {
                                                         // call helper method with element that was clicked
                                                         // .target is an attribute in the Event object
                                                         //         containing a reference that had the event
                                                          toggleDescriptionEdit(event.target)
                                                         })                                            
      // We want to save the text entered in the text box to the description
      //       when  the user presses enter - add an event listener to text box to react on enter pressed
      //       when the user presses escape we don't save the text
      const theTextBox = document.getElementById('inputDesc')  // Get a reference to the text box
      theTextBox.addEventListener('keyup',(event)=> 
           {
           // Check to see if enter was pressed - event.key
           if(event.key === 'Enter') {
              exitDescriptionEdit(event.target, true)  // call the helper with the element and boolean to save text
           }
           if(event.key === 'Escape') {
              exitDescriptionEdit(event.target, false)  // call the helper with the element and boolean to not save text
         }
          })  // end of anon-func for keyup
    
      // When the user moves the cursor out of the text box while editing, close the box and discard changes
      // Since we already have a reference to the text box we don't need to get another
      theTextBox.addEventListener('mouseleave', (event) => 
               {
                exitDescriptionEdit(event.target,false)  // call the helper with the element and boolean to not save text
           })  // end of anon-func got mouseleave


      // When the user clicks the "Add Review" button, display the form to get the review data
      const theAddReviewButton = document.getElementById('btnToggleForm');   // Get a reference to the Add Review button
      theAddReviewButton.addEventListener('click', () => {                   // Listen for click on Add Review button
          showHideForm()                                                     // When clicked - call showHideForm()
      })

      // When the user click the Save Review button, we need to save the review
      const theSaveReviewButton = document.getElementById('btnSaveReview')  // Get a reference to the Save Review button
      theSaveReviewButton.addEventListener('click', (event) =>  {                // Listen for click on Save Review
        event.preventDefault();  // Disable default behavior of submit button - WE want to handle what it does   
        saveReview()                                                     // When clicked - call saveReview()
      })

                                                  
  }) // end of the anon-func for DOMContentLoaded Event Listener

/**
 *Run when a click event on the description id fired and swap out the description for a text box.
 *
 * Swap the description on the page with a text box so the user can enter a nw description
 * 
 * @param {desc} element that was clicked
 */
function toggleDescriptionEdit(desc) {     // receive the element that was clicked as an argument
  const textBox = desc.nextElementSibling; // Get a reference to the next sibling of the element that was clicked
                                           // Get a reference to the <input> that follows the <p> which we were passed
  textBox.value = description;             // initialize the textBox to the original description
  textBox.classList.remove('d-none');      // Allow the textBox to display - remove the d-none class
  // .classList contains all the classes assigned to an element - you can manipulate the list
  desc.classList.add('d-none');            // Hide the element that was clicked - adding d-none class
  textBox.focus();                         // Put the cursor in the textBox
}

/**
 * Handle an event on the text box and set the description to the contents
 * of the text box and then hide the text box and show the description.
 * 
 * Get us out of the text box and save or discard the contents
 *
 * @param {Event} event the event object
 * @param {Boolean} save should we save the description text
 */
function exitDescriptionEdit(textBox, save) {  // Receives the element-that-had-the-event, boolean to save or not
                                               // if boolean is true we save the new text, false we don't
  let desc = textBox.previousElementSibling;   // Get a reference to sibling BEFORE the one having the event
                                              //  In this case, the <p> element for description
  if (save) {                         // if the boolean passed was true
    desc.innerText = textBox.value;   // set the text in the previous sibling to the text entered
  }                                   // if boolean was false, do nothing
  textBox.classList.add('d-none');    // Hide the text box - adding d-none to class list
  desc.classList.remove('d-none');    // Display the description - removing d-none from class list
}

/**
 * I will show / hide the add review form
 */
function showHideForm() {
  const form = document.querySelector('form');
  const btn = document.getElementById('btnToggleForm');

  if (form.classList.contains('d-none')) {  // if form is hidden
    form.classList.remove('d-none');        //    display it
    btn.innerText = 'Hide Form';            //    change the button to say "Hide Form"
    document.getElementById('name').focus();//    put cursor in the name field on the form
  } else {                                  // if the form is displayed
    resetFormValues();                      //    reset the form values
    form.classList.add('d-none');           //    hide the form
    btn.innerText = 'Add Review';           //    change the button to say "Add Review"
  }
}

/**
 * I will reset all of the values in the form.
 */
function resetFormValues() {
  const form = document.querySelector('form');    // Get a reference to the form
  const inputs = form.querySelectorAll('input');  // Get an array of references to all <input> elements
  inputs.forEach((input) => {                     // Loop through the array of input elements
    input.value = '';                             //      setting each ones value to ''
  });
  document.getElementById('rating').value = 1;    // Reset rating to 1
  document.getElementById('review').value = '';   // Reset the review to ''
}

/**
 * I will save the review that was added using the add review from
 */
function saveReview() {
  // Get the information entered by user
  //     get a reference to the form or to each individual input field
  const theName    = document.getElementById('name')     // Get a reference to the name input field
  const theTitle   = document.getElementById('title' )   // Get a reference to the title  input 
  const theReview  = document.getElementById('review')   // Get a reference to the review input 
  const theRating  = document.getElementById('rating')   // Get a reference to the rating  input 

  // Save it to the reviews array (array of review objects)
  //    create a new object for the new review
  const newReview = {
    reviewer: theName.value,   // set the reviewer attribute to the value in name input field
    title: theTitle.value,     // set the title attribute to the value in title input field
    review: theReview.value,   // set the review attribute to the value in review input field
    rating: theRating.value,   // set the rating attribute to the value in rating input field
  }
  //    add the new object to the array
  reviews.push(newReview)     

  // Display the reviews to see the new one that was added
  // We need to have it added to the DOM - right now it is only in the array
  // displayReview(aReview) that adds a review to the DOM
  displayReview(newReview)   // add the new review to the DOM

  // Hide the form since we are done with it using our helper method
  showHideForm()
}
