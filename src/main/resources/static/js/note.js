function updateNote(id) {
    let name = document.getElementById("name-note").value;
    let description = document.getElementById("content-note").value;

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