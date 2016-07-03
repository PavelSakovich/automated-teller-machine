<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="style.css" />
	<link rel="stylesheet" href="buttons.css" />
    <title>Банкомат - Снять деньги</title>
    <%@include file="checkForm.js" %>
</head>
<body>

<h2>Банкомат → Снять деньги</h2>
<%@include file="navigation.jsp" %>

<form onsubmit="return checkForm(this)" action="withdraw" method="post">
    <div class="center">
        <p>
           <table id="table" border="1" style="margin: 0px auto;">
                <tr>
                  <td>Количество</td>
                  <td><input id="q" type="text" name="quantity" value=""/></td>
                </tr>
                <div id='err' class='error'></div>
           </table>
        </p>
    </div>
    <br />
    <button class="small color green button" type="submit">Снять деньги</button>
</form>
</body>
</html>