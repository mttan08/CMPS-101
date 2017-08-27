//-----------------------------------------------------------------------------
// Matthew Tan
// mxtan
// pa3
// A test client for the Matrix ADT
//-----------------------------------------------------------------------------

public class MatrixTest
{
    public static void main(String[] args)
    {
        int i, j, n = 100000;
        Matrix A = new Matrix(n);
        Matrix B = new Matrix(n);

        A.changeEntry(1,1,1); B.changeEntry(1,1,1);
        A.changeEntry(1,2,2); B.changeEntry(1,2,0);
        A.changeEntry(1,3,3); B.changeEntry(1,3,1);
        A.changeEntry(2,1,4); B.changeEntry(2,1,0);
        A.changeEntry(2,2,5); B.changeEntry(2,2,1);
        A.changeEntry(2,3,6); B.changeEntry(2,3,0);
        A.changeEntry(3,1,7); B.changeEntry(3,1,1);
        A.changeEntry(3,2,8); B.changeEntry(3,2,1);
        A.changeEntry(3,3,9); B.changeEntry(3,3,1);

        System.out.println(A.getNNZ());
        System.out.println(A);

        System.out.println(B.getNNZ());
        System.out.println(B);

        Matrix C = A.scalarMult(1.5);
        System.out.println(C.getNNZ());
        System.out.println(C);

        Matrix D = A.add(A);
        System.out.println(D.getNNZ());
        System.out.println(D);

        Matrix E = A.sub(A);
        System.out.println(E.getNNZ());
        System.out.println(E);

        Matrix F = B.transpose();
        System.out.println(F.getNNZ());
        System.out.println(F);

        Matrix G = B.mult(B);
        System.out.println(G.getNNZ());
        System.out.println(G);

        Matrix H = A.add(B);
        System.out.println(H.getNNZ());
        System.out.println(H);
      
        Matrix I = B.add(A);
        System.out.println(I.getNNZ());
        System.out.println(I);

        Matrix J = A.sub(B);
        System.out.println(J.getNNZ());
        System.out.println(J);

        Matrix K = B.sub(A);
        System.out.println(K.getNNZ());
        System.out.println(K);

        Matrix L = A.copy();
        System.out.println(L.getNNZ());
        System.out.println(L);

        System.out.println(A.equals(L));
        System.out.println(A.equals(B));
        System.out.println(A.equals(A));        

        A.makeZero();
        System.out.println(A.getNNZ());
        System.out.println(A);

        // test for NonEmpty_toString
        /*
        A.changeEntry(1, 1, 1);
        A.changeEntry(1, 2, 0);
        A.changeEntry(1, 3, 3.5);
        A.changeEntry(5, 7, 2.5);
        A.changeEntry(3, 4, 2);
        A.changeEntry(1, 1, 2);
        System.out.println(A);
        String comp = "1: (1, 2.0) (3, 3.5)\n" 
            + "3: (4, 2.0)\n" + "5: (7, 2.5)\n";
        System.out.println(A.toString().equals(comp));
        if (A.toString().equals("1: (1, 2.0) (3, 3.5)\n" + "3: (4, 2.0)\n" +
                                "5: (7, 2.5)\n")) System.out.println("test passed");
        else System.out.println("test failed");
        */

        // test for NonEmpty_equals
        /*
        B = new Matrix(15);
        A.changeEntry(1, 1, 1);
        B.changeEntry(1, 1, 1);
        if (A.equals(B)) System.out.println("test 1 passed");
        B = new Matrix(10);
        A.changeEntry(1, 1, 1);
        A.changeEntry(1, 3, 1);
        B.changeEntry(1, 1, 1);
        B.changeEntry(1, 3, 1);
        if (!A.equals(B)) System.out.println("test 2 passed");
        A.changeEntry(1, 3, 0);
        if (A.equals(B)) System.out.println("test 3 passed");
        A.changeEntry(1, 1, 0);
        B.makeZero();
        A.changeEntry(10, 10, 10);
        B.changeEntry(10, 10, 10);
        if (!A.equals(B)) System.out.println("test 4 passed");
        A = new Matrix(100);
        B = new Matrix(100);
        int valcount = 1;
        for (int j = 1; j <= 100; j++) 
        {
            for (int k = 1; k <= 100; k++) 
            {
                // hint: this is 1-10000 left-to-right row-by-row
                A.changeEntry(j, k, valcount++);
            }
            B.changeEntry(j, j, 1); // hint: this is the identity matrix
        }
        C = A.scalarMult(2);
        if (!C.equals(A.add(A))) System.out.println("test 5 passed");
        C = A.scalarMult(-2);
        if (!C.equals(A.sub(A).sub(A).sub(A))) System.out.println("test 6 passed");
        C = A.mult(B);
        if (C.equals(A)) System.out.println("all tests passed");
        */

        // test for sub_getNNZ
        /*
        A = new Matrix(10);
        B = new Matrix(10);
        A.changeEntry(1, 1, -4);
        A.changeEntry(1, 2, -2);
        A.changeEntry(1, 3, 0);
        A.changeEntry(2, 5, 4);
        A.changeEntry(2, 1, -2);
        A.changeEntry(3, 1, 2);
        A.changeEntry(2, 2, -2);
        A.changeEntry(3, 3, 0);
        C = A.sub(A);
        System.out.println("case 1: C.getNNZ() is: " + C.getNNZ());
        System.out.println("A.getNNZ() is: " + A.getNNZ());
        if (C.getNNZ() != 0 || A.getNNZ() != 6) System.out.println("test 1 failed");
        B.changeEntry(1, 1, -4);
        B.changeEntry(1, 2, 0);
        B.changeEntry(2, 1, 0);
        B.changeEntry(2, 2, -2);
        B.changeEntry(2, 4, 2);
        B.changeEntry(3, 1, 2);
        B.changeEntry(3, 2, 2);
        B.changeEntry(7, 8, 5);
        System.out.println("A.equals(B) is: " + A.equals(B));
        C = A.sub(B);
        System.out.println("C is: " + C);
        System.out.println("case 2: C.getNNZ() is: " + C.getNNZ());
        if (C.getNNZ() == 6) System.out.println("test passed");
        */

        // test for add_getNNZ
        /*
        A.changeEntry(1, 1, 4);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 0);
        A.changeEntry(2, 1, 2);
        A.changeEntry(3, 1, 0);
        A.changeEntry(2, 2, 2);
        A.changeEntry(3, 3, 0);
        C = A.add(A);
        System.out.println("case 1: C.getNNZ() is: " + C.getNNZ());
        System.out.println("A.getNNZ() is: " + A.getNNZ());
        if (C.getNNZ() != 4 || A.getNNZ() != 4) System.out.println("test 1 failed");
        B.changeEntry(1, 1, -4);
        B.changeEntry(1, 2, 0);
        B.changeEntry(2, 1, 0);
        B.changeEntry(2, 2, -2);
        B.changeEntry(2, 4, 2);
        B.changeEntry(3, 1, 0);
        B.changeEntry(3, 2, 2);
        B.changeEntry(7, 8, 5);
        C = A.add(B);
        System.out.println("case 2: C.getNNZ() is: " + C.getNNZ());
        if (C.getNNZ() == 5) System.out.println("test passed");
        */
        
        // test for changeEntry()
        /*
        A.changeEntry(2, 1, 2);
        A.changeEntry(3, 1, 5);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 5);
        A.changeEntry(1, 1, 4);
        A.changeEntry(2, 2, 2);
        A.changeEntry(2, 5, 0);
        A.changeEntry(2, 3, 0);
        A.changeEntry(3, 3, 5);
        System.out.println("case 1: A.getNNZ() is: " + A.getNNZ());
        if (A.getNNZ() != 7) System.out.println("test 1 failed");
        A.changeEntry(1, 3, 0);
        A.changeEntry(3, 1, 0);
        A.changeEntry(3, 3, 0);
        System.out.println("case 2: A.getNNZ() is: " + A.getNNZ());
        if (A.getNNZ() != 4) System.out.println("test 2 failed");
        A.changeEntry(7, 6, 42);
        A.changeEntry(10, 1, 24);
        System.out.println("case 3: A.getNNZ() is: " + A.getNNZ());
        if (A.getNNZ() != 6) System.out.println("test 3 failed");
        A.changeEntry(7, 6, 0);
        System.out.println("case 4: A.getNNZ() is: " + A.getNNZ());
        if (A.getNNZ() != 5) System.out.println("test 4 failed");
        A.makeZero();
        A.changeEntry(5, 5, 5);
        System.out.println("case 5: A.getNNZ() is: " + A.getNNZ());
        if (A.getNNZ() == 1) System.out.println("passed");
        */
    }
}
