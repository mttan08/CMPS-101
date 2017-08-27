// Matthew Tan
// mxtan
// pa3
// Matrix.java: performs some basic operations
// on a matrix

public class Matrix
{
    // private class Entry to store
    // the column in sorted order
    private class Entry 
    {
        int column;
        double value;

        // constructor
        public Entry(int column, double value) 
        {
            this.column = column;
            this.value = value;
        }

        // toString()
        // overrides Objet's toString()
        public String toString() 
        {
            return "(" + column + ", " + value + ")";
        }

        // equals()
        // overrides Object equals()
        public boolean equals(Object x) 
        {
            boolean eq = false;
            Entry entry;
            if (x instanceof Entry) 
            {
                entry = (Entry) x;
                eq = (this.value == entry.value);
                eq = (this.column == entry.column);
            }
            return eq;
        }
    }

    // fields
    List[] row;
    int matrixSize = 0;
    int NNZ = 0; // also known as non-empty entries


    // constructor
    // makes a n * n zero matrix
    // pre: n > 1
    public Matrix(int n)
    {
        if (n < 1)
        {
            throw new RuntimeException(
                  "cannot make matrix if n is 0.");
        }
        else
        {
            matrixSize = n; // initialize matrixSize to n
            row = new List[matrixSize + 1]; // initialize array to start at 1
            // start entries counting by 1
            for (int i = 1; i <= matrixSize; i++) 
            {
                row[i] = new List(); // store a list
            }
        }
    }

    // Access functions    

    // getSize() 
    // returns n, the number of rows and columns
    // of this matrix
    public int getSize()
    {
        return matrixSize; 
    }

    // getNNZ()
    // returns the number of non-zero entries
    // in this matrix
    public int getNNZ()
    {
        return NNZ;
    }

    // equals()
    // overrides Object's equals() method
    public boolean equals(Object x)
    {
        boolean eq = true;
        Matrix M = (Matrix) x;
        eq = (matrixSize == M.matrixSize);
        for (int i = 1; i <= getSize(); i++)
        {
            //System.out.println("row[i] is: " + row[i]);
            //System.out.println("M.row[i] is: "+ M.row[i]);
            //System.out.println("this.getNNZ() is: " + this.getNNZ());
            //System.out.println("M.getNNZ() is: " + M.getNNZ());
            //System.out.println("row[i].equals(M.row[i]) is: " 
            //         + row[i].equals(M.row[i]));
            if (row[i].length()!= 0 && M.row[i].length() != 0)
            {
                if (!(row[i].equals(M.row[i]) || this.getNNZ() == M.getNNZ()))
                { 
                    eq = false;
                    break;
                }
            }
        }
        return eq;
    }

    // Manipulation procedures
    
    // makeZero()
    // sets this matrix to zero state
    public void makeZero()
    {
        row = new List[matrixSize + 1];
        NNZ = 0;
        for(int i = 1; i <= getSize(); i++) 
        {
            row[i] = new List();
        }
    }

    // copy()
    // returns a new matrix having the same entries
    // as this matrix
    public Matrix copy()
    {
        Matrix M = new Matrix(matrixSize);
        for (int i = 1; i <= getSize(); i++)
        {
            if (row[i].length() > 0)
            {
                row[i].moveFront();
                while (row[i].index() != -1)
                {
                    Entry entry = (Entry) row[i].get();
                    M.changeEntry(i, entry.column, entry.value);
                    row[i].moveNext();
                }
            }
        }
        return M;
    }

    // changeEntry()
    // changes ith row, jth column of this matrix to x
    // pre: 1<=i<=getSize(), 1<=j<=getSize()
    public void changeEntry(int i, int j, double x)
    {
        //System.out.println("in changeEntry(): i is: " + i);
        //System.out.println("in changeEntry(): j is: " + j);
        //System.out.println("in changeEntry(): j is: " + j);
        //System.out.println("in changeEntry(): matrix[i - 1] is: "
        //       + matrix[i - 1]);
        if((i <= 1 && i >= getSize()) || (j <= 1 && j >= getSize()))
        {
            throw new RuntimeException(
                    "Matrix Exception: changeEntry() called with invalid arguments.");
        }        
        if (row[i].length() == 0) 
        {
            //System.out.println("in changeEntry(): x is: " + x);

            // if x == 0, do nothing
            if (x != 0)
            {
                Entry E = new Entry(j, x);
                row[i].append(E);
                NNZ++;
                //System.out.println("changeEntry(): case 1: NNZ is: " + NNZ);
            }
        } 
        else 
        {
            //System.out.println("changeEntry(): case 2: NNZ is: " + NNZ);
            row[i].moveFront();
            for (int rows = 1; rows <= j; rows++) 
            {
                Entry entry = (Entry) row[i].get();
                if (entry.column == j) 
                {
                    if (x == 0) 
                    {
                        row[i].delete();
                        NNZ--;
                        //System.out.println("changeEntry(): case 3: NNZ is: " + NNZ);
                        break;
                    } 
                    else 
                    {
                        entry.value = x;
                        break;
                    }
                }
                else if (entry.column > j) 
                {
                    if (x == 0) 
                    {
                        break;
                    }
                    else
                    {
                        row[i].insertBefore(new Entry(j, x));
                        NNZ++;
                        break;
                    }
                }
                else
                {
                    row[i].moveNext();
                    if (row[i].index() == -1) 
                    {
                        if (x == 0) 
                        {
                            break;
                        }
                        else
                        {
                            row[i].append(new Entry(j, x));
                            NNZ++;
                            break;
                        }
                    }
                }
            }
        }
    }

    // scalarMult()
    // returns a new matrix that is the scalar
    // product of this matrix with x
    public Matrix scalarMult(double x)
    {
        Matrix M = new Matrix(matrixSize);
        for (int i = 1; i <= getSize(); i++) 
        {
            if (row[i].length() != 0) 
            {
                row[i].moveFront();
                while (row[i].index() != -1) 
                {
                    Entry entry = (Entry) row[i].get();
                    double scalarMult = x * entry.value;
                    M.changeEntry(i, entry.column, scalarMult);
                    row[i].moveNext();
                }
            }
        }
        return M;
    }

    // transpose()
    // returns a new matrix that is the
    // the transpose of this matrix
    public Matrix transpose()
    {
        Matrix M = new Matrix(matrixSize);
        for (int i = 1; i <= getSize(); i++) 
        {
            if (row[i].length() != 0) 
            {
                row[i].moveFront();
                for (int j = 1; j <= row[i].length(); j++) 
                {
                    if (row[i].index() != -1) 
                    {
                        Entry entry = (Entry) row[i].get();
                        M.changeEntry(entry.column, i, entry.value);
                        row[i].moveNext();
                    }
                }
            }
        }
        return M;
    }

    // dot()
    // helper function for mult
    // returns the dot product of a matrix
    private static double dot(List P, List Q)
    {
        P.moveFront();
        Q.moveFront();
        double sum = 0;
        while (P.index() != - 1 && Q.index() != -1)
        {
            Entry eP = (Entry) P.get();
            Entry eQ = (Entry) Q.get();
            if (eP.column < eQ.column)
            {
                P.moveNext();
            }
            else if (eP.column > eQ.column)
            {
                Q.moveNext();
            }
            else
            {
                sum += (eP.value * eQ.value);
                P.moveNext();
                Q.moveNext();
            }
        }
        return sum;
    }

    // add()
    // returns a new matrix that is the sum
    // of this matrix with M
    public Matrix add(Matrix M)
    {
        if (getSize() != M.getSize())
        {
            throw new RuntimeException(
                  "Matrix Exception: cannot add() matrices of different sizes");
        }
      
        if (M == this)
        {
            return this.scalarMult(2);
        }
        Matrix N = new Matrix(getSize());
        for (int i = 1; i <= getSize(); i++)
        {
            List list1 = row[i];
            List list2 = M.row[i];
            list1.moveFront();
            list2.moveFront();
         
            while (list1.index() >= 0 && list2.index() >= 0)
            {
                Entry e1 = (Entry) (list1.get());
                Entry e2 = (Entry) (list2.get());
                if (e1.column == e2.column)
                {
                    if (e1.value + e2.value != 0)
                    {
                        N.changeEntry(i, e1.column, (e1.value + e2.value));
                        list1.moveNext();
                        list2.moveNext();
                    }
                    else
                    {
                        list1.moveNext();
                        list2.moveNext();
                    }
                }
                else if (e1.column < e2.column)
                {
                    N.changeEntry(i, e1.column, e1.value);
                    list1.moveNext();
                }
                else
                {
                    N.changeEntry(i, e2.column, e2.value);
                    list2.moveNext();
                }
            }
            if (list1.index() == list2.index())
            {
                continue;
            }
            List list3;
            if (list1.index() > -1)
            {
                list3 = list1;
            }
            else
            {
                list3 = list2;
            }
            while (list3.index() > -1)
            {
                Entry e3 = (Entry) list3.get();
                N.changeEntry(i, e3.column, e3.value);
                list3.moveNext();
            }
        }
        return N;
    }

    // sub()
    // returns a new matrix that is the subtraction
    // of this matrix with M
    // pre: getSize() == M.getSize()
    public Matrix sub(Matrix M)
    {
        //System.out.println("Matrix.java: sub() called.");
        if (getSize() != M.getSize())
        {
            throw new RuntimeException(
                  "Matrix Exception: cannot sub() matrices of different sizes");
        }
      
        Matrix N = new Matrix(getSize());
        //System.out.println("M.equals(this) is: " + M.equals(this));
        if (M == this)
        {
            return N;
        }
        for (int i = 1; i <= getSize(); i++)
        {
            List list1 = row[i];
            List list2 = M.row[i];
            list1.moveFront();
            list2.moveFront();
            //System.out.println("Matrix.java: sub(): list1 is: " + list1);
            //System.out.println("Matrix.java: sub(): list2 is: " + list2);
            //System.out.println("Matrix.java: results is: " + results);
            //System.out.println("getSize() is: " + getSize());
            while (list1.index() > -1 && list2.index() > -1)
            {
                Entry e1 = (Entry) (list1.get());
                Entry e2 = (Entry) (list2.get());
                if (e1.column == e2.column)
                {
                    //System.out.println("Matrix.java: sub(): e1.value is: " 
                    //       + e1.value);
                    //System.out.println("Matrix.java: sub(): e2.value is: " 
                    //       + e2.value);
                    if ((e1.value - e2.value) != 0)
                    {
                        N.changeEntry(i, e1.column, (e1.value - e2.value));
                        list1.moveNext();
                        list2.moveNext();
                    }
                    else
                    {
                        //System.out.println("Matrix.java: sub(): list1.get() is: "
                        //     + list1.get());
                        //System.out.println("Matrix.java: sub(): list2.get() is: " 
                        //               + list2.get());
                        //System.out.println("Matrix.java: sub(): list1.length() is: " 
                        //            + list1.length());
                        //System.out.println("Matrix.java: sub(): list2.length() is: " 
                        //           + list2.length());
                        list1.moveNext();
                        list2.moveNext();
                    }
                }
                else if (e1.column < e2.column)
                {
                    N.changeEntry(i, e1.column, e1.value);
                    list1.moveNext();
                }
                else
                {
                    N.changeEntry(i, e2.column, -e2.value);
                    list2.moveNext();
                }
            }
            if (list1.index() == list2.index())
            {
                continue;
            }
            List list3;
            if (list1.index() > -1)
            {
                list3 = list1;
            }
            else
            {
                list3 = list2;
            }
            int sign;
            if (list1.index() > -1)
            {
                sign = 1;
            }
            else
            {
                sign = -1;
            }
            while (list3.index() >= 0)
            {
                Entry e3 = (Entry) (list3.get());
                N.changeEntry(i, e3.column, sign * e3.value);
                list3.moveNext();
            }
        }
        return N;
    }

    // mult()
    // returns a new matrix that is the product
    // of this matrix with M
    // pre: getSize() == M.getSize()
    public Matrix mult(Matrix M)
    {   
        if (getSize() != M.getSize())
        {
            throw new RuntimeException(
                  "Matrix Exception: cannot mult() on unequal matrices");
        }

        Matrix temp = M.transpose();
        Matrix N = new Matrix(getSize());
        
        for (int i = 1; i <= getSize(); i++)
        {
            if (row[i].length() == 0)
            {
                continue;  
            }
            for (int j = 1; j <= temp.getSize(); j++)
            {
                if (temp.row[j].length() == 0)
                {
                    continue;
                }
                else
                {
                    double dotProd = dot(row[i], temp.row[j]);
                    if (dotProd != 0.0)
                    {
                        N.changeEntry(i, j, dotProd);
                    }
                }
            }
        }        
        return N;
    }

    // Other functions

    // toString()
    // overrides Object's toString() method
    public String toString() 
    {        
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= getSize(); i++)
        {
            if (row[i].length() > 0)
            {
                sb.append(i + ": " + row[i] + "\n");
            }
        }
        return new String(sb);
    }
}
