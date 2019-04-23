package assignment_5;
import java.util.*;

/**
 *
 * @author manuj
 */
public class checkfiles {
    public class Edge{
        int start_ind;
        int end_ind;
        Node par;
        Node child;
        
        public Edge(){
            start_ind = end_ind = -1;
            par = child = null;
        }
    }
    public class Node{
        ArrayList<Edge> arl = new ArrayList<>();
        boolean leaf_node;
        ArrayList<Integer> index = new ArrayList<>();
        Edge par;
        
        public Node(){
            //index.add(-1);
            leaf_node = false;
            par = null;
            //arl = null;
        }
    }
    
    Node main_root;
    
    public checkfiles(){
        this.main_root = new Node();
    }
    
    public void MakeTree(String s){
        Edge edge = new Edge(); // add first root-edge (complete string)
        edge.start_ind = 0;
        edge.end_ind = s.length()-1;
        edge.par = main_root;
        Node node = new Node();
        node.leaf_node = true;
        node.index.add(0);
        node.par = edge;
        edge.child = node;
        main_root.arl.add(edge);
        int i = 1;
        while(i < s.length()){ // loop to add each suffix
            boolean check = false;
            String a = s.substring(i); //add suffix to tree
            System.out.println(a);
            int j = 0;
            while(j < main_root.arl.size()){ // check if 1st char of suffix matches 1st char of any root-edge
                if(a.charAt(0) == s.charAt(main_root.arl.get(j).start_ind)){
                    //System.out.println(main_root.arl.get(j).start_ind);
                    //System.out.println(j);
                    match(a, j, s, i, main_root, i); //call function if 1st character matches
                    check = true;
                    break;
                }
                j++;
            }
            if(check == false){
                Edge edge1 = new Edge(); // create new root-edge for suffix
                edge1.start_ind = i;
                edge1.end_ind = s.length()-1;
                edge1.par = main_root;
                Node node1 = new Node();
                node1.leaf_node = true;
                node1.index.add(i);
                node1.par = edge1;
                edge1.child = node1;
                main_root.arl.add(edge1);
            }
            i++;
        }
    }
    
    public void match(String suf, int k, String s, int su, Node root, int old_su){ // k is index of root-arraylist element which matched
        //System.out.println(old_su);
        int i = 0; // su tells us from where the suffix starts in s
        boolean check1 = false;
        while(i <= (root.arl.get(k).end_ind - root.arl.get(k).start_ind) && i < suf.length()){
        //if(i <= (root.arl.get(k).end_ind - root.arl.get(k).start_ind)){ // check until edge has no more chars - i.e. you reach a node
            if(suf.charAt(i) == s.charAt(root.arl.get(k).start_ind + i)){ // check if next character of suffix matches that of edge
                i++;
//                Edge edge1 = new Edge(); // separate unmatched chars into new edge and node
//                edge1.start_ind = root.arl.get(k).start_ind;
//                edge1.end_ind = i - 1;
//                edge1.par = root;
//                Node node1 = new Node();
//                node1.leaf_node = false;
//                node1.par = edge1;
//                edge1.child = node1;
//                root.arl.add(edge1);
//
//                root.arl.get(k).start_ind = i;
//                root.arl.get(k).par = node1;
//                node1.arl.add(root.arl.get(k));
//                root.arl.remove(k);
//
//                Edge edge2 = new Edge(); // add remaining unmatched portion of suffix to new edge and node
//                edge2.start_ind = su + i;
//                edge2.end_ind = s.length() - 1;
//                edge2.par = node1;
//                Node node2 = new Node();
//                node2.leaf_node = true;
//                node2.index = su;
//                node2.par = edge2;
//                edge2.child = node2;
//                node1.arl.add(edge2);
            }
            else{ // reached the point where character doesn't match
                //System.out.println("asas");
                Edge edge2 = new Edge(); // separate unmatched chars into new edge and node
                edge2.start_ind = root.arl.get(k).start_ind;
                edge2.end_ind = edge2.start_ind + i - 1;
                edge2.par = root;
                Node node2 = new Node();
                node2.leaf_node = false;
                node2.par = edge2;
                edge2.child = node2;
                root.arl.add(edge2);

                root.arl.get(k).start_ind = edge2.start_ind + i;
                root.arl.get(k).par = node2;
                node2.arl.add(root.arl.get(k));
                node2.index.addAll(root.arl.get(k).child.index);
                root.arl.remove(k);

                Edge edge3 = new Edge(); // add remaining unmatched portion of suffix to new edge and node
                edge3.start_ind = su + i;
                edge3.end_ind = s.length() - 1;
                edge3.par = node2;
                Node node3 = new Node();
                node3.leaf_node = true;
                node3.index.add(old_su);
                node3.par = edge3;
                edge3.child = node3;
                node2.arl.add(edge3);
                while(node3.par != null){
                    node3.par.par.index.add(old_su);
                    node3 = node3.par.par;
                }
                check1 = true;
                break;
            }
        }
        if(i == suf.length()){
            //System.out.println(old_su);
            Edge edge2 = new Edge(); // separate unmatched chars into new edge and node
            edge2.start_ind = root.arl.get(k).start_ind;
            edge2.end_ind = edge2.start_ind + i - 1;
            edge2.par = root;
            Node node2 = new Node();
            node2.leaf_node = false;
            node2.index.add(old_su);
            node2.par = edge2;
            edge2.child = node2;
            root.arl.add(edge2);

            root.arl.get(k).start_ind = edge2.start_ind + i;
            root.arl.get(k).par = node2;
            node2.arl.add(root.arl.get(k));
            node2.index.addAll(root.arl.get(k).child.index);
            root.arl.remove(k);
            while(node2.par != null){
                node2.par.par.index.add(old_su);
                node2 = node2.par.par;
            }

//            Edge edge3 = new Edge(); // add remaining unmatched portion of suffix to new edge and node
//            edge3.par = node2;
//            Node node3 = new Node();
//            node3.leaf_node = true;
//            node3.index = old_su;
//            node3.par = edge3;
//            edge3.child = node3;
//            node2.arl.add(edge3);
            check1 = true;
        }
        if(check1 == false){ // you reach the node at the end of edge
            //System.out.println("cxcx");
            int j = 0;
            boolean check2 = false;
            while(j < root.arl.get(k).child.arl.size()){ // check if ith char of suffix matches 1st char of any new-node-edge
                if(suf.charAt(i) == s.charAt(root.arl.get(k).child.arl.get(j).start_ind)){
                    //System.out.println(suf);
                    match(suf.substring(i), j, s, su+i, root.arl.get(k).child, old_su); //call function if 1st character matches
                    check2 = true;
                    break;
                }
                j++;
            }
            if(check2 == false){
                Edge edge1 = new Edge(); // create new edge for remaining portion of suffix
                edge1.start_ind = su + i;
                edge1.end_ind = s.length()-1;
                edge1.par = root.arl.get(k).child;
                Node node1 = new Node();
                node1.leaf_node = true;
                node1.index.add(su);
                node1.par = edge1;
                edge1.child = node1;
                root.arl.get(k).child.arl.add(edge1);
                while(node1.par != null){
                    node1.par.par.index.add(su);
                    node1 = node1.par.par;
                }
            }
        }
    }
    
    public ArrayList<Integer[]> search(String p, String s, Node root, String orig){
        int j = 0, i = 1, l = 0;
        ArrayList<Integer> qm = new ArrayList<>();
        boolean check = false;
//        while(j < root.arl.size()){ // check if 1st char of suffix matches 1st char of any root-edge
        while(i < p.length()){
            if(Character.toString(p.charAt(i)).compareTo("*") == 0){
                String a1 = p.substring(0, i);
                String a2 = p.substring(i+1);
                ArrayList<Integer[]> l1 = new ArrayList<>();
                ArrayList<Integer[]> l2 = new ArrayList<>();
                ArrayList<Integer[]> l3 = new ArrayList<>();
                l1 = search(a1, s, root, orig);
                l2 = search(a2, s, root, orig);
                int q = 0;
                while(q < l1.size()){
                int o = 0;
                    while(o < l2.size()){
                        if(l2.get(o)[0] - l1.get(q)[1] == 1){
                            Integer[] in = new Integer[2];
                            in[0] = l1.get(q)[0];
                            in[1] = l2.get(o)[1];
                            l3.add(in);
                        }
                        o++;
                    }
                    q++;
                }
                return l3;
            }
            i++;
        }
        while(l < p.length()){ // store indices of all '?'
            //System.out.println(l);
            if(Character.toString(p.charAt(l)).compareTo("?") == 0){
                qm.add(l);
            }
            l++;
        }
        //while(i <= p.length()){
        if(!qm.isEmpty()){
            System.out.println(qm);
            int k = 0;
            ArrayList<Integer[]> li = new ArrayList<>();
            while(k < qm.size()){
                if(k == 0 && k == qm.size() - 1){
                    System.out.println("ahsgdg");
                    String p1 = p.substring(0, qm.get(k));
                    String p2 = p.substring(qm.get(k)+1);
                    System.out.println(p2);
                    li = question_mark(p1, p2, s, root, orig, null);
                    System.out.println(li);
                }
                else if(k == 0){
                    System.out.println("ahah");
                    String p1 = p.substring(0, qm.get(k));
                    String p2 = p.substring(qm.get(k)+1, qm.get(k+1));
                    System.out.println(p2);
                    li = question_mark(p1, p2, s, root, orig, null);
                }
                else if(k == qm.size() - 1){
                    System.out.println("plpl");
                    String p1 = p.substring(0, qm.get(k));
                    String p2 = p.substring(qm.get(k)+1);
                    System.out.println(p2 + "dd");
                    li = question_mark(p1, p2, s, root, orig, li);
                }
                else{
                    String p1 = p.substring(0, qm.get(k));
                    String p2 = p.substring(qm.get(k)+1, qm.get(k+1));
                    li = question_mark(p1, p2, s, root, orig, li);
                }
                k++;
            }
            return li;
        }
        else{
            return normal_search(p, s, root, orig);
        }
        //}
                //System.out.println(root.arl.get(j).end_ind - root.arl.get(j).start_ind);
                
//                    if(i == p.length()){
//                        check = true;
//                        break;
//                    }
//                    else if(i > (root.arl.get(j).end_ind - root.arl.get(j).start_ind)){
//                        //System.out.println("azxs");
//                        search(p.substring(i), s, root.arl.get(j).child, orig);
//                        break;
//                    }
//                    else if(p.charAt(i) == s.charAt(root.arl.get(j).start_ind + i)){
//                        //System.out.println("xcv");
//                        i++;
//                    }
//                    else if(p.charAt(i) != s.charAt(root.arl.get(j).start_ind + i)){
//                        break;
//                    }
//                }
//                if(check == true){ // pattern matched
//                    Collections.sort(root.arl.get(j).child.index);
//                    //System.out.println(orig);
//                    ArrayList<Integer[]> list = new ArrayList<>();
//                    Iterator<Integer> it = root.arl.get(j).child.index.iterator();
//                    while(it.hasNext()){
//                        int z = it.next();
//                        Integer[] in = new Integer[2];
//                        in[0] = z;
//                        in[1] = z + orig.length() - 1;
//                        list.add(in);
//                    }
//                    return list;
//                }
//                break;
    //    }
        //return null;
    }
    
    public ArrayList<Integer[]> question_mark(String p1, String p2, String s, Node root, String orig, ArrayList<Integer[]> list4){
        int i = 0;
        if(list4 == null){
            ArrayList<Integer[]> list1 = new ArrayList<>();
            ArrayList<Integer[]> list2 = new ArrayList<>();
            ArrayList<Integer[]> list3 = new ArrayList<>();
            //System.out.println(list1.size() + " rr");
            //System.out.println(list2.get(1)[1] + " kk");
            if(p1.length() == 0){
                int u = 0;
                while(u < s.length()){
                    Integer[] in = new Integer[2];
                    in[0] = u;
                    in[1] = u;
                    list3.add(in);
                    u++;
                }
                return list3;
            }
            list1 = normal_search(p1, s, root, p1);
            list2 = normal_search(p2, s, root, p2);
            while(i < list1.size()){
                int j = 0;
                System.out.println(list1.get(i)[0] + " oioi " + list1.get(i)[1]);
                while(j < list2.size()){
                    System.out.println(list2.get(j)[0] + " yjyj " + list2.get(j)[1]);
                    if(list2.get(j)[0] - list1.get(i)[1] == 2){
                        System.out.println(list1.get(i)[0] + "dd");
                        Integer[] in = new Integer[2];
                        in[0] = list1.get(i)[0];
                        in[1] = list2.get(j)[1];
                        list3.add(in);
                    }
                    j++;
                }
                i++;
            }
            return list3;
        }
        else{
            //System.out.println(p2);
            ArrayList<Integer[]> list1 = new ArrayList<>();
            ArrayList<Integer[]> list2 = new ArrayList<>();
            ArrayList<Integer[]> list3 = new ArrayList<>();
            if(p2.length() == 0){
                while(i < list4.size()){
                    list4.get(i)[1] = list4.get(i)[1] + 1;
                    i++;
                }
                return list4;
            }
            else{
                list2 = normal_search(p2, s, root, p2);
                while(i < list4.size()){
                int j = 0;
                    while(j < list2.size()){
                        if(list2.get(j)[0] - list4.get(i)[1] == 2){
                            Integer[] in = new Integer[2];
                            in[0] = list4.get(i)[0];
                            in[1] = list2.get(j)[1];
                            list3.add(in);
                        }
                        j++;
                    }
                    i++;
                }
                return list3;
            }
        }
    }
    public ArrayList<Integer[]> normal_search(String p, String s, Node root, String orig){
        int j = 0, i = 1, l = 0;
        boolean check = false;
        while(j < root.arl.size()){ // check if 1st char of suffix matches 1st char of any root-edge
            //System.out.println(p + " ");
            if(p.charAt(0) == s.charAt(root.arl.get(j).start_ind)){
                //System.out.println(root.arl.get(j).end_ind - root.arl.get(j).start_ind);
                while(i <= p.length()){
                    if(i == p.length()){
                        check = true;
                        break;
                    }
                    else if(i > (root.arl.get(j).end_ind - root.arl.get(j).start_ind)){
                        //System.out.println("azxs");
                        return normal_search(p.substring(i), s, root.arl.get(j).child, orig);
                    }
                    else if(p.charAt(i) == s.charAt(root.arl.get(j).start_ind + i)){
                        //System.out.println("xcv");
                        i++;
                    }
                    else if(p.charAt(i) != s.charAt(root.arl.get(j).start_ind + i)){
                        break;
                    }
                }
                if(check == true){ // pattern matched
                    Collections.sort(root.arl.get(j).child.index);
                    //System.out.println("dhfhdeh");
                    ArrayList<Integer[]> list = new ArrayList<>();
                    Iterator<Integer> it = root.arl.get(j).child.index.iterator();
                    while(it.hasNext()){
                        int z = it.next();
                        Integer[] in = new Integer[2];
                        in[0] = z;
                        //System.out.println(orig.length());
                        in[1] = z + orig.length() - 1;
                        list.add(in);
                    }
                    return list;
                }
                break;
            }
            j++;
        }
        return null;
    }
    
    public static void main(String[] args){
        checkfiles sf = new checkfiles();
        ArrayList<Integer[]> l = new ArrayList<>();
        sf.MakeTree("indian institute of technology india");
        l = sf.search("?n?i", "indian institute of technology india", sf.main_root, "?n?i");
        System.out.println(l);
        for(int w = 0; w < l.size(); w++){
                System.out.println(l.get(w)[0] + " " + l.get(w)[1]);
        }
        //System.out.println(sf.main_root.arl.get(1).child.index); //instu fecholgyda
    }
}