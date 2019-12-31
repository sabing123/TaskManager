package com.sabin.taskmanager.api;

import com.sabin.taskmanager.model.User;
import com.sabin.taskmanager.serverresponse.SignupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Userapi {
    @POST ("users/signup")
    Call<SignupResponse> resgisterUser(@Body User user);
}
