package september.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** 최소스패닝트리
 * 
 * 그래프가 주어졌을때 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하라
 * 최소 스패닝트리 = 주어진 그래프의 모든 정점을 연결하는 부분 중에서 그 가중치합이 최소인 트리이다.
 * 그래프의 정점은 1번부터 V번까지 번호가 매겨져있고 임의의 두 정점사이의 경로가 있다.
 * 
 * input : 첫줄에 정점갯수 V와 간선갯수 E가 주어진다.
 * 			다음 E개의 줄에 간선정보가 주어진다. 정점,정점,가중치가 주어지며 가중치는 음수도 나옴
 * output : 첫째줄에 최소스패닝트리의 가중치를 출력한다.
 * 
 * 풀이 : 최소신장 트리의 풀이법은 크루스칼(간선 오름차순 정렬후 사이클안되는 간선 V-1만큼 선택)이랑 프림(임의의 정점의 간선중 작은걸 택하며 풀어나감)
 * 난.. 크루스칼로 풀어보겠긔
 * 
 */
public class BOJ_1197 {
	static class Node implements Comparable<Node>{
		private int to;
		private int from;
		private int weight;
		
		Node(int to, int from, int weight){
			this.to = to;
			this.from = from;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	static int[] parents;
	static int V,E;
	//make, union, find 메소드 필요
	public static void make() {
		parents = new int[V+1];
		for(int i=1; i<V; i++) parents[i] = i;
	}
	
	public static boolean union(int a, int b) {
		if(find(a) == find(b)) return false;
		
		parents[find(a)] = parents[find(b)];
		return true;
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		List<Node> list = new ArrayList<>();
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.add(new Node(to,from,weight));
		}
		
		//크루스칼 : 간선을 오름차순 정렬한 뒤 사이클이 아닌, 즉 union의 결과가 true인 간선들을 V-1개 선택한다.
		Collections.sort(list);
		//부모 초기화
		make();
		
		int answer = 0;
		int cnt = 0;
		for(Node n : list) {
			if(union(n.from,n.to)) {
				answer += n.weight;
				cnt++;
			}
			if(cnt==V-1) break;
		}
		
		System.out.println(answer);
	}
}
