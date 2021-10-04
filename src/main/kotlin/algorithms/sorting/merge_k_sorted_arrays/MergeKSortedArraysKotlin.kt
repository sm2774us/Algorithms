package algorithms.sorting.merge_k_sorted_arrays;

import java.util.PriorityQueue
import java.util.Queue

class MergeKSortedArraysKotlin {

    /**
     * Facebook Interview Question.
     * 
     * Implement a method to merge K sorted arrays.
     * The time complexity must be better than O(N*K).
     * Method signature (C++): vector<char> merge(vector<vector<char>> chunks)
     * 
     * Reference:
     * https://leetcode.com/discuss/interview-question/617596/facebook-onsite-merge-k-sorted-arrays
     * 
     * MAX HEAP Solution.
     * 
     * Time Complexity  : O(N*log(K))
     * Space Complexity : O(N+K)
     * 
     * @param arrays
     * @return
     */
    fun mergekSortedArraysUsingMaxHeap(arrays: Array<IntArray>): IntArray {        
        val nonNullArrays: List<IntArray> = arrays.filterNotNull()
        val rows = nonNullArrays.size
        val pq: Queue<Entry> = PriorityQueue(rows)
        var size = 0
        for (r in 0 until rows) {
            if (nonNullArrays[r].size > 0) {
                size += nonNullArrays[r].size
                pq.add(Entry(nonNullArrays[r][0], r, 0))
            }
        }
        val result = IntArray(size)
        var i = 0
        while (!pq.isEmpty()) {
            val curr = pq.poll()
            result[i] = curr.`val`
            val nextIndex = curr.index + 1
            if (nextIndex < nonNullArrays[curr.arr].size) {
                pq.add(Entry(nonNullArrays[curr.arr][nextIndex], curr.arr, nextIndex))
            }
            i++
        }
        return result
    }

    private class Entry(val `val`: Int, val arr: Int, val index: Int) : Comparable<Entry> {
        override fun compareTo(other: Entry): Int {
            return Integer.compare(`val`, other.`val`)
        }
    }

    fun mergekSortedUsingDivideAndConquer(lists: Array<IntArray>): IntArray? {
        return divide(lists, 0, lists.size - 1)
    }

    private fun divide(lists: Array<IntArray>, lo: Int, hi: Int): IntArray? {
        val mid = lo + (hi - lo) / 2
        if (hi - lo == 1) {
            return merge(lists[lo], lists[hi])
        }
        if (hi - lo == 0) {
            return lists[lo]
        }
        val left = divide(lists, lo, mid)
        val right = divide(lists, mid + 1, hi)
        return merge(left, right)
    }

    private fun merge(left: IntArray?, right: IntArray?): IntArray? {
        if (left == null && right == null) return null
        if (left == null) return right
        if (right == null) return left
        val mergedArray = IntArray(left.size + right.size)
        var leftPtr = 0
        var rightPtr = 0
        var index = 0
        while (leftPtr < left.size && rightPtr < right.size) {
            val leftVal = left[leftPtr]
            val rightVal = right[rightPtr]
            if (leftVal <= rightVal) {
                mergedArray[index] = leftVal
                leftPtr++
            } else {
                mergedArray[index] = rightVal
                rightPtr++
            }
            index++
        }
        if (leftPtr != left.size) {
            for (i in leftPtr until left.size) {
                mergedArray[index++] = left[leftPtr++]
            }
        }
        if (rightPtr != right.size) {
            for (i in rightPtr until right.size) {
                mergedArray[index++] = right[rightPtr++]
            }
        }
        return mergedArray
    }
}