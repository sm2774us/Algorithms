package algorithms.sorting.topk;

import java.util.*;

public class TopKFrequent {

    /**
	 * LeetCode - 692 : Top K Frequent Words.
	 * https://leetcode.com/problems/top-k-frequent-words/
	 *
	 *	
     * Solution-1: NAIVE SORT
     * 
     * 1. Have a map of words and their counts. 
     * 2. Create a list of the words and sort them based on their frequency in reverse order 
     *    (to make it descending order) first, then based on alphabatical order.
     * 3. Now return k elements from this list.
     *
     * Time Complexity  : O(N*log(N)), as Collections.sort is O(N*log(N))
     * Space Complexity : O(N)       , for map and list
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequentUsingNaiveSort(String[] words, int k) {
        if (words == null || words.length == 0 || k <= 0 || k > words.length) return Collections.<String>emptyList();
        Map<String, Integer> wordCountMap = new HashMap<>();
        
        for(String word : words){
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0)+1);
        }
        
        List<String> wordsList = new ArrayList<>(wordCountMap.keySet());
        
        Collections.sort(wordsList, (word1, word2) -> {
            int diff = wordCountMap.get(word2) - wordCountMap.get(word1);     //Compare frequency in reverse order 
            if(diff!=0){                                                      //Frequency is different, sort based on frequency
                return diff;
            }
            else{                                                             //Frequency is same
                return word1.compareTo(word2);                                //Sort based on alphabatical order
            }
        });
        
        return wordsList.subList(0, k);                                       //Return k elements
    }

    /**
	 * LeetCode - 692 : Top K Frequent Words.
	 * https://leetcode.com/problems/top-k-frequent-words/
	 *
	 *	
     * Solution-2: MAX HEAP
     * 
     * 1. Create hash table for the words with frequency
     * 2. Create maxHeap with words and add them to it
     * 3. Poll top k from the maxHeap and add them to Result list
     * 
     * Time Complexity  : O(K*log(N))
     *                    O(N) for heapify 
     *                    and 
     *                    O(K*log(N)) for poping k times
     * 
     *                    Highest Time Complexity of O(K*log(N)) v/s O(N) so,
     *                    instead of: O(N+K*log(N))
     *                    TC = O(K*log(N))
     *
     * Space Complexity : O(N)         , for heap
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequentUsingMaxHeap(String[] words, int k) {
        if (words == null || words.length == 0 || k <= 0 || k > words.length) return Collections.<String>emptyList();
        List<String> results = new ArrayList<String>();

        // 1. Create hash table for the word with frequency | O(N)
        Map<String , Integer> wordFrequency = new HashMap<String, Integer>();

        for( String word : words){
            
            int frequency = wordFrequency.getOrDefault(word, 0) + 1;
            wordFrequency.put(word.toLowerCase(), frequency);
        }

        // 2. Create maxHeap and add all words | O(N)
        Queue<String> maxHeap = new PriorityQueue<>(new FrequencyComparator(wordFrequency));

        for (String word: wordFrequency.keySet()){
            maxHeap.add(word);

        }

        // 3. Poll top k from the maxHeap | Time: O(k LogN) because each poll is logN and we are polling k times.
        while (!maxHeap.isEmpty() && k-- > 0){
            results.add(maxHeap.poll());
        }

        //Highest Time complexity O(k LogN) which is better than O(NlogN) if we sort the element in Hashmap .
        return results;
    }

    /**
     * Solution-3: MIN HEAP - Instead of using a max heap, we only store Top K Freqency word 
	 * we have met so far in our min heap.
     * 
     * Determine top-K frequent elements using Min Heap.
     *
     * heap implemented using PriorityQueue & lambda comparator
     * time: O(NlogK) space: O(N)
     * we can also use kth smallest algorithm for this algorithm to 
     * achieve O(N) time. But Java doesn't has a built-in package for that.
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequentUsingMinHeap(String[] words, int k) {
        if (words == null || words.length == 0 || k <= 0 || k > words.length) return Collections.<String>emptyList();
        // first count the occurance of each word
        Map<String, Integer> map = new HashMap<>();
        for (String word: words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        // now build the min heap with occurance as the Key
        PriorityQueue<String> pq = new PriorityQueue<>((s1, s2) -> map.get(s1) - map.get(s2) != 0 ? map.get(s1) - map.get(s2) : - s1.compareTo(s2));
        
        for(String word: map.keySet()) {
            pq.offer(word);
            if (pq.size() > k) pq.poll();
        }
        
        List<String> sortedList = new LinkedList<String>();
        while (!pq.isEmpty()) sortedList.add(0, pq.poll());
        
        return sortedList;        
    }

    private static class FrequencyComparator implements Comparator<String> {
        private final Map<String, Integer> wordFrequency;

        public FrequencyComparator(Map<String, Integer> wordFrequency) {
            this.wordFrequency = wordFrequency;
        }

        @Override
        public int compare(String left, String  right) {
            if (wordFrequency.get(left) == wordFrequency.get(right)){
                return left.compareTo(right);
            }
            return wordFrequency.get(right) - wordFrequency.get(left); //Higher value of right - MaxHeap
        }
    }

    /**
	 * LeetCode - 347 : Top K Frequent Elements.
	 * https://leetcode.com/problems/top-k-frequent-elements/
	 *
     * Solution-1: MAX HEAP -
     * 
     * Algorithm Steps:
     *   1. Create a frequency table
     *   2. Create a Max Heap and add all the distinct elements
     *   3. Poll top k frequent elements off the Heap
     * 
     * Time & Space Complexity Analysis:
     * 
     * N = # of elements in the input array
     * D = # of distinct (unique) elements in the input array
     * 
     * Building the HashMap: O(N) time
     *   - Add all the N elements into the HashMap and add thier frequency
     * 
     * Building the Heap: O(D) time
     *   - https://www.geeksforgeeks.org/time-complexity-of-building-a-heap/
     *   - Above is a proof that shows that building a heap of N elements will take O(N) time
     *   - In our case we are building a heap of D elements = O(D) time
     * 
     * Poll K distinct elements from the Heap: O(K log D) time
     *   - There are D elements in the Heap and we call poll() K times = O(K log D) time
     * 
     * Total Time Complexity = O(K log D)
     * Total Space Complexity = O(D), this is the size of the heap.
     * 
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequentElementsUsingMaxHeap(int[] nums, int k) {        
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        
		// count the number of each item
        for (int n: nums) {
	        map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
		// create a heap with customized comparator 
		// [1,1,1,2,2,3] ->
		// {[1,3][2,2][3,1]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        
		// fill the heap
        map.keySet().forEach(v -> {
            pq.offer(new int[] {v, map.get(v)});
        });
        
		// get the result
        int[] ans = new int[k];
        for (int i = 0; i < k; i ++) {
            ans[i] = pq.poll()[0];
        }
        
        return ans;
    }

    /**
	 * LeetCode - 347 : Top K Frequent Elements.
	 * https://leetcode.com/problems/top-k-frequent-elements/
	 *
     * Solution-2: MIN HEAP -
     * 
     * 1. Keep a heap which has the size of k.
     * 2. Since we are using min-heap, the top element of the heap 
     *    is the smallest frequency in a heap. 
     *    The top element of heap works as a threshold. 
     *    Only the frequency more significant than the threshold can get into the heap. 
     *    When we met a new frequency, if the new frequency is less than the top element 
     *    of the heap, move on; if the new frequency is larger than the top element 
     *    of the heap, we pop out the top element of the heap, and push the new frequency 
     *    into the heap.
     * 
     * Time & Space Complexity Analysis:
     * 
     * Time Complexity = O(N log K)
     * Space Complexity = O(K + N)
     * 
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequentElementsUsingMinHeap(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) return new int[0];
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0)+1);
        }
        //System.out.println(freqMap);
        //PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k + 1, (e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = 
        new PriorityQueue<>((e1, e2) -> e1.getValue()-e2.getValue());
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        //System.out.println(minHeap);
        List<Integer> result = new ArrayList<>(k);
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        //int[] res = new int[k];
        //int i = 0;
        //for (Map.Entry<Integer, Integer> entry : minHeap) {
        //    res[i++] = entry.getKey();
        //}
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
	 * LeetCode - 347 : Top K Frequent Elements.
	 * https://leetcode.com/problems/top-k-frequent-elements/
	 *
     * Solution-3: BUCKET SORT -
     * 
     * It is intuitive to map a value to its frequency. 
     * Then our problem becomes 'to sort map entries by their values'.
     * Since frequency is within the range [1, n] for n is the number of elements, 
     * we could apply the idea of Bucket Sort:
     *   1) We divide frequencies into n + 1 buckets, in this way, the list in buckets[i] contains elements with the same frequency i
     *   2) Then, we go through the buckets from tail to head until we collect k elements.
     * 
     * Determine top-K frequent elements using Bucket Sort.
     *
     * Time Complexity  : O(N)
     * Space Complexity : O(N)
     *
     * Java Learning Point:
     * --------------------
     * You can create an array of lists but you cannot use type during initalization.
     * 
     * Example:
     * List<Integer>[] lists=new List[10];
     * 
     * Why this works? 
     * Because the lists variable points to an array data structure 
     * whose data type is List<Integer>. This array contains a set of references 
     * to different objects of type List<Integer>, and this is why if we try 
     * to run lists[i]=new ArrayList<String>(); it will not compile. 
     * However when we initialize the array itself we don't need to provide 
     * the type of the List objects as List since from JVM point of view a 
     * List of Integer objects and a List of Object objects will require 
     * the same number of bytes as logn as their sizes is same. 
     * The only constraint comes when we set a array member to a value 
     * (of type List - it has to be List<Integer> not anything else)
     * 
     * You can type cast the List[] to a List<Integer>[] but the end result 
     * and the JVM behavior is the same.
     * 
     * Reference: 
     * https://stackoverflow.com/questions/15636558/how-to-new-a-listinteger-in-java
     * 
     * @param words
     * @param k
     * @return
     */
    @SuppressWarnings("unchecked") 
    public int[] topKFrequentElementsUsingBucketSort(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) return new int[0];
        // since the range of count is fixed (0, nums), we can use bucket sort
        // count frequency
        Map<Integer, Integer> counts = new HashMap<>();
        for (int n : nums) {
            counts.put(n, counts.getOrDefault(n, 0) + 1);
        }

        // create one bucket for each frequency
        // each bucket is a list of n for that frequency
        // NOTE: no <> after new List[]
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int n : counts.keySet()) {
            int count = counts.get(n);
            // default vaule for object is null
            if (bucket[count] == null) bucket[count] = new ArrayList<>();
            bucket[count].add(n);
        }

        // scan each bucket and add to res
        int[] res = new int[k];
        int j = 0;
        for (int i = nums.length; i >= 0; i--) {
            if (bucket[i] == null) continue;
            for (int n : bucket[i]) {
                res[j++] = n;
                if (j == k) return res;
            }
        }

        return res;
    }

    /**
	 * LeetCode - 347 : Top K Frequent Elements.
	 * https://leetcode.com/problems/top-k-frequent-elements/
	 *
     * Solution-4: QUICK SELECT
     * 
     * HashMap + Quick Select Solution
     * 1. HashMap to store <num, freq> pair
     * 2. Quick select to select the most frequent k element 
     *    Quick select is by the freq value not the num key.
     *
     * Time Complexity  : O(N) 
     *                    ( Worst case could be O(N^2) ... O(N) is the average )
     *                    Refer to: https://en.wikipedia.org/wiki/Quickselect
     * 
     * Space Complexity : O(N)
     *
     * @param words
     * @param k
     * @return
     */
    @SuppressWarnings("unchecked")
    public int[] topKFrequentElementsUsingQuickSelect(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) return new int[0];
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            int freq = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, freq);
        }
        Map.Entry<Integer, Integer>[] arr = freqMap.entrySet().toArray(new Map.Entry[0]);
        quickSelect(arr, arr.length - k);
        int[] res = new int[k];
        for (int i = 0, j = arr.length - 1; i < k; i++, j--) {
            res[i] = arr[j].getKey();
        }
        return res;
    }
    
    private void quickSelect(Map.Entry<Integer, Integer>[] arr, int k) {
        // shuffle is not needed because arr is shuffled by hash map.
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = partition(arr, l , r);
            if (mid > k) {
                r = mid - 1;
            } else if (mid < k) {
                l = mid + 1;
            } else {
                return;
            }
        }
    }
    
    private int partition(Map.Entry<Integer, Integer>[] arr, int l, int r) {
        int pivot = arr[r].getValue();
        while (l < r) {
            while (l < r && arr[l].getValue() <= pivot) {
                l++;
            }
            swap(arr, l, r);
            while (l < r && arr[r].getValue() >= pivot) {
                r--;
            }
            swap(arr, l, r);
        }
        return l;
    }
    
    private void swap(Map.Entry<Integer, Integer>[] arr, int l, int r) {
        Map.Entry<Integer, Integer> temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
