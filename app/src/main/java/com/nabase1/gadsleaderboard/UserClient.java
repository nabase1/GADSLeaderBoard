package com.nabase1.gadsleaderboard;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.nabase1.gadsleaderboard.Constants.*;

public interface UserClient {


    @POST(GOOGLE_FORMS_ID)
    @FormUrlEncoded
    Call<ResponseBody>submitProject(
            @Field(FIRST_NAME_ID) String first_name,
            @Field(LAST_NAME_ID) String last_name,
            @Field(EMAIL_ID) String email,
            @Field(LINK_TO_PROJECT) String github_link
    );


}
