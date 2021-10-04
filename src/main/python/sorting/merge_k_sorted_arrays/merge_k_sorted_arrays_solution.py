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
