<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="style.css" />
	<link rel="stylesheet" href="buttons.css" />
    <title>Банкомат</title>
    <%@include file="checkForm.js" %>
</head>
<body>

<h2>Банкомат → Положить деньги</h2>

<button class="small button" onclick="location.href='/atm'";>Главная</button>
<button class="small button" onclick="location.href='deposit'";>Положить деньги</button>
<button class="small button" onclick="location.href='withdraw'";>Снять деньги</button>
<button class="small button" onclick="location.href='balance'";>Баланс</button>
<button class="color red small button" onclick="location.href='log'";>Скачать лог-файлы</button>
<br /><br />

           <form onsubmit="return checkForm(this)" action="deposit" method="post">
<div class="center">
  <p>
           <table id="table" border="1" style="margin: 0px auto;">
                <tr>
                  <td>Купюры</td>
                  <td>
                      <select name="selectedValue" required>
                          <c:forEach items="${denominations}" var="entry">
                              <option value="${entry}">${entry}</option>
                          </c:forEach>
                      </select>
                  </td>
                </tr>
                <tr>
                  <td>Количество</td>
                  <td><input id="q" type="text" name="quantity" value=""/></td>
                </tr>
                <div id='err' class='error'></div>
           </table>
  </p>
</div>
<br />
<button class="small color green button" type="submit">Положить деньги</button>
           </form>

</body>
</html>