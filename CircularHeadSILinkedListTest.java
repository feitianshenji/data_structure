/**
 * Created by zejian
 * 循环单链表
 */
class Node<T> {
    public T data;//数据域
    public Node<T> next;//地址域

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}

/**
 * Created by zejian
 * 链表顶级接口
 */

interface ILinkedList<T> {
    /**
     * 判断链表是否为空
     * @return
     */
    boolean isEmpty();
    /**
     * 链表长度
     * @return
     */
    int length();
    /**
     * 获取元素
     * @param index
     * @return
     */
    T get(int index);
    /**
     * 设置某个结点的的值
     * @param index
     * @param data
     * @return
     */
    T set(int index, T data);
    /**
     * 根据index添加结点
     * @param index
     * @param data
     * @return
     */
    boolean add(int index, T data);
    /**
     * 添加结点
     * @param data
     * @return
     */
    boolean add(T data);
    /**
     * 根据index移除结点
     * @param index
     * @return
     */
    T remove(int index);
    /**
     * 根据data移除结点
     * @param data
     * @return
     */
    boolean removeAll(T data);
    /**
     * 清空链表
     */
    void clear();
    /**
     * 是否包含data结点
     * @param data
     * @return
     */
    boolean contains(T data);
    /**
     * 通过传入的链表构造新的链表
     * @param list
     */
    //void concat(CircularHeadSILinkedList<T> list);
    /**
     * 输出格式
     * @return
     */
    String toString();
}
/**
 * Created by zejian
 * 循环单链表
 */
class CircularHeadSILinkedList<T> implements ILinkedList<T> {

    protected Node<T> head;//不带数据头结点
    protected Node<T> tail;//指向尾部的指针

    public CircularHeadSILinkedList() {
        //初始化头结点和尾指针
        this.head = new Node<T>(null);
        this.head.next = head;
        this.tail = head;
    }

    public CircularHeadSILinkedList(T[] array) {

        this();
        if (array!=null && array.length>0) {
            this.head.next = new Node<>(array[0], head);
            tail = this.head.next;
            int i = 1;
            while (i < array.length) {
                tail.next = new Node<>(array[i++]);
                tail.next.next = head;
                tail = tail.next;
            }
        }
    }

    public boolean isEmpty() {
        return this.head.next == head;
    }

    public int length() {
        int length = 0;
        Node<T> p = this.head.next;
        while (p != head) {
            p = p.next;
            length++;
        }
        return length;
    }

    public T get(int index) {

        if (index >= 0) {
            int j = 0;
            Node<T> pre = this.head.next;
            while (pre!=head && j<index) {
                j++;
                pre = pre.next;
            }
            if (pre != head) {
                return pre.data;
            }
        }
        return null;
    }

    public T set(int index, T data) {

        if (data == null) {
            return null;
        }
        if (index >= 0) {
            int j = 0;
            Node<T> p = this.head.next;
            while (p!=head && j<index) {
                j++;
                p = p.next;
            }
            //如果不是头结点
            if (p!=head) {
                T old = p.data;
                p.data = data;
                return old;
            }
        }
        return null;
    }

    public boolean add(int index, T data) {
        int size = length();
        if (data==null || index<0 || index>=size) {
            return false;
        }
        int j = 0;
        Node<T> p = this.head;
        //寻找插入点的位置的前一个结点
        while (p.next!=head && j<index) {
            p = p.next;
            j++;
        }
        //创建新结点，如果index=3，那么插入的位置就是第4个位置
        Node<T> q = new Node<>(data, p.next);
        p.next = q;
        //更新尾部指向
        if (p == tail) {
            this.tail = q;
        }
        return true;
    }

    public boolean add(T data) {
        if (data == null) {
            return false;
        }
        Node<T> q = new Node<>(data, this.tail.next);
        this.tail.next = q;
        //更新尾部指向
        this.tail = q;
        return true;
    }

    public T remove(int index) {
        int size = length();
        if (index<0 || index>=size || isEmpty()) {
            return null;
        }
        int j = 0;
        Node<T> p = this.head.next;
        while (p!=head && j<index-1) {
            j++;
            p = p.next;
        }

        if (p != head) {
            T old = p.next.data;
            if (tail == p.next) {//最后一个结点
                tail = p;
            }
            p.next = p.next.next;
            return old;
        }
        return null;
    }

    public boolean removeAll(T data) {
        boolean isRemove = false;
        if (data == null) {
            return isRemove;
        }
        //用于记录要删除结点的前一个结点
        Node<T> front = this.head;
        //当前遍历的结点
        Node<T> pre = front.next;
        //查找所有数据相同的结点并删除
        while (pre != head) {
            if (data.equals(pre.data)) {
                //如果恰好是尾部结点，则更新rear的指向
                if (data.equals(tail.data)) {
                    this.tail = front;
                }
                //相等则删除pre并更改指针指向
                front.next = pre.next;
                pre = front.next;
                isRemove = true;
            } else {
                front = pre;
                pre = pre.next;
            }
        }
        return isRemove;
    }

    public void clear() {
        this.head.next = head;
        this.tail = head;
    }

    public boolean contains(T data) {
        if (data == null) {
            return false;
        }
        Node<T> p = this.head.next;
        while (p != head) {
            if (data.equals(p.data)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    public String toString()
    {
        String str="(";
        Node<T> p = this.head.next;
        while (p!=head)
        {
            str += p.data.toString();
            p = p.next;
            if (p!=head)
                str += ", ";
        }
        return str+")";
    }
}


public class CircularHeadSILinkedListTest {
    public static void main(String[] args) {

        String[] letters={"A","B","C","D","E","F"};
        CircularHeadSILinkedList<String> list=new CircularHeadSILinkedList<>(letters);

        System.out.println("list.get(3)->"+list.get(3));
        System.out.println("list:"+list.toString());

        System.out.println("list.add(4,Y)—>"+list.add(4,"Y"));
        System.out.println("list:"+list.toString());
        System.out.println("list.add(Z)—>"+list.add("Z"));
        System.out.println("list:"+list.toString());


        System.out.println("list.contains(Z)->"+list.contains("Z"));
        System.out.println("list.set(4,P)-->"+list.set(4,"P"));
        System.out.println("list:"+list.toString());

        System.out.println("list.removeAll(Z)->"+list.removeAll("Z"));
        System.out.println("list:"+list.toString());
        System.out.println("list.remove(4)-->"+list.remove(4));
        System.out.println("list:"+list.toString());
    }
}
