package AllSourceShortestPathProblem;
import java.util.*;
class WeightedNode{
	public String name;
	public int index;
	public boolean isVisited=false;
	public ArrayList<WeightedNode> neighbours=new ArrayList<>();
	public HashMap<WeightedNode,Integer> weightMap=new HashMap<>();
	public WeightedNode parent;
	public WeightedNode(String name,int index) {
		this.name=name;
		this.index=index;
	}
}
class FloydWarshell{
	public ArrayList<WeightedNode> nodeList=new ArrayList<WeightedNode>();
	public FloydWarshell(ArrayList<WeightedNode> nodeList) {
		this.nodeList=nodeList;
	}
	public void floydwarshellAlgorithm() {
		int v[][]=new int[nodeList.size()][nodeList.size()];
		for(int i=0;i<nodeList.size();i++) {
			WeightedNode first=nodeList.get(i);
			for(int j=0;j<nodeList.size();j++) {
				WeightedNode second=nodeList.get(j);
				if(i==j) {
					v[i][j]=0;
				}else if(first.neighbours.contains(second)) {
					v[i][j]=first.weightMap.get(second);
				}else {
					v[i][j]=Integer.MAX_VALUE/10;
				}
			}
		}
		for(int k=0;k<nodeList.size();k++) {
			for(int i=0;i<nodeList.size();i++) {
				for(int j=0;j<nodeList.size();j++) {
					if(v[i][j]>v[i][k]+v[k][j]) {
						v[i][j]=v[i][k]+v[k][j];
					}
				}
			}
		}
		for(int i=0;i<nodeList.size();i++) {
			System.out.print("printing distance for "+nodeList.get(i).name);
			for(int j=0;j<nodeList.size();j++) {
				System.out.print(v[i][j]);
			}
			System.out.println();
		}
	}
	public void addWeightedDirectedNode(int i,int j,int d) {
		WeightedNode first=nodeList.get(i);
		WeightedNode second=nodeList.get(j);
		first.neighbours.add(second);
		first.weightMap.put(second,d);
	}
}
public class FloydWarshellAlgorithms {
	public static void main(String[] args) {
		ArrayList<WeightedNode> nodeList=new ArrayList<WeightedNode>();
		nodeList.add(new WeightedNode("A",0));
		nodeList.add(new WeightedNode("B",1));
		nodeList.add(new WeightedNode("C",2));
		nodeList.add(new WeightedNode("D",3));
		FloydWarshell f=new FloydWarshell(nodeList);
		f.addWeightedDirectedNode(0, 3, 1);
		f.addWeightedDirectedNode(0, 1, 8);
		f.addWeightedDirectedNode(1, 2, 1);
		f.addWeightedDirectedNode(2, 0,4);
		f.addWeightedDirectedNode(3, 1, 2);
		f.addWeightedDirectedNode(3, 2, 9);
		f.floydwarshellAlgorithm();
	}
}
