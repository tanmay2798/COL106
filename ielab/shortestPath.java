package ielab;

//Dijkstra's shortest path algorithm.  
import java.util.*; 
import java.lang.*; 
import java.io.*; 

class shortestPath { 
	// A utility function to find the vertex with minimum distance value, 
	// from the set of vertices not yet included in shortest path tree 
	static int V=0; 
	int minDistance(int dist[], Boolean sptSet[]) { 
		// Initialize min value 
		int min = Integer.MAX_VALUE, min_index=-1; 

		for (int v = 0; v < V; v++) { 
			if (sptSet[v] == false && dist[v] <= min) { 
				min = dist[v]; 
				min_index = v; 
			} 
		}

		return min_index; 
	} 

	void dijkstra(int graph[][], int src) { 
		int dist[] = new int[V]; // The output array. dist[i] will hold 

		Boolean sptSet[] = new Boolean[V]; 

		// Initialize all distances as INFINITE and stpSet[] as false 
		for (int i = 0; i < V; i++) { 
			dist[i] = Integer.MAX_VALUE; 
			sptSet[i] = false; 
		} 

		// Distance of source vertex from itself is always 0 
		dist[src] = 0; 

		// Find shortest path for all vertices 
		for (int count = 0; count < V-1; count++) { 
			// Pick the minimum distance vertex from the set of vertices 
			// not yet processed. u is always equal to src in first 
			// iteration. 
			int u = minDistance(dist, sptSet); 

			// Mark the picked vertex as processed 
			sptSet[u] = true; 

			// Update dist value of the adjacent vertices of the 
			// picked vertex. 
			for (int v = 0; v < V; v++) 

				// Update dist[v] only if is not in sptSet, there is an 
				// edge from u to v, and total weight of path from src to 
				// v through u is smaller than current value of dist[v] 
				if (!sptSet[v] && graph[u][v]!=0 && dist[u] != Integer.MAX_VALUE && dist[u]+graph[u][v] < dist[v]) 
					dist[v] = dist[u] + graph[u][v]; 
		} 

		// print the constructed distance array 
		System.out.println("Vertex   Distance from Source"); 
		for (int i = 0; i < V; i++) 
			System.out.println(i+"            "+dist[i]); 
	} 

	// Driver method 
	public static void main (String[] args) { 

		FileInputStream fstream;
		try {
			fstream = new FileInputStream("/Users/tanmaygoyal/Downloads/tantan.txt");
			Scanner scan = new Scanner(fstream);
			//while(scan.hasNextLine()) {
			V = Integer.parseInt(scan.nextLine());
			int src = Integer.parseInt(scan.nextLine());
			int graph[][] = new int[V][V];
			for(int i=0;i<V;i++) {
				for(int j=0;j<V;j++) {
					graph[i][j]=Integer.parseInt(scan.next());
				}
			}

			shortestPath t = new shortestPath(); 
			System.out.println("dijkstra method");
			System.out.println("source = "+src);
			Long t1 = (System.nanoTime());
			t.dijkstra(graph, src);
			System.out.println(System.nanoTime()-t1);
			System.out.println("\n");
			System.out.println("floyd warshall method");
			System.out.println("source = "+src);
			t1=(System.nanoTime());
			t.fw_fun(graph,src);
			System.out.println((System.nanoTime()-t1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void fw_fun(int input_matrix[][],int src) {
		V = input_matrix[0].length;
		int path[][] = new int[V][V]; 

		for (int i = 0; i < V; i++) 
			for (int j = 0; j < V; j++) 
				path[i][j] = input_matrix[i][j]; 

		for (int k = 0; k < V; k++) { 
			for (int i = 0; i < V; i++) { 
				for (int j = 0; j < V; j++) { 
					if ( path[i][j] > path[i][k] + path[k][j]) 
						path[i][j] = path[i][k] + path[k][j]; 
				} 
			} 
		} 
		System.out.println("Vertex   Distance from Source"); 
		for (int n=0; n<V; ++n) { 
			if (path[src][n]==100000) 
				System.out.println(n+" "+"inf "); 
			else {				
				System.out.println(n+"            "+path[src][n]+" "); 
			}
		} 
	}
	
}