package datastructure.Tree.BinaryTree;

/**
 * Created by feitianshenji
 * 二叉树结点
 */

public class BinaryNode<T extends Comparable> {

    public T data;
    public BinaryNode<T> left;//左结点
    public BinaryNode<T> right;//右结点

    public BinaryNode(T data, BinaryNode left, BinaryNode right) {//构造函数
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T data) {//构造函数
        this(data, null, null);
    }

    /**
     * 判断是否为叶子结点
     * @return
     */
    public boolean isLeaf() {
        return this.left== null && this.right==null;
    }
}
