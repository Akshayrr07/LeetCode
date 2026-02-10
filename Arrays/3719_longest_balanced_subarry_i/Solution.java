class Solution {
    public int longestBalanced(int[] nums) {
        int maxLength = 0;
        int n = nums.length;

        for (int start = 0; start < n; start++) {
            Set<Integer> distinctEvens = new HashSet<>();
            Set<Integer> distinctOdds = new HashSet<>();

            for (int end = start; end < n; end++) {
                int value = nums[end];
                if (value % 2 == 0) {
                    distinctEvens.add(value);
                } else {
                    distinctOdds.add(value);
                }

                if (distinctEvens.size() == distinctOdds.size()) {
                    maxLength = Math.max(maxLength, end - start + 1);
                }
            }
        }
        return maxLength;
    }
}
