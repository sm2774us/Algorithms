package algorithms.sorting.topk;

import algorithms.sorting.utils.SimpleMapComparator;

import java.util.*;

public class TopKFrequent {

    /**
     * Determine top-K frequent elements using Naive sorting.
     *
     * Time Complexity  : O(N*log(N)), naive sort is O(N*log(N))
     * Space Complexity : O(N)       , for map and list
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequentUsingNaiveSort(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String word:words){
            map.put(word, map.getOrDefault(word, 0)+1);
        }
        List<Map.Entry<String, Integer>> l = new LinkedList<>();
        for(Map.Entry<String, Integer> e:map.entrySet()){
            l.add(e);
        }
        Collections.sort(l, new SimpleMapComparator());//just use our Comparator to sort
        List<String> ans = new LinkedList<>();
        for(int i = 0;i<=k-1;i++){
            ans.add(l.get(i).getKey());
        }
        return ans;
    }
}
