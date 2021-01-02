package com.api.streaming.util;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder encoder = Base64.getUrlEncoder();

    public static String generadorTokens(){
        byte[] bytesRandom = new byte[24];
        secureRandom.nextBytes(bytesRandom);
        return encoder.encodeToString(bytesRandom);
    }
}
