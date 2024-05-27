package com.dsapractice;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class Array2DActivity extends AppCompatActivity {

    private static final String TAG = "Array2DActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array2_dactivity);

        //Basics of 2d array to store values.
      /*  storeValueIn2D();

        matrixMultiplication();
        waveTraversal();*/
        /*int matrix[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};*/


       /* int matrix[][] = {{6, 6, 2, 28, 2},
                {12, 26, 3, 28, 7},
                {22, 25, 3, 4, 23},
        };
        ArrayList<Integer> listOfElementsTraversed = spirallyTraverse(matrix, 3, 5);
        Log.d(TAG, "Spiral traversal in matrix: " + listOfElementsTraversed);


        int A[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};

        int k = findK(A, 4, 4, 10);
        Log.d(TAG, "element present at kth position is : " + k);


        int arr[][] = {{0, 1, 0}, {0, 1, 1}, {0, 0, 0}};
        int[] result = FindExitPoint(9, 23, arr);
        Log.d(TAG, "FindExitPoint: " + Arrays.toString(result));


        int[][] matrix1 = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        int n = 3;
        // rotateBy90ClkWise(matrix1, n);
        rotateBy90AntiClkWise(matrix1, n);
        printMatrix(n, matrix);*/


        // row with minimum number of 1's
/*        int[][] a = {
                {0,0,1,0},
                {0,0, 1,1},
                {0, 1, 1, 0},
                {0,0,0,0}
        };

        int minRowIndex = minRow(4, 4, a);
        Log.d(TAG, "minRowIndex: "+ minRowIndex);

        int maxRowIndex = maxOnes(a,4, 4);
        Log.d(TAG, "maxRowIndex: "+ maxRowIndex);

        searchIn2DMatrix(40);*/


        int[][] matrix2 = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        ArrayList<Integer> list = downwardDiagonal(3, matrix2);
        Log.d(TAG, "downwardDiagonal : " + list);
    }

    //Give a N * N square matrix A, return all the elements of its anti-diagonals from top to bottom.
    static ArrayList<Integer> downwardDiagonal(int N, int A[][]) {
        /**
         * Input
         [[1, 2, 3],
         [4, 5, 6],
         [7, 8, 9]]

         Output:
         1 2 4 3 5 7 6 8 9
         */

        // code here

        ArrayList<Integer> resultList = new ArrayList<>();
        int i = 0, j = 0;
        //row and column are same, i.e. N, if n,m are given different use it accordingly.


        while (j < N) {
            int row = i;
            int col = j;

            while (col >= 0 && row < N) {
                resultList.add(A[row][col]);
                row++;
                col--;
            }
            j++;
        }

        j--;
        i = 1;
        while (i < N) {
            int row = i;
            int col = j;

            while (col >= 0 && row < N) {
                resultList.add(A[row][col]);
                row++;
                col--;
            }
            i++;
        }

        return resultList;
    }

    int minRow(int n, int m, int a[][]) {
        /*{1, 1, 1, 1},
        {1, 1, 0, 0},
        {0, 0, 1, 1},
        {1, 1, 1, 1}*/
        // code here

        int currentCount = 0;
        int minCount = 0;
        int minRow = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 1) {
                    currentCount++;
                }
            }

            if (currentCount < minCount || i == 0) {
                minCount = currentCount;
                minRow = i;
            }
            currentCount = 0;
        }

        return minRow + 1;

    }


    public int maxOnes(int Mat[][], int N, int M) {
        // your code here

        int currentCount = 0;
        int maxCount = 0;
        int maxRow = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (Mat[i][j] == 1) {
                    currentCount++;
                }
            }

            if (currentCount > maxCount /*|| i==0*/) {
                maxCount = currentCount;
                maxRow = i;
            }
            currentCount = 0;
        }

        return maxRow;
    }

    private static void printMatrix(int n, int[][] matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(matrix[i][j] + " ");
        }
    }

    private void rotateBy90ClkWise(int matrix[][], int n) {
        // Since it is n x n matrix
        // n = matrix.length,
        // n = matrix[0].length

        /**
         input                     90 degree rotate clkwise
         1 2 3                        7 4 1
         4 5 6        =>              8 5 2
         7 8 9                        9 6 3

         ||
         Transpose
         1 4 7
         2 5 8
         3 6 9

         //solution is first we take transpose of input, after transpose we just need to reverse
         //each row to obtain our desired result

         */
        // Taking transpose of matrix, here row are converted to columns, simply reverse [i][j] with
        // [j][i]. Important, but we will not cover all elements, either upper diagonal or lower
        // diagonal, because if traverse all element same elements will be reverse 2 times and the
        //end result will be the same matrix not the reversed one.

        //for row
        //Here here got our transpose
        for (int i = 0; i < matrix.length; i++) {
            //for column, note, j is not initialized with 0 but with i itself, to prevent same
            //elements to be swapped 2 times.
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //reverse each row
        for (int i = 0; i < matrix.length; i++) {
            int left = 0;
            int right = matrix[i].length - 1;

            while (left < right) {

                //swapping
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;

                left++;
                right--;
            }
        }


    }

    private void rotateBy90AntiClkWise(int matrix[][], int n) {
        // Since it is n x n matrix
        // n = matrix.length,
        // n = matrix[0].length

        /**
         input                     90 degree rotate anti-clkwise
         1 2 3                        3 6 9
         4 5 6         =>             2 5 8
         7 8 9                        1 4 7

         ||
         Transpose
         1 4 7
         2 5 8
         3 6 9

         //solution is first we take transpose of input, after transpose we just need to reverse
         //each column to obtain our desired result

         */

        //for row
        //Here we got our transpose
        for (int i = 0; i < matrix.length; i++) {
            //for column, note, j is not initialized with 0 but with i itself, to prevent same
            //elements to be swapped 2 times.
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //reverse each column
        for (int j = 0; j < matrix[0].length; j++) {
            int left = 0;
            int right = matrix.length - 1;

            while (left < right) {

                //swapping
                int temp = matrix[left][j];
                matrix[left][j] = matrix[right][j];
                matrix[right][j] = temp;

                left++;
                right--;
            }
        }


    }

    private void searchIn2DMatrix(int NumToFind) {
        //https://www.youtube.com/watch?v=5vP0-g94xEA&list=PL-Jc9J83PIiFkOETg2Ybq-FMuJjkZSGeH&index=19
        int arr[][] = {{11, 12, 13, 14}, {21, 22, 23, 24}, {31, 32, 33, 34}, {41, 42, 43, 44}};

        //Here we will start from top right, why ? Because if our value is less than that
        // we can move in left direction which represent lesser number than this as it is
        // sorted array, say if the number we are finding is greater than top right, then
        // we will move towards bottom direction as going downwards means greater values are there.

        int i = 0, j = arr[0].length - 1;

        while (i < arr.length && j >= 0) {
            if (arr[i][j] == NumToFind) {
                Log.d(TAG, "searchIn2DMatrix: " + i + " " + j);
                return;
            } else if (arr[i][j] < NumToFind) {
                i++;
            } else {
                j--;
            }
        }

        Log.d(TAG, "searchIn2DMatrix: " + i + " " + j);


    }

    public int[] FindExitPoint(int n, int m, int[][] matrix) {
        //https://www.youtube.com/watch?v=FUBlb360kqU&list=PL-Jc9J83PIiFkOETg2Ybq-FMuJjkZSGeH&index=9
        // Here,In a matrix containing 0 and 1. we need to travel in same direction when 0
        // is found and if 1 is encountered we need to take a 90 degree turn, and finally
        // we need to return i,j i.e element location where the loop is exited. We will
        // start our travel from east direction
        //Here 0 means east direction, 90 degree from here goes to south, so for south we have 1,
        // after that west is represented as 2, north as 3
        //Initially we have our direction as east (i.e.dir = 0), if we getting 0 we want to remain
        //in east direction only, if 1 encountered then 90 turn, so what we do is we add this 1 value
        //to dir, dir = 0 +1 => dir = 1, 1 means direction is south now which is correct, similarly
        //for west => dir = 1+1 => 2 , then for north =>  dir = 2+1 =>3 which represent north direction.
        //Now, the thing if we encounter 1 then dir = 3+1 => 4 which is incorrect, we don't have fifth
        //direction, on this we want dir to set as 0 again, hence we take modulas for complete
        //statement (dir + arr[i][j]) % 4 i.e.  0 %4 = 0, 1%4 = 1, 2%4 = 2, 3%4 = 3 and our main
        //edge case 4%4 =0 which is correct now.

        //n = no of row = matrix.length
        //m = no. of column = matrix[0].length

        // code here
        int[] result = new int[2];

        int dir = 0;
        int i = 0, j = 0;

        while (true) {
            dir = (dir + matrix[i][j]) % 4; // Main condition which maintains direction

            if (dir == 0) { // east
                //here we are resetting 1 to zero as per question requirement
                matrix[i][j] = 0;
                //only column increases, row remains same
                j++;
            } else if (dir == 1) { // south
                //only row increases, column remains same
                matrix[i][j] = 0;
                i++;
            } else if (dir == 2) { // west
                //only column decreases, row remains same
                matrix[i][j] = 0;
                j--;
            } else if (dir == 3) { // north
                //only row decreases, column remains same
                matrix[i][j] = 0;
                i--;
            }
            //These are exit points, i<0 means it exit from top, i= -1, but before break we incremented
            // i with one, because in final result we can't write (-1, column index), we should
            // return previous value before exit.
            if (i < 0) {
                i++;
                break;
            }
            // j<0 means it exit from left, i.e. j became j= -1
            if (j < 0) {
                j++;
                break;
            }
            // i == matrix.length means it exceed max row (max = matrix.length -1 ), exited from bottom
            if (i == n) {
                i--;
                break;
            }
            //j == matrix[0].length means it exceed max column (max = matrix[0].length -1 ), exited from right
            if (j == m) {
                j--;
                break;
            }
        }
        result[0] = i;
        result[1] = j;

        return result;
    }


    private ArrayList<Integer> spirallyTraverse(int matrix[][], int r, int c) {
        //left top corner is the point with minrow, min column, rightbottom corner is max row, max colum
        // for each side we run a loop, and adjust these 4 variable to avoid repeating elements.
        // code here
        ArrayList<Integer> resultList = new ArrayList<>();
        int totalElements = r * c;
        int count = 0;
        int minRow = 0, minCol = 0, maxRow = r - 1, maxCol = c - 1;

        while (count < totalElements) {

            //Top Wall
            for (int i = minRow, j = minCol; j <= maxCol && count < totalElements; j++) {
                resultList.add(matrix[i][j]);
                count++;
            }
            minRow++;

            //Right Wall
            for (int i = minRow, j = maxCol; i <= maxRow && count < totalElements; i++) {
                resultList.add(matrix[i][j]);
                count++;
            }
            maxCol--;

            //Bottom Wall
            for (int i = maxRow, j = maxCol; j >= minCol && count < totalElements; j--) {
                resultList.add(matrix[i][j]);
                count++;
            }
            maxRow--;

            //Left Wall
            for (int i = maxRow, j = minCol; i >= minRow && count < totalElements; i--) {
                resultList.add(matrix[i][j]);
                count++;
            }
            minCol++;
        }

        return resultList;

    }


    /*Given a matrix with n rows and m columns. Your task is to find the kth element which is
     obtained while traversing the matrix spirally. You need to complete the method findK which
      takes four arguments the first argument is the matrix A and the next two arguments will be n
      and m denoting the size of the matrix A and then the forth argument is an integer k. The
      function will return the kth element obtained while traversing the matrix spirally.*/
    int findK(int A[][], int n, int m, int k) {
        // Your code here
        int totalElements = n * m;
        int count = 0;
        int result = -1;
        int minRow = 0, minCol = 0, maxRow = n - 1, maxCol = m - 1;

        if (k > totalElements) {
            return result;
        }

        while (count < totalElements) {

            //Top Wall
            for (int i = minRow, j = minCol; j <= maxCol && count < totalElements; j++) {
                count++;
                if (k == count) {
                    return A[i][j];
                }
            }
            minRow++;

            //Right Wall
            for (int i = minRow, j = maxCol; i <= maxRow && count < totalElements; i++) {
                count++;
                if (k == count) {
                    return A[i][j];
                }
            }
            maxCol--;

            //Bottom Wall
            for (int i = maxRow, j = maxCol; j >= minCol && count < totalElements; j--) {
                count++;
                if (k == count) {
                    return A[i][j];
                }
            }
            maxRow--;

            //Left Wall
            for (int i = maxRow, j = minCol; i >= minRow && count < totalElements; i--) {
                count++;
                if (k == count) {
                    return A[i][j];
                }
            }
            minCol++;
        }

        return result;
    }

    private void waveTraversal() {
        int a[][] = {{11, 12, 13, 14}, {21, 22, 23, 24}, {31, 32, 33, 34}};

        boolean isReverse = false;

        for (int j = 0; j < a[0].length; j++) {
            if (isReverse) {
                for (int i = a.length - 1; i >= 0; i--) {
                    isReverse = false;
                    Log.d(TAG, "waveTraversal: " + a[i][j]);
                }
            } else {
                for (int i = 0; i < a.length; i++) {
                    isReverse = true;
                    Log.d(TAG, "waveTraversal: " + a[i][j]);
                }
            }


        }
    }

    private void matrixMultiplication() {
        int a[][] = {{7, 8, 1}, {2, 9, 1}};   // 2*3
        int b[][] = {{14, 5}, {5, 18}, {1, 3}}; // 3*2
        // just for simplicity creating new variable for row and column, but in real
        // scenarios we need to input row and column and scan elements and then loop accordingly.
        int r1 = 2, c1 = 3, r2 = 3, c2 = 2;

        // Here we not checking the condition which checks if these 2 matrices multiplication is
        // possible or not  i.e a[r1][c1] , b[r2][c2] ==> if (c1 == r2) then only multiplication possible
        // and then resultant matrix would of size c[r1][c2].
        int[][] c = new int[r1][c2];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                //it could c1 or r2, anything we can choose.
                for (int k = 0; k < c1; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                Log.d(TAG, "matrixMultiplication: " + c[i][j]);
            }
        }


    }

    private void storeValueIn2D() {
        //Imp: To get rows in 2d array use a.length, to get no. of columns for each row,
        // use a[i].length --> a[0].length or a[1].length etc.
        int[][] a = new int[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                a[i][j] = j;
            }
        }


        Log.d(TAG, "storeValueIn2D: " + Arrays.toString(a));
    }


    //{{181, 38}, {265, 50}}
}