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

    public List<Tweet> filterNumberOfPosts(List<Tweet> tweets, int posts) {
        if (posts <= tweets.size()) {
            List<Tweet> tempTweets = new ArrayList<>();
            for (int i = 0; i < posts; i++) {
                tempTweets.add(tweets.get(i));
            }
            return tempTweets;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public List<Tweet> filterSkipPosts(List<Tweet> tweets, int skip) {
        List<Tweet> tempTweets = new ArrayList<>();
        for (int i = skip; i < tweets.size(); i++) {
            tempTweets.add(tweets.get(i));
        }
        return tempTweets;
    }

    public List<Tweet> filterPosterOfPosts(List<Tweet> tweets, String poster) {
        List<Tweet> tempTweets = new ArrayList<>();
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getPoster().equals(poster)) {
                tempTweets.add(tweets.get(i));
            }
        }
        return tempTweets;
    }

    public List<Tweet> filterTimeFrom(List<Tweet> tweets, long date) {
        List<Tweet> tempTweets = new ArrayList<>();
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getDate() > date) {
                tempTweets.add(tweets.get(i));
            }
        }
        return tempTweets;
    }

}
