package GraphRepresentation;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
class GN{
	public String name;
	public int index;
	public ArrayList<GN> neighbours=new ArrayList<GN>();
	public GN parent;
	public boolean isVisited=false;
	public GN(String name,int index) {
		this.name=name;
		this.index=index;
	}
}
class graphAL{
	ArrayList<GN> nodeList=new ArrayList<GN>();
	public graphAL(ArrayList<GN> nodeList) {
		this.nodeList=nodeList;
	}
	public void addUndirectedEdge(int i,int j) {
		GN first=nodeList.get(i);
		GN second=nodeList.get(j);
		first.neighbours.add(second);
		second.neighbours.add(first);
	}
	public void print() {
		for(int i=0;i<nodeList.size();i++) {
			System.out.print(nodeList.get(i).name+"-->");
			for(int j=0;j<nodeList.get(i).neighbours.size();j++) {
				System.out.print(nodeList.get(i).neighbours.get(j).name+" ");
			}
			System.out.println();
		}
	}
	public void BFS() {
		for(GN node:nodeList) {
			if(!node.isVisited) {
				bfsVisit(node);
			}
		}
	}
	public void DFS() {
		for(GN node:nodeList) {
			if(!node.isVisited) {
				dfsVisit(node);
			}
		}
	}
	public void bfsVisit(GN node) {
		LinkedList<GN> queue=new LinkedList<GN>();
		queue.add(node);
		while(!queue.isEmpty()) {
			GN currNode=queue.remove(0);
			currNode.isVisited=true;
			System.out.print(currNode.name+" ");
			for(GN neighbour:currNode.neighbours) {
				if(!neighbour.isVisited) {
					queue.add(neighbour);
					neighbour.isVisited=true;
				}
			}
		}
	}
	public void dfsVisit(GN node) {
		Stack<GN> stack=new Stack<>();
		stack.push(node);
		while(!stack.isEmpty()) {
			GN currNode=stack.pop();
			currNode.isVisited=true;
			System.out.print(currNode.name+" ");
			for(GN neighbour:currNode.neighbours) {
				if(!neighbour.isVisited) {
					stack.push(neighbour);
					neighbour.isVisited=true;
				}
			}
		}
	}
	public void topologicalSort() {
		Stack<GN> stack=new Stack<GN>();
		for(GN node:nodeList) {
			topologicalVisit(node,stack);
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
	}
	public void topologicalVisit(GN node,Stack<GN> stack) {
		for(GN neighbour:node.neighbours) {
			if(!neighbour.isVisited) {
				topologicalVisit(node,stack);
			}
			stack.push(node);
			node.isVisited=true;
		}
	}
	public void SSSPP(GN node) {
		LinkedList<GN> queue=new LinkedList<GN>();
		queue.add(node);
		while(!queue.isEmpty()) {
			GN currNode=queue.remove(0);
			currNode.isVisited=true;
			System.out.print("Printing path of "+currNode.name);
			printPath(currNode);
			for(GN neighbour:currNode.neighbours) {
				if(!neighbour.isVisited) {
					queue.add(neighbour);
					neighbour.isVisited=true;
					neighbour.parent=currNode;
				}
			}
		}
	}
	public void printPath(GN node) {
		if(node.parent!=null) {
			printPath(node.parent);
		}
		System.out.print(node.name+" ");
	}
}
public class adjacencyList {
	public static void main(String[] args) {
        ArrayList<GN> nodeList=new ArrayList<GN>();
        nodeList.add(new GN("A",0));
        nodeList.add(new GN("B",1));
        nodeList.add(new GN("C",2));
        nodeList.add(new GN("D",3));
        nodeList.add(new GN("E",4));
        graphAL gal=new graphAL(nodeList);
        gal.addUndirectedEdge(0, 1);
        gal.addUndirectedEdge(0, 2);
        gal.addUndirectedEdge(0, 3);
        gal.addUndirectedEdge(1, 4);
        gal.addUndirectedEdge(2, 3);
        gal.addUndirectedEdge(3, 4);
//        gal.BFS();
        gal.DFS();
	}
}
