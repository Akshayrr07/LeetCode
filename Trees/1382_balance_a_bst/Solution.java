class Solution {
    private final List<Integer> sortedValues = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        collectInorder(root);
        return buildBalanced(0, sortedValues.size() - 1);
    }

    private void collectInorder(TreeNode node) {
        if (node == null) return;
        collectInorder(node.left);
        sortedValues.add(node.val);
        collectInorder(node.right);
    }

    private TreeNode buildBalanced(int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(sortedValues.get(mid));
        root.left = buildBalanced(left, mid - 1);
        root.right = buildBalanced(mid + 1, right);
        return root;
    }
}
