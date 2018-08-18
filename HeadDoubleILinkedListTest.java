/**
 * Created by zejian
 * 双链表
 */
class DNode<T> {

    public T data;
    public DNode<T> prev, next;//前继指针和后继指针

    public DNode(T data, DNode<T> prev, DNode<T> next) {
        this.data =data;
        this.prev = prev;
        this.next = next;
    }

    public DNode(T data) {
        this(data, null, null);
    }
    public DNode() {
        this(null, null, null);
    }
    public String toString() {
        return this.data.toString();
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
 * 双链表的实现,带头结点(不带数据)的双链表,为了更高的效率该类包含指向尾部的指针tail
 */
class HeadDoubleILinkedList<T> implements ILinkedList<T> {

    protected DNode<T> head;//不带数据的头结点
    protected DNode<T> tail;//指向尾部的指针

    public HeadDoubleILinkedList() {
        this.head = this.tail = new DNode<>(); //初始化头结点
    }

    //传入一个数组,转换成链表
    public HeadDoubleILinkedList(T[] array) {
        this();
        if (array!=null && array.length>0) {
            this.head.next = new DNode<T>(array[0]);
            tail = this.head.next;
            tail.prev = this.head;
            int i = 1;
            while (i < array.length) {
                tail.next = new DNode<T>(array[i++]);
                tail.next.prev = tail;
                tail = tail.next;
            }
        }
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public int length() {
        int length = 0;
        DNode<T> pre = head.next;
        while (pre != null) {
            pre = pre.next;
            length++;
        }
        return length;
    }

    public T get(int index) {
        if (index >= 0) {
            int j=0;
            DNode<T> pre = this.head.next;
            while (pre!=null && j<index) {
                j++;
                pre = pre.next;
            }
            if (pre != null) {
                return pre.data;
            }
        }
        return null;
    }

    public T set(int index, T data) {
        T old = null;
        if (index>0 && data!=null) {
            int j=0;
            DNode<T> pre = this.head.next;
            //查找需要替换的位置
            while (pre!=null && j<index) {
                j++;
                pre = pre.next;
            }
            if (pre != null) {
                old = pre.data;
                //替换数据
                pre.data = data;
            }
        }
        return old;
    }

    //插入结点
    public boolean add(int index, T data) {

        if (index<0 || data==null) {
            throw new NullPointerException("index < 0 || data == null");
        }
        int j=0;
        DNode<T> front = this.head;
        //查找要插入位置的前一个结点
        while (front.next!=null && j<index) {
            j++;
            front = front.next;
        }
        //创建需要插入的结点,并让其前继指针指向front,后继指针指向front.next
        DNode<T> q = new DNode<T>(data, front, front.next);
        //空双链表插入,需要确保front.next不为空
        if(front.next != null) {
            //更改front.next的前继指针
            front.next.prev = q;
        }
        //更改front的后继指针
        front.next = q;
        //在尾部插入时需要注意更新tail指向
        if (front == this.tail) {
            this.tail = q;
        }
        return true;
    }

    //尾部添加
    public boolean add(T data) {
        if (data == null) {
            return false;
        }
        //创建新结点，并把其前继指针指向tail
        DNode<T> q = new DNode<>(data, tail, null);
        tail.next = q;
        //更新尾部结点
        this.tail = q;
        return true;
    }
    //根据下标删除结点:1.头删除 2.中间删除 3.尾部删除,更新tail指向.下标起始值为0
   public T remove(int index) {

        int size = length();
        T temp = null;

        if (index<0 || index>=size || isEmpty()) {
            return temp;
        }

        DNode<T> p = this.head;
        int j=0;
        //头删除/尾删除/中间删除,查找需要删除的结点(要删除的当前结点因此i<=index)
       while (p!=null && j<=index) {
           p = p.next;
           j++;
       }
       //当链表只有一个结点时，无需此步
       if(p.next != null){
           p.next.prev=p.prev;
       }
       p.prev.next = p.next;
       //如果是尾结点
       if (p == this.tail) {
           this.tail = p.prev;//更新未结点的指向
       }
       temp = p.data;

       return temp;
   }
   //根据data删除结点,无需像单向链表那样去存储要删除结点的前一个结点
    // 1.头删除 2.中间删除.3.尾部删除,更新tail指向
    public boolean removeAll(T data) {

        boolean isRemove = false;
        if (data==null && isEmpty()) {
            return isRemove;
        }
        //注意这里的起点,如果起点为this.head,那么情况区别如同前面的根据index的删除实现
        DNode<T> p = this.head.next;

        //头删除/尾删除/中间删除(size>1),查找所有需要删除的结点
        while (p != null) {

            if (data.equals(p.data)) {
                if (p != this.tail) {//在中间删除
                    p.prev.next = p.next;
                    p.next.prev = p.prev;
                } else {//在第一个结点或尾部删除
                    tail = p.prev;
                    p.prev.next = null;
                }
                isRemove = true;
                p = p.next;
            } else {
                p = p.next;
            }
        }
        return isRemove;
    }
    //清空链表
    public void clear() {
        this.head.next = null;
        this.tail = this.head;
    }

    public boolean contains(T data) {
        if (data == null) {
            return false;
        }
        DNode<T> p = this.head.next;
        while (p != null) {
            if (data.equals(p.data)) {
                return true;
            } else {
                p = p.next;
            }
        }
        return false;
    }
    public String toString() {
        String str="(";
        DNode<T> pre = this.head.next;
        while (pre!=null)
        {
            str += pre.data;
            pre = pre.next;
            if (pre!=null)
                str += ", ";
        }
        return str+")";
    }
}

public class HeadDoubleILinkedListTest {
    public static void main(String[] args) {

        String[] letters={"A","B","C","D","Z","E","F"};
//        String[] letters={"A"};
        HeadDoubleILinkedList<String> list=new HeadDoubleILinkedList<>(letters);

        System.out.println("list.get(3)->"+list.get(3));
        System.out.println("list:"+list.toString());

        System.out.println("list.add(4,Y)—>"+list.add(4,"Y"));
        System.out.println("list:"+list.toString());
        System.out.println("list.add(Z)—>"+list.add("Z"));
        System.out.println("list:"+list.toString());


        System.out.println("list.contains(Z)->"+list.contains("Z"));
        System.out.println("list.set(4,P)-->"+list.set(4,"P"));
        System.out.println("list:"+list.toString());


        System.out.println("list.remove(6)-->"+list.remove(6));
        System.out.println("list.remove(Z)->"+list.removeAll("Z"));
        System.out.println("list:"+list.toString());
    }
}
