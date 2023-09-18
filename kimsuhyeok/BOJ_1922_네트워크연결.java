package kimsuhyeok;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 컴퓨터와 컴퓨터를 직접 연결
 * a에서 b를 연결하는 선이 있고, b에서 c를 연결하는 선이 있으면 => a와 c는 연결되어 있음.
 *
 * 컴퓨터 연결 비용을 최소로 하자
 *
 * 해당 그래프는 무방향, weight가 있는 그래프이다.
 */
public class BOJ_1922_네트워크연결 {

    static int N,M; // N: 컴퓨터의 수, M: 연결할 수 있는 선의 수
    static class Node implements Comparable<Node>{
        int from;
        int to;
        int weight;
        public Node(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static List<Node> nodelist;
    static int[] parents;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        nodelist = new ArrayList<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(from != to) nodelist.add(new Node(from, to, weight));
        }

        Collections.sort(nodelist);
        make();

//        for(int i=0;i<nodelist.size();i++){
//            System.out.println(nodelist.get(i).from+ " " + nodelist.get(i).to+" "+nodelist.get(i).weight);
//            System.out.println();
//        }

        int answer = 0;
        int cnt = 0;
        for(Node node: nodelist){
            if(union(node.from, node.to)){
                answer += node.weight;
                if(++cnt == N-1) break;
            }
        }
        System.out.println(answer);
    }

    private static void make(){
        parents = new int[N+1];
        for(int i=1;i<=N;i++){
            parents[i] = i;
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
