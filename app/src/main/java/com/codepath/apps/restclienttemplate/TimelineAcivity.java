package com.codepath.apps.restclienttemplate;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TimelineAcivity extends AppCompatActivity {

    private TwitterClient client;
    private RecyclerView rvTweets;
    private TweetsAdapter tweetsAdapter;
    private List<Tweet>tweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        client = TwitterApp.getRestClient(this);
        rvTweets = findViewById(R.id.rvTweets);
        tweets = new ArrayList<>();
        tweetsAdapter = new TweetsAdapter(this,tweets);
        rvTweets.setLayoutManager(new LinearLayoutManager(this));
        rvTweets.setAdapter(tweetsAdapter);
        populateHomeTimeline();
    }

    private void populateHomeTimeline()
    {
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for (int i=0; i < response.length(); i++){
                    try {
                        JSONObject jsonTweetObject = response.getJSONObject(i);
                        Tweet tweet = Tweet.fromJson(jsonTweetObject);
                        tweets.add(tweet);
                        tweetsAdapter.notifyItemInserted(tweets.size() - 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(TimelineAcivity.this, responseString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(TimelineAcivity.this, errorResponse.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
