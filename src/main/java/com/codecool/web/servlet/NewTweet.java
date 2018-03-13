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
public class NewTweet extends HttpServlet {

    private PostService service = new PostService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        String message = req.getParameter("message");

        service.handleNewTweet(name, message, "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\TweetcoolByFB\\tweets.xml");

        Cookie nameCookie = new Cookie("name", name);
        Cookie messageCookie = new Cookie("message", message);
        nameCookie.setMaxAge(3600 * 24);
        nameCookie.setMaxAge(3600 * 24);
        resp.addCookie(nameCookie);
        resp.addCookie(messageCookie);


        req.setAttribute("tweets", service.getTweets());

        req.getRequestDispatcher("tweet.jsp").forward(req, resp);
    }
}
