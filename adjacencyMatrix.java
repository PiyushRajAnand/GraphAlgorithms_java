package GraphRepresentation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
class graphNode{
	public String name;
	public int index;
	public boolean isVisited;
	public graphNode parent;
	graphNode(String name,int index){
		this.name=name;
		this.index=index;
	}
}
class graph{
	ArrayList<graphNode> nodeList=new ArrayList<graphNode>();
	public int adjacencyMatrix[][];
	public graph(ArrayList<graphNode> nodeList) {
		this.nodeList=nodeList;
		this.adjacencyMatrix=new int[nodeList.size()][nodeList.size()];
	}
	public void addUndirectedEdge(int i,int j) {
		adjacencyMatrix[i][j]=1;
		adjacencyMatrix[j][i]=1;
	}
	public void Print() {
		System.out.print("  ");
		for(int i=0;i<nodeList.size();i++) {
			System.out.print(nodeList.get(i).name+" ");
		}
		System.out.println();
		for(int j=0;j<nodeList.size();j++) {
			System.out.print(nodeList.get(j).name+" ");
			for(int k=0;k<adjacencyMatrix[0].length;k++) {
				System.out.print(adjacencyMatrix[j][k]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public ArrayList<graphNode> getNeighbours(graphNode node){
		ArrayList<graphNode> neighbours=new ArrayList<graphNode>();
		int NodeIndex=node.index;
		for(int i=0;i<adjacencyMatrix.length;i++) {
			if(adjacencyMatrix[NodeIndex][i]==1) {
				neighbours.add(nodeList.get(i));
			}
		}
		return neighbours;
	}
	public void BFS() {
		for(graphNode node:nodeList) {
			if(!node.isVisited) {
				bfsVisit(node);
			}
		}
	}
	public void bfsVisit(graphNode node) {
		System.out.print("Breadth first search: ");
		LinkedList<graphNode> queue=new LinkedList<graphNode>();
		queue.add(node);
		while(!queue.isEmpty()) {
			graphNode currNode=queue.remove(0);
			currNode.isVisited=true;
			System.out.print(currNode.name+" ");
			ArrayList<graphNode> neighbours=getNeighbours(currNode);
			for(graphNode neighbour:neighbours) {
				if(!neighbour.isVisited) {
				    queue.add(neighbour);
				    neighbour.isVisited=true;
				}
			}
		}
	}
	public void DFS() {
		for(graphNode node:nodeList) {
			if(!node.isVisited) {
				dfsVisit(node);
			}
		}
	}
	public void dfsVisit(graphNode node) {
		System.out.print("Depth first search: ");
		Stack<graphNode> stack=new Stack<graphNode>();
		stack.push(node);
		while(!stack.isEmpty()) {
			graphNode currNode=stack.pop();
			currNode.isVisited=true;
			System.out.print(currNode.name+" ");
			ArrayList<graphNode> neighbours=getNeighbours(currNode);
			for(graphNode neighbour:neighbours) {
				if(!neighbour.isVisited) {
					stack.push(neighbour);
					neighbour.isVisited=true;
				}
			}
		}
	}
	public void topologicalSort() {
		Stack<graphNode> stack=new Stack<graphNode>();
		for(graphNode node:nodeList) {
			if(!node.isVisited) {
				topologicalVisit(node,stack);
			}
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
	}
	public void topologicalVisit(graphNode node,Stack<graphNode> stack) {
		ArrayList<graphNode> neighbours=getNeighbours(node);
		for(graphNode neighbour:neighbours) {
			if(!neighbour.isVisited) {
				topologicalVisit(neighbour,stack);
			}
		}
		stack.push(node);
		node.isVisited=true;
	}
	public void SSSPP(graphNode node) {
		LinkedList<graphNode> queue=new LinkedList<graphNode>();
		queue.add(node);
		while(!queue.isEmpty()) {
			graphNode currNode=queue.remove(0);
		    currNode.isVisited=true;
		    System.out.print("Printing path of "+currNode.name);
		    printPath(currNode);
		    System.out.println();
		    ArrayList<graphNode> neighbour=getNeighbours(currNode);
		    for(graphNode neigh:neighbour) {
		    	if(!neigh.isVisited) {
		    		queue.add(neigh);
		    		neigh.isVisited=true;
		    		neigh.parent=currNode;
		    	}
		    }
		}
	}
	public void printPath(graphNode node) {
		if(node.parent!=null) {
			printPath(node.parent);
		}
		System.out.print(node.name+" ");
	}
}
public class adjacencyMatrix {
	public static void main(String[] args) {
        ArrayList<graphNode> nodeList=new ArrayList<graphNode>();
        nodeList.add(new graphNode("A",0));
        nodeList.add(new graphNode("B",1));
        nodeList.add(new graphNode("C",2));
        nodeList.add(new graphNode("D",3));
        nodeList.add(new graphNode("E",4));
        graph am=new graph(nodeList);
        am.addUndirectedEdge(0, 1);
        am.addUndirectedEdge(0, 2);
        am.addUndirectedEdge(0, 3);
        am.addUndirectedEdge(1, 4);
        am.addUndirectedEdge(2, 3);
        am.addUndirectedEdge(3, 4);
        am.SSSPP(nodeList.get(0));
	}
}
