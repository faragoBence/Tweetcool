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

@WebServlet("/tweets")
public class TweetsServlet extends HttpServlet {

    private PostService service = new PostService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int limit = Integer.parseInt(req.getParameter("limit"));
        int offset = Integer.parseInt(req.getParameter("offset"));
        String poster = req.getParameter("poster");
        String time = req.getParameter("time");
        service.restart();
        List<Tweet> tweets = service.filter(limit, offset, poster, time);
        req.setAttribute("tweets", tweets);

        req.getRequestDispatcher("tweet.jsp").forward(req, resp);
    }
}
