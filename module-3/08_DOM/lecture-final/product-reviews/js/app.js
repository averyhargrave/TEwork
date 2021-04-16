const pageName = 'Cigar Parties for Dummies';
const description = 'Host and plan the perfect cigar party for all of your squirrelly friends.';
const reviews = [  // array containing the data ti be displayed on the page
  {                                // each array element is an object with several attribute 
    reviewer: 'Malcolm Gladwell',
    title: 'What a book!',
    review:
      "It certainly is a book. I mean, I can see that. Pages kept together with glue (I hope that's glue) and there's writing on it, in some language.",
    rating: 3
  },
  {
    reviewer: 'Tim Ferriss',
    title: 'Had a cigar party started in less than 4 hours.',
    review:
      "It should have been called the four hour cigar party. That's amazing. I have a new idea for muse because of this.",
    rating: 4
  },
  {
    reviewer: 'Ramit Sethi',
    title: 'What every new entrepreneurs needs. A door stop.',
    review:
      "When I sell my courses, I'm always telling people that if a book costs less than $20, they should just buy it. If they only learn one thing from it, it was worth it. Wish I learned something from this book.",
    rating: 1
  },
  {
    reviewer: 'Gary Vaynerchuk',
    title: 'And I thought I could write',
    review:
      "There are a lot of good, solid tips in this book. I don't want to ruin it, but prelighting all the cigars is worth the price of admission alone.",
    rating: 3
  }
];
/********************************************************************************************
 * Each of our DOM manipulations will be done in a function - encapsulate each one
 * 
 * At the end of this file, we call each of the main functions to manipulate the DOM
 ********************************************************************************************/
/**
 * Add our product name stored in a variable at the top of the file to the page title
 * Get our page page title by the id and the query the .name selector
 * once you have the element you can add the product name to the span.
 */
function setPageTitle() {
  // Get a reference to the id='page-title' so we can change it
  // .getElementById() takes an id="" value as an argument
  const thePageTitle = document.getElementById('page-title')   // Get me to the element with the id='page-titel'
  //                           .querySelector('#id') is OK too
  // Get a reference to the <span> element (class='name') of the page title
  // .querySelector() takes any valid CSS selector as an argument
  const theSpan = thePageTitle.querySelector('.name')  // Get me to the element with class='name' of thePageTitle

  // Set the text in the <span> to the contents of the variable pageName (product description)
  theSpan.innerText = pageName;   // innerText represents the text in an element
}

/**
 * Add our product description to the page.
 */
function setPageDescription() {
  // Get a reference to the element with the class="description"
  const theDescription = document.querySelector('.description')
  theDescription.innerText = description;  // Set the text of theDescription to variable description
  // alternative: document.querySelector('.description').innerText = description;
}

/**
 * I will display all of the reviews on the page.
 * I will loop over the array of reviews and use some helper functions (defined below)
 * to create the elements needed for our html and add them to the DOM
 */
function displayReviews() {
  // Get a reference to where we want to add the reviews - in the <div> with id='main"
  const theDiv = document.getElementById('main')

  // Loop through the array of reviews (reviews) - adding an element to the DOM for each review
  // Use a .forEach array function with an anonymous function to do this
  reviews.forEach((aReview) => {
    // create a new html element hold the current review
    const newReview = document.createElement('div');  // create a <div> element to add to DOM

    // add class="review" attribute to the new <div>
    newReview.setAttribute('class','review')

    // Call each helper function to add it's contribution to the DOM
    // Each helper function takes the parent element it belongs to and the data to be included in the element
    //      the element it belongs to is newReview element and the data is an attribute of current array element
    
    addReviewer(newReview, aReview.reviewer) // Call addReviewer with the reviewer from the array element
    addRating(newReview, aReview.rating)     // Call addRating with the rating from the array element
    addTitle(newReview, aReview.title)       // Call addTitle with the title from the array element
    addReview(newReview, aReview.review)   // Call addReview with the review from the array element

    // Now we have all the elements for a review defined and connected to a new <div>
    //     all that's left is to add the new <div> to DOM
    theDiv.insertAdjacentElement('beforeend', newReview)  // add the new dev before the end of theDiv (main)
  })



}

/**
 * I will creating a new h4 element with the name of the reviewer and append it to
 * the parent element that is passed to me.
 *
 * @param {HTMLElement} el: The element to append the reviewer to
 * @param {string} name The name of the reviewer
 */
function addReviewer(parent, name) {
  const aReviewer = document.createElement('h4');
  aReviewer.innerText = name;  // Set the text of the h4 to the name passed in to this function
  parent.appendChild(aReviewer); // Connect the new h4 element to the parent html element passed in
}

/**
 * I will add the rating div along with a star image for the number of ratings 1-5
 * @param {HTMLElement} parent
 * @param {Number} numberOfStars
 */
function addRating(parent, numberOfStars) {
  // create the <div> to hold the star imag tags (1 img tag for each rating value)
  const starDiv = document.createElement('div')
  starDiv.setAttribute('class','rating')  // add class="rating" to the div
  // generate one <img> for each value in the rating
  for(let i=0; i < numberOfStars; i++){
    const starImg = document.createElement('img')   // create an img tag
    starImg.src='img/star.png'                      // set src="img/stag.png"
    starImg.setAttribute('class', 'ratingStar')     // set class="ratingStar"
    starDiv.appendChild(starImg)                    // add img tag to div for rating
  }
  parent.appendChild(starDiv)
}

/**
 * I will add an h3 element along with the review title
 * @param {HTMLElement} parent
 * @param {string} title
 */
function addTitle(parent, title) {
  const aTitle = document.createElement('h3')
  aTitle.innerText = title;
  parent.appendChild(aTitle)
}

/**
 * I will add the product review
 * @param {HTMLElement} parent
 * @param {string} review
 */
function addReview(parent, review) {
  const aReview = document.createElement('p')
  aReview.innerText = review;
  parent.appendChild(aReview)
}

// set the product reviews page title using the function defined earlier
setPageTitle();
// set the product reviews page description using the function defined earlier
setPageDescription();
// display all of the product reviews on our page using the function defined earlier
displayReviews();
