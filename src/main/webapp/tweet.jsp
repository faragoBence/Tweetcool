<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="icon" type="image/png" sizes="16x16" href="resources/favico/favicon-16x16.png">
    <link rel="manifest" href="resources/favico/site.webmanifest">
    <meta name="theme-color" content="#ffffff">
    <title>Posts</title>
</head>
<body>
<h1>Posts</h1>
<section class="posts">
<c:forEach var="person" items="${tweets}">
<table class="postTable">
    <tr class="postHeader">
        <td class="poster">
            <c:out value="${person.poster}"  />
        </td>
        <td class="time">
            <c:out value="${person.date}"  />
        </td>
    </tr>
    <tr class="postMain">
        <td colspan="2" class="main">
            <c:out value="'${person.content}'"  />
        </td>
    </tr>
</table>
</c:forEach>
</section>
<div class="goBack">
<a class="btn btnBack" href="index.html">Go back</a>
</div>
</body>
</html>
