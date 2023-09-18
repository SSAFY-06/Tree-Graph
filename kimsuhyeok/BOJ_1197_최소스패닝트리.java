package kimsuhyeok;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 기본적인 최소 스패닝 트리 문제
 * 문제를 푸는 방법에는 크루스칼 알고리즘과 프림 알고리즘이 있다.
 * 나는 크루스칼 알고리즘을 이용하여 문제를 해결하였다.
 */

public class BOJ_1197_최소스패닝트리 {

    static int V,E; // V: 정점, E: 간선
    static int[] parents;
    static Edge[] edgelist;

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int weight;
        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgelist = new Edge[E];

        // 간선의 개수만큼 입력받기
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgelist[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edgelist);
        make();

        long result = 0;
        int cnt = 0;
        for(Edge edge: edgelist){
            if(union(edge.from, edge.to)){
                result += edge.weight;
                if(++cnt==V-1) break;
            }
        }
        System.out.println(result);
    }

    private static void make(){
        parents = new int[V+1];
        for(int i=1;i<=V;i++){
            parents[i]=i;
        }
    }

    private static int find(int x){
        if(x==parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    private static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

}
