package com.hellohasan.expandablelayoutcode.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hellohasan.expandablelayoutcode.Interface.ApiInterface;
import com.hellohasan.expandablelayoutcode.Model.ArticleModel;
import com.hellohasan.expandablelayoutcode.R;
import com.hellohasan.expandablelayoutcode.Retrofit.RetrofitApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        Call<List<ArticleModel>> call = apiInterface.getArticles();

        call.enqueue(new Callback<List<ArticleModel>>() {
            @Override
            public void onResponse(Call<List<ArticleModel>> call, Response<List<ArticleModel>> response) {
                progressBar.setVisibility(View.GONE);
                List<ArticleModel> articleModelList = response.body();
            }

            @Override
            public void onFailure(Call<List<ArticleModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                textView.setText("Failed! Check your connection");
            }
        });
    }
}
