let allItemsIncomplete = true;
const pageTitle = 'My Shopping List';
const groceries = [
  { id: 1, name: 'Oatmeal', completed: false },
  { id: 2, name: 'Milk', completed: false },
  { id: 3, name: 'Banana', completed: false },
  { id: 4, name: 'Strawberries', completed: false },
  { id: 5, name: 'Lunch Meat', completed: false },
  { id: 6, name: 'Bread', completed: false },
  { id: 7, name: 'Grapes', completed: false },
  { id: 8, name: 'Steak', completed: false },
  { id: 9, name: 'Salad', completed: false },
  { id: 10, name: 'Tea', completed: false }
];

/**
 * This function will get a reference to the title and set its text to the value
 * of the pageTitle variable that was set above.
 */
function setPageTitle() {
  const title = document.getElementById('title');
  title.innerText = pageTitle;
}

/**
 * This function will loop over the array of groceries that was set above and add them to the DOM.
 */
function displayGroceries() {
  const ul = document.querySelector('ul');
  groceries.forEach((item) => {
    const li = document.createElement('li');
    li.innerText = item.name;
    const checkCircle = document.createElement('i');
    checkCircle.setAttribute('class', 'far fa-check-circle');
    li.appendChild(checkCircle);
    ul.appendChild(li);
  });
}

document.addEventListener('DOMContentLoaded', () => {

  setPageTitle();
  
  displayGroceries();

  const theGroceries = document.querySelectorAll('li');
  theGroceries.forEach((mark) => {
    // when you click on a task mark it completed
    mark.addEventListener('click', () => {
      if (!mark.classList.contains('completed')) {
        mark.classList.add('completed');
        mark.querySelector('i').classList.add('completed');
      }
    });
    mark.addEventListener('dblclick', () => {
      if (mark.classList.contains('completed')) {
        mark.classList.remove('completed');
        mark.querySelector('i').classList.remove('completed');
      }
    });
  });
  const markAll = document.getElementById('toggleAll');
    markAll.addEventListener('click', () => {
      theGroceries.forEach((mark) => {
        mark.classList.add('completed');
        mark.querySelector('i').classList.add('completed');
  });
    const markAll = document.getElementById('toggleAll');
    markAll.addEventListener('click', () => {
      theGroceries.forEach((mark) => {
        if (!mark.classList.contains('completed')) {
          mark.classList.add('completed');
          markAll.innerText = 'Mark All Incomplete';
        } 
        else {
          mark.classList.remove('completed');
          markAll.innerText = 'Mark All Complete';
        }
      });
    });
  });
});