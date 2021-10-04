package algorithms.sorting.merge_k_sorted_arrays;

import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedArrays {
    
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
    public int[] mergekSortedArrays(int[][] arrays) {
        int rows = arrays.length;
        Queue<Entry> pq = new PriorityQueue<>(rows);
    
        int size = 0;
        for (int r = 0; r < rows; r++) {
            if (arrays[r] != null && arrays[r].length > 0) {
                size += arrays[r].length;
                pq.add(new Entry(arrays[r][0], r, 0));
            }
        }
    
        int[] result = new int[size];
    
        for (int i = 0; !pq.isEmpty(); i++) {
            Entry curr = pq.poll();
            result[i] = curr.val;
    
            int nextIndex = curr.index + 1;
            if (nextIndex < arrays[curr.arr].length) {
                pq.add(new Entry(arrays[curr.arr][nextIndex], curr.arr, nextIndex));
            }
        }
    
        return result;
    }
    
    private static class Entry implements Comparable<Entry> {
        final int val, arr, index;
    
        public Entry(int val, int arr, int index) {
            this.val = val;
            this.arr = arr;
            this.index = index;
        }
    
        @Override
        public int compareTo(Entry that) {
            return Integer.compare(this.val, that.val);
        }
    }

}
