<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="style.css" />
	<link rel="stylesheet" href="buttons.css" />
    <title>Банкомат - Баланс</title>
</head>
<body>

<h2>Банкомат → Баланс</h2>
<%@include file="navigation.jsp" %>

<div class="center">
  <p>
      <h3>Баланс: ${balance} грн.</h3>
        ${status}
  </p>
</div>

</body>
</html>