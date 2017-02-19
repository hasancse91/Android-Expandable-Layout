package com.hellohasan.androidexpandablelayout.Interface;

import com.hellohasan.androidexpandablelayout.Model.ArticleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("data.json")
    Call<List<ArticleModel>> getArticles();
}
