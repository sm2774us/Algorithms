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
    fun mergekSortedArrays(arrays: Array<IntArray>): IntArray {        
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

}