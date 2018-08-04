//P16二分法求实根

import java.util.Scanner;

package datastructure;

public class chap1DivideConquer {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double a3 = sc.nextDouble();
        double a2 = sc.nextDouble();
        double a1 = sc.nextDouble();
        double a0 = sc.nextDouble();
        double a = sc.nextDouble();
        double b = sc.nextDouble();

        while ((b-a)>=0.001) { //设置循环条件 阈值
            if (f(a3,a2,a1,a0,(a+b)/2) == 0) { //如果f((a+b)/2)正好为0，则(a+b)/2就是要求的根
                System.out.printf("%.2f", (a+b)/2);
                break;
            }

            if (f(a3,a2,a1,a0,a)*f(a3,a2,a1,a0,(a+b)/2) < 0) {  //根据题目  重新设置a、b的值
                b = (a+b)/2;
            }
            if (f(a3,a2,a1,a0,(a+b)/2)*f(a3,a2,a1,a0,b) < 0) {
                a = (a+b)/2;
            }
        }

        if (f(a3,a2,a1,a0,(a+b)/2) != 0) {
            System.out.printf("%.2f", (a+b)/2);
        }
    }
     public static double f(double a3, double a2, double a1, double a0, double x) { //f(x)对应的公式
        return a3*x*x*x+a2*x*x+a1*x+a0;
    }
}
