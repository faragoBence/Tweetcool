package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import com.codecool.web.model.XmlParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostService {
    private final XmlParser xmlParser;
    private int id;

    private static List<Tweet> tweets;

    public PostService() {
        xmlParser = new XmlParser();
        id = 1000000;
        tweets = new ArrayList<>();
    }

    public void handleNewTweet(String poster, String message, String path) {
        id++;
        long date = Instant.now().toEpochMilli();
        Tweet tweet = new Tweet(poster, message, id, date);
        tweets.add(tweet);
        xmlParser.writeToXML(path, tweet);
    }

    public List<Tweet> filter(int limit, int offset, String poster, String time) {
        List<Tweet> tempTweets;
        tempTweets = filterTimeFrom(tweets, time);
        tempTweets = filterSkipPosts(tempTweets, offset);
        if ((poster != null || !poster.equals("")) && poster.length() > 1) {
            tempTweets = filterPosterOfPosts(tempTweets, poster);
        }
        tempTweets = filterNumberOfPosts(tempTweets, limit);
        return tempTweets;
    }


    public static List<Tweet> getTweets() {
        return tweets;
    }

    public List<Tweet> filterNumberOfPosts(List<Tweet> tweets, int posts) {
        List<Tweet> tempTweets = new ArrayList<>();
        for (int i = 0; i < tweets.size(); i++) {
            if (i == posts) {
                break;
            }
            tempTweets.add(tweets.get(i));
        }
        return tempTweets;
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

    public List<Tweet> filterTimeFrom(List<Tweet> tweets, String time) {
        long date = convertToMilli(time);
        List<Tweet> tempTweets = new ArrayList<>();
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getDate() > date) {
                tempTweets.add(tweets.get(i));
            }
        }
        return tempTweets;
    }

    public long convertToMilli(String date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
        Date d = new Date();
        try {
            d = f.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d.toInstant().toEpochMilli();
    }

    public void restart() {
        try {
            tweets = xmlParser.readXML("./webapps/tweets.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}
