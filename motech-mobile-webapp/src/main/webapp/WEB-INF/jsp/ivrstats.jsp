<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IVR Call Statistics</title>
</head>
<body>
<h1>Count of IVR Call Sessions</h1>
<table border="1">
<tr>
<th>Created in:</th>
<th>Last 5 Minutes</th>
<th>Last Hour</th>
<th>Last Day</th>
<th>All</th>
</tr>
<tr>
<td></td>
<td>${fiveMinuteSessionCount}</td>
<td>${oneHourSessionCount}</td>
<td>${oneDaySessionCount}</td>
<td>${allSessionCount}</td>
</tr>
</table>

</body>
</html>