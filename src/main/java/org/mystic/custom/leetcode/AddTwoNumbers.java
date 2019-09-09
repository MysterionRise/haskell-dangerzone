/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

import java.math.*;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder sb1 = new StringBuilder();
        do {
            sb1.append(l1.val);
            l1 = l1.next;
        } while (l1 != null); 
        StringBuilder sb2 = new StringBuilder();
        do {
            sb2.append(l2.val);
            l2 = l2.next;
        } while (l2 != null);
        final BigInteger res = new BigInteger(sb1.reverse().toString()).add(new BigInteger(sb2.reverse().toString()));
        char[] r = res.toString().toCharArray();
        final ListNode result = new ListNode(r[r.length - 1] - '0');
        ListNode curr = result;
        for (int i = r.length - 2; i >= 0; --i) {
            ListNode next = new ListNode(r[i] - '0');
            curr.next = next;
            curr = next;
        }
        return result;
    }
}
