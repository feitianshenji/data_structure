package datastructure.Graph;
/**
 * Created by feitianshenji
 * 邻接矩阵无向图
 */
public class MatrixNDG {

    int size;//图顶点个数
    char[] vertexs;//图顶点名称
    int[][] matrix;//图关系矩阵

    public MatrixNDG(char[] vertexs,char[][] edges){
        size=vertexs.length;
        matrix=new int[size][size];//设定图关系矩阵大小
        this.vertexs=vertexs;

        for(char[] c:edges){//设置矩阵值
            int p1 = getPosition(c[0]);//根据顶点名称确定对应矩阵下标 第一次取A，第二次取A，第三次取A，第四次取B，第五次取C......
            int p2 = getPosition(c[1]);//第一次取C，第二次取D，第三次取F，第四次取C，第五次取D......

            matrix[p1][p2] = 1;//无向图，在两个对称位置存储
            matrix[p2][p1] = 1;
        }
    }

    //图的遍历输出
    public void print(){
        for(int[] i:matrix){//打印二维数组的方法
            for(int j:i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    //根据顶点名称获取对应的矩阵下标
    private int getPosition(char ch) {
        for(int i=0; i<vertexs.length; i++)
            if(vertexs[i]==ch)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G','H','I','J','K'};
        char[][] edges = new char[][]{
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'D', 'G'},
                {'I','J'},
                {'J','G'},};
        MatrixNDG pG;
        // 自定义"图"(输入矩阵队列)
        // 采用已有的"图"
        pG = new MatrixNDG(vexs, edges);
        pG.print();   // 打印图
    }
}
