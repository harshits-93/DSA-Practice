package com.dsapractice;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class SortingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);

        int[] arr = {2, 5, 1, 7, 3};
       // bubbleSort(arr);
        //selectionSort(arr);
        //insertionSort(arr);
    }

    private void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (isSmaller(arr, j + 1, j)) {
                    swap(arr, j + 1, j);
                } else {
                    break;
                }
            }
        }

        Log.d("TAG", "insertionSort: "+Arrays.toString(arr));
        //print(arr);

    }

    private void selectionSort(int[] arr) {
        int numOfIteration = arr.length - 1;

        for (int i = 0; i < numOfIteration; i++) {
            int min = i;

            for (int j = i + 1; j <= numOfIteration; j++) {
                if (isSmaller(arr, j, min)) {
                    min = j;
                }
            }

            swap(arr, min, i);
        }

        print(arr);
    }

    private void bubbleSort(int[] arr,int n) {

        if(n>1){
            int numOfIteration = arr.length - 1;
            for (int i = 0; i < numOfIteration; i++) {
                for (int j = 0; j < numOfIteration - i; j++) {
                    if (isSmaller(arr, j + 1, j)) {
                        swap(arr, j + 1, j);
                    }
                }
            }
        }


       // print(arr);

    }

    // used for swapping ith and jth elements of array
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping " + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // return true if ith element is smaller than jth element
    public static  boolean isSmaller(int[] arr, int i, int j) {
        System.out.println("Comparing " + arr[i] + " and " + arr[j]);
        if (arr[i] < arr[j]) {
            return true;
        } else {
            return false;
        }
    }

    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //System.out.println(arr[i]);
            Log.d("TAG", "print array : " + arr[i]);
        }
    }

}