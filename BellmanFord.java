package SingleSourceShortestPath;
import java.util.*;
class WeightNode{
	public String name;
	public int index;
	public ArrayList<WeightNode> neighbours=new ArrayList<>();
	public HashMap<WeightNode,Integer> weightMap=new HashMap<>();
	public int distance;
	public WeightNode parent;
	public WeightNode(String name,int index) {
		this.name=name;
		this.index=index;
		this.distance=Integer.MAX_VALUE;
	}
	public int compareTo(WeightNode o) {
		return this.distance-o.distance;
	}
}
class bellmanFord{
	public ArrayList<WeightNode> nodeList=new ArrayList<>();
	public bellmanFord(ArrayList<WeightNode> nodeList) {
		this.nodeList=nodeList;
	}
	public void printPath(WeightNode node) {
		if(node.parent!=null) {
			printPath(node.parent);
		}
		System.out.print(node.name+" ");
	}
	public void addWeightedEdge(int i,int j,int d) {
		WeightNode first=nodeList.get(i);
		WeightNode second=nodeList.get(j);
		first.neighbours.add(second);
		first.weightMap.put(second,d);
	}
	public void bf(WeightNode sourceNode) {
		sourceNode.distance=0;
		for(int i=0;i<nodeList.size();i++) {
			for(WeightNode nodeE:nodeList) {
				for(WeightNode neighbour:nodeE.neighbours) {
					if(nodeE.distance+nodeE.weightMap.get(neighbour)<neighbour.distance) {
						neighbour.distance=sourceNode.distance+sourceNode.weightMap.get(neighbour);
						neighbour.parent=nodeE;
					}
				}
			}
		}
		System.out.println("Checking for negative cycle");
		for(WeightNode node:nodeList) {
			for(WeightNode neigh:node.neighbours) {
				if(node.distance+node.weightMap.get(neigh)<neigh.distance) {
					System.out.println("negative cycle found");
					System.out.println("Found at "+neigh.name);
					neigh.distance=node.distance+node.weightMap.get(neigh);
					System.out.println("new distance"+neigh.distance);
					return;
				}
			}
		}
		for(WeightNode n:nodeList) {
			System.out.print(n.name+" "+n.distance+" path");
			printPath(n);
		}
		System.out.println();
	}
}
public class BellmanFord {

	public static void main(String[] args) {

	}
}
