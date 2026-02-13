class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLength = 0;

        for (int start = 0; start < n; start++) {
            int[] frequency = new int[26];
            int distinctCount = 0;
            int maxFrequency = 0;

            for (int end = start; end < n; end++) {
                int index = s.charAt(end) - 'a';

                if (frequency[index] == 0) {
                    distinctCount++;
                }

                frequency[index]++;
                maxFrequency = Math.max(maxFrequency, frequency[index]);

                int currentLength = end - start + 1;

                if (maxFrequency * distinctCount == currentLength) {
                    maxLength = Math.max(maxLength, currentLength);
                }
            }
        }

        return maxLength;
    }
}
