/**
 * Created by zejian
 * 链式队列的实现
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
 * 队列接口抽象数据类型
 */
interface Queue<T> {

    /**
     * 返回队列长度
     * @return
     */
    int size();
    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();
    /**
     * data 入队,添加成功返回true,否则返回false,可扩容
     * @param data
     * @return
     */
    boolean add(T data);
    /**
     * 返回队头元素,不执行删除操作,若队列为空,返回null
     * @return
     */
    T peek();
    /**
     * 出队,执行删除操作,返回队头元素,若队列为空,返回null
     * @return
     */
    T poll();
    /**
     * 清空队列
     */
    void clearQueue();
}

class LinkedQueue<T> implements Queue<T> {


     //指向队头和队尾的结点,front==null&&rear==null时,队列为空
    private Node<T> front, rear;
    private int size;

    public LinkedQueue() {
        //初始化队列
        this.front = this.rear = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return front == null && rear == null;
    }

    //data入队，添加成功返回true，否则返回false
    public boolean add(T data) {

        Node<T> q = new Node<>(data, null);
        if (this.front == null) {//空队列插入
            this.front = q;
        } else {//非空队列，尾部插入
            this.rear.next = q;
        }
        this.rear = q;
        size++;
        return true;
    }
    //返回队头元素，不执行删除操作，若队列为空，返回null
    public T peek() {
        return this.isEmpty() ? null : this.front.data;
    }
    //出队，执行删除操作，返回队头元素，若队列为空，返回null
    public T poll() {

        if (this.isEmpty()) {
            return null;
        }
        T x = this.front.data;
        this.front = this.front.next;
        if (this.front == null) {
            this.rear = null;
        }
        size--;
        return x;
    }
    //清空队列
    public void clearQueue() {
        this.front = this.rear = null;
        size = 0;
    }
}

public class LinkedQueueTest {
    public static void main(String[] args) {

        LinkedQueue<String> lq = new LinkedQueue<>();
        lq.add("A");
        lq.add("B");
        lq.add("C");
        int length = lq.size();
        for (int i=0; i<length; i++) {
            System.out.println("lq.poll->" + lq.poll());
        }
    }
}
