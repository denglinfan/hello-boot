package com.example.helloboot.articles.redBlackTree;

public class RedBlackTree<T extends Comparable<T>,K extends Comparable<K>> {

    private Node<T,K> mRoot;                //根结点

    private static final Integer RED = 1;
    private static final Integer BLACK = 0;

    class Node<T extends Comparable<T>,K extends Comparable<K>>{
        T key;

        K value;

        Node<T,K> parent;

        Node<T,K> left;

        Node<T,K> right;

        Integer color = RED;

        public Node(T key, K value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public K getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "key:" + key + "===>value:" + value + "===>color:" + ((this.color == RED)?"R":"B");
        }
    }

    public RedBlackTree(){
        mRoot = null;
    }

    private Node<T,K> parentOf(Node<T,K> node){
        return node == null ? null : node.parent;
    }

    private Integer colorOf(Node<T,K> node){
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<T,K> node){
        return (node != null && node.color == RED)? true:false;
    }

    private boolean isRed(Node<T,K> node){
        return !isBlack(node);
    }

    private void setBlack(Node<T,K> node){
        if(node != null){
            node.color = BLACK;
        }
    }

    private void setRed(Node<T,K> node){
        if(node != null){
            node.color = RED;
        }
    }

    private void setParent(Node<T,K> node,Node<T,K> parent){
        if(node != null){
            node.parent = parent;
        }
    }

    private void setColor(Node<T,K> node,Integer color){
        if(node != null){
            node.color = color;
        }
    }

    /**
     * 前序遍历红黑树
     */
    public void preOrder(){
        preOrder(mRoot);
    }

    private void preOrder(Node<T,K> tree){
        if(tree != null){
            System.out.println("key:" + tree.key + "====>value:" + tree.value + "====>color:" + tree.color);
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /**
     * 中序遍历红黑树
     */
    public void inOrder(){
        inOrder(mRoot);
    }

    private void inOrder(Node<T,K> tree){
        if(tree != null){
            inOrder(tree.left);
            System.out.println("key:" + tree.key + "====>value:" + tree.value + "====>color:" + tree.color);
            inOrder(tree.right);
        }
    }

    /**
     * 后序遍历红黑树
     */
    public void postOrder(){
        postOrder(mRoot);
    }

    private void postOrder(Node<T,K> tree){
        if(tree != null){
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.println("key:" + tree.key + "====>value:" + tree.value + "====>color:" + tree.color);
        }
    }

    /**
     * 递归查找树中节点为key的节点
     * @param key
     * @return
     */
    public Node<T, K> search(T key) {
        return search(mRoot,key);
    }

    private Node<T,K> search(Node<T,K> root,T key){
        if(root == null){
            return null;
        }
        int cmp = key.compareTo(root.key);
        if(cmp < 0){
            return search(root.left,key);
        }else if(cmp > 0){
            return search(root.right,key);
        }else{
            return root;
        }
    }

    /**
     * 非递归查找树中的key所在节点
     * @param key
     * @return
     */
    public Node<T, K> iterativeSearch(T key) {
        return  iterativeSearch(mRoot,key);
    }

    private Node<T,K> iterativeSearch(Node<T, K> mRoot, T key) {
        while(mRoot != null){
            int cmp = key.compareTo(mRoot.key);
            if(cmp < 0){
                mRoot = mRoot.left;
            }else if(cmp > 0){
                mRoot = mRoot.right;
            }else{
                return mRoot;
            }
        }
        return null;
    }

    /**
     * 查找最小结点：返回tree为根结点的红黑树的最小结点。
     * @return
     */
    public T minimum(){
        Node<T,K> node = minimum(mRoot);
        if(node != null){
            return node.key;
        }
        return null;
    }

    public Node<T,K> minimum(Node<T,K> tree){
        if(tree == null){
            return null;
        }

        while(tree.left != null){
            tree = tree.left;
        }
        return tree;
    }

    /**
     * 查找最大结点：返回tree为根结点的红黑树的最大结点。
     * @return
     */
    public T maximum(){
        Node<T,K> node  = maximum(mRoot);
        if(node != null){
            return node.key;
        }
        return null;
    }

    public Node<T,K> maximum(Node<T,K> tree){
        if(tree == null){
            return null;
        }

        while(tree.right != null){
            tree = tree.right;
        }
        return tree;
    }

    /**
     * 找结点(x)的后继结点。即，查找"红黑树中数据值大于该结点"的"最小结点"。
     * @return
     */
    public Node<T, K> successor(Node<T,K> node) {
        if(node == null){
            return null;
        }
        if(node.right != null){
            return minimum(node.right);
        }

        /** 如果x没有右孩子。则x有以下两种可能：
         * (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
         * (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
         */
        Node<T,K> y = node.parent;
        while(y != null && node == y.right){
            node = y;
            y = y.parent;
        }

        return y;
    }

    /**
     * 找结点(x)的前驱结点。即，查找"红黑树中数据值小于该结点"的"最大结点"。
     * @param node
     * @return
     */
    public Node<T,K> predeessor(Node<T,K> node){
        if(node == null){
            return null;
        }
        if(node.left != null){
            return maximum(node.left);
        }

        /** 如果x没有左孩子。则x有以下两种可能：
         * (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
         * (02) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
         */
        Node<T,K> y = node.parent;
        while(y != null && node == y.left){
            node = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    x                               y
     *   / \      --(左旋)-.             / \
     *  lx  y                          x  ry
     *     / \                        / \
     *    ly  ry                     lx ly
     *
     *
     */
    private void leftRotate(Node<T,K> nodeTree){
        Node<T,K> rightChild = nodeTree.right;

        nodeTree.right = rightChild.left;
        if(rightChild.left != null){
            rightChild.left.parent = nodeTree;
        }

        rightChild.parent = nodeTree.parent;

        if(nodeTree.parent == null){
            this.mRoot = rightChild;
        }else{
            if(nodeTree.parent.left == nodeTree){
                nodeTree.parent.left = rightChild;
            }else{
                nodeTree.parent.right = rightChild;
            }
        }

        rightChild.left = nodeTree;
        nodeTree.parent = rightChild;
    }

    /**
     * 对红黑树的节点(y)进行右旋转
     *
     * 右旋示意图(对节点y进行左旋)：
     *            py                               py
     *           /                                /
     *          y                                x
     *         / \      --(右旋)-.              /  \                     #
     *        x   ry                          lx   y
     *       / \                                  / \                   #
     *      lx  rx                              rx  ry
     *
     */
    private void rightRotate(Node<T,K> nodeTree){
        Node<T,K> leftNode = nodeTree.left;

        nodeTree.left = leftNode.right;
        if(leftNode.right != null){
            leftNode.right.parent = nodeTree;
        }

        leftNode.parent = nodeTree.parent;
        if(nodeTree.parent == null){
            this.mRoot = leftNode;
        }else{
            if(nodeTree.parent.left == nodeTree){
                nodeTree.parent.left = leftNode;
            }else{
                nodeTree.parent.right = leftNode;
            }
        }

        leftNode.right = nodeTree;
        nodeTree.parent = leftNode;
    }

    /**
     * 红黑树插入修正函数
     *
     * 在向红黑树中插入节点之后(失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     *
     * 参数说明：
     *     node 插入的结点        // 对应《算法导论》中的z
     */
    private void insertFixUp(Node<T,K> node){
        Node<T,K> parent, gParent;
        //若父节点存在并且父节点为红色
        while(((parent = parentOf(node)) != null) && isRed(parent)){
            gParent  = parentOf(parent);

            //若父节点是祖父节点到左孩子
            if(parent == gParent.left){
                //case 1:叔叔节点是红色
                Node<T,K> uncle = gParent.right;
                if(uncle != null && isRed(uncle)){
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gParent);
                    node = gParent;
                    continue;
                }
                //case 2:叔叔节点是黑色,并且当前节点是右孩子
                if(node == parent.right){
                    Node<T,K> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //case 3:叔叔节点是黑色，并且当前节点是左孩子
                setRed(gParent);
                setBlack(parent);
                rightRotate(gParent);
            }else{//若父节点是祖父节点到右孩子
                //case 1:叔叔节点是红色
                Node<T,K> uncle = gParent.left;
                if(uncle != null && isRed(uncle)){
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gParent);
                    node = gParent;
                    continue;
                }

                //case 2:叔叔节点是黑色并且当前节点是父节点左孩子
                if(node == parent.left){
                    Node<T,K> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //case 3:叔叔节点是黑色并且当前节点是父节点到右孩子
                setBlack(parent);
                setRed(gParent);
                leftRotate(gParent);
            }
        }
        //设置根节点为黑色
        setBlack(this.mRoot);
    }

    /**
     * 将结点插入到红黑树中
     *
     * 参数说明：
     *     node 插入的结点        // 对应《算法导论》中的node
     */
    private void insert(Node<T,K> node){
        int cmp;
        Node<T,K> y = null;
        Node<T,K> x = this.mRoot;

        //将红黑树当作一颗二叉查找树，将节点插入到树中
        if(x != null){
            y = x;
            cmp = node.key.compareTo(x.key);
            if(cmp < 0){
                x = x.left;
            }else{
                x = x.right;
            }
        }
        node.parent = y;
        if(y != null){
            cmp = node.key.compareTo(y.key);
            if(cmp < 0){
                y.left = node;
            }else{
                y.right = node;
            }
        }else{
            this.mRoot = node;
        }
        //将它重新修正为一颗红黑树
        insertFixUp(node);
    }

    /**
     * 新建结点(key)，并将其插入到红黑树中
     *
     * 参数说明：
     *     key 插入结点的键值
     */
    public void insert(T key,K value){
        Node<T,K> node = new Node<T,K>(key,value);

        if(node != null){
            insert(node);
        }
    }

    /**
     * 红黑树删除修正函数
     *
     * 在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     *
     * 参数说明：
     *     node 待修正的节点
     */
    private void removeFixUp(Node<T,K> node,Node<T,K> parent){
        Node<T,K> other;

        while((node == null || isBlack(node)) && (node != this.mRoot)){
            if(parent.left == node){
                other = parent.right;
                if(isRed(other)){
                    //case 1:node节点的兄弟节点是红色
                    setBlack(other);
                    setRed(node);
                    leftRotate(parent);
                    other = parent.right;
                }

                if((other.left == null || isBlack(other.left))
                        &&(other.right == null || isBlack(other.right))){
                    //case 2:node的兄弟节点是黑色，且其兄弟节点的两个孩子都是黑色
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                }else{
                    if(other.right == null || isBlack(other.right)){
                        //case 3:node的兄弟节点是黑色，并且其左孩子是红色，右孩子是黑色
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    //case 4:node的兄弟是黑色，并且其左孩子是黑色，右孩子是红色
                    setColor(other,colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.mRoot;
                    break;
                }
            }else{
                other = parent.left;
                if(isRed(other)){
                    //case 1:node的兄弟节点是红色
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if((other.left == null || isBlack(other.left))
                        &&(other.right == null || isBlack(other.right))){
                    //case 2:node的兄弟节点是黑色，并且其兄弟节点的孩子均为黑色
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                }else{
                    if(other.left == null || isBlack(other.left)){
                        //case 3:node的兄弟节点是黑色，并且其兄弟节点左孩子是红色，右孩子是黑色
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    //case 4:node的兄弟节点是黑色，并且其兄弟节点的左孩子是黑色，右孩子是红色
                    setColor(other,colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.mRoot;
                    break;
                }
            }
        }
        if(node != null){
            setBlack(node);
        }
    }

    /**
     * 删除结点(node)，并返回被删除的结点
     *
     * 参数说明：
     *     node 删除的结点
     */
    private void remove(Node<T,K> node){
        Node<T,K> child,parent;
        Integer color;
        //被删除节点到左右孩子都不为空到情况
        if(node.left != null && node.right != null){
            //找到该节点到后继节点，作为代替节点，取代被删除节点
            Node<T,K> replace = node;
            replace = replace.right;
            while(replace.left != null){
                replace = replace.left;
            }
            //node节点不是根结点，只有根结点不存在父节点
            if(parentOf(node) != null){
                if(parentOf(node).left == node){
                    parentOf(node).left = replace;
                }else{
                    parentOf(node).right = replace;
                }
            }else{
                //node节点是根结点，更新根结点
                mRoot = replace;
            }

            //child是取代节点到右孩子，则需要调整其新到父节点。该节点必定不会存在左孩子
            child = replace.right;
            parent = parentOf(replace);

            //保存取代节点的颜色
            color = colorOf(replace);

            //若被删除节点是其后继节点的父节点
            if(parent == node){
                parent = replace;
            }else{
                //child不为空
                if(child != null){
                    setParent(child,parent);
                }
                parent.left = child;
                replace.right = node.right;

                setParent(node.right,replace);
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if(color == BLACK){
                removeFixUp(child,parent);
            }

            node = null;
            return;
        }

        if(node.left != null){
            child = node.left;
        }else{
            child = node.right;
        }
        parent = node.parent;
        color = node.color;

        if(child == null){
            child.parent = parent;
        }

        //若node节点不是根结点
        if(parent != null){
            if(parent.left == node){
                parent.left = child;
            }else{
                parent.right = child;
            }
        }else{
            this.mRoot = child;
        }

        if(color == BLACK){
            removeFixUp(node,parent);
        }
        node = null;
    }

    /**
     * 删除结点(z)，并返回被删除的结点
     *
     * 参数说明：
     *     tree 红黑树的根结点
     *     z 删除的结点
     */
    public void remove(T key){
        Node<T,K> node;
        if((node = search(mRoot,key)) != null){
            remove(node);
        }
    }

    /*
     * 销毁红黑树
     */
    private void destroy(Node<T,K> tree) {
        if (tree==null)
            return ;

        if (tree.left != null)
            destroy(tree.left);
        if (tree.right != null)
            destroy(tree.right);

        tree=null;
    }

    public void clear() {
        destroy(mRoot);
        mRoot = null;
    }

    /*
     * 打印"红黑树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(Node<T,K> tree, T key, int direction) {

        if(tree != null) {

            if(direction==0)    // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, isRed(tree)?"R":"B", key, direction==1?"right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right,tree.key,  1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
