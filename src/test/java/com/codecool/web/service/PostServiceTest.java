package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest {
    PostService postService = new PostService();

    @BeforeEach
    void setUp() {
        postService.handleNewTweet("Bence", "I'm fine", "src/main/resources/Servicetest.xml");
        postService.handleNewTweet("Goat", "MEEEEE", "src/main/resources/Servicetest.xml");
        postService.handleNewTweet("Squirrel", "Accorn!", "src/main/resources/Servicetest.xml");
        postService.handleNewTweet("Dog", "Woooof!", "src/main/resources/Servicetest.xml");
        postService.handleNewTweet("Mentor", "Assignments graded", "src/main/resources/Servicetest.xml");
        postService.handleNewTweet("Dog", "Wooof wooof", "src/main/resources/Servicetest.xml");
    }

    @Test
    void filterNumberOfPosts() {
        assertEquals(3, postService.filterNumberOfPosts(postService.getTweets(), 3).size());
    }

    @Test
    void filterSkipPosts() {
        assertEquals(3, postService.filterSkipPosts(postService.getTweets(), 3).size());
        assertEquals(0, postService.filterSkipPosts(postService.getTweets(), 7).size());
        assertEquals("Dog", postService.filterSkipPosts(postService.getTweets(), 3).get(0).getPoster());
    }

    @Test
    void filterPosterOfPosts() {
        assertEquals(2, postService.filterPosterOfPosts(postService.getTweets(), "Dog").size());
        assertEquals("Accorn!", postService.filterPosterOfPosts(postService.getTweets(), "Squirrel").get(0).getContent());
        assertEquals(0, postService.filterPosterOfPosts(postService.getTweets(), "Jani").size());
    }

    @Test
    void filterTimeFrom() {
        assertEquals(6, postService.filterTimeFrom(postService.getTweets(), "1990-01-01").size());
        assertEquals(0, postService.filterTimeFrom(postService.getTweets(), "2020-01-01").size());
    }

    @Test
    void allTogether() {
        List<Tweet> tweets = postService.getTweets();
        assertEquals(6, tweets.size());
        tweets = postService.filterTimeFrom(tweets, "1990-01-01");
        assertEquals(6, tweets.size());
        tweets = postService.filterPosterOfPosts(tweets, "Dog");
        assertEquals(2, tweets.size());
        tweets = postService.filterNumberOfPosts(tweets, 2);
        assertEquals(2, tweets.size());
        tweets = postService.filterSkipPosts(tweets, 1);
        assertEquals("Wooof wooof", tweets.get(0).getContent());
    }
}
