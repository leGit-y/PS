import java.io.*;
import java.util.*;
class Node implements Comparable<Node>{
	int node;
	int cost;
	Node(int node,int cost){
		this.node=node;
		this.cost=cost;
	}
	@Override
	public int compareTo(Node o) {
		if(this.cost>o.cost)
			return 1;
		return -1;
	}
}

public class Main {

	static StringBuilder sb=new StringBuilder();

	static ArrayList<Node> graph[];
	static int INF=1000000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		graph=new ArrayList[n+1];
		for(int i=1;i<graph.length;i++)
			graph[i]=new ArrayList<>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int node1=Integer.parseInt(st.nextToken());
			int node2=Integer.parseInt(st.nextToken());
			int cost=Integer.parseInt(st.nextToken());
			graph[node1].add(new Node(node2,cost));
			graph[node2].add(new Node(node1,cost));
		}
		st=new StringTokenizer(br.readLine());
		int s=Integer.parseInt(st.nextToken());
		int t=Integer.parseInt(st.nextToken());
		
		System.out.println(dijkstra(s,t));
		
		
	}
	public static int dijkstra(int start,int end) {
		PriorityQueue<Node> pq=new PriorityQueue<>();
		pq.add(new Node(start,0));
		boolean visited[]=new boolean[graph.length];
		int dist[]=new int[graph.length];
		Arrays.fill(dist,INF);
		dist[start]=0;
		while(!pq.isEmpty()) {
			
			int nowVertex=pq.poll().node;
			if(visited[nowVertex]) continue;
			visited[nowVertex]=true;
		
			for(Node next:graph[nowVertex]) {
				if(dist[next.node]>dist[nowVertex]+next.cost) {
					dist[next.node]=dist[nowVertex]+next.cost;
					pq.add(new Node(next.node,dist[next.node]));
				}
				
			}
			
			
			
		}
		
		return dist[end];
	}
}