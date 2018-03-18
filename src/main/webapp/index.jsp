<!DOCTYPE html>
<html lang="en">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<head>
    <script type="text/javascript" src="resources/js/methods.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="icon" type="image/png" sizes="16x16" href="resources/favico/favicon-16x16.png">
    <link rel="manifest" href="resources/favico/site.webmanifest">
    <meta name="theme-color" content="#ffffff">
    <title>TweetCool</title>
</head>

<body>
    <header>
        <h1>TweetCool</h1>

    </header>

    <section class="forms">
        <table class="formTable">
            <tr>
                <td>
                    <form name="post" class="form post" action="new-tweet" onsubmit="return postForm();" method="POST">
                        <img src="resources/img/Twitter-Download-PNG.png" alt="logo" class="logo"><br> Name:
                        <br>
                        <input type="text" name="name" value="${myCookie}"><br> Message:<br>
                        <input type="text" name="message"><br>
                        <div class="button-div">
                            <input type="submit" value="Send" class="btn">
                        </div>
                    </form>
                </td>
                <td>
                    <form class="form tweets" action="tweets" method="GET">
                        <div class="elements">
                            Number of posts:<br>
                            <input type="number" name="limit" value="10"><br> Posts to skip:<br>
                            <input type="number" name="offset" value="0"><br>Poster:<br>
                            <input type="text" name="poster"><br> Time:<br>
                            <input type="date" name="time" value="1970-01-01"><br>
                            <div class="button-div">
                                <input type="submit" value="Get" class="btn btnget">
                            </div>
                        </div>

                    </form>

                </td>
            </tr>
        </table>



    </section>



</body>


</html>
