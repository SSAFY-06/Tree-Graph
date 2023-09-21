package kwakmirae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10868_최소값 {

	static int N, M, LEAF;
	static long tree[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int height = (int) Math.ceil(Math.log(N)/Math.log(2)) +1;
		int size = (int) Math.pow(2, height);
		
		tree = new long[size];
		LEAF = (int)Math.pow(2, height-1);
		for(int i=LEAF; i<LEAF+N; i++) {
			tree[i] = Long.parseLong(br.readLine());
		}
		init(1);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(search(1, 1, LEAF, a, b)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static long search(int node, int start, int end, int left, int right) {
		if(end < left || start > right) return Long.MAX_VALUE;
		
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end)/2;
		return Math.min(search(node*2, start, mid, left, right), search(node*2+1, mid+1, end, left, right));
	}

	public static long init(int idx) {
		if(idx >= LEAF) {
			return tree[idx];
		}
		
		return tree[idx] = Math.min(init(idx*2), init(idx*2+1));
	}
	
	
}
