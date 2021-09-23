package algorithms.sorting.topk

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

}