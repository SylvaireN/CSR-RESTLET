package org.inria.restlet.mta.internals;

import java.util.Date;

/**
 * Tweet
 */
public class Tweet
{
    private int id_;
    /** Content of the tweet. */
    private String content_;

    /** Timestamp of the tweet. */
    private Date timestamp_;

    public Tweet(int id,String content)
    {
        id_ = id;
        content_ = content;
        timestamp_ = new Date();
    }

    public int getId()
    {
        return id_;
    }

    public void SetId(int id)
    {
        id_ = id;
    }

    public String getContent()
    {
        return content_;
    }

    public void setContent(String content)
    {
        content_ = content;
    }

    public Date getTimestamp()
    {
        return timestamp_;
    }
}
