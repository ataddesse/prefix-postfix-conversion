import java.io.DataInputStream;
import java.io.IOException;
import java.util.Stack;

public class Project4 {


    public static void main(String args[]) throws IOException

    {

        String ch = "y";

        DataInputStream inp = new DataInputStream(System.in);

        while (ch.equals("y"))

        {

            Tree t1 = new Tree();

            System.out.println("Enter the Expression");

            String a = inp.readLine();

            char type = a.charAt(0);

            String stream = a.substring(1);



if(type == '@'){

    String pre = post2Pre(stream);

    System.out.println("Prefix: "+ pre);

    String infix = getInfix(stream);

    System.out.println("Inorder: " + infix);


    t1.insertPost(infix);


    System.out.println("");
    t1.traverse(4);

}else if(type == '!'){



    String post = pre2Post(stream);

    System.out.println("Postfix: " + post);

    String infix = getInfix(post);

    System.out.println("Inorder: " + infix);


    t1.insertPost(infix);


    System.out.println("");
    t1.traverse(4);
}else{
    System.out.println("Please try again by inputing a valid format.");
}




            System.out.print("Enter y to continue ");

            ch = inp.readLine();

        }

    }

    private static String post2Pre(String exp)
    {

        Stack<String> stack=new Stack();

        for(int i=0;i<exp.length();i++)
        {
            if(!isOperator(exp.charAt(i)))
                stack.push(exp.charAt(i)+"");
            else
            {
                String s1=stack.pop();
                String s2=stack.pop();
                stack.push(exp.charAt(i)+s2+s1);
            }
        }
        return stack.pop();

    }

    private static String pre2Post(String exp)
    {

        Stack<String> stack=new Stack();

        for(int i=exp.length()-1;i>=0;i--)
        {
            if(!isOperator(exp.charAt(i)))
                stack.push(exp.charAt(i)+"");
            else
            {
                String s1=stack.pop();
                String s2=stack.pop();
                stack.push(s1+s2+exp.charAt(i));
            }
        }
        return stack.pop();

    }

    private static boolean isOperator(char c)
    {

        switch(c)
        {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
        }
        return false;

    }

    static boolean isOperand(char x)
    {
        return (x >= 'a' && x <= 'z') ||
                (x >= 'A' && x <= 'Z');
    }

    // Get Infix for a given postfix
// expression
    static String getInfix(String exp)
    {
        Stack<String> s = new Stack<String>();

        for (int i = 0; i < exp.length(); i++)
        {
            // Push operands
            if (isOperand(exp.charAt(i)))
            {
                s.push(exp.charAt(i) + "");
            }

            // We assume that input is
            // a valid postfix and expect
            // an operator.
            else
            {
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();
                s.push("" + op2 + exp.charAt(i) +
                        op1 + "");
            }
        }

        // There must be a single element
        // in stack now which is the required
        // infix.
        return s.peek();
    }


}
