package SingleSourceShortestPath;
import java.util.*;
class Bsfnode{
	public String name;
	public int index;
	public boolean isVisited;
	public Bsfnode parent;
	public ArrayList<Bsfnode> neighbours=new ArrayList<>();
	public Bsfnode(String name,int index) {
		this.name=name;
		this.index=index;
	}
}
class BFS{
	public ArrayList<Bsfnode> nodeList=new ArrayList<>();
	public BFS(ArrayList<Bsfnode> nodeList) {
		this.nodeList=nodeList;
	}
	public void printPath(Bsfnode node) {
		if(node.parent!=null) {
			printPath(node.parent);
		}
		System.out.print(node.name+" ");
	}
	public void addUndirectedEdge(int i,int j) {
		Bsfnode first=nodeList.get(i);
		Bsfnode second=nodeList.get(j);
		first.neighbours.add(second);
		second.neighbours.add(first);
	}
	public void bfs(Bsfnode node) {
		LinkedList<Bsfnode> queue=new LinkedList<>();
		queue.add(node);
		while(!queue.isEmpty()) {
			Bsfnode currNode=queue.remove(0);
			currNode.isVisited=true;
			System.out.print("Printing path from "+currNode.name+" ");
			printPath(currNode);
			System.out.println();
			for(Bsfnode neighbour:currNode.neighbours) {
				if(!neighbour.isVisited) {
					queue.add(neighbour);
					neighbour.isVisited=true;
					neighbour.parent=currNode;
				}
			}
		}
	}
}
public class BFSsssp {

	public static void main(String[] args) {
       ArrayList<Bsfnode> nodeList=new ArrayList<>();
       nodeList.add(new Bsfnode("A",0));
       nodeList.add(new Bsfnode("B",1));
       nodeList.add(new Bsfnode("C",2));
       nodeList.add(new Bsfnode("D",3));
       nodeList.add(new Bsfnode("E",4));
       nodeList.add(new Bsfnode("F",5));
       nodeList.add(new Bsfnode("G",6));
       BFS b=new BFS(nodeList);
       b.addUndirectedEdge(0, 1);
       b.addUndirectedEdge(0, 2);
       b.addUndirectedEdge(1, 4);
       b.addUndirectedEdge(1, 3);
       b.addUndirectedEdge(1, 2);
       b.addUndirectedEdge(3, 4);
       b.addUndirectedEdge(4, 6);
       b.addUndirectedEdge(5, 6);
       b.addUndirectedEdge(2, 5);
       b.bfs(nodeList.get(0));
	}

}
