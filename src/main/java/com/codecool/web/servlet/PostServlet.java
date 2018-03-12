package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int limit = Integer.parseInt(req.getParameter("limit"));
        int offset = Integer.parseInt(req.getParameter("offset"));
        String poster = req.getParameter("poster");
        String time = req.getParameter("time");
        List<Tweet> tweets = service.filter(limit, offset, poster, time);
        req.setAttribute("tweets", tweets);

        req.getRequestDispatcher("tweet.jsp").forward(req, resp);
    }
}
