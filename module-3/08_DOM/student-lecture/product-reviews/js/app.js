const pageName = 'Cigar Parties for Dummies';
const description = 'Host and plan the perfect cigar party for all of your squirrelly friends.';
const reviews = [ // array containing the data to be displayed on our page
  {
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

/****************************************************************************************************************************************************************
 * Each of our DOM manipulations will be done in a function - encapsulate each one
 * 
 * At the end of this file, we call each of the main functions to manipulate the DOM
 ****************************************************************************************************************************************************************/

/**
 * Add our product name, stored in a variable at the top of this file, to the page title
 * Get our page page title by the id and the query the .name selector
 * once you have the element you can add the product name to the span.
 */
function setPageTitle() {
// Get a reference to the id='page-title' so we can change it
// .getElementById() takes an id='' value as an argument
  const pageTitle = document.getElementById('page-title'); // get me to the element with the id='page-title'
//                          .querySelector('#page-title) - this method works but is slower

// Get a reference to the <span> element (class='name') of the page title
// .querySelector() takes any valid CSS selector as an argument
  const span = pageTitle.querySelector('.name'); // get me to the element of the page title with the class='name'

// Set the text in the <span> tag to the contents of the name variable defined above
  span.innerText = pageName; // innerText represents the text in an element
}

/**
 * Add our product description to the page.
 */
function setPageDescription() {
  const descript = document.querySelector('.description');
  descript.innerText = description;
// alternative solution for one line:
// document.querySelector('.description').innerText = description;
}

/**
 * I will display all of the reviews on the page.
 * I will loop over the array of reviews and use some helper functions (defined below)
 * to create the elements needed for our html and add them to the DOM
 */
function displayReviews() {
// Get a reference to where we want to add the reviews - in the div with id='main'
  const divMain = document.getElementById('main');

// Loop through the array of reviews - adding an element to the DOM for each review
// Use a .forEach array function with an anonymous function to do this
  reviews.forEach((aReview) => {
  // create a new html element to hold the review
    const newReview = document.createElement('div'); // create a <div> element to add to the DOM

  // add class='review' to the div
    newReview.setAttribute('class', 'review');

  // call each helper function to add it's contribution to our DOM
  // each helper function takes the parent element it belongs to and the data to be included in the element
  // the element it belongs to is the newReview element and the data is an attribute of current array element
    addReviewer(newReview, aReview.reviewer); // call addReviewer() with the reviewer from the array
    addRating(newReview, aReview.rating);     // call addRating() with the rating from the array
    addTitle(newReview, aReview.title);       // call addTitle() with the title from the array
    addReview(newReview, aReview.review);     // call addReview() with the review from the array
  
  // Now we have all the elements for a review defined and connected to a new div
  // All that's left is to add the new div to the DOM
    divMain.insertAdjacentElement('beforeend', newReview);
  });
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
  aReviewer.innerText = name; // set the text of the h4 to the name passed into the function
  parent.appendChild(aReviewer); // connect the new h4 element to the parent html element
}


/**
 * I will add the rating div along with a star image for the number of ratings 1-5
 * @param {HTMLElement} parent
 * @param {Number} numberOfStars
 */
function addRating(parent, numberOfStars) {
// create the div to hold the star images (1 img tag for each rating value)
  const divStar = document.createElement('div');
  divStar.setAttribute('class', 'rating'); // add class='rating' to the div

// generate 1 img tag for each value in the rating
  for(let i=0; i < numberOfStars; i++) {
    const starImg = document.createElement('img'); // create img tag
    starImg.src='img/star.png';                    // set the src for the img
    starImg.setAttribute('class', 'ratingStar');   // add class='ratingStar' to the starImg
    divStar.appendChild(starImg);                  // add the img to the div
  }
  parent.appendChild(divStar);
}

/**
 * I will add an h3 element along with the review title
 * @param {HTMLElement} parent
 * @param {string} title
 */
function addTitle(parent, title) {
  const aTitle = document.createElement('h3');
  aTitle.innerText = title; // set the text of the h3 to the name passed into the function
  parent.appendChild(aTitle); // connect the new h3 element to the parent html element
}

/**
 * I will add the product review
 * @param {HTMLElement} parent
 * @param {string} review
 */
function addReview(parent, review) {
  const aReview = document.createElement('p');
  aReview.innerText = review; // set the text of the p to the name passed into the function
  parent.appendChild(aReview); // connect the new p element to the parent html element
}

// set the product reviews page title using the function defined earlier
setPageTitle();
// set the product reviews page description using the function defined earlier
setPageDescription();
// display all of the product reviews on our page using the function defined earlier
displayReviews();
