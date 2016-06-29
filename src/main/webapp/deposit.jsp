<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Банкомат - Положить деньги</title>
    </head>
    <body>
        <h3>Положить деньги:</h3>
         <form action="deposit" method="post">
         <table id="table" border="1">
              <tr>
                <td>Купюры</td>
                <td>
                    <select name="selectedValue" required>
                        <option>Выберите из списка</option>
                        <c:forEach items="${denominations}" var="entry">
                            <option value="${entry}">${entry}</option>
                        </c:forEach>
                    </select>
                </td>
              </tr>
              <tr>
                <td>Количество</td>
                <td><input type="text" name="quantity" value=""/></td>
              </tr>
         </table><br />
         <button type="submit">Положить деньги</button>
         </form>
         <br />Вернуться в <a href="/atm">Меню</a>
    </body>
</html>