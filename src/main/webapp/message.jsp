<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="style.css" />
	<link rel="stylesheet" href="buttons.css" />
    <title>Банкомат - Информация</title>
</head>
<body>

<h2>Банкомат → Информация</h2>

<button class="small button" onclick="location.href='/atm'";>Главная</button>
<button class="small button" onclick="location.href='deposit'";>Положить деньги</button>
<button class="small button" onclick="location.href='withdraw'";>Снять деньги</button>
<button class="small button" onclick="location.href='balance'";>Баланс</button>
<button class="color red small button" onclick="location.href='log'";>Скачать лог-файлы</button>
<br /><br />

<div class="center">
  <p>${message}</p>
</div>

</body>
</html>