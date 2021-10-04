package algorithms.sorting.merge_k_sorted_arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MergeKSortedArraysTest {

    private final MergeKSortedArrays solution = new MergeKSortedArrays();
    
    private static Stream<Arguments> methodTestDataSource1() {
        return Stream.of(
            Arguments.of(new int[][]{
                {1,4,5,10},
                {1,3,4},
                {2,6},
                null
            }, new int[] {1,1,2,3,4,4,5,6,10})
        );
    }

    private static Stream<Arguments> methodTestDataSource2() {
        return Stream.of(
            Arguments.of(new int[][]{
                {1,2},
                {2,3}
            }, new int[] {1,2,2,3})
        );
    }

    @ParameterizedTest(name = "merge K sorted arrays using max heap - TC: O(N*log(K)) ; SC: O(N+K)")
    @MethodSource({"methodTestDataSource1", "methodTestDataSource2"})
    void mergekSortedArraysUsingMaxHeap(int[][] arrays, int[] result) {
        assertArrayEquals(result, solution.mergekSortedArraysUsingMaxHeap(arrays));
    }

    @ParameterizedTest(name = "merge K sorted arrays using divide and conquer - TC: O(N*log(K)) ; SC: O(N+K)")
    @MethodSource({"methodTestDataSource1", "methodTestDataSource2"})
    void mergekSortedUsingDivideAndConquer(int[][] arrays, int[] result) {
        assertArrayEquals(result, solution.mergekSortedUsingDivideAndConquer(arrays));
    }

}
