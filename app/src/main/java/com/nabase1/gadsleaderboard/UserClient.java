package com.nabase1.gadsleaderboard;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserClient {

    @FormUrlEncoded
    @POST(Constants.GOOGLE_FORMS_BASE_URL + Constants.GOOGLE_FORMS_ID)
    Call<ResponseBody>submitProject(
            @Field(Constants.FIRST_NAME_ID) String first_name,
            @Field(Constants.LAST_NAME_ID) String last_name,
            @Field(Constants.EMAIL_ID) String email,
            @Field(Constants.LINK_TO_PROJECT) String github_link
    );


}
