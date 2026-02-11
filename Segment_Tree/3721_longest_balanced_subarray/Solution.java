import java.util.*;

class Solution {

    static class SegTree {
        int n;
        int[] mn, mx, lazy;

        SegTree(int n) {
            this.n = n;
            mn = new int[4 * n];
            mx = new int[4 * n];
            lazy = new int[4 * n];
        }

        void apply(int idx, int val) {
            mn[idx] += val;
            mx[idx] += val;
            lazy[idx] += val;
        }

        void push(int idx) {
            if (lazy[idx] != 0) {
                apply(idx << 1, lazy[idx]);
                apply(idx << 1 | 1, lazy[idx]);
                lazy[idx] = 0;
            }
        }

        void pull(int idx) {
            mn[idx] = Math.min(mn[idx << 1], mn[idx << 1 | 1]);
            mx[idx] = Math.max(mx[idx << 1], mx[idx << 1 | 1]);
        }

        void addRange(int idx, int l, int r, int ql, int qr, int val) {
            if (ql > r || qr < l) return;
            if (ql <= l && r <= qr) {
                apply(idx, val);
                return;
            }
            push(idx);
            int mid = (l + r) >> 1;
            addRange(idx << 1, l, mid, ql, qr, val);
            addRange(idx << 1 | 1, mid + 1, r, ql, qr, val);
            pull(idx);
        }

        void add(int l, int r, int val) {
            if (l <= r)
                addRange(1, 0, n - 1, l, r, val);
        }

        int findRightmostZero(int idx, int l, int r, int ql, int qr) {
            if (ql > r || qr < l || mn[idx] > 0 || mx[idx] < 0) return -1;
            if (l == r) return l;

            push(idx);
            int mid = (l + r) >> 1;

            if (qr > mid) {
                int res = findRightmostZero(idx << 1 | 1, mid + 1, r, ql, qr);
                if (res != -1) return res;
            }
            if (ql <= mid) {
                return findRightmostZero(idx << 1, l, mid, ql, qr);
            }
            return -1;
        }

        int find(int l, int r) {
            return findRightmostZero(1, 0, n - 1, l, r);
        }
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;

        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        SegTree st = new SegTree(n);

        // Initial contribution: first occurrence onward
        for (Map.Entry<Integer, List<Integer>> e : pos.entrySet()) {
            int v = e.getKey();
            int sign = (v & 1) == 1 ? 1 : -1;
            st.add(e.getValue().get(0), n - 1, sign);
        }

        Map<Integer, Integer> ptr = new HashMap<>();
        for (int v : pos.keySet()) ptr.put(v, 0);

        int ans = 0;

        for (int l = 0; l < n; l++) {
            int r = st.find(l, n - 1);
            if (r != -1) {
                ans = Math.max(ans, r - l + 1);
            }

            int v = nums[l];
            int sign = (v & 1) == 1 ? 1 : -1;

            int idx = ptr.get(v);
            ptr.put(v, idx + 1);

            List<Integer> list = pos.get(v);
            int nextPos = (idx + 1 < list.size()) ? list.get(idx + 1) : n;

            st.add(l, nextPos - 1, -sign);
        }

        return ans;
    }
}
