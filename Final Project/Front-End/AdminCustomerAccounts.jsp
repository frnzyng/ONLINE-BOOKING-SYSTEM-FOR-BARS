<jsp:useBean id="AdminCustomerAccounts" class="com.infmglproject.AdminCustomerAccounts" />
<%@ page import="com.infmglproject.DataCustomerAccounts" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.io.ByteArrayInputStream" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <title>Customer Accounts</title>
    <link rel="stylesheet" href="CSS/admin/admin-acc.css">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://kit.fontawesome.com/5f69afeadf.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- Used for JQuery -->
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
</head>
<body>

    <!-- Navbar -->
    <header>
        <img src="Images/xylo-logo-favicon - Copy.png" width="150" class="header-logo">
        <input type="checkbox" id="nav-toggle" class="nav-toggle">
        <nav>
            <ul>
                <li><a href="javascript:void(null);" class="nav-main" onclick="toggleMenu()"><i class="fa-solid fa-circle-user fa-2x"></i></a></li>
            </ul>
            <div class="sub-menu-wrap" id="subMenu">
                <div class="sub-menu">
                    <div class="user-info"></div>
                    <a href="AdminCustomerReservations.jsp" class="sub-menu-link">
                        <p>Reservations</p>
                        <span></span>
                    </a>
                    <a href="AdminCustomerAccounts.jsp" class="sub-menu-link">
                        <p>Customer Accounts</p>
                        <span></span>
                    </a>
                    <a href="AdminSignIn.jsp" class="sub-menu-link">
                        <p>Sign Out</p>
                        <span></span>
                    </a>
                </div>
            </div>
        </nav>
        <label for="nav-toggle" class="nav-toggle-label">
            <span></span>
        </label>
        <div id="progress-bar"></div>
    </header>

    <div class="pseudoblock"></div>

    <!--Title and Queries Field-->
    <div class="block1-container" data-aos="fade-down" data-aos-duration="2000">
        <div class="block1-title">
        <p>Customer Accounts</p>
        </div>
        <!-- Hidden:
        <div class="queries" data-aos="zoom-in" data-aos-duration="2000">
            <input type="text" placeholder="Enter Query">
            <button>Execute</button>
        </div>
        -->
    </div>

    <!--Add, Delete, View and Filter (Select)-->
    <div class="upper-btn-box">
        <div class="left-box">
            <button id="add-btn"  data-aos="zoom-in" data-aos-duration="1000"><i class="fa fa-plus"></i> &nbsp; Add Account</button>
            <button id="clear-accounts-btn" class="button2" data-aos="zoom-in" data-aos-duration="1500"><i class="fa fa-eraser"></i> &nbsp; Clear All Accounts</button>
            <!-- Hidden: <input type="checkbox" name="" id="del-all-box"  data-aos="zoom-in" data-aos-duration="1500"> -->
			<!-- Function to clear all accounts on the table: -->
			<script>
			  $(document).ready(function() {
			    $('#clear-accounts-btn').click(function() {
			      swal({
			        title: "Are you sure?",
			        text: "Once deleted, you will not be able to recover this table!",
			        icon: "warning",
			        buttons: true,
			        dangerMode: true,
			      })
			      .then((willDelete) => {
			        if (willDelete) {
			          // Send the AJAX request to the 'ClearAllAccounts' servlet:
			          $.ajax({
			            url: 'clearAllAccounts',
			            type: 'GET',
			            success: function(response) {
			              swal("Table deleted successfully!", {
			                icon: "success",        
			              });
			            },
			            error: function(xhr, status, error) {
			              swal("Error deleting table!", xhr.responseText, "error");
			            }
			          });
			        }
			      });
			    });
			  });
			</script>
        </div>
        <div class="right-box" data-aos="zoom-in" data-aos-duration="1500">
            <button class="view" id="view-all-btn"><i class="fa fa-eye"></i></button>		
			<!-- Function to view all accounts on the table: -->
			<script>
			  $(document).ready(function() {
			    $('#view-all-btn').click(function() {
			      // Send the AJAX request to the 'AdminCustomerAccounts' servlet:
			      $.ajax({
			        url: 'AdminCustomerAccounts',
			        type: 'GET',
			        success: function(response) {
			        	window.location.href = "AdminCustomerAccounts.jsp";
			        },
			        error: function(xhr, status, error) {
			          console.log('Error: ' + error);
			        }
			      });
			    });
			  });
			</script>
			
            <input type="text" id="search-input" placeholder="Enter Account Details">
            <button class="search" id="search-btn">Search</button>
            <!-- Function to search accounts on the table: -->
            <script>
			$(document).ready(function() {
			    $("#search-btn").click(function() {
			    	// Get the search term from the input field:
			        var searchTerm = $("#search-input").val(); 
			        $.ajax({
			            url: "AdminCustomerAccounts",
			            data: { searchTerm: searchTerm },
			            type: "GET",
			            success: function(response) {
			                // Sends the user to the search results page:
			            	window.location.href = "AdminSearchAccounts.jsp";
			                
			            },
			            error: function(jqXHR, textStatus, errorThrown) {
			                console.log(textStatus, errorThrown);
			            }
			        });
			    });
			});
			</script>
        </div>
    </div>

    <!-- Table -->
    <!-- Table data obtained from DataCustomerAccounts.getDataList.java -->
    <div class="table-box" data-aos="fade-up" data-aos-duration="2000">
        <table>
            <tr>
                <th>No.</th>
                <th>Name</th>
                <th>Username</th>
                <th>Email</th>
                <th>Contact No.</th>
                <th>Password</th>
                <th>Birthdate</th>
                <th>Valid ID</th>
            </tr>
            <tbody id="table-data">
            	<% List <DataCustomerAccounts> dataList = DataCustomerAccounts.getDataList(request, response);%>
				<% for (DataCustomerAccounts data : dataList) {%>
			      <tr>
			        <td><%= data.getID() %></td>
			        <td><%= data.getName() %></td>
			        <td><%= data.getUsername() %></td>
			        <td><%= data.getEmail() %></td>
			        <td><%= data.getContactNumber() %></td>
			        <td><%= data.getPassword() %></td>
			        <td><%= data.getBirthdate() %></td>		        
			        <% 
			        ByteArrayInputStream bais = data.getValidID();
			        byte[] validIDBytes = bais.readAllBytes(); %>
			        <td><img src="data:image/jpeg;base64, <%= Base64.getEncoder().encodeToString(validIDBytes) %>" style="width: 50px; height: 50px;"/></td>
			      	<td><a href="javascript:void(0);" class="delete-row" data-ID="<%= data.getID() %>"><i class="fa fa-trash"></i></a></td>	 
			      </tr>
				<% } %>
            </tbody>
        </table>
              
        <!-- For displaying the image after upload: -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
        $(document).ready(function(){
        	  $('#upload').on('change', function(){
        	    var file = this.files[0];
        	    var reader = new FileReader();
        	    reader.onload = function(event){
        	      $('#preview').attr('src', event.target.result);
        	    }
        	    reader.readAsDataURL(file);
        	  });
        });
		</script>
		
		<!-- Function to delete account row on the table: -->
        <script>
		$(document).ready(function() {
		  // Handle delete button clicks
		  $(".delete-row").click(function() {
		    // Get the id of the row to delete
		    var ID = $(this).data("id");
		
		    swal({
		      title: "Are you sure?",
		      text: "Once deleted, you will not be able to recover this account!",
		      icon: "warning",
		      buttons: true,
		      dangerMode: true,
		    }).then((willDelete) => {
		      if (willDelete) {
		        // Submit a POST request to the servlet to delete the row
		        $.post("DeleteAccount", {ID: ID}, function() {
		          // Show success message
		          swal({
		            title: "Account deleted successfully!",
		            icon: "success",
		          }).then(() => {
		            location.reload();
		          });
		        }).fail(function() {
		          swal({
		            title: "Error",
		            text: "There was an error deleting the account. Please try again later.",
		            icon: "error",
		          });
		        });
		      } 
		    });
		  });
		});
        </script>
	</div>

        <!-- Input Window -->
        <!-- Note: Linagay ko na lang sa loob ng form yung upload valid id, nahirapan kasi ako i-configure kapag nasa labas siya -->
        <form method="post" action="addCustomerAccount" id="register-form" enctype="multipart/form-data">
        <div class="modal">
            <i class="fa fa-times-circle close-icon"></i>
            <div class="left-modal">
                <div class="img-box">
                    <img src="Images/image-placeholder.png" id="preview" width="150" height="150" alt="Profile Picture">
                    <input type="file" name="validID" id="upload" required>
                    <p>Valid ID</p>
                </div>
            </div>
            <div class="right-modal">              
                    <div class="input-box">
                        <label for="name">Name</label>
                        <input type="text" name="name" id="name" required>
                    </div>
                    <div class="input-box">
                        <label for="numbert">Username</label>
                        <input type="text" name="username" class="username" id="username" required>
                    </div>
                    <div class="input-box">
                        <label for="email">Email</label>
                        <input type="text" name="email" id="email" required>
                    </div>
                    <div class="input-box">
                        <label for="tel">Contact No.</label>
                        <input input type="tel" name="contactNumber" class="tel" id="tel" min="11" max="11" required>
                    </div>
                    <div class="input-box">
                        <label for="numberg">Password</label>
                        <input type="password" name="password" class="password" id="password" min-length="8" required>
                    </div>
                    <div class="input-box">
                        <label for="date">Birthdate</label>
                        <input type="date" name="birthdate" class="date" id="date" required>
                    </div>
                    <div class="input-box">
                        <button class="register-button" id="update-btn">Register</button>
                        <button disabled="disabled" class="update-button" id="update-btn">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- Script -->
    <script src="JS/admin/admin-acc.js"></script>
    <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
    <script>
        AOS.init();
    </script>
	
	<!-- Alert messages for the following error handling: -->
	<%
		//Alert message if fields are empty
		if (request.getAttribute("fieldsEmpty") == "empty") {
			%>
		    <script type="text/javascript">
		    	swal("Registration Failed", "Please fill up all the fields.", "error");
		    </script>
			<%
		} 
		// Alert message if the user is a minor:
		else if (request.getAttribute("ageStatus") == "minor") {
			%>
		    <script type="text/javascript">
		    	swal("Registration Failed", "You are currently under age! Come back when you're older.", "error");
		    </script>
			<%
		} 
		// Alert message if password doesn't match:
		else if (request.getAttribute("passwordMatch") == "failed") {
			%>
		    <script type="text/javascript">
		    	swal("Registration Failed", "Password doesn't match.", "error");
		    </script>
			<%
		} 
		// Alert message if username and email exists:
		else if (request.getAttribute("usernameExists") == "failed") {
			%>
		    <script type="text/javascript">
		    	swal("Registration Failed", "The username already exists!", "error");
		    </script>
			<%
		} 
		else if (request.getAttribute("emailExists") == "failed") {
			%>
		    <script type="text/javascript">
		    	swal("Registration Failed", "The email already exists!", "error");
		    </script>
			<%
		}	
		// Alert message if sign up is successful or unsuccessful
		else if (request.getAttribute("status") == "success") {
			%>
			<script type="text/javascript">
				swal("Registration Success!", "Welcome to Xylo!", "success");
			</script>
			<%
		}
		else if (request.getAttribute("status") == "failed") {
			%>
			<script type="text/javascript">
				swal("Registration Failed", "Something went wrong. Please try again later.", "error");
			</script>
			<%			
		}
	%>  
</body>
</html>