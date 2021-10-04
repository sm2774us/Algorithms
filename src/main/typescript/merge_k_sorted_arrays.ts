/* 
  Using Binary Heap (Optimized) ... MAX HEAP.

  Time Complexity - O(nklog(k)) or O(N*log(K))
  - Heap size will be k. So Heapifying is O(log(k))
  - We're going to be heapifying n * k elements. Therefore, T(n) = O(nklog(k))

  Space Complexity - O(nk) + O(k) from the heap.
					 or,
					 O(N+K)  
*/
export function mergeArraysUsingMaxHeap(arrays: number[][]): number[] {
	const merged: number[] = [],
		increasingOrder: boolean = isIncreasingOrder(arrays)
	// [0]
	const heap: MinBinaryHeap | MaxBinaryHeap = increasingOrder ? new MinBinaryHeap() : new MaxBinaryHeap()

	// [1]
	arrays.forEach((arr, i) => {
		heap.insert(new HeapNode(i, 0, arr[0]))
	})

	heap.heapify()

	if (increasingOrder) {
		// [2]
		while (heap.top.value !== Number.POSITIVE_INFINITY) {
			getAndInsertNextVal(arrays, heap, merged, Infinity)
		}
	} else {
		while (heap.top.value !== Number.NEGATIVE_INFINITY) {
			getAndInsertNextVal(arrays, heap, merged, -Infinity)
		}
	}

	return merged
}

/* 
Notes: 
[0] Create min or max heap based on order
[1] - Insert first element of all arrays into heap.
    - Each heap item will be an instance of HeapNode which will keep track of 
      the arrIndex, elementIndex & value.
[2] - Keep getting heap top(min) & pushing it into the merged array.
    - If the min value is Infinity, we've reached the end of all arrays
*/

/* 
getAndInsertNextVal:
  - Function to get the top(min or max val), push top val into merged
    & insert next array value into heap
  - instead of extracting & inserting, we're going to swap the new value with the top value.
    So we don't have to re-heapify twice for extraction & insertion
  - We'll use the arrIndex, elementIndex from the heap top to get the next item to insert
  - If we're at the end of the array insert Infinity(min heap or increasing order) or -Infinity
*/
function getAndInsertNextVal(arrays: number[][], heap: MinBinaryHeap | MaxBinaryHeap, merged: number[], infinity: number): void {
	const n: number = arrays[0].length;
	let top: HeapNode = heap.top;

	merged.push(top.value)

	const arrIndex = top.arrIndex,
		elementIndex = top.elementIndex + 1,
		value = elementIndex >= n ? infinity : arrays[arrIndex][elementIndex]

	heap.swapTop(new HeapNode(arrIndex, elementIndex, value))
	heap.sinkDown(0)
}

// HeapNode will keep track of the array index, the element index & the value
class HeapNode {

    private _arrIndex: number;
    private _elementIndex: number;
    private _value: number;

	constructor(arrIndex: number, elementIndex: number, value: number) {
		this._arrIndex = arrIndex;
		this._elementIndex = elementIndex;
		this._value = value;
	}

    // Getters
    get arrIndex(): number {
        return this._arrIndex;
    }

    // Getters
    get elementIndex(): number {
        return this._elementIndex;
    }

    // Getters
    get value(): number {
        return this._value;
    }
}

// Modified Binary Heaps
// Unmodified Binary Heap implementation here --> https://blog.mrinalini.dev/posts/binary-heap/
class BinaryHeap {
    private _heapArr: HeapNode[] = [];

	constructor() {
	}
    
	// Getters
    get heapArr(): HeapNode[] {
        return this._heapArr;
    }

	get size(): number {
		return this._heapArr.length;
	}

	get top(): HeapNode {
		return this._heapArr[0];
	}

	// Class methods
	insert(node: HeapNode): void {
		this._heapArr[this.size] = node;
	}

	swapTop(newNode: HeapNode): void {
		this._heapArr[0] = newNode;
	}

	heapify(): void {
		const lastIndex: number = this.size - 1;
		for (let i = lastIndex; i >= 0; i--) {
			this.sinkDown(i);
		}
	}
	// sinkDown will be different for max & min heap
	sinkDown(p: number): void {}
}

// Min Binary Heap for increasing sort order
class MinBinaryHeap extends BinaryHeap {
	constructor() {
		super();
	}

	sinkDown(p: number): void {
		const element: number = this.heapArr[p].value,
			lastIndex: number = this.heapArr.length - 1;

		while (p < lastIndex) {
			const r = 2 * p + 2,
				l = r - 1,
				left = this.heapArr[l] && this.heapArr[l].value,
				right = this.heapArr[r] && this.heapArr[r].value;

			let swapIndex = null,
				min = element;

			if (l <= lastIndex && left < min) {
				swapIndex = l;
				min = left;
			}

			if (r <= lastIndex && right < min) {
				swapIndex = r;
			}

			if (swapIndex == null) break

			swap(this.heapArr, p, swapIndex);

			p = swapIndex;
		}
	}
}

// Max Binary Heap for decreasing sort order
class MaxBinaryHeap extends BinaryHeap {
	constructor() {
		super();
	}

	sinkDown(p: number): void {
		const element: number = this.heapArr[p].value,
			lastIndex: number = this.heapArr.length - 1;

		while (p < lastIndex) {
			const r: number = 2 * p + 2,
				l: number = r - 1,
				left: number = this.heapArr[l] && this.heapArr[l].value,
				right: number = this.heapArr[r] && this.heapArr[r].value;

			let swapIndex: Nullable<number> = null,
				max: number = element;

			if (l <= lastIndex && left > max) {
				swapIndex = l;
				max = left;
			}

			if (r <= lastIndex && right > max) {
				swapIndex = r;
			}

			if (swapIndex == null) break;

			swap(this.heapArr, p, swapIndex);

			p = swapIndex;
		}
	}
}

// --------------------------------------------------------------
/* 
isIncreasingOrder
 - Function to get the sort order of the arrays
 - Can't just check the first two values of the first array cause they might be repeated values
 - So keep checking until two values are different & return true or false
*/
function isIncreasingOrder(arrays: number[][]): boolean {
	let i: number = 0,
		k: number = arrays.length;

	while (i < k) {
		const arr: number[] = arrays[i],
			len: number = arr.length;

		for (let j = 1; j < len; j++) {
			const diff = arr[j] - arr[j - 1];
			if (diff > 0) return true;
			else if (diff < 0) return false;
			else continue;
		}
		i++;
	}
	return false;
}

function swap(arr: HeapNode[], i: number, j: number) {
	const temp: HeapNode = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
}