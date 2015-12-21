<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header">
		<!-- Logo -->
		<div class="sixteen columns">
			<div id="logo">
				<a href="#"><img src="images/logo.png" alt="logo" /></a>
				<div id="tagline">快加入我们吧！</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="sixteen columns">
		<div id="navigation">
			<ul id="nav">
				<li><a href="index">首页</a></li>
				<li><a href="register">注册</a></li>
				<li><a href="trade">交易明细</a></li>
				<li><a href="center">个人中心</a></li>
				<c:if test="${sessionScope.accountUser==null}">
					  <li style="float: right"><a href="login">登录</a></li>
				</c:if>
				<c:if test="${sessionScope.accountUser!=null}">
					<li style="float: right"><a href="loginout"><span style="color: green; margin-right: 100px;">欢迎您：${sessionScope.accountUser.name}</span>    退出</a></li>
				</c:if>
			</ul>
		</div> 
		<div class="clear"></div>
	</div>