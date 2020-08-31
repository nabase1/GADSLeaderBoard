package com.nabase1.gadsleaderboard.utils;

import android.net.Uri;
import android.util.Log;

import com.nabase1.gadsleaderboard.modals.Learners;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import static com.nabase1.gadsleaderboard.Constants.BASE_URL_API;
import static com.nabase1.gadsleaderboard.Constants.QUERY_PARAM_KEY;

public class ApiUtils {

    public ApiUtils() {
    }

    public static URL buildUrl(String title){
        //String fullUrl = BASE_URL_API + title;
        URL url = null;
        Uri uri = Uri.parse(BASE_URL_API).buildUpon()
                .appendPath(title)
                .build();
        try {

            url = new URL(uri.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getJson(URL url) throws IOException {
        //HttpsURLConnection httpsURLConnection = NetCipher.getHttpsURLConnection(url);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

        try {
            InputStream inputStream = httpsURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasDate = scanner.hasNext();
            if (hasDate) {
                return scanner.next();
            } else {
                return null;
            }
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            return null;
        }
        finally {
            httpsURLConnection.disconnect();
        }
    }


    public static ArrayList getLeaderListFromJson(String json){
        String name = "name";
        String hours = "hours";
        String score = "score";
        String country = "country";
        String imageUrl = "badgeUrl";

        ArrayList<Learners> learners = new ArrayList<Learners>();

        try {

            JSONArray jsonArray = new JSONArray(json);
            int numberOfLearners = jsonArray.length();

            for(int i = 0; i<numberOfLearners; i++){

                JSONObject leanersObject = jsonArray.getJSONObject(i);
                Learners learners1 = new Learners();

                learners1.setName(leanersObject.getString(name));
                if(leanersObject.isNull(hours)){
                    learners1.setScore(leanersObject.getString(score));
                }else {
                    learners1.setHours(leanersObject.getString(hours));
                }
                learners1.setCountry(leanersObject.getString(country));
                learners1.setImageUrl(leanersObject.getString(imageUrl));

                learners.add(learners1);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return learners;
    }
}
