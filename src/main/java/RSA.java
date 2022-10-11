import java.math.BigInteger;
import java.util.Scanner;

public class RSA {
        private BigInteger e = null;
        private BigInteger d = null;
    private BigInteger n;

    public RSA() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter number");
        String s= scanner.next();
        int p = Integer.parseInt(s);
        Scanner scann = new Scanner(System.in);
        System.out.println("enter number");
        String st= scanner.next();
        int q = Integer.parseInt(st);
        int n = p*q; // n = p * q;   totient =(p-1)*(q-1)
        int t= (p-1)*(q-1);
        BigInteger totient = BigInteger.valueOf(t);
        e = totient.divide(BigInteger.valueOf(4)).nextProbablePrime();
        //選擇公鑰
        BigInteger y = egcd(totient, e)[1];
        d = y.mod(totient); //產生私鑰
        }
    // 擴充套件的Euclid演算法，目的：算出e-1 mod n
    public static BigInteger[] egcd(BigInteger d1, BigInteger d2) {
        BigInteger[] ret = new BigInteger[3];
        BigInteger u = BigInteger.valueOf(1), u1 = BigInteger.valueOf(0);
        BigInteger v = BigInteger.valueOf(0), v1 = BigInteger.valueOf(1);
        if (d2.compareTo(d1) > 0) {
            BigInteger tem = d1;
            d1 = d2;
            d2 = tem;
        }
        while (d2.compareTo(BigInteger.valueOf(0)) != 0) {
            BigInteger tq = d1.divide(d2); // tq = d1 / d2
            BigInteger tu = u;
            u = u1;
            u1 = tu.subtract(tq.multiply(u1)); // u1 =tu - tq * u1
            BigInteger tv = v;
            v = v1;
            v1 = tv.subtract(tq.multiply(v1)); // v1 = tv - tq * v1
            BigInteger td1 = d1;
            d1 = d2;
            d2 = td1.subtract(tq.multiply(d2)); // d2 = td1 - tq * d2
            ret[0] = u;
            ret[1] = v;
            ret[2] = d1;
        }
        return ret;
    }
    // 加密
    public BigInteger encode(BigInteger d) {
        return d.modPow(this.e, this.n);
    }
    // 解密
    public BigInteger decode(BigInteger c) {
        return c.modPow(this.d, this.n);
    }
}
