package DisjointSETproblems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
class GraphNode implements Comparable<GraphNode>{
	public String name;
	public int index;
	public int distance;
	public boolean isVisited=false;
	public DisjointSet set;
	public ArrayList<GraphNode> neighbours=new ArrayList<>();
	public HashMap<GraphNode,Integer> weightMap=new HashMap<GraphNode,Integer>();
	public GraphNode(String name,int index) {
		this.name=name;
		this.index=index;
		this.distance=Integer.MAX_VALUE;
	}
	@Override
	public int compareTo(GraphNode o) {
		return this.distance-o.distance;
	}
}
class DisjointSet{
	public ArrayList<GraphNode> nodeList=new ArrayList<>();
	
	public void makeset(ArrayList<GraphNode> nodeList) {
		 for(GraphNode node:nodeList) {
			 DisjointSet set=new DisjointSet();
			 set.nodeList.add(node);
			 node.set=set;
		 }
	}
	public DisjointSet findSet(GraphNode node) {
		return node.set;
	}
	public DisjointSet union(GraphNode node1,GraphNode node2) {
		if(node1.set.equals(node2.set)) {
			return null;
		}
		DisjointSet set1=node1.set;
		DisjointSet set2=node2.set;
		if(set1.nodeList.size()>set2.nodeList.size()) {
			ArrayList<GraphNode> nodeset=set1.nodeList;
			for(GraphNode node:nodeset) {
				node.set=set1;
				set1.nodeList.add(node);
			}
			return set1;
		}else {
			ArrayList<GraphNode> nodeset=set2.nodeList;
			for(GraphNode node:nodeset) {
				node.set=set2;
				set2.nodeList.add(node);
			}
			return set2;
		}
	}
}
class undirectedEdge{
	public GraphNode first;
	public GraphNode second;
	public int weight;
	public undirectedEdge(GraphNode first,GraphNode second,int weight) {
		this.first=first;
		this.second=second;
		this.weight=weight;
	}
}
class Kruskal{
	public ArrayList<GraphNode> nodeList=new ArrayList<>();
	public ArrayList<undirectedEdge> edgeList=new ArrayList<>();
	public Kruskal(ArrayList<GraphNode> nodeList) {
		this.nodeList=nodeList;
	}
	public void addUndirectedWeightedEdge(int i,int j,int d) {
		undirectedEdge edge=new undirectedEdge(nodeList.get(i),nodeList.get(j),d);
		GraphNode first=edge.first;
		GraphNode second=edge.second;
		first.neighbours.add(second);
		second.neighbours.add(first);
		first.weightMap.put(second,d);
		second.weightMap.put(first,d);
		edgeList.add(edge);
	}
	public void KruskalAlgorithms() {
		DisjointSet ds=new DisjointSet();
		ds.makeset(nodeList);
		Comparator<undirectedEdge> comparator=new Comparator<undirectedEdge>() {
			@Override
			public int compare(undirectedEdge o1, undirectedEdge o2) {
				return o1.weight-o2.weight;
			}
		};
		Collections.sort(edgeList,comparator);
		int cost=0;
		for(undirectedEdge edge:edgeList) {
			GraphNode first=edge.first;
			GraphNode second=edge.second;
			if(!ds.findSet(first).equals(ds.findSet(second))) {
				ds.union(first, second);
				cost+=edge.weight;
			}
		}
		System.out.println("minimum cost: "+cost);
	}
}
public class KruskalAlgorithmAndDisjointSet {

	public static void main(String[] args) {

	}
}
