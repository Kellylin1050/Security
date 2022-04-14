import java.security.SecureRandom;
import java.util.Scanner;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

public class Des {
    public static void main(String args[]) {
//加密內容
        Scanner scanner = new Scanner(System.in);
        System.out.println("your content(can't use space)");
        String str = scanner.next();
//輸入密碼
        Scanner s = new Scanner(System.in);
        System.out.println("your password(multiples of 8)");
        String password = s.next();

        byte[] result = Des.encrypt(str.getBytes(),password);
        System.out.println("加密後:"+new String(result));

        try {
            byte[] decryResult = Des.decrypt(result, password);
            System.out.println("解密後:"+new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    /*
     * 加密
     * @param datasource byte[]
     * @param password String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource, String password) {
        try{
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            return cipher.doFinal(datasource);
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }
    /*
     * 解密
     * @param src byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        return cipher.doFinal(src);
    }
}

