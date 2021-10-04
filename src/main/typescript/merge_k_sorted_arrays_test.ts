//import { test } from "https://deno.land/std/testing/mod.ts";
import { test, TestSuite } from "https://deno.land/x/test_suite@0.9.0/mod.ts";
import { assertEquals } from "https://deno.land/std/testing/asserts.ts";
import { mergeArraysUsingMaxHeap } from "./merge_k_sorted_arrays.ts";

test("merge K sorted arrays using max heap - TC: O(N*log(K)) ; SC: O(N+K)", () => {
  assertEquals(
    mergeArraysUsingMaxHeap([
      [1, 4, 5, 10],
      [1, 3, 4],
      [2, 6],
    ]),
    [1, 1, 2, 3, 4, 4, 5, 6, 10]
  );
  assertEquals(
    mergeArraysUsingMaxHeap([
      [1, 2],
      [2, 3],
    ]),
    [1, 2, 2, 3]
  );
});
