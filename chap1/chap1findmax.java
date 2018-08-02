//P16 减半递归找最大值

package datastructure;

public class chap1findmax {
    public static void main(String[] args) {
        findmaxshu themax = new findmaxshu();
        int a[] = {15,6,4,-1,5,21,7,3,78,-51,40,36,43,49,63,27};
        System.out.println(themax.max(a,1, 16));
    }
}

class findmaxshu {
    int max(int a[], int m, int n) { //m,n为数组上、下标

        int x,y,d;
        if (m == n) {
            return a[m-1];
        } else {
           x = max(a, m, (m+n)/2);
           y = max(a, (m+n)/2+1, n);

            if (x > y) {
                d = x;
            } else {
                d = y;
            }
            return d;
        }
    }
}
