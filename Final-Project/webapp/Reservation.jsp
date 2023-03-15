<jsp:useBean id="reservation" class="com.infmglproject.Reservation" />
<%@ page import="com.infmglproject.UserReservationDetails" %>
<%@ page import="com.infmglproject.CountGuestsTables" %>
<%@ page import="java.util.List" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Reservation</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="CSS/user-reserve.css">
    <script src="https://kit.fontawesome.com/5f69afeadf.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Counter-Up/1.0.0/jquery.counterup.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="shortcun icon" type="image/png" href="Images/xylo-logo-favicon.png">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
</head>
<body>

    <div id="parallax">

    <!-- Navbar -->
    <header>
        <img src="Images/Xylo-logo.png" width="191" class="header-logo">
        <input type="checkbox" id="nav-toggle" class="nav-toggle">
        <nav>
            <ul>
                <li><a href="Home.jsp">Home</a></li>
                <li><a href="About.jsp">About</a></li>
                <li><a href="Contact.jsp">Contact</a></li>
                <li><a href="javascript:void(null);" class="nav-main" onclick="toggleMenu()">Account</a></li>
            </ul>

            <div class="sub-menu-wrap" id="subMenu">
                <div class="sub-menu">
                    <div class="user-info"></div>
                    <a href="ChUsername.jsp" class="sub-menu-link">
                        <p>Change Username</p>
                        <span></span>
                    </a>
                    <a href="ChPassword.jsp" class="sub-menu-link">
                        <p>Change Password</p>
                        <span></span>
                    </a>
                    <a href="SignIn.jsp" class="sub-menu-link">
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

    <!--Block1 - Hero-->
    <div class="block1-container" data-aos="fade" data-aos-duration="1000">
        <div class="block1-content-wrapper">
            <h1 class="block1-heading">Book your event at the top nightlife destination in the country!</h1>
        </div>
    </div>
	
	<% int countTablesAvailable = CountGuestsTables.getTablesAvailable(request, response); %>
	<% int countYourTables = CountGuestsTables.getYourTables(request, response); %>
	<% int countYourGuests = CountGuestsTables.getYourGuests(request, response); %>
	<% int countReservations = CountGuestsTables.getReservations(request, response); %>
	
    <!--Block2 - Details-->
    <div class="block2-wrapper">
        <h1>Your Reservation</h1>
        <div class="wrapper">
            <div class="container" data-aos="zoom-in" data-aos-duration="1000">
                <i class="fas fa-wine-glass"></i>
                <span class="num" data-val="<%=countTablesAvailable%>"><%=countTablesAvailable%></span>
                <span class="text">Tables Available</span>
            </div>
            <div class="container" data-aos="zoom-in" data-aos-duration="1500">
                <i class="fa-solid fa-champagne-glasses"></i>
                <span class="num" data-val="<%=countYourTables%>"><%=countYourTables%></span>
                <span class="text">Your Tables</span>
            </div>
            <div class="container" data-aos="zoom-in" data-aos-duration="2000">
                <i class="fa-solid fa-users"></i>
                <span class="num" data-val="<%=countYourGuests%>"><%=countYourGuests%></span>
                <span class="text">Your Guests</span>
            </div>
            <div class="container" data-aos="zoom-in" data-aos-duration="2500">
                <i class="fa-solid fa-clipboard"></i>
                <span class="num" data-val="<%=countReservations%>"><%=countReservations%></span>
                <span class="text">Reservations</span>
            </div>
        </div>
    </div>

    <!--Block3 - Form-->
	<div class="block3-container">
		<div class="block3-wrapper">
			<div class="inner">
				<div class="block3-title">Form</div>
				<div class="block3-content">
					<form class="myTable" method="post" action="reservation">
						<div class="user-details">
							<p class="input-box">
								<span class="details">Name</span>
								<input type="text" class="name" name="name" id="name" placeholder="Enter Reserver Name" required>
							</p>
							<p class="input-box">
								<span class="details">Email</span>
								<input type="email" class="email" name="email" id="email" placeholder="Enter Email" required>
							</p>
							<p class="input-box">
								<span class="details">Contact No.</span>
								<input type="tel" class="tel" name="tel" id="tel" min="11" max="11" placeholder="Enter Reserver Contact No." required>
							</p>
							<p class="input-box">
								<span class="details">Date & Time</span>
								<input type="datetime-local" class="datetimeLocal" name="datetimeLocal" id="datetimeLocal" required>
							</p>
							<p class="input-box">
								<span class="details">Number of Guests</span>
								<input type="number" class="numberg" name="numberg" id="numberg" min="1" max="200" placeholder="Enter number of Guests" required>
							</p>
							<p class="input-box">
								<span class="details">Number of Tables</span>
								<input type="number" class="numbert" name="numbert" id="numbert" min="0" max="35" placeholder="Enter number of Tables" required>
							</p>
							<p class="drop-down">
								<span class="details">Payment Method</span>
								<select class=payment name="payment" id="payment" required>
									<option disabled selected value="">Select Payment Method</option>
									<option value="cash">Cash</option>
									<option value="gcash">Gcash</option>
									<option value="paymaya">PayMaya</option>
									<option value="mastercard">Master Card</option>
									<option value="visa">Visa</option>
								</select>
							</p>
							<button type="submit" class="save" id="save">Save</button> <!-- removed type="button" -->
						</div>
					</form>	
				</div>
			</div>
		</div>
	</div>

	<div class="inner overlow" data-aos="fade-up" data-aos-duration="1000">
		<!-- Table Title Code -->
		<div class="title">
			<h2>Reservation Details</h2>
		</div>
		<!-- Table Data Code -->	
		<table class="data" id="myData">
			<thead>
				<tr class="dataRow">
					<th>Id</th> 
					<th>Name</th>
					<th>Email</th>
					<th>Contact No.</th>
					<th>Date</th>
					<th>Time</th>
					<th>Guests</th>
					<th>Tables</th>
					<th>Payment Method</th>
				</tr>
			</thead>
			<tbody>
				<% List <UserReservationDetails> dataList = UserReservationDetails.getDataList(request, response); %>
				<% for (UserReservationDetails data : dataList) { %>
			      <tr>
			        <td><%= data.getID() %></td>
			        <td><%= data.getName() %></td>
			        <td><%= data.getEmail() %></td>
			        <td><%= data.getContact() %></td>
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
	</div>

    <!-- Footer -->
    <footer>
        <div class="footer-row">
            <div class="footer-col1">
                <img src="Images/Xylo-logo.png" alt="Xylo Logo" class="footer-logo">
                <p>
                    Palace is the greatest place to be. Submerge yourself deep into world-quality clubbing, par with best anywhere
                    &mdash; part of unique international elite clubs with levels of full experiences, everytime.
                </p>
            </div>
            <div class="footer-col">
                <h3 class="footer-title">Links</h3>
                <ul class="footer-links">
                    <li class="footer-links"><a href="Home.jsp" class="footer-links-content">Home</a></li>
                    <li class="footer-links"><a href="About.jsp" class="footer-links-content">About</a></li>
                    <li class="footer-links"><a href="Contact.jsp" class="footer-links-content">Contact</a></li>
                    <li class="footer-links"><a href="#" class="footer-links-content">Create Reservation</a></li>
                </ul>
            </div>
            <div class="footer-col">
                <h3 class="footer-title">Be Updated With Anything Xylo!</h3>

                <form class="form-email" action="https://formsubmit.co/idel.aranilla@gmail.com" method="POST">
                    <i class="fa-solid fa-envelopes-bulk"></i>
                    <input type="hidden" name="_autoresponse" value="Welcome to Xylo. Our Team will contact you shortly. See you soon!">
                    <input type="email" name="email" placeholder="  Enter your email ID" required>
                    <button type="submit"><i class="fa-footer fa-solid fa-arrow-right-from-bracket"></i></button>
                </form>

                <div class="footer-social-icons">
                    <a href="https://www.facebook.com/XYLOatThePalace/" target="_blank">
                        <i class="fa-brands fa-facebook-square"></i>
                    </a>
                    <a href="#">
                        <i class="fa-brands fa-twitter-square"></i>
                    </a>
                    <a href="https://www.instagram.com/xylomanila/?hl=en" target="_blank">
                        <i class="fa-brands fa-instagram-square"></i>
                    </a>
                    <a href="https://www.youtube.com/channel/UCC6grB_kGN2CwyBT9WTXTaA" target="_blank">
                        <i class="fa-brands fa-youtube"></i>
                    </a>
                    <a href="https://www.linkedin.com/company/the-palace-manila/" target="_blank">
                        <i class="fa-brands fa-linkedin"></i>
                    </a>
                </div>
            </div>
        </div>
        <hr>
        <p class="copyright">INF213 Hi-5 - NU-Manila (CTINFMGL) 2023-2024</p>
    </footer>

    <!-- Script -->
    <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
    <script src="JS/home-script.js"></script>
    <script src="JS/user-reserve.js"></script>
    <script>
        AOS.init();
    </script>
    
    <!-- Retrieves the following attributes to be used for the alert messages: -->
    <input type="hidden" id="status" value="<%= request.getAttribute("status") %>" >
    <input type="hidden" id="fieldsEmpty" value="<%= request.getAttribute("fieldsEmpty") %>" >
    <input type="hidden" id="pastDate" value="<%= request.getAttribute("pastDate") %>" >
    <input type="hidden" id="reservationIsFull" value="<%= request.getAttribute("reservationIsFull") %>" >
    <input type="hidden" id="reservationExist" value="<%= request.getAttribute("reservationExist") %>" >
    <input type="hidden" id="sessionExpired" value="<%= request.getAttribute("sessionExpired") %>" >
    
    <!--Alert message if reservation is successful or unsuccessful:-->
	<script type="text/javascript">
		var status = document.getElementById("status").value;
		var fieldsEmpty = document.getElementById("fieldsEmpty").value;
		var pastDate = document.getElementById("pastDate").value;
		var reservationIsFull = document.getElementById("reservationIsFull").value;
		var reservationExist = document.getElementById("reservationExist").value;
		var sessionExpired = document.getElementById("sessionExpired").value;
		
		// Alert message if reservation is successful:
		if (status == "success") {
			window.location.href = "ReservationSuccess.jsp";
		}
		// Alert message if fields are empty:
		else if (fieldsEmpty == "empty") {
			swal("Reservation Failed", "Please fill up all the fields.", "error");
		}
		// Alert message if reservation date is a past date:
		else if (pastDate == "past") {
			swal("Reservation Failed", "Date cannot be set on the past.", "error");
		}
		// Alert message if reservation is full:
		else if (reservationIsFull == "failed") {
			swal("Reservation Failed", "Reservation is full. Please choose a different date and time.", "error");
		}
		// Alert message if reservation exists:
		else if (reservationExist == "failed") {
			swal("Reservation Failed", "One reservation at a time only. You already have a reservation. Please cancel your reservation if you want to modify it.", "error");
		}
		// Alert message if session has expired:
		else if (sessionExpired == "failed") {
			swal({
		        title: "Session Expired!",
		        text: "Please sign in again to your account.",
		        type: "error",
		        confirmButtonText: "OK"
		    }).then(function() {
		        // Redirect to login page
		        window.location.href = "SignIn.jsp";
		    });	
		}
		else if (status == "failed") {
			swal("Reservation Failed", "An error occured. Please try again later.", "error");
		}
	</script>
</body>
</html>