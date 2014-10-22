package com.powermanager.android.powermanager.Tasks;

/**
 * @author zafrani (david@touchlab.co).
 */
public class LoginResponse{
    public LoginResponseData data;
    public class LoginResponseData{
        public String access_token;
        public String refresh_token;
        public String token_type;
        public String token_endpoint;


    }
}
