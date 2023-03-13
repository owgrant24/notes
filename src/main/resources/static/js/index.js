let addCategoryForm = document.getElementById("add-category");
addCategoryForm.addEventListener("submit", (e) => {
    e.preventDefault();

    let name = document.getElementById("new-category-name").value;
    let description = document.getElementById("new-category-description").value;
    let page = addCategoryForm.getAttribute('pageNumber');

    axios.post('/v1/categories/', {
        name: name, description: description
    })
        .then(function () {
            $('#add-category').trigger('reset');
            updatePartPage(page);
        })
        .catch((err) => console.log(err.message));
});

const delCategory = document.getElementById('del-category')
delCategory.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget

    const catId = button.getAttribute('data-bs-category-id')

    const element = delCategory.querySelector('.modal-footer button[name=del]')

    element.setAttribute("category-id", catId)
})

function deleteCategory(id, page) {
    axios.delete('/v1/categories/' + id)
        .then(function () {
            updatePartPage(page);
        })
        .catch((err) => console.log(err.message));
}

// Обновление части страницы
function updatePartPage(page) {
    $("#categories").load(`/?page=${page} #categories`);
}