//import { test } from "https://deno.land/std/testing/mod.ts";
import { test, TestSuite } from "https://deno.land/x/test_suite@0.9.0/mod.ts";
import { assertEquals } from "https://deno.land/std/testing/asserts.ts";
import { topKFrequentUsingNaiveSort } from "./top_k_frequent_naive_sorting.ts";

test("692. topK Frequent Words Using Naive Sort - TC: O(N*log(N)) ; SC: O(N)", () => {
  assertEquals(
    topKFrequentUsingNaiveSort(
      ["i", "love", "leetcode", "i", "love", "coding"],
      2
    ),
    ["i", "love"]
  );
  assertEquals(
    topKFrequentUsingNaiveSort(
      ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"],
      4
    ),
    ["the", "is", "sunny", "day"]
  );
});
