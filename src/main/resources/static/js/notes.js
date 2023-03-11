function addNote(categoryId) {
    let name = document.getElementById("note-name").value;
    let description = document.getElementById("note-description").value;

    if (name.length === 0) {
        alert("Поле 'Имя' не заполнено. Заметка не сохранена")
        return
    }

    axios.post('/v1/notes/', {
        name: name,
        description: description,
        categoryId: categoryId
    })
        .then(function () {
            $('#form-add-note').trigger('reset');
            $("#notes").load(`/notes/categories/${categoryId} #notes`);
        })
        .catch((err) => console.log(err.message));
}

const delNote = document.getElementById('del-note')
delNote.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget

    const catId = button.getAttribute('data-bs-category-id')
    const noteId = button.getAttribute('data-bs-note-id')

    const element = delNote.querySelector('.modal-footer button[name=del]')

    element.setAttribute("category-id", catId)
    element.setAttribute("note-id", noteId)
})

function deleteNote(id, categoryId) {
    axios.delete('/v1/notes/' + id)
        .then(function () {
            $("#notes").load(`/notes/categories/${categoryId} #notes`);
        })
        .catch((err) => console.log(err.message));
}