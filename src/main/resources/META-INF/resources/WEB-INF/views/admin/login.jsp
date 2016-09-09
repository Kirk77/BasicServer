<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1" />
	
	<title>로그인</title>
	<style type="text/css">	
		body{
			font-family:Arial,Dotum;
			color:#ffffff;
			margin: 0;
			text-align: center;
		}
		
		#loginBackground{ 
			background: #0071a0; /* Old browsers */
		    background: -moz-linear-gradient(top,  #979493, #524d4b); /* FF3.6+ */
		    background: -webkit-gradient(linear, bottom, top, color-stop(#979493), color-stop(#524d4b)); /* Chrome,Safari4+ */
		    background: -webkit-linear-gradient(top,  #979493,#524d4b); /* Chrome10+,Safari5.1+ */
		    background: -o-linear-gradient(top,  #979493,#524d4b); /* Opera 11.10+ */
		    background: -ms-linear-gradient(top,  #979493,#524d4b); /* IE10+ */
		    background: linear-gradient(to bottom,  #979493,#524d4b); /* W3C */
			filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#979493', endColorstr='#524d4b',GradientType=0 ); /* IE6-9 */
			width:100%;
			height:100%;
			min-width:500px;
			min-height:460px;
			/* z-index:-1; */
			position:fixed;			
		}
	</style>
	
	<script src="../webjars/jquery/3.1.0/jquery.min.js"></script>
	
	<script type="text/JavaScript">
		window.history.forward(0);
	
	    function login() {
	        if($("#j_username").val() == "") {
	            alert("input user id");
	            $("#j_username").focus();
	            return;
	        }
	        if($("#j_password").val() == "") {
	            alert("input password");
	            $("#j_password").focus();
	            return;
	        }
		        
	        document.loginForm.submit();
	        
	        fncSaveCookie();
	    }    
	
	    function SetCookie(name, value) {
	        var argv = SetCookie.arguments;
	        var argc = SetCookie.arguments.length;
	        var expires =  new Date((new Date).getTime() + 28 * 24 * 60 * 60 * 1000);
	        var path = (3 < argc) ? argv[3] : null;
	        var domain = (4 < argc) ? argv[4] : null;
	        var secure = (5 < argc) ? argv[5] : false;
	        document.cookie = name + "=" + escape (value) +
	            ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
	            ((path == null) ? "" : ("; path=" + path)) +
	            ((domain == null) ? "" : ("; domain=" + domain)) +
	            ((secure == true) ? "; secure" : "");
	    }
	    
		function fncSaveCookie() {
			// 체크가 되어있으면
			if ($("#rememberMe").is(":checked")) {
				// 값들을 쿠키에 저장
				SetCookie('user_chk', true);
				SetCookie('user_id', $("#j_username").val());
				SetCookie('user_pw', $("#j_password").val());
	
			} else {
				// 값들을 쿠키에 제거
				SetCookie('user_chk', false);
				SetCookie('user_id', '');
				SetCookie('user_pw', '');
			}
		}
	
		function enterLogin() {
			if(event.keyCode=='13') {
				login();
			}
		}
	
	</script>
</head>

<body id="loginBackground" >
	<div style=" width:100%; height: 100%;">	
		<div id="content"> 
			<form id="loginForm" name="loginForm" action="/admin_login_check" method="POST">
				
				<div style="margin: 0 auto; width:250px">
					<div style=" height: 110px"></div>
					 
					<div>
						<label for="j_username">user name</label>
						<input type="text" class="inputText" id="j_username" name="j_username" maxlength="64" tabindex="1" onkeypress="enterLogin()" style="margin-bottom:4px;"/>
						<label for="j_password">password</label>
						<input type="password" class="inputText" id="j_password" name="j_password" maxlength="64" tabindex="2" AUTOCOMPLETE="OFF" onkeypress="enterLogin()"/>
					</div>
	
					<div class="checks">	
							<input type="checkbox" id="rememberMe" name="rememberMe"/> save password 
					</div>
					
					<div style="margin-top:26px;">
						<input type="button" class="ct-btn" onClick="login();" value="LOGIN" >
					</div> 
				</div>
			</form>
					
		</div>
	</div>
	
</body>
</html>
