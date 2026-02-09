## 1382. Balance a Binary Search Tree

### Intuition
Inorder traversal of a BST produces sorted values. Rebuilding the tree using the middle element ensures balance.

### Approach

<div style="text-align: center;"><img src="Images\Balancing a Binary Search Tree.png" alt="Solution"/></div>

1. Inorder traversal to collect sorted values
2. Recursive reconstruction using mid element

### Complexity
- Time: O(n)
- Space: O(n)

### Edge Cases
- Empty tree
- Skewed BST
