//p15 找3个整数的中数


package datastructure;

public class chap1 {
    public static void main(String[] args) {
        int middle;
        findshu ff = new findshu();
        middle = ff.find(5,2,9);
        System.out.println(middle);
    }
}

class findshu {

    public int find(int a, int b, int c) {
        int x;
        x = a;
        if (x >= b) {
            if (x >= c) {
                if (b >= c) {
                    x = b;
                } else {
                    x = c;
                }
            }
        } else {
            if (x <= c) {
                if (b >= c) {
                    x = c;
                } else {
                    x = b;
                }
            }
        }
        return x;
    }
}
