package com.nabase1.gadsleaderboard.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nabase1.gadsleaderboard.R;
import com.nabase1.gadsleaderboard.adapters.LeanerBoardAdapter;
import com.nabase1.gadsleaderboard.modals.Learners;
import com.nabase1.gadsleaderboard.utils.ApiUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearningLeaders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningLeaders extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private View items;

    public LearningLeaders() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearningLeaders.
     */
    // TODO: Rename and change types and number of parameters
    public static LearningLeaders newInstance(String param1, String param2) {
        LearningLeaders fragment = new LearningLeaders();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        try{
            URL url = ApiUtils.buildUrl("hours");
            new QueryTask().execute(url);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        items =  inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        mProgressBar = items.findViewById(R.id.progressBar);
        mRecyclerView = items.findViewById(R.id.recyclerView);

        initialize(mRecyclerView);

        return items;

    }

    public void initialize(RecyclerView recyclerView){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public class QueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String searchResult = null;
            try {
                searchResult = ApiUtils.getJson(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return searchResult;
        }

        @Override
        protected void onPostExecute(String s) {

            if(s == null){
                mRecyclerView.setVisibility(View.INVISIBLE);
               // mProgressBar.setVisibility(View.VISIBLE);
            }else {
                mRecyclerView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);

            }

            ArrayList<Learners> learners = ApiUtils.getLeaderListFromJson(s);

            LeanerBoardAdapter leanerBoardAdapter = new LeanerBoardAdapter(learners);
            mRecyclerView.setAdapter(leanerBoardAdapter);


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // mProgressBar.setVisibility(View.VISIBLE);
        }
    }
}