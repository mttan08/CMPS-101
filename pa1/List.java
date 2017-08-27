// Matthew Tan
// mxtan
// pa1: List.java
// This is a doubly-linked list ADT

public class List
{
    // private class Node
    private class Node
    {
        // Fields
        int data;
        Node prev;
        Node next;
      
        // Constructor
        public Node(int data) 
        { 
            this.data = data; 
            prev = null;
            next = null; 
        }
      
        // toString():  overrides Object's toString() method
        public String toString() 
        { 
            return String.valueOf(data); 
        }
      
        // equals(): overrides Object's equals() method
        public boolean equals(Object x)
        {
            boolean eq = false;
            Node that;
            if (x instanceof Node)
            {
                that = (Node) x;
                eq = (this.data == that.data);
            }
            return eq;
        }
    }

    // Fields
    private Node front;
    private Node back;
    private Node cursor;
    private int index;
    private int length;

    // Constructor
    public List() 
    {
        init();
    }
    
    // init() condenses all for List() constructor
    private void init()
    {
        front = null;
        back = null;
        cursor = null;
        index = -1;
        length = 0;
    }
        
    // -- Access functions --------------------------------------

    // length()
    // returns the number of elements in this List
    public int length()
    {
        return length;
    }
    
    // index()
    // if cursor is defined, returns the index of
    // the current element,
    // otherwise return -1
    public int index()
    {
        if (length() <= 0)
        {
            return -1;
        }
        return index;
    }

    // front()
    // returns front element. pre: length() > 0
    public int front()
    {
        if (length() <= 0)
        {
            throw new RuntimeException(
                  "ListException: front() called on empty list");
        }
        return front.data;
    }
     
    // back()
    // returns back element. pre: length() > 0
    public int back()
    {
        if (length() <= 0)
        {
            throw new RuntimeException(
                  "ListException: back() called on empty list");
        }
        return back.data;
    }
    
    // get()
    // returns the cursor element. pre: length() > 0 and index() > 0
    public int get()
    {
        if (length() <= 0)
        {
            throw new RuntimeException(
                  "ListException: cannot get() if empty");
        }
        if (cursor == null)
        {
            throw new RuntimeException(
                  "ListException: cannot get() if cursor is null");
        }
        return cursor.data;
    }

    // equals()
    // returns true if this List and L are the same integer
    // sequence. the cursor is ignored in both lists.
    public boolean equals(List L)
    {
        boolean eq = false;
        Node M, N;
        M = this.front;
        N = L.front;
        eq = (this.length() == L.length());
        while (eq && M != null)
        {
            eq = M.equals(N);
            M = M.next;
            N = N.next;
        }
        return eq;
    }

    // -- Mainpulation procedure --------------------------
    
    // clear()
    // rests this List to it original empty state
    public void clear()
    {
        init();

        // alternate clear:
        /*
        front = null;
        back = null;
        cursor = null;
        index = -1;
        length = 0;
        */
    }

    // moveFront()
    // if List is non-empty, places the cursor under
    // the front element, otherwise does nothing
    public void moveFront()
    {
        if (length() > 0)
        {
            cursor = front;
            index = 0;
        }
    }

    // moveBack()
    // if list is non-empty, places the cursor under
    // the back element, otherwise does nothing
    public void moveBack()
    {
        if (length() > 0)
        {
            cursor = back;
            index = length - 1;
       }
    }
    
    // movePrev()
    // if cursor is defined and not at front, moves cursor
    // one step toward front of this List, cursor
    // becomes undefined, if cursor is undefined does nothing
    public void movePrev()
    {
        if (cursor == front)
        {
            cursor = null;
            index = -1;
        }
        else
        {
            cursor = cursor.prev;
            index--;
        }
    }

    // moveNext
    // if cursor is defined and not at front, move cursor
    // one step toward back of this List, if cursor is defined,
    // and at front, cursor becomes undefined, if
    // cursor is undefined, does nothing
    public void moveNext()
    {
        if (cursor == back)
        {
            cursor = null;
            index = -1;
        }
        else
        {
            cursor = cursor.next;
            index++;
        }
    }

    // prepend()
    // insert new elment into this List. if List is non-empty,
    // insertion takes place before front element
    public void prepend(int data)
    {
        Node N = new Node(data);
        if (front == null) 
        {
            front = N;
            back = N;
            cursor = front;
        } 
        else 
        {
            front.prev = N;
            N.next = front;
            N.prev = null;
            front = N;
            index++;
        }
        length++;
    }
    
    // append()
    // insert new element into this List. if this lis is non-empty
    // insertion takes place after back element
    public void append(int data)
    {
        Node N = new Node(data);
        if (back == null) 
        {
            front = N;
            back = N;
            cursor = back;
        } 
        else 
        {
            back.next = N;
            N.prev = back;
            back = N;
            N.next = null;
        }
        length++;
    }
    
    // insertBefore()
    // insert new element before cursor
    // pre: length() > 0, index() > 0
    public void insertBefore(int data)
    {
        if (length() <= 0)
        {
            //throw new RuntimeException(
            //      "ListException: cannot insertBefore() in an empty list");
            prepend(data);
        }
        if (cursor == null)
        {
            throw new RuntimeException(
             "ListException: cannot insertBefore() if cursor is undefined");
        }
        if (cursor == front)
        {
            prepend(data);
        }
        else
        {
            Node N = new Node(data);
            //System.out.println("in insertBefore(): Node N is: " + N);
            //System.out.println("in insertBefore(): cursor is: " + cursor);
            N.prev = cursor.prev;
            //System.out.println("in insertBefore(): N.prev is: " + N.prev);
            //System.out.println("in insertBefore(): cursor.prev is: " + cursor.prev);
            N.next = cursor;
            //System.out.println("in insertBefore(): N.next is: " + N.next);
            cursor.prev.next = N;
            cursor.prev = N;
            index++;
            length++;
        }
    }
    
    // insertAfter()
    // insert new element after cursor
    // pre: length() > 0, index() > 0
    public void insertAfter(int data)
    {
        if (length() <= 0)
        {
            throw new RuntimeException(
                 "ListException: insertAfter() called on empty List");
        }
        if (cursor == back) 
        {
            append(data);
        }
        else 
        {
            Node N = new Node(data);
            cursor.next.prev = N;
            N.next = cursor.next;
            N.prev = cursor;
            cursor.next = N;
            length++;
        }
    }
    
    // deleteFront()
    // deletes front element. pre: length() > 0
    public void deleteFront()
    {
        if (length() <= 0)
        {
            throw new RuntimeException(
                "ListException: cannot deleteFront() empty list.");
        }
        else
        {
            if (front == cursor)
            {
                cursor = null;
                index = -1;
            }
            if (length > 1)
            {
                front = front.next;
                front.prev = null;
                if (cursor != null)
                {                    
                    index--;
                }
            }
            length--;
        }
    }

    // deleteBack()
    // deletes back element. pre: length() > 0
    public void deleteBack()
    {
        if (length() <= 0)
        {
            throw new RuntimeException(
                  "ListException :cannot deleteBack() on an empty list");
        }
        
        else
        {
            if (back == cursor)
            {
                //System.out.println("deleteBack() called: " + "cursor is: " + cursor);
                cursor = null;
                //System.out.println("deleteBack() called: " + "cursor is: " + cursor);
                index = -1;
            }
            //System.out.println("deleteBack(): resetting back: ");
            if (length > 1)
            {
                back = back.prev;
                back.next = null;
            }
            length--;
        }
    }
    
    // delete()
    // deletes cursor element, making cursor undefined
    // pre: length() > 0, index() > 0
    public void delete()
    {
        //System.out.println("List: delete() called");
        if (length() <= 0)
        {
            throw new RuntimeException(
                  "ListException: cannot delete() empty list");
        }
        if (cursor == null)
        {
            throw new RuntimeException(
                  "ListException: cannot delete() on undefined cursor");
        }
        if (cursor == front)
        {
            deleteFront();
        }
        else if (cursor == back)
        {
            //System.out.println("deleteBack() called");
            deleteBack();
        }
        else
        {
            //System.out.println("in delete: cursor is: " + cursor);
            cursor.prev.next = cursor.next;
            cursor.next.prev = cursor.prev;
            length--;
        }
        index = -1;
    }

    // -- Other methods --------------------------------------

    // toString()
    // overrides object's toString method. returns a string
    // representation of this List containing a space
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        Node N = front;
        while (N != null)
        {
            sb.append(N.toString());
            if (N.next != null)
            {
                sb.append(" ");
            }
            N = N.next;
        }
        return new String(sb);
    }

    // copy()
    // returns a new List representing the same integer
    // sequence as this List. the cursor in the new lis
    // is undefined, regardless of the state of the cursor
    // in this list. the List is unchanged
    public List copy()
    {
        List newList = new List();
        if (length() > 0)
        {
            Node N = front;
            while (N != null)
            {
                newList.append(N.data);
                N = N.next;
            }
        }
        newList.cursor = null;
        newList.index = -1;
        return newList;
    }
}
