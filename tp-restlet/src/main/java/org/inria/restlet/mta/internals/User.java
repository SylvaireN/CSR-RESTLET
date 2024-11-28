package org.inria.restlet.mta.internals;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * User
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class User
{

    /** Internal id of the user.*/
    private int id_;

    /** Name of the user.*/
    private String name_;

    /** Age of the user.*/
    private int age_;

    /** Collection of tweets of the user. */
    private List<Tweet> tweets_;

    /** To be added: collection of tweets of the user. */

    public User(String name, int age)
    {
        name_ = name;
        age_ = age;
        tweets_ = new ArrayList<>();
    }

    public String getName()
    {
        return name_;
    }

    public void setName(String name)
    {
        name_ = name;
    }

    public int getAge()
    {
        return age_;
    }

    public void setAge(int age)
    {
        age_ = age;
    }

    public int getId()
    {
        return id_;
    }

    public void setId(int id)
    {
        id_ = id;
    }

    /**
     * Get all tweets of the user.
     *
     * @return the list of tweets
     */
    public List<Tweet> getTweets()
    {
        return tweets_;
    }

      /**
     * Add a new tweet to the user's collection.
     * 
     * @param tweet the tweet to add.
     */
    public void addTweet(Tweet tweet) {
        tweets_.add(tweet);
    }

    

}
