/**
 * 建立一个线性表，依次输入元素0,1,2...9;然后在第4个位置插入9 ，然后删除数据元素7。最后依次显示当前线性表元素。
 * 采用顺序表实现。
 * @author feitianshenji
 *
 */

 class SeqList {
    private int maxSize;
    private int size;
    private Object[] arrayList;

    public SeqList(int sz) { //构造函数
        maxSize = sz;
        size = 0;
        arrayList = new Object[sz];
    }

    public void insert(int i, Object obj) throws Exception { //顺序表的插入

        if (size == maxSize) {
            throw new Exception("顺序表已满，无法插入！");
        }
        if (i < 0 || i > size) {
            throw new Exception("插入位置不存在！");
        }

        for (int j=size; j>i; j--) { //后移
            arrayList[j] = arrayList[j-1];
        }
        arrayList[i] = obj;
        size++;
    }

    public Object delete(int i) throws Exception { //顺序表的删除

        if (size == 0) {
            throw new Exception("顺序表已为空");
        }
        if (i < 0 || i > size) {
            throw new Exception("删除位置不存在！");
        }
        Object obj = arrayList[i];

        for (int j=i; j<size; j++) { //前移
            arrayList[j] = arrayList[j+1];
        }
        size--;
        return obj;
    }

    public Object getData(int i) throws Exception{

        if (i < 0 || i > size) {
            throw new Exception("该位置不存在！");
        }
        return arrayList[i];
    }

    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
}

public class SeqListText1 {
    public static void main(String[] args) throws Exception{

        SeqList seqList = new SeqList(100);
        System.out.println("原始线性表：");

        for (int i=0; i<10; i++) {
            seqList.insert(i, new Integer(i));
            System.out.print(seqList.getData(i) + " ");
        }

        System.out.println("插入一个元素后：");
        seqList.insert(4, new Integer(9));
        for (int i=0; i<seqList.getSize(); i++) {
            System.out.print(seqList.getData(i) + " ");
        }

        System.out.println("删除一个元素后：");
        seqList.delete(7);
        for (int i=0; i<seqList.getSize(); i++) {
            System.out.print(seqList.getData(i) + " ");
        }
    }
}
