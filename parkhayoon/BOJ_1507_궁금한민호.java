package algo.week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
문제
N개 도시, M개 도로, 도로 지나는 시간(cost) 있음, 이동할 수 없는 경우 X
모든 쌍의 도시에 대해서 최소이동시간 주어짐
원래 있던 도로의 개수는?

입력
도시 개수 N(1 ≤ N ≤ 20)
N개의 줄: 각각의 도시 사이에 이동하는데 필요한 시간
A에서 B로 가는 시간과 B에서 A로 가는 시간은 동일(방향성 X)
A와 B가 같은 경우에는 0
필요한 시간은 2500보다 작거나 같은 자연수

출력
도로 개수가 최소일 때, 모든 도로의 시간의 합
불가능한 경우에는 -1을 출력

아이디어
모든 도시 연결, 모든 쌍의 도시에 대해서 최소이동시간 -> 플로이드 워셜 결과
플로이드 워셜 배열에서 역으로 원래 도로 찾기
결과 배열에서 한번 더 알고리즘 돌리고 값 변화 - 무한 감소 -> -1 출력
 */
public class BOJ_1507_궁금한민호 {

    static int N;
    static int[][] arr; // 원소거리 저장할 값
    static int[][] fwResult; // 입력받은 결과값

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        fwResult = new int[N][N];
        StringTokenizer st = null;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                fwResult[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = fwResult[i][j];
            }
        }

        int sum = floydWarshall();
        System.out.println(sum);

    }

    private static int floydWarshall() {
        int sum = 0;

        for(int k=0; k<N; k++) { // 경유지
            for(int i=0; i<N; i++) { // 시작점
                if(i==k) continue; // 같은 지점으로 가는 길 X
                for(int j=0; j<N; j++) { // 도착점
                    if(i==j || k==j) continue; // 같은 지점으로 가는 길 X
                    if(fwResult[i][j] == fwResult[i][k] + fwResult[k][j]) // i->j는 i->k->j로 가는 경로가 최소임
                        arr[i][j] = 0; // 해당 경로는 직접 이어진 경로가 아님 - 지우기
                    else if(fwResult[i][j] > fwResult[i][k] + fwResult[k][j]) // 최솟값 갱신됨 - 무한 감소하므로 -1 출력
                        return -1;
                }
            }
        }

        for(int i=0; i<N; i++) { // 원소 거리 합
            for(int j=0; j<=i; j++) // 배열의 절반만 더하기
                sum+=arr[i][j];
        }

        return sum;
    }

}
