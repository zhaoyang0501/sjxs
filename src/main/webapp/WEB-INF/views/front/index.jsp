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
<%@include file="./head.jsp" %>
</div>
<!-- 960 Container / End -->
<div class="container">
<!-- Content
================================================== -->

	<!-- Flexslider -->
	<div class="sixteen columns">
		<section class="slider">
			<div class="flexslider home">
				<ul class="slides">
				
					<li>
						<img src="images/web/c.jpg" alt="" />
						<div class="slide-caption">
							<h3>基金服务</h3>
							<p>新手入门基金类型丰富，安全专业，就是平安陆金所，基金频道已经强势上线。平安陆金所基金频道，是您2015理财优质基金平台，千万用户理财</p>
						
						</div>
					</li>
					
					<li>
						<img src="images/web/a.jpg" alt="" />
					</li>
					<li>
						<img src="images/web/d.jpg" alt="" />
					</li>
					<li>
						<img src="images/web/b.jpg" alt="" />
						<div class="slide-caption">
							<h3>理财服务中心</h3>
							<p>协会优先登记有管理基金经验的私募投资基金管理机构的申请。对于没有管理过基金的申请机构，协会除核对其是否如实填报申请材料、申请机构及其实际控制人</p>
						</div>
					</li>
					
				</ul>
			</div>
		</section>
  	</div>

<div class="container">

	<!-- Icon Boxes -->
	<div class="icon-box-container">

		<!-- Icon Box Start -->
		<div class="one-third column">
			<div class="icon-box">
				<i class="ico-display" style="margin-left: -10px;"></i>
				<h3>立即签约</h3>
				<p>汇聚海量资深投资顾问智慧，提供个性化、一对一专属服务，助您开启财富之门</p>
			</div>
		</div>
		<!-- Icon Box End -->
		
		<!-- Icon Box Start -->
		<div class="one-third column">
			<div class="icon-box">
				<i class="ico-cogwheel"></i>
				<h3>立即开通</h3>
				<p>不用到香港，不用换港币，不用新开户，为您开启直投港股的直通车</p>
			</div>
		</div>
		<!-- Icon Box End -->
		
		<!-- Icon Box Start -->
		<div class="one-third column">
			<div class="icon-box">
				<i class="ico-iphone"></i>
				<h3>马上融资</h3>
				<p>有财贷”是一种通过沪深交易所场内股票质押通道，为您提供最低融资</p>
			</div>
		</div>
		<!-- Icon Box End -->
		
	</div>
	<!-- Icon Boxes / End -->
	
</div>
 </div>
</div>
<!-- Wrapper / End -->

	<%@include file="./foot.jsp" %>

</body>
</html>