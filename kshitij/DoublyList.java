package kshitij;
public class DoublyList {
    pointer head, tail;
    int len;
    DoublyList(){
        head = new pointer(null);
        tail = new pointer(null);
        len = 0;
        head.next = tail;
        tail.prev = head;
    }
    DoublyList(pointer node) {
        head = new pointer(null);
        tail = new pointer(null);
        len = 1;
        head.next = node;
        node.prev = head;
        node.next = tail;
        tail.prev = node;
    }

    void add(pointer node){
        pointer t = tail.prev;
        t.next = node;
        node.prev = t;
        node.next = tail;
        tail.prev = node;
        len += 1;
    }

    //    void del_emp(Node e){
//        if (head.elem!=e){
//            pointer cur = head;
//            if(tail.elem==e){
//                tail = tail.prev;
//                tail.next = null;
//            }
//            else {
//                while (cur.next != null && cur.next.elem != e) {//System.out.println(cur.elem.name+" boo");
//                    cur = cur.next;
//                }
//                if (cur.next != null)
//                    cur.next = (cur.next).next;
//            }
//        }
//        else
//            head = head.next;
//        len -= 1;
//    }
    void remove(pointer p){
        if (p!=head && p!=tail){
            pointer pr = p.prev;
            pointer ne = p.next;
            pr.next = ne;
            ne.prev = pr;}
    }

    void print_list(){
        pointer cur = head.next;
        while(cur!=tail) {
            System.out.print(cur.val.elem+" ");
            cur = cur.next;
        }
    }

    public static void main(String[] args){
        pointer node = new pointer(new Node(8));
        DoublyList ls = new DoublyList(node);
        ls.add(new pointer(new Node(7)));
        ls.add(new pointer(new Node(2)));
        ls.add(new pointer(new Node(9)));
        pointer t1 = new pointer(new Node(15));
        ls.add(t1);
        ls.add(new pointer(new Node(4)));
        ls.print_list();
//        ls.del_emp(node.elem);
//        ls.del_emp(t1.elem);
//        ls.print_list();
//        ChildList mer = new ChildList(new Node("G",1));
//        mer.add(new Node("H",1));
//        ls.merge(mer);
        ls.remove(t1);
        ls.print_list();
//        System.out.println(ls.len+ " "+ls.tail.elem.name+" "+ls.head.elem.name);
    }

}
