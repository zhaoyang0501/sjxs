<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx }/account/card/${action}" method="post">
		<table class="formTable">
			<tr>
				<td>开户人：</td>
				<td>
				<input type="hidden" name="id" value="${id }"/>
					 <select   data-options="required:'required'" class="easyui-combobox easyui-validatebox" name="accountUser.id" style="width:200px;">
				      <c:forEach items="${accountUsers}" var="bean">
				        <option value="${bean.id }">${bean.name }</option>
				      </c:forEach>
   					 </select>
				</td>
			</tr>
			<tr>
				<td>账户类型：</td>
				<td><select data-options="required:'required'" class="easyui-combobox easyui-validatebox"  name="accountType.id" style="width:200px;">
				      <c:forEach items="${accountTypes}" var="bean">
				        <option value="${bean.id }">${bean.name }</option>
				      </c:forEach>
   					 </select>
   				</td>
			</tr>
			<tr>
				<td>开户人身份证号码：</td>
				<td><input id="personno" value='${card.personno}' name="personno" type="text" class="easyui-validatebox" data-options="width: 150,required:'required',validType:'length[10,16]'"/></td>
			</tr>
			<tr>
				<td>开户银行：</td>
				<td><input id="bank" value='${card.bank}'  name="bank" type="text" class="easyui-validatebox" data-options="width: 150,required:'required',validType:'length[0,40]'"/></td>
			</tr>
			<tr>
				<td>账户金额：</td>
				<td>
				<input id="cash" value='${card.cash}'  name="cash" type="text" class="easyui-validatebox easyui-numberbox" data-options="width: 150,required:'required',validType:'length[1,6]'"/></td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
var action="${action}";
//用户 添加修改区分
if(action=='create'){
	$("input[name='gender'][value=1]").attr("checked",true); 
	//用户名存在验证
	$('#loginName').validatebox({    
	    required: true,    
	    validType:{
	    	length:[2,20],
	    	remote:["${ctx}/system/user/checkLoginName","loginName"]
	    }
	});  
}else if(action=='update'){
	/* $("input[name='loginName']").attr('readonly','readonly');
	$("input[name='loginName']").css('background','#eee')
	$("input[name='gender'][value=${user.gender}]").attr("checked",true); */
	$("select[name='accountUser.id']").val("${card.accountUser.id}");
	$("select[name='accountType.id']").val("${card.accountType.id}");
	
}

//提交表单
$('#mainform').form({    
    onSubmit: function(){    
    	var isValid = $(this).form('validate');
		return isValid;	// 返回false终止表单提交
    },    
    success:function(data){   
    	successTip(data,dg,d);
    }    
});    
</script>
</body>
</html>