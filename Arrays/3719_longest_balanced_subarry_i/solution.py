class Solution:
    def longestBalanced(self, nums: List[int]) -> int:
        max_length = 0
        n = len(nums)

        for start in range(n):
            distinct_evens = set()
            distinct_odds = set()

            for end in range(start, n):
                value = nums[end]
                if value % 2 == 0:
                    distinct_evens.add(value)
                else:
                    distinct_odds.add(value)

                if len(distinct_evens) == len(distinct_odds):
                    max_length = max(max_length, end - start + 1)

        return max_length
