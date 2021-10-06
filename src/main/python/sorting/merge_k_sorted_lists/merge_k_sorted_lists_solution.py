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