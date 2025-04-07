class SplayTree {

    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.left = this.right = null;
        }
    }

    private Node root;

    // Helper function to right rotate a subtree rooted with y
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        return x;
    }

    // Helper function to left rotate a subtree rooted with x
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        return y;
    }

    // This function brings the key to root if key is present in tree.
    // If key is not present, then it brings the last accessed node to root.
    private Node splay(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (key < root.key) {
            // Key is in left subtree
            if (root.left == null) {
                return root;
            }

            if (key < root.left.key) {
                // Left-Left case (Zig-Zig)
                root.left.left = splay(root.left.left, key);
                root = rightRotate(root);
            } else if (key > root.left.key) {
                // Left-Right case (Zig-Zag)
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }

            return (root.left == null) ? root : rightRotate(root);
        } else {
            // Key is in right subtree
            if (root.right == null) {
                return root;
            }

            if (key < root.right.key) {
                // Right-Left case (Zig-Zag)
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            } else if (key > root.right.key) {
                // Right-Right case (Zig-Zig)
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            }

            return (root.right == null) ? root : leftRotate(root);
        }
    }

    // Function to insert a new key k in splay tree
    public void insert(int key) {
        Node z = new Node(key);
        if (root == null) {
            root = z;
            return;
        }

        root = splay(root, key);

        if (key < root.key) {
            z.right = root;
            z.left = root.left;
            root.left = null;
            root = z;
        } else if (key > root.key) {
            z.left = root;
            z.right = root.right;
            root.right = null;
            root = z;
        } else {
            // Key already exists, no insertion needed
            return;
        }
    }

    // Function to search a given key k in the splay tree
    public boolean search(int key) {
        root = splay(root, key);
        return (root != null && root.key == key);
    }

    // Function to delete a key from the splay tree
    public void delete(int key) {
        if (root == null)
            return;

        root = splay(root, key);

        if (root.key == key) {
            Node temp = root;
            if (root.left == null) {
                root = root.right;
            } else {
                root = splay(root.left, key);
                root.right = temp.right;
            }
        }
    }

    // A utility function to print preorder traversal of the tree.
    // The function also prints height of every node
    public void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void main(String[] args) {
        SplayTree tree = new SplayTree();

        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);

        System.out.println("Preorder traversal of the constructed Splay tree is ");
        tree.preOrder(tree.root);  // Output: Varies depending on splay operations

        System.out.println();

        System.out.println("Search 10: " + tree.search(10));  // Output: true
        System.out.println("Search 25: " + tree.search(25));  // Output: false

        tree.delete(10);

        System.out.println("Preorder traversal after deleting 10");
        tree.preOrder(tree.root);
    }
}