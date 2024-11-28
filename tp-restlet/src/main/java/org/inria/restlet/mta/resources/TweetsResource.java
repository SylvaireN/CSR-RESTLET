package org.inria.restlet.mta.resources;


import org.inria.restlet.mta.database.InMemoryDatabase;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Resource exposing tweets of a user.
 *
 * @author msimonin
 * @author ctedeschi
 *
 */
public class TweetsResource extends ServerResource {

    /** Database. */
    private InMemoryDatabase db_;

    /** User associated with this resource. */
    private User user_;

    /**
     * Constructor.
     * Called for every single request to /users/{userId}/tweets.
     */
    public TweetsResource() {
        db_ = (InMemoryDatabase) getApplication().getContext().getAttributes()
                .get("database");
    }

    /**
     * Handle GET requests to retrieve all tweets of the user.
     * 
     * @return a JSON representation of the user's tweets.
     * @throws Exception if an error occurs during processing.
     */
    @Get("json")
    public Representation getTweets() throws Exception {
        // Retrieve the userId from the request
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        user_ = db_.getUser(userId);

        if (user_ == null) {
            // Return a 404 if the user does not exist
            throw new Exception("User not found");
        }

        // Convert the list of tweets into a JSON array
        JSONArray tweetsArray = new JSONArray();
        for (Tweet tweet : user_.getTweets()) {
            JSONObject tweetObject = new JSONObject();
            tweetObject.put("id", tweet.getId());
            tweetObject.put("content", tweet.getContent());
            tweetObject.put("timestamp", tweet.getTimestamp());
            tweetsArray.put(tweetObject);
        }

        return new JsonRepresentation(tweetsArray);
    }

    /**
     * Handle POST requests to add a new tweet for the user.
     * 
     * @param entity JSON representation of the tweet.
     * @return a JSON representation of the created tweet.
     * @throws Exception if an error occurs during processing.
     */
    @Post("json")
    public Representation addTweet(Representation entity) throws Exception {
        // Retrieve the userId from the request
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        user_ = db_.getUser(userId);

        if (user_ == null) {
            // Return a 404 if the user does not exist
            throw new Exception("User not found");
        }

        // Parse the incoming JSON to extract tweet details
        JsonRepresentation jsonRep = new JsonRepresentation(entity);
        JSONObject jsonObject = jsonRep.getJsonObject();
        String content = jsonObject.getString("content");

        // Create a new Tweet and add it to the user's list
        Tweet newTweet = new Tweet(user_.getTweets().size() + 1, content);
        user_.addTweet(newTweet);

        // Return the created tweet as a JSON response
        JSONObject tweetObject = new JSONObject();
        tweetObject.put("id", newTweet.getId());
        tweetObject.put("content", newTweet.getContent());
        tweetObject.put("timestamp", newTweet.getTimestamp());

        return new JsonRepresentation(tweetObject);
    }
}
