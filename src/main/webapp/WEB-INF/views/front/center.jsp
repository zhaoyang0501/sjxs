<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  
<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"><![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"><![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"><!--<![endif]-->
<head>

<!-- Basic Page Needs
================================================== -->
<meta charset="utf-8">
<title> - Free Theme s& Templates</title>

<!-- Mobile Specific
================================================== -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS
================================================== -->
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/boxed.css" id="layout">
<link rel="stylesheet" type="text/css" href="css/colors/green.css" id="colors">

<!-- Java Script
================================================== -->
<script src="js/jquery.min.js"></script>
<script src="js/custom.js"></script>
<script src="js/selectnav.js"></script>
<script src="js/flexslider.js"></script>
<script src="js/twitter.js"></script>
<script src="js/tooltip.js"></script>
<script src="js/effects.js"></script>
<script src="js/fancybox.js"></script>
<script src="js/carousel.js"></script>
<script src="js/isotope.js"></script>

<!-- Styles Switcher
================================================== -->
<link rel="stylesheet" type="text/css" href="css/switcher.css">
<script src="js/switcher.js"></script>

</head>
<body>

<!-- Wrapper Start -->
<div id="wrapper">


<!-- Header
================================================== -->

<!-- 960 Container -->
<div class="container ie-dropdown-fix">
	<!-- Header -->
		<%@include file="./head.jsp" %>
	<!-- Header / End -->
	
</div>
<!-- 960 Container / End -->


<!-- Content
================================================== -->
<div class="container">

	<div class="sixteen columns">
		<div id="page-title">
			<h2>个人中心</h2>
			<div id="bolded-line"></div>
		</div>
	</div>
</div>

<div class="container">
	
	<c:if test="${tip!=null }">
	<div class="sixteen columns">
		<div class="notification success  closeable" style="margin: 5px 0 25px 0;">
			<p>${tip}</p>
		</div>
	</div>
</c:if>

	<!-- Contact Form -->
	<div class="sixteen columns " >
		<!-- Form -->
		<div id="contact-form">
			<form  action="${pageContext.request.contextPath}/center" method="post">
				<input type="hidden" name='id' value="${sessionScope.accountUser.id }">
				<div class="field">
					<label>用户名:</label>
					<input type="text" readonly="readonly" name="loginName" class="text" value="${sessionScope.accountUser.loginName }">
				</div>
				
				<div class="field">
					<label>姓名:</label>
					<input type="text" name="name" class="text" value="${sessionScope.accountUser.name }">
				</div>
				<div class="field">
					<label>电话:</label>
					<input type="text" name="phone" class="text" value="${sessionScope.accountUser.phone }">
				</div>
				<div class="field">
					<label>邮件:</label>
					<input type="text" name="email" class="text" value="${sessionScope.accountUser.email }">
				</div>
				<div class="field">
					<label>性别:</label>
					<input style="width: 30px" type="radio" id="man" name="gender" value="1" <c:if test="${sessionScope.accountUser.gender=='1' }">checked=checked</c:if> >男
					<input style="width: 30px"  type="radio" id="woman" name="gender" value="0" <c:if test="${sessionScope.accountUser.gender=='0' }">checked=checked</c:if>>女
				</div>
				<div class="field">
					<input  class='button color medium' type="submit"  value="提交">
				</div>
			</form>
		</div>
	</div>
</div>

<div class="container">
	<div class="sixteen columns">
		<div id="page-title">
			<h2>修改密码</h2>
			<div id="bolded-line"></div>
		</div>
	</div>
</div>


<div class="container">
	<!-- Contact Form -->
	<div class="sixteen columns " >
		<!-- Form -->
		<div id="contact-form">
			<form  action="${pageContext.request.contextPath}/updatePw" method="post">
				<input type="hidden" name='id' value="${sessionScope.accountUser.id }">
				<div class="field">
					<label>用户名:</label>
					<input readonly="readonly" type="text" name="loginName" class="text" value="${sessionScope.accountUser.loginName }">
				</div>
				<div class="field">
					<label>旧密码:</label>
					<input type="password" name="plainPassword" class="text" value="">
				</div>
				<div class="field">
					<label>新密码:</label>
					<input type="password" name="password" class="text" value="">
				</div>
				<div class="field">
					<input  class='button color medium' type="submit"  value="提交">
				</div>
			</form>
		</div>
	</div>
</div>

</div>
<!-- Wrapper / End -->
	<%@include file="./foot.jsp" %>


</body>
</html>