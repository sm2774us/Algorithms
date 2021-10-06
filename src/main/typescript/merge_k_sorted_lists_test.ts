//import { test } from "https://deno.land/std/testing/mod.ts";
import { test, TestSuite } from "https://deno.land/x/test_suite@0.9.0/mod.ts";
import { assertEquals } from "https://deno.land/std/testing/asserts.ts";
import { mergeKLists, ListNode } from "./merge_k_sorted_lists.ts";
import { toNumber, fromNumber } from "./linked_list_utils.ts";

// test("merge K sorted lists using max heap - TC: O(N*log(K)) ; SC: O(K)", () => {
//   const l1: ListNode | null = fromNumber(145);
//   const l2: ListNode | null = fromNumber(134);
//   const l3: ListNode | null = fromNumber(26);
//   const expected: ListNode | null = fromNumber(11234456);
//   assertEquals(
//     mergeKLists([
//       l1,l2,l3
//     ]),
//     expected
//   );
// });

test("merge K sorted lists using max heap for null - TC: O(N*log(K)) ; SC: O(K)", () => {
  const l: ListNode = new ListNode(null, null);
  const expected: ListNode = new ListNode(null, null);
  assertEquals(
    mergeKLists([
      l
    ]),
    expected
  );
});