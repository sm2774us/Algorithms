/**
 * Definition for singly-linked list.
 */ 
export class ListNode {
    
	val: number;
    next: ListNode | null;
	
	constructor(val?: number | null, next?: ListNode | null) {
    	this.val = (val===undefined ? 0 : val);
    	this.next = (next===undefined ? null : next);
	}

}

/**
 * LeetCode - 23 : Merge K Sorted Lists.
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * 
 * Solution - 1: MIN HEAP
 * 
 * In order to build one sorted list, we always want the minimum node among heads of lists.
 * That's natural to Min Heap.
 * 
 * The complexity of this problem is O(knlogk), no algorithm can run it O(nlogk). We have T(n) = 2T(n/2) + kn, and therefore, O(kn*logk).
 * when people talking about time complexity; say, O(nlogk)
 * Can we first clarify what is "n" please?
 * If n is total number of numbers of all lists, then it's nlogk;
 * if n is average length of each list, then it's nklogk;
 * Without clarification it'll cause lots of confusion.
 * 
 * Time Complexity  : O(N*log(K)), 
 *                    where, 
 *                    N = total number of numbers of all lists.
 *                    K = the number of linked list.
 *                    
 *                    Min Heap contains at most K ListNode(s).
 *                    There are N ListNode to insert, so overall 
 *                    the time complexity is O(N*log(K))
 * 
 * Space Complexity : O(K)
 *                    stores the head of the LinkedList, 
 *                    thus at any time, the Min Heap contains 
 *                    at most K key-value pairs.
 * 
 * @param {Array<ListNode | null>} lists
 * @returns {ListNode | null}
 */
export function mergeKLists(lists: Array<ListNode | null>): ListNode | null {
    const pq = new BinaryHeap<ListNode>((a, b) => a.val < b.val);
    
    for (const listNode of lists) {
        if (listNode !== null) pq.push(listNode);
    }
    
    let dummyHead = new ListNode();
    let head = dummyHead;
    while(pq.size() > 0) {
        const listNode = pq.peek();
        if (listNode.next) pq.push(listNode.next);
        
        head.next = listNode;
        head = head.next;
    }
    
    return dummyHead.next;
};

function swap<T>(list: T[], i: number, j: number): T[] {
    if (i !== j) {
        [list[i], list[j]] = [list[j], list[i]];
    }
    return list;
}

class BinaryHeap<T> {
    private harr: T[] = [];

    constructor(private lessThan: (a:T, b:T) => boolean) {
    }

    size() {
        return this.harr.length;
    }

    push(v: T) {
        this.harr.push(v);
        let i = this.harr.length - 1;
        while(i > 0 && this.lessThan(this.harr[i], this.harr[this.parent(i)])) {
            swap(this.harr, i, this.parent(i));
            i = this.parent(i);
        }
    }

    peek() {
        if (this.harr.length === 0) throw new Error('Overflow');

        const [head] = this.harr;
        this.harr[0] = this.harr[this.harr.length - 1];
        this.harr.length -= 1;

        this.heapify(0);

        return head;
    }

    private heapify(i: number) {
        const l = this.left(i);
        const r = this.right(i);
        let smallest = i;
        if (l < this.harr.length && this.lessThan(this.harr[l], this.harr[i])) {
            smallest = l;
        }
        if (r < this.harr.length && this.lessThan(this.harr[r], this.harr[smallest])) {
            smallest = r;
        }
        if (smallest !== i) {
            swap(this.harr, smallest, i);
            this.heapify(smallest);
        }
    }

    private parent(i: number) {
        return Math.floor((i - 1) / 2);
    }

    private left(i: number) {
        return 2 * i + 1;
    }

    private right(i: number) {
        return 2 * i + 2;
    }
}