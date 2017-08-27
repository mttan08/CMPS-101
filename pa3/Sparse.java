// Matthew Tan
// mxtan
// pa3
// Sparse.java: prints out a sparse
// matrix by using the matrix operations
// sparse matrix: matrix that does
// not contains 0s

import java.io.*;
import java.util.*;

public class Sparse
{
    public static void main (String[] args) throws IOException
    {
        int n = 0;
        int a = 0;
        int b = 0;
        Matrix A = null;
        Matrix B = null;
       
        Scanner in = null;
        PrintWriter out = null;
 
        // if args.length is not 2
        // display message and quit
        if (args.length < 2 || args.length > 2)
        {
            System.err.println ("Usage: Sparse <infile> <outfile>");
            System.exit(1);
        }
       
        // embed into try catch 
        // in case either is null
        try
        {
            in = new Scanner (new File (args[0]));
            out = new PrintWriter(new FileWriter(args[1]));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
      
        // read fine line by line
        while (in.hasNextInt())
        {
            n = in.nextInt();
            a = in.nextInt();
            b = in.nextInt();      
            A = new Matrix(n);
            B = new Matrix(n);

            // loop through matrix A
            for (int i = 1; i <= a; i++)
            {
                int rowA = in.nextInt();
                int colA = in.nextInt();
                double valA = in.nextDouble();
                A.changeEntry(rowA, colA, valA);
            }

            // loop through matrix B
            for (int j = 1; j <= b; j++)
            {
                int rowB = in.nextInt();
                int colB = in.nextInt();
                double valB = in.nextDouble();
                B.changeEntry(rowB, colB, valB);
            }
        }

        // print out the results
        out.println("A has " + a + " non-zero entries:\n" + A);
        out.println("B has " + b + " non-zero entries:\n" + B);
        out.println("(1.5)*A = \n" + A.scalarMult(1.5));
        out.println("A+B = \n" + A.add(B));
        out.println("A+A = \n" + A.add(A));
        out.println("B-A = \n" + B.sub(A));
        out.println("A-A = \n" + A.sub(A));
        out.println("Transpose(A) = \n" + A.transpose());
        out.println("A*B = \n" + A.mult(B));
        out.print("B*B = \n" + B.mult(B));
      
        // close the files
        in.close();
        out.close();
    }
}
