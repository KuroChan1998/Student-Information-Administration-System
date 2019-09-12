package others;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.*;
import org.junit.Test;

import java.security.Key;

public class EncryptTest {

    //加密内容
    private String pass = "encryptString";

    //盐
    private String salt = "salt";

    //加密次数
    private int hashIterations = 10;


    /**
     * base64
     */
    @Test
    public void test1() {
        String encodeToString = Base64.encodeToString(pass.getBytes());
        System.out.println(encodeToString);

        String decodeToString = new String(Base64.decodeToString(pass));
        System.out.println(decodeToString);
    }

    /**
     * md5加密
     */
    @Test
    public void test2() {
        //MD5普通加密
        String encodeToString = new Md5Hash(pass).toString();
        System.out.println(encodeToString);

        //md5加密转base64位编码或者16进制编码
        String md5Base64 = new Md5Hash(pass).toBase64();
        String md5Hex = new Md5Hash(pass).toHex();
        System.out.println(md5Base64);
        System.out.println(md5Hex);

        //md5加密，加密内容source,带盐加密salt，还可以指定加密次数：hashIterations
        md5Base64 = new Md5Hash("encryptString", "123", 5).toBase64();
        System.out.println(md5Base64);
        System.out.println(new Md5Hash("encryptString", "123", 5).toBase64());

    }

    /**
     * sha加密
     * SHA1,SHA256,SHA512
     */
    @Test
    public void test3() {
        String sha1hash = new Sha1Hash(pass, salt, hashIterations).toBase64();
        String sha256hash = new Sha256Hash(pass, salt, hashIterations).toBase64();
        String sha512hash = new Sha512Hash(pass, salt, hashIterations).toBase64();

        System.out.println(sha1hash);
        System.out.println(sha256hash);
        System.out.println(sha512hash);
    }

    /**
     * 通用加密：SimpleHash,将算法名称添加到方法即可
     */
    @Test
    public void testSimleHash() {
        // algorithmName 算法名称
        String algorithmName="md5";//sha1,sha-256,sha-512。。。，下面的第一个参数
        String encryptStr = new SimpleHash("md5", pass, salt, hashIterations).toBase64();
        String sha256 = new SimpleHash("sha-256", pass, salt, hashIterations).toBase64();
        String sha512= new SimpleHash("sha-512", pass, salt, hashIterations).toBase64();

        System.out.println(encryptStr);
        System.out.println(sha256);
        System.out.println(sha512);
    }

    //hex十六进制编码
    @Test
    public void testHex() {
        String encodeToString = Hex.encodeToString(pass.getBytes());
        String decodeToString = new String(Hex.decode(encodeToString));

        System.out.println("加密："+encodeToString);
        System.out.println("解密："+decodeToString);
    }

    // AES算法实现
    @Test
    public void passWord7() {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128); // 设置key长度
        // 生成key
        Key key = aesCipherService.generateNewKey();
        String text = "hello";
        // 加密
        String encrptText = aesCipherService
                .encrypt(text.getBytes(), key.getEncoded()).toHex();
        // 解密
        String text2 = new String(aesCipherService
                .decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        System.out.println("text2=" + text2);
        System.out.println("encrptText=" + encrptText);
    }

}
