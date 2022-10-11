import java.io.*;

import java.util.*;

public class PostfixEvaluation {

    public static void main(String[] args) throws Exception {
        System.out.println("請輸入後序式!");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        Stack< Integer> vs = new Stack< >();
        Stack< String> is = new Stack< >();
        Stack< String> ps = new Stack< >();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') { //3
                int v2 = vs.pop();
                int v1 = vs.pop();
                int val = operation(v1, v2, ch);
                vs.push(val);

                String iv2 = is.pop();
                String iv1 = is.pop();
                String ival = "(" + iv1 + ch + iv2 + ")";
                is.push(ival);

                String pv2 = ps.pop();
                String pv1 = ps.pop();
                String pval = ch + pv1 + pv2;
                ps.push(pval);
            } else {
                vs.push(ch - '0'); //6
                is.push(ch + "");
                ps.push(ch + "");
            }
        }
        System.out.println("答案:"+"\t"+vs.pop());
        System.out.println("中序式:"+"\t"+is.pop());
        System.out.println("前序式:"+"\t"+ps.pop());
    }
    public static int operation (int v1, int v2, char op) {


        if (op == '+') {
            return v1 + v2;
        } else if (op == '-') {
            return v1 - v2;
        } else if (op == '*') {
            return v1 * v2;
        } else {
            return v1 / v2;
        }

    }
}