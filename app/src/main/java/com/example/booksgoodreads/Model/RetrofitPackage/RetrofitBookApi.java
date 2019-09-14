package com.example.booksgoodreads.Model.RetrofitPackage;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitBookApi {



   @GET("volumes")
    Call<BooksRespone> getBaseJsonObject(@Query("q") String searchItem);
}
