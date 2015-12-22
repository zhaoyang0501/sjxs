<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
    <div class="easyui-panel" title="订单录入" >
        <div style="padding:10px 60px 20px 60px">
        <form id="mainform" method="post">
            
     <fieldset class="default">
				<legend>订单详情</legend>
				
				<table>
				 <tr>
                    <td>姓名</td>
                    <td><input id="nowcash"  name="nowcash" type="text"  readonly="readonly"></input></td>
                </tr>
                <tr>
                    <td>客户姓名:</td>
                    <td><input id="nowcash"  name="nowcash" type="text"  readonly="readonly"></input></td>
                </tr>
                
                <tr>
                    <td>客户地址:</td>
                    <td><input id="nowcash"  name="nowcash" type="text"  readonly="readonly"></input></td>
                </tr>
				</table>
				</fieldset>
    
    <fieldset class="default">
				<legend>销售产品</legend>
					<table class="easyui-datagrid"  data-options="iconCls: 'icon-edit' ">
       		<thead>
       		<tr>
       			<th>名称</th>
       			<th>单价</th>
       			<th>数量</th>
       			<th>服务费</th>
       			<th>小计</th>
       		</tr>
       			
       		</thead>
       		<tbody>
       			<tr>
       			<td>钢笔</td>
       			<td>100.2</td>
       			<td>500</td>
       			<td>500</td>
       			<td>500</td>
       			</tr>
       			<tr>
       			<td>钢笔</td>
       			<td>100.2</td>
       			<td>500</td>
       			<td>500</td>
       			<td>500</td>
       			</tr>
       			<tr>
       			<td>钢笔</td>
       			<td>100.2</td>
       			<td>500</td>
       			<td>500</td>
       			<td>500</td>
       			</tr>
       		</tbody>
       	</table>
		</fieldset>
		
		
		  
    <fieldset class="default">
				<legend>合计</legend>
				
				<table>
				 <tr>
                    <td>订单合计</td>
                    <td><input id="nowcash"  name="nowcash" type="text"  readonly="readonly"></input></td>
                </tr>
                <tr>
                    <td>定金:</td>
                    <td><input id="nowcash"  name="nowcash" type="text"  readonly="readonly"></input></td>
                </tr>
                
                <tr>
                    <td>订单合计:</td>
                    <td><input id="nowcash"  name="nowcash" type="text"  readonly="readonly"></input></td>
                </tr>
				</table>
				</fieldset>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">取消</a>
        </div>
        </div>
    </div>
    <script>
    $('#mainform').form({    
        onSubmit: function(){    
        	var isValid = $(this).form('validate');
    		return isValid;	// 返回false终止表单提交
        },    
        success:function(data){   
        	successTip(data);
        	$("input[name='cash']").val("0");
        	$("input[name='man']").val("");
        	$("input[name='remark']").val("");
        }    
    });
     function submitForm(){
         $('#mainform').form('submit');
     }
     function clearForm(){
         $('#mainform').form('clear');
     }
     
        
      

    </script>
</body>
</html>