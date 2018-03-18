package com.codecool.web.servlet;

import com.codecool.web.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/new-tweet")
public class NewTweetServlet extends HttpServlet {

    private PostService service = new PostService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        String message = req.getParameter("message");

        service.handleNewTweet(name, message, "./webapps/tweets.xml");
        Cookie cookie = new Cookie("poster", name);

        cookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(cookie);
        service.restart();
        req.setAttribute("tweets", PostService.getTweets());

        req.getRequestDispatcher("tweet.jsp").forward(req, resp);
    }
}
