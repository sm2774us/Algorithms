package algorithms.sorting.merge_k_sorted_lists

import java.util.Comparator
import java.util.PriorityQueue
import java.util.Queue

import algorithms.sorting.common_data_structures.ListNodeKotlin

class MergeKSortedListsKotlin {

    /**
	 * LeetCode - 23 : Merge K Sorted Lists.
	 * https://leetcode.com/problems/merge-k-sorted-lists/
     *
     * Solution - 1: MIN HEAP
     * 
     * In order to build one sorted list, we always want the minimum node among heads of lists.
     * That's natural to Min Heap.
     * 
     * Pseudo Code:
     * ---------------------------------------
     * 1. Create a minHeap based on the value of the node.
     * 2. Fill the minHeap with the input lists
     * 3. Get the top node from the minHeap, put it into our result.
     * 4. Modify the top node by removing its head that we just put into our result.
     * 5. Put the modified top node back to the minHeap
     * 6. Back to 3.
     * ---------------------------------------
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
	// fun mergeKLists(lists: Array<ListNodeKotlin?>): ListNodeKotlin? {
    //     val minHeap = PriorityQueue<ListNodeKotlin?>(compareBy{ it?.`val` })
    //     // fill the minHeap
    //     for (i in lists.indices) {
    //         lists[i]?.let {
    //             minHeap.add(it)
    //         }
    //     }
        
    //     val result = ListNodeKotlin(0) // dummy head, the result will be its next Node
    //     var resultPointer = result
        
    //     while(minHeap.isNotEmpty()) {
    //         // get current min value
    //         var min = minHeap.remove()
    //         // add min value to the results
    //         resultPointer.next = min
    //         resultPointer = resultPointer.next!!
            
    //         // remove current min, re-add it to the heap
    //         min?.next?.let {
    //             minHeap.add(it)
    //         }
    //     }
        
    //     return result.next
    // }    
    fun mergeKLists(lists: Array<ListNodeKotlin?>): ListNodeKotlin? {
        val minHeap = PriorityQueue<ListNodeKotlin?>(Comparator { a, b -> a.`val` - b.`val` })
        val dummy = ListNodeKotlin(-1)
        var p = dummy
        for (l in lists) l?.let {
            minHeap.offer(it) 
        }
        while (!minHeap.isEmpty()) {
            p.next = minHeap.poll()
            p.next?.next?.let { minHeap.offer(it) }
            p = p.next!!
        }
        return dummy.next
    }

}