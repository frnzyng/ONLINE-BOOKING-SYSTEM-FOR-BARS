<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="CSS/user-sign-up.css">
    <script src="https://kit.fontawesome.com/5f69afeadf.js" crossorigin="anonymous"></script>
    <link rel="shortcun icon" type="image/png" href="Images/xylo-logo-favicon.png">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
                <li><a href="SignIn.jsp">Create Reservation</a></li>
            </ul>
        </nav>
        <label for="nav-toggle" class="nav-toggle-label">
            <span></span>
        </label>
        <div id="progress-bar"></div>
    </header>

    <div class="pseudoblock"></div>

    <!-- User sign-up -->
    <div class="wrapper">
        <div class="container" data-aos="zoom-in" data-aos-duration="1000">
            <div class="title">Sign Up</div>
            <div class="content">
            	<!-- When the user submits the form, it will be sent to the URL "/signUp" using the HTTP POST method. -->
                <form method="post" action="signUp" enctype="multipart/form-data"> 
                <div class="user-details">
                    <div class="input-box">
                        <span class="details">Full Name</span>
                        <input type="text" name="fullName" placeholder="Enter your name" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Username</span>
                        <input type="text" name="username" placeholder="Enter your username" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Email</span>
                        <input type="email" name="email" placeholder="Enter your email" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Contact Number</span>
                        <input type="text" name="contactNumber" placeholder="Enter your number" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Password (8 characters minimum)</span>
                        <input type="password" name="password" placeholder="Enter your password" id="password" minlength="8" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Confirm Password</span>
                        <input type="password" name="confirmPassword" placeholder="Confirm your password" id="confirm-password" minlength="8" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Birthdate</span>
                        <input type="date" name="birthdate" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Upload Valid ID Picture</span>
                        <button type="button" class="btn-warning">
                            <i class="fa fa-upload"></i>&nbsp Upload File
                            <input type="file" name="validID" accept="image/png, image/jpeg" required>
                        </button>
                    </div>
                </div>

                <div class="policy">
                    <p>By Signing Up, you agree to our <a href="#">Terms and Conditions.</a> You may receive email and SMS notifications from us
                        to keep you updated with event promotions from the club and can opt out at anytime.
                    </p>
                </div>
                <div class="button">
                    <input type="submit" value="Register" class="button">
                </div>
                <div class="account">
                    <p>Already have an account? <a href="SignIn.jsp">Sign in.</a></p>
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
    <!-- <script src="JS/user-sign-up.js"></script> -->
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
		    	swal("Sign Up Failed", "Please fill up all the fields.", "error");
		    </script>
			<%
		} 
		// Alert message if the user is a minor:
		else if (request.getAttribute("ageStatus") == "minor") {
			%>
		    <script type="text/javascript">
		    	swal("Sign Up Failed", "You are currently under age! Come back when you're older.", "error");
		    </script>
			<%
		} 
		// Alert message if password doesn't match:
		else if (request.getAttribute("passwordMatch") == "failed") {
			%>
		    <script type="text/javascript">
		    	swal("Sign Up Failed", "Password doesn't match.", "error");
		    </script>
			<%
		} 
		// Alert message if username exists:
		else if (request.getAttribute("usernameExists") == "failed") {
			%>
		    <script type="text/javascript">
		    	swal("Sign Up Failed", "The username already exists!", "error");
		    </script>
			<%
		} 
		// Alert message if email exists:
		else if (request.getAttribute("emailExists") == "failed") {
			%>
		    <script type="text/javascript">
		    	swal("Sign Up Failed", "The email already exists!", "error");
		    </script>
			<%
		}	
		// Alert message if sign up is successful or unsuccessful:
		else if (request.getAttribute("status") == "success") {
			%>
			<script type="text/javascript">
				swal("Sign Up is Complete", "Welcome to Xylo!", "success");
			</script>
			<%
		}
		else if (request.getAttribute("status") == "failed") {
			%>
			<script type="text/javascript">
				swal("Sign Up Failed", "Something went wrong. Please try again later.", "error");
			</script>
			<%			
		}
	%>  
</body>
</html>