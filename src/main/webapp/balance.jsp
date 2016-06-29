<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Банкомат - Баланс</title>
    </head>
    <body>
        <h3>Баланс: ${balance} грн.</h3>
        <h3>Купюры:</h3>
            ${status}
        <br />Вернуться в <a href="/atm">Меню</a>
    </body>
</html>