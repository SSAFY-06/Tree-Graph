package algo.week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
문제
모든 컴퓨터를 연결하는 네트워크
컴퓨터 연결 시 최소 비용

입력
첫째 줄: 컴퓨터의 수 N (1 ≤ N ≤ 1000)
둘째 줄: 연결할 수 있는 선의 수 M (1 ≤ M ≤ 100,000)
M개의 줄: 세 개의 정수로 주어지는데, a b c = a 컴퓨터와 b 컴퓨터를 연결하는데 비용이 c (1 ≤ c ≤ 10,000)
a와 b는 같을 수도 있음 - 어차피 union 시 부모 확인하므로 추가 고려 X

아이디어
MST 구현
 */
public class BOJ_1922_네트워크연결 {

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

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        for(int i=1; i<=N; i++) // 초기화: 자기 자신의 부모 = 자신
            parent[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
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
        parent[parentY] = parentX;
        return true;
    }

    private static int find(int x) {
        if(parent[x]==x)
            return x;
        return find(parent[x]);
    }

}
