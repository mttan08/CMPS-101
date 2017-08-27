// ListTest2.java

public class ListTest2
{
    public static void main(String[] args)
    {
        List A = new List();
        // List B = new List();
        
        // Non-empty toString()
        A = new List();
        A.append(1);
        A.prepend(5);
        A.deleteBack();
        A.append(7);
        A.append(1);
        System.out.println("A is: "  + A);        
        System.out.println("A.toString() is:\n" + A.toString());
        String newStr = "5 7 1";
        System.out.println("newStr is:\n" + newStr);
        System.out.println("A.toString().equals(newStr) is: " 
                           + A.toString().equals(newStr));
        if (A.toString().equals("5 7 1")) System.out.println("passed");
        else System.out.println("failed");

        // NonEmpty_front
        /*
        A = new List();
        A.prepend(5);
        A.append(7);
        A.prepend(2);
        A.moveFront();
        A.insertBefore(43);
        System.out.println("A currently is: " + A);
        System.out.println("A.front() is: " + A.front());
        System.out.println("A.index() is: " + A.index());
        A.deleteFront();
        System.out.println("A after deleteFront(): " + A);
        System.out.println("A.front() after deleteFront(): " + A.front());
        System.out.println("A.index() after deleteFront() is: " + A.index());
        A.delete();
        System.out.println("A after  deleteFront(): " + A);
        System.out.println("A.front() after  delete(): " + A.front());
        System.out.println("A.index() after  delete(): " + A.index());
        if (A.front() == 5) System.out.println("passed");
        else System.out.println("failed");
        */
         
        // DeleteFront_index
        /*
        A = new List();
        A.prepend(5);
        A.prepend(65);
        A.prepend(43);
        A.prepend(2);
        A.prepend(8);
        A.prepend(1);
        A.moveFront();
        System.out.println("A currently is: " + A);
        A.deleteFront();
        System.out.println("deleteFront(): A currently is: " + A);
        System.out.println("current index is: " + A.index());
        if (A.index() != -1) System.out.println("test failed");
        A.moveBack();
        System.out.println("moveBack(): index is: " + A.index());
        A.deleteFront();
        System.out.println("deleteFront(): A is currently is: " + A);
        System.out.println("A.index() is: " + A.index());
        if (A.index() == 3) System.out.println("passed");
        else System.out.println("failed");
        */

        // DeleteBack_length
        /*
        A = new List();
        A.append(1);
        A.deleteBack();
        A.append(2);
        A.append(3);
        A.append(5);
        A.moveFront();
        A.insertAfter(12);
        A.deleteBack();
        if (A.length() == 3) System.out.println("passed");
        else System.out.println("failed");
        */

        /* deleteFront() length
        A = new List();
        A.prepend(5);
        A.prepend(65);
        A.prepend(43);
        A.prepend(2);
        A.prepend(8);
        A.prepend(1);
        A.moveFront();
        A.deleteFront();
        System.out.println("A.index() is: " + A.index());
        if (A.index() != -1) System.out.println("failed test 1");
        A.moveBack();
        A.deleteFront();
        System.out.println("A.index() is: " + A.index());
        if (A.index() == 3) System.out.println("passed");
        else System.out.println("failed");
        System.out.println("A is: " + A);
        System.out.println("A.length is: " + A.length());
        */

        // NonEmpty_back
        /*
        A = new List();
        A.append(5);
        System.out.println("list currently is: " + A);
        A.prepend(7);
        System.out.println("list currently is: " + A);
        A.append(2);
        System.out.println("list currently is: " + A);
        A.moveBack();
        System.out.println("list current index is: " + A.index());
        A.insertAfter(43);
        System.out.println("after insert 43: list currently is: " + A);
        System.out.println("after insert 43: list current index is: " + A.index());
        A.deleteBack();
        System.out.println("after deleteBack(): list currently is: " + A);
        A.delete();
        System.out.println("after delete(): list currently is: " + A);
        System.out.println("back of list: " + A.back());
        if (A.back() == 5) System.out.println("passed");
        else System.out.println("failed");
        System.out.println(A);
        System.out.println("length of list: " + A.length());
        */

        /*    
        for (int i=1; i<=20; i++)
        {
            A.append(i);
            B.prepend(i);
        }
        System.out.println(A);
        System.out.println(B);
      
        for (A.moveFront(); A.index()>=0; A.moveNext())
        {
            System.out.print(A.get()+" ");
        }
        System.out.println();
        for(B.moveBack(); B.index()>=0; B.movePrev()){
            System.out.print(B.get()+" ");
        }
        System.out.println();
      
        List C = A.copy();
        System.out.println(A.equals(B));
        System.out.println(B.equals(C));
        System.out.println(C.equals(A));

        A.moveFront();
        for (int i = 0; i < 5; i++) 
        {
            A.moveNext();
        }
        A.insertBefore(-1);
        A.back();
        for(int i = 0; i < 9; i++) 
        {
            A.moveNext();
        }
        A.insertAfter(-2);
        for(int i=0; i<5; i++)
        { 
            A.movePrev();
        }
        A.deleteFront();
        System.out.println(A);
        A.deleteBack();
        System.out.println(A);
        A.prepend(1);
        System.out.println(A);
        A.append(20);
        System.out.println(A);
        A.delete();
        System.out.println(A);
        System.out.println(A.length());
        A.clear();
        System.out.println(A.length());
        */
    }
}
