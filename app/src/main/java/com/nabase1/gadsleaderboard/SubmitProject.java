package com.nabase1.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.nabase1.gadsleaderboard.databinding.ActivitySubmitProjectBinding;
import com.nabase1.gadsleaderboard.databinding.ConfirmationDialogDesignBinding;
import com.nabase1.gadsleaderboard.databinding.DialogDesignBinding;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.nabase1.gadsleaderboard.Constants.*;
import static com.nabase1.gadsleaderboard.utils.ApiUtils.getClient;

public class SubmitProject extends AppCompatActivity {

    private static final String TAG = SubmitProject.class.getCanonicalName();
    private ActivitySubmitProjectBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_submit_project);

        Toolbar toolbar = findViewById(R.id.mtoolbar2);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //create retrofit instance

        mBinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFieldEmpty()){
                    showConfirmDialog();
                }else {
                    Toast.makeText(SubmitProject.this, getString(R.string.required_fields), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    //method for submission
    private void executeSubmitProject(String fname, String lname, String email, String githubLink) {

        UserClient userClient = getClient(GOOGLE_FORMS_BASE_URL).create(UserClient.class);  //instantiating userClient interface

        Call<ResponseBody> call = userClient.submitProject(fname,
                                    lname,
                                    email,
                                    githubLink);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){
                    mBinding.progressBar3.setVisibility(View.INVISIBLE);
                    clearFields();
                    showDialog(getString(R.string.submission_successful), R.drawable.ic_baseline_check_circle_24);
                }else {

                    mBinding.progressBar3.setVisibility(View.INVISIBLE);
                    showDialog(getString(R.string.submission_not_successful), R.drawable.ic_baseline_report_problem_24);
                    Log.d(TAG, String.valueOf(response.code()));
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mBinding.progressBar3.setVisibility(View.INVISIBLE);
                Log.d("Failed", t.getMessage());
                showDialog(getString(R.string.submission_not_successful), R.drawable.ic_baseline_report_problem_24);
            }


        });

    }

    public void showDialog(String message, Integer icon){
        LayoutInflater inflater = LayoutInflater.from(this);
        DialogDesignBinding dialogDesignBinding = DialogDesignBinding.inflate(inflater);

        dialogDesignBinding.textViewMessage.setText(message);
        dialogDesignBinding.imageViewInfo.setBackgroundResource(icon);

      AlertDialog alertDialog = new AlertDialog.Builder(this)
              .setView(dialogDesignBinding.getRoot())
              .create();

      alertDialog.show();
    }

    public void showConfirmDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        ConfirmationDialogDesignBinding dialogDesignBinding = ConfirmationDialogDesignBinding.inflate(inflater);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(dialogDesignBinding.getRoot())
                .create();

        alertDialog.show();

        dialogDesignBinding.imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                showDialog(getString(R.string.submission_not_successful), R.drawable.ic_baseline_report_problem_24);
            }
        });

        dialogDesignBinding.buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.progressBar3.setVisibility(View.VISIBLE);
                                executeSubmitProject(mBinding.editTextFirstName.getText().toString(),
                        mBinding.editTextLastName.getText().toString(),
                        mBinding.editTextEmail.getText().toString(),
                        mBinding.editTextLink.getText().toString());

                alertDialog.dismiss();

            }
        });
    }

    public void clearFields(){
        mBinding.editTextLink.setText("");
        mBinding.editTextEmail.setText("");
        mBinding.editTextFirstName.setText("");
        mBinding.editTextLastName.setText("");
    }

    public Boolean isFieldEmpty(){
        boolean checkField = !mBinding.editTextLastName.getText().toString().isEmpty() &&
                            !mBinding.editTextFirstName.getText().toString().isEmpty() &&
                            !mBinding.editTextEmail.getText().toString().isEmpty() &&
                            !mBinding.editTextLink.getText().toString().isEmpty();

        return checkField;
    }

}