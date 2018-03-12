package com.codecool.web.servlet;

import com.codecool.web.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class PostServlet extends HttpServlet {

    private PostService service = new PostService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");

        String message = req.getParameter("message");

        service.handleNewTweet(name, message);


        req.setAttribute("tweets", service.getTweets());

        req.getRequestDispatcher("tweet.jsp").forward(req, resp);
    }
}
