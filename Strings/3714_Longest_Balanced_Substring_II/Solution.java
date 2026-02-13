import java.util.*;

class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLen = 1;

        // Handle single-character runs
        int run = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                run++;
            } else {
                maxLen = Math.max(maxLen, run);
                run = 1;
            }
        }
        maxLen = Math.max(maxLen, run);

        Map<String, Integer> mapAB = new HashMap<>();
        Map<String, Integer> mapBC = new HashMap<>();
        Map<String, Integer> mapCA = new HashMap<>();
        Map<String, Integer> mapABC = new HashMap<>();

        mapAB.put("0#0", -1);
        mapBC.put("0#0", -1);
        mapCA.put("0#0", -1);
        mapABC.put("0#0", -1);

        int[] count = new int[3]; // a, b, c

        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;

            int a = count[0];
            int b = count[1];
            int c = count[2];

            String key = (b - a) + "#" + (c - a);
            if (mapABC.containsKey(key))
                maxLen = Math.max(maxLen, i - mapABC.get(key));
            else
                mapABC.put(key, i);

            key = (a - b) + "#" + c;
            if (mapAB.containsKey(key))
                maxLen = Math.max(maxLen, i - mapAB.get(key));
            else
                mapAB.put(key, i);

            key = (b - c) + "#" + a;
            if (mapBC.containsKey(key))
                maxLen = Math.max(maxLen, i - mapBC.get(key));
            else
                mapBC.put(key, i);

            key = (c - a) + "#" + b;
            if (mapCA.containsKey(key))
                maxLen = Math.max(maxLen, i - mapCA.get(key));
            else
                mapCA.put(key, i);
        }

        return maxLen;
    }
}
