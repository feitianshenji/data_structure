import java.util.ArrayList;
import java.util.Stack;

/*   计算  3.8/2+222/(23-21)-45*2
 * 思路：1.中缀表达式--->后缀表达式
 *           2.计算后缀表达式。
 * @author sheepmu
 */
public class Stack_KuoHaoEXP {

    enum OperatorEnum{
        add("+"),
        sub("-"),
        mul("*"),
        div("/"),
        leftkh("("),
        rightkh(")");//最后一个必须是分号。
        private String value;
        public String getValue(){
            return value;
        }
        OperatorEnum(String s){
            value=s;
        }
    }

    public static void main(String[] args){
        String s0="3.8/2+222/(23-21)-45*2";
        System.out.println("原表达式---> "+s0);
        ArrayList<String> list_in=toArray(s0);
        System.out.println("中缀表达式ArrayList---> "+ list_in);
        Stack<String> stack_post= toPostfix(list_in);
        System.out.println("后缀表达式Stack---> "+ stack_post);
        double result=calculatePost(stack_post);
        System.out.println("结果---> "+ result);
    }

    public static ArrayList<String> toArray(String input){//用ArrayList和数组相比的好处
        int len=input.length();
        ArrayList<String> list=new ArrayList<String>();
        for(int i=0;i<len;){//!!!!!不能直接i++,因为不是每个字符为一个，因为数字可能多位数。!!!!!
            String ch=String.valueOf(input.charAt(i)); //返回 char 参数的字符串表示形式
            if(ch.equals(OperatorEnum.add.getValue())||ch.equals(OperatorEnum.sub.getValue())||
                    ch.equals(OperatorEnum.mul.getValue())||ch.equals(OperatorEnum.div.getValue())||
                    ch.equals(OperatorEnum.leftkh.getValue())||ch.equals(OperatorEnum.rightkh.getValue())){
                list.add(ch);//如果遇到操作符则直接放入数组中，
                i++;
            }
            else{//如果非操作符，则就要判断这个数有几位数，因为不全是个位数，可能是多位数或者带有小数点。
                int end=getEnd(input, i);
                list.add(input.substring(i, end)) ;  //将指定的元素添加到列表list的尾部
                i=end;
            }
        }
        return list;
    }


    public static int getEnd(String s,int i){
        int result_end=i;
        for(int j=i;j<s.length();j++){
            String ch=String.valueOf(s.charAt(j));
            if(ch.equals(OperatorEnum.add.getValue())||ch.equals(OperatorEnum.sub.getValue())||
                    ch.equals(OperatorEnum.mul.getValue())||ch.equals(OperatorEnum.div.getValue())||
                    ch.equals(OperatorEnum.leftkh.getValue())||ch.equals(OperatorEnum.rightkh.getValue())){
                result_end=j;
                break;//一旦遇到操作符就说明这个数结束，跳出循环。
            }
            else if(j==s.length()-1){//         !!!!!!!!!!!!!!!!若没有遇到操作符，即最后一个数!!!!!!!!!!
                result_end=s.length();//上面一定是s.length()-1,这儿一定是= s.length()；不然会导致上面循环结束不了。
            }
        }
        return  result_end;
    }


    public static Stack<String> toPostfix(ArrayList<String> infix){  //转换为后缀表达式
        Stack<String> stack=new Stack<String>();//放操作符的栈
        Stack<String> stack_post=new Stack<String>();//把后缀表达式存在栈里比较好，方便后面运算。
        int len=infix.size();

        for(int i=0;i<len;i++){
            if(infix.get(i).equals(OperatorEnum.leftkh.getValue())){//第一种情况：若果是反括号;直接进操作符栈
                stack.push(infix.get(i));
            }
            else if(infix.get(i).equals(OperatorEnum.add.getValue())||infix.get(i).equals(OperatorEnum.sub.getValue())||
                    infix.get(i).equals(OperatorEnum.mul.getValue())||infix.get(i).equals(OperatorEnum.div.getValue())){
                //第二种情况：若是加减乘除，则先判断操作符栈是否为空，若为空，操作符直接进栈；否则比较运算级别。若扫描到的比操作符栈顶的低，操作符栈顶出来，
                //继续比较出了栈顶后的新栈顶是否比扫描到的高或等！！！while~~~~

                if(!stack.isEmpty()){//栈不为空则比较
                    if(compare(stack.peek(),infix.get(i))){ //若栈顶>=扫描到的  !!!应该是while而不是if,因为有可能连着几个都比他高
                        while(!stack.isEmpty()&&compare(stack.peek(),infix.get(i)))// !!!!!!!!stack.isEmpty()  ！！！！！！！！！！！！！
                            stack_post.push(stack.pop());

                        stack.push(infix.get(i));}

                    else if(!compare(stack.peek(),infix.get(i)))
                        stack.push(infix.get(i));
                }
                else{//栈为空则直接进栈 ！！！！！！！！！！！
                    stack.push(infix.get(i));
                }
            }
            else if(infix.get(i).equals(OperatorEnum.rightkh.getValue())){//第三种情况：如果扫描到右括号

                if(!stack.isEmpty()){
                    while(!stack.peek().equals(OperatorEnum.leftkh.getValue())){
                        stack_post.push(stack.pop()) ;
                    }
                    stack.pop();//把左括号弹出栈，不要了。
                }
            }

            else {//第四种情况：非运算符，即为运算分量

                stack_post.push(infix.get(i));
            }
        }

        if(!stack.isEmpty()){//若果读完了栈中还有运算符，便一个个的出栈并加到后缀表达式中
            while(!stack.isEmpty())
                stack_post.push(stack.pop());
        }
        System.out.println("临时存放中缀数组操作符的栈---->"+ stack);
        return 	stack_post;
    }

    public static boolean compare(String stackpeek,String ch){
        if(stackpeek.equals(OperatorEnum.mul.getValue())||stackpeek.equals(OperatorEnum.div.getValue())){
            if(ch.equals(OperatorEnum.add.getValue())||ch.equals(OperatorEnum.sub.getValue())||
                    ch.equals(OperatorEnum.mul.getValue())||ch.equals(OperatorEnum.div.getValue())){
                return true;//栈顶等级>=扫描到的元素----->把栈顶弹出并放到后缀表达式中，扫描到的进栈
            }
        }
        if(stackpeek.equals(OperatorEnum.add.getValue())||stackpeek.equals(OperatorEnum.sub.getValue())){
            if(ch.equals(OperatorEnum.add.getValue())||ch.equals(OperatorEnum.sub.getValue()))
                return true;
        }

        return false;
    }
    /**
     * 后缀栈计算思路：1.建个新栈存放后缀表达式栈中遍历到的操作数，若遍历到的是操作数直接放进新栈；若遍历到操作符，则取出
     * 新栈中的两个栈顶元素执行遍历到的操作符的运算，并把计算结果放到新栈顶，原后缀表达式栈继续遍历。
     * 最后新栈表达式中的那唯一的一个数字即最后答案
     */
    public static double calculatePost(Stack<String> postfix){
        Stack<String> newStack=new Stack<String>();//建立新栈存放操作数
        for(String s:postfix){
            if(s.equals(OperatorEnum.add.getValue())||s.equals(OperatorEnum.sub.getValue())||
                    s.equals(OperatorEnum.mul.getValue())||s.equals(OperatorEnum.div.getValue())){
                String p=s;
                String d2= newStack.pop() ;
                String d1= newStack.pop() ;
                double result_temp=caltwo(d1,d2,p);
                newStack.push(result_temp+"");
            }
            else{//读到的是数据
                newStack.push(s);

            }
        }
        return Double.valueOf(newStack.pop());
    }
    public static double caltwo(String s1,String s2,String perator){
        double result=0;
        double d1=Double.valueOf(s1);//返回保存用参数字符串 s 表示的 double 值的 Double 对象
        double d2=Double.valueOf(s2);
        switch( perator){//switch里面居然可以String     jdk1.7后就可以咯！
            case "+":
                result=d1+d2;
                break;
            case "-":
                result=d1-d2;
                break;
            case "*":
                result=d1*d2;
                break;
            case "/":
                result=d1/d2;
                break;
        }
        return result;
    }
}
