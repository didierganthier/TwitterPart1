package com.codepath.apps.restclienttemplate.models;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;

import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
    public String body,createdAt;
    public long uid;
    public User user;

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        return tweet;
    }
}
