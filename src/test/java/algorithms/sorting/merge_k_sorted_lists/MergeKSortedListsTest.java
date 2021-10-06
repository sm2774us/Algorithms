package algorithms.sorting.merge_k_sorted_lists;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

// import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import algorithms.sorting.common_data_structures.ListNode;

public class MergeKSortedListsTest {

    //private MergeKSortedLists mergeKSortedLists;
    private MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();

    // @BeforeAll
    // public void setup() {
    //     mergeKSortedLists = new MergeKSortedLists();
    // }

    private ListNode arrayToLinkedList(int[] nums) {
        if (nums == null) {
            return null;
        }

        ListNode head, current;
        head = new ListNode(0);
        current = head;
        for (int i = 0; i < nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
        }

        return head.next;
    }

    private int[] linkedListToArray(ListNode head) {
        if (head == null) {
            return null;
        }

        int len = 1;
        ListNode current = head;
        while (current.next != null) {
            len++;
            current = current.next;
        }

        int[] nums = new int[len];

        current = head;
        for (int i = 0; i < len; i++) {
            nums[i] = current.val;
            current = current.next;
        }

        return nums;
    }

    @Test
    public void leetcodeExample1() throws Exception {
        int[][] arrayLists = {{1,4,5},{1,3,4},{2,6}};
        int[] expected = {1,1,2,3,4,4,5,6};
        ListNode[] lists = new ListNode[arrayLists.length];

        for (int i = 0; i < arrayLists.length; i++)
            lists[i] = arrayToLinkedList(arrayLists[i]);

        ListNode resultList = mergeKSortedLists.mergeKLists(lists);
        int[] result = linkedListToArray(resultList);

        assertArrayEquals(expected, result);
    }

    @Test
    public void leetcodeExample2() throws Exception {
        int[][] arrayLists = {{1,4,5,5,9},{1},{-1,-2,0,1,11}};
        int[] expected = {-1,-2,0,1,1,1,4,5,5,9,11};
        ListNode[] lists = new ListNode[arrayLists.length];

        for (int i = 0; i < arrayLists.length; i++)
            lists[i] = arrayToLinkedList(arrayLists[i]);

        ListNode resultList = mergeKSortedLists.mergeKLists(lists);
        int[] result = linkedListToArray(resultList);

        assertArrayEquals(expected, result);
    }
 
}