// Problem 269. Alien Dictionary
// Time Complexity : O(C), where C is the total number of characters in all words combined
// Space Complexity : O(U+E), where U is the number of unique characters and E is the number of edges in the graph
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class Solution {
    public String alienOrder(String[] words) {
        int[] indegrees = new int[26];
        HashMap<Character, HashSet<Character>> map = new HashMap<>();
        
        if (!buildGraph(words, map, indegrees)) {
            return "";
        }

        Queue<Character> q = new LinkedList<>();
        StringBuilder result = new StringBuilder();
        int count = 0;
        
        for (char c : map.keySet()) {
            if (indegrees[c - 'a'] == 0) {
                q.add(c);
                count++;
            }
        }
        
        while (!q.isEmpty()) {
            char current = q.poll();
            result.append(current);
            
            for (char neighbor : map.get(current)) {
                indegrees[neighbor - 'a']--;
                if (indegrees[neighbor - 'a'] == 0) {
                    q.add(neighbor);
                    count++;
                }
            }
        }
        
        if (result.length() != map.size()) {
            return "";
        }

        return result.toString();
    }

    private boolean buildGraph(String[] words, HashMap<Character, HashSet<Character>> map, int[] indegrees) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!map.containsKey(c)) {
                    map.put(c, new HashSet<>());
                }
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            
            if (first.length() > second.length() && first.startsWith(second)) {
                return false;
            }
            
            for (int j = 0; j < Math.min(first.length(), second.length()); j++) {
                char fchar = first.charAt(j);
                char schar = second.charAt(j);
                
                if (fchar != schar) {
                    HashSet<Character> set = map.get(fchar);
                    if (!set.contains(schar)) {
                        set.add(schar);
                        indegrees[schar - 'a']++;
                    }
                    break;
                }
            }
        }
        return true;
    }
}