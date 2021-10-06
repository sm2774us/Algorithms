import { mergeKLists, ListNode } from "./merge_k_sorted_lists.ts";

export function toNumber(l: ListNode): number {
    let num = 0;
    let currentNode = l;
    let exponent = 0;
    while (currentNode) {
        // @ts-ignore: Object is possibly 'null'.
        num += currentNode?.val * Math.pow(10, exponent++);
        if (currentNode.next !== null) {
            currentNode = currentNode.next;
        }
    }
    return num;
}

export function fromNumber(num: number): ListNode | null {
    const numStr = String(num);
    let headNode: ListNode | null = null;
    for (let i = 0; i < numStr.length; i++) {
        const digit = parseInt(numStr.charAt(i), 10);
        const node = new ListNode(digit);
        node.next = headNode;
        headNode = node;
    }
    return headNode;
}