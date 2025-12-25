package GraphRepresentation;
import java.util.*;
class NewGraph{
	public String name;
	public int index;
	public NewGraph parent;
	public ArrayList<NewGraph> neighbours=new ArrayList<NewGraph>();
	public HashMap<NewGraph,Integer> weighMap=new HashMap<>();
	public boolean isVisited=false;
	public int distance;
	public NewGraph(String name,int index) {
		this.name=name;
		this.index=index;
		this.distance=Integer.MAX_VALUE;
	}
	public int compareTo(NewGraph o) {
		return this.distance-o.distance;
	}
	public String toString() {
		return name;
	}
}
class dijkstraAlgo{
	public ArrayList<NewGraph> nodeList=new ArrayList<>();
	public dijkstraAlgo(ArrayList<NewGraph> nodeList) {
		this.nodeList=nodeList;
	}
	public void addWeightedNode(int i,int j,int d) {
		NewGraph first=nodeList.get(i);
		NewGraph second=nodeList.get(j);
		first.neighbours.add(second);
		first.weighMap.put(second,d);
	}
	public void dijkstra(NewGraph node) {
		PriorityQueue<NewGraph> queue=new PriorityQueue<>();
		node.distance=0;
		queue.addAll(nodeList);
		while(!queue.isEmpty()) {
			NewGraph currNode=queue.remove();
			for(NewGraph neighbour:currNode.neighbours) {
				if(queue.contains(neighbour)) {
					if(neighbour.distance>currNode.distance+currNode.weighMap.get(neighbour)) {
						neighbour.distance=currNode.distance+currNode.weighMap.get(neighbour);
						neighbour.parent=currNode;
						queue.remove(neighbour);
						queue.add(neighbour);
					}
				}
			}
		}
		for(NewGraph n:nodeList) {
			System.out.println(n+" "+n.distance+"Node path");
			printPath(n);
		}
	}
	public void printPath(NewGraph node) {
		if(node.parent!=null) {
			printPath(node.parent);
		}
		System.out.print(node.name);
	}
}

public class DijkstraAlgorithm {
	public static void main(String[] args) {

	}

}
