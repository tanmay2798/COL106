package ielab;

import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
  
class floyd { 
	int V;
    static int inf = 100000;
  
    void fw_fun(int input_matrix[][]) 
    {
    	V = input_matrix[0].length;
        int path[][] = new int[V][V]; 

        for (int i = 0; i < V; i++) 
            for (int j = 0; j < V; j++) 
                path[i][j] = input_matrix[i][j]; 
  
        for (int k = 0; k < V; k++) { 
            for (int i = 0; i < V; i++) { 
                for (int j = 0; j < V; j++) { 
                    // If vertex k is on a shorter path then update path[i][j] 
                    if ( path[i][j] > path[i][k] + path[k][j]) 
                        path[i][j] = path[i][k] + path[k][j]; 
                } 
            } 
        } 
        for (int m=0; m<V; ++m) { 
            for (int n=0; n<V; ++n) { 
                if (path[m][n]==inf) 
                    System.out.print("inf "); 
                else
                    System.out.print(path[m][n]+" "); 
            } 
            System.out.println(); 
        }
        //System.out.println(path);
    } 
   
    public static void main (String[] args) 
    { 
    	floyd ff = new floyd(); 
    	
    	// INPUT
    	int input_matrix[][] = { {0, 4, inf, inf, inf, inf, inf, 8, inf}, 
                          {4, 0, 8, inf, inf, inf, inf, 11, inf}, 
                          {inf, 8, 0, 7, inf, 4, inf, inf, 2}, 
                          {inf, inf, 7, 0, 9, 14, inf, inf, inf},
                          {inf, inf, inf, 9, 0, 10, inf, inf, inf},
                          {inf, inf, 4, 14, 10, 0, 2, inf, inf},
                          {inf, inf, inf, inf, inf, 2, 0, 1, 6},
                          {8, 11, inf, inf, inf, inf, 1, 0, 7},
                          {inf, inf, 2, inf, inf, inf, 6, 7, 0},
                        }; 

        ff.fw_fun(input_matrix); 
    } 
} 

