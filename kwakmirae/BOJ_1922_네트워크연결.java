package kwakmirae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1922_네트워크연결 {
	
	static int[] parent;
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void makeDisjointSet() {
		for(int i=1; i<parent.length; i++) {
			parent[i] = i;
		}
	}
	
	public static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	
	public static boolean union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		if(aroot == broot) return false;
		
		parent[broot] = aroot;
		return true;
	}
	
	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		Edge[] edgeList = new Edge[M];
		parent = new int[N+1];
		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			edgeList[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(edgeList);
		makeDisjointSet();
		
		int cnt = 0;
		int price = 0;
		for(Edge edge: edgeList) {
			if(union(edge.from, edge.to)) {
				cnt++;
				price += edge.weight;
			}
			if(cnt == N-1) break;
		}
		
		System.out.println(price);
	}
}
