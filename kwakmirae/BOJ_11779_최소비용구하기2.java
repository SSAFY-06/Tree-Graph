package kwakmirae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11779_최소비용구하기2 {

	static class Node implements Comparable<Node>{
		int vertex, weight;
		Node next;
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		Node[] adjList = new Node[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] distance = new int[N+1];
		int[] selected = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		selected[start] = 0;
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(new Node(start, 0, null));
		while(true) {
			// 1. 최소 비용 경로를 가지는 vertex 찾기
			Node curr = queue.poll();
			
			// 2. 방문 처리하기
			if(distance[curr.vertex] < curr.weight) continue;
			if(curr.vertex == end) break;
			
			// 3. 해당 vertex와 연결된 edge를 보면서 최소 비용 갱신하기
			for(Node temp=adjList[curr.vertex]; temp != null; temp = temp.next) {
				if(distance[temp.vertex] > distance[curr.vertex]+temp.weight) {
					distance[temp.vertex] = distance[curr.vertex]+temp.weight;
					selected[temp.vertex] = curr.vertex;
					queue.add(new Node(temp.vertex, distance[temp.vertex], null));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int idx = end, cnt = 0;
		while(idx != 0) {
			sb.insert(0, idx+" ");
			idx = selected[idx];
			cnt++;
		}
		System.out.println(distance[end]);
		System.out.println(cnt);
		System.out.println(sb);
	}
}
