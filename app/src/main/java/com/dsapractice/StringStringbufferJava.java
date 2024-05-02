package com.dsapractice;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class StringStringbufferJava extends AppCompatActivity {

    private static final String TAG = "StringStringbufferJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_stringbuffer_java);

        // printAllPalindromicSubstring("abccbc");
        char[] ch = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        String result = stringCompression(ch);
        Log.d(TAG, "stringCompression: " + result);

        String arr[] = {"geeksforgeeks", "geeks", "geek", "geezer"};
        String lcp = longestCommonPrefix(arr, arr.length);
        Log.d(TAG, "longestCommonPrefix : " + lcp);
    }

    private String longestCommonPrefix(String arr[], int n) {
        char currentCh = 0;
        StringBuilder sb = new StringBuilder();

        //First we have to find smallest string in arr.
        if (arr.length > 1) {
            int smallestStrLength = arr[0].length();

            for (int i = 1; i < arr.length; i++) {
                if (arr[i].length() < smallestStrLength) {
                    smallestStrLength = arr[i].length();
                }
            }

            //Here we get the smallest string length, we will loop to the length of this string,
            //then, for each iteration we check index of each string of the arr and this index of each string
            // is decided based on our outer loop index

            outer:
            for (int i = 0; i < smallestStrLength; i++) {

                for (int j = 0; j < arr.length; j++) {
                    String currentStr = arr[j].trim();
                    if (j == 0) {
                        currentCh = currentStr.charAt(i);
                    } else {
                        if (currentStr.charAt(i) != currentCh) {
                            break outer;
                        } else {
                            //checking if its the last string which has same character as all others,
                            //then we will add it to stringbuilder.
                            if (j == arr.length - 1) {
                                sb.append(currentCh);
                            }
                        }
                    }
                }
            }


            if (sb.length() > 0) {
                return sb.toString();
            } else {
                return "-1";
            }
        }else if(arr.length == 1){
            return arr[0];
        }
        else{
            return "-1";
        }
    }

    private String stringCompression(char[] chars) {
        String resultStr = "";
        int count = 1;

        if (chars.length >= 1) {
            StringBuffer sb = new StringBuffer();
            sb.append(chars[0]);
            for (int i = 1; i < chars.length; i++) {
                char currentCh = chars[i];
                char prevCh = chars[i - 1];

                if (currentCh == prevCh) {
                    count++;
                }

                if (currentCh != prevCh) {
                    if (count > 1) {
                        sb.append(count);
                        count = 1;
                    }
                    sb.append(currentCh);
                }
            }

            if (count > 1) {
                sb.append(count);
                count = 1;
            }
            resultStr = sb.toString();

        }

        return resultStr;
    }

    private void printAllPalindromicSubstring(String str) {
        for (int i = 0; i < str.length(); i++) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str.charAt(i));
            Log.d(TAG, "This is palindrome : " + stringBuffer.toString() + "\n");
            for (int j = i + 1; j < str.length(); j++) {
                StringBuffer append = stringBuffer.append(str.charAt(j));
                StringBuffer stringBuffer1 = new StringBuffer(append);

                if (append.toString().equals(stringBuffer1.reverse().toString())) {
                    Log.d(TAG, stringBuffer.toString() + "\n");
                }
            }
        }

    }
}