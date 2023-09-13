package kwakmirae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404_플로이드 {

	public static void main(String[] args) throws Exception, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] distance = new int[N][N];
		int INF = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			Arrays.fill(distance[i], INF);
		}
		for(int i=0; i<N; i++) {
			distance[i][i] = 0;
		}
		
		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			distance[from][to] = Math.min(distance[from][to], w);
		}
		
		for(int i=0; i<N; i++) {
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(distance[r][i] == INF || distance[i][c] == INF) continue;
					distance[r][c] = Math.min(distance[r][c], distance[r][i]+distance[i][c]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(distance[i][j] == INF ? 0 : distance[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
