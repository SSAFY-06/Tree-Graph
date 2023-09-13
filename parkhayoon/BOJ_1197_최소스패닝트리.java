package algo.week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제
 * 그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리 구하기
 * MST: 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리
 *
 * 입력
 * 첫째 줄: 정점의 개수 V(1 ≤ V ≤ 10,000), 간선의 개수 E(1 ≤ E ≤ 100,000)
 * E개 줄: 각 간선에 대한 정보를 나타내는 세 정수 A, B, C
 * A번 정점과 B번 정점이 가중치 C인 간선으로 연결, C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않음
 * 그래프의 정점은 1번부터 V번까지 번호, 임의의 두 정점 사이에 경로가 있음
 * 최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어짐
 *
 * 아이디어
 * MST 구현
 */
public class BOJ_1197_최소스패닝트리 {

    static class Edge implements Comparable<Edge> {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            super();
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost>o.cost?1:-1; // cost 순으로 정렬
        }

    }

    static int V, E;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        for(int i=1; i<=V; i++) // 초기화: 자기 자신의 부모 = 자신
            parent[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Edge(start, end, cost));
        }

        int sum = 0;
        while(!pq.isEmpty()) {
            Edge current = pq.poll(); // 최저 cost 간선부터 오름차순 확인
            if(!union(current.start, current.end)) continue; // 만약 두 점이 이미 연결되어있는 경우 - 통과
            sum += current.cost; // 해당 간선 선택 - cost 추가
        }
        System.out.println(sum);

    }

    private static boolean union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if(parentX==parentY) return false; // 만약 이미 두 점이 한 tree 안에 존재하는 경우 - 통과
        parent[parentY] = parentX; // 주의!: parent[y]가 아니라 parent[parentY]
        return true;
    }

    private static int find(int x) {
        if(parent[x]==x)
            return x;
        return find(parent[x]);
    }

}