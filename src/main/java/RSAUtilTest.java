import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class RSAUtilTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("your content(can't use space)");
        String content = scanner.next();
        System.out.println("原始明文:"+content);
        try {
            KeyPair keyPair = RSAUtil.getKeyPair();
            String privateKeyStr = RSAUtil.getPrivateKey(keyPair);
            String publicKeyStr = RSAUtil.getPublicKey(keyPair);
            //System.out.println("私鑰：" + privateKeyStr + "\n"+ "公鑰：" + publicKeyStr);
            PrivateKey privateKey = RSAUtil.string2Privatekey(privateKeyStr);
            PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
            // 公鑰加密/私鑰解密
            byte[] publicEncryBytes = RSAUtil.publicEncrytype(content.getBytes(), publicKey);
            System.out.println("密文：" + Base64.getEncoder().encodeToString(publicEncryBytes));
            byte[] privateDecryBytes = RSAUtil.privateDecrypt(publicEncryBytes, privateKey);
            System.out.println("解密明文：" + new String(privateDecryBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}