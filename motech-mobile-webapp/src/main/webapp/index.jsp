<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMP Test Page</title>
    </head>
    <body>
        <h1>IMP Test Page</h1>
        <form method="GET" action="incomingmessage">
            Sender Number: <input type="text" name="from" id="from" />
            Message:
            <textarea cols="40" rows="10" name="text" id="text"></textarea>
            <input type="submit" value="send" />
        </form>
    </body>
</html>