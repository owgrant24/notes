function updateCategory(id) {
    let name = document.getElementById("name-category").value;
    let description = document.getElementById("content-category").value;

    axios.put('/v1/categories/' + id, {
        name: name,
        description: description
    })
        .then(function () {
            $("#info-category").load(`/categories/${id} #info-category`);
            notifySuccessfulSaving();
        })
        .catch((err) => console.log(err.message));
}

function notifySuccessfulSaving() {
    const notifySuccess = document.getElementById('notifySuccess');
    const toast = new bootstrap.Toast(notifySuccess);
    toast.show();
}