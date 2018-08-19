/**
 * Created by zejian
 * 栈的链式实现
 */

class Node<T> {
    public T data;
    public Node<T> next;

    public Node(){

    }

    public Node(T data){
        this.data=data;
    }

    public Node(T data,Node<T> next){
        this.data=data;
        this.next=next;
    }
}
/**
 * Created by zejian
 * 栈接口抽象数据类型
 */
interface Stack<T> {

    //栈是否为空
    boolean isEmpty();
    //data元素入栈
    void push(T data);
    //返回栈顶元素，未出栈
    T peek();
    //出栈
    T pop();
}

class LinkedStack<T> implements Stack<T> {

    private Node<T> top;
    private int size;

    public LinkedStack() {//构造函数
        this.top = new Node<>();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return top==null || top.data==null;
    }

    public void push(T data) {
//        if (data == null) {
//            throw new EmptyStackException("data cannot be null");
//        }
        if (this.top == null) {
            this.top = new Node<T>(data);
        }else if (this.top.data == null) {
            this.top.data = data;
        }else {
            Node<T> p = new Node<>(data, top);
            top = p;//更新栈顶
        }
        size++;
    }

    public T peek() {
//        if (isEmpty()) {
//            throw new EmptyStackException("Stack empty");
//        }
        return top.data;
    }

    public T pop() {
//        if(isEmpty()){
//            throw new EmptyStackException("Stack empty");
//        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }
}

public class LinkedStackTest {
    public static void main(String[] args) {
        LinkedStack<String> s1 = new LinkedStack<>();
        s1.push("A");
        s1.push("B");
        s1.push("C");
        int length = s1.size();
        for (int i=0; i<length; i++) {
            System.out.println("s1.pop->"+s1.pop());
        }
    }
}
