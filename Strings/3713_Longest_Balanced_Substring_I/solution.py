class Solution:
    def longestBalanced(self, s: str) -> int:
        n = len(s)
        max_length = 0

        for start in range(n):
            freq = [0] * 26
            distinct = 0
            max_freq = 0

            for end in range(start, n):
                idx = ord(s[end]) - ord('a')

                if freq[idx] == 0:
                    distinct += 1

                freq[idx] += 1
                max_freq = max(max_freq, freq[idx])

                length = end - start + 1

                if max_freq * distinct == length:
                    max_length = max(max_length, length)

        return max_length
