import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// All the implementation codes go here

// A node class
class Node

{

    public char data;

    public Node leftChild;

    public Node rightChild;



    public Node(char x)

    {

        data = x;

    }


// A method that displays the node
    public void displayNode()

    {

        System.out.print(data);

    }

}

// a stack of nodes

class Stack1

{

    private Node[] a;

    private int    top, m;


//The constructor for Stack1
    public Stack1(int max)

    {

        m = max;

        a = new Node[m];

        top = -1;

    }

//Push a node into the stack

    public void push(Node key)

    {

        a[++top] = key;

    }


//Pop a node from the stack
    public Node pop()

    {
if(!(isEmpty())) {
    return ( a[top--] );
}
return null;
    }


//Checking if the stack is empty
    public boolean isEmpty()

    {


        return (top == -1);


    }

}

//A stack of characters

class Stack2

{

    private char[] a;

    private int    top, m;


//A constructor for Stack2
    public Stack2(int max)

    {

        m = max;

        a = new char[m];

        top = -1;

    }


//Push a character into the stack
    public void push(char key)

    {

        a[++top] = key;

    }


//Pop a character from the stack
    public char pop()

    {

        return (a[top--]);

    }



    public boolean isEmpty()

    {

        return (top == -1);

    }

}


// A class that handles all the string conversion before insertion takes place
class Conversion

{

    private Stack2 s;

    private String input;

    private String output = "";


// A constructor for the conversion class
    public Conversion(String str)

    {

        input = str;

        s = new Stack2(str.length());

    }


//This method will check if the character is an operand then push into a the stack

    public String inToPost()

    {

        for (int i = 0; i < input.length(); i++)

        {

            char ch = input.charAt(i);

            switch (ch)

            {

                case '+':

                case '-':

                    gotOperator(ch, 1);

                    break;

                case '*':

                case '/':

                    gotOperator(ch, 2);

                    break;

                case '(':

                    s.push(ch);

                    break;

                case ')':

                    gotParenthesis();

                    break;

                default:

                    output = output + ch;

            }

        }

        while (!s.isEmpty())

            output = output + s.pop();

        return output;

    }
    public String inToPre()

    {

        for (int i = 0; i < input.length(); i++)

        {

            char ch = input.charAt(i);

            switch (ch)

            {

                case '+':

                case '-':

                    gotOperator(ch, 1);

                    break;

                case '*':

                case '/':

                    gotOperator(ch, 2);

                    break;

                case '(':

                    s.push(ch);

                    break;

                case ')':

                    gotParenthesis();

                    break;

                default:

                    output = output + ch;

            }

        }

        while (!s.isEmpty())

            output = output + s.pop();

        return output;

    }


//Check if a character is an operator or not
    private void gotOperator(char opThis, int prec1)

    {

        while (!s.isEmpty())

        {

            char opTop = s.pop();

            if (opTop == '(')

            {

                s.push(opTop);

                break;

            } else

            {

                int prec2;

                if (opTop == '+' || opTop == '-')

                    prec2 = 1;

                else

                    prec2 = 2;

                if (prec2 < prec1)

                {

                    s.push(opTop);

                    break;

                } else

                    output = output + opTop;

            }

        }

        s.push(opThis);

    }


//Check for paranthesis
    private void gotParenthesis()

    {

        while (!s.isEmpty())

        {

            char ch = s.pop();

            if (ch == '(')

                break;

            else

                output = output + ch;

        }

    }

}

// A class of tree with a root

class Tree

{

    private Node root;

// A constructor of the tree

    public Tree()

    {

        root = null;

    }


// Insert a given string into a node
    public void insertPost(String s)

    {

        Conversion c = new Conversion(s);

        s = c.inToPost();

        Stack1 stk = new Stack1(s.length());

        s = s + "#";

        int i = 0;

        char symbol = s.charAt(i);

        Node newNode;

        while (symbol != '#')

        {

            if (symbol >= '0' && symbol <= '9' || symbol >= 'A'

                    && symbol <= 'Z' || symbol >= 'a' && symbol <= 'z')

            {

                newNode = new Node(symbol);

                stk.push(newNode);

            } else if (symbol == '+' || symbol == '-' || symbol == '/'

                    || symbol == '*')

            {

                Node ptr1 = null;
                Node ptr2 = null;


                    ptr1 = stk.pop();





                ptr2 = stk.pop();


                newNode = new Node(symbol);

                newNode.leftChild = ptr2;

                newNode.rightChild = ptr1;

                stk.push(newNode);

            }

            symbol = s.charAt(++i);

        }

        root = stk.pop();

    }



    public void insertPre(String s)

    {
//Passing the string into the conversion class
        Conversion c = new Conversion(s);
//Positing the instance
        s = c.inToPre();

        Stack1 stk = new Stack1(s.length());

        s = s + "#";

        int i = 0;

        char symbol = s.charAt(i);

        Node newNode;

        while (symbol != '#')

        {

            if (symbol >= '0' && symbol <= '9' || symbol >= 'A'

                    && symbol <= 'Z' || symbol >= 'a' && symbol <= 'z')

            {

                newNode = new Node(symbol);

                stk.push(newNode);
                newNode.leftChild = null;
                newNode.rightChild = null;

            } else if (symbol == '+' || symbol == '-' || symbol == '/'

                    || symbol == '*')

            {


                Node ptr1 = null;
                Node ptr2 = null;




                newNode = new Node(symbol);



                   newNode.rightChild = stk.pop();
                    newNode.leftChild = stk.pop();

                stk.push(newNode);



            }

            symbol = s.charAt(++i);

        }

        root = stk.pop();

    }




// A method handling all types of traverses
    public void traverse(int type)

    {

        switch (type)

        {

            case 1:

                System.out.print("Preorder Traversal:-    ");

                preOrder(root);

                break;

            case 2:



                inOrder(root);

                break;

            case 3:

                System.out.print("Postorder Traversal:-   ");

                postOrder(root);

                break;

            case 4:
                System.out.println("Inorder Diagram");
                printNode(root);



        }

    }

    //Preorder traversal

    private void preOrder(Node localRoot)

    {

        if (localRoot != null)

        {

            localRoot.displayNode();

            preOrder(localRoot.leftChild);

            preOrder(localRoot.rightChild);

        }

    }


//Inorder traversal
    private void inOrder(Node localRoot)

    {

        if (localRoot != null)

        {

            inOrder(localRoot.leftChild);

            localRoot.displayNode();

            inOrder(localRoot.rightChild);

        }

    }


//Postorder traversal
    private void postOrder(Node localRoot)

    {

        if (localRoot != null)

        {

            postOrder(localRoot.leftChild);

            postOrder(localRoot.rightChild);

            localRoot.displayNode();

        }

    }

// This is the printer method

    public static <Character extends Comparable<?>> void printNode(Node root) {
        int maxLevel = maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                char c = (char)node.data;
                System.out.print(c);
                newNodes.add(node.leftChild);
                newNodes.add(node.rightChild);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");



        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.leftChild), maxLevel(node.rightChild)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}