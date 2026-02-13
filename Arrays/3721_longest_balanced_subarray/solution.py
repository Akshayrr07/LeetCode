from typing import List

class SegmentTree:
    def __init__(self, n: int):
        self.n = n
        self.minv = [0] * (4 * n)
        self.maxv = [0] * (4 * n)
        self.lazy = [0] * (4 * n)

    def _apply(self, idx: int, delta: int):
        self.minv[idx] += delta
        self.maxv[idx] += delta
        self.lazy[idx] += delta

    def _push(self, idx: int):
        if self.lazy[idx] != 0:
            self._apply(idx * 2, self.lazy[idx])
            self._apply(idx * 2 + 1, self.lazy[idx])
            self.lazy[idx] = 0

    def _pull(self, idx: int):
        self.minv[idx] = min(self.minv[idx * 2], self.minv[idx * 2 + 1])
        self.maxv[idx] = max(self.maxv[idx * 2], self.maxv[idx * 2 + 1])

    def _range_add(self, idx, l, r, ql, qr, delta):
        if ql > r or qr < l:
            return
        if ql <= l and r <= qr:
            self._apply(idx, delta)
            return
        self._push(idx)
        mid = (l + r) // 2
        self._range_add(idx * 2, l, mid, ql, qr, delta)
        self._range_add(idx * 2 + 1, mid + 1, r, ql, qr, delta)
        self._pull(idx)

    def add(self, l: int, r: int, delta: int):
        if l <= r:
            self._range_add(1, 0, self.n - 1, l, r, delta)

    def _find_zero(self, idx, l, r, ql, qr):
        if ql > r or qr < l or self.minv[idx] > 0 or self.maxv[idx] < 0:
            return -1
        if l == r:
            return l
        self._push(idx)
        mid = (l + r) // 2
        res = self._find_zero(idx * 2 + 1, mid + 1, r, ql, qr)
        if res != -1:
            return res
        return self._find_zero(idx * 2, l, mid, ql, qr)

    def rightmost_zero(self, l: int, r: int) -> int:
        return self._find_zero(1, 0, self.n - 1, l, r)


class Solution:
    def longestBalanced(self, nums: List[int]) -> int:
        n = len(nums)

        positions = {}
        for i, v in enumerate(nums):
            positions.setdefault(v, []).append(i)

        seg = SegmentTree(n)

        for v, pos in positions.items():
            contribution = 1 if v % 2 else -1
            seg.add(pos[0], n - 1, contribution)

        ptr = {v: 0 for v in positions}
        ans = 0

        for left in range(n):
            right = seg.rightmost_zero(left, n - 1)
            if right != -1:
                ans = max(ans, right - left + 1)

            v = nums[left]
            contribution = 1 if v % 2 else -1
            idx = ptr[v]
            ptr[v] += 1

            next_pos = positions[v][ptr[v]] if ptr[v] < len(positions[v]) else n
            seg.add(left, next_pos - 1, -contribution)

        return ans
