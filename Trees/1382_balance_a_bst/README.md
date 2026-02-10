## 1382. Balance a Binary Search Tree

### Intuition
Inorder traversal of a BST produces sorted values. Rebuilding the tree using the middle element ensures balance.

### Approach
  
  <img width="1896" height="1068" alt="Balancing a Binary Search Tree" src="https://github.com/user-attachments/assets/234e0179-4a9c-4fa3-9fe5-b025029590a8" />


1. Inorder traversal to collect sorted values

   <img width="1115" height="937" alt="bt-inorder-traversal-in" src="https://github.com/user-attachments/assets/215faa59-3299-46a8-8e92-35a4d45678cf" />

3. Recursive reconstruction using mid element

   <img width="1400" height="993" alt="1_PBYLHFeQxh3l7zhJPBZf2w" src="https://github.com/user-attachments/assets/be78e8ed-a630-4cf3-9f41-0ddee2be2810" />

### Steps

  <img width="480" height="500" alt="steps" src="https://github.com/user-attachments/assets/51927836-3575-4c70-b797-bd0e1a153023" />

### Complexity
- Time: O(n)
- Space: O(n)

### Edge Cases
- Empty tree
- Skewed Binary Search Tree
