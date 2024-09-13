let list = document.querySelectorAll(".sidebar ul li");

function activeLink() {
  list.forEach((item) => {
    item.classList.remove("hovered");
  });
  this.classList.add("hovered");

  
  localStorage.setItem('activeItem', this.dataset.index);
}

list.forEach((item, index) => {
  item.addEventListener("click", activeLink);
  item.dataset.index = index;  
});

window.addEventListener('DOMContentLoaded', () => {
  const activeIndex = localStorage.getItem('activeItem');
  if (activeIndex !== null) {
    list[activeIndex].classList.add("hovered");
  }
});
