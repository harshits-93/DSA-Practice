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
        /*int[] arr1 = {1, 3, 5, 7, 9,11, 15};
        int[] arr2 = {2, 4, 6, 8, 10};*/
        //bubbleSort(arr, 5);
        //selectionSort(arr);
        //insertionSort(arr);

        // mergeTwoSortedArrays(arr1, arr2);
      /*  int[] sorted = mergeSort(arr, 0, arr.length - 1);
        Log.d("TAG", "Merge Sort: " + Arrays.toString(sorted));
*/
        String s = "i.like.this.program.very.much";
        String result = reverseWords(s);
        Log.d("TAG", "reverseWords: " + result);

    }

    String reverseWords(String S) {
        // code here
        String[] split = S.split("\\.");
        int i = 0;
        int j = split.length - 1;

        while (i < j) {
            //swap
            String temp = split[i];
            split[i] = split[j];
            split[j] = temp;
            i++;
            j--;
        }

        //.join method to add dots in b/w reversed array.
        return String.join(".", split);
    }

    void mergeSortAlt(int arr[], int l, int r) {
        /** The base case in the recursion ensures that when the subarray has only one element (i.e., when l == r),
         the function does nothing and returns immediately. When the base case is met, the recursion
         stops dividing that part of the array further and starts merging back the sorted subarrays.*/
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSortAlt(arr, l, mid);
            mergeSortAlt(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }

    void merge(int arr[], int l, int m, int r) {
        /**  m - l + 1 calculates the number of elements in the left subarray.
         * For example, if l = 0 and m = 2, the left subarray includes indices 0, 1, 2, so n1 = 2 - 0 + 1 = 3.
         *
         * r - m calculates the number of elements in the right subarray.
         * For example, if m = 2 and r = 4, the right subarray includes indices 3, 4, so n2 = 4 - 2 = 2.
         **/
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] left = new int[n1];
        int[] right = new int[n2];

        // Copy data to temporary arrays left[] and right[]
        for (int i = 0; i < n1; ++i)
            left[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            right[j] = arr[m + 1 + j];

        // Merge the temporary arrays

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of left[], if any
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }

        // Copy the remaining elements of right[], if any
        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    private int[] mergeSort(int arr[], int l, int r) {
        /*Given an array arr[], its starting position l and its ending position r. Sort the array
        using merge sort algorithm.*/

        //code here

        //base case/ exit condition
        //Here is l and r are same means only one item left and both pointing to same. So basically
        //we are creating an array of length one, and adding that only element in it and return it.
        //Note e[0] = arr[l] or e[0] = arr[r] both same, since l, r pointing to same place.
        if (l == r) {
            int[] e = new int[1];
            e[0] = arr[l];
            return e;
        }

        int mid = (l + r) / 2;
        int[] firstSortedHalf = mergeSort(arr, l, mid);
        int[] secondSortedHalf = mergeSort(arr, mid + 1, r);

        return mergeTwoSortedArrays(firstSortedHalf, secondSortedHalf);

    }

    private int[] mergeTwoSortedArrays(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        int k = 0;

        int[] result = new int[a.length + b.length];

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                result[k] = a[i];
                i++;
            } else {
                result[k] = b[j];
                j++;
            }
            k++;
        }

        while (i < a.length) {
            result[k] = a[i];
            i++;
            k++;
        }
        while (j < b.length) {
            result[k] = b[j];
            j++;
            k++;
        }

        Log.d("TAG", "Sorted array: " + Arrays.toString(result));


        return result;
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

        Log.d("TAG", "insertionSort: " + Arrays.toString(arr));
        //print(arr);

    }

    private void selectionSort(int[] arr) {
        // say we have 5 elements, total iteration would be 4. In first iteration, smallest element
        // will be at first position, in second iteration, we will start from 2nd position and at
        //the end of it, smallest of current iteration will be at 2nd position and so on.
        // i will the element which we are considering in current iteration and at the end of iteration,
        // it will represent smallest element, min is used to track current minimum of the iteration,
        // initially it is equal to i , i.e. at starting position, j denotes current element with
        //which we comparing minimum, j always starts from i+1, i.e. one element ahead of i.

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < arr.length; j++) {
                // if our j element is smaller than min, then our min will become j, i.e. now j and
                //min are at same position
                if (isSmaller(arr, j, min)) {
                    min = j;
                }
            }
//Finally when one iteration is over, we swap our min with i which at starting position, with this
            //operation, minimum of the iteration will be at first position, and we will start
            //next iteration without touching it.
            swap(arr, min, i);
        }

        print(arr);
    }

    private void bubbleSort(int[] arr, int n) {

        if (n > 1) {
            int numOfIteration = arr.length - 1;
            for (int i = 0; i < numOfIteration; i++) {
                for (int j = 0; j < numOfIteration - i; j++) {
                    if (isSmaller(arr, j + 1, j)) {
                        swap(arr, j + 1, j);
                    }
                }
            }
        }


        print(arr);

    }

    // used for swapping ith and jth elements of array
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping " + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // return true if ith element is smaller than jth element
    public static boolean isSmaller(int[] arr, int i, int j) {
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