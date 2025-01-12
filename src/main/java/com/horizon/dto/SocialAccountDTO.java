package com.horizon.dto;

public abstract class SocialAccountDTO {

    protected String googleAccountId;

    public boolean isGoogleAccountIdValid() {
        return googleAccountId != null && !googleAccountId.isEmpty();
    }


    //check account is social login or not
    public boolean isSocialLogin() {
        return googleAccountId != null && !googleAccountId.isEmpty();
    }
}
