package com.example.helloboot.articles;

/**
 * Implement pow(x, n).


     Example 1:
     Input: 2.00000, 10
     Output: 1024.00000

     Example 2:
     Input: 2.10000, 3
     Output: 9.26100
 */
public class Pow {

    public static double myPow(double x, int n) {
        long m = n > 0 ? n : -(long)n;
        double ans = 1.0;
        while (m != 0) {
            if ((m & 1) == 1)
                ans *= x;
            x *= x;
            m >>= 1;
        }
        return n >= 0 ? ans : 1 / ans;
    }


    public static void main(String[] args){
        double t = myPow(2.10000,3);
        System.out.println(t);
    }
}
