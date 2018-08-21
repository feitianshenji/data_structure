/**
 * Created by zejian
 * 斐波那契数列的实现
 */
public class Fibonacci {
    /**
     * 斐波那契数列的实现
     * 0,1,1,2,3,5,8,13,21......
     * @param day
     */
    static long fibonacci(int day) {

        if (day == 0) {//F(0)=0
            return 0;
        } else if (day==1 || day==2) {//F(1)=0
            return 1;
        } else {
            return fibonacci(day-1) + fibonacci(day-2);//F(n)=F(n-1)+F(n-2)
        }
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        System.out.println("第8天动物数量为："+ fibonacci(8));
    }
}
