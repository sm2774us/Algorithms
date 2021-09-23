package algorithms.sorting.topk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TopKFrequentTest {

    private final TopKFrequent solution = new TopKFrequent();

    private static Stream<Arguments> source1() {
        String[] words = new String[] {"i","love","leetcode","i","love","coding"};
        return Stream.of(
            Arguments.of(words, 2, List.of("i","love"))
        );
    }

    private static Stream<Arguments> source2() {
        String[] words = new String[] {"the","day","is","sunny","the","the","the","sunny","is","is"};
        return Stream.of(
            Arguments.of(words, 4, List.of("the","is","sunny","day"))
        );
    }

    @ParameterizedTest
    @MethodSource({"source1", "source2"})
    void topKFrequentUsingNaiveSort(String[] words, int k, List<String> result) {
        assertEquals(result, solution.topKFrequentUsingNaiveSort(words, k));
    }

}
