import { mergeKLists, ListNode } from "./merge_k_sorted_lists.ts";

export function toNumber(l: ListNode): number {
    let num = 0;
    let currentNode = l;
    let exponent = 0;
    while (currentNode) {
        num += currentNode.val * Math.pow(10, exponent++);
        currentNode = currentNode.next;
    }
    return num;
}

export function fromNumber(num: number): ListNode {
    const numStr = String(num);
    let headNode: ListNode = null;
    for (let i = 0; i < numStr.length; i++) {
        const digit = parseInt(numStr.charAt(i), 10);
        const node = new ListNode(digit);
        node.next = headNode;
        headNode = node;
    }
    return headNode;
}