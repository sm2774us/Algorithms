package algorithms.sorting.topk;

import algorithms.sorting.utils.MaxHeapComparator;
import algorithms.sorting.utils.SimpleMapComparator;

import java.util.*;

public class TopKFrequent {

    /**
     * Solution-1: NAIVE SORT - The easiest way to think of this problem and easy to implement.
     * 
     * Determine top-K frequent elements using Naive Sorting.
     *
     * Time Complexity  : O(N*log(N)), naive sort is O(N*log(N))
     * Space Complexity : O(N)       , for map and list
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequentUsingNaiveSort(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String word:words){
            map.put(word, map.getOrDefault(word, 0)+1);
        }
        List<Map.Entry<String, Integer>> l = new LinkedList<>();
        for(Map.Entry<String, Integer> e:map.entrySet()){
            l.add(e);
        }
        Collections.sort(l, new SimpleMapComparator());//just use our Comparator to sort
        List<String> ans = new LinkedList<>();
        for(int i = 0;i<=k-1;i++){
            ans.add(l.get(i).getKey());
        }
        return ans;
    }

    /**
     * Solution-2: MAX HEAP - Maintain a max heap and add all the words in it. Pop top K words to get the results.
     * 
     * Determine top-K frequent elements using Max Heap.
     *
     * Time Complexity  : O(N+K*log(N)), O(N) for heapify and O(k*log(N)) for poping k times
     * Space Complexity : O(N)         , for heap
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequentUsingMaxHeap(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String word:words){
            map.put(word, map.getOrDefault(word, 0)+1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new MaxHeapComparator());
        for(Map.Entry<String, Integer> e:map.entrySet()){
            pq.offer(e);
        }
        List<String> ans = new LinkedList<>();
        for(int i = 0; i<=k-1; i++) {
            ans.add(pq.poll().getKey());
        }
        return ans;
    }

    /**
     * Solution-3: MIN HEAP - Instead of using a max heap, we only store Top K Freqency word we have met so far in our min heap.
     * 
     * Determine top-K frequent elements using Min Heap.
     *
     * Complexity Analysis:
     * 
     * We iterate over the array and store into map, taking O(n) time complexity and space.
     * So, we see that n elements are added to a heap of size of max k => n log k
     * (n - k) elements are deleted from heap of size k + 1 => O((n - k) log k)
     * Copy k elements for the result => O(k + k log k) including deletion from heap
     *
     * Hence, the worst case time complexity =>
     * O(n + n log k + (n - k) log k + k + k log k) ~~ O(n log k + n + k)
     *
     * And space : O(max(n, k)), as O(n) for map and O(k) for heap.
     * 
     * Time Complexity  : O(N*(log(K) + N + K)
     * Space Complexity : O(max(N, K))
     *
     * @param words
     * @param k
     * @return
     */
    // public List<String> topKFrequentUsingMinHeap(String[] words, int k) {
    //     Map<String, Integer> map = new HashMap<>();
    //     for(String word:words){
    //         map.put(word, map.getOrDefault(word, 0)+1);
    //     }
    //     MyComparator comparator = new MyComparator();
    //     PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(comparator);
    //     for(Map.Entry<String, Integer> e:map.entrySet()){
    //         // If minHeap's size is smaller than K, we just add the entry
    //         if(pq.size()<k){
    //             pq.offer(e);
    //         }
    //         // Else, we compare the current entry with "min" entry in priority queue
    //         else {
    //             if(comparator.compare(e, pq.peek())>0){
    //                 pq.poll();
    //                 pq.offer(e);
    //             }
    //         }
    //     }
    //     List<String> ans = new LinkedList<>();
    //     for(int i = 0;i<=k-1;i++){
    //         ans.add(0, pq.poll().getKey());//the "smaller" entry poll out ealier 
    //     }
    //     return ans;
    // }
    public List<String> topKFrequentUsingMinHeap(String[] words, int k) {

        List<Integer> result = new ArrayList<>();
        // n distinct elements are stored, lets say, so, space O(n)
        Map<Integer, Integer> map = new HashMap<>(); 
    
        for (int e : nums) // O(n) time complexity and space too
            map.merge(e, 1, Integer::sum);
    
        // minHeap can hold max k +1 elements
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = 
            new PriorityQueue<Map.Entry<Integer, Integer>>((a, b) -> Integer.compare(a.getValue(), b.getValue()));
    
        for (Map.Entry<Integer, Integer> e : map.entrySet()) { // runs for n times
            // time : n log k, why? the heap is restricted to keep max k + 1 elements, hence for each add time complexity is O(log k) and that happens for n times, resulting in O(n log k)
            minHeap.offer(e); 
    
            if (minHeap.size() > k) // elimination happens for n - k times
                minHeap.poll(); // each delete takes O(log k)
        }
    
        while (!minHeap.isEmpty()) { // O(k) times
            result.add(0, minHeap.poll().getKey()); // O(log k) for each removal
        }
        return result;        
    }

    /**
     * Solution-4: BUCKET SORT -
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
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequentUsingBucketSort(String[] words, int k) {
        // freq map
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        // bucket sort on freq
        List<Integer>[] buckets = new List[nums.length + 1];
        for(int key: freq.keySet()){
            int frequency = freq.get(key);
            if(bucket[frequency] == null)
                bucket[frequency] = new ArrayList<>();
            bucket[frequency].add(key);
        }
        // gather result
        List<Integer> res = new ArrayList<>();
        for(int pos = bucket.length-1; pos >= 0; pos--){
            if(bucket[pos] != null){
                for(int i = 0; i < bucket[pos].size() && res.size() < k; i++)
                    res.add(bucket[pos].get(i));
            }
        }
        return res;
    }

    /**
     * Solution-5: QUICKSELECT -
     * 
     * Use quick select to figure out what the top k elements are. That's O(N).
     * Then sort those top k elements. That's O(K*log(K)).
     * 
     * For both of these operations, use the custom compare operation that prioritizes frequency, followed by alphabetical order.
     * 
     * Determine top-K frequent elements using QuickSelect + top-k sort.
     *
     * Time Complexity  : O(N) + O(K*Log(K)) = O(N + K*log(K)), N time for quick select and K*log(K) time for sort
     * 
     * where N = words.length
     * 
     * Space Complexity : O(N)
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequentUsingQuickSelect(String[] words, int k) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word: words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        String[] distinctWords = new String[counts.keySet().size()];
        int idx = 0;
        for (String word: counts.keySet()) distinctWords[idx++] = word;
        findKthLargest(distinctWords, k, counts);
        // System.out.println(Arrays.toString(distinctWords) + " " + counts);
        String[] topK = Arrays.copyOfRange(distinctWords, 0, k);
        Arrays.sort(topK, (a, b) -> compare(b, a, counts));
        return Arrays.asList(topK);
    }

    private String findKthLargest2(String[] nums, int k, Map<String, Integer> counts) {
        int low = 0, high = nums.length - 1;
        
        while (low < high) {
            int pivot = partition(nums, low, high, counts);
            if (pivot == k -1) return nums[pivot];
            
            if (pivot >= k) high = pivot - 1;
            else low = pivot + 1;
        }
        return nums[low];
    }
    
    private int partition(String[] nums, int low, int high, Map<String, Integer> counts) {
        int pivot = low;
        // Note that we should start with the pivot element, and not the next element, 
        // because it's possible that none of the next elements are larger than the pivot. 
        // The main assumption behind the rest of the loop is that low points to a 'valid' greater-than range at the end.
        while (low < high) { // when this breaks, low points to the last element >= pivot
            while (low < high && compare(nums[high], nums[pivot], counts) < 0) high--; // this breaks when one of the two conditions are violated
            while (low < high && compare(nums[low], nums[pivot], counts) >= 0) low++; // This may break either because high has now gone past the >=pivot, or because we've now reached a smaller than pivot element. Note that for low to stop because of the latter condition, high should have stopped at a 'larger than pivot' element; otherwise high would have crossed the border into 'low' and low wouldn't have a chance of crossing the border into high (loop would have broken already by then)
            
            if (low < high) swap(nums, low, high); // if the previous loops broke without crossing the borders, it means that that high points to a larger element, and low points to a smaller element. Swap them if that's the case.
        }
        // since low always points to the last element that satisfies 'larger than or equal to pivot', we can always safely move pivot to 'low' at the end.
        swap(nums, low, pivot);
        return low;
    }

    private int compare(String a, String b, Map<String, Integer> counts) {
        if (counts.get(a) == counts.get(b)) return b.compareTo(a); // 'lower alphabetical order comes first'
        return Integer.compare(counts.get(a), counts.get(b));
    }

    private void swap(String[] nums, int a, int b) {
        String tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    /**
     * Solution-6: SORTED SET -
     * 
     * Time Complexity  : O(N*log(K)), 
     * 
     * Adding an item to a TreeSet is O(log(N)).
     * And, we are doing this N times.
     * So, a total time complexity of: O(N*log(N)).
     * 
     * For this problem we are doing an TreeSet.addAll() and the passed in collection is a SortedSet.
     * So addAll() takes O(N) time.
     * 
     * Reference: https://stackoverflow.com/questions/3390449/computational-complexity-of-treeset-operations-in-java
     * 
     * We are also doing a next() on TreeSet using the iterator which will be O(log(K)) time.
     * So overall time of O(N*log(K)).
     * 
     * References:
     * https://stackoverflow.com/questions/14379515/computational-complexity-of-treeset-methods-in-java 
     * https://gist.github.com/psayre23/c30a821239f4818b0709
     * 
     * where N = words.length
     * 
     * Space Complexity : O(N)
     * 
     * NOTE: This is a slightly more expensive form of the heap approach... requires "re-heaping".
     * It is always more efficient to check to see if you should add it before you insert it.
     * 
     */
    public List<String> topKFrequentUsingSortedSet(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        SortedSet<Map.Entry<String, Integer>> sortedset = new TreeSet<>(
                (e1, e2) -> {
                    if (e1.getValue() != e2.getValue()) {
                        return e2.getValue() - e1.getValue();
                    } else {
                        //return e1.getKey().compareToIgnoreCase(e2.getKey());
                        return e1.getKey().compareTo(e2.getKey());
                    }
                });
        sortedset.addAll(map.entrySet());

        List<String> result = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> iterator = sortedset.iterator();
        while (iterator.hasNext() && k-- > 0) {
            result.add(iterator.next().getKey());
        }
        return result;
    }
}
