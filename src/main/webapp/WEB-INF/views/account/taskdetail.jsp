<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
<div>
	<form id="mainform" action="${ctx}/account/plan/dist" method="post">
		  <fieldset class="default">
				<legend>计划详情</legend>
				<table  class="doc-table">
				 <tr>
                    <td>计划名称</td>
                    <td><b>${plan.name }</b></td>
                    <td>下发时间</td>
                    <td><b><fmt:formatDate value="${plan.createDate}" type="date" dateStyle="full"/></b></td>
                     <td rowspan="2">完成程度</td>
                    <td rowspan="2"><b>${(plan.endnum/plan.num)*100 }%</b></td>
                </tr>
                <tr>
                    <td>计划开始时间:</td>
                    <td><b><fmt:formatDate value="${plan.startDate}" type="date" dateStyle="full"/> </b></td>
                     <td>计划结束时间:</td>
                    <td><b><fmt:formatDate value="${plan.endDate}" type="date" dateStyle="full"/> </b></td>
                </tr>
                
                <tr>
                    <td>计划内容:</td>
                    <td colspan="5"><b>${plan.remark }</b></td>
                </tr>
				</table>
			</fieldset>
			
			
			 <fieldset class="default">
				<legend>我的下属完成情况</legend>
				<table  class="doc-table">
				<thead>
					<tr>
					<th>姓名</th>
					<th>完成度</th>
					<th>分配量</th>
					<th>完成量</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${plans}" var="bean">
				<tr> <td>${bean.user.name }</td><td>${(bean.endnum/bean.num)*100 }%</td><td>${bean.num }</td><td>${bean.endnum }</td></tr>
				</c:forEach>
					
				</tbody>
				</table>
			</fieldset>
	</form>
</div>
</body>
</html>