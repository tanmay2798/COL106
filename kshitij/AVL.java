package kshitij;


//import java.util.LinkedList;

class NodeDoesNotExistException extends Exception{
    NodeDoesNotExistException(String s){
        super(s);
    }
}

class pointer{
    Node val = null;
    pointer next, prev;
    pointer(Node n){
        val = n;
        next = prev = null;
    }
}
class Node {
    Node left;
    Node right;
    Node parent;
    int elem;
    int height;
    int size;
    Node val1;
    pointer val2;
    DoublyList list = new DoublyList();
    //    LinkedList<pointer> list = new LinkedList<>();
    Node(int n){
        left = right = parent = null;
        height = 0; size = 0;
        elem = n;
        val1 = null; val2 =null;
    }
    void setLeft(Node n){
        left = n;
        if (n!=null)
            n.parent = this;
    }
    void setRight(Node n){
        right = n;
        if(n!=null)
            n.parent = this;
    }
    boolean isLeft(){ return this.parent.left==this;}

}

public class AVL {
    Node root;
    public AVL() { root = null;}
    public AVL(Node n){
        root = n;
    }
    boolean isLeaf(Node n){return n==null;}
    int height(Node n){ return (n==null)?0:n.height;    }
    void updateheight(Node cur){cur.height=(height(cur.left)>height(cur.right))?1+height(cur.left):1+height(cur.right); }

    public boolean insert(Node n) {
        //insert as in bst
        Node cur = root;
        boolean chk = true;
        while (true) {
            if (n.elem < cur.elem) {
                if (cur.left == null) {
                    cur.setLeft(n);
                    break;
                }
                cur = cur.left;
            }
            else if (n.elem > cur.elem){ //exp
//                if (n.elem == cur.elem)
//                    chk = false;
                if (cur.right == null) {
                    cur.setRight(n);
                    break;
                }
                cur = cur.right;
            }
            else{
                chk = false;
                if(n.val1!=null && n.val1.elem < cur.val1.elem){
                    if (cur.left == null) {
                        cur.setLeft(n);
                        break;
                    }
                    cur = cur.left;

                }
                else{
                    if (cur.right == null) {
                        cur.setRight(n);
                        break;
                    }
                    cur = cur.right;
                }
            }
        }
        //traversing till root, updating heights & checking for AVL rule violation
        n.height = 1;
        condchk(n);
//        if(n.parent.left!=n && n.parent.right!=n)
//            System.out.println("HaddHadd");
        return chk;
    }
    Node search(int n) throws NodeDoesNotExistException{
        Node cur = root;
        while(cur!=null) {
            if (n<cur.elem)
                cur = cur.left;
            else if (n>cur.elem)
                cur = cur.right;
            else
                return cur;
        }
        throw new NodeDoesNotExistException("The searched entity does not exist");
    }
    public void delete(Node cur) {
        //delete as in bst

        if (cur!=root) {

            if (cur.left == null && cur.right == null) {
                if (cur.isLeft())
                    cur.parent.left = null;
                else{
                    cur.parent.right = null;
                }
//            System.out.println(cur.parent.elem);
            } else if (cur.left == null) {
                if (cur.isLeft())
                    cur.parent.setLeft(cur.right);
                else
                    cur.parent.setRight(cur.right);
            } else if (cur.right == null) {
                if (cur.isLeft())
                    cur.parent.setLeft(cur.left);
                else
                    cur.parent.setRight(cur.left);
            } else {

                Node rep = cur.right;
                while (rep.left != null)
                    rep = rep.left;
                cur.elem = rep.elem;
                Node b = rep.val1;
                cur.val1 = b;
                if(rep.val2==null)
                    b.val1 = cur;
                else
                    cur.size = rep.size;
                cur.val2 = rep.val2;
//                cur.list = rep.list;
//            cur.val = rep.val;
//            System.out.println(rep.elem+"HHHHH");
                delete(rep);
            }

        }
        else{
//            boolean xtra=false;
//            if(cur.elem == 9) {
//                xtra = true;
//                System.out.print("Main kaa karun ");
//                try{Node t= search(10);System.out.println(t.size);}catch (Exception e){}
//            }
            if(cur.left==null && cur.right==null)
                root = null;
            else if (cur.left == null) {
                root = cur.right;
                root.parent = null;
            }
            else if (cur.right == null) {
                root = cur.left;
                root.parent = null;
            }
            else{
                Node rep = cur.right;
                while (rep.left != null)
                    rep = rep.left;
//                if(xtra){
//                    System.out.println(rep.elem);
//                }
                cur.elem = rep.elem;
                Node b = rep.val1;
                cur.val1 = b;
                if(rep.val2==null)
                    b.val1 = cur;
                else
                    cur.size = rep.size;
                cur.val2 = rep.val2;
//                cur.list = rep.list;
                delete(rep);
            }
//            if(xtra) {
//                System.out.print("Main kaa karun2 ");
//                try{Node t= search(10);System.out.println(t.size);}catch (Exception e){}
//            }
        }

        //traversing till root, updating heights & checking for AVL rule violation
        condchk(cur);

    }
    void condchk(Node cur){
        while (cur != root) {
            cur = cur.parent;
//            System.out.println("BBBB"+cur.elem);
            int temp = cur.height;
            updateheight(cur);
//            System.out.println("Hoi"+cur.elem+" " +cur.height+" "+height(cur.left)+" "+height(cur.right));
            if (Math.abs(height(cur.left) - height(cur.right)) > 1) {
//                System.out.println(cur.elem+"HHHHHH");
                Node Z = cur;
                Node Y = (height(cur.left) > height(cur.right))?cur.left:cur.right;
                Node X;
                if (height(Y.left)>height(Y.right))
                    X = Y.left;
                else if (height(Y.left)<height(Y.right))
                    X = Y.right;
                else if (Y.isLeft())
                    X = Y.left;
                else
                    X = Y.right;
//                System.out.println(Z.elem+" "+Y.elem+" "+X.elem);
//                System.out.println(root.left.left);
                Node t = restruct(Z,Y,X);
//                System.out.println(root.left.left+" "+root.right+" ");
                cur = t;
            }
//            System.out.println(cur.height+" fgkk");
            if (cur.height == temp)
                break; //why does it end?
        }
    }
    Node restruct(Node Z, Node Y, Node X){
        Node p1 = (Y.isLeft())?Z.right:Z.left;
        Node p2 = (X.isLeft())?Y.right:Y.left;
        Node p3 = X.left;
        Node p4 = X.right;
        Node par = Z.parent;
//        System.out.println((p1==null));
        boolean f = false;
        if (Z!=root)
            f = Z.isLeft();
        Node t;
//        ArrayList<Node> arr = new ArrayList<>();
        if (!(Y.isLeft()) && !(X.isLeft())){//Z.elem<Y.elem && Y.elem<X.elem){
            Y.setLeft(Z);
            Z.setRight(p2);
            t = Y;
        }
        else if(Y.isLeft() && X.isLeft()){//Z.elem>=Y.elem && Y.elem>=X.elem){
            Y.setRight(Z);
            Z.setLeft(p2);
            t = Y;
        }
        else if(!(Y.isLeft()) && X.isLeft()){//Z.elem<Y.elem && X.elem<=Y.elem){
            X.setLeft(Z);
            X.setRight(Y);
            Y.setLeft(p4);
            Z.setRight(p3);
            t = X;
        }
        else{
            X.setRight(Z);
            X.setLeft(Y);
            Y.setRight(p3);
            Z.setLeft(p4);
            t = X;
        }
        updateheight(Z);
        if(t==X)updateheight(Y);
        else updateheight(X);
        updateheight(t);
        if (Z==root)
            root = t;
        else if (f)
            par.setLeft(t);
        else
            par.setRight(t);
        return t;
    }
    Node insert(int n){
        Node t = new Node(n);
        insert(t);
        return t;
    }
    void delete(int s) throws NodeDoesNotExistException{
        Node del = search(s);
        delete(del);
    }
    void print(Node k){
//        System.out.println("Hi");
        if(k!=null){
            print(k.left);
            System.out.print(k.elem+" " +k.val1.elem+" objs"+" ");
            DoublyList d = k.list;
            d.print_list();
            System.out.println();
            print(k.right);
        }
    }
    void preorder(Node k){
        if(k!=null){
            System.out.println(k.elem);
            preorder(k.left);
            preorder(k.right);
        }
    }
//    int order_chk(Node k, int t){
//        if(k!=null){
//            order_chk(k.left,t);
//            if(k!=root && k.parent.left!=k && k.parent.right!=k)
//            {System.out.println("chihuahua_budbak " +k.elem); return -1;}
//            System.out.println(k.elem+" "+k.val1.elem);
//
//            order_chk(k.right,t);
//        }
//        return t;
//    }
    void postorder(Node k){
        if(k!=null){
            postorder(k.left);
            postorder(k.right);
            System.out.println(k.elem);
        }
    }
    public static void main(String[] args) throws NodeDoesNotExistException{
        Node t = new Node(7);
        AVL tree = new AVL(t);
        tree.insert(2);
        tree.insert(4);
        tree.insert(8);
        tree.insert(26);
//        tree.print(tree.root);
        tree.insert(54);
        tree.insert(3);
//        System.out.println("Kya");
        tree.insert(12);
        t = new Node(30);
        tree.insert(t);

//        tree.insert(30);
//        System.out.println("Ka");
        tree.insert(20);
        tree.insert(60);
//        System.out.println("Kuu");
        tree.insert(40);
        tree.print(tree.root);
//        System.out.println("Dekhun");
//        tree.preorder(tree.root);
        tree.delete(7);
        tree.delete(26);
//        tree.delete(12);
//        tree.delete(2);
        tree.delete(60);
//        System.out.println("Gudgobar");
        tree.print(tree.root);
//        System.out.println("Gudgobar66");
//        tree.preorder(tree.root);
        tree.delete(30);
//        System.out.println("Gudgobar2");
//        tree.print(tree.root);
        tree.delete(4);
        tree.print(tree.root);
        System.out.println("Nava nava");
        Node t1 = new Node(6);
        AVL tree2 = new AVL(t1);
//        tree2.insert(6);
        tree2.insert(2);
        t1 = new Node(8);
        tree2.insert(t1);
        tree2.insert(3);
        tree2.insert(6);
//        System.out.println(tree2.root.elem);
//        System.out.println(tree2.root.left.elem);
//        System.out.println(tree2.root.right.elem);
//        System.out.println(tree2.root.left.left.elem + " "+tree2.root.left.left.left.elem + " "+tree2.root.right.left + " "+tree2.root.right.right + " ");
        tree2.print(tree2.root);
        tree2.delete(t1);
        System.out.println("Bhak");
        tree2.print(tree2.root);
    }
}
