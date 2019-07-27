package com.codepath.apps.restclienttemplate.models;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.codepath.apps.restclienttemplate.TimelineAcivity;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    public String name, screenName,profileImageUrl;
    public long uid;

    public static User fromJson(JSONObject jsonObject) throws JSONException
    {
        User user = new User();
        user.name = jsonObject.getString("name");
        user.uid = jsonObject.getLong("id");
        user.screenName = jsonObject.getString("screen_name");
        user.profileImageUrl = jsonObject.getString("profile_image_url");
        return user;
    }
}
