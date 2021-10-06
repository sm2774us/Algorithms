package algorithms.sorting.merge_k_sorted_lists;

import java.util.PriorityQueue;

import algorithms.sorting.common_data_structures.ListNode;

public class MergeKSortedLists {

    /**
	 * LeetCode - 23 : Merge K Sorted Lists.
	 * https://leetcode.com/problems/merge-k-sorted-lists/
     * 
     * Solution - 1: MIN HEAP
     * 
     * In order to build one sorted list, we always want the minimum node among heads of lists.
     * That's natural to Min Heap.
     * 
     * The complexity of this problem is O(knlogk), no algorithm can run it O(nlogk). We have T(n) = 2T(n/2) + kn, and therefore, O(kn*logk).
     * when people talking about time complexity; say, O(nlogk)
     * Can we first clarify what is "n" please?
     * If n is total number of numbers of all lists, then it's nlogk;
     * if n is average length of each list, then it's nklogk;
     * Without clarification it'll cause lots of confusion.
     * 
     * Time Complexity  : O(N*log(K)), 
     *                    where, 
     *                    N = total number of numbers of all lists.
     *                    K = the number of linked list.
     *                    
     *                    Min Heap contains at most K ListNode(s).
     *                    There are N ListNode to insert, so overall 
     *                    the time complexity is O(N*log(K))
     * 
     * Space Complexity : O(K)
     *                    stores the head of the LinkedList, 
     *                    thus at any time, the Min Heap contains 
     *                    at most K key-value pairs.
     * 
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode head : lists) {
            if (head != null) // Ensures no "null" in minHeap
                minHeap.offer(head);
        }
        
        ListNode dummyHead = new ListNode(), cur = dummyHead;
        while (!minHeap.isEmpty()) {
            ListNode min = minHeap.poll();
            cur.next = min;
            cur = cur.next;
            
            if (min.next != null) // Ensures no "null" in minHeap
                minHeap.offer(min.next);
        }
        
        return dummyHead.next;
    }

}