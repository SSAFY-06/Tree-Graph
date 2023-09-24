import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 이미 최단거리임
 * 그렇다면 우리는 플로이드 워셜 알고리즘을 수행한 배열을 이용해서 플로이드 워셜 알고리즘 수행 전 배열을 찾아야 함
 * 
 */
public class Main {
    
    static int N;
    static int[][] map;
    static int[][] copy;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        copy = new int[N][N];
        
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = map[i][j];
            }
        }
        
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                for(int k=0;k<N;k++){
                    if(i==j||i==k||j==k)continue;
                    
                    if(map[j][k] == map[j][i]+map[i][k]){
                        copy[j][k]=0;
                    }
                    
                    //말이 안되는 경우
                    if(map[j][k] > map[j][i] + map[i][k]){
                        System.out.println(-1);
                        System.exit(0);
                    }
                }
            }
        }
        
        int answer = 0;
        
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                answer += copy[i][j];
            }
        }

        System.out.println(answer/2);
        
    }
    
}
