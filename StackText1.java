/**
 * 围绕栈的4个元素来实现栈，。
 *  2个状态：是否栈空；是否栈满。
 *  2个操作：压栈push;进栈pop。
 * @author feitianshenji
 *
 */

class SortStack {

    public Object[] data; //数组表示的栈内元素
    public int maxSize; //栈空间大小
    public int top; //栈顶指针（指向栈顶元素）

    /**
     * 初始化栈的长度
     * @param initialSize
     */
    public SortStack(int initialSize) { //构造函数
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            this.top = -1;
            this.data = new Object[initialSize];
        }else {
            System.out.println("栈初始化失败！");
        }
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty() {
        return top == -1 ? true : false;
    }

    /**
     * 判断是否栈满
     * @return
     */
    public boolean isFull() {
        return top == (maxSize-1) ? true : false;
    }

    /**
     * 入栈操作
     * 先判断是否栈满
     * @param value
     */
    public void push(Object value){
        if (!isFull()) {
            System.out.print(value + "入栈   ");
            data[++top] = value;
        } else {
            System.out.println("满栈，无法进行入栈操作！");
        }
    }

    /**
     * 出栈操作
     * 先判断是否为空栈
     * @return
     */
    public Object pop() {
        Object num = null;
        if (!isEmpty()) {
            num = data[top--];
            return num;
        }else {
            return "空栈，无法进行出栈操作！";
        }
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public Object getPeek() {
        if (!isEmpty()) {
            return data[top];
        } else {
            return "栈顶指针为空，无栈顶元素！";
        }
    }

    /**
     * 打印栈内元素
     */
    public void displayStack() {
        if (!isEmpty()) {
            for (int i=top; i>= 0; i--) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        } else {
            System.out.println("栈内元素为空！");
        }
    }

    /**
     * 获取栈顶指针为n的栈内元素
     * @param n
     * @return
     */
    public Object getPeekN(int n) {
        if (n<top) {
            return data[n];
        } else {
            return "error";
        }
    }
}

public class StackText1 {
    public static void main(String[] args) {

        SortStack stack = new SortStack(3);
        System.out.println("栈顶元素为： " + stack.getPeek());
        System.out.println("栈是否为空： " + stack.isEmpty());
        System.out.println("是否为满栈： " + stack.isFull());

        System.out.println("++++++++++");
        stack.push(1);
        stack.push("A");
        stack.push(5);
        stack.push("hello");

        System.out.println("栈是否为空： " + stack.isEmpty());
        System.out.println("是否为满栈： " + stack.isFull());
        System.out.println("栈顶指针为： " + stack.top);
        System.out.println("===========");

        System.out.println("出栈后的栈顶元素为： " + stack.pop());
        System.out.println("出栈后的栈顶元素为： " + stack.pop());
        System.out.println("++++++++++");

        System.out.println("栈顶元素为： " + stack.getPeek());
        System.out.println("栈顶指针为2的元素为： " + stack.getPeekN(2));
        System.out.println("栈顶指针为9的元素为： " + stack.getPeekN(9));
        System.out.println("栈是否为空： " + stack.isEmpty());
        System.out.println("是否为满栈： " + stack.isFull());

        System.out.println("**********");
        System.out.print("栈内元素为： ");
        stack.displayStack();
        System.out.println("栈顶指针为： " + stack.top);
    }
}
