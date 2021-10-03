package algorithms.sorting.topk

import java.util.PriorityQueue

import kotlin.Comparator
import kotlin.collections.List
import kotlin.collections.HashMap

class TopKFrequentKotlin {

    /**
     * Determine top-K frequent elements using Naive sorting.
     *
     * Time Complexity  : O(N*log(N)), naive sort is O(N*log(N))
     * Space Complexity : O(N)       , for map and list
     *
     * @param words
     * @param k
     * @return
     */    
    fun topKFrequentUsingNaiveSort(words: Array<String>, k: Int): List<String> =
	    words                                        // Array<String>: ["i", "love", "leetcode", "i", "love", "coding"]
		.groupingBy { it }
		.eachCount()                                 // Map<String, Int>: {"i" to 2, "love" to 2, "leetcode" to 1, "coding" to 1}
		.toList()                                    // List<Pair<String, Int>>: ["i" to 2, "love" to 2, "leetcode" to 1, "coding" to 1]
		.groupBy({ it.second }, { it.first })        // Map<Int, List<String>>: {2 to ["i", "love"], 1 to ["leetcode", "coding"]}
		.toSortedMap(compareByDescending { it })     // SortedMap<Int, List<String>>: {2 to ["i", "love"], 1 to ["leetcode", "coding"]}
		.flatMap { it.value.sorted() }               // List<String>: ["i", "love", "coding", "leetcode"]
		.take(k)                                     // List<String>: ["i", "love", "coding"]

    /**
	 * LeetCode - 692 : Top K Frequent Words.
	 * https://leetcode.com/problems/top-k-frequent-words/
	 *
	 *	
     * Solution-2: MAX HEAP - Maintain a max heap and add all the words in it. Pop top K words to get the results.
     * 
     * Determine top-K frequent elements using Max Heap.
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
    fun topKFrequentUsingMaxHeap(words: Array<String>, k: Int): List<String> {
        val map = HashMap<String, Int>()
        val pq = PriorityQueue<String> { a, b -> 
            if((map.get(b)!! == map.get(a)!!)) {
                a.compareTo(b)
            } else {
                map.get(b)!! - map.get(a)!! 
            }
        }
        val result = mutableListOf<String>()
        var numOfElements = k
        
        words.forEach {
            map.put(it, map.getOrDefault(it, 0) + 1)
        }
        
        map.forEach { key, _ ->
            pq.offer(key)
        }
        
        while(numOfElements > 0) {
            result.add(pq.poll())
            numOfElements--
        }
        
        return result
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
    fun topKFrequentUsingMinHeap(words: Array<String>, k: Int): List<String> {
        val map = HashMap<String, Int>()
        for(i in 0 until words.size) {
            val word  =words[i]
            map[word] = map.getOrDefault(word, 0) + 1
        }

        val minHeap = PriorityQueue<Element>(object: Comparator<Element> {
            override fun compare(o1: Element, o2: Element): Int {
                if(o1.freq == o2.freq) {
                    return o2.word.compareTo(o1.word)
                }
                return o1.freq - o2.freq
            }
        })

        var result = mutableListOf<String>()
        map.forEach {
            val word = it.key
            val freq = it.value
            minHeap.add(Element(word, freq))
            if(minHeap.size > k) {
                minHeap.poll()
            }
        }

        while(minHeap.isNotEmpty()) {
            result.add(minHeap.poll().word)
        }
        return result.asReversed()
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
    fun topKFrequentElementsUsingMaxHeap(nums: IntArray, k: Int): IntArray {
                
        // create hashmap of value to frequency
        val map = hashMapOf<Int, Int>()
        for (num in nums) {
            map.put(num, map.getOrDefault(num, 0) + 1)
        }
        
        val pq = PriorityQueue { 
            a: Map.Entry<Int, Int>, b: Map.Entry<Int, Int> -> 
            b.value - a.value 
        }
        pq.addAll(map.entries)
        
        val result = IntArray(k)
        var count = 0 

        while (!pq.isEmpty()) {
            val entry: Map.Entry<Int, Int> = pq.remove()
            result[count] = entry.key
            count = count + 1
            if(count == k){
                break
            }
        }        
        return result
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
    fun topKFrequentElementsUsingMinHeap(nums: IntArray, k: Int): IntArray {
        val map = HashMap<Int, Int>()
        val pq = PriorityQueue<Int>(Comparator { a, b ->
                            map[a]!!.compareTo(map[b]!!) })
    
        for (n in nums)
            map[n] = (map[n] ?: 0) + 1
    
        for (m in map) {
            pq.offer(m.key)
            if (pq.size > k)
                pq.poll()
        }
    
        return pq.toIntArray()
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
     * Kotlin Learning Point:
     * --------------------
     * Declare fields as non-nullable and also write one validation method to check 
     * for nulls, which validate fields for null check. As fields foo& bar are declared 
     * as non-nullable, Your IDE starts complaining about it, and you can suppress it 
     * using SENSELESS_COMPARISON annotation
     * 
     * Example:
     * 
     * data class ResponseFromBackend(val foo: String, val bar:String)
     * 
     * @Suppress("SENSELESS_COMPARISON")
     * fun ResponseFromBackend.isValid(): Boolean {
     *     if (foo == null)
     *         return false
     *     if (bar == null)
     *         return false
     *     return true
     * }
     * 
     * Now as we have checked for null at one place we can use foo& bar 
     * without checking for null checks in subsequent code blocks like this
     * 
     * doSomethingAwesomeWithFoo(foo)
     * 
     * Reference: 
     * https://medium.com/@akhilgupta.me/senseless-comparison-making-sense-in-kotlin-e589e124aa4e
     * 
     * @param words
     * @param k
     * @return
     */
    @Suppress("SENSELESS_COMPARISON")
    fun topKFrequentElementsUsingBucketSort(nums: IntArray, k: Int): IntArray {
        val result = ArrayList<Int>()
        val frequencyMap = HashMap<Int, Int>()
        for (num in nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1)
        }
        /*
        * bucket to save that numbers appear the same frequency, for example:
        * 1,1,1,2,2,3, bucket[3]=1, bucket[2]=2
        * */
        val bucket = Array<ArrayList<Int>>(nums.size + 1) { ArrayList() }
        for (item in frequencyMap) {
            val frequency = item.value
            if (bucket[frequency] == null) {
                bucket[frequency] = ArrayList()
            }
            bucket[frequency].add(item.key)
        }
        var i = bucket.size - 1
        while (i > 0 && result.size < k) {
            if (bucket[i]!=null){
                result.addAll(bucket[i])
            }
            i--
        }
        return result.toIntArray()
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
    fun topKFrequentElementsUsingQuickSelect(nums: IntArray, k: Int): IntArray {
        val freq = HashMap<Int, Int>()
        for (n in nums) freq[n] = freq.getOrDefault(n, 0) + 1
        val arr = freq.keys.toIntArray()
    
        fun partition(start: Int, end: Int): Int {
            var l = start; var r = end
            var pivot = start
            while (l <= r) {
                while (l <= r && freq[arr[l]]!! <= freq[arr[pivot]]!!) l++
                while (l <= r && freq[arr[r]]!! > freq[arr[pivot]]!!) r--
                if (l < r) arr[l] = arr[r].also { arr[r] = arr[l] }
            }
            arr[r] = arr[pivot].also { arr[pivot] = arr[r] }
            return r
        }
    
        fun quickSelect(start: Int, end: Int, k: Int) {
            if (start >= end) return
            val pi = partition(start, end)
            if (pi < k) quickSelect(pi + 1, end, k)
            else if (pi > k) quickSelect(start, pi - 1, k)
        }
    
        quickSelect(0, arr.size - 1, arr.size - k)
        return arr.sliceArray(arr.size - k..arr.size - 1)
    }
}

data class Element(val word: String, val freq: Int)