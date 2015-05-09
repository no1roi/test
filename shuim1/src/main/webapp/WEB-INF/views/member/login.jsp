<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<script type="text/javascript">
function registerMember() {
	if($('#user_name').val()==""){
		alert("name input x");
		$('#user_name').focus();
		return false;
	}
	if($('#email').val()==""){
		alert("email input x");
		$('#email').focus();
		return;
	}
	if($('#passwd').val()==""){
		alert("pwd input x");
		$('#passwd').focus();
		return;
	}
	if($('#phone').val()==""){
		alert("phone input x");
		$('#phone').focus();
		return;
	}
	if($('#mobile').val()==""){
		alert("mobile input x");
		$('#mobile').focus();
		return;
	}

	jQuery.ajax({
		type : "POST",
		url : '/member/registerMember_ok.do',
		data : jQuery('#registerMember').serialize(),
	}).done(function(data){
		var message = jQuery(data).find("message").text();
		var error = jQuery(data).find("error").text();
		//alert(message);
		if(error == 'false'){
			alert(message);
			location.href = '/member/login.do';
		} else {
			alert("register fail");
		}
	});
}
</script>
</head>

<body>
<div class="sign-form">
	<h2>신규 가입</h2>
	<form id="registerMember" action="javascript:void(0);" onsubmit="registerMember();return false">
		<table width="30%" cellpadding="2" cellspacing="0" border="1" class="inptbl">	
		<tr>
   			<td class="tdTitle">
             	USER NAME
            </td>
            <td>
            	<input type="text" id="user_name" name="user_name" placeholder="Name"/>
            </td>
          	<td class="tdTitle">
          		EMAIL
            </td>
            <td>
            	<input type="email" id="email" name="email" placeholder="Email Address"/>
            </td>                    
       	</tr>
		<tr>
   			<td class="tdTitle">
             	PASSWORD
            </td>
            <td>
            	<input type="password" id="passwd" name="passwd" placeholder="Password"/>
            </td>
          	<td class="tdTitle">
          		PHONE
            </td>
            <td>
            	<input type="text" id="phone" name="phone" placeholder="phone"/>
            </td>                    
       	</tr>
		<tr>
   			<td class="tdTitle">
             	MOBILE
            </td>
            <td>
            	<input type="text" id="mobile" name="mobile" placeholder="mobile"/>
            </td>                   
       	</tr>
       	</table>
		<button type="submit" class="btn-default">가입허가</button>
	</form>
</div>

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