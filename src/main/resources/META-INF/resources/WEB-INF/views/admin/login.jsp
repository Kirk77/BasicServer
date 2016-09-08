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
	<!--[if lt IE 9]> 
		<style type="text/css">
			input[type=checkbox].custom-check{ 
			    width:15px;
			    height:15px; 
			    margin:0 0 0 -5px;
			    padding:0;
			    border:0; 
			} 
			input[type=checkbox].custom-check + label.custom-check{ 
			    display:inline-block; 
			    padding-left: 7px; 
			} 
			input[type=checkbox].custom-check:checked + label.custom-check:before{ 
				background-color:white;
				width: 14px;
				height: 14px;
				color:#37a0e9;
			    position:absolute;margin-left:-1em; 
			    content:'✔'; 
			} 				
		</style>
	<![endif]--> 
	<script type="text/javascript" src="../resources/js/jquery-1.4.4.min.js"></script>
	
	<script type="text/JavaScript">
		window.history.forward(0);
	
	    function login() {
	        if($("#userid").val() == "") {
	            alert("input user id");
	            $("#userid").focus();
	            return;
	        }
	        if($("#password").val() == "") {
	            alert("input password");
	            $("#password").focus();
	            return;
	        }
		        
	        document.loginForm.submit();
	        
	        fncSaveCookie();
	    }    
	
		function fncSaveCookie() {
			// 체크가 되어있으면
			if ($("#rememberMe").is(":checked")) {
				// 값들을 쿠키에 저장
				SetCookie('user_chk', true);
				SetCookie('user_id', $("#userid").val());
				SetCookie('user_pw', $("#password").val());
	
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
			<form id="loginForm" name="loginForm" action="/xml/admin/login" method="POST">
		
				
				<div style="margin-top:14px;">
				 	<font style="font-size:18pt;">${version}</font>
				</div>
				
				<div style="margin: 0 auto; width:250px">
					<div style=" height: 110px"></div>
					 
					<div>
						<input type="text" class="inputText" id="userid" name="userid" maxlength="64" tabindex="1" onkeypress="enterLogin()" style="margin-bottom:4px;"/>
						<input type="password" class="inputText" id="password" name="password" maxlength="64" tabindex="2" AUTOCOMPLETE="OFF" onkeypress="enterLogin()"/>
					</div>
	
					<div class="checks">	
						<div class="custom-check" style="float: left; margin-left: -10px; width: 15px;height: 15px;background-color: white;"></div> 
						<div style="float: left; margin-left: -10px;">
							<input type="checkbox" id="rememberMe" name="rememberMe" class="custom-check"/> 
							<label for="rememberMe" class="custom-check">&nbsp;&nbsp;save password</label> 
						</div>
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
