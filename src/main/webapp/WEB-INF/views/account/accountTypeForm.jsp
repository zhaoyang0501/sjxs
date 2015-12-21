<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/account/accountType/${action}" method="post">
	<table  class="formTable">
		<tr>
			<td>类型名称：</td>
			<td>
			<input type="hidden" name="id" value="${id }" />
			<input name="name" type="text" value="${accountType.name }" class="easyui-validatebox"  data-options="required:true,validType:['length[0,255]']" />
			</td>
		</tr>
		<tr>
			<td>描述：</td>
			<td><input name="remark" type="text" value="${accountType.remark}" class="easyui-validatebox" data-options="required:false"/></td>
		</tr>
	</table>
	</form>
</div>
<script type="text/javascript">
$(function(){
	$('#mainform').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },    
	    success:function(data){   
	    	if(successTip(data,dg,d))
	    		dg.treegrid('reload');
	    }    
	}); 
});

</script>
</body>
</html>