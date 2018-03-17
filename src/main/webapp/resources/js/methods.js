function postForm() {
    var name = document.post.name.value;
    if (name == null || name == "") {
        alert("Enter your Name!");
        return false;
    }
    var message = document.post.message.value;
    if (message == null || message == "") {
        alert("Enter a Message!");
        return false;
    }
    return true;
}