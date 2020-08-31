package com.nabase1.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.nabase1.gadsleaderboard.databinding.ActivitySubmitProjectBinding;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitProject extends AppCompatActivity {

    private ActivitySubmitProjectBinding mBinding;


    public static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .baseUrl(Constants.GOOGLE_FORMS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit sRetrofit = sBuilder.build();
    private Call<ResponseBody> mCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_submit_project);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_submit_project);

//        Toolbar toolbar = findViewById(R.id.mtoolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
//        toolbar.setTitle("Google Africa \n Developer Scholarship");
//
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        //create retrofit instance


        mBinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeSubmitProject(mBinding.editTextFirstName.getText().toString(),
                        mBinding.editTextLastName.getText().toString(),
                        mBinding.editTextEmail.getText().toString(),
                        mBinding.editTextLink.getText().toString());
            }
        });

    }

    private void executeSubmitProject(String fname, String lname, String email, String githubLink) {
        UserClient userClient = sRetrofit.create(UserClient.class);

        Call<ResponseBody> call = userClient.submitProject(fname,
                lname,
                email,
                githubLink);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
    
}