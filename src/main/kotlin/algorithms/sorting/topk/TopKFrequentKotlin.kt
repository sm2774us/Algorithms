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
     * Time Complexity  : O(N+K*log(N)), O(N) for heapify and O(k*log(N)) for poping k times
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
        
        map.forEach { key, value ->
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
    
}

data class Element(val word: String, val freq: Int)