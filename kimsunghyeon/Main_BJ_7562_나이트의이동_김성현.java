package 알고리즘스터디.Week2_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 풀이 프로세스
 * 1. 나이트가 이동할 수 있는 방향 델타 8개 생성
 * 2. 나이트의 현재 위치 정보를 담은 나이트 클래스 생성
 * 3. 시작 위치에서 레벨별 bfs 돌리기 level 이 정답값이 된다.
 *
 * 나이트의 이동은 어떻게 처리할까?
 * bfs에서 while문으로 델타 배열 돌리면서 처리하면 될듯
 * 도착 위치와 같아지면 종료하고 정답 출력
 */

public class Main_BJ_7562_나이트의이동_김성현 {
    // 이동할 수 있는 좌표 8개 델타 배열
    static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};

    static int L; // 체스판의 크기

    static class Knight {
        int r, c; // 현재 나이트의 위치 r, c
        public Knight (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static Knight knight; // 현재 나이트
    static int arriveR, arriveC; // 도착 행, 도착 열

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            L = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine()); // 시작 위치 설정
            int startR = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken());
            knight = new Knight(startR, startC);

            st = new StringTokenizer(br.readLine()); // 도착 위치 저장
            arriveR = Integer.parseInt(st.nextToken());
            arriveC = Integer.parseInt(st.nextToken());

            int result = getKnightMoves(); // 나이트의 이동 횟수 구하는 함수 호출
            sb.append(result).append('\n');
        }
        System.out.println(sb); // 출력
    }

    // bfs (반환값 int)
    private static int getKnightMoves() {
        Queue<Knight> que = new ArrayDeque<>(); // 큐 생성
        boolean[][] visited = new boolean[L][L]; // 방문 체크
        int level = 1; // 레벨 - 이동횟수

        que.add(knight);
        visited[knight.r][knight.c] = true; // 시작 점 방문체크
        if (knight.r == arriveR && knight.c == arriveC) return 0; // 시작 위치와 도착 위치가 같은 경우 이동횟수 0 return

        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                Knight k = que.poll();
                for (int d = 0; d < 8; d++) {
                    int nr = k.r + dr[d];
                    int nc = k.c + dc[d];
                    if (isInRange(nr, nc) && !visited[nr][nc]) { // 이동할 칸이 체스판 안에 있고, 방문한 적이 없다면
                        // 나이트의 위치 이동
                        Knight n = new Knight(nr, nc);
                        que.add(n); // 큐에 추가
                        visited[nr][nc] = true; // 이동한 위치 방문 처리

                        if (n.r == arriveR && n.c == arriveC) return level;
                    }
                }
            }
            level++;
        }
        return -1; // 나이트의 위치에 도달할 수 없는 경우. 하지만 어차피 여기까지 안옴
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < L && c >= 0 && c < L;
    }
}

/**
 * 객체 말고 배열 쓸걸 그랬나..?
 */