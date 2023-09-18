package kwakmirae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197_최소스패닝트리 {

	static Node[] adjList;
	
	static class Node implements Comparable<Node>{
		int v, weight;
		Node next;

		public Node(int to, int weight, Node node) {
			this.v = to;
			this.weight = weight;
			this.next = node;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		adjList = new Node[V+1];
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, w, adjList[from]);
			adjList[to] = new Node(from, w, adjList[to]);
		}
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		boolean[] visited = new boolean[V+1];

		queue.add(new Node(1, 0, null));

		int total = 0;
		int cnt = 0;
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			if(visited[curr.v]) continue;
			
			visited[curr.v] = true;
			total += curr.weight;
			if(++cnt == V) break;
			
			for(Node temp = adjList[curr.v]; temp != null; temp = temp.next) {
				if(visited[temp.v]) continue;
				queue.add(temp);
			}
		}
		System.out.println(total);
	}
}