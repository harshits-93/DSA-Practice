package com.dsapractice;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayActivity extends AppCompatActivity {

    private static final String TAG = "Tag_arr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);
        int arr[] = {1, 2, 3, 4, 5, 0, 7};

        reverseWord("Geeks");

        //Here k is the no. of elements  we want to rotate, it's range is -infinity to +infinity
        // negative k means to rotate array in clockwise direction and positive means for anti-clockwise.
        int k = -8;
       /* digitFrequency();
        spanOfArray();*/
        //barChart(arr);
        Log.d(TAG, "sumOfArray: " + sumOfArray());
        reverseArray(arr, 0, arr.length - 1);
        rotateArrayByOne();

        rotateArrayClkwiseOrAntiClkWise(arr, k);
        //meaning if at 0th index, value is 3 , then in inverse array, at 3th index, the value should be 0
        // int[] result = inverseArray(arr);

        /*printAllSubArray(arr);
        printAllSubsetOfArray(arr);
*/
     /*   binarySearch(arr, 2);
        ceilAndFloor(arr, 2);
        //printArray(result);

        int[] arr1 = {1, 2, 0, 2};
        Log.d(TAG, "findDuplicatesInArray: " + findDuplicatesInArray(arr1, 4));*/

        moveNegativeToOneSide();
        unionAndIntersection();
        sort01();
        sort012elements();
        maximumSumSubArrayOrKadaneAlgo();


    /*    int[] arr2 = {1, 2, 3, 7, 5};
        ArrayList<Integer> result = subArraySum(arr2, 5, 12);
        Log.d(TAG, "result : " + result);

        int a3[] = {1, 2, 3, 5};
        int i = missingNumber(a3, 5);
        Log.d(TAG, "missing number  : " + i);*/

    }

    private ArrayList<Integer> sumOfArray() {
        /*https://www.youtube.com/watch?v=BzJK1zNXwcU&list=PL-Jc9J83PIiHOV7lm2uSw4ZiVsIRsGS6r&index=26*/
        int a[] = {1, 5, 2};
        int b[] = {9, 4, 2};

        ArrayList<Integer> result = new ArrayList<>();

        if (a.length > 0 && b.length > 0) {
            int[] sum = new int[a.length > b.length ? a.length : b.length];

            int i = a.length - 1;
            int j = b.length - 1;
            int k = sum.length - 1;
            int c = 0;
            int d = 0;

            while (k >= 0) {
                d = c;

                if (i >= 0) {
                    d += a[i];
                }
                if (j >= 0) {
                    d += b[j];
                }

                c = d / 10;
                d = d % 10;

                sum[k] = d;

                i--;
                j--;
                k--;
            }

            for (int value : sum) {
                result.add(value);
            }

            if (c != 0) {
                result.add(0, c);
            }
        }


        return result;
    }

    public static ArrayList<Integer> findDuplicatesInArray(int arr[], int n) {
        //Note this solution has O(N) time complexity and O(1) as space complexity.
        // this is when input range is b/w 0 to N-1

        ArrayList<Integer> duplicates = new ArrayList<>();

        if (arr.length > 0) {

            //
            //This solutions works when given input range is 0 to N-1, Steps:
            /*
             * 1 - Traverse the given array from i = 0 to n -1 elements
             * Go to index arr[i]%n and increment its value by n.
             * 2 - Now traverse the array again and print all those
             * elements at indexes i for which arr[ i]/n is greater
             * than 2.
             *
             * This approach works because all elements are in range
             * from 0 to n-1 and arr[ i]/n would be greater than 1
             * only if a value "i" has appeared more than once.
             */

            for (int i = 0; i < n; i++) {
                int idx = arr[i] % n;
                arr[idx] += n;
            }

            for (int i = 0; i < n; i++) {
                if ((arr[i] / n) >= 2) {
                    duplicates.add(i);
                }
            }

           /* //When given input range is 1 to N, then below given solution is the best with time
            // complexity = n and space complexity = 1, but it doesn't work if it input contain zero.

            //Steps
            *//*
             * 1.) Starting from first index, check value of array at that index, note here we are taking
             *   absolute value of tat element as it can be already marked as negative.
             * 2.) Subtract 1 from that value, store it as index, and mark the element present in that
             *     index as -ve.
             * 3.) If while iterating we found any element values as -ve means duplicate is there and
             *     we just add that in our answer list. else we just mark it negative.
             * 4.) Continue same process till end.
             * *//*

            //if any confusion check this --> https://www.youtube.com/watch?v=YoPx8sG_a7E

            for (int i = 0; i < arr.length; i++) {
                int idx = Math.abs(arr[i]) - 1;

                int value = arr[idx];
                if (value < 0) {
                    //Already marked -ve and duplicate found, so add this in result
                      duplicates.add(idx + 1);
                } else {
                    //Marking as -ve
                    arr[idx] *= -1;
                }

            }
*/


        }


        if (duplicates.size() == 0) {
            duplicates.add(-1);
        }
        Log.d(TAG, "divide : " + 2 % 4 + " " + 3 % 4 + " " + 0 % 4);
        return duplicates;


    }

    //Function to find a continuous sub-array which adds up to a given number.
    //https://www.youtube.com/watch?v=20v8zSo2v18 --> note in this video, it return total possible
    // no. of subarrays which matches given s.
    private ArrayList<Integer> subArraySum(int[] arr, int n, int s) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, sum = 0;
        if (s == 0) {
            result.add(-1);
            return result;
        }

        for (int j = 0; j < arr.length; j++) {
            sum += arr[j];
            // If sum is less than s then it will automatically increased as loop progress.
            //If found greater we will reduce it till it becomes == or < s, if equals then
            // add required indexes and return list otherwise continue till end.
            while (sum > s) {
                sum -= arr[i];
                i++;
            }

            if (sum == s) {
                result.add(i + 1);
                result.add(j + 1);
                return result;
            }

        }
        result.add(-1);
        return result;
    }

    private int missingNumber(int array[], int n) {
        // Your Code Here
        int sumOfNaturalNum = (n * (n + 1)) / 2;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        return sumOfNaturalNum - sum;

    }

    private void rotateArrayClkwiseOrAntiClkWise(int[] arr, int k) {
        //This algo will work for all direction and all count rotate.like 1-step, 2-step etc.
        /*Steps
         *  1.) Take modulus of k and arr length i.e. k%arr.length , then we will divide array into 2 parts:
         *   a.) From 0 index to array length-k-1 , this portion is first half
         *   b.) From  array length-k to array length-1
         * 2.) Reverse first and second portion(note it is reverse not rotate)
         * 3.) Reverse complete array(first + second)
         * 4.) Just after modulus --> Check if k is negative , if yes, then add arr.length to k.
         *
         * */

        k = k % arr.length;
        if (k < 0) {
            //Say we have an array of length 5, we are adding array length to -ve k because array rotated four places i.e k = 4 is same
            // as array rotated by one -ve place i.e k = -1. so if we add array length 5 to -1 , then its result is 4 -> -1 +5 = 4
            // this will work for any -ve number.
            k = k + arr.length;
        }

        //Ist portion reverse
        reverseArray(arr, 0, arr.length - k - 1);

        //IInd portion reverse
        reverseArray(arr, arr.length - k, arr.length - 1);

        //Final step -> complete reverse
        reverseArray(arr, 0, arr.length - 1);


        printArray(arr);
    }

    private void ceilAndFloor(int[] arr, int inputNum) {
        int low = 0;
        int high = arr.length - 1;

        int floor = 0;
        int ceil = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (inputNum == arr[mid]) {
                Log.d(TAG, "binarySearch: " + mid);
                return;
            } else if (inputNum > arr[mid]) {
                low = mid + 1;
                floor = arr[mid];
            } else {
                high = mid - 1;
                ceil = arr[mid];
            }
        }

        Log.d(TAG, "ceil :" + ceil + " floor :" + floor);
    }

    private void binarySearch(int[] arr, int elementToSearch) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (elementToSearch == arr[mid]) {
                Log.d(TAG, "binarySearch: " + mid);
                return;
            } else if (elementToSearch > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        Log.d(TAG, "binarySearch: " + -1);

    }

    private void printAllSubsetOfArray(int[] arr) {
        /*
         * Total subsets of array of size n is 2 to the power n. so for size 3, it would be 8 subsets.
         * Its the binary representation of number , like 00,01,10,11 for size 2 array. So where we have
         * 1 ,we will print that element and for zero we will ignore or print - for it.
         *
         */

        /*how many times the first loop will run  ==  no. of subsets which is 2 to the power n*/

        int totalNumOfSubsets = (int) Math.pow(2, arr.length);
        for (int i = 0; i < totalNumOfSubsets; i++) {
            // Here we will take i value and convert it to binary, use 0's and 1's to know if we
            //want to print element or not, for 1 print element, for 0 don't print.
            //this loop will run that many times which we want the bits in one subset , here it is
            // 3 i.e - - -, equal to size of array. We will run the loop from end to maintain the print order.

            //To find binary of any number, we divide it by 2, and check remainder. Example
            // 2|_6__
            // 2|_3__  --> 0 -->this is remainder
            // 2|_1__  --> 1 -->this is remainder
            // 2|_0__  --> 1 -->this is remainder
            // Print from bottom to top i.e 110 --> which is binary of 6, hence we start loop from
            // end instead od start.

            String mySubset = "";
            int temp = i;

            for (int j = arr.length - 1; j >= 0; j--) {

                int remainder = temp % 2;
                temp = temp / 2;

                if (remainder == 0) {
                    mySubset = "_" + "\t" + mySubset;
                } else {
                    mySubset = arr[j] + "\t" + mySubset;
                }
            }

            Log.d(TAG, "printAllSubsetOfArray: " + mySubset);

        }

    }

    private void printAllSubArray(int[] arr) {
        //To find sub array from given input means like this
        /*
         * arr[3] = { a b c}
         * For this input the total subarrays would be:
         *  a
         *  a b
         *  a b c
         *  b
         *  b c
         *  c
         *
         * */

        // This pick starting point
        for (int i = 0; i < arr.length; i++) {
            // Pick ending point
            for (int j = i; j < arr.length; j++) {
                // Print subarray between current starting
                // and ending points
                for (int k = i; k <= j; k++) {
                    Log.d(TAG, "printAllSubArray: " + arr[k] + "\n");
                }
                System.out.println();
            }
        }
    }

    private int[] inverseArray(int[] arr) {
        int[] resultArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            resultArr[arr[i]] = i;
        }
        return resultArr;
    }

    private void printArray(int[] arr) {
        Log.d(TAG, "printArray: " + Arrays.toString(arr));
    }

    private void swap(int i, int j, int[] arr) {
        int temp = 0;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void reverseArray(int[] arr, int l, int h) {
        int low = l;
        int high = h;
        if (arr.length > 1) {
            while (low < high) {
                swap(low, high, arr);
                low++;
                high--;
            }
        }
    }

    private void rotateArrayByOne() {
        int arr[] = {2, 3, 4, 1, 5, 67};

        //Cyclic or clockwise rotate or right shift by one
        int x = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = x;


        //Anti- Clockwise rotate or left shift by one
        int y = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = y;
        printArray(arr);

    }


    private void barChart(int[] arr) {
        //Firstly finding max here, this will gives the no. of lines or rows = no of times outer loop will run
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] >= i) {
                    System.out.print("*\t");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    private void spanOfArray() {
        //Span of array is difference b/w max value and min value.
        int arr[] = {3, 23, 4, 5, 7, 8, 2, 76, 1, 9};
        int max = arr[0], min = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }

            if (arr[i] < min) {
                min = arr[i];
            }
        }

        int span = max - min;

        Log.d(TAG, "spanOfArray: maximum : " + max + " minimum : " + min + " Span : " + span);

    }

    private void digitFrequency() {
        int arr[] = {1, 23, 4, 5, 7, 8, 0, 0, 1, 9};

        int count = 0;
        int num_whose_frequency_is_checked = 4;

 /*
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num_whose_frequency_is_checked) {
                count++;
            }
        }
*/

        //or

        int n = 24342367;
        while (n > 0) {
            //Here digit will contain the remainder, basically we are dividing number by 10 and checking remainder value,
            //if it matches remainder, increase count or frequency.
            int digit = n % 10;
            n = n / 10;

            if (digit == num_whose_frequency_is_checked) {
                count++;
            }
        }

        Log.d(TAG, "digitFrequency: " + count);
    }

    private void sort01() {
        /*
         * Here we are using partitioning technique where
         * (i to end [Unknowns]) --> initially all elements are unknown i.e from 0 to length-1
         * (0 to j-1) --> area of 0's
         * (j to i-1) --> area of 1's
         *
         * Note: As i value increases the unknown space reduces.
         * */
        int arr[] = {0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0};

        int i = 0, j = 0;

        while (i < arr.length) {
            if (arr[i] == 1) {
                i++;
            } else {
                swap(i, j, arr);
                i++;
                j++;
            }
        }

        Log.d("TAG_DS", "sort01: " + Arrays.toString(arr));

    }

    private void sort012elements() {
        /*
         * Here we are using partitioning technique where
         * (i to k [Unknowns]) --> initially all elements are unknown i.e from 0 to length-1
         * (0 to j-1) --> area of 0's
         * (j to i-1) --> area of 1's
         * (k+1 to end) --> area of 2's
         * here k is flowing from right to left.
         *
         * */

        int arr[] = {2, 2, 0, 1, 1, 0, 2, 1, 0, 0, 0, 1, 2, 0, 1, 2, 0};

        int i = 0, j = 0, k = arr.length - 1;

        while (i <= k) {
            if (arr[i] == 1) {
                i++;
            } else if (arr[i] == 0) {
                swap(i, j, arr);
                i++;
                j++;
            } else {
                swap(i, k, arr);
                k--;
            }
        }

        Log.d("TAG_DS", "sort012: " + Arrays.toString(arr));

    }

    private void maximumSumSubArrayOrKadaneAlgo() {
        /* https://www.youtube.com/watch?v=VMtyGnNcdPw
         * Here we take 2 things current subarray sum and overall largest sum till each step.
         *  Initially both are same.
         *
         *  1.) While looking at our current element, we check if prev sum or current sum is -ve or
         *   +ve. Why ? Because if current/prev sum is -ve, means if we add any number to that whether
         *   it is +ve or -ve, it will always be lesser than our current element, hence we are starting
         *   new subarray/ train (lol) from there. If prev sum or current sum is +ve, then we just
         *   add current element to current sum. Why? Because if prev/current sum values is +ve ,then
         *   if we add current element to that sum, the total would always be greater than our current
         *   element.
         *
         * 2.) For each element we check, if current sum > overall sum or not, if yes we update
         * overall sum with current as it is the highest.
         *
         * */


        int arr[] = {2, -3, 4, -1, 5, 6, -7, 2, 9};
        int currentSum = arr[0], overallSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (currentSum >= 0) {
                currentSum += arr[i];
            } else {
                currentSum = arr[i];
            }
            if (currentSum > overallSum) {
                overallSum = currentSum;
            }
        }
        Log.d("TAG_DS", "largestSumSubArrayOrKadaneAlgo: " + Arrays.toString(arr));
    }


    private void moveNegativeToOneSide() {

        //int arr[] = {2, -4, 23, 4, 1, -4, -7, -8};
        int arr[] = {-5, 7, -3, -4, 9, 10, -1, 11};
        int left = 0;
        int right = arr.length - 1;

        /*while (left <= right) {
            if (arr[left] < 0 && arr[right] < 0) {
                left++;
            } else if (arr[left] > 0 && arr[right] < 0) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                left++;
                right--;
            } else if (arr[left] > 0 && arr[right] > 0) {
                right--;
            } else {
                left++;
                right--;
            }
        }*/
//        Log.d("TAG_DS", "moveNegative: " + Arrays.toString(arr));

        while (left <= right) {
            if (arr[left] < 0 && arr[right] < 0) {
                right--;
            } else if (arr[left] > 0 && arr[right] < 0) {
                left++;
                right--;
            } else if (arr[left] > 0 && arr[right] > 0) {
                left++;
            } else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        Log.d("TAG_DS", "moveNegative: " + Arrays.toString(arr));

    }

    public  String reverseWord(String str) {

        if(str.isEmpty()){
            return "";
        }
        else{
            char[] chars = str.toCharArray();

            int l = 0;
            int r = chars.length - 1;

            while (l <= r) {
                //swapping
                char temp = chars[l];
                chars[l] = chars[r];
                chars[r] = temp;
                l++;
                r--;
            }

            Log.d(TAG, "reverseWord: "+ String.valueOf(chars));

            return String.valueOf(chars);
        }

    }

    private void unionAndIntersection() {
        int arr1[] = {1, 2, 3, 4, 5};
        int arr2[] = {1, 2, 3};
        int arr[] = new int[arr1.length + arr2.length];

        Log.d("TAG_DS", "unionAndIntersection: " + arr.length + Arrays.toString(arr));

        for (int i = 0; i < arr2.length; i++) {

        }
    }

  /*  private int getPairsCount(int[] arr, int n, int k) {
        // code here
    }*/

}