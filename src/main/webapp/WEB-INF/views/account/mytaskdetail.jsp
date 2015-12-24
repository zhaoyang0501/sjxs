<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
<div>
	 <form id="mainform" method="post">
            
     <fieldset class="default">
				<legend>订单详情</legend>
				
				<table>
				 <tr>
                    <td>姓名</td>
                    <td>${sale.name }</td>
                </tr>
                <tr>
                    <td>客户姓名:</td>
                    <td>${sale.customer }</td>
                </tr>
                
                <tr>
                    <td>客户地址:</td>
                    <td>${sale.addr }</td>
                </tr>
				</table>
				</fieldset>
    
    <fieldset class="default">
				<legend>销售产品</legend>
			<table class="doc-table" style="    border-collapse: collapse;
    border-spacing: 0;
    width: 100%;
    margin-bottom: 1.65em;">
       		<thead>
       		<tr>
       			<th>名称</th>
       			<th>单价</th>
       			<th>数量</th>
       			<th>服务费</th>
       			<th>小计</th>
       		</tr>
       			
       		</thead>
       		<tbody id='itemdetails'>
       			<c:forEach items="${sale.saleitems }" var="bean">
       				<tr><td>${bean.name }</td><td>${bean.price }</td><td>${bean.total }</td>
       				<td>${bean.fee }</td><td>${bean.fee+bean.price*bean.total }</td></tr>
       			</c:forEach>
       		</tbody>
       	</table>
		</fieldset>
		
		
		  
    <fieldset class="default">
				<legend>合计</legend>
				
				<table>
				 <tr>
                    <td>订单合计</td>
                    <td>${sale.cash }</td>
                </tr>
                <tr>
                    <td>定金:</td>
                    <td>${sale.earlycash }</td>
                </tr>
                
                <tr>
                    <td>订单实际:</td>
                    <td>${sale.realcash }</td>
                </tr>
				</table>
				</fieldset>
        </form>
</div>
</body>
</html>