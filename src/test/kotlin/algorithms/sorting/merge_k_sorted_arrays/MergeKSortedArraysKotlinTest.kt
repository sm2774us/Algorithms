package algorithms.sorting.merge_k_sorted_arrays

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertArrayEquals

import kotlin.collections.List
import java.util.stream.Stream

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class MergeKSortedArraysKotlinTest {

    companion object {
        @JvmStatic
        fun mergekSortedArraysSource(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(1,4,5,10), intArrayOf(1,3,4), intArrayOf(2,6), null), intArrayOf(1,1,2,3,4,4,5,6,10)),
                Arguments.of(arrayOf(intArrayOf(1,2), intArrayOf(2,3)), intArrayOf(1,2,2,3))
            )
        }
    }

    @ParameterizedTest(name = "merge K sorted arrays using max heap - TC: O(N*log(K)) ; SC: O(N+K)")
    @MethodSource("mergekSortedArraysSource")
    fun mergekSortedArrays(arrays: Array<IntArray>, result: IntArray) {
        assertArrayEquals(result, MergeKSortedArraysKotlin().mergekSortedArrays(arrays))
    }

}