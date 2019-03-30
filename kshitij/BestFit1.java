package kshitij;
import java.io.*;
import java.util.*;
//import javafx.util.Pair;

class LargeObjectException extends Exception{
    LargeObjectException(String s){
        super(s);
    }
}
class SameIdExistsException extends Exception{
    SameIdExistsException(String s) { super(s);}
}
public class BestFit1{
    AVL bin_sizes;
    AVL bin_ids;
    static AVL objs;
    public BestFit1(){
        bin_sizes = new AVL();
        bin_ids = new AVL();
        objs = new AVL();
    }
    public void add_bin(int bin_id, int capacity) throws SameIdExistsException{
        if (bin_ids.root == null){
            bin_ids.root = new Node(bin_id);
            bin_sizes.root = new Node(capacity);
            bin_ids.root.val1 = bin_sizes.root;
            bin_sizes.root.val1 = bin_ids.root;
        }
        else{
            Node n = new Node(bin_id);
            boolean chk = bin_ids.insert(n);
            if(!chk)
                throw new SameIdExistsException("This id already exists!!");
            Node m = bin_sizes.insert(capacity);
            n.val1 = m;
            m.val1 = n;
        }
    }

    public int add_object(int obj_id, int size) throws LargeObjectException, SameIdExistsException{
        Node ob = new Node(obj_id);
        ob.size = size;

        Node cur = bin_sizes.root;
        while(cur.right!=null)
            cur = cur.right;
        Node bin = cur.val1;

//        if(cur.val1.elem == 96) {
//            System.out.print("Main kaa karun ");
//            System.out.println(cur.elem);
////            Node k = null;
////            try{
////            k = bin_ids.search(62);}catch(Exception e){}
////            System.out.println(" Bhakarbhakar " + k.val1.elem);
//        }
        //updating size
        if(size>cur.elem) {
            return -1;
//            throw new LargeObjectException("The object cannot be fit in any available bin");
        }
        else{
            if (objs.root == null){
                objs.root = ob;
            }
            else {
                boolean chk = objs.insert(ob); //error if larger than biggest bin
                if(!chk)
                    throw new SameIdExistsException("This id already exists!!");
            }
            pointer p = new pointer(ob);
            bin.list.add(p);

            int temp_cap = cur.elem;
//            Node temp_val1 = cur.val1;
//            DoublyList temp_list = cur.list;
//            LinkedList<pointer> temp_list = cur.list;
            bin_sizes.delete(cur);
            Node ins = new Node(temp_cap - size);
//            ins.list = temp_list;
//            ins.val1 = temp_val1;
            ins.val1 = bin;
//            bin.val1 = ins;//locha
            bin_sizes.insert(ins);
//            ins.val1 = bin;
            bin.val1 = ins;//locha
            ob.val1 = bin;
            ob.val2 = p;
        }
        return cur.val1.elem;
    }

    public int delete_object(int obj_id){
        // Remove the object with id "id" from the bin it is placed in.
        Node ob = null;
        try{ob = objs.search(obj_id);}
        catch(Exception e){System.out.println(e);}
        Node bin = ob.val1;
        Node cur = bin.val1;
        boolean xtra = false;



        bin.list.remove(ob.val2);
        int temp_size = ob.size;
//        if(ob.elem == 9) {
//            xtra = true;
//            System.out.print("Main kaa karun ");
////            System.out.println(ob.size);
//            try{Node t= objs.search(10);System.out.println(t.size);}catch (Exception e){}
//        }
        objs.delete(ob);
//         if(xtra){
//            System.out.print("Main kaa karun2 ");
//            try{Node t= objs.search(10);System.out.println(t.size);}catch (Exception e){}
//        }
        int temp_cap = cur.elem;
//        LinkedList<pointer> temp_list = cur.list;
        bin_sizes.delete(cur);
//        if(bin.elem==40)
//            System.out.println("aayehaaye2 "+cur.elem+" "+temp_size);
        Node ins = new Node(temp_cap + temp_size);
//        ins.list = temp_list;
//        ins.val1 = temp_val1;
        ins.val1 = bin;
        bin.val1 = ins;
        bin_sizes.insert(ins);
//        if(bin.elem == 96) {
//            System.out.println("huff "+ temp_cap+" "+temp_size);
//            System.out.print("Main kaa karun2 ");
//            System.out.println(ins.elem);
//        }

//        ins.val1 = bin;
//        bin.val1 = ins;
        // Return the bin_id from which this object was removed

        return bin.elem;
    }

//     public List<Pair<Integer, Integer>> contents(int bin_id){
// //        // Return the list of current objects in the bin with id "bin_id" : list of pairs  for each object in the bin.
//         Node cur = null;
//         try{cur = bin_ids.search(bin_id);}
//         catch(Exception e){System.out.println(e);}
// //        LinkedList<String> l = new LinkedList<>();
//         List<Pair<Integer, Integer>> ret = new ArrayList<>();
// //        ListIterator<pointer> iter = cur.list.listIterator();
//         pointer p = cur.list.head.next;
//         while (p!=cur.list.tail){
//             ret.add(new Pair(p.val.elem,p.val.size));
//             p = p.next;
// //            l.add(iter.next().val.elem+" "+iter.next().val.size);
//         }

//         return  ret;
//     }
    public static void main(String[] args) throws Exception{
        BestFit1 mod = new BestFit1();
        int count=0, j = 0;
        Scanner sc = new Scanner(new File("/Users/tanmaygoyal/Downloads/new_large-3.txt"));
//        FileWriter foo = new FileWriter("large_chk3.txt");
//        BufferedWriter out = new BufferedWriter(foo);
//        Writer out = new BufferedWriter(new OutputStreamWriter(
//                new FileOutputStream("large_chk3.txt"), "utf-8"));
        while(sc.hasNextLine()){
            j++;

            String comm = sc.nextLine();
            String[] ar = comm.split(" ");
            int arr[] = new int[ar.length];
            for(int t = 0;t<ar.length; t++)
                arr[t] = Integer.parseInt(ar[t]);
//            try {
                if (arr[0] == 1) {
                    try {
                        mod.add_bin(arr[1], arr[2]);
                    } catch (Exception e) {
                        System.out.println(j+" "+e+" "+comm);
                        System.exit(1);
                    }
                } else if (arr[0] == 2) {
                    try {
                        int t = mod.add_object(arr[1], arr[2]);
                        if (t != -1) {
                            System.out.println(t);
//                            out.write(Integer.toString(t));
//                            out.newLine();
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println(j+" "+e+" "+comm);
                        System.exit(1);
                    }
                } else if (arr[0] == 3) {
                    try {
                        int t = mod.delete_object(arr[1]);
                        System.out.println(t);
//                        out.write(Integer.toString(t));
//                        out.newLine();
                        count++;
                    } catch (Exception e) {
                        System.out.println(e);
                        System.exit(1);

                    }
                } else if (arr[0] == 4) {
                   // List<Pair<Integer, Integer>> ret = mod.contents(arr[1]);
                    //ListIterator<Pair<Integer, Integer>> iter = ret.listIterator();
//                     while (iter.hasNext()) {
//                         Pair<Integer, Integer> elem = iter.next();
//                         System.out.println(elem.getKey() + " " + elem.getValue());
// //                    out.write(Integer.toString(elem.getKey())+" " +Integer.toString(elem.getValue()));
// //                    out.newLine();
                        count++;
                    


                }

        }
//        out.close();
//        mod.bin_ids.print(mod.bin_ids.root);
        AVL n = new AVL();
        System.out.println(objs.height(objs.root));

    }
}