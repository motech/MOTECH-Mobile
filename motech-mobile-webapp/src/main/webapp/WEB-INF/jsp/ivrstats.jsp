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
<h1>Search</h1>
<table>
<tr>
<form>
<td>
User ID:<input type="text" name="userid"/>
<input type="submit"/>
</td>
</form>
<form>
<td>
Phone Number:<input type="text" name="phone"/>
<input type="submit"/>
</td>
</form>
</tr>
</table>
<h1>Call Session Stats</h1>
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

<h1>Call Stats</h1>


<table>
<tr>

<td>
<table border="1">
<th colspan="2">Last 5 Minutes</th>
<c:forEach var="stat" items="${fiveMinuteCallStats}">
<tr>
<td>${stat.status}</td>
<td>${stat.count}</td>
</tr>
</c:forEach>
</table>
</td>

<td>
<table border="1">
<th colspan="2">Last Hour</th>
<c:forEach var="stat" items="${oneHourCallStats}">
<tr>
<td>${stat.status}</td>
<td>${stat.count}</td>
</tr>
</c:forEach>
</table>
</td>

<td>
<table border="1">
<th colspan="2">Last Day</th>
<c:forEach var="stat" items="${oneDayCallStats}">
<tr>
<td>${stat.status}</td>
<td>${stat.count}</td>
</tr>
</c:forEach>
</table>
</td>

<td>
<table border="1">
<th colspan="2">All</th>
<c:forEach var="stat" items="${allCallStats}">
<tr>
<td>${stat.status}</td>
<td>${stat.count}</td>
</tr>
</c:forEach>
</table>
</td>

</tr>
</table>

<h1>Recording Stats</h1>

<table border="1">
<th>Name</th>
<th>Listens</th>
<th>Average Time Listened</th>
<c:forEach var="stat" items="${recordingStats}">
<tr>
<td>${stat.name}</td>
<td>${stat.totalListens }</td>
<td>${stat.averageTimeListened }</td>
</tr>
</c:forEach>

</table>

</body>
</html>