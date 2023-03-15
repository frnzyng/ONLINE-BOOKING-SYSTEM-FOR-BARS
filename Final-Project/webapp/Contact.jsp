<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="CSS/user-contact.css">
    <script src="https://kit.fontawesome.com/5f69afeadf.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Counter-Up/1.0.0/jquery.counterup.min.js"></script>
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
                <li><a href="SignIn.jsp">Create Reservation</a></li>
            </ul>
        </nav>
        <label for="nav-toggle" class="nav-toggle-label">
            <span></span>
        </label>
        <div id="progress-bar"></div>
    </header>

    <div class="pseudoblock"></div>

    <!--Block1 - Hero-->
    <div class="box-container">
        <div class="box1">
            <div class="image-gallery">
                <img src="Images/contact-hero1.jpg" alt="Experience Xylo 1" data-aos="fade-up" data-aos-duration="1000">
                <img src="Images/contact-hero2.jpg" alt="Experience Xylo 2" data-aos="fade-up" data-aos-duration="1500">
                <img src="Images/contact-hero3.jpg" alt="Experience Xylo 3" data-aos="fade-up" data-aos-duration="2000">
                <img src="Images/contact-hero4.jpg" alt="Experience Xylo 4"data-aos="fade-up" data-aos-duration="2500">
            </div>
        </div>
        <div class="box2">
            <h2 class="contact-title">GOT QUESTIONS?</h2>
            <h1 class="contact-content">
                Send us an inquiry!
            </h1>
            <div class="contact-details">
                <p class="contact-content">
                    +63 917 680 8888
                </p>
                <button type="button" class="email">
                    <a href="mailto:events@thepalacemanila.com" target="_blank">Email</a>
                </button>
                <div class="contact-social-icons">
                    <a href="https://www.facebook.com/XYLOatThePalace/" target="_blank">
                        <i class="fa-brands fa-facebook-square"></i>
                    </a>
                    <a href="#" target="_blank">
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
    <script>
        AOS.init();
    </script>
</body>
</html>