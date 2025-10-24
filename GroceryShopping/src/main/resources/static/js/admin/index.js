// js/admin/index.js
const toggleBtn = document.getElementById('toggleBtn');
const sidebar = document.getElementById('sidebar');

toggleBtn.addEventListener('click', () => {
  console.log("Button clicked!");
  sidebar.classList.toggle('active');
});
