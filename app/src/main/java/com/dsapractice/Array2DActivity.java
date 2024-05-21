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


        int matrix[][] = {{6, 6, 2, 28, 2},
                          {12, 26, 3 ,28 ,7},
                          {22, 25, 3 ,4 ,23},
        };
        ArrayList<Integer> listOfElementsTraversed = spirallyTraverse(matrix, 3, 5);
        Log.d(TAG, "Spiral traversal in matrix: " + listOfElementsTraversed);
        /*exitElement();
        searchIn2DMatrix(40);*/
    }

    private void searchIn2DMatrix(int NumToFind) {
        //https://www.youtube.com/watch?v=5vP0-g94xEA&list=PL-Jc9J83PIiFkOETg2Ybq-FMuJjkZSGeH&index=19
        int arr[][] = {{11, 12, 13, 14}, {21, 22, 23, 24}, {31, 32, 33, 34}, {41, 42, 43, 44}};

        //Here we will start from top right, why ? Because if our value is less than that
        // we can move in left direction which represent lesser number than this as it is
        // sorted array, say if the number we finding is greater than top right, then
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

    private void exitElement() {
        int arr[][] = {{0, 1, 1, 0}, {0, 0, 0, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
        //https://www.youtube.com/watch?v=FUBlb360kqU&list=PL-Jc9J83PIiFkOETg2Ybq-FMuJjkZSGeH&index=9
        // Here,In a matrix containing 0 and 1. we need to travel in same direction when 0
        // is found and if 1 is encountered we need to take a 90 degree turn, and finally
        // we need to return i,j i.e element location where the loop is exited. We will
        // start our travel from east direction
        //Here 0 means east direction, 90 degree from here goes to south, so for south we have 1,
        // after that west is 2, north 3
        int dir = 0;
        int i = 0, j = 0;

        while (true) {
            dir = (dir + arr[i][j]) % 4; // Main condition which to maintain direction

            if (dir == 0) { // east
                j++;
            } else if (dir == 1) { // south
                i++;
            } else if (dir == 2) { // west
                j--;
            } else if (dir == 3) { // north
                i--;
            }

            if (i < 0) {
                i++;
                break;
            }
            if (j < 0) {
                j++;
                break;
            }
            if (i == arr.length) {
                i--;
                break;
            }
            if (j == arr[0].length) {
                j--;
                break;
            }
        }

        Log.d(TAG, "exitElement: " + i + " " + j);


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