// Problem 953. Verifying an Alien Dictionary
// Time Complexity : O(M+N*L) - M is the length of the order string, N is the number of words, and L is the average length of each word.
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            if (notInOrder(words[i], words[i + 1], map)) return false;
        }

        return true;
    }

    private boolean notInOrder(String first, String second, HashMap<Character, Integer> map) {
        int len = Math.min(first.length(), second.length());
        for (int i = 0; i < len; i++) {
            char c1 = first.charAt(i);
            char c2 = second.charAt(i);
            if (c1 != c2) {
                return map.get(c1) > map.get(c2);
            }
        }
        return first.length() > second.length();
    }
}
