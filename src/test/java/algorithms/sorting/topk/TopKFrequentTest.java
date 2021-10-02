package algorithms.sorting.topk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TopKFrequentTest {

    private final TopKFrequent solution = new TopKFrequent();

    private static Stream<Arguments> topKFreqentWordsSource1() {
        String[] words = new String[] {"i","love","leetcode","i","love","coding"};
        return Stream.of(
            Arguments.of(words, 2, List.of("i","love"))
        );
    }

    private static Stream<Arguments> topKFreqentWordsSource2() {
        String[] words = new String[] {"the","day","is","sunny","the","the","the","sunny","is","is"};
        return Stream.of(
            Arguments.of(words, 4, List.of("the","is","sunny","day"))
        );
    }
	
	private static Stream<Arguments> topKFreqentElementsSource1() {
        int[] nums = new int[] {1,1,1,2,2,3};
        return Stream.of(
            Arguments.of(nums, 2, new int[] {1,2})
        );
    }

	private static Stream<Arguments> topKFreqentElementsSource2() {
        int[] nums = new int[] {1};
        return Stream.of(
            Arguments.of(nums, 1, new int[] {1})
        );
    }
	
    @ParameterizedTest(name = "topK Frequent Words Using Naive Sort - TC: O(N*log(N)) ; SC: O(N)")
    @MethodSource({"topKFreqentWordsSource1", "topKFreqentWordsSource2"})
    void topKFrequentUsingNaiveSort(String[] words, int k, List<String> result) {
        assertEquals(result, solution.topKFrequentUsingNaiveSort(words, k));
    }

    @ParameterizedTest(name = "topK Frequent Words Using Max Heap - TC: O(N+K*log(N)) ; SC: O(N)")
    @MethodSource({"topKFreqentWordsSource1", "topKFreqentWordsSource2"})
    void topKFrequentUsingMaxHeap(String[] words, int k, List<String> result) {
        assertEquals(result, solution.topKFrequentUsingMaxHeap(words, k));
    }
    
    @ParameterizedTest(name = "topK Frequent Words Using Min Heap - TC: O(N*log(K)) ; SC: O(N)")
    @MethodSource({"topKFreqentWordsSource1", "topKFreqentWordsSource2"})
    void topKFrequentUsingMinHeap(String[] words, int k, List<String> result) {
        assertEquals(result, solution.topKFrequentUsingMinHeap(words, k));
    }

    @ParameterizedTest(name = "topK Frequent Words Using Max Heap - TC: O(N+K*log(K)) ; SC: O(N)")
    @MethodSource({"topKFreqentWordsSource1", "topKFreqentWordsSource2"})
    void topKFrequentUsingQuickSelect(String[] words, int k, List<String> result) {
        assertEquals(result, solution.topKFrequentUsingQuickSelect(words, k));
    }

    @ParameterizedTest(name = "topK Frequent Words Using Sorted Set - TC: O(N*log(K)) ; SC: O(N)")
    @MethodSource({"topKFreqentWordsSource1", "topKFreqentWordsSource2"})
    void topKFrequentUsingSortedSet(String[] words, int k, List<String> result) {
        assertEquals(result, solution.topKFrequentUsingSortedSet(words, k));
    }

    @ParameterizedTest(name = "topK Frequent Words Using Trie Data Structure - TC: O(N) ; SC: O(N)")
    @MethodSource({"topKFreqentWordsSource1", "topKFreqentWordsSource2"})
    void topKFrequentUsingTrie(String[] words, int k, List<String> result) {
        assertEquals(result, solution.topKFrequentUsingTrie(words, k));
    }

    @ParameterizedTest(name = "topK Frequent Elements Using Bucket Sort - TC: O(N) ; SC: O(N)")
    @MethodSource({"topKFreqentElementsSource1", "topKFreqentElementsSource2"})
    void topKFrequentElementsUsingBucketSort(int[] nums, int k, int[] result) {
        assertArrayEquals(result, solution.topKFrequentElementsUsingBucketSort(nums, k));
    }

}
