package algorithms.sorting.topk

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertArrayEquals

import kotlin.collections.List
import java.util.stream.Stream

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TopKFrequentKotlinTest {

    companion object {
        @JvmStatic
        fun topKFreqentWordsSource(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf("i","love","leetcode","i","love","coding"), 2, listOf("i","love")),
                Arguments.of(arrayOf("the","day","is","sunny","the","the","the","sunny","is","is"), 4, listOf("the","is","sunny","day"))
            )
        }

        @JvmStatic
        fun topKFreqentElementsSource(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1,1,1,2,2,3), 2, intArrayOf(1,2)),
                Arguments.of(intArrayOf(1), 1, intArrayOf(1))
            )
        }
    }

    @ParameterizedTest(name = "topK Frequent Words Using Naive Sort - TC: O(N*log(N)) ; SC: O(N)")
    @MethodSource("topKFreqentWordsSource")
    fun topKFrequentUsingNaiveSort(words: Array<String>, k: Int, result: List<String>) {
        assertEquals(result, TopKFrequentKotlin().topKFrequentUsingNaiveSort(words, k))
    }

    @ParameterizedTest(name = "topK Frequent Words Using Max Heap - TC: O(K*log(N)) ; SC: O(N)")
    @MethodSource("topKFreqentWordsSource")
    fun topKFrequentUsingMaxHeap(words: Array<String>, k: Int, result: List<String>) {
        assertEquals(result, TopKFrequentKotlin().topKFrequentUsingMaxHeap(words, k));
    }
    
    @ParameterizedTest(name = "topK Frequent Words Using Min Heap - TC: O(N*log(K)) ; SC: O(N)")
    @MethodSource("topKFreqentWordsSource")
    fun topKFrequentUsingMinHeap(words: Array<String>, k: Int, result: List<String>) {
        assertEquals(result, TopKFrequentKotlin().topKFrequentUsingMinHeap(words, k));
    }

    @ParameterizedTest(name = "topK Frequent Elements Using Max Heap - TC: O(K*log(N)) ; SC: O(N)")
    @MethodSource("topKFreqentElementsSource")
    fun topKFrequentElementsUsingMaxHeap(nums: IntArray, k: Int, result: IntArray) {
        assertArrayEquals(result, TopKFrequentKotlin().topKFrequentElementsUsingMaxHeap(nums, k));
    }

    // TODO: Fix this test case.    
    // @ParameterizedTest(name = "topK Frequent Elements Using Min Heap - TC: O(N*log(K)) ; SC: O(K+N)")
    // @MethodSource("topKFreqentElementsSource")
    // fun topKFrequentElementsUsingMinHeap(nums: IntArray, k: Int, result: IntArray) {
    //     assertArrayEquals(result, TopKFrequentKotlin().topKFrequentElementsUsingMinHeap(nums, k));
    // }

    @ParameterizedTest(name = "topK Frequent Elements Using Bucket Sort - TC: O(N) ; SC: O(N)")
    @MethodSource("topKFreqentElementsSource")
    fun topKFrequentElementsUsingBucketSort(nums: IntArray, k: Int, result: IntArray) {
        assertArrayEquals(result, TopKFrequentKotlin().topKFrequentElementsUsingBucketSort(nums, k));
    }

    // TODO: Fix this test case.
    // @ParameterizedTest(name = "topK Frequent Elements Using Quick Select - TC: O(N) avg and O(N^2) worst ; SC: O(N)")
    // @MethodSource("topKFreqentElementsSource")
    // fun topKFrequentElementsUsingQuickSelect(nums: IntArray, k: Int, result: IntArray) {
    //     assertArrayEquals(result, TopKFrequentKotlin().topKFrequentElementsUsingQuickSelect(nums, k));
    // }
}