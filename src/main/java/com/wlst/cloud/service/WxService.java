package com.wlst.cloud.service;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.util.Arrays;

public class WxService {

    private static final String TOKEN = "huang";

    public static boolean check(String timestamp, String nonce, String signature) {
        String[] strs = new String[] {TOKEN,timestamp,nonce};
        Arrays.sort(strs);
        String str = strs[0]+strs[1]+strs[2];
        String mysig = sha1(str);
        System.out.println(mysig);
        System.out.println(signature);

        return mysig.equalsIgnoreCase(signature);

    }

    private static String sha1(String src) {
        if (src == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");

            byte[] digest = messageDigest.digest(src.getBytes());
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                    'e', 'f'};
            StringBuilder sb = new StringBuilder();
            for(byte b:digest) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
