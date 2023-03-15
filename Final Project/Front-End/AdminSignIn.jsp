<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Sign In</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="CSS/admin/admin-sign-in.css">
    <script src="https://kit.fontawesome.com/5f69afeadf.js" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="shortcun icon" type="image/png" href="Images/xylo-logo-favicon.png">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
</head>
<body>

    <div id="parallax">

    <!-- Navbar -->
    <header>
        <img src="Images/xylo-logo-favicon - Copy.png" width="150" class="header-logo" data-aos="zoom-in" data-aos-duration="2000">
        <input type="checkbox" id="nav-toggle" class="nav-toggle">
        <nav>
            <!--<ul>
                <li><a href="#b1">Home</a></li>
                <li><a href="#b2">About</a></li>
                <li><a href="#b4">Contact</a></li>
                <li><a href="#b5">Create Reservation</a></li>
            </ul>-->
        </nav>
        <!--<label for="nav-toggle" class="nav-toggle-label">
            <span></span>
        </label>-->
        <div id="progress-bar"></div>
    </header>

    <div class="pseudoblock" id="welcome-admin"></div>
    <div class="admin-hero">
        <div class="welcome" data-aos="fade" data-aos-duration="2000">Welcome, Admin</div>
        <button type="button" onclick="location.href='#admin-sign-in'" data-aos="zoom-in" data-aos-duration="3000">Sign In</button>
    </div>

    <!--Admin sign-up -->
    <div class="wrapper">
        <div class="container" data-aos="zoom-in-up" data-aos-duration="1000">
            <div class="title"  id="admin-sign-in">Sign In</div>
            <div class="content">
            <!-- When the user submits the form, it will be sent to the URL "/signInAdmin" using the HTTP POST method. -->
                <form method="post" action="signInAdmin">
                <div class="user-details">
                    <div class="input-box">
                        <span class="details">Username</span>
                        <input type="text" name="username" placeholder="Enter your username" required>
                    </div>
                    <div class="input-box">
                        <span class="details">Password</span>
                        <input type="password" name="password" placeholder="Enter your password"  minlength="8" required>
                    </div>
                    <div class="button">
                        <input type="submit" value="Sign In">
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>

    <!--Return Button-->
    <a href="#welcome-admin" class="top">
        <i class="fa-solid fa-arrow-up"></i>
    </a>


    <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
    <script>
        AOS.init();
    </script>
    
    <!-- Alert message if sign in is successful or unsuccessful: -->
	<%		
		if (request.getAttribute("status") == "failed") {
			%>
			<script type="text/javascript">
				swal("Sign In Failed", "Incorrect username or password. Please try again.", "error");
			</script>
			<%			
		}
    %>
</body>
</html>