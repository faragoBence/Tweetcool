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
        <form class="form filter" action="tweets" method="GET">
            <div class="elements">
                Number of posts:<br>
                <input type="number" name="limit" value="10"><br> Posts to skip:<br>
                <input type="number" name="offset" value="0"><br>Poster:<br>
                <input type="text" name="poster"><br> Time:<br>
                <input type="date" name="time" value="1970-01-01"><br>
                <div class="button-div">
                    <input type="submit" value="Refresh" class="btn btnget">
                </div>
            </div>

        </form>
        <section class="posts">
            <c:forEach var="tweet" items="${tweets}">
                <table class="postTable">
                    <tr class="postHeader">
                        <td class="poster">
                            <c:out value="${tweet.poster}" />
                        </td>
                        <td class="time">
                            <c:out value="${tweet.dateFormat}" />
                        </td>
                    </tr>
                    <tr class="postMain">
                        <td colspan="2" class="main">
                            <c:out value="'${tweet.content}'" />
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
