package com.example.apprest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface Bank {


  //  public-Merchant-Id: da84e6bf7dcb4b2d931d88b015de20f7
//api-uat.kushkipagos.com/transfer/v1/bankList
    @GET("/payouts/transfer/v1/bankList")
    Call<List<DataBank>> getListAll(@Header("Public-Merchant-Id") String valor);
}
