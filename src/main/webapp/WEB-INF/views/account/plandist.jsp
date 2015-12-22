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
		 <table class="formTable">
			<tr>
				<td>上级下发：</td>
				<input type="hidden" name='plan.id' value="${plan.id}"/>
				<td><input id="name" name="name" value='${plan.num }' type="text" class="easyui-validatebox" data-options="width: 150" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>未分配：</td>
				<td><input id="undist" name="undist" value='${plan.num }' type="text" class="easyui-validatebox" data-options="width: 150" readonly="readonly"/></td>
	
			</tr>
			<tr>
				<td>我的下属：</td>
				<td style="padding-top: 20px">
				
				<table  >
				 <thead>
			        <tr>
			            <th data-options="field:'code' ,width:150" >姓名</th>
			            <th data-options="field:'name',width:140" >分配数量</th>
			        </tr>
			    </thead>
  				  <tbody>
  				  <c:forEach items="${users }" var="bean" varStatus="stauts" >
  				  	<tr><td>${bean.name }</td><td>
  				  	<input  name="plans[${stauts.index }].user.id" value='${bean.id }' type="hidden" />
  			
  				  	<input  name="plans[${stauts.index }].num" value='' type="text" class="easyui-numberbox" data-options="width: 100" /></td></tr>
  				  </c:forEach>
			        </tbody>
       			 </table>
				</td>
			</tr>
		</table> 
	</form>
</div>

<script type="text/javascript">
//提交表单
$('#mainform').form({    
    onSubmit: function(){    
    	return true;
    },    
    success:function(data){   
    	successTip(data,dg,d);
    }    
});    
</script>
</body>
</html>