package com.horizon.repository.auth;

import java.io.IOException;
import java.util.Map;

public class AuthServiceImpl implements AuthService{
    @Override
    public String generateAuthUrl(String loginType) {
        return "";
    }

    @Override
    public Map<String, Object> authenticateAndFetchProfile(String code, String loginType) throws IOException {
        return Map.of();
    }
}
