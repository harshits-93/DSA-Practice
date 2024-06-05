package com.dsapractice;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class RecursionActivity extends AppCompatActivity {

    private static final String TAG = "RecursionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recursion);

        //printDecreasing(5);
        printDecreasingIncreasing(5);

        int i = printFactorial(4);
        Log.d(TAG, "factorial : " + i);

        int i1 = calculatePower(4, 3);
        Log.d(TAG, "power : " + i1);

        long l = calculatePowerLogarithmic(12, 21);
        Log.d(TAG, "power logarithmic: " + l);

        int n = 2;
        printZigZag(n);
        int[] arr = {10, 20, 30, 40, 50};
        int[] arrMax = {7, 12, 30, 0, 3};
        displayArray(arr, 0);
        displayArrayReverse(arr, 0);

        int maxOfArray = maxOfArray(arrMax, 0);
        Log.d(TAG, "maxOfArray: " + maxOfArray);

        int firstOccurrenceIndex = findFirstOccurrenceIndex(arr, 0, 30);
        Log.d(TAG, "findFirstOccurrenceIndex: " + firstOccurrenceIndex);

        int lastOccurrenceIndex = lastOccurrenceIndex(arr, 0, 30);
        Log.d(TAG, "lastOccurrenceIndex: " + lastOccurrenceIndex);

        int[] allIndices = findAllIndices(arr, 0, 30, 0);
        Log.d(TAG, "allIndices arr: " + Arrays.toString(allIndices));

    }

    private long calculatePowerLogarithmic(int x, int n) {

        // This approach is much more efficient than previous approach of calculating power, previous
        //approach time complexity is n, whereas this this has logn.

        if (n == 0) {
            return 1;
        }

        long xrpn = calculatePowerLogarithmic(x, n / 2);
        long xn = xrpn * xrpn;

        //if n is odd, i.e. power is odd, then we need to multiply with x one more time.
        if (n % 2 == 1) {
            xn = xn * x;
        }

        return xn;

    }

    private long power(int N, int R) {
        https:
//www.geeksforgeeks.org/problems/power-of-numbers-1587115620/1?page=1&category=Recursion&sortBy=submissions

        if (R == 0) {
            return 1;
        }

        long xrpn = calculatePowerLogarithmic(N, R / 2);
        long xn = (xrpn * xrpn) % 1000000007;

        //if n is odd, i.e. power is odd, then we need to multiply with x one more time.
        if (R % 2 == 1) {
            xn = (xn * N) % 1000000007;
        }

        return xn;
    }


    //Here fsf means found so far i.e. no. of times the n is found in arr.
    //While going upwards we check arr element with n, and if found equals we
    //increment fsf also along with idx, Now while coming back from top to bottom
    // at last arr index we return an array of size fsf, and then coming back downwards
    //we fill that arr with indexess of arr  where n  was found.

    private int[] findAllIndices(int[] arr, int idx, int n, int fsf) {

        if (arr.length == idx) {
            return new int[fsf];
        }

        if (arr[idx] == n) {
            int[] resultArr = findAllIndices(arr, idx + 1, n, fsf + 1);
            resultArr[fsf] = idx;
            return resultArr;
        } else {
            int[] resultArr = findAllIndices(arr, idx + 1, n, fsf);
            return resultArr;
        }
    }

    private int lastOccurrenceIndex(int[] arr, int idx, int n) {
        if (idx == arr.length) {
            return -1;
        }

        int i = lastOccurrenceIndex(arr, idx + 1, n);
        if (i == -1) {

            if (arr[idx] == n) {
                return idx;
            } else {
                return -1;
            }
        } else {
            return i;
        }
    }

    private int findFirstOccurrenceIndex(int[] arr, int idx, int n) {
        if (idx == arr.length) {
            return -1;
        }

        if (arr[idx] == n) {
            return idx;
        } else {
            int firstOccurrenceIndex = findFirstOccurrenceIndex(arr, idx + 1, n);
            return firstOccurrenceIndex;
        }
    }

    private int maxOfArray(int[] arr, int idx) {
        if (idx == arr.length - 1) {
            return arr[idx];
        }

        int internalMax = maxOfArray(arr, idx + 1);
        if (internalMax > arr[idx]) {
            return internalMax;
        } else {
            return arr[idx];
        }
    }

    private void displayArrayReverse(int[] arr, int idx) {
        if (idx == arr.length) {
            return;
        }
        displayArrayReverse(arr, idx + 1);
        Log.d(TAG, " " + arr[idx]);
    }

    private void displayArray(int[] arr, int idx) {
        if (idx == arr.length) {
            return;
        }
        Log.d(TAG, " " + arr[idx]);
        displayArray(arr, idx + 1);
    }




    private void printZigZag(int n) {

        // This question is made to understand the internal flow of recursion and stack state at
        // each step,  we can easily analyse it when n is smaller, but for bigger values of n it is
        // time consuming to draw stack using dry run and check flow, for this we have euler's path
        // which exactly tells/depicts the required steps and stack content  at each step.
        /**For reference how eular path is drawn and how it works check this video from  20:05
         * https://www.youtube.com/watch?v=R7qja_gZrvI&list=PL-Jc9J83PIiFxaBahjslhBD1LiJAV7nKs&index=10
         *
         * When only one recursion call is there, we have 2 regions, Ist region is all the code above
         * call, and 2nd is all the code below call. But when 2 recursions calls are there it divide
         * the area into 3 regions namely pre, in and post.
         * These 2 call are call Left call(Ist recursion call) and right call(2nd recursion call)
         * pre region is when no left call or right call executed
         * in region means left call already executed but right call is made yet, in b/w code is
         * executed for in area
         * post region executes when both left and right call executed
         *
         * In eular path these call are represent by lines.
         */
        if (n == 0) {
            return;
        }

        Log.d(TAG, "Pre : " + n);
        printZigZag(n - 1);
        Log.d(TAG, "In : " + n);
        printZigZag(n - 1);
        Log.d(TAG, "Post : " + n);

    }

    private int calculatePower(int x, int n) {

        if (n == 0) {
            return 1;
        }

        return x * calculatePower(x, n - 1);
    }

    private void printDecreasingIncreasing(int n) {
        //Exit condition
        if (n == 0) {
            return;
        }

        /*
         * Remember code above recursive call will run from bottom to top in stack
         * meaning all lines above recursive call will execute first and then new call made.
         * But  all lines below recursive call will run from top to bottom in stack,
         * meaning all recursive call will be done and once the exit condition hit, then from
         * there moving backwards the lines are executed.
         * */
        Log.d(TAG, "" + n);
        printDecreasingIncreasing(n - 1);          // our recursive call
        Log.d(TAG, "" + n);
    }

    private int printFactorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * printFactorial(n - 1);
    }

    private void printDecreasing(int n) {
        if (n == 0) {
            return;
        }
        Log.d(TAG, "printDecreasing: " + n);
        printDecreasing(n - 1);
    }
}