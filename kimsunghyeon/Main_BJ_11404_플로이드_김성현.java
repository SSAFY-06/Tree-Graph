package 알고리즘스터디.Week2_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_11404_플로이드_김성현 {
    static final int INF = 987_654_321; // 무한을 의미하는 값
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 노드 수
        int m = Integer.parseInt(br.readLine()); // 간선의 수

        // 2차원 리스트 생성
        int[][] graph = new int[n + 1][n + 1];

        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                if (i == j) graph[i][j] = 0; // 자기 자신에서 자신으로 가는 비용은 0으로 초기화
                else graph[i][j] = INF; // 아니라면 무한으로 초기화
            }
        }

        // 각 간선에 대한 정보 입력 받아서 그 값으로 초기화
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작 도시
            int b = Integer.parseInt(st.nextToken()); // 도착 도시
            int c = Integer.parseInt(st.nextToken()); // a -> b 버스비
            graph[a][b] = Math.min(graph[a][b], c); // (1,4,1) , (1,4,2)인 경우 최솟값을 골라야하므로
        }

        // 점화식에 따라 플로이드 워셜 알고리즘 수행
        for (int k = 1; k < n + 1; k++) {
            for (int a = 1; a < n + 1; a++) {
                for (int b = 1; b < n + 1; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        // 수행 결과 출력
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                sb.append(graph[i][j] != INF ? graph[i][j] : 0).append(' '); // 갈 수 있는 방법이 없는 경우 0으로 출력
            }
            sb.append('\n');
        }

        System.out.println(sb);




    }
}
