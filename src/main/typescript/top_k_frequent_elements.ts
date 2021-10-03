/**
 * LeetCode - 347 : Top K Frequent Elements.
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Solution-1: MAX HEAP -
 *
 * Algorithm Steps:
 *   1. Create a frequency table
 *   2. Create a Max Heap and add all the distinct elements
 *   3. Poll top k frequent elements off the Heap
 *
 * Time & Space Complexity Analysis:
 *
 * N = # of elements in the input array
 * D = # of distinct (unique) elements in the input array
 *
 * Building the HashMap: O(N) time
 *   - Add all the N elements into the HashMap and add thier frequency
 *
 * Building the Heap: O(D) time
 *   - https://www.geeksforgeeks.org/time-complexity-of-building-a-heap/
 *   - Above is a proof that shows that building a heap of N elements will take O(N) time
 *   - In our case we are building a heap of D elements = O(D) time
 *
 * Poll K distinct elements from the Heap: O(K log D) time
 *   - There are D elements in the Heap and we call poll() K times = O(K log D) time
 *
 * Total Time Complexity = O(K log D)
 * Total Space Complexity = O(D), this is the size of the heap.
 *
 * @param {number[]} nums
 * @param {number} k
 * @returns {number[]}
 */
export function topKFrequentUsingMaxHeap(nums: number[], k: number): number[] {
  const numsToFrequencyMap: { [key: number]: number } = {};
  for (let i = 0; i < nums.length; i++) {
    const element = nums[i];
    if (numsToFrequencyMap[element] !== undefined) {
      numsToFrequencyMap[element] += 1;
    } else {
      numsToFrequencyMap[element] = 1;
    }
  }

  const order: [number, number][] = [];
  for (const key in numsToFrequencyMap) {
    const frequency = numsToFrequencyMap[key];
    order.push([Number(key), Number(frequency)]);
  }

  order.sort((a, b) => b[1] - a[1]);

  const result: number[] = [];
  for (let i = 0; i < k; i++) {
    const element = order[i];
    const num = element[0];
    result.push(num);
  }
  return result;
}

/**
 * LeetCode - 347 : Top K Frequent Elements.
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Solution-3: BUCKET SORT -
 *
 * It is intuitive to map a value to its frequency.
 * Then our problem becomes 'to sort map entries by their values'.
 * Since frequency is within the range [1, n] for n is the number of elements,
 * we could apply the idea of Bucket Sort:
 *   1) We divide frequencies into n + 1 buckets, in this way, the list in buckets[i] contains elements with the same frequency i
 *   2) Then, we go through the buckets from tail to head until we collect k elements.
 *
 * Determine top-K frequent elements using Bucket Sort.
 *
 * Time Complexity  : O(N)
 * Space Complexity : O(N)
 *
 * @param {number[]} nums
 * @param {number} k
 * @returns {number[]}
 */
export function topKFrequentElementsUsingBucketSort(
  nums: number[],
  k: number
): number[] {
  // Map of counts
  const counts = new Map<number, number>();
  for (const num of nums) counts.set(num, (counts?.get(num) ?? 0) + 1);
  if (counts.size === k) return [...counts.keys()];

  const buckets: number[][] = Array(nums.length + 1)
    .fill(undefined)
    .map(() => []); // count => [nums]
  for (const [num, count] of counts.entries()) buckets[count].push(num);

  const result: number[] = [];
  for (let i = buckets.length - 1; i >= 0; i--) {
    const bucket = buckets[i];
    const remaining = k - result.length;
    result.push(...bucket.slice(0, remaining));
    if (result.length === k) break;
  }

  return result;
}
