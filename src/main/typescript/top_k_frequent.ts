/**
 * LeetCode - 692 : Top K Frequent Words.
 * https://leetcode.com/problems/top-k-frequent-words/
 *
 *
 * Solution-2: MAX HEAP
 *
 * 1. Create hash table for the words with frequency
 * 2. Create maxHeap with words and add them to it
 * 3. Poll top k from the maxHeap and add them to Result list
 *
 * Time Complexity  : O(K*log(N))
 *                    O(N) for heapify
 *                    and
 *                    O(K*log(N)) for poping k times
 *
 *                    Highest Time Complexity of O(K*log(N)) v/s O(N) so,
 *                    instead of: O(N+K*log(N))
 *                    TC = O(K*log(N))
 *
 * Space Complexity : O(N)         , for heap
 *
 * @param {string[]} words
 * @param {number} k
 * @returns {string[]}
 */
export function topKFrequentUsingMaxHeap(words: string[], k: number): string[] {
  const wordsMap: Record<string, number> = createWordsMap(words);

  const queue: PriorityQueue<string> = new PriorityQueue<string>((a: string, b: string) => {
    if (wordsMap[a] > wordsMap[b]) {
      return true;
    }

    if (wordsMap[a] === wordsMap[b]) {
      // THE COMPARISON OF LETTER STRINGS IN JS IS BASED ON ASCII CODES
      return a < b;
    }
    return false;
  });

  Object.keys(wordsMap).forEach((word) => queue.insert(word));

  const answer: string[] = [];
  while (k--) {
    answer.push(<string> queue.poll());
  }

  return answer;
}

function createWordsMap(words: string[]): Record<string, number> {
  return words.reduce((map, word) => {
    if (map[word] !== undefined) {
      map[word]++;
    } else {
      map[word] = 1;
    }
    return map;
  }, {} as Record<string, number>);
}

function swap<E>(list: E[], a: number, b: number) {
  [list[a], list[b]] = [list[b], list[a]];
}

class PriorityQueue<E> {
  private heap: [null, ...E[]] = [null];
  constructor(private comparator: (a: E, b: E) => boolean) {}

  get size(): number {
    return this.heap.length - 1;
  }

  sink(index: number) {
    const { heap } = this;
    while (2 * index <= this.size) {
      // the child elements of the k item are 2 x k and 2 x k x 1
      let child = 2 * index;
      if (
        child < this.size &&
        this.comparator(heap[child + 1] as E, heap[child] as E)
      ) {
        child++;
      }
      if (!this.comparator(heap[child] as E, heap[index] as E)) break;
      swap(heap, index, child);
      index = child;
    }
  }

  swim(index: number) {
    const { heap } = this;
    while (
      index > 1 &&
      this.comparator(heap[index] as E, heap[index >> 1] as E)
    ) {
      const parent = index >> 1;
      swap(heap, index, parent);
      index = parent;
    }
  }

  insert(el: E) {
    const newIndex: number = this.size + 1;
    this.heap[newIndex] = el;
    this.swim(newIndex);
  }

  poll(): E | undefined {
    if (this.size <= 0) return;
    const deleted: E = this.heap[1];
    swap(this.heap, 1, this.size);
    this.heap.length -= 1;
    this.sink(1);
    return deleted;
  }
}
