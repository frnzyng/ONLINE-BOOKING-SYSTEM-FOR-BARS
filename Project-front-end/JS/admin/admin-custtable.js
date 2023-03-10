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
var emailCs = document.querySelector("#email");
var telCs = document.querySelector("#tel");
var datetimeLocalCs = document.querySelector("#datetimeLocal");
var numbergCs = document.querySelector("#numberg");
var numbertCs = document.querySelector("#numbert");
var paymentCs = document.querySelector("#payment");
var registerBtn = document.querySelector("#register-btn");
var updateBtn = document.querySelector("#update-btn");
var registerForm = document.querySelector("#register-form");

/* End */

/* Register Button */
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
        email : emailCs.value,
        tel : telCs.value,
        datetimeLocal : datetimeLocalCs.value,
        numberg : numbergCs.value,
        numbert : numbertCs.value,
        payment : paymentCs.value,
    });

    var userString = JSON.stringify(userData);
    localStorage.setItem("userData", userString);
    swal("Registration Success!", "Customer Reservation Registered", "success");
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
        <td>${data.email}</td>
        <td>${data.tel}</td>
        <td>${data.datetimeLocal}</td>
        <td>${data.numberg}</td>
        <td>${data.numbert}</td>
        <td>${data.payment}</td>
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
                text: "Once deleted, you will not be able to recover this reservation.",
                icon: "warning",
                buttons: true,
                dangerMode: true,
              })
              .then((willDelete) => {
                if (willDelete) {
                    userData.splice(id, 1);
                    localStorage.setItem("userData", JSON.stringify(userData));
                    tr.remove();
                    swal("Reservation successfully removed!", {
                    icon: "success",
                  });
                } else {
                  swal("Reservation not removed.");
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
            var email = td[2].innerHTML;
            var tel = td[3].innerHTML;
            var datetimeLocal = td[4].innerHTML;
            var numberg = td[5].innerHTML;
            var numbert = td[6].innerHTML;
            var payment = td[7].innerHTML;
            addBtn.click();
            registerBtn.disabled = true;
            updateBtn.disabled = false;
            nameCs.value = name;
            emailCs.value = email;
            telCs.value = tel;
            datetimeLocalCs.value = datetimeLocal;
            numbergCs.value = numberg;
            numbertCs.value = numbert;
            paymentCs.value = payment;
            updateBtn.onclick = function(e) {
                userData[index] = {
                    name : nameCs.value,
                    email : emailCs.value,
                    tel : telCs.value,
                    datetimeLocal : datetimeLocalCs.value,
                    numberg : numbergCs.value,
                    numbert : numbertCs.value,
                    payment : paymentCs.value,
                }
                localStorage.setItem("userData", JSON.stringify(userData));
            }
        }
    }
}
getDataFromLocal();

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
        var email = tr[i].getElementsByTagName("TD")[2].innerHTML;
        var tel = tr[i].getElementsByTagName("TD")[3].innerHTML;
        var datetimeLocal = tr[i].getElementsByTagName("TD")[4].innerHTML;
        var numberg = tr[i].getElementsByTagName("TD")[5].innerHTML;
        var numbert = tr[i].getElementsByTagName("TD")[6].innerHTML;
        var payment = tr[i].getElementsByTagName("TD")[7].innerHTML;
        if(name.toLowerCase().indexOf(filter) > -1){
            tr[i].style.display = "";
        }else if(email.toLowerCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
        }else if(tel.toLowerCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
        }else if(datetimeLocal.toLowerCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
        }else if(numberg.toLowerCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
        }else if(numbert.toLowerCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
        }else if(payment.toLowerCase().indexOf(filter) > -1) {
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
            text: "Once deleted, you will not be able to recover these reservations.",
            icon: "warning",
            buttons: true,
            dangerMode: true,
          })
          .then((willDelete) => {
            if (willDelete) {
                localStorage.removeItem("userData");
                window.location = location.href;
                swal("Reservations successfully deleted!", {
                icon: "success",
              });
            } else {
              swal("Reservation not removed.");
            }
          });
    } else {
        swal("Confirm Deletion", "Check the right box to clear all reservations.", "warning");
    }
})

// Sort/Order OnClick Animate
const table_headings = document.querySelectorAll('th');

table_headings.forEach((head)=> {
    let sort_asc = true;
    head.onclick = () => {
        table_headings.forEach(head => head.classList.remove('active'));
        head.classList.add("active");

        head.classList.toggle('asc', sort_asc);
        sort_asc = head.classList.contains('asc') ? false : true;

    } 
})
