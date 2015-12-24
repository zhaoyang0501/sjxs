<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
    <div class="easyui-panel" title="定金" >
        <div style="padding:10px 60px 20px 60px">
        <form id="mainform" method="post">
            <table cellpadding="5">
            
            	<tr>
                    <td>当前账户定金余额:</td>
                    <td>${cash }</td>
	
                </tr>
                <tr>
                    <td>姓名:</td>
                    <td><input id="num"  name="num" type="text" class="easyui-validatebox " data-options="required:'required',validType:'length[1,6]'"></input></td>
            
                </tr>
                
                 <tr>
                    <td>定金金额:</td>
                    <td><input id="num"  name="num" type="text" class="easyui-validatebox easyui-numberbox" data-options="required:'required',validType:'length[1,6]'"></input></td>
            
                </tr>
                
                     <tr>
                    <td>日期:</td>
                  	<td><input class="easyui-datebox"   name="endDate" 
       					 data-options="required:true,showSeconds:false" value="2015-12-24" style="width:150px"></td>
                </tr>
                
            </table>
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