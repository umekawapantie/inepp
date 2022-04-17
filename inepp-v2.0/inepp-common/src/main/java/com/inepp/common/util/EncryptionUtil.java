package com.inepp.common.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 字符串加密功能
 */
public class EncryptionUtil {

    private static volatile EncryptionUtil instance;
    private final String ALGORITHM = "MD5";
    private MessageDigest md;
    private  final char[] HEX_CHARS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private EncryptionUtil() {
        try {
            md = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static EncryptionUtil getInstance(){
        if(instance==null){
            synchronized (EncryptionUtil.class){
                if(instance==null){
                    instance = new EncryptionUtil();
                }
            }
        }
        return instance;
    }

    public String encryt(String oldString){

        md.update(oldString.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest();

        char[] chars = new char[32];
        for (int i = 0; i < chars.length; i = i + 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
            chars[i + 1] = HEX_CHARS[b & 0xf];
        }

        return new String(chars);
    }

    public static void main(String[] args) {
      String s =  EncryptionUtil.getInstance().encryt("123");
      String s2 = DigestUtils.md5DigestAsHex("abc".getBytes(StandardCharsets.UTF_8));
    }
}
