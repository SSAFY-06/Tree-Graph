package september.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** 네트워크 연결
 * 
 * 도현이는 컴퓨터와 컴퓨터를 모두 연결하는 네트워크를 구축하려고 한다,
 * 하지만 아쉽게도 허브가 없어 두 컴퓨터를 직접 연결해야한다. 잘공유를 위해 모든 컴퓨터를 연결해야한다.
 * a와 b가 연결되었다 = a에서 b로의 경로가 존재한다. 이상태에서 b와 c를 연결하는 선이 있으면 a와 c는 연결이 되어있다.
 * 즉 무방향 그래프이다.
 * 
 * 이왕이면 컴퓨터 연결 비용을 최소로 하려고 한다. 연결하는데 필요한 비용이 주어졌을때 모든 컴퓨터를 연결하는데
 * 필요한 최소비용을 출력해라.
 * 
 * 입력 : 첫줄에 컴퓨터 수 N
 * 		 둘쨋줄에 연결할수있는 선의 수 M
 * 		셋째줄부터 M+2번째줄까지 M개줄에 각 컴퓨터를 연결하는데 드는 비용 컴퓨터1,컴퓨터2,비용 (컴퓨터1과 컴퓨터2가 같을수있음)
 * 
 * 출력 : 모든 컴퓨터를 연결하는데 필요한 최소비용
 * 
 * 풀이 : 비용이있는 무방향그래프의 최소신장트리를 구하는것이므로 크루스칼 또는 프림 알고리즘을 사용..
 */
public class BOJ_1922 {
	static class Node implements Comparable<Node>{
		private int from;
		private int to;
		private int weight;
		
		public Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	static int[] parents;
	static void make(int N) {
		parents = new int[N+1];
		for(int i=1; i<N; i++) parents[i] = i;
	}
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a, int b) {
		if(find(a)==find(b)) return false;
		parents[find(a)] = find(b);
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		List<Node> list = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(to!=from) list.add(new Node(from,to,weight));
		}
		
		//오름차순정렬
		Collections.sort(list);
		//각 정점의 루트노드를 저장하는 parents배열 생성
		make(N);
		
		int cnt = 0, answer = 0;
		for(Node n : list) {
			if(union(n.from, n.to)) {
				answer+=n.weight;
				cnt++;
			}
			if(cnt==N-1) break;
		}
		
		System.out.println(answer);
	}
}
