package algo.week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제
 * 나이트가 이동하려고 하는 칸이 주어질 때, 나이트 이동 횟수
 *
 * 입력
 * 테스트케이스 수 T
 * 첫째 줄: 한 변의 길이 l(4 ≤ l ≤ 300, 체스판 크기: lxl)
 * 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}
 * 둘째 줄: 나이트가 현재 있는 칸
 * 셋째 줄: 나이트가 이동하려고 하는 칸
 *
 * 아이디어
 * (x,y)에서 나이트 이동 방향
 * (x-1,y-2),(x-2,y-1),(x+1,y-2),(x+2,y-1),(x-1,y+2),(x-2,y+1),(x+1,y+2),(x+2,y+1)
 * -> 나이트 8방 탐색, BFS
 */
public class BOJ_7562_나이트의이동 {

    static int L; // 체스 판의 한
    static int[] knightPos, destPos; // 나이트 현재 위치, 나이트 목적 위치
    static int[][] depth; // 나이트가 이동하는데 소요된 이동 횟수
    static int[][] deltas = {{-1,-2},{-2,-1},{1,-2},{2,-1},{-1,2},{-2,1},{1,2},{2,1}}; // 나이트 이동 방향

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {
            //////////

            L = Integer.parseInt(br.readLine());
            depth = new int[L][L];
            knightPos = new int[2];
            destPos = new int[2];

            StringTokenizer st = new StringTokenizer(br.readLine());
            knightPos[0] = Integer.parseInt(st.nextToken());
            knightPos[1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            destPos[0] = Integer.parseInt(st.nextToken());
            destPos[1] = Integer.parseInt(st.nextToken());

            if(knightPos[0]==destPos[0] && knightPos[1]==destPos[1]) // 시작 위치와 목적 위치가 동일하면 바로 종료
                System.out.println(0);
            else
                bfs(knightPos); // 목표까지 가는 횟수(깊이) 구하기 - BFS 탐색

            //////////
        }

    }

    private static void bfs(int[] pos) {
        Queue<int[]> q = new ArrayDeque<int[]>();
        q.add(pos);
        ALL: while(!q.isEmpty()) {
            int[] current = q.poll(); // 현재 위치에서 나이트 이동 방향 조회
            int x = current[0];
            int y = current[1];
            for(int d=0; d<8; d++) { // 나이트 이동 가능한 8방 탐색
                int dx = x+deltas[d][0];
                int dy = y+deltas[d][1];
                if(dx<0 || dx>=L || dy<0 || dy>=L || depth[dx][dy]!=0) continue; // 범위 밖이거나 이미 방문한 점이라면 넘어감
                q.offer(new int[] {dx,dy}); // 새로 방문한 점 큐에 추가
                depth[dx][dy] = depth[x][y]+1; // 이동 횟수 1 추가
                if(dx==destPos[0] && dy==destPos[1]) { // 목적지 도착
                    System.out.println(depth[dx][dy]); // 이동 횟수 출력
                    break ALL; // 결과 나왔으므로 종료
                }
            }
        }
    }

}
