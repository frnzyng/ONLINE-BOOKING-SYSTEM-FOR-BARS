//Drop-down Account
let subMenu = document.getElementById("subMenu");

function toggleMenu(){
    subMenu.classList.toggle("open-menu");
}

/* Control Coding */
var registerForm = document.querySelector("#register-form");
var allInput = registerForm.querySelectorAll("INPUT");
var addBtn = document.querySelector("#add-btn");
var modal = document.querySelector(".modal");
var closeBtn = document.querySelector(".close-icon");
addBtn.onclick = function(){
    modal.classList.add("active");
}
closeBtn.addEventListener("click", ()=> {
    modal.classList.remove("active");
    var i;
    for(i=0; i<allInput.length; i++) {
        allInput[i].value = "";
    }
})

/* Start global variable */
var userData = [];
var nameCs = document.querySelector("#name");
var usernameCs = document.querySelector("#username");
var emailCs = document.querySelector("#email");
var telCs = document.querySelector("#tel");
var passwordCs = document.querySelector("#password");
var dateCs = document.querySelector("#date");
var registerBtn = document.querySelector("#register-btn");
var updateBtn = document.querySelector("#update-btn");
var registerForm = document.querySelector("#register-form");
var imgUrl;

/* End */

/* Register Coding */

registerBtn.onclick = function(e){
    e.preventDefault();
    registrationData();
    getDataFromLocal();
    registerForm.reset('');
    closeBtn.click();
}

if(localStorage.getItem("userData") != null) {
    userData = JSON.parse(localStorage.getItem("userData"));
}

function registrationData() {
    userData.push({
        name : nameCs.value,
        username : usernameCs.value,
        email : emailCs.value,
        tel : telCs.value,
        password : passwordCs.value,
        date : dateCs.value,
        validID : imgUrl == undefined ? "../../Images/image-placeholder.png" : imgUrl
    });

    var userString = JSON.stringify(userData);
    localStorage.setItem("userData", userString);
    swal("Registration Success!", "Customer Account Registered", "success");
}

/* Return Data from Local Storage */
var tableData = document.querySelector("#table-data");
const getDataFromLocal = () =>{
    tableData.innerHTML = "";
    userData.forEach((data, index)=>{
        tableData.innerHTML += `
        <tr index='${index}'> 
        <td>${index+1}</td>
        <td>${data.name}</td>
        <td>${data.username}</td>
        <td>${data.email}</td>
        <td>${data.tel}</td>
        <td>${data.password}</td>
        <td>${data.date}</td>
        <td><img src="${data.validID}" width="40" height"40"></td>
        <td>
            <button class="edit-btn"><i class="fa fa-eye"></i></button>
            <button class="table-button2 del-btn"><i class="fa fa-trash"></i></button></td>
        </tr>`;
    });

    /* Delete Button */
    var i;
    var allDelBtn = document.querySelectorAll(".del-btn")
    for(i=0; i<allDelBtn.length; i++) {
        allDelBtn[i].onclick = function() {
            var tr = this.parentElement.parentElement;
            //tr.remove();
            var id = tr.getAttribute("index");
            swal({
                title: "Are you sure?",
                text: "Once deleted, you will not be able to recover this account.",
                icon: "warning",
                buttons: true,
                dangerMode: true,
              })
              .then((willDelete) => {
                if (willDelete) {
                    userData.splice(id, 1);
                    localStorage.setItem("userData", JSON.stringify(userData));
                    tr.remove();
                    swal("Account successfully removed!", {
                    icon: "success",
                  });
                } else {
                  swal("Account not removed.");
                }
              });              
        }
    }

    /* Update Button */
    var allEdit = document.querySelectorAll(".edit-btn");
    for(i=0; i<allEdit.length; i++) {
        allEdit[i].onclick = function() {
            var tr = this.parentElement.parentElement;
            var td = tr.getElementsByTagName("TD");
            var index = tr.getAttribute("index");
            var name = td[1].innerHTML;
            var username = td[2].innerHTML;
            var email = td[3].innerHTML;
            var tel = td[4].innerHTML;
            var password = td[5].innerHTML;
            var date = td[6].innerHTML;
            var imgTag = td[7].getElementsByTagName("IMG");
            var validID = imgTag[0].src;
            addBtn.click();
            registerBtn.disabled = true;
            updateBtn.disabled = false;
            nameCs.value = name;
            usernameCs.value = username;
            emailCs.value = email;
            telCs.value = tel;
            passwordCs.value = password;
            dateCs.value = date;
            valid_id.src = validID;
            updateBtn.onclick = function(e) {
                userData[index] = {
                    name : nameCs.value,
                    username : usernameCs.value,
                    email : emailCs.value,
                    tel : telCs.value,
                    password : passwordCs.value,
                    date : dateCs.value,
                    validID : validID.value == "" ? valid_id.src : imgUrl
                }
                localStorage.setItem("userData", JSON.stringify(userData));
            }
        }
    }
}
getDataFromLocal();

// Image (Valid ID) Processing 
var valid_id = document.querySelector("#valid-id");
var uploadPic = document.querySelector("#upload-field");
uploadPic.onchange = function(){ 
    if(uploadPic.files[0].size < 1000000) {
        var fReader = new FileReader();
        fReader.onload = function(e) {
            imgUrl = e.target.result;
            valid_id.src = imgUrl;
            console.log(imgUrl);
        }
        fReader.readAsDataURL(uploadPic.files[0]);
    } else {
        alert("File Size is Too Large");
    }
}

// Search Bar
var searchCs = document.querySelector("#empId");
searchCs.oninput = function() {
    searchFuc();
}

function searchFuc() {
    var tr = tableData.querySelectorAll("TR");
    var filter = searchCs.value.toLowerCase();
    var i;
    for(i=0; i<tr.length; i++) {
        var name = tr[i].getElementsByTagName("TD")[1].innerHTML; //What it looks for
        var username = tr[i].getElementsByTagName("TD")[2].innerHTML;
        var email = tr[i].getElementsByTagName("TD")[3].innerHTML;
        var tel = tr[i].getElementsByTagName("TD")[4].innerHTML;
        var password = tr[i].getElementsByTagName("TD")[5].innerHTML;
        var date = tr[i].getElementsByTagName("TD")[6].innerHTML;
        if(name.toLowerCase().indexOf(filter) > -1){
            tr[i].style.display = "";
        }else if(username.toLowerCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
        }else if(email.toLowerCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
        }else if(tel.toLowerCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
        }else if(password.toLowerCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
        }else if(date.toLowerCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
        }else {
            tr[i].style.display = "none";
        }
    }
}

/* Clear All Reservations */
var delAllBtn = document.querySelector("#del-all-btn");
var allDelBox = document.querySelector("#del-all-box");
delAllBtn.addEventListener('click', () => {
    if(allDelBox.checked == true) {
        swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover these accounts.",
            icon: "warning",
            buttons: true,
            dangerMode: true,
          })
          .then((willDelete) => {
            if (willDelete) {
                localStorage.removeItem("userData");
                window.location = location.href;
                swal("Accounts successfully deleted!", {
                icon: "success",
              });
            } else {
              swal("Accounts not removed.");
            }
          });
    } else {
        swal("Confirm Deletion", "Check the right box to clear all accounts.", "warning");
    }
})