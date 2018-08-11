import java.util.Stack;

public class maze {
    static int[][] dedale={    //用二维数组表示地图
            {0,0,0,0,0},
            {0,1,1,0,0},
            {0,1,1,0,0},
            {0,1,1,1,0},
            {0,0,0,0,0}};

    public static void main(String[] args) {
        Stack<Position> stack=new Stack<Position>();
        Position start=new Position(1, 1);
        Position current=start;  //设置当前位置为起始位置

        do{
            if(dedale[current.x][current.y]==1&&validate(current)){  //当前位置可通（注：当前位置是标记过的位置时,路线肯定不对）
                dedale[current.x][current.y]=9;  //已经过的地方做个标记
                stack.push(current);
                if(current.x==3&&current.y==3) break;  //到达终点

                current=nextPosition(current);  //将当前指向下一个位置
            }else{
                if(stack.size()!=0){
                    current=stack.peek();  //取出当前栈顶元素
                    if(current.di>=4){    //此时此位置四个方向的路均不通时
                        dedale[current.x][current.y]=4;  //留下不能通过的标记
                        stack.pop();      //pop出栈顶元素
                        current=stack.peek();    //获取此时栈顶元素
                        continue;
                    }
                    if(current.di<4) current=nextPosition(current);
                }
            }  //end else
        }while(stack.size()>0);

        printPath(dedale);
    }

    public static boolean validate(Position p){
        if(dedale[p.x][p.y]==9||dedale[p.x][p.y]==4){   //排除已走过的部分
            return false;
        }
        return true;
    }

    public static Position nextPosition(Position p){  //获取下一个Position
        if(p.di==1){         //东
            p.di++;
            Position pos=new Position(p.x,p.y+1);
            if(!validate(pos)) return nextPosition(p);
            return pos;
        }else if(p.di==2){   //南
            p.di++;
            Position pos=new Position(p.x+1,p.y);
            if(!validate(pos)) return nextPosition(p);
            return pos;
        }else if(p.di==3){   //西
            p.di++;
            Position pos=new Position(p.x,p.y-1);
            if(!validate(pos)) return nextPosition(p);
            return pos;
        }else if(p.di==4){   //北
            p.di++;
            Position pos=new Position(p.x-1,p.y);
            return pos;
        }else return null;
    }

    public static void printPath(int[][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++)
                System.out.print(a[i][j]+",");
            System.out.println();
        }
    }
}

class Position{ //表示点实体
    int x;
    int y;
    int di=1; //方向(direct)

    public Position(){}

    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }

}
