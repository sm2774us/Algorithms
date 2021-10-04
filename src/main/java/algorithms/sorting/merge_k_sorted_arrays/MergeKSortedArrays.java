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
    public int[] mergekSortedArraysUsingMaxHeap(int[][] arrays) {
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

    public int [] mergekSortedUsingDivideAndConquer(int[][] lists) {
        return divide(lists, 0, lists.length-1);
    }
    
    private int [] divide(int[][] lists, int lo, int hi) {
        int mid = lo + (hi-lo)/2;
    
        if (hi-lo == 1) {
            return merge(lists[lo], lists[hi]);
        }
        if (hi - lo == 0) {
            return lists[lo];
        }
        int [] left = divide(lists, lo, mid);
        int [] right = divide(lists, mid+1, hi);
        return merge(left, right);
    }
    
    private int[] merge(int[] left, int[] right) {
        if (left == null && right == null) return null;
        if (left == null) return right;
        if (right == null) return left;
    
        int [] mergedArray = new int[left.length + right.length];
        int leftPtr = 0;
        int rightPtr  = 0;
        int index = 0;
    
        while (leftPtr < left.length && rightPtr < right.length) {
            int leftVal = left[leftPtr];
            int rightVal = right[rightPtr];
    
            if (leftVal<= rightVal) {
                mergedArray[index] = leftVal;
                leftPtr++;
            } else {
                mergedArray[index] = rightVal;
                rightPtr++;
            }
            index++;
        }
    
        if (leftPtr != left.length) {
            for (int i = leftPtr; i<left.length; i++) {
                mergedArray[index++] = left[leftPtr++];
            }
        }
        if (rightPtr != right.length) {
            for (int i = rightPtr; i<right.length; i++) {
                mergedArray[index++] = right[rightPtr++];
            }
        }
        return mergedArray;
    }
    
}
