package com.dsapractice;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class StringStringbufferJava extends AppCompatActivity {

    private static final String TAG = "StringStringbufferJava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_stringbuffer_java);

        String demo = "Ge1ek23s";
        String s = "geeks for geeks";
        String a = "defRTSersUXI";
        String duplicates = "zvvo";
        String S1 = "Geeks";
        String S2 = "forGeeks";


        /*Given a string S, check if it is palindrome or not.*/
        checkIfStringPalindrome(s);


        /**
         * Given a string S, find the longest palindromic substring in S. In case of conflict,
         * return the substring which occurs first ( with the least starting index ).*/
        //String S = "aaaabbaa";
        String S = "aaaabbaa";
        String s1 = longestPalindromicSubstring(S);
        Log.d(TAG, "onCreate: " + s1);


      /*  concatAndReverse(S1, S2);
        detectNumbers(demo);
        printFirstCharacterOfEachWord(s);
        removeDuplicatesFromString(duplicates);
        sortUpperCaseLowerCaseLexographically(a);
       // removeChars(string1, string2);
        countIfSomeElementIsPresentTwice();
        commonElements();
        numberCheck(9.378);*/


        // printAllPalindromicSubstring("abccbc");
       /* char[] ch = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        String result = stringCompression(ch);
        Log.d(TAG, "stringCompression: " + result);

        String arr[] = {"geeksforgeeks", "geeks", "geek", "geezer"};
        String lcp = longestCommonPrefix(arr, arr.length);
        Log.d(TAG, "longestCommonPrefix : " + lcp);*/
    }

    private String longestPalindromicSubstring(String S) {
        StringBuilder sb = new StringBuilder();

        String result = "";
        for (int i = 0; i < S.length(); i++) {
            for (int j = i; j < S.length(); j++) {

                for (int k = i; k <= j; k++) {
                    sb.append(S.charAt(k));
                }

                if (checkIfStringPalindrome(sb.toString()) == 1) {
                    if (result.length() < sb.toString().length()) {
                        result = sb.toString();
                    }
                }

                sb.setLength(0);
            }

        }

        return  result;

    }

    private int checkIfStringPalindrome(String s) {

        StringBuffer sb = new StringBuffer(s);
        if (s.equals(sb.reverse().toString())) {
            return 1;
        } else {
            return 0;
        }

    }

    public void concatAndReverse(String st1, String st2) {
        String s = st1 + st2;
        String r = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            r += s.charAt(i);
        }
        Log.d("TAG_D", "concatAndReverse: " + r.toString());
    }

    public void detectNumbers(String demo) {

        /*for (int i = 0; i < demo.length() - 1; i++) {
            if(Character.isDigit(demo.charAt(i))){

            }
        }*/

        String s = demo.replaceAll("[^0-9]+", " ");
        List<String> strings = Arrays.asList(s.trim().split(" "));


        Log.d("TAG_D", "concatAndReverse: " + strings.toString());
    }

    private String printFirstCharacterOfEachWord(String s) {
        String strToReturn = "";

        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            String currentStr = split[i];
            strToReturn += currentStr.charAt(0);
        }
        return strToReturn;

    }

    private void removeDuplicatesFromString(String duplicates) {
        String resultStr = "";

        LinkedHashSet<Character> linkedSet = new LinkedHashSet<>();

        for (int i = 0; i < duplicates.length(); i++) {
            linkedSet.add(duplicates.charAt(i));
        }
        ArrayList<Character> characters = new ArrayList<>(linkedSet);
        for (Character character : characters) {
            resultStr += character;
        }
        Log.d("TAG_D", "Result Linkedhashset : " + resultStr);
    }

    private void countIfSomeElementIsPresentTwice() {
        int elementsRepeatedTwice = 0;
        List<String> strings = Arrays.asList("Tom", "Jerry", "Thomas", "Tom", "Jerry");

        HashSet<String> set = new HashSet<>(strings);
        for (String s : set) {
            Log.d("TAG_d", s + "  repeated : " + Collections.frequency(strings, s) + " times");
            if (Collections.frequency(strings, s) == 2) {
                elementsRepeatedTwice += 1;
            }

        }
      /*  for (int i = 0; i < strings.size(); i++) {
            int frequency = Collections.frequency(strings, strings.get(i));

            Log.d("TAG_d", "frequency : " + i + "  is  " + frequency);
            if (frequency == 2) {
                elementsRepeatedTwice += 1;
            }
        }*/
        Log.d("TAG_d", "elementsRepeatedTwice : " + elementsRepeatedTwice);
    }


    private void removeChars(String string1, String string2) {
        String resultStr = "";

        ArrayList<Character> chars1List = new ArrayList<>();
        char[] chars = string1.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars1List.add(chars[i]);
        }

        ArrayList<Character> chars2List = new ArrayList<>();
        char[] chars2 = string2.toCharArray();
        for (int i = 0; i < chars2.length; i++) {
            chars2List.add(chars2[i]);
        }

        ArrayList<Character> removedChars = new ArrayList<>();

        for (int i = 0; i < chars2List.size(); i++) {
            char c = chars2List.get(i);
            for (int j = 0; j < chars1List.size(); j++) {
                if (c == chars1List.get(j)) {
                    removedChars.add(c);
                }
            }
        }

        chars1List.removeAll(removedChars);
        Log.d("TAG_d", "removeChars: " + chars1List.toString());

        for (Character character : chars1List) {
            resultStr += character;
        }


     /*   char[] chars = string1.toCharArray();
        HashSet hashSet = new HashSet();
        for (int i = 0; i < chars.length; i++) {
            hashSet.add(chars[i]);
        }

        char[] chars2 = string2.toCharArray();
        HashSet hashSet1 = new HashSet();
        for (int i = 0; i < chars2.length; i++) {
            hashSet1.add(chars2[i]);
        }*/

    }

    private void sortUpperCaseLowerCaseLexographically(String s) {
        String resultStr = "";

        ArrayList<Character> lwrCase = new ArrayList<>();
        ArrayList<Character> uprCase = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                uprCase.add(s.charAt(i));
            } else {
                lwrCase.add(s.charAt(i));
            }
        }

        Collections.sort(lwrCase);
        Collections.sort(uprCase);
        int j = 0;
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                resultStr += uprCase.get(j);
                j++;
            } else {
                resultStr += lwrCase.get(k);
                k++;
            }
        }

        Log.d("TAG_D", "sortUpperCaseLowerCaseLexographically: " + resultStr);
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
        } else if (arr.length == 1) {
            return arr[0];
        } else {
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

    private void commonElements() {
        ArrayList<Integer> v1 = new ArrayList<>();
        v1.add(1);
        v1.add(2);
        v1.add(4);
        v1.add(3);
        v1.add(2);
        ArrayList<Integer> v2 = new ArrayList<>();
        v2.add(7);
        v2.add(2);
        v2.add(2);
        v2.add(1);
        v2.add(8);
        /*1,2,4,3,2
        7,2,2,1,8*/


        ArrayList<Integer> v3 = new ArrayList<>(v1);
/*        v3.retainAll(v2);
        Log.d("TAG_D", "commonElements v1: " + v1.toString());
        Log.d("TAG_D", "commonElements v3: " + v3.toString());
*/

        for (int i = 0; i < v1.size(); i++) {
            if (v2.contains(v1.get(i))) {
                v3.add(v2.get(i));
            }
        }

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            List<Integer> collect = v1.stream().filter(v2::contains).collect(Collectors.toList());
        }*/


    }

    private void numberCheck(double n) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.d("TAG_d", "\n sign :" + Math.signum(n) + "\n Ceil : " + Math.ceil(n) + "\n Absolute : " + Math.abs(n) + "\n Floor : " + Math.floor(n)
                    + "\n Next down : " + Math.nextDown(n) + "\n Next up : " + Math.nextUp(n) + "\n FloorDiv : " + Math.floorDiv(5, 3)
                    + "\n Rint :" + Math.rint(n) + "\n Hypot " + Math.hypot(2, 3));
        }


        /*
        if (n % 5 == 0) {
            Log.d("TAG_d", "yes");
            System.out.print("yes");
        } else {
            Log.d("TAG_d", "no");
            System.out.print("no");
        }
*/
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