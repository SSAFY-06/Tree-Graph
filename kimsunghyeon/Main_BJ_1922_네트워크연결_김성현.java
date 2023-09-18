package 알고리즘스터디.Week2_Graph;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * 모든 컴퓨터를 연결하는 데에 필요한 최소 비용 구하기
 *
 * 입력값
 * 첫줄: 컴퓨터의 수 N
 * 둘째줄: 간선의 수 M
 * 셋째줄 ~ M개의 줄 : a컴퓨터와 b컴퓨터를 연결하는데 드는 비용 c
 * a, b 는 같을 수도 있다.
 * 문제 해결 프로세스
 * 1. 최소 스패닝 트리
 * 2. from to weight
 * weight 작은 순서대로 정렬
 */
public class Main_BJ_1922_네트워크연결_김성현 {
    static int N, M; // 정점의 수, 간선의 수
    static int[] roots; // 대표 원소 배열 (초기상태 : [1, 2, 3, 4.., N])
    static Edge[] edgeList; // 간선 리스트

    // 시작 정점, 도착 정점, 가중치 정보를 저장해줄 Edge 클래스
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge (int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override // 가중치를 기준으로 compareTo를 재정의
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 정점의 수
        M = Integer.parseInt(br.readLine()); // 간선의 수

        roots = new int[N+1]; // 대표 노드 배열 초기화
        for (int i = 1; i <= N; i++) {
            roots[i] = i;
        }

        edgeList = new Edge[M]; // 간선리스트 생성

        for (int i = 0; i < M; i++) { // 간선리스트 초기화
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(a, b, c);
        }

        int minCost = 0; // 최소 비용 (정답값)

        Arrays.sort(edgeList); // 가중치가 작은 순으로 간선리스트 정렬

        for (Edge edge : edgeList) {
            if(union(edge.from, edge.to)) { // 간선이 연결되었다면
                minCost += edge.weight; // 가중치만큼 비용에 추가
            }
        }
        System.out.println(minCost); // 정답 출력
    }

    // 유니온 - 파인드
    // 유니온
    private static boolean union(int a, int b) {
        int aRoot = find(a); // a의 루트를 찾고
        int bRoot = find(b); // b의 루트를 찾아서
        if (aRoot == bRoot) return false; // 비교해서 같으면 union 할 필요 x => false 반환
        // a와 b의 루트가 다르다면, 서로 연결되어있지 않은 것 -> 한쪽의 루트를 다른 한쪽의 루트와 같게 해주어 연결한다.
        roots[bRoot] = aRoot; // 순서가 바뀌어도 상관없음
        return true; // 연결 o => true 반환
    }
    // 파인드
    private static int find (int n) {
        if (roots[n] == n) return n; // 현재 정점의 번호가 root라면 현재 정점 반환
        return roots[n] = find(roots[n]); // 아니라면 root를 찾고, 루트 배열의 현재 정점 idx를 업데이트 (필수 x => 최적화를 위해)
    }
}
