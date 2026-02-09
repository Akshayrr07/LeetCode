# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def balanceBST(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        sorted_values = []

        def collect_inorder(node):
            if node is None:
                return
            collect_inorder(node.left)
            sorted_values.append(node.val)
            collect_inorder(node.right)

        def build_balanced(left, right):
            if left > right:
                return None
            mid = (left + right) // 2
            root = TreeNode(sorted_values[mid])
            root.left = build_balanced(left, mid - 1)
            root.right = build_balanced(mid + 1, right)
            return root

        collect_inorder(root)
        return build_balanced(0, len(sorted_values) - 1)
