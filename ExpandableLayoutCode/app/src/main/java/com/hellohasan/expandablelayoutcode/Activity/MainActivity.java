package com.hellohasan.expandablelayoutcode.Activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hellohasan.expandablelayoutcode.Adapter.ArticleReadingAdapter;
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
    private RecyclerView recyclerView;

    private ArticleReadingAdapter articleReadingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewArticleReading);

        if(isNetworkAvailable())
            callToServer();
        else{
            progressBar.setVisibility(View.GONE);
            textView.setText("No internet Connection available!");
        }


    }

    private void callToServer() {

        Call<List<ArticleModel>> call = apiInterface.getArticles();
        call.enqueue(new Callback<List<ArticleModel>>() {
            @Override
            public void onResponse(Call<List<ArticleModel>> call, Response<List<ArticleModel>> response) {
                progressBar.setVisibility(View.GONE);
                List<ArticleModel> articleModelList = response.body();

                //Show Article List
                try {
                    if(articleModelList.size()>0){
                        articleReadingAdapter = new ArticleReadingAdapter(getApplicationContext(), articleModelList, recyclerView);
                        recyclerView.setAdapter(articleReadingAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false));
                    }
                    else
                        textView.setText("Article List is Empty!");
                }
                catch (Exception e){
                    textView.setText(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<ArticleModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                textView.setText(t.getMessage());
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
