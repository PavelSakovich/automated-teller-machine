<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Банкомат - Снять деньги</title>
    </head>
    <body>
        <h3>Снять деньги:</h3>
         <form action="withdraw" method="post">
         <table id="table" border="1">
              <tr>
                <td>Количество</td>
                <td><input type="text" name="quantity" value=""/></td>
              </tr>
         </table><br />
         <button type="submit">Снять деньги</button>
         </form>
         <br />Вернуться в <a href="/atm">Меню</a>
    </body>
</html>