ClassicEditor
    .create(document.querySelector('.editor'), {})
    .then(editor => {
        window.editor = editor;
        const toolbarElement = editor.ui.view.toolbar.element;

        editor.enableReadOnlyMode('editor');
        toolbarElement.style.display = 'none';

        editor.on('change:isReadOnly', (evt, propertyName, isReadOnly) => {
            if (isReadOnly) {
                toolbarElement.style.display = 'none';
            } else {
                toolbarElement.style.display = 'flex';
            }
        });
    })
    .catch(error => {
        console.error(error);
    });

function updateNote(id) {
    let name = document.getElementById("name-note").value;
    let description = editor.getData();

    axios.put('/v1/notes/' + id, {
        name: name,
        description: description
    })
        .then(function () {
            $("#info-note").load(`/notes/${id} #info-note`);
            notifySuccessfulSaving();
        })
        .catch((err) => console.log(err.message));
}

function notifySuccessfulSaving() {
    const notifySuccess = document.getElementById('notifySuccess');
    const toast = new bootstrap.Toast(notifySuccess);
    toast.show();
}

function changeReadOnlyStatus() {
    editor.isReadOnly
        ? editor.disableReadOnlyMode("editor")
        : editor.enableReadOnlyMode("editor")
}
