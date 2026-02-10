## Longest Balanced Subarray

### Problem
Find the longest subarray where the number of distinct even elements equals the number of distinct odd elements.

### Approach
Brute-force expansion with hash sets to track distinct values.
<img width="1807" height="604" alt="Balanced Subarrays_ A Visual Guide - visual selection" src="https://github.com/user-attachments/assets/6c380181-3eb7-4825-b61e-64fa0c97b3b1" />

### Complexity
- Time: O(n²)
- Space: O(n)

### Notes
Sliding window is not applicable due to non-monotonic distinct counts.
