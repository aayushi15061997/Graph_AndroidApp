package in.project.com.graph;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	
	private int vertices;
	private ArrayList<LinkedList<Integer>> adj;
	private boolean[] visited;
	private boolean[] artPoints;
	private ArrayList<Pair> bridges;
	private final int NIL = -1;
	private int time = 0;
	
	public Graph(int vertices) {
		this.vertices = vertices;
		adj = new ArrayList<>();
		for (int i = 0; i <= vertices; i++)
			adj.add(new LinkedList<Integer>());
	}
	
	public void addEdge(int first, int second) {
		LinkedList<Integer> temp = adj.get(first);
		temp.add(second);
		adj.set(first, temp);
		temp = adj.get(second);
		temp.add(first);
		adj.set(second, temp);
	}
	
	public void BFS(int vertex) {
		visited = new boolean[vertices + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(vertex);
		visited[vertex] = true;
		while (!q.isEmpty()) {
			vertex = q.poll();
			Iterator<Integer> it = adj.get(vertex).iterator();
			while (it.hasNext()) {
				int n = it.next();
				if (!visited[n]) {
					q.add(n);
					visited[n] = true;
				}
			}
		}
	}
	
	public boolean isConnected() {
		for (int i = 1; i <= vertices; i++)
			if (!visited[i])
				return false;
		return true;
	}
	
	public int articulationPoints() {
		visited = new boolean[vertices + 1];
		artPoints = new boolean[vertices + 1];
		int[] parent = new int[vertices + 1];
		int[] discTime = new int[vertices + 1];
		int[] low = new int[vertices + 1];
		time = 0;
		
		Arrays.fill(parent, NIL);
		
		for (int i = 1; i <= vertices; i++)
			if (!visited[i])
				articulationPoints(i, discTime, low, parent);

        int numOfArticulationPoints = 0;
        for (int i = 1; i <= vertices; i++)
            if (artPoints[i])
                numOfArticulationPoints++;
        return numOfArticulationPoints;
	}

	private void articulationPoints(int u, int[] discTime, int[] low, int[] parent) {
		int children = 0;
		visited[u] = true;
		discTime[u] = low[u] = ++time;
		Iterator<Integer> it = adj.get(u).iterator();
		while (it.hasNext()) {
			int v = it.next();
			if (!visited[v]) {
				children++;
				parent[v] = u;
				articulationPoints(v, discTime, low, parent);
				low[u] = Math.min(low[u], low[v]);
				if (parent[u] == NIL && children > 1)
					artPoints[u] = true;
				if (parent[u] != NIL && low[v] >= discTime[u])
					artPoints[u] = true;
			}
			else if (v != parent[u])
				low[u] = Math.min(low[u], discTime[v]);
		}
	}
	
	public int bridges() {
        visited = new boolean[vertices + 1];
        bridges = new ArrayList<>();
        int discTime[] = new int[vertices + 1];
        int low[] = new int[vertices + 1];
        int parent[] = new int[vertices + 1];
        time = 0;
 
        Arrays.fill(parent, NIL);

        for (int i = 1; i <= vertices; i++)
            if (!visited[i])
                bridges(i, discTime, low, parent);

        return bridges.size();
    }

	private void bridges(int u, int[] discTime, int[] low, int[] parent) {
        visited[u] = true;
        discTime[u] = low[u] = ++time;
        Iterator<Integer> it = adj.get(u).iterator();
        while (it.hasNext()) {
            int v = it.next();
            if (!visited[v]) {
                parent[v] = u;
                bridges(v, discTime, low, parent);
                low[u]  = Math.min(low[u], low[v]);
                if (low[v] > discTime[u])
                	bridges.add(new Pair(u, v));
            }
            else if (v != parent[u])
                low[u]  = Math.min(low[u], discTime[v]);
        }
	}
}