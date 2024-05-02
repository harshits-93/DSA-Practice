package com.dsapractice;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StackActivity extends AppCompatActivity {

    private static final String TAG = "StackActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);

        String s = "((a+b))+ (p+q)";
        //boolean result = duplicateBraces(s);
        //Log.d(TAG, "duplicateBraces : " + result);

        String s1 = "[{{a+b}+(a+b)}";
        boolean res = balancedBracket(s1);
        Log.d(TAG, "balancedBracket : " + res);

        long[] arr = {7, 8, 1, 4}/*{1, 3, 2, 4}*/;
        nextGreaterElementOnRight(arr);
        Log.d(TAG, "nextGreaterElementOnRight : " + Arrays.toString(nextGreaterElementOnRight(arr)));

        alternativeSolutionForNextGreaterElementOnRight(arr);
        Log.d(TAG, "Alternative nextGreaterElementOnRight : " + Arrays.toString(alternativeSolutionForNextGreaterElementOnRight(arr)));

        int[] arr1 = {19, 19, 19, 19, 19, 19, 19};
      /*  int[] nseor = nextSmallestElementOnRight(arr1);
        Log.d(TAG, "nextSmallestElementOnRight : " + Arrays.toString(nseor));*/

        //int[] nseol = nextSmallestElementOnLeft(arr1);
        //Log.d(TAG, "nextSmallestElementOnLeft : " + Arrays.toString(nseol));

        List<Integer> integers = nextSmallestElementOnLeft(arr1);
        Log.d(TAG, "nextSmallestElementOnLeft : " + integers);

        int[] price = {100, 80, 60, 70, 60, 75, 85};
        int[] stockSpan = stockSpan(price);
        Log.d(TAG, "stockSpan : " + Arrays.toString(stockSpan));

        // int[] arr2 = {6, 2, 5, 4, 5, 1, 6};
        long[] arr2 = {7, 2, 8, 9, 1, 3, 6, 5};
        long maxArea = maxRectangularAreaHistogram(arr2);
        Log.d(TAG, "maxRectangularAreaHistogram: " + maxArea);

        slidingWindowMaximum(arr1);


    }

    private void slidingWindowMaximum(int[] arr1) {

    }

    private long maxRectangularAreaHistogram(long[] hist) {

        //Right smallest element
        long[] rse = new long[hist.length];

        Stack<Integer> st = new Stack<>();
        rse[hist.length - 1] = hist.length;
        st.push(hist.length - 1);

        for (int i = hist.length - 2; i >= 0; i--) {            while (!st.isEmpty() && hist[i] <= hist[st.peek()]) {
                st.pop();
            }
            if (st.isEmpty()) {
                rse[i] = hist.length;
            } else {
                rse[i] = st.peek();
            }
            st.push(i);
        }

        //Left smallest element
        long[] lse = new long[hist.length];

        st = new Stack<>();
        lse[0] = -1;
        st.push(0);

        for (int i = 1; i < hist.length; i++) {
            while (!st.isEmpty() && hist[i] <= hist[st.peek()]) {
                st.pop();
            }
            if (st.isEmpty()) {
                lse[i] = -1;
            } else {
                lse[i] = st.peek();
            }
            st.push(i);
        }

        //Area
        long maxArea = 0;
        for (int height = 0; height < hist.length; height++) {
            long width = rse[height] - lse[height] - 1;
            long area = hist[height] * width;

            if (area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }

    private List<Integer>/*int[]*/ nextSmallestElementOnLeft(int[] arr1) {
        Stack<Integer> st = new Stack<>();
        int[] resultArr = new int[arr1.length];
        resultArr[0] = -1;

        st.push(arr1[0]);

        for (int i = 1; i < arr1.length; i++) {
            while (!st.isEmpty() && arr1[i] <= st.peek()) {
                st.pop();
            }

            if (st.isEmpty()) {
                resultArr[i] = -1;
            } else {
                resultArr[i] = st.peek();
            }

            st.push(arr1[i]);
        }

        List<Integer> intList = new ArrayList<Integer>(resultArr.length);
        for (int i : resultArr) {
            intList.add(i);
        }

        return intList;

    }

    private int[] nextSmallestElementOnRight(int[] arr1) {

        Stack<Integer> st = new Stack<>();
        int[] resultArr = new int[arr1.length];
        resultArr[arr1.length - 1] = -1;

        st.push(arr1[arr1.length - 1]);

        for (int i = arr1.length - 2; i >= 0; i--) {
            while (!st.isEmpty() && arr1[i] < st.peek()) {
                st.pop();
            }

            if (st.isEmpty()) {
                resultArr[i] = -1;
            } else {
                resultArr[i] = st.peek();
            }

            st.push(arr1[i]);
        }

        return resultArr;
    }

    private int[] stockSpan(int[] price) {
        //https://www.youtube.com/watch?v=0BsPlzqksZQ&list=PL-Jc9J83PIiEyUGT3S8zPdTMYojwZPLUM&index=10
        //Here we have to find nextGreaterElementToLeft in this, then half problem is solved, span means
        //from current element, on how much steps backwards we find the first greater element. These
        // no. of steps from current till greater element in left is span. Default span is 1 i.e
        // say we checking span for one element, and to its exact left(just one position less than it)
        //we found greater then its 1 only.
        //Here also same process followed pop first, then answer, then push, it follows
        //alternativeSolutionForNextGreaterElementOnRight approach of storing indexes in stack.
        // span is current index +1 if no greater element present immediate to left, else
        //if gap then our index - index of the element which is greater.

        Stack<Integer> st = new Stack<>();
        int[] resultArr = new int[price.length];
        //Its because for first index nothing on left, hence default 1 span applied.
        resultArr[0] = 1;
        st.push(0);

        for (int i = 1; i < price.length; i++) {
            while (!st.isEmpty() && price[i] >= price[st.peek()]) {
                st.pop();
            }

            if (st.isEmpty()) {
                resultArr[i] = i + 1;
            } else {
                resultArr[i] = i - st.peek();
            }
            st.push(i);
        }

        return resultArr;
    }

    private long[] alternativeSolutionForNextGreaterElementOnRight(long[] arr) {
        //https://www.youtube.com/watch?v=XP8iEi9Aa8c&list=PL-Jc9J83PIiEyUGT3S8zPdTMYojwZPLUM&index=8
        //Here is the alternate approach for same question, Here we will scan from left to right
        //In stack we will push the index instead of the element, and for each iteration we will
        //check if previous element is smaller or not, if smaller then  for previous element
        //element the answer will be our current element value only, so we will pop and mark answer
        // in same step and then we will push the index of our current element.
        //If not smaller then no answer marked and nothing popped, we just push the current index.
        // In last step, we check if stack is not empty, meaning there are still indexes left
        // with no answer, we will mark all of them as -1.
        Stack<Long> st = new Stack<>();
        long[] resultArr = new long[arr.length];
        st.push(0L);

        for (int i = 0; i < arr.length; i++) {
            while (!st.isEmpty() && arr[i] > arr[Math.toIntExact(st.peek())]) {
                Long pos = st.peek();
                resultArr[Math.toIntExact(pos)] = arr[i];
                st.pop();
            }
            st.push((long) i);
        }

        while (!st.isEmpty()) {
            Long pos = st.peek();
            resultArr[Math.toIntExact(pos)] = -1;
            st.pop();
        }

        return resultArr;
    }

    private long[] nextGreaterElementOnRight(long[] arr) {
        //https://www.youtube.com/watch?v=rSf9vPtKcmI&list=PL-Jc9J83PIiEyUGT3S8zPdTMYojwZPLUM&index=7

        //Here we will run the loop from the end to start.
        //Logic is, starting from end the first element is pushed to stack and since no value is
        //in the right, we add -1 for its position in result int array.
        // Now for every element in array, we do this operation i.e - ans +, meaning popping the
        //element from stack, printing the answer and pushing the current element in stack.
        // Main thing is, say we are at second last element, we check if element present at top of
        // stack is smaller than this element, if yes, then pop that element and continue doing the
        // same until the stack is empty or we have a top of stack that is greater than our element. if
        // greater found, then nothing to pop just add top of stack as answer and push our current
        //element in stack and continue with same process.
        // Time complexity is o(n) for more detail review video.

        Stack<Long> st = new Stack<>();
        long[] resultArr = new long[arr.length];
        resultArr[arr.length - 1] = -1;
        st.push(arr[arr.length - 1]);

        for (int i = arr.length - 2; i >= 0; i--) {
            while (!st.isEmpty() && arr[i] >= st.peek()) {
                st.pop();
            }

            if (st.isEmpty()) {
                resultArr[i] = -1;
            } else {
                resultArr[i] = st.peek();
            }
            st.push(arr[i]);
        }
        return resultArr;
    }

    private boolean balancedBracket(String s) {
        Stack<Character> stk = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ')') {
                boolean b = handleClosing(stk, '(');
                if (!b) {
                    return false;
                }
            } else if (ch == '}') {
                boolean b = handleClosing(stk, '{');
                if (!b) {
                    return false;
                }
            } else if (ch == ']') {
                boolean b = handleClosing(stk, '[');
                if (!b) {
                    return false;
                }
            } else {
                if (ch == '{' || ch == '(' || ch == '[') {
                    stk.push(ch);
                }
            }
        }

        return stk.isEmpty();
    }

    private boolean handleClosing(Stack<Character> stk, char openingBracketType) {
        if (stk.isEmpty()) {
            return false;
        }
        if (stk.peek() != openingBracketType) {
            return false;
        } else {
            stk.pop();
            return true;
        }
    }

    private boolean duplicateBraces(String s) {
        //example1  -- ((a+b)+ (p+q)) --> no duplicate present.
        //example2  -- ((a+b))+ (p+q) --> duplicate present. Look at first expression

        //Logic is, we will push elements until we get a closing bracket, if it is detected then
        // we will pop all elements till one opening bracket is detected, we will pop the opening
        // bracket as well. Main thing here is if after detection of closing bracket we get some
        // element before opening bracket means this pair of braces contain some data hence not
        //duplicate.But if after detecting an closing bracket we directly get opening bracket,
        // then it means duplicates are there, as no content in between.

        Stack<Character> stack = new Stack();
        boolean result = false;


        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ')') {
                stack.push(c);
            } else {
                if (stack.peek().equals('(')) {
                    result = true;
                } else {
                    while (stack.peek() != '(') {
                        stack.pop();
                    }
                    stack.pop();
                }
            }

        }
        return result;
    }
}