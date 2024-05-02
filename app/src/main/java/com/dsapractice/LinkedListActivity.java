package com.dsapractice;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dsapractice.utils.Node;


public class LinkedListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_list);

        LinkedList linkedList = new LinkedList();
        linkedList.addToLast(1);
        linkedList.addToLast(1);
        linkedList.addToLast(2);
        linkedList.addToLast(2);
        linkedList.addToLast(3);
        linkedList.addToLast(5);
        linkedList.addToLast(7);
        linkedList.addToLast(7);
/*
        linkedList.displayList();
        Log.d("TAG", "onCreate: linked list size before : " + linkedList.size());
        linkedList.removeFirst();
        Log.d("TAG", "onCreate: linked list size after : " + linkedList.size());

        int firstValue = linkedList.getFirstValue();
        int lastValue = linkedList.getLastValue();
        int valueAtIndex = linkedList.getValueAtIndex(23);
        Log.d("TAG", "\nfirstValue : " + firstValue + "\nlastValue : " + lastValue + "\nvalueAtIndex : " + valueAtIndex);

        linkedList.addToFirst(65);
        linkedList.addToIndex(1, 81);
*/
        //linkedList.displayList();
        //linkedList.removeLast();
        // linkedList.displayList();

        //linkedList.reverseListIterativeDataOnly();
        //linkedList.displayList();

       /* linkedList.reverseListIterativePointerAlso();
        linkedList.displayList();
        linkedList.removeAtIndex(2);*/

/*        int dataValueAtK = linkedList.kthElementFromLast(3);
        Log.d("TAG", "dataValueAtK : " + dataValueAtK);

        linkedList.displayList();
        int mid = linkedList.findMid();
        Log.d("TAG", "mid of list  : " + mid);*/

        linkedList.removeDuplicatesFromSortedList();
        linkedList.displayList();


        LinkedList linkedList1 = new LinkedList();
        linkedList1.addToLast(5);
        linkedList1.addToLast(2);
        linkedList1.addToLast(6);
        linkedList1.addToLast(13);
        linkedList1.addToLast(17);
        linkedList1.addToLast(10);
        linkedList1.addToLast(1);
        linkedList1.oddEvenList();
        linkedList1.displayList();

    }

    class LinkedList {
        Node head;
        Node tail;
        int size;

        public void addToLast(int value) {
            Node temp = new Node();
            temp.data = value;
            temp.next = null;

            // Case when list is empty and we are adding node to last.
            //Here we make new node, add data to it and in its next field set it to null.
            //since here we have one node only head,tail are same, so we stored our created
            //temp node to both and increment size of list by one.
            if (size == 0) {
                head = temp;
                tail = temp;
            }
            //if size>=1 then adding node and increment size is same but here in tail's next we add the address
            //of newly created temp node so that our previous tail node points to this new temp node and
            // then we make this newly created temp node as tail.
            else {
                tail.next = temp;
                tail = temp;
            }
            size++;

        }

        public void removeFirst() {
            if (size == 0) {
                Toast.makeText(LinkedListActivity.this, "list already empty", Toast.LENGTH_SHORT).show();
            } else if (size == 1) {
                head = tail = null;
                size = 0;
            } else {
                head = head.next;
                size--;
            }
        }

        public int getFirstValue() {
            if (size == 0) {
                return -1;
            } else {
                return head.data;
            }
        }

        public int getLastValue() {
            if (size == 0) {
                return -1;
            } else {
                return tail.data;
            }
        }

        public int getValueAtIndex(int index) {
            if (size == 0) {
                return -1;
            } else if (index < 0 || index >= size) {
                Toast.makeText(LinkedListActivity.this, "Invalid argument", Toast.LENGTH_SHORT).show();
                return -1;
            } else {
                Node temp = head;
                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }
                return temp.data;
            }
        }

        public Node getNodeAt(int index) {
            Node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        }

        public void addToFirst(int value) {
            Node temp = new Node();
            temp.data = value;
            temp.next = head;
            head = temp;

            if (size == 0) {
                tail = temp;
            }
            size++;
        }

        public void addToIndex(int index, int value) {
            if (index < 0 || index > size) {
                Toast.makeText(LinkedListActivity.this, "Invalid argument", Toast.LENGTH_SHORT).show();
            } else if (index == 0) {
                addToFirst(value);
            } else if (index == size) {
                addToLast(value);
            } else {
                Node node = new Node();
                node.data = value;

                Node temp = head;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }

                node.next = temp.next;
                temp.next = node;

                size++;
            }
        }

        public void removeLast() {

            if (size == 0) {
                Toast.makeText(LinkedListActivity.this, "list already empty", Toast.LENGTH_SHORT).show();
            } else if (size == 1) {
                head = tail = null;
                size = 0;
            } else {
                Node temp = head;
                for (int i = 0; i < size - 2; i++) {
                    temp = temp.next;
                }
                temp.next = null;
                tail = temp;
                size--;
            }
        }

        public void reverseListIterativeDataOnly() {
            if (size > 1) {
                int i = 0;
                int j = size - 1;

                while (i < j) {
                    Node leftNode = getNodeAt(i);
                    Node rightNode = getNodeAt(j);

                    int temp = leftNode.data;
                    leftNode.data = rightNode.data;
                    rightNode.data = temp;

                    i++;
                    j--;
                }

            } else {
                Toast.makeText(LinkedListActivity.this, "Not possible , not sufficient elements", Toast.LENGTH_SHORT).show();
            }
        }

        //Very important
        public void reverseListIterativePointerAlso() {
            if (size == 0) {
                Toast.makeText(LinkedListActivity.this, "list already empty", Toast.LENGTH_SHORT).show();
            } else if (size == 1) {
                Toast.makeText(LinkedListActivity.this, "Only one element in list", Toast.LENGTH_SHORT).show();
            } else {
                Node prev = null;
                Node curr = head;

                while (curr != null) {
                    Node next = curr.next;
                    curr.next = prev;

                    prev = curr;
                    curr = next;
                }

                //swap both head and tail now
                Node temp = head;
                head = tail;
                tail = temp;
            }
        }


        public void removeAtIndex(int index) {
            if (size == 0) {
                Toast.makeText(LinkedListActivity.this, "list already empty", Toast.LENGTH_SHORT).show();
            } else if (index < 0 || index >= size) {
                Toast.makeText(LinkedListActivity.this, "Invalid index", Toast.LENGTH_SHORT).show();
            } else if (index == 0) {
                removeFirst();
            } else if (index == size - 1) {
                removeLast();
            } else {
                Node temp = head;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }

                temp.next = temp.next.next;
                size--;

            }

        }

        //Here constraint is we can't use size. So we will two pointer approach i.e slow and fast
        //meaning whatever the value of k, we maintain k diatance b/w fast and slow.Example
        // k=2 , then 2 position gap b/w slow and fast. i.e if slow at 0 then fast at 2 index.
        public int kthElementFromLast(int k) {

            Node slow = head;
            Node fast = head;
            //here moving fast kth position ahead of slow.
            for (int i = 0; i < k; i++) {
                fast = fast.next;
            }

            //till the fast reaches at end or tail, we increment both slow and fast by one position.
            while (fast != tail) {
                fast = fast.next;
                slow = slow.next;
            }

            //once fast is at end slow will be our desired location.

            return slow.data;
        }

        public int findMid() {
            if (size == 0) {
                Toast.makeText(LinkedListActivity.this, "list already empty", Toast.LENGTH_SHORT).show();
                return -1;
            } else if (size == 1 || size == 2) {
                return head.data;
            } else {
                Node fast = head;
                Node slow = head;

                //Here handling both odd and even lists, when slow moves 1 position fast move 2 times i.e 2 positions
                //for even list we to stop when its next node's next is null.
                while (fast != null && fast.next.next != null) {
                    slow = slow.next;
                    fast = fast.next.next;
                }

                return slow.data;
            }
        }

        public LinkedList mergeTwoSortedLinkedList(LinkedList l1, LinkedList l2) {

            //Here we make new list, and start from initial element of each list.
            // till one of the list is empty we compare values of these 2 list, the list whose element is less
            // is added to new list and its position is increased.
            //Now finally whichever list is not empty after first loop, we added its remaining elements to result list
            LinkedList resultList = new LinkedList();
            Node firstListElement = l1.head;
            Node secondListElement = l2.head;

            while (firstListElement != null || secondListElement != null) {
                if (firstListElement.data < secondListElement.data) {
                    resultList.addToLast(firstListElement.data);
                    firstListElement = firstListElement.next;
                } else {
                    resultList.addToLast(secondListElement.data);
                    secondListElement = secondListElement.next;
                }
            }

            while (firstListElement != null) {
                resultList.addToLast(firstListElement.data);
                firstListElement = firstListElement.next;
            }
            while (secondListElement != null) {
                resultList.addToLast(secondListElement.data);
                secondListElement = secondListElement.next;
            }

            return resultList;

        }


        public void removeDuplicatesFromSortedList() {
            LinkedList result = new LinkedList();

            while (this.size() > 0) {

                int value = this.getFirstValue();
                this.removeFirst();

                if (result.size() == 0 || result.tail.data != value) {
                    result.addToLast(value);
                }
            }

            this.head = result.head;
            this.tail = result.tail;
            this.size = result.size;
        }

        //Order should remain same in this case.
        public void oddEvenList() {
            LinkedList evenList = new LinkedList();
            LinkedList oddList = new LinkedList();

            while (this.size() > 0) {

                int value = this.getFirstValue();
                this.removeFirst();

                if (value % 2 == 0) {
                    evenList.addToLast(value);
                } else {
                    oddList.addToLast(value);
                }
            }

            if (evenList.size() == 0) {
                this.head = oddList.head;
                this.tail = oddList.tail;
            } else if (oddList.size() == 0) {
                this.head = evenList.head;
                this.tail = evenList.tail;
            } else {
                oddList.tail.next = evenList.head;
                this.head = oddList.head;
                this.tail = evenList.tail;
            }

        }

        public void displayList() {

            // Here we make a new node that point to head. then we print data of that node, then we pick next of
            // that node and again assign to temp so now temp points to 2nd node and so on , till the node is not null.
            //i.e end of list
            Node temp = head;

            while (temp != null) {
                Log.d("TAG", "displayList element : " + temp.data);
                temp = temp.next;
            }
        }

        public int size() {
            return size;
        }
    }
}