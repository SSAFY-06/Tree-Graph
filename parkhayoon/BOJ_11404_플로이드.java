package algo.week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
문제
n(2 ≤ n ≤ 100)개의 도시 - 정점
한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스 - 간선
각 버스는 한 번 사용할 때 필요한 비용이 있음 - 비용
모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값
모든 점 -> 모든점으로 가는 최솟값: 플로이드-워셜

입력
첫째 줄: 도시의 개수 n
둘째 줄: 버스의 개수 m
m개 줄: 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c(1 ≤ a, b ≤ n, 1 ≤ c ≤ 100,000)
시작 도시와 도착 도시가 같은 경우는 없음
시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있음
 */
public class BOJ_11404_플로이드 {

    static int N, M;
    static int[][] arr;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i==j) arr[i][j] = 0;
                else arr[i][j] = INF;
            }
        }

        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a][b] = Math.min(arr[a][b], c);
        }

        // 플로이드-워셜 알고리즘 구현 부분
        // 중요! - 순서: 경유지 - 시작점 - 도착점
        // 시작점 - 도착점 - 경유지 순서로 가는 경우 길이 있음에도 아직 탐색하지 못해 INF인 지점으로 인식하고 통과할 수 있음!
        for(int k=1; k<=N; k++) { // 경유지
            for(int i=1; i<=N; i++) { // 시작점
                if(i==k) continue; // 같은 지점으로 가는 길 X
                for(int j=1; j<=N; j++) { // 도착점
                    if(i==j || k==j || arr[i][k] == INF || arr[k][j] == INF) continue; // 같은 지점으로 가는 길 X, 경유지로 오가는 길 없으면 통과
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        for(int i=1; i<=N; i++) {
            for (int j = 1; j <= N; j++) {
                if(arr[i][j]==INF) arr[i][j] = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++)
                sb.append(arr[i][j]).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);

    }

}
