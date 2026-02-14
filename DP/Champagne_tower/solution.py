class Solution:
    def champagneTower(self, poured: int, query_row: int, query_glass: int) -> float:
        # Create DP table up to required row
        dp = [[0.0] * (query_row + 2) for _ in range(query_row + 2)]
        dp[0][0] = float(poured)

        for row in range(query_row + 1):
            for col in range(row + 1):
                if dp[row][col] > 1.0:
                    overflow = (dp[row][col] - 1.0) / 2.0
                    dp[row + 1][col] += overflow
                    dp[row + 1][col + 1] += overflow

        return min(1.0, dp[query_row][query_glass])
