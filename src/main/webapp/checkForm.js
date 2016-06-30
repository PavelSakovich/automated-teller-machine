<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
    function checkForm(form){
        if (document.getElementById('q').value==""){
            document.getElementById('err').innerHTML='<font color=red>Пожалуйста, укажите количество!</font>';
            return false;
        }
        else {
            document.getElementById('err').innerHTML="";
        };
        return true;
    };
</script>