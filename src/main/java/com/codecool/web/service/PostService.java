package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import com.codecool.web.model.XmlParser;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PostService {
    private final XmlParser xmlParser;
    private int id;
    private List<Tweet> tweets;

    public PostService() {
        xmlParser = new XmlParser();
        id = 1000000;
        tweets = new ArrayList<>();
    }

    public void handleNewTweet(String poster, String message) {
        id++;
        long date = Instant.now().toEpochMilli();
        Tweet tweet = new Tweet(poster, message, id, date);
        tweets.add(tweet);
        xmlParser.writeToXML("src/main/resources/tweets.xml", tweet);
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
