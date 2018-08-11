package datastructure;

/**
 * feitianshenji
 * 循环队列的实现
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

class SeqQueue<T> implements Queue<T> {

    private static final int DEFAULT_SIZE = 10;
    public T elementData[];
    public int front, rear;
    private int size;

    public SeqQueue() {
        elementData = (T[]) new Object[DEFAULT_SIZE];
        front = rear = 0;
    }

    public SeqQueue(int capacity) {
        elementData = (T[]) new Object[capacity];
        front = rear = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean add(T data) {
        //判断是否满队
        if (this.front == (this.rear+1)%this.elementData.length) {
            ensureCapacity(elementData.length*2 + 1);
        }
        //添加data
        elementData[this.rear] = data;
        size++;
        //更新rear指向下一个空元素的位置
        this.rear = (this.rear + 1)%this.elementData.length;
        return true;
    }

    public T peek() {
        return elementData[this.front];// 返回队头元素,不执行删除操作,若队列为空,返回null
    }

    public T poll() {//出队,执行删除操作,返回队头元素,若队列为空,返回null
        T temp = elementData[this.front];
        this.front = (this.front + 1)%this.elementData.length;
        size--;
        return temp;
    }

    public void clearQueue() {
        for (int i=this.front; i!=this.rear; i=(i+1)%elementData.length) {
            elementData[i] = null;
        }
        //复位
        this.front = this.rear = 0;
        size = 0;
    }

    public void ensureCapacity(int capacity) {
        //如果需要拓展的容量比现在数组的容量还小，则无需扩容
        if (capacity < size) {
            return ;
        }

        T old[] = elementData;
        elementData = (T[]) new Object[capacity];

        int j = 0;
        //复制元素
        for (int i=this.front; i!=this.rear; i=(i+1)%old.length) {
            elementData[j++] = old[i];
        }
        //恢复front、rear指向
        this.front = 0;
        this.rear = j;
    }
}

public class SeqQueueTest {
    public static void main(String[] args) {
        SeqQueue queuetest = new SeqQueue();
        queuetest.add(1);
        queuetest.add(2);
        queuetest.add(3);
        queuetest.add(4);
        queuetest.add(5);

        System.out.println("输出循环队列元素");
        for (int i=queuetest.front; i!=queuetest.rear; i=(i+1)%queuetest.elementData.length) {
            System.out.print(queuetest.elementData[i]+" ");
        }
        System.out.println();
        System.out.println("循环队列长度为："+queuetest.size());
        System.out.println("返回对头元素: "+queuetest.peek());
        System.out.println("输出退出元素： "+queuetest.poll());
        System.out.println("输出退出元素： "+queuetest.poll());
        System.out.println("循环队列长度为："+queuetest.size());
        System.out.println("再次输出循环队列元素");
        for (int i=queuetest.front; i!=queuetest.rear; i=(i+1)%queuetest.elementData.length) {
            System.out.print(queuetest.elementData[i]+" ");
        }
        System.out.println();
        queuetest.clearQueue();
        System.out.println("清空循环队列");
        System.out.println("打印");
        for (int i=queuetest.front; i!=queuetest.rear; i=(i+1)%queuetest.elementData.length) {
            System.out.print(queuetest.elementData[i]+" ");
        }
    }
}
