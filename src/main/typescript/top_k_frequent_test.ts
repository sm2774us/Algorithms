//import { test } from "https://deno.land/std/testing/mod.ts";
import { test, TestSuite } from "https://deno.land/x/test_suite@0.9.0/mod.ts";
import { assertEquals } from "https://deno.land/std/testing/asserts.ts";
import { topKFrequentUsingMaxHeap } from "./top_k_frequent.ts";

test("692. topK Frequent Elements Using Max Heap - TC: O(K*log(N)) ; SC: O(N)", () => {
  assertEquals(
    topKFrequentUsingMaxHeap(
      ["i", "love", "leetcode", "i", "love", "coding"],
      2
    ),
    ["i", "love"]
  );
  assertEquals(
    topKFrequentUsingMaxHeap(
      ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"],
      4
    ),
    ["the", "is", "sunny", "day"]
  );
});
