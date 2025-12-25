package GraphRepresentation;
import java.util.*;
class WGN{
	public String name;
	public int index;
	public boolean isVisited;
	public int distance;
	public WGN parent;
	public ArrayList<WGN> neighbours=new ArrayList<>();
	public HashMap<WGN,Integer> weightMap=new HashMap<>();
	public WGN(String name,int index) {
		this.name=name;
		this.index=index;
		this.distance=Integer.MAX_VALUE;
	}
	public int compareTo(WGN o) {
		return this.distance-o.distance;
	}
}
class Prim{
	public ArrayList<WGN> nodeList=new ArrayList<WGN>();
	public Prim(ArrayList<WGN> nodeList) {
		this.nodeList=nodeList;
	}
	public void addWeightedEdge(int i,int j,int d) {
		WGN first=nodeList.get(i);
		WGN second=nodeList.get(j);
		first.neighbours.add(second);
		first.weightMap.put(second,d);
	}
	public void primAlgorthim(WGN node) {
		PriorityQueue<WGN> queue=new PriorityQueue<>();
		node.distance=0;
		queue.addAll(nodeList);
		while(!queue.isEmpty()) {
			WGN currNode=queue.remove();
			for(WGN neighbour:currNode.neighbours) {
				if(neighbour.distance>currNode.weightMap.get(neighbour)) {
					neighbour.distance=currNode.weightMap.get(neighbour);
					neighbour.parent=currNode;
					queue.remove(neighbour);
					queue.add(neighbour);
				}
			}
		}
	}
	public void print() {
		int totalCOst=0;
		for(WGN node:nodeList) {
			System.out.println(node.name+" "+node.distance);
			totalCOst+=node.distance;
		}
		System.out.println(totalCOst);
	}
}
public class PrimAlgorithms {
	public static void main(String[] args) {
		  
	}
}
