<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
<!doctype html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<title>Brushed | Responsive One Page Template</title>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
</head>
 -->

<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if (IE 9)]><html class="no-js ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->
<head>

<!-- Meta Tags -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Brushed | Responsive One Page Template</title>   

<meta name="description" content="Insert Your Site Description" /> 

<!-- Mobile Specifics -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="HandheldFriendly" content="true"/>
<meta name="MobileOptimized" content="320"/>   

<!-- Mobile Internet Explorer ClearType Technology -->
<!--[if IEMobile]>  <meta http-equiv="cleartype" content="on">  <![endif]-->

<!-- Bootstrap -->
<link href="/resources/_include/css/bootstrap.min.css" rel="stylesheet">

<!-- Main Style -->
<link href="/resources/_include/css/main.css" rel="stylesheet">

<!-- Supersized -->
<link href="/resources/_include/css/supersized.css" rel="stylesheet">
<link href="/resources/_include/css/supersized.shutter.css" rel="stylesheet">

<!-- FancyBox -->
<link href="/resources/_include/css/fancybox/jquery.fancybox.css" rel="stylesheet">

<!-- Font Icons -->
<link href="/resources/_include/css/fonts.css" rel="stylesheet">

<!-- Shortcodes -->
<link href="/resources/_include/css/shortcodes.css" rel="stylesheet">

<!-- Responsive -->
<link href="/resources/_include/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="/resources/_include/css/responsive.css" rel="stylesheet">

<!-- Supersized -->
<link href="/resources/_include/css/supersized.css" rel="stylesheet">
<link href="/resources/_include/css/supersized.shutter.css" rel="stylesheet">

<!-- Google Font -->
<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900' rel='stylesheet' type='text/css'>

<!-- Fav Icon -->
<link rel="shortcut icon" href="#">

<link rel="apple-touch-icon" href="#">
<link rel="apple-touch-icon" sizes="114x114" href="#">
<link rel="apple-touch-icon" sizes="72x72" href="#">
<link rel="apple-touch-icon" sizes="144x144" href="#">

<!-- Modernizr -->
<script src="/resources/_include/js/modernizr.js"></script>

<!-- Analytics -->
<script type="text/javascript">
window.onload = function () {
	/* 
	var htm = '<input type="button" value="1번 이미지" onclick="fnChgImg(\'1\');" />';
	htm += '<input type="button" value="2번 이미지" onclick="fnChgImg(\'2\');" />';
	htm += '<input type="button" value="3번 이미지" onclick="fnChgImg(\'3\');" />';
	htm += '<input type="button" value="4번 이미지" onclick="fnChgImg(\'4\');" />';
	$(".control-nav").prepend(htm);
	 */
	var htm = '<button type="button" class=btn-default onclick="fnChgImg(\'1\');">1 Image</button>';
	htm += '<button type="button" class=btn-primary onclick="fnChgImg(\'2\');">2 Image</button>';
	htm += '<button type="button" class=btn-success onclick="fnChgImg(\'3\');">3 Image</button>';
	htm += '<button type="button" class=btn-info onclick="fnChgImg(\'4\');">4 Image</button>';
	$(".control-nav").prepend(htm);
}
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'Insert Your Code']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

  function fnChgImg(no) {
	  /*
	  var vSrc = "";
	  if (no == "1" || no == "3") {
		  vSrc = "/resources/_include/img/slider-images/image01.jpg";
	  } else if (no == "2" || no == "4") {
		  vSrc = "/resources/_include/img/slider-images/Tulips.jpg";
	  } 
	  $("#slidecaption > img").attr("src", vSrc);
	  */
	  /*
	  var vImg = "";
	  if (no == "1" || no == "3") {
		  vImg = '<img src="/resources/_include/img/slider-images/image01.jpg">';
	  } else if (no == "2" || no == "4") {
		  vImg = '<img src="/resources/_include/img/slider-images/image02.jpg">';
	  } 
	  $("#slidecaption").html(vImg);
	 */
	  var vImg = "";
	  if (no == "1") {
		  vImg = '<img src="/resources/_include/img/slider-images/Tulips.jpg">';
	  } else if (no == "2") {
		  vImg = '<img src="/resources/_include/img/slider-images/Desert.jpg">';
	  } else if (no == "3") {
		  vImg = '<img src="/resources/_include/img/slider-images/Hydrangeas.jpg">';
	  } else {
		  vImg = '<img src="/resources/_include/img/slider-images/Lighthouse.jpg">';
	  }
	  $("#slidecaption").html(vImg);
  }
  
</script>
<!-- End Analytics -->

</head>

<body>
 
<%--  <!-- 본 샘플은 완투에 의해 누구나 쉽게 개발할 수 있도록 제공하기 위해 시리즈로 제작됩니다. -->
 
<div id="tabs">

	<ul>
		<c:forEach items="${listTest}" var="list" varStatus="status">
			<li>
				<a href="#fragment-${list.id}"><span>${list.title}</span></a>
			</li>
		</c:forEach>
	</ul>

  	<c:forEach items="${listTest}" var="list" varStatus="status">
		<div id="fragment-${list.id}">
			<p>${list.content}</p>
			<img width="200" height="200" alt="" src="/resources/image/${list.id}.jpg">
		</div>
	</c:forEach>
</div>
 
<script>
$( "#tabs" ).tabs();
</script> --%>

<!-- This section is for Splash Screen -->
<div class="ole">
<section id="jSplash">
	<div id="circle"></div>
</section>
</div>
<!-- End of Splash Screen -->

<!-- Homepage Slider -->
<div id="home-slider">	
    <div class="overlay"></div>

    <div class="slider-text">
    	<div id="slidecaption"><img src="/resources/_include/img/slider-images/Tulips.jpg"></div>
    <!-- 
    	<div id="slidecaption"><img src="/resources/_include/img/slider-images/Tulips.jpg"></div>
     -->
    </div> 
	<div class="control-nav">
        <a id="prevslide" class="load-item"><i class="font-icon-arrow-simple-left"></i></a>
        <a id="nextslide" class="load-item"><i class="font-icon-arrow-simple-right"></i></a>
        <ul id="slide-list"></ul>
        
        <a id="nextsection" href="#work"><i class="font-icon-arrow-simple-down"></i></a>
    </div>
</div>
<!-- End Homepage Slider -->

<!-- Header -->
<header>
    <div class="sticky-nav">
    	<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>
        
        <div id="logo">
        	<a id="goUp" href="#home-slider" title="Brushed | Responsive One Page Template">Brushed Template</a>
        </div>
        
        <nav id="menu">
        	<ul id="menu-nav">
            	<li class="current"><a href="#home-slider">Home</a></li>
                <li><a href="#work">Our Work</a></li>
                <li><a href="#about">About Us</a></li>
                <li><a href="#contact">Contact</a></li>
				<!-- 
				<li><a href="shortcodes.html" class="external">Login</a></li>
				-->
				<li><a href="/member/login.do" class="external">Login</a></li>
            </ul>
        </nav>
        
    </div>
</header>
<!-- End Header -->

<!-- Our Work Section -->
<div id="work" class="page">
	<div class="container">
    	<!-- Title Page -->
        <div class="row">
            <div class="span12">
                <div class="title-page">
                    <h2 class="title">Our Work</h2>
                    <h3 class="title-description">Check Out Our Projects on <a href="#">Dribbble</a>.</h3>
                </div>
            </div>
        </div>
        <!-- End Title Page -->
        
        <!-- Portfolio Projects -->
        <div class="row">
        	<div class="span3">
            	<!-- Filter -->
                <nav id="options" class="work-nav">
                    <ul id="filters" class="option-set" data-option-key="filter">
                    	<li class="type-work">Type of Work</li>
                        <li><a href="#filter" data-option-value="*" class="selected">All Projects</a></li>
                        <li><a href="#filter" data-option-value=".design">Design</a></li>
                        <li><a href="#filter" data-option-value=".photography">Photography</a></li>
                        <li><a href="#filter" data-option-value=".video">Video</a></li>
                    </ul>
                </nav>
                <!-- End Filter -->
            </div>
            
            <div class="span9">
            	<div class="row">
                	<section id="projects">
                    	<ul id="thumbs">
                        
							<!-- Item Project and Filter Name -->
                        	<li class="item-thumbs span3 design">
                            	<!-- Fancybox - Gallery Enabled - Title - Full Image -->
                            	<a class="hover-wrap fancybox" data-fancybox-group="gallery" title="Tulips" href="/resources/_include/img/work/full/Tulips.jpg">
                                	<span class="overlay-img"></span>
                                    <span class="overlay-img-thumb font-icon-plus"></span>
                                </a>
                                <!-- Thumb Image and Description -->
                                <img src="/resources/_include/img/work/thumbs/Tulips.jpg" alt="Tulips.jpg">
                            </li>
                        	<!-- End Item Project -->
                            
							<!-- Item Project and Filter Name -->
                        	<li class="item-thumbs span3 design">
                            	<!-- Fancybox - Gallery Enabled - Title - Full Image -->
                            	<a class="hover-wrap fancybox" data-fancybox-group="gallery" title="Desert" href="/resources/_include/img/work/full/Desert.jpg">
                                	<span class="overlay-img"></span>
                                    <span class="overlay-img-thumb font-icon-plus"></span>
                                </a>
                                <!-- Thumb Image and Description -->
                                <img src="/resources/_include/img/work/thumbs/Desert.jpg" alt="Desert.jpg">
                            </li>
                        	<!-- End Item Project -->
                            
							<!-- Item Project and Filter Name -->
                        	<li class="item-thumbs span3 photography">
                            	<!-- Fancybox - Gallery Enabled - Title - Full Image -->
                            	<a class="hover-wrap fancybox" data-fancybox-group="gallery" title="Chrysanthemum" href="/resources/_include/img/work/full/Chrysanthemum.jpg">
                                	<span class="overlay-img"></span>
                                    <span class="overlay-img-thumb font-icon-plus"></span>
                                </a>
                                <!-- Thumb Image and Description -->
                                <img src="/resources/_include/img/work/thumbs/Chrysanthemum.jpg" alt="Chrysanthemum.jpg">
                            </li>
                        	<!-- End Item Project -->
                            
							<!-- Item Project and Filter Name -->
                        	<li class="item-thumbs span3 video">
                            	<!-- Fancybox Media - Gallery Enabled - Title - Link to Video -->
                            	<a class="hover-wrap fancybox-media" data-fancybox-group="video" title="Video Content From Vimeo" href="http://vimeo.com/51460511">
                                	<span class="overlay-img"></span>
                                    <span class="overlay-img-thumb font-icon-plus"></span>
                                </a>
                                <!-- Thumb Image and Description -->
                                <img src="/resources/_include/img/work/thumbs/mv1.jpg" alt="Video">
                            </li>
                        	<!-- End Item Project -->
                            
							<!-- Item Project and Filter Name -->
                        	<li class="item-thumbs span3 photography">
                            	<!-- Fancybox - Gallery Enabled - Title - Full Image -->
                            	<a class="hover-wrap fancybox" data-fancybox-group="gallery" title="Hydrangeas" href="/resources/_include/img/work/full/Hydrangeas.jpg">
                                	<span class="overlay-img"></span>
                                    <span class="overlay-img-thumb font-icon-plus"></span>
                                </a>
                                <!-- Thumb Image and Description -->
                                <img src="/resources/_include/img/work/thumbs/Hydrangeas.jpg" alt="Hydrangeas.jpg">
                            </li>
                        	<!-- End Item Project -->
                            
							<!-- Item Project and Filter Name -->
                        	<li class="item-thumbs span3 photography">
                            	<!-- Fancybox - Gallery Enabled - Title - Full Image -->
                            	<a class="hover-wrap fancybox" data-fancybox-group="gallery" title="Jellyfish" href="/resources/_include/img/work/full/Jellyfish.jpg">
                                	<span class="overlay-img"></span>
                                    <span class="overlay-img-thumb font-icon-plus"></span>
                                </a>
                                <!-- Thumb Image and Description -->
                                <img src="/resources/_include/img/work/thumbs/Jellyfish.jpg" alt="Jellyfish.jpg">
                            </li>
                        	<!-- End Item Project -->
                            
							<!-- Item Project and Filter Name -->
                        	<li class="item-thumbs span3 video">
                            	<!-- Fancybox Media - Gallery Enabled - Title - Link to Video -->
                            	<a class="hover-wrap fancybox-media" data-fancybox-group="video" title="Video Content From Vimeo" href="http://vimeo.com/50834315">
                                	<span class="overlay-img"></span>
                                    <span class="overlay-img-thumb font-icon-plus"></span>
                                </a>
                                <!-- Thumb Image and Description -->
                                <img src="/resources/_include/img/work/thumbs/mv2.jpg" alt="Video">
                            </li>
                        	<!-- End Item Project -->
                            
							<!-- Item Project and Filter Name -->
                        	<li class="item-thumbs span3 design">
                            	<!-- Fancybox - Gallery Enabled - Title - Full Image -->
                            	<a class="hover-wrap fancybox" data-fancybox-group="gallery" title="Koala" href="/resources/_include/img/work/full/Koala.jpg">
                                	<span class="overlay-img"></span>
                                    <span class="overlay-img-thumb font-icon-plus"></span>
                                </a>
                                <!-- Thumb Image and Description -->
                                <img src="/resources/_include/img/work/thumbs/Koala.jpg" alt="Koala.jpg">
                            </li>
                        	<!-- End Item Project -->
                            
							<!-- Item Project and Filter Name -->
                        	<li class="item-thumbs span3 design">
                            	<!-- Fancybox - Gallery Enabled - Title - Full Image -->
                            	<a class="hover-wrap fancybox" data-fancybox-group="gallery" title="Penguins" href="/resources/_include/img/work/full/Penguins.jpg">
                                	<span class="overlay-img"></span>
                                    <span class="overlay-img-thumb font-icon-plus"></span>
                                </a>
                                <!-- Thumb Image and Description -->
                                <img src="/resources/_include/img/work/thumbs/Penguins.jpg" alt="Penguins.jpg">
                            </li>
                        	<!-- End Item Project -->
                        </ul>
                    </section>
                    
            	</div>
            </div>
        </div>
        <!-- End Portfolio Projects -->
    </div>
</div>
<!-- End Our Work Section -->

<!-- About Section -->
<div id="about" class="page-alternate">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">About Us</h2>
                <h3 class="title-description">Learn About our Team &amp; Culture.</h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- People -->
    <div class="row">
    	
        <!-- Start Profile -->
    	<div class="span4 profile">
        	<div class="image-wrap">
                <div class="hover-wrap">
                    <span class="overlay-img"></span>
                    <span class="overlay-text-thumb">CTO/Founder</span>
                </div>
                <img src="/resources/_include/img/profile/profile-01.jpg" alt="John Doe">
            </div>
            <h3 class="profile-name">Jeon Young-ro</h3>
            <p class="profile-description">Hello, Java Study!! Fighting </p>
            	
            <div class="social">
            	<ul class="social-icons">
                	<li><a href="#"><i class="font-icon-social-twitter"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-dribbble"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-facebook"></i></a></li>
                </ul>
            </div>
        </div>
        <!-- End Profile -->
        
        <!-- Start Profile -->
    	<div class="span4 profile">
        	<div class="image-wrap">
                <div class="hover-wrap">
                    <span class="overlay-img"></span>
                    <span class="overlay-text-thumb">Creative Director</span>
                </div>
                <img src="/resources/_include/img/profile/profile-02.jpg" alt="Jane Helf">
            </div>
            <h3 class="profile-name">Person1</h3>
            <p class="profile-description">Good people</p>
            	
            <div class="social">
            	<ul class="social-icons">
                	<li><a href="#"><i class="font-icon-social-twitter"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-email"></i></a></li>
                </ul>
            </div>
        </div>
        <!-- End Profile -->
        
        <!-- Start Profile -->
    	<div class="span4 profile">
        	<div class="image-wrap">
                <div class="hover-wrap">
                    <span class="overlay-img"></span>
                    <span class="overlay-text-thumb">Lead Designer</span>
                </div>
                <img src="/resources/_include/img/profile/profile-03.jpg" alt="Joshua Insanus">
            </div>
            <h3 class="profile-name">Person2</h3>
            <p class="profile-description">Good people</p>
            	
            <div class="social">
            	<ul class="social-icons">
                	<li><a href="#"><i class="font-icon-social-twitter"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-linkedin"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-google-plus"></i></a></li>
                    <li><a href="#"><i class="font-icon-social-vimeo"></i></a></li>
                </ul>
            </div>
        </div>
        <!-- End Profile -->
        
    </div>
    <!-- End People -->
</div>
</div>
<!-- End About Section -->


<!-- Contact Section -->
<div id="contact" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">Get in Touch</h2>
                <h3 class="title-description">We’re currently accepting new client projects. We look forward to serving you.</h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- Contact Form -->
    <div class="row">
    	<div class="span9">
        
        	<form id="contact-form" class="contact-form" action="#">
            	<p class="contact-name">
            		<input id="contact_name" type="text" placeholder="Full Name" value="" name="name" />
                </p>
                <p class="contact-email">
                	<input id="contact_email" type="text" placeholder="Email Address" value="" name="email" />
                </p>
                <p class="contact-message">
                	<textarea id="contact_message" placeholder="Your Message" name="message" rows="15" cols="40"></textarea>
                </p>
                <p class="contact-submit">
                	<a id="contact-submit" class="submit" href="#">Send Your Email</a>
                </p>
                
                <div id="response">
                
                </div>
            </form>

        </div>
        
        <div class="span3">
        	<div class="contact-details">
        		<h3>Contact Details</h3>
                <ul>
                    <li><a href="#">yrjeon.7@gmail.com</a></li>
                    <li>010-6330-7095</li>
                    <li>
                        Brushed Studio
                        <br>
                        Korea
                        <br>
                        Unknow
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- End Contact Form -->
</div>
</div>
<!-- End Contact Section -->

<!-- Twitter Feed -->
<div id="twitter-feed" class="page-alternate">
	<div class="container">
    	<div class="row">
            <div class="span12">
                <div class="follow">
                    <a href="https://twitter.com/Bluxart" title="Follow Me on Twitter" target="_blank"><i class="font-icon-social-twitter"></i></a>
                </div>
                    
                <div id="ticker" class="query"> 
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Twitter Feed -->

<!-- Socialize -->
<div id="social-area" class="page">
	<div class="container">
    	<div class="row">
            <div class="span12">
                <nav id="social">
                    <ul>
                        <li><a href="https://twitter.com/" title="Follow Me on Twitter" target="_blank"><span class="font-icon-social-twitter"></span></a></li>
                        <li><a href="http://dribbble.com/" title="Follow Me on Dribbble" target="_blank"><span class="font-icon-social-dribbble"></span></a></li>
                        <li><a href="http://forrst.com/people/" title="Follow Me on Forrst" target="_blank"><span class="font-icon-social-forrst"></span></a></li>
                        <li><a href="http://www.behance.net/" title="Follow Me on Behance" target="_blank"><span class="font-icon-social-behance"></span></a></li>
                        <li><a href="https://www.facebook.com/" title="Follow Me on Facebook" target="_blank"><span class="font-icon-social-facebook"></span></a></li>
                        <li><a href="https://plus.google.com/" title="Follow Me on Google Plus" target="_blank"><span class="font-icon-social-google-plus"></span></a></li>
                        <li><a href="http://www.linkedin.com/" title="Follow Me on LinkedIn" target="_blank"><span class="font-icon-social-linkedin"></span></a></li>
                        <li><a href="http://themeforest.net/user/" title="Follow Me on ThemeForest" target="_blank"><span class="font-icon-social-envato"></span></a></li>
                        <li><a href="http://zerply.com/" title="Follow Me on Zerply" target="_blank"><span class="font-icon-social-zerply"></span></a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<!-- End Socialize -->

<!-- Footer -->
<footer>
	<p class="credits">&copy;2013 Brushed. <a href="http://themes.alessioatzeni.com/html/brushed/" title="Brushed | Responsive One Page Template">Brushed Template</a> by <a href="http://www.alessioatzeni.com/" title="Alessio Atzeni | Web Designer &amp; Front-end Developer">Alessio Atzeni</a></p>
</footer>
<!-- End Footer -->

<!-- Back To Top -->
<a id="back-to-top" href="#">
	<i class="font-icon-arrow-simple-up"></i>
</a>
<!-- End Back to Top -->


<!-- Js -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> <!-- jQuery Core -->
<script src="/resources/_include/js/bootstrap.min.js"></script> <!-- Bootstrap -->
<script src="/resources/_include/js/supersized.3.2.7.min.js"></script> <!-- Slider -->
<script src="/resources/_include/js/waypoints.js"></script> <!-- WayPoints -->
<script src="/resources/_include/js/waypoints-sticky.js"></script> <!-- Waypoints for Header -->
<script src="/resources/_include/js/jquery.isotope.js"></script> <!-- Isotope Filter -->
<script src="/resources/_include/js/jquery.fancybox.pack.js"></script> <!-- Fancybox -->
<script src="/resources/_include/js/jquery.fancybox-media.js"></script> <!-- Fancybox for Media -->
<script src="/resources/_include/js/jquery.tweet.js"></script> <!-- Tweet -->
<script src="/resources/_include/js/plugins.js"></script> <!-- Contains: jPreloader, jQuery Easing, jQuery ScrollTo, jQuery One Page Navi -->
<script src="/resources/_include/js/main.js"></script> <!-- Default JS -->
<!-- End Js -->
 
</body>
</html>