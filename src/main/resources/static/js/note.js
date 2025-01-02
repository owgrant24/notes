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
    let version = document.getElementById("note-version").innerText;
    let name = document.getElementById("name-note").value;
    let description = editor.getData();
    let v = Number(version) + 1

    axios.put('/v1/notes/' + id, {
        name: name,
        description: description,
        version: v
    })
        .then(function () {
            $("#info-note").load(`/notes/${id} #info-note`);
            notifySuccessfulSaving();
        })
        .catch((err) => {
            notifyFailedSaving(err.response.data);
            console.log(err.message);
        });
}

function notifySuccessfulSaving() {
    const notifySuccess = document.getElementById('notifySuccess');
    const toast = new bootstrap.Toast(notifySuccess);
    toast.show();
}

function notifyFailedSaving(message) {
    const notifyFailed = document.getElementById('notifyFailed');
    const toast = new bootstrap.Toast(notifyFailed);
    const toastBody = notifyFailed.querySelector('#notifyFailed .toast-body');
    toastBody.textContent = message
    toast.show();
}

function changeReadOnlyStatus() {
    editor.isReadOnly
        ? editor.disableReadOnlyMode("editor")
        : editor.enableReadOnlyMode("editor")
}
