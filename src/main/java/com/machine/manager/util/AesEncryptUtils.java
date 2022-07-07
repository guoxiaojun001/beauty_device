package com.machine.manager.util;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;


/**
 * 前后端数据传输加密工具类
 *
 */
public class AesEncryptUtils {
    //可配置到Constant中，并读取配置文件注入
//    public static final String KEY = "abcdef0123456789";
    public static final String KEY = "jkl;POIU1234%%==";

    //参数分别代表 算法名称/加密模式/数据填充方式
    public static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     * @param content 加密的字符串
     * @param encryptKey key值
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        byte[] b = cipher.doFinal(content.getBytes("utf-8"));
        return Base64.encodeBase64String(b);
    }

    /**
     * 解密
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String encrypt(String content) throws Exception {
        return encrypt(content, KEY);
    }

    public static String decrypt(String encryptStr) throws Exception {
        return decrypt(encryptStr, KEY);
    }


    public static void main(String[] args) throws Exception {
        //测试
        String cKey = "jkl;POIU1234++==";
        // 需要加密的字串
        String content = "atom";
        content = content + "#####" + System.currentTimeMillis();
        System.out.println("加密前：" + content);

        String encrypt = encrypt(content ,cKey);
        System.out.println("加密后：" + encrypt);

        String decrypt = decrypt(encrypt,cKey );
        System.out.println("解密后 = " + decrypt);

        String[] arr = decrypt.split("#####");

        System.out.println("解密后：arr = " + arr[1]);
        long aaa = Long.parseLong(arr[1]);
        System.out.println("aaa = " + aaa);
        Thread.sleep(1000);
        long xxx = System.currentTimeMillis();
        System.out.println("xxx = " + xxx);

        System.out.println("diff = " + (xxx-aaa));
    }
}