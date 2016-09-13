<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1" />
	
	<title>로그인</title>
	
	
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
						<label for="j_username">username</label>
						<input type="text" class="inputText" id="j_username" name="j_username" maxlength="64" tabindex="1" onkeypress="enterLogin()" style="margin-bottom:4px;"/>
						<label for="j_password">password</label>
						<input type="password" class="inputText" id="j_password" name="j_password" maxlength="64" tabindex="2" AUTOCOMPLETE="OFF" onkeypress="enterLogin()"/>
					</div>
					
					<c:if test="${not empty loginmsg}">
						<div class="error">${loginmsg}</div>
					</c:if>
					
					<div style="margin-top:26px;">
						<input type="button" class="ct-btn" onClick="login();" value="LOGIN" >
					</div> 
				</div>
			</form>
					
		</div>
	</div>
	
</body>
</html>
