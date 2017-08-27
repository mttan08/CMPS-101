// Matthew Tan
// mxtan
// pa1
// Lex.java
// Returns a output file of sorted items

import java.io.*;
import java.util.*;

public class Lex
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = null;
        PrintWriter out = null;
        int countLines = 0;

        if (args.length < 2 || args.length > 2) // check if args is 2
        {
            System.err.println("Usage: Lex infile outfile");
            System.exit(1);
        } 

        // open file to read      
        try
        {
            in = new Scanner(new File(args[0]));
            out = new PrintWriter(new FileWriter(args[1]));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
        // shortcut way of doing things:
        /*
        in.useDelimiter("\\Z");
        String s = in.next();
        in.close();
        String[] lines = s.split("\n");
        */

        // read in file and count lines
        while (in.hasNextLine())
        {
            countLines++;
            in.nextLine();
        }
        in.close();
        
        // re-open the file
        try
        {
            in = new Scanner(new File(args[0]));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
        // store into array
        String[] lines = new String[countLines];
        for (int i = 0; i < countLines; i++)
        {
            lines[i] = in.nextLine();
        }

        List list = new List();
        list.append(0); // insert the first element
        // use insertion sort to sort the List
        // note: the array is untouched
        for (int i = 1; i < lines.length; i++) 
        {
            list.moveFront();
            String element = lines[i];
            int j = i;
            while (j > 0 && element.compareTo(lines[list.get()]) >= 0) 
            {
                list.moveNext();
                j--;
            }
            if (list.index() >= 0)
            {
                list.insertBefore(i);
            }
            else
            {
                list.append(i);
            }
        }

        // testing purposes only:
        /*
        for (int k = 0; k < lines.length; k++)
        {
            System.out.println(lines[k]);
        }
        
        System.out.println(list);
        */

        // print contents to file
        list.moveFront();
        while (list.index() >= 0) 
        {
            out.println(lines[list.get()]);
            list.moveNext();
        }

        in.close();
        out.close();
    }
}
