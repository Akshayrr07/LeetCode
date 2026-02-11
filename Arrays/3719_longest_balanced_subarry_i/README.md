## Longest Balanced Subarray

### Problem
Find the longest subarray where the number of distinct even elements equals the number of distinct odd elements.

### Approach
Brute-force expansion with hash sets to track distinct values.

### Complexity
- Time: O(n²)
- Space: O(n)

### Notes
Sliding window is not applicable due to non-monotonic distinct counts.
