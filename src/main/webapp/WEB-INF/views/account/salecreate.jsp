<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<style type="text/css">
		.doc-table{
	border-collapse:collapse;
	border-spacing:0;
	width:100%;
	margin-bottom: 1.65em;
}
.doc-table th,.doc-table td{
	border:1px solid #8CACBB;
	padding:0.3em 0.7em;
}
.doc-table th{
	background:#eee;
}
.doc-table pre{
	font-family:Verdana;
	font-size:12px;
	color:#006600;
	background:#fafafa;
	padding:5px;
	margin: 12px 0;
	line-height: 120%;
}
.doc-table p{
	margin: 14px 0;
	line-height: 100%;
}
		</style>

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
                    <td><input id="nowcash"  name="name" type="text" ></input></td>
                </tr>
                <tr>
                    <td>客户姓名:</td>
                    <td><input id="nowcash"  name="customer" type="text"  ></input></td>
                </tr>
                
                <tr>
                    <td>客户地址:</td>
                    <td><input id="nowcash"  name="addr" type="text"  ></input></td>
                </tr>
				</table>
				</fieldset>
    
    <fieldset class="default">
				<legend>销售产品</legend>
				 <div style="margin:20px 0 10px 0;">
       					 <a href="#" class="easyui-linkbutton" onclick="addtype()">新增类型</a>
       					 <a href="#" class="easyui-linkbutton" onclick="addtype()">新增产品</a>
   				 </div>
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
       			<th>操作</th>
       		</tr>
       			
       		</thead>
       		<tbody id='itemdetails'>
       		<!-- <tr>
       			<td>
       			<input name='saleitems[0].name' value=''/>
       			<input name='saleitems[0].num' value=''/>
       			</td>
       		</tr> -->
       		</tbody>
       	</table>
		</fieldset>
		
		
		  
    <fieldset class="default">
				<legend>合计</legend>
				
				<table>
				 <tr>
                    <td>订单合计</td>
                    <td><input id="cash" value="131"  name="cash" type="text"  readonly="readonly"></input></td>
                </tr>
                <tr>
                    <td>定金:</td>
                    <td><input id="earlycash" value="11"   name="earlycash" type="text"  readonly="readonly"></input></td>
                </tr>
                
                <tr>
                    <td>订单合计:</td>
                    <td><input id="realcash"  value="12" name="realcash" type="text"  readonly="readonly"></input></td>
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
    
     <div id="addtype" >
				<table>
				 <tr>
                    <td>名称</td>
                    <td>
                    	<select id='itemname'>
                    	<c:forEach var="b" items="${types}">
                    		<option value="${b.name }">${b.name }</option>
                    	</c:forEach>
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td>单价:</td>
                    <td><input id='itemprice'  name="customer" type="text"  ></input></td>
                </tr>
                
                <tr>
                    <td>数量:</td>
                    <td><input id="itemnum"  name="addr" type="text"  ></input></td>
                </tr>
                 <tr>
                    <td>小计:</td>
                    <td><input id="itemtoal"  name="addr" type="text"  ></input></td>
                </tr>
				</table>
		</div>

    <script>
    var d1;
    var d;
    $(function(){
    	d1=$('#addtype').dialog({
        });
    	d1.panel('close');
    }); 
    function addtype(){
    	d1=$('#addtype').dialog({
            title: '添加类型',
            width: 400,
            height: 200,
            closed: false,
            cache: false,
            modal: true,
            buttons:[{
    			text:'提交',
    			handler:function(){
    				d1.panel('close');
    				var index=$("#itemdetails tr").size() ;
    				$("#itemdetails").append("<tr>"+
    		       			"<td><input name='saleitems["+index+"].name' value='"+$("#itemname").val()+"'/>"+$("#itemname").val()+"</td>"+
    		       			"<td><input name='saleitems["+index+"].price' value='"+$("#itemprice").val()+"'/>"+$("#itemprice").val()+"</td>"+
    		       			"<td><input name='saleitems["+index+"].num' value='"+$("#itemprice").val()+"'/>"+$("#itemnum").val()+""+$("#itemnum").val()+"</td>"+
    		       			"<td>0.0</td>"+
    		       			"<td><input name='saleitems["+index+"].total' value='"+$("#itemtoal").val()+"'/>"+$("#itemtoal").val()+"</td>"+
    		       			"<td> <a href='#' class='easyui-linkbutton' onclick='del()'>删除</a></td>"+
    		       			"</tr>");
    			}
    		},{
    			text:'关闭',
    			handler:function(){}
    		}]
        });
    	d1.dialog('refresh');
    }
    
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