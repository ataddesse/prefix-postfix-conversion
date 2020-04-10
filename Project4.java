import java.io.DataInputStream;
import java.io.IOException;

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
            t1.insert(stream);
if(type == '@'){


            t1.traverse(1);

            System.out.println("");


    t1.traverse(2);

    System.out.println("");
    t1.traverse(4);

}else if(type == '!'){

   stream

            t1.traverse(3);

            System.out.println("");

    t1.traverse(2);

    System.out.println("");
    t1.traverse(4);

}else{

    System.out.println("Invalid type please try again");

}




            System.out.print("Enter y to continue ");

            ch = inp.readLine();

        }

    }

}
