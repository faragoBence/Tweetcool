package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostServiceTest {
    PostService postService = new PostService();

    @BeforeEach
    void setUp() {
        postService.handleNewTweet("Bence", "I'm fine");
        postService.handleNewTweet("Goat", "MEEEEE");
        postService.handleNewTweet("Squirrel", "Accorn!");
        postService.handleNewTweet("Dog", "Woooof!");
        postService.handleNewTweet("Mentor", "Assignments graded");
        postService.handleNewTweet("Dog", "Wooof wooof");
    }

    @Test
    void filterNumberOfPosts() {
        assertEquals(3, postService.filterNumberOfPosts(postService.getTweets(), 3).size());
        assertThrows(IndexOutOfBoundsException.class, () -> postService.filterNumberOfPosts(postService.getTweets(), 30));
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
        assertEquals(6, postService.filterTimeFrom(postService.getTweets(), 1111).size());
    }

    @Test
    void allTogether() {
        List<Tweet> tweets = postService.getTweets();
        assertEquals(6, tweets.size());
        tweets = postService.filterTimeFrom(tweets, 1);
        assertEquals(6, tweets.size());
        tweets = postService.filterPosterOfPosts(tweets, "Dog");
        assertEquals(2, tweets.size());
        tweets = postService.filterNumberOfPosts(tweets, 2);
        assertEquals(2, tweets.size());
        tweets = postService.filterSkipPosts(tweets, 1);
        assertEquals("Wooof wooof", tweets.get(0).getContent());
    }
}
