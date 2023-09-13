package kwakmirae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7562_나이트의이동 {
	
	static int N;
	static Knight start, end;
	static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};
	
	static class Knight {
		int r, c, depth;
		public Knight(int r, int c, int depth) {
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
	}
	
	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			start = new Knight(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			st = new StringTokenizer(br.readLine());
			end = new Knight(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			sb.append(bfs()).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int bfs() {
		Queue<Knight> queue = new ArrayDeque<Knight>();
		boolean[][] visited = new boolean[N][N];
		
		queue.offer(start);
		visited[start.r][start.c] = true;
		
		while(!queue.isEmpty()) {
			Knight curr = queue.poll();
			if(curr.r == end.r && curr.c == end.c) return curr.depth;
			
			for(int d=0; d<8; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				if(!isInRange(nr, nc) || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				queue.offer(new Knight(nr, nc, curr.depth+1));
			}
		}
		return -1;
	}
	
	public static boolean isInRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
