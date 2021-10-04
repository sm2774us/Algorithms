import random
import collections
import heapq
from itertools import chain

from typing import List

import unittest

class FreqWord:
    def __init__(self, freq, word):
        self.freq = freq
        self.word = word
        
    def __lt__(self, other):
        if self.freq != other.freq:
            return lt(self.freq, other.freq) # self.freq < other.freq
        else:
            # opposite sort
            return lt(other.word, self.word) # other.word < self.word

class Solution:
    ##
    #  LeetCode - 692 : Top K Frequent Words.
    #  https://leetcode.com/problems/top-k-frequent-words/
	#
	#	
    #  Solution-1: NAIVE SORT - The easiest way to think of this problem and easy to implement.
    #
    #  Determine top-K frequent elements using Naive Sorting.
    #
    #  Time Complexity  : O(N*log(N)), naive sort is O(N*log(N))
    #  Space Complexity : O(N)       , for map and list
    #
    def topKFrequentUsingNaiveSort(self, words: List[str], k: int) -> List[str]:
        counts = collections.Counter(words)
        result = sorted(counts, key=lambda word: (-counts[word], word))
        return result[:k]

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

    ##
    #  LeetCode - 692 : Top K Frequent Words.
    #  https://leetcode.com/problems/top-k-frequent-words/
	#
	#	
    #  Solution-3: MIN HEAP - Instead of using a max heap, we only store Top K Freqency word 
	#  we have met so far in our min heap.
    #
    #  Determine top-K frequent elements using Min Heap.
    #
    #  time: O(NlogK) space: O(N)
    #
    def topKFrequentUsingMinHeap(self, words: List[str], k: int) -> List[str]:
        words_with_count = collections.Counter(words)
            
        min_heap = list()
        for word, count in words_with_count.items():
            heapq.heappush(min_heap, FreqWord(count, word))
            if len(min_heap) > k:
                heapq.heappop(min_heap)
        
        return [heapq.heappop(min_heap).word for _ in range(k)][::-1]

    ##
	# LeetCode - 347 : Top K Frequent Elements.
	# https://leetcode.com/problems/top-k-frequent-elements/
	#
    # Solution-1: MAX HEAP -
    # 
    # Algorithm Steps:
    #   1. Create a frequency table
    #   2. Create a Max Heap and add all the distinct elements
    #   3. Poll top k frequent elements off the Heap
    # 
    # Time & Space Complexity Analysis:
    # 
    # N = # of elements in the input array
    # D = # of distinct (unique) elements in the input array
    # 
    # Building the HashMap: O(N) time
    #   - Add all the N elements into the HashMap and add thier frequency
    # 
    # Building the Heap: O(D) time
    #   - https://www.geeksforgeeks.org/time-complexity-of-building-a-heap/
    #   - Above is a proof that shows that building a heap of N elements will take O(N) time
    #   - In our case we are building a heap of D elements = O(D) time
    # 
    # Poll K distinct elements from the Heap: O(K log D) time
    #   - There are D elements in the Heap and we call poll() K times = O(K log D) time
    # 
    # Total Time Complexity = O(K log D)
    # Total Space Complexity = O(D), this is the size of the heap.
    # 
    def topKFrequentElementsUsingMaxHeap(self, nums: List[int], k: int) -> List[int]:
        freq_map = collections.Counter(nums)
        maxheap = []
        for key, value in freq_map.items():
            maxheap.append((-value, int(key)))
            
        result = []
        heapq.heapify(maxheap)

        while maxheap and k > 0:
            result.append(heapq.heappop(maxheap)[1])
            k -= 1
            
        return result

    ##
	# LeetCode - 347 : Top K Frequent Elements.
	# https://leetcode.com/problems/top-k-frequent-elements/
	#
    # Solution-2: MIN HEAP -
    # 
    # 1. Keep a heap which has the size of k.
    # 2. Since we are using min-heap, the top element of the heap 
    #    is the smallest frequency in a heap. 
    #    The top element of heap works as a threshold. 
    #    Only the frequency more significant than the threshold can get into the heap. 
    #    When we met a new frequency, if the new frequency is less than the top element 
    #    of the heap, move on; if the new frequency is larger than the top element 
    #    of the heap, we pop out the top element of the heap, and push the new frequency 
    #    into the heap.
    # 
    # Time & Space Complexity Analysis:
    # 
    # Time Complexity = O(N log K)
    # Space Complexity = O(K + N)
    # 
    def topKFrequentElementsUsingMinHeap(self, nums: List[int], k: int) -> List[int]:
        c = collections.Counter(nums)
        output = []
        #print(c)
        for v,f in c.items():
            #print(output)
            if len(output) < k:
                heapq.heappush(output,[f,v])
            else:
                if f > output[0][0]:
                    heapq.heappop(output)
                    heapq.heappush(output,[f,v])
        #print(output)
        res = []
        for i in range(k):
            res.append(heapq.heappop(output)[1])  
        return res[::-1]

    ##
	# LeetCode - 347 : Top K Frequent Elements.
	# https://leetcode.com/problems/top-k-frequent-elements/
	#
    # Solution-3: BUCKET SORT -
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

    ##
	# LeetCode - 347 : Top K Frequent Elements.
	# https://leetcode.com/problems/top-k-frequent-elements/
	#
    # Solution-4: QUICK SELECT
    # 
    # HashMap(Dictionary) + Quick Select Solution
    #
    # I used a dictionary to get the frequencies, and then used quick select 
    # to get the top k frequenct elements.
    #
    # 1. HashMap to store <num, freq> pair
    # 2. Quick select to select the most frequent k element 
    #    Quick select is by the freq value not the num key.
    #
    # Time Complexity  : O(N) 
    #                     ( Worst case could be O(N^2) ... O(N) is the average )
    #                     Refer to: https://en.wikipedia.org/wiki/Quickselect
    #  
    # Space Complexity : O(N)
    #
    def quick_select(self, frequencies, k):
        pivot = random.choice(list(frequencies.keys()))
        pivot_frequency = frequencies[pivot]

        left = {key: val for key, val in frequencies.items() if val > pivot_frequency}
        equal = {key: val for key, val in frequencies.items() if val == pivot_frequency}
        right = {key: val for key, val in frequencies.items() if val < pivot_frequency}

        if k < len(left):
            self.quick_select(left, k)
        elif k == len(left):
            self.output += [v for v in list(left.keys())]
            return
        elif k < len(left) + len(equal):
            self.output += [v for v in list(left.keys())]
            k -= len(left)
            self.quick_select(equal, k)
        elif k == len(left) + len(equal):
            new_output = [v for v in list(left.keys())] + [x for x in list(equal.keys())]
            self.output += new_output
        else:
            new_output = [v for v in list(left.keys())] + [x for x in list(equal.keys())]
            self.output += new_output
            k -= len(new_output)
            self.quick_select(right, k)
        
    def topKFrequentElementsUsingQuickSelect(self, nums: List[int], k: int) -> List[int]:
        frequencies = collections.defaultdict(int)
        for num in nums:
            frequencies[num] += 1
        
        self.output = []
        self.quick_select(frequencies, k)
        return self.output    
        
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
            self.assertEqual(sol.topKFrequentUsingNaiveSort(words, k), solution)
            self.assertEqual(sol.topKFrequentUsingMaxHeap(words, k), solution)
            self.assertEqual(sol.topKFrequentUsingMaxHeap(words, k), solution)

    def test_topKFrequentElements(self):
        sol = Solution()
        for nums, k, solution in (
            [ [1,1,1,2,2,3], 2, [1,2] ],
            [ [1], 1, [1] ]
        ):
            self.assertEqual(sol.topKFrequentElementsUsingMaxHeap(nums, k), solution)
            self.assertEqual(sol.topKFrequentElementsUsingMinHeap(nums, k), solution)
            self.assertEqual(sol.topKFrequentElementsUsingBucketSort(nums, k), solution)
            self.assertEqual(sol.topKFrequentElementsUsingBucketSortLongVersion(nums, k), solution)
            self.assertEqual(sol.topKFrequentElementsUsingQuickSelect(nums, k), solution)

if __name__ == '__main__':
    unittest.main()
