# Algorithms and Solutions

## LeetCode

| No. | LC-#     | Title	                                                                                                                                             | url                                                                                           | Time                                                       | Space                 | Difficulty | Data_Structure     | Algorithm                    | Solutions    | Test Case(s) |
| --- | -------- | ----------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- | ---------------------------------------------------------- | --------------------- | ---------- | ------------------ | ---------------------------- | ------------ | ------------ |
| 1   | 692      | [Top K Frequent Words](https://github.com/sm2774us/Algorithms#lc-692top-k-frequent-words)                                                             | https://leetcode.com/problems/top-k-frequent-words/                                           | _O(K*log(N))_                                              | _O(N)_                | Medium     | Max Heap           | Sorting                      | [Java](src/main/java/algorithms/sorting/topk/TopKFrequent.java)<br>[Kotlin](src/main/kotlin/algorithms/sorting/topk/TopKFrequentKotlin.kt)<br>[Python](src/main/python/sorting/top_k/top_k_frequent_solution.py)<br>[TypeScript](src/main/typescript/top_k_frequent.ts)                                                                             | [Java](src/test/java/algorithms/sorting/topk/TopKFrequentTest.java)<br>[Kotlin](src/test/kotlin/algorithms/sorting/topk/TopKFrequentKotlinTest.kt)<br>[Python](src/main/python/sorting/top_k/top_k_frequent_solution.py)<br>[TypeScript](src/main/typescript/top_k_frequent_test.ts)                                                                             |
| 2   | 347      | [Top K Frequent Elements](https://github.com/sm2774us/Algorithms#lc-347top-k-frequent-elements)                                                       | https://leetcode.com/problems/top-k-frequent-elements/                                        | _O(N)_                                                     | _O(N)_                | Medium     | Hash Map; Array    | Sorting ; Bucket Sort        | [Java](src/main/java/algorithms/sorting/topk/TopKFrequent.java)<br>[Kotlin](src/main/kotlin/algorithms/sorting/topk/TopKFrequentKotlin.kt)<br>[Python](src/main/python/sorting/top_k/top_k_frequent_solution.py)<br>[TypeScript](src/main/typescript/top_k_frequent_elements.ts)                                                                    | [Java](src/test/java/algorithms/sorting/topk/TopKFrequentTest.java)<br>[Kotlin](src/test/kotlin/algorithms/sorting/topk/TopKFrequentKotlinTest.kt)<br>[Python](src/main/python/sorting/top_k/top_k_frequent_solution.py)<br>[TypeScript](src/main/typescript/top_k_frequent_elements_test.ts)                                                                    |
| 3   |          | [Facebook Interview - Merge K Sorted Arrays - Facebook Interview](https://github.com/sm2774us/Algorithms#facebook-interview---merge-k-sorted-arrays)  | https://leetcode.com/discuss/interview-question/617596/facebook-onsite-merge-k-sorted-arrays  | _O(N*log(K))_                                              | _O(N+K)_              | Hard       | Max Heap           | Sorting                      | [Java](src/main/java/algorithms/sorting/merge_k_sorted_arrays/MergeKSortedArrays.java)<br>[Kotlin](src/main/kotlin/algorithms/sorting/merge_k_sorted_arrays/MergeKSortedArraysKotlin.kt)<br>[Python](src/main/python/sorting/merge_k_sorted_arrays/merge_k_sorted_arrays_solution.py)<br>[TypeScript](src/main/typescript/merge_k_sorted_arrays.ts) | [Java](src/test/java/algorithms/sorting/merge_k_sorted_arrays/MergeKSortedArraysTest.java)<br>[Kotlin](src/test/kotlin/algorithms/sorting/merge_k_sorted_arrays/MergeKSortedArraysKotlinTest.kt)<br>[Python](src/main/python/sorting/merge_k_sorted_arrays/merge_k_sorted_arrays_solution.py)<br>[TypeScript](src/main/typescript/merge_k_sorted_arrays_test.ts) |
| 4   | 23       | [Merge K Sorted Lists](https://github.com/sm2774us/Algorithms#lc-23merge-k-sorted-lists)                                                              | https://leetcode.com/problems/merge-k-sorted-lists/                                           | _O(N)_                                                     | _O(N)_                | Hard       | Max Heap           | Sorting                      | [Java](src/main/java/algorithms/sorting/merge_k_sorted_lists/MergeKSortedLists.java)<br>[Kotlin](src/main/kotlin/algorithms/sorting/merge_k_sorted_lists/MergeKSortedListsKotlin.kt)<br>[Python](src/main/python/sorting/merge_k_sorted_lists/merge_k_sorted_lists_solution.py)<br>[TypeScript](src/main/typescript/merge_k_sorted_lists.ts)        | [Java](src/test/java/algorithms/sorting/merge_k_sorted_lists/MergeKSortedListsTest.java)<br>[Kotlin](src/test/kotlin/algorithms/sorting/merge_k_sorted_lists/MergeKSortedListsKotlinTest.kt)<br>[Python](src/main/python/sorting/merge_k_sorted_lists/merge_k_sorted_lists_solution.py)<br>[TypeScript](src/main/typescript/merge_k_sorted_lists_test.ts)        |


#### [LC-692:Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/)
##### Solution Explanation
```
MAX HEAP
  1. Create hash table for the words with frequency
  2. Create maxHeap with words and add them to it
  3. Poll top k from the maxHeap and add them to Result list
```
##### Complexity Analysis
```
Time Complexity  : O(K*log(N))
                   O(N) for heapify 
                   and 
                   O(K*log(N)) for poping k times
 
                   Highest Time Complexity of O(K*log(N)) v/s O(N) so,
                   instead of: O(N+K*log(N))
                   
				   TC = O(K*log(N))

Space Complexity : O(N)         , for heap
```
```java
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

}
```
```kotlin
package algorithms.sorting.topk

import java.util.PriorityQueue

import kotlin.Comparator
import kotlin.collections.List
import kotlin.collections.HashMap

class TopKFrequentKotlin {

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

}
```
```python3
import collections
import heapq

from typing import List

import unittest

class Solution:
    ##
    #  LeetCode - 692 : Top K Frequent Words.
    #  https://leetcode.com/problems/top-k-frequent-words/
    #
    #	
    # Solution-2: MAX HEAP
    # 
    # 1. Create hash table for the words with frequency
    # 2. Create maxHeap with words and add them to it
    # 3. Poll top k from the maxHeap and add them to Result list
    # 
    # Time Complexity  : O(K*log(N))
    #                    O(N) for heapify 
    #                    and 
    #                    O(K*log(N)) for poping k times
    # 
    #                    Highest Time Complexity of O(K*log(N)) v/s O(N) so,
    #                    instead of: O(N+K*log(N))
    #                    TC = O(K*log(N))
    #
    # Space Complexity : O(N)         , for heap
    #
    def topKFrequentUsingMaxHeap(self, words: List[str], k: int) -> List[str]:
        freqs = collections.Counter(words)
        h = [(-freq, word) for word, freq in freqs.items()]
        heapq.heapify(h)
        return [heapq.heappop(h)[1] for _ in range(k)]

class Test(unittest.TestCase):
    def setUp(self):
        '''before each test function'''
        pass

    def tearDown(self):
        '''after each test function'''
        pass

    def test_topKFrequentWords(self):
        sol = Solution()
        for words, k, solution in (
            [ 
                ["i","love","leetcode","i","love","coding"], 
                2, 
                ["i","love"] 
            ],
            [ 
                ["the","day","is","sunny","the","the","the","sunny","is","is"], 
                4, 
                ["the","is","sunny","day"] 
            ]
        ):
            self.assertEqual(sol.topKFrequentUsingMaxHeap(words, k), solution)

if __name__ == '__main__':
    unittest.main()
```
```typescript
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
 * @param {string[]} words
 * @param {number} k
 * @returns {string[]}
 */
export function topKFrequentUsingMaxHeap(words: string[], k: number): string[] {
  const wordsMap: Record<string, number> = createWordsMap(words);

  const queue: PriorityQueue<string> = new PriorityQueue<string>((a: string, b: string) => {
    if (wordsMap[a] > wordsMap[b]) {
      return true;
    }

    if (wordsMap[a] === wordsMap[b]) {
      // THE COMPARISON OF LETTER STRINGS IN JS IS BASED ON ASCII CODES
      return a < b;
    }
    return false;
  });

  Object.keys(wordsMap).forEach((word) => queue.insert(word));

  const answer: string[] = [];
  while (k--) {
    answer.push(<string> queue.poll());
  }

  return answer;
}

function createWordsMap(words: string[]): Record<string, number> {
  return words.reduce((map, word) => {
    if (map[word] !== undefined) {
      map[word]++;
    } else {
      map[word] = 1;
    }
    return map;
  }, {} as Record<string, number>);
}

function swap<E>(list: E[], a: number, b: number) {
  [list[a], list[b]] = [list[b], list[a]];
}

class PriorityQueue<E> {
  private heap: [null, ...E[]] = [null];
  constructor(private comparator: (a: E, b: E) => boolean) {}

  get size(): number {
    return this.heap.length - 1;
  }

  sink(index: number) {
    const { heap } = this;
    while (2 * index <= this.size) {
      // the child elements of the k item are 2 x k and 2 x k x 1
      let child = 2 * index;
      if (
        child < this.size &&
        this.comparator(heap[child + 1] as E, heap[child] as E)
      ) {
        child++;
      }
      if (!this.comparator(heap[child] as E, heap[index] as E)) break;
      swap(heap, index, child);
      index = child;
    }
  }

  swim(index: number) {
    const { heap } = this;
    while (
      index > 1 &&
      this.comparator(heap[index] as E, heap[index >> 1] as E)
    ) {
      const parent = index >> 1;
      swap(heap, index, parent);
      index = parent;
    }
  }

  insert(el: E) {
    const newIndex: number = this.size + 1;
    this.heap[newIndex] = el;
    this.swim(newIndex);
  }

  poll(): E | undefined {
    if (this.size <= 0) return;
    const deleted: E = this.heap[1];
    swap(this.heap, 1, this.size);
    this.heap.length -= 1;
    this.sink(1);
    return deleted;
  }
}
```

#### [LC-347:Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)
##### Solution Explanation
```
BUCKET SORT
----------------
It is intuitive to map a value to its frequency. 
Then our problem becomes 'to sort map entries by their values'.
Since frequency is within the range [1, n] for n is the number of elements, 
we could apply the idea of Bucket Sort:
  1) We divide frequencies into n + 1 buckets, in this way, the list in buckets[i] contains elements with the same frequency i
  2) Then, we go through the buckets from tail to head until we collect k elements.

Determine top-K frequent elements using Bucket Sort.
```
##### Java Learning Point:
```
You can create an array of lists but you cannot use type during initalization.

Example:
List<Integer>[] lists=new List[10];

Why this works? 
Because the lists variable points to an array data structure 
whose data type is List<Integer>. This array contains a set of references 
to different objects of type List<Integer>, and this is why if we try 
to run lists[i]=new ArrayList<String>(); it will not compile. 
However when we initialize the array itself we don't need to provide 
the type of the List objects as List since from JVM point of view a 
List of Integer objects and a List of Object objects will require 
the same number of bytes as logn as their sizes is same. 
The only constraint comes when we set a array member to a value 
(of type List - it has to be List<Integer> not anything else)

You can type cast the List[] to a List<Integer>[] but the end result 
and the JVM behavior is the same.

Reference: 
https://stackoverflow.com/questions/15636558/how-to-new-a-listinteger-in-java
```
##### Complexity Analysis
```
Time Complexity  : O(N)
Space Complexity : O(N)
```
```java
import java.util.*;

public class TopKFrequent {

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


}
```
```kotlin
package algorithms.sorting.topk

import kotlin.collections.ArrayList
import kotlin.collections.List
import kotlin.collections.HashMap

class TopKFrequentKotlin {

    /**
	 * LeetCode - 347 : Top K Frequent Elements.
	 * https://leetcode.com/problems/top-k-frequent-elements/
	 *
     * BUCKET SORT -
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

}
```
```python3
import collections
from itertools import chain

from typing import List

import unittest

class Solution:
    ##
	# LeetCode - 347 : Top K Frequent Elements.
	# https://leetcode.com/problems/top-k-frequent-elements/
	#
    # BUCKET SORT -
    # 
    # There are solution, using quickselect with O(n) complexity in average, 
    # but I think they are overcomplicated: actually, there is O(n) solution, 
    # using bucket sort. The idea, is that frequency of any element can not be 
    # more than n. So, the plan is the following:
    # 
    #   1. Create list of empty lists for bucktes: for frequencies 1, 2, ..., n.
    #   2. Use Counter to count frequencies of elements in nums
    #   3. Iterate over our Counter and add elements to corresponding buckets.
    #   4. buckets is list of lists now, create one big list out of it.
    #   5. Finally, take the k last elements from this list, these elements will be top K frequent elements.
    # 
    # Complexity: 
    # time complexity is O(n), because we first iterate over nums once and create buckets, then we flatten list of lists with total number of elements O(n) and finally we return last k elements. 
    # Space complexity is also O(n).
    # 
    # Time Complexity  : O(N)
    # Space Complexity : O(N)
    #    
    def topKFrequentElementsUsingBucketSort(self, nums: List[int], k: int) -> List[int]:
        bucket = [[] for _ in range(len(nums) + 1)]
        count = collections.Counter(nums).items()  
        for num, freq in count: bucket[freq].append(num) 
        flat_list = list(chain(*bucket))
        return flat_list[::-1][:k]

    def topKFrequentElementsUsingBucketSortLongVersion(sefl, nums: List[int], k: int) -> List[int]:
        m = {}
        for n in nums:
            if n in m:
                m[n] += 1
                continue
            m[n] = 1

        buckets = [[] for i in range(len(nums))]
        for key, val in m.items():
            buckets[val - 1].append(key)

        res = []
        for bucket in buckets[::-1]:
            for bucket_num in bucket:
                if len(res) == k:
                    return res
                res.append(bucket_num)
        return res

class Test(unittest.TestCase):
    def setUp(self):
        '''before each test function'''
        pass

    def tearDown(self):
        '''after each test function'''
        pass

    def test_topKFrequentElements(self):
        sol = Solution()
        for nums, k, solution in (
            [ [1,1,1,2,2,3], 2, [1,2] ],
            [ [1], 1, [1] ]
        ):
            self.assertEqual(sol.topKFrequentElementsUsingBucketSort(nums, k), solution)
            self.assertEqual(sol.topKFrequentElementsUsingBucketSortLongVersion(nums, k), solution)

if __name__ == '__main__':
    unittest.main()
```
```typescript
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
 * @param {number[]} nums
 * @param {number} k
 * @returns {number[]}
 */
export function topKFrequentElementsUsingBucketSort(
  nums: number[],
  k: number
): number[] {
  // Map of counts
  const counts = new Map<number, number>();
  for (const num of nums) counts.set(num, (counts?.get(num) ?? 0) + 1);
  if (counts.size === k) return [...counts.keys()];

  const buckets: number[][] = Array(nums.length + 1)
    .fill(undefined)
    .map(() => []); // count => [nums]
  for (const [num, count] of counts.entries()) buckets[count].push(num);

  const result: number[] = [];
  for (let i = buckets.length - 1; i >= 0; i--) {
    const bucket = buckets[i];
    const remaining = k - result.length;
    result.push(...bucket.slice(0, remaining));
    if (result.length === k) break;
  }

  return result;
}
```

#### [Facebook Interview - Merge K Sorted Arrays](https://leetcode.com/discuss/interview-question/617596/facebook-onsite-merge-k-sorted-arrays)
##### Solution Explanation
```
MAX HEAP Solution
```
##### Complexity Analysis
```
Time Complexity  : O(N*log(K))
Space Complexity : O(N+K)
```
```java
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
```
```kotlin
package algorithms.sorting.merge_k_sorted_arrays

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
```
```python3
import heapq
from typing import List

import unittest

class Solution:

    ##
    # Facebook Interview Question.
    # 
    # Implement a method to merge K sorted arrays.
    # The time complexity must be better than O(N*K).
    # Method signature (C++): vector<char> merge(vector<vector<char>> chunks)
    # 
    # Reference:
    # https://leetcode.com/discuss/interview-question/617596/facebook-onsite-merge-k-sorted-arrays
    # 
    # MAX HEAP Solution.
    # 
    # Time Complexity  : O(N*log(K))
    # Space Complexity : O(N+K)
    # 
    # @param arrays
    # @return
    #
    def mergeSortedArrays(self, arrays: List[List[int]]) -> List[int]:
        final_list = []
        heap = [(mylst[0], i, 0) for i, mylst in enumerate(arrays) if mylst]
        heapq.heapify(heap)

        while heap:
            val, list_ind, element_ind = heapq.heappop(heap)
            final_list.append(val)
            if element_ind + 1 < len(arrays[list_ind]):
                next_tuple = (arrays[list_ind][element_ind + 1],
                              list_ind,
                              element_ind + 1)
                heapq.heappush(heap, next_tuple)
        return final_list

class Test(unittest.TestCase):
    def setUp(self):
        '''before each test function'''
        pass

    def tearDown(self):
        '''after each test function'''
        pass

    def test_mergeSortedArrays(self):
        sol = Solution()
        for arrays, solution in (
            [ 
                [[1,4,5,10],[1,3,4],[2,6],[]],                 
                [1,1,2,3,4,4,5,6,10]
            ],
            [ 
                [[1,2],[2,3],[]], 
                [1,2,2,3] 
            ]
        ):
            self.assertEqual(sol.mergeSortedArrays(arrays), solution)
```
```typescript
/* 
  Using Binary Heap (Optimized) ... MAX HEAP.

  Time Complexity - O(nklog(k)) or O(N*log(K))
  - Heap size will be k. So Heapifying is O(log(k))
  - We're going to be heapifying n * k elements. Therefore, T(n) = O(nklog(k))

  Space Complexity - O(nk) + O(k) from the heap.
					 or,
					 O(N+K)  
*/
export function mergeArraysUsingMaxHeap(arrays: number[][]): number[] {
	const merged: number[] = [],
		increasingOrder: boolean = isIncreasingOrder(arrays);
	// [0]
	const heap: MinBinaryHeap | MaxBinaryHeap = increasingOrder ? new MinBinaryHeap() : new MaxBinaryHeap();

	// [1]
	arrays.forEach((arr, i) => {
		heap.insert(new HeapNode(i, 0, arr[0]));
	})

	heap.heapify();

	if (increasingOrder) {
		// [2]
		while (heap.top.value !== Number.POSITIVE_INFINITY) {
			getAndInsertNextVal(arrays, heap, merged, Infinity);
		}
	} else {
		while (heap.top.value !== Number.NEGATIVE_INFINITY) {
			getAndInsertNextVal(arrays, heap, merged, -Infinity);
		}
	}

	// Filter out null's and undefined's from merged array.
	//const result: number[] = merged.filter(item => !!item);
	const result: number[] = merged.filter(item => item !== undefined && item !== null);
	return result;
}

/* 
Notes: 
[0] Create min or max heap based on order
[1] - Insert first element of all arrays into heap.
    - Each heap item will be an instance of HeapNode which will keep track of 
      the arrIndex, elementIndex & value.
[2] - Keep getting heap top(min) & pushing it into the merged array.
    - If the min value is Infinity, we've reached the end of all arrays
*/

/* 
getAndInsertNextVal:
  - Function to get the top(min or max val), push top val into merged
    & insert next array value into heap
  - instead of extracting & inserting, we're going to swap the new value with the top value.
    So we don't have to re-heapify twice for extraction & insertion
  - We'll use the arrIndex, elementIndex from the heap top to get the next item to insert
  - If we're at the end of the array insert Infinity(min heap or increasing order) or -Infinity
*/
function getAndInsertNextVal(arrays: number[][], heap: MinBinaryHeap | MaxBinaryHeap, merged: number[], infinity: number): void {
	const n: number = arrays[0].length;
	let top: HeapNode = heap.top;

	merged.push(top.value)

	const arrIndex = top.arrIndex,
		elementIndex = top.elementIndex + 1,
		value = elementIndex >= n ? infinity : arrays[arrIndex][elementIndex]

	heap.swapTop(new HeapNode(arrIndex, elementIndex, value))
	heap.sinkDown(0)
}

// HeapNode will keep track of the array index, the element index & the value
class HeapNode {

    private _arrIndex: number;
    private _elementIndex: number;
    private _value: number;

	constructor(arrIndex: number, elementIndex: number, value: number) {
		this._arrIndex = arrIndex;
		this._elementIndex = elementIndex;
		this._value = value;
	}

    // Getters
    get arrIndex(): number {
        return this._arrIndex;
    }

    // Getters
    get elementIndex(): number {
        return this._elementIndex;
    }

    // Getters
    get value(): number {
        return this._value;
    }
}

// Modified Binary Heaps
// Unmodified Binary Heap implementation here --> https://blog.mrinalini.dev/posts/binary-heap/
class BinaryHeap {
    private _heapArr: HeapNode[] = [];

	constructor() {
	}
    
	// Getters
    get heapArr(): HeapNode[] {
        return this._heapArr;
    }

	get size(): number {
		return this._heapArr.length;
	}

	get top(): HeapNode {
		return this._heapArr[0];
	}

	// Class methods
	insert(node: HeapNode): void {
		this._heapArr[this.size] = node;
	}

	swapTop(newNode: HeapNode): void {
		this._heapArr[0] = newNode;
	}

	heapify(): void {
		const lastIndex: number = this.size - 1;
		for (let i = lastIndex; i >= 0; i--) {
			this.sinkDown(i);
		}
	}
	// sinkDown will be different for max & min heap
	sinkDown(p: number): void {}
}

// Min Binary Heap for increasing sort order
class MinBinaryHeap extends BinaryHeap {
	constructor() {
		super();
	}

	sinkDown(p: number): void {
		const element: number = this.heapArr[p].value,
			lastIndex: number = this.heapArr.length - 1;

		while (p < lastIndex) {
			const r = 2 * p + 2,
				l = r - 1,
				left = this.heapArr[l] && this.heapArr[l].value,
				right = this.heapArr[r] && this.heapArr[r].value;

			let swapIndex = null,
				min = element;

			if (l <= lastIndex && left < min) {
				swapIndex = l;
				min = left;
			}

			if (r <= lastIndex && right < min) {
				swapIndex = r;
			}

			if (swapIndex == null) break

			swap(this.heapArr, p, swapIndex);

			p = swapIndex;
		}
	}
}

type Nullable<T> = T | null;

// Max Binary Heap for decreasing sort order
class MaxBinaryHeap extends BinaryHeap {
	constructor() {
		super();
	}

	sinkDown(p: number): void {
		const element: number = this.heapArr[p].value,
			lastIndex: number = this.heapArr.length - 1;

		while (p < lastIndex) {
			const r: number = 2 * p + 2,
				l: number = r - 1,
				left: number = this.heapArr[l] && this.heapArr[l].value,
				right: number = this.heapArr[r] && this.heapArr[r].value;

			let swapIndex: Nullable<number> = null,
				max: number = element;

			if (l <= lastIndex && left > max) {
				swapIndex = l;
				max = left;
			}

			if (r <= lastIndex && right > max) {
				swapIndex = r;
			}

			if (swapIndex == null) break;

			swap(this.heapArr, p, swapIndex);

			p = swapIndex;
		}
	}
}

// --------------------------------------------------------------
/* 
isIncreasingOrder
 - Function to get the sort order of the arrays
 - Can't just check the first two values of the first array cause they might be repeated values
 - So keep checking until two values are different & return true or false
*/
function isIncreasingOrder(arrays: number[][]): boolean {
	let i: number = 0,
		k: number = arrays.length;

	while (i < k) {
		const arr: number[] = arrays[i],
			len: number = arr.length;

		for (let j = 1; j < len; j++) {
			const diff = arr[j] - arr[j - 1];
			if (diff > 0) return true;
			else if (diff < 0) return false;
			else continue;
		}
		i++;
	}
	return false;
}

function swap(arr: HeapNode[], i: number, j: number) {
	const temp: HeapNode = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
}
```

#### [LC-23:Merge K Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)
##### Solution Explanation
```
Solution - 1: MIN HEAP
 
In order to build one sorted list, we always want the minimum node among heads of lists.
That's natural to Min Heap.

Pseudo Code:
---------------------------------------
1. Create a minHeap based on the value of the node.
2. Fill the minHeap with the input lists
3. Get the top node from the minHeap, put it into our result.
4. Modify the top node by removing its head that we just put into our result.
5. Put the modified top node back to the minHeap
6. Back to 3.
```
##### Complexity Analysis
```
Time Complexity  : O(N*log(K))

                   where, 
                   N = total number of numbers of all lists.
                   K = the number of linked list.
                    
                   Min Heap contains at most K ListNode(s).
                   There are N ListNode to insert, so overall 
                   the time complexity is O(N*log(K))
 
Space Complexity : O(K)
                   stores the head of the LinkedList, 
                   thus at any time, the Min Heap contains 
                   at most K key-value pairs.
```
```java
package algorithms.sorting.merge_k_sorted_lists;

import java.util.PriorityQueue;

import algorithms.sorting.common_data_structures.ListNode;

public class MergeKSortedLists {

    /**
	 * LeetCode - 23 : Merge K Sorted Lists.
	 * https://leetcode.com/problems/merge-k-sorted-lists/
     * 
     * Solution - 1: MIN HEAP
     * 
     * In order to build one sorted list, we always want the minimum node among heads of lists.
     * That's natural to Min Heap.
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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode head : lists) {
            if (head != null) // Ensures no "null" in minHeap
                minHeap.offer(head);
        }
        
        ListNode dummyHead = new ListNode(), cur = dummyHead;
        while (!minHeap.isEmpty()) {
            ListNode min = minHeap.poll();
            cur.next = min;
            cur = cur.next;
            
            if (min.next != null) // Ensures no "null" in minHeap
                minHeap.offer(min.next);
        }
        
        return dummyHead.next;
    }

}
```
```kotlin
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
```
```python3
import heapq

from typing import List

import unittest

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __eq__(self, other):
        if not self.equal(other):
            # print("List1 != List2 where")
            # print("List1:")
            # print(str(self))
            # print("List2:")
            # print(str(other))
            # print("\n")
            return False
        else:
            return True

    def equal(self, other):
        if other is not None:
            return self.val == other.val and self.next == other.next
        else:
            return False

    def __repr__(self):
        lst = []
        p = self
        while p:
            lst.append(str(p.val))
            p = p.next

        return "List: [{}].".format(",".join(lst))

    def initList(self, nums):
        if not nums:
            return None
        head = None
        current = None

        for n in nums:
            if not head:
                head = ListNode(n)
                current = head
            else:
                node = ListNode(n)
                current.next = node
                current = node
        return head

    def printList(self, head):
        string = ""
        if not head:
            return string
        while head.next:
            if head.val is None:
                string += "%s->" % str(head.val)
            else:
                string += "%d->" % head.val
            head = head.next
        if head.val is None:
            string += "%s->" % str(head.val)
        else:
            string += "%d" % head.val
        return string

class Solution:

    ##
	# LeetCode - 23 : Merge K Sorted Lists.
	# https://leetcode.com/problems/merge-k-sorted-lists/
    #
    # Solution - 1: MIN HEAP
    #
    # In order to build one sorted list, we always want the minimum node among heads of lists.
    # That's natural to Min Heap.
    # 
    # Pseudo Code:
    # ---------------------------------------
    # 1. Create a minHeap based on the value of the node.
    # 2. Fill the minHeap with the input lists
    # 3. Get the top node from the minHeap, put it into our result.
    # 4. Modify the top node by removing its head that we just put into our result.
    # 5. Put the modified top node back to the minHeap
    # 6. Back to 3.
    # ---------------------------------------
    # 
    # The complexity of this problem is O(knlogk), no algorithm can run it O(nlogk). We have T(n) = 2T(n/2) + kn, and therefore, O(kn*logk).
    # when people talking about time complexity; say, O(nlogk)
    # Can we first clarify what is "n" please?
    # If n is total number of numbers of all lists, then it's nlogk;
    # if n is average length of each list, then it's nklogk;
    # Without clarification it'll cause lots of confusion.
    # 
    # Time Complexity  : O(N*log(K)), 
    #                    where, 
    #                    N = total number of numbers of all lists.
    #                    K = the number of linked list.
    #                    
    #                    Min Heap contains at most K ListNode(s).
    #                    There are N ListNode to insert, so overall 
    #                    the time complexity is O(N*log(K))
    # 
    # Space Complexity : O(K)
    #                    stores the head of the LinkedList, 
    #                    thus at any time, the Min Heap contains 
    #                    at most K key-value pairs.
    # 
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        curr = head = ListNode(0)
        queue = []
        count = 0
        for l in lists:
            if l is not None:
                count += 1
                heapq.heappush(queue, (l.val, count, l))
        while len(queue) > 0:
            _, _, curr.next = heapq.heappop(queue)
            curr = curr.next
            if curr.next is not None:
                count += 1
                heapq.heappush(queue, (curr.next.val, count, curr.next))
        return head.next    

class Test(unittest.TestCase):
    def setUp(self):
        '''before each test function'''
        pass

    def tearDown(self):
        '''after each test function'''
        pass

    def test_mergeKLists(self):
        listNode = ListNode()
        s = Solution()
        self.assertEqual(
            listNode.initList([1, 1, 2, 3, 4, 4, 5, 6]),
            s.mergeKListsUsingHeap(
                [
                    listNode.initList([1, 4, 5]),
                    listNode.initList([1, 3, 4]),
                    listNode.initList([2, 6]),
                ]
            ),
        )
        self.assertEqual(listNode.initList([]), s.mergeKListsUsingHeap([]))
        self.assertEqual(
            listNode.initList([]), s.mergeKListsUsingHeap(listNode.initList([]))
        )
```
```typescript
/**
 * Definition for singly-linked list.
 */ 
class ListNode {
    
	val: number;
    next: ListNode | null;
	
	constructor(val?: number, next?: ListNode | null) {
    	this.val = (val===undefined ? 0 : val);
    	this.next = (next===undefined ? null : next);
	}

}

/**
 * LeetCode - 23 : Merge K Sorted Lists.
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * 
 * Solution - 1: MIN HEAP
 * 
 * In order to build one sorted list, we always want the minimum node among heads of lists.
 * That's natural to Min Heap.
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
 * @param {Array<ListNode | null>} lists
 * @returns {ListNode | null}
 */
export function mergeKLists(lists: Array<ListNode | null>): ListNode | null {
    const pq = new BinaryHeap<ListNode>((a, b) => a.val < b.val);
    
    for (const listNode of lists) {
        if (listNode !== null) pq.push(listNode);
    }
    
    let dummyHead = new ListNode();
    let head = dummyHead;
    while(pq.size() > 0) {
        const listNode = pq.peek();
        if (listNode.next) pq.push(listNode.next);
        
        head.next = listNode;
        head = head.next;
    }
    
    return dummyHead.next;
};

function swap<T>(list: T[], i: number, j: number): T[] {
    if (i !== j) {
        [list[i], list[j]] = [list[j], list[i]];
    }
    return list;
}

class BinaryHeap<T> {
    private harr: T[] = [];

    constructor(private lessThan: (a:T, b:T) => boolean) {
    }

    size() {
        return this.harr.length;
    }

    push(v: T) {
        this.harr.push(v);
        let i = this.harr.length - 1;
        while(i > 0 && this.lessThan(this.harr[i], this.harr[this.parent(i)])) {
            swap(this.harr, i, this.parent(i));
            i = this.parent(i);
        }
    }

    peek() {
        if (this.harr.length === 0) throw new Error('Overflow');

        const [head] = this.harr;
        this.harr[0] = this.harr[this.harr.length - 1];
        this.harr.length -= 1;

        this.heapify(0);

        return head;
    }

    private heapify(i: number) {
        const l = this.left(i);
        const r = this.right(i);
        let smallest = i;
        if (l < this.harr.length && this.lessThan(this.harr[l], this.harr[i])) {
            smallest = l;
        }
        if (r < this.harr.length && this.lessThan(this.harr[r], this.harr[smallest])) {
            smallest = r;
        }
        if (smallest !== i) {
            swap(this.harr, smallest, i);
            this.heapify(smallest);
        }
    }

    private parent(i: number) {
        return Math.floor((i - 1) / 2);
    }

    private left(i: number) {
        return 2 * i + 1;
    }

    private right(i: number) {
        return 2 * i + 2;
    }
}
```