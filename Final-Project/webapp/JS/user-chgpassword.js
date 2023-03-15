document.querySelector(".button").onclick = function() {
    var password = document.querySelector("#password").value,
        confirmPassword = document.querySelector("#confirm-password").value;

        if(password == "") {
            alert("Fields cannot be empty.");
        } else if(password != confirmPassword) {
            alert("Passwords do not match! Try again.");
            return false;
        } else if(password == confirmPassword) {
            alert("Password Successfully Changed");
        }
        return true
}