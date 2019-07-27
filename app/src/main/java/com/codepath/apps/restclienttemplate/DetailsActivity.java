package com.codepath.apps.restclienttemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class DetailsActivity extends AppCompatActivity {

    private TextView tweetBody, tweetScreenName, tweetTimeStamp;
    private ImageView profileDetails;
    private Tweet detailsTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tweetBody = findViewById(R.id.tweetBody);
        tweetScreenName = findViewById(R.id.tweetScreenName);
        tweetTimeStamp = findViewById(R.id.tweetTimeStamp);
        profileDetails = findViewById(R.id.userProfile);

        tweetBody.setText(getIntent().getStringExtra("body"));
        tweetScreenName.setText(getIntent().getStringExtra("screenName"));
        tweetTimeStamp.setText(getIntent().getStringExtra("timestamp"));
        Glide.with(this)
                .load(getIntent().getStringExtra("profileUrl"))
                .apply(new RequestOptions().transform(new RoundedCorners(50)).placeholder(R.drawable.bee))
                .into(profileDetails);
    }
}
