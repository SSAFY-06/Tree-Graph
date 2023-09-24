package kwakmirae;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1507_궁금한민호 {

	private static int N, distance[][], result[][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		distance = new int[N][N];
		result = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				distance[i][j] = result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				if(k==i) continue;
				for(int j=0; j<N; j++) {
					if(j==k || i==j) continue;
					if(distance[i][j] == distance[i][k] + distance[k][j]) {
						result[i][j] = 0;
					}
					if(distance[i][j] > distance[i][k] + distance[k][j]) {
						System.out.println(-1);
						return;						
					}
				}
			}
		}
		
		int sum = 0;		
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				sum += result[i][j];
			}
		}		
		System.out.println(sum);
	}
}
