//import { test } from "https://deno.land/std/testing/mod.ts";
import { test, TestSuite } from "https://deno.land/x/test_suite@0.9.0/mod.ts";
import { assertEquals } from "https://deno.land/std/testing/asserts.ts";
import { topKFrequentUsingMaxHeap, topKFrequentElementsUsingBucketSort } from "./top_k_frequent.ts";

test("692. topK Frequent Elements Using Max Heap - TC: O(K*log(N)) ; SC: O(N)", () => {
  assertEquals(topKFrequentUsingMaxHeap([1,1,1,2,2,3], 2), [1, 2]);
  assertEquals(topKFrequentUsingMaxHeap([1], 1), [1]);
  assertEquals(topKFrequentElementsUsingBucketSort([1,1,1,2,2,3], 2), [1, 2]);
  assertEquals(topKFrequentElementsUsingBucketSort([1], 1), [1]);
});