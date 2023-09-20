package kwakmirae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11437_LCA {

	private static List<Integer>[] list;
	private static int[] parent;
	private static int[] depth;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		StringTokenizer st;
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			list[v1].add(v2);
			list[v2].add(v1);
		}
		
		parent = new int[N+1];
		depth = new int[N+1];
		init(1, 1, 0);
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			sb.append(lca(v1, v2)).append("\n");
		}
		System.out.println(sb);
	}

	private static void init(int v, int d, int pa) {
		depth[v] = d;
		parent[v] = pa;
		
		for(int child: list[v]) {
			if(child == pa) continue;
			init(child, d+1, v);
		}
	}
	
	private static int lca(int a, int b) {
		int ah = depth[a];
		int bh = depth[b];
		
		while(ah > bh) {
			a = parent[a];
			ah--;
		}
		while(bh > ah) {
			b = parent[b];
			bh--;
		}
		while(a!=b) {
			a = parent[a];
			b = parent[b];
		}
		return a;
	}
}