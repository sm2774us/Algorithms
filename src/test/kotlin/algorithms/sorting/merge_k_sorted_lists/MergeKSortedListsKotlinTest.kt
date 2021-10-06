package algorithms.sorting.merge_k_sorted_lists

import org.junit.jupiter.api.Assertions.assertArrayEquals
import java.lang.Exception

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import algorithms.sorting.common_data_structures.ListNodeKotlin

class MergeKSortedListsKotlinTest {

    private val mergeKSortedListsKotlin: MergeKSortedListsKotlin = MergeKSortedListsKotlin()

    private fun arrayToLinkedList(nums: IntArray?): ListNodeKotlin? {
        if (nums == null) {
            return null
        }
        val head: ListNodeKotlin
        var current: ListNodeKotlin
        head = ListNodeKotlin(0)
        current = head
        for (i in nums.indices) {
            current.next = ListNodeKotlin(nums[i])
            current = current.next!!
        }
        return head.next
    }

    private fun linkedListToArray(head: ListNodeKotlin?): IntArray? {
        if (head == null) {
            return null
        }
        var len = 1
        var current: ListNodeKotlin = head
        while (current.next != null) {
            len++
            current = current.next!!
        }
        val nums = IntArray(len)
        current = head
        for (i in 0 until len) {
            nums[i] = current.`val`
            //current = current.next
            if (current.next != null) {
                current = current.next!!
            }
        }
        return nums
    }

    @Test
    @Throws(Exception::class)
    fun leetcodeExample1() {
        val arrayLists = arrayOf(intArrayOf(1, 4, 5), intArrayOf(1, 3, 4), intArrayOf(2, 6))
        val expected = intArrayOf(1, 1, 2, 3, 4, 4, 5, 6)
        val lists: Array<ListNodeKotlin?> = arrayOfNulls<ListNodeKotlin>(arrayLists.size)
        for (i in arrayLists.indices) lists[i] = arrayToLinkedList(arrayLists[i])
        val resultList: ListNodeKotlin? = mergeKSortedListsKotlin.mergeKLists(lists)
        val result = linkedListToArray(resultList)
        assertArrayEquals(expected, result)
    }

    @Test
    @Throws(Exception::class)
    fun leetcodeExample2() {
        val arrayLists = arrayOf(intArrayOf(1, 4, 5, 5, 9), intArrayOf(1), intArrayOf(-1, -2, 0, 1, 11))
        val expected = intArrayOf(-1, -2, 0, 1, 1, 1, 4, 5, 5, 9, 11)
        val lists: Array<ListNodeKotlin?> = arrayOfNulls<ListNodeKotlin>(arrayLists.size)
        for (i in arrayLists.indices) lists[i] = arrayToLinkedList(arrayLists[i])
        val resultList: ListNodeKotlin? = mergeKSortedListsKotlin.mergeKLists(lists)
        val result = linkedListToArray(resultList)
        assertArrayEquals(expected, result)
    }
}