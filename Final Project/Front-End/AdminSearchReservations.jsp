<jsp:useBean id="dataCustomerReservations" class="com.infmglproject.DataCustomerReservations" />
<%@ page import="com.infmglproject.DataCustomerReservations" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Reservations</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="CSS/admin/admin-custtable.css">
    <script src="https://kit.fontawesome.com/5f69afeadf.js" crossorigin="anonymous"></script>
    <link rel="shortcun icon" type="image/png" href="Images/xylo-logo-favicon.png">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://kit.fontawesome.com/5f69afeadf.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> <!-- Used for JQuery -->
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
    <div class="block1-container">
        <div class="block1-title" data-aos="fade-down" data-aos-duration="2000">
        <p>Reservations</p>
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
            <button id="add-btn" data-aos="zoom-in" data-aos-duration="1000"><i class="fa fa-plus"></i> &nbsp; Add Reservation</button>
            <button id="clear-reservations-btn" class="button2" data-aos="zoom-in" data-aos-duration="1500"><i class="fa fa-eraser"></i> &nbsp; Clear All Reservations</button>
            <!-- Hidden: <input type="checkbox" name="" id="del-all-box" data-aos="zoom-in" data-aos-duration="1500">-->
            <!-- Function to clear all accounts on the table: -->
			<script>
			  $(document).ready(function() {
			    $('#clear-reservations-btn').click(function() {
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
			            url: 'clearAllReservations',
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
            <button class="view" id="view-all-btn" ><i class="fa fa-eye"></i></button>
            <!-- Function to view all accounts on the table: -->
			<script>
			  $(document).ready(function() {
			    $('#view-all-btn').click(function() {
			      // Send the AJAX request to the 'AdminCustomerAccounts' servlet:
			      $.ajax({
			        url: 'AdminCustomerReservations',
			        type: 'GET',
			        success: function(response) {
			        	// Goes back to the default AdminCustomerReservations page:
			        	window.location.href = "AdminCustomerReservations.jsp";
			        },
			        error: function(xhr, status, error) {
			          console.log('Error: ' + error);
			        }
			      });
			    });
			  });
			</script>
            
            <input type="text" id="search-input" placeholder="Enter Reservation Details">
            <button class="search" id="search-btn">Search</button>
            <!-- Function to search accounts on the table: -->
            <script>
			$(document).ready(function() {
			    $("#search-btn").click(function() {
			    	// Get the search term from the input field:
			        var searchTerm = $("#search-input").val(); 
			        $.ajax({
			            url: "adminSearchReservations", // Sends to the servlet that has the "adminSearchAccounts" WebAnnotation
			            data: { searchTerm: searchTerm },
			            type: "GET",
			            success: function(response) {
			                // Sends the user to the search results page:
			            	window.location.href = "AdminSearchReservations.jsp";
			                
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
    <div class="table-box" data-aos="fade-up" data-aos-duration="2000">
        <table>
            <tr>
                <th>Rsrv No. <!--<span class="icon-arrow">&UpArrow;--></span></th>
                <th>Name <!--<span class="icon-arrow">&UpArrow;</span>--></th>
                <th>Username <!--<span class="icon-arrow">&UpArrow;</span>--></th>
                <th>Email <!--<span class="icon-arrow">&UpArrow;</span>--></th>
                <th>Contact No. <!--<span class="icon-arrow">&UpArrow;</span>--></th>
                <th>Date <!--<span class="icon-arrow">&UpArrow;</span>--></th>
                <th>Time <!--<span class="icon-arrow">&UpArrow;</span>--></th>
                <th>No. of Guests <!--<span class="icon-arrow">&UpArrow;</span>--></th>
                <th>No. of Tables <!--<span class="icon-arrow">&UpArrow;</span>--></th>
                <th>Payment Method <!--<span class="icon-arrow">&UpArrow;</span>--></th>
            </tr>
            <tbody id="table-data">
	            <% 
	        		String searchTerm = null;
	        		String searchTermSession = (String)session.getAttribute("searchTerm");
	        		String searchTermParameter = request.getParameter("searchTerm");
	        		
	        		if (searchTermParameter != null) {
	        		    // set session attribute
	        		    session.setAttribute("searchTerm", searchTermParameter);
	        		    searchTerm = searchTermParameter;
	        		} else if (searchTermSession != null) {
	        		    searchTerm = searchTermSession;
	        		}
	            %>
	            <% List <DataCustomerReservations> dataList = DataCustomerReservations.getSearchList(request, response, searchTerm);%>
					<% for (DataCustomerReservations data : dataList) {%>
				      <tr>
				        <td><%= data.getID() %></td>
				        <td><%= data.getName() %></td>
				        <td><%= data.getUsername() %></td>
				        <td><%= data.getEmail() %></td>
				        <td><%= data.getContactNumber() %></td>
				        <td><%= data.getDate() %></td>
				        <td><%= data.getTime() %></td>	
				        <td><%= data.getNumberOfGuests() %></td>	
				        <td><%= data.getNumberOfTables() %></td>
				        <td><%= data.getPayment() %></td>
				        <td><a href="javascript:void(0);" class="delete-row" data-ID="<%= data.getID() %>"><i class="fa fa-trash"></i></a></td>	 			        
				      </tr>
					<% } %>
            </tbody>
        </table>
        
        <!-- Function to delete reservation row on the table: -->
        <script>
		$(document).ready(function() {
		  // Handle delete button clicks
		  $(".delete-row").click(function() {
		    // Get the id of the row to delete
		    var ID = $(this).data("id");
		
		    swal({
		      title: "Are you sure?",
		      text: "Once deleted, you will not be able to recover this reservation!",
		      icon: "warning",
		      buttons: true,
		      dangerMode: true,
		    }).then((willDelete) => {
		      if (willDelete) {
		        // Submit a POST request to the servlet to delete the row
		        $.post("CancelReservation", {ID: ID}, function() {
		          // Show success message
		          swal({
		            title: "Reservation cancelled successfully!",
		            icon: "success",
		          }).then(() => {
		            location.reload();
		          });
		        }).fail(function() {
		          swal({
		            title: "Error",
		            text: "There was an error cancelling the reservation. Please try again later.",
		            icon: "error",
		          });
		        });
		      } 
		    });
		  });
		});
        </script>

        <!-- Input Window -->
        <div class="modal">
            <i class="fa fa-times-circle close-icon"></i>
            <div class="left-modal">
                <div class="img-box">
                    <img src="Images/xylo-logo-favicon - Copy.png" width="150" height="120" alt="Xylo Logo">
                </div>
            </div>
            <div class="right-modal">
                <form method="post" action="addReservation" id="register-form">
                    <div class="input-box">
                        <label for="name">Name</label>
                        <input type="text" name="name" id="name" required>
                    </div>
                    <div class="input-box">
                        <label for="email">Email</label>
                        <input type="text" name="email" id="email" required>
                    </div>
                    <div class="input-box">
                        <label for="tel">Contact No.</label>
                        <input input type="tel" class="tel" name="tel" id="tel" min="11" max="11" required>
                    </div>
                    <div class="input-box">
                        <label for="datetimeLocal">Date & Time</label>
                        <input type="datetime-local" class="datetimeLocal" name="datetimeLocal" id="datetimeLocal" required>
                    </div>
                    <div class="input-box">
                        <label for="numberg">Guests No.</label>
                        <input type="number" class="numberg" name="numberg" id="numberg" min="1" max="200" required>
                    </div>
                    <div class="input-box">
                        <label for="numbert">Tables No.</label>
                        <input type="number" class="numbert" name="numbert" id="numbert" min="0" max="35" required>
                    </div>
                    <div class="input-box">
                        <label for="payment">Payment Method</label>
                        <select class=payment name="payment" id="payment" required>
                            <option disabled selected>Select Payment Method</option>
                            <option value="cash">Cash</option>
                            <option value="gcash">Gcash</option>
                            <option value="paymaya">PayMaya</option>
                            <option value="mastercard">Master Card</option>
                            <option value="visa">Visa</option>
                        </select>
                    </div>
                    <div class="input-box">
                        <button class="register-button" id="update-btn">Register</button>
                        <button disabled="disabled" class="update-button" id="update-btn">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!--Script-->
    <script src="JS/admin/admin-custtable.js"></script>
    <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
    <script>
        AOS.init();
    </script>
    
    <!-- Alert message if reservation is successful or unsuccessful, and for error handling: -->
	<%
		//Alert message if reservation is successful
		if (request.getAttribute("status") == "success") {
			%>
		    <script type="text/javascript">
		    	swal("Reservation is successful", "Thank you for choosing Xylo!", "success");
		    </script>
			<%
		} 
		// Alert message if reservation is full:
		else if (request.getAttribute("reservationIsFull") == "failed") {
			%>
		    <script type="text/javascript">
		    	swal("Reservation Failed", "Reservation is full. Please choose a different date and time.", "error");
		    </script>
			<%
		} 
		// Alert message if session has expired:
		else if (request.getAttribute("sessionExpired") == "failed") {
			%>
		    <script type="text/javascript">
			    swal({
			        title: "Session Expired!",
			        text: "Please sign in again to your account.",
			        type: "error",
			        confirmButtonText: "OK"
			    }).then(function() {
			        // Redirect to login page
			        window.location.href = "AdminSignIn.jsp";
			    });	
		    </script>
			<%
		} 
		else if (request.getAttribute("status") == "failed") {
			%>
		    <script type="text/javascript">
		    	swal("Reservation Failed", "An error occured. Please try again later.", "error");
		    </script>
			<%
		}	
	%>
</body>
</html>