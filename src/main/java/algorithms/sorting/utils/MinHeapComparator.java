package algorithms.sorting.utils;

import java.util.Comparator;
import java.util.Map;

public class MinHeapComparator implements Comparator<Map.Entry<String, Integer>> {

    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2){
        String word1 = e1.getKey();
        int freq1 = e1.getValue();
        String word2 = e2.getKey();
        int freq2 = e2.getValue();
        if(freq1!=freq2){
            return freq1-freq2;
        }
        else {
            return word2.compareTo(word1);
        }
    }

}
