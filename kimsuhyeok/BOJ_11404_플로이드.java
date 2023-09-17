package kimsuhyeok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404_플로이드 {

    static int N,M;
    static int[][] graph;
    static final int INF = 1000000000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N+1][N+1];

        //최댓값으로 채워주고
        for(int i=1;i<=N;i++){
            Arrays.fill(graph[i], INF);
        }

        // 시작점과 도착점이 같은 경우 제외
        for(int i=1;i<=N;i++){
            graph[i][i] = 0;
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            //해당 위치를 들어온 weight와 비교해서 더 적은값 넣어주기
            graph[a][b] = Math.min(graph[a][b], c);
        }

        //최단 경로 갱신 함수 실행
        Floyd();

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(graph[i][j]==INF){
                    sb.append("0");
                }else{
                    sb.append(graph[i][j]);
                }
                sb.append(" ");
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    private static void Floyd(){
        for(int k=1;k<=N;k++){  //거쳐가는 중간 노드
            for(int i=1;i<=N;i++){  //시작 노드
                for(int j=1;j<=N;j++){  //도착 노드
                    //최단경로 갱신
                    graph[i][j] = Math.min(graph[i][k]+graph[k][j], graph[i][j]);
                }
            }
        }
    }
}
