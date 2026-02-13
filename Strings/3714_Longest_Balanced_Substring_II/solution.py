class Solution:
    def longestBalanced(self, s: str) -> int:
        n = len(s)
        max_len = 1

        # Handle single-character runs
        run_length = 1
        for i in range(1, n):
            if s[i] == s[i - 1]:
                run_length += 1
            else:
                max_len = max(max_len, run_length)
                run_length = 1
        max_len = max(max_len, run_length)

        # Prefix maps
        map_ab = {(0, 0): -1}
        map_bc = {(0, 0): -1}
        map_ca = {(0, 0): -1}
        map_abc = {(0, 0): -1}

        count = [0, 0, 0]  # a, b, c

        for i, ch in enumerate(s):
            count[ord(ch) - ord('a')] += 1
            a, b, c = count

            # 3-letter equality
            key = (b - a, c - a)
            if key in map_abc:
                max_len = max(max_len, i - map_abc[key])
            else:
                map_abc[key] = i

            # A & B equality
            key = (a - b, c)
            if key in map_ab:
                max_len = max(max_len, i - map_ab[key])
            else:
                map_ab[key] = i

            # B & C equality
            key = (b - c, a)
            if key in map_bc:
                max_len = max(max_len, i - map_bc[key])
            else:
                map_bc[key] = i

            # C & A equality
            key = (c - a, b)
            if key in map_ca:
                max_len = max(max_len, i - map_ca[key])
            else:
                map_ca[key] = i

        return max_len
