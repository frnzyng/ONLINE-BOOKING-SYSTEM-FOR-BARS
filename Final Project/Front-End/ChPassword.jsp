<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="CSS/user-chpass.css">
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
                    <a href="Reservation.jsp" class="sub-menu-link">
                        <p>Create Reservation</p>
                        <span></span>
                    </a>
                    <a href="ChUsername.jsp" class="sub-menu-link">
                        <p>Change Username</p>
                        <span></span>
                    </a>
                    <a href="#" class="sub-menu-link">
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

    <div class="pseudoblock"></div>

    <!--User sign-up -->
    <div class="wrapper">
        <div class="container" data-aos="zoom-in" data-aos-duration="1000">
            <div class="title">Change Password</div>
            <div class="content">
                <form method="post" action="chPassword">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">New Password</span>
                            <input type="password" name="newPassword" id="nusername" placeholder="Enter password" minlength="8" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Confirm Password</span>
                            <input type="password" name="confirmNewPassword" id="cusername" placeholder="Enter password" minlength="8" required>
                        </div>
                        <div class="button">
                            <input type="submit" id="mainBtn" value="Save Changes">
                        </div>
                    </div>
                </form>
            </div>
        </div>
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
                    <li class="footer-links"><a href="SignIn.jsp" class="footer-links-content">Create Reservation</a></li>
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
    
    <!-- Alert message if change password is successful or unsuccessful: -->
    <input type="hidden" id="status" value="<%= request.getAttribute("status") %>" >
    <input type="hidden" id="sessionExpired" value="<%= request.getAttribute("sessionExpired") %>" >
    <input type="hidden" id="fieldsEmpty" value="<%= request.getAttribute("fieldsEmpty") %>" >
    <input type="hidden" id="samePassword" value="<%= request.getAttribute("samePassword") %>" >
    <input type="hidden" id="confirmNewPassword" value="<%= request.getAttribute("confirmNewPassword") %>" >

	<script type="text/javascript">
		var status = document.getElementById("status").value;
		var sessionExpired = document.getElementById("sessionExpired").value;
		var fieldsEmpty = document.getElementById("fieldsEmpty").value;
		var samePassword = document.getElementById("samePassword").value;
		var confirmNewPassword = document.getElementById("confirmNewPassword").value;
		
		// Alert message if change password is successful:
		if (status == "success") {
			swal({
		        title: "Password has been changed successfully!",
		        text: "Please sign in again.",
		        type: "success",
		        confirmButtonText: "OK"
		    }).then(function() {
		        // Redirect to login page
		        window.location.href = "SignIn.jsp";
		    });	
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
		// Alert message if fields are empty:
		else if (fieldsEmpty == "empty") {
			swal("Change Password Failed", "Please fill up all the fields.", "error");
		}
		// Alert message if password is the same:
		else if (samePassword == "failed") {
			swal("Change Password Failed", "Don't use the same password.", "error");
		}
		// Alert message if password don't match:
		else if (confirmNewPassword == "failed") {
			swal("Change Password Failed", "Password doesn't match.", "error");
		}
		else if (status == "failed") {
			swal("Change Password Failed", "An error occured. Please try again later.", "error");
		}
	</script>
</body>
</html>