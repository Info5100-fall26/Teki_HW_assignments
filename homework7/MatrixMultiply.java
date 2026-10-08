public class MatrixMultiply {
   //Multiply two Matrices using loops.
    public static void main(String[] args) {
        int[][] A = {{2, 3, 4}, {3,4,5}};
        int[][] B = {{1, 2}, {3, 4}, {5, 6}};

        int rowsA = A.length;      
        int colsA = A[0].length;   
        int rowsB = B.length;      
        int colsB = B[0].length;   

        if (colsA != rowsB) {
            System.out.println("Cannot multiply: A has " + colsA + " columns but B has " + rowsB + " rows.");
            return;
        }
        System.out.println("A is " + rowsA + "x" + colsA + ", B is " + rowsB + "x" + colsB
                + " = they can be multiplied. Result will be " + rowsA + "x" + colsB + ".");

        int[][] C = new int[rowsA][colsB];
 
        for (int i = 0; i < rowsA; i++) {            
            for (int j = 0; j < colsB; j++) {       
                int sum = 0;
                for (int k = 0; k < colsA; k++) {    
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
 
        System.out.println("A * B =");
        for (int[] row : C) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}