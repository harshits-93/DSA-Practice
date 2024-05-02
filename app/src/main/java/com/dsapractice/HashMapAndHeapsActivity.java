package com.dsapractice;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class HashMapAndHeapsActivity extends AppCompatActivity {

    private static final String TAG = HashMapAndHeapsActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_map_and_heaps);
        String str = "abracadabra";
        highestFrequency(str);

        int[] arr1 = {1, 1, 2, 2, 2, 3, 5};
        int[] arr2 = {1, 1, 1, 2, 2, 4, 5};

        HashSet<Integer> set = new HashSet<>();
        //Here finding common elements in both array.
        // getCommonElement1(arr1, arr2);

        getCommonElement2(arr1, arr2);

        int[] arr4 = {10, 5, 9, 1, 11, 8, 6, 15, 3, 12, 2};
        //all the sequence in this array are:
        /*
         * 1,2,3
         * 5,6
         * 8,9,10,11,12
         * 15
         * Out of all these four, 3rd one is the longest, hence we need to print that.
         * */
        longestConsecutiveSequence(arr4);

        // this method is valid only if arr is not sorted in ascending order, as for
        // time complexity will increase ti nlogn.
        //Here we need to print k largest element in ascending order, k= 3 means 3 largest elements
        int[] kLargestElements = findKLargestElements(arr4, 11, 3);
        Log.d(TAG, "kLargestElements: " + Arrays.toString(kLargestElements));

        int[] arr5 = {2, 3, 1, 4, 6, 7, 5, 8, 9};
        ArrayList<Integer> nearlySorted = nearlySortedArray(arr5, 2);
        Log.d(TAG, "nearlySorted: " + nearlySorted);

        heapSort(arr4);
    }

    private ArrayList<Integer> nearlySortedArray(int[] arr, int k) {
        //https://www.youtube.com/watch?v=pptk8cUHHUg&list=PL-Jc9J83PIiHq5rMZasunIR19QG3E-PAA&index=14
        /*
         * Nearly sorted here mean, each element is misplace from its correct position by +k or -k
         * positions, here +k means shifted to right, -k means shifted to left. Example k =2 ,
         * means each element can be misplace by 0 or +1 or +2 , or misplaced to left then -2 or -1
         * or 0
         * */

        /*
         * Here we are creating a funnel/filter which initially contains element upto k, as for Ist
         * element it can't be -ve. so filled initial values in funnel. After starting from k+1
         * elements till array end, we remove current peek from filter and add new element in filter,
         * so size of filter remain k, each element coming out is
         * */
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            pq.add(arr[i]);
        }

        for (int i = k + 1; i < arr.length; i++) {
            result.add(pq.remove());
            pq.add(arr[i]);
        }

        while (pq.size() > 0) {
            result.add(pq.remove());
        }

        return result;
    }

    private void heapSort(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }

        while (pq.size() > 0) {
            Log.d(TAG, "heap sort: " + pq.peek());
            pq.remove(pq.peek());
        }
    }

    private int[] findKLargestElements(int[] arr, int n, int k) {
        // https://www.youtube.com/watch?v=taL2G6jDLog&list=PL-Jc9J83PIiHq5rMZasunIR19QG3E-PAA&index=12
        //Here we are using Priority Queue, in normal queue FIFO follow, but in priority
        //queue, we can give priority to elements, by default the highest priority is given to
        // smaller elements, we can change it to higher elements as well.

        //Note: Time complexity of various methods
        // peek() has O(1), add() and remove() has o(logn) complexity.

        // required space complexity - O(k), time complexity - O(nlogk)

        //just adding initial elements in pq till they are less k, less than because we are using
        // 0 index as well , then after that, check if current element is greater than current peek
        // which is usually the lowest elements among the 3 as k =3 here. if greater number found
        // remoce peek element and add current one, so that count remain 3 only.
        PriorityQueue<Integer> pq = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            pq = new PriorityQueue<>();
        }
        ArrayList<Integer> al = new ArrayList<>(k);
        int[] result = new int[k];

        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                pq.add(arr[i]);
            } else {
                if (arr[i] > pq.peek()) {
                    pq.remove(pq.peek());
                    pq.add(arr[i]);
                }
            }
        }

        while (pq.size() > 0) {
            al.add(pq.peek());
            pq.remove(pq.peek());
        }
        Collections.reverse(al);
        for (int i = 0; i < al.size(); i++) {
            result[i] = al.get(i);
        }

        return result;
    }

    private void longestConsecutiveSequence(int[] arr) {
        // https://www.youtube.com/watch?v=YWXbu5uyGXs&list=PL-Jc9J83PIiHq5rMZasunIR19QG3E-PAA&index=9

        //Here we are creating an hashmap with key as our array element and value as boolean,
        //for Ist loop, we are storing true value for all element in hashmap.Meaning all are
        //starting point of some sequence which is not correct for final solution

        //Then in second loop for each element we check if there is any element present in hashmap
        // which is one less than current element, if present,then we know current element can't
        // be the starting point of any sequence, so marking its value as false. By the end of this
        // loop we have only those element value marked as true which are actually the starting
        // of some sequence.

        //For 3rd loop, we run loop only for those element which are marked as true, in we initally
        // current limit to 1 as one element is there, and currentSeqnStartPoint which is the key
        // itself of ongoing element, if we found element after currentSeqnStartPoint in hashmap,
        //we keep on increasing the limit by one. for example for 1 2 3, at end of while we have
        //currentSeqnStartPoint = 1 and currentLimit = 3. At end we check if our currentLimit is
        //greater than max limit,if yes, we update max limit and maxstartpoint.

        //Note: for 1 and 2 loop time complexity will n + n = n only, but for 3rd loop it looks
        //like it is running n*n which actually it is also running n times only. Because
        //while doesn't run for each element and even if we calculate total no. of time the
        //while loop runs by adding each true values, its addition is = n only
        //hence n+n+n = O(n)

        //Finally printing the sequence using these variables
        HashMap<Integer, Boolean> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], true);
        }

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i] - 1)) {
                map.put(arr[i], false);
            }
        }

        int maxSeqnStartPoint = 0;
        int maxLimit = 0;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i]) && map.get(arr[i])) {
                int currentSeqnStartPoint = arr[i];
                int currentLimit = 1;

                while (map.containsKey(currentSeqnStartPoint + currentLimit)) {
                    currentLimit++;
                }

                if (currentLimit > maxLimit) {
                    maxSeqnStartPoint = currentSeqnStartPoint;
                    maxLimit = currentLimit;
                }
            }
        }

        for (int i = maxSeqnStartPoint; i < maxSeqnStartPoint + maxLimit; i++) {
            Log.d(TAG, "longestConsecutiveSequence: " + i);
        }
    }

    private void getCommonElement1(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr1.length; i++) {
            // Checking if hashmap already contains the key, if yes, then increasing its
            // frequency by one,else simply putting that value in hashmap
            if (map.containsKey(map.get(arr1[i]))) {
                map.put(arr1[i], map.get(arr1[i]) + 1);
            } else {
                map.put(arr1[i], 1);
            }
        }
// Here picking elements for second array one by one and checking for each element if it is
// present in hashmap, if present  delete that entry from hashmap and print it, continue till end.
        for (int i = 0; i < arr2.length; i++) {
            if (map.containsKey(arr2[i])) {
                Log.d(TAG, "getCommonElement: " + arr2[i]);
                map.remove(arr2[i]);
            }
        }
    }


    private void getCommonElement2(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr1.length; i++) {
            // Checking if hashmap already contains the key, if yes, then increasing its
            // frequency by one,else simply putting that value in hashmap
            if (map.containsKey(map.get(arr1[i]))) {
                map.put(arr1[i], map.get(arr1[i]) + 1);
            } else {
                map.put(arr1[i], 1);
            }
        }
        // Here picking elements for second array one by one and checking for each element if it is
        // present in hashmap, if present  checking frequency of it, once detected reducing
        // frequency by one, if frequency is 0 after reducing then only deleting that entry
        // from hashmap and printing it, continue till end.
        for (int i = 0; i < arr2.length; i++) {
            if (map.containsKey(arr2[i])) {
                Log.d(TAG, "getCommonElement: " + arr2[i]);
                map.put(arr2[i], map.get(arr2[i]) - 1);
                if (map.get(arr2[i]) == 0) {
                    map.remove(arr2[i]);
                }
            }
        }
    }

    private void highestFrequency(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.containsKey(c)) {
                map.put(c, map.get(c) != null ? map.get(c) + 1 : 1);
            } else {
                map.put(c, 1);
            }
        }

        Set<Character> keys = map.keySet();
        int max = 0;
        char maxFrequencyChar = s.charAt(0);
        for (Character key : keys) {
            if (map.get(key) > max) {
                max = map.get(key);
                maxFrequencyChar = key;
            }
        }

        Log.d(TAG, "highestFrequency: " + maxFrequencyChar);

    }
}