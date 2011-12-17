//ChoiceNode class extending the Node class

package xmltoefg;

import java.util.ArrayList;

/**
 *
 * @author Avinash Thummala
 */
public class ChoiceNode extends Node{

    ArrayList<Node> nextNodes;

    public ChoiceNode(String nodeName, int infoSetId, String infoSetName){

        setNodeName(nodeName);
        setInfoSetNumber(infoSetId);
        setInfoSetName(infoSetName);

        nextNodes=new ArrayList();
    }

    public void addNextNode(Node val){
        nextNodes.add(val);
    }

    public void printNode(){

        System.out.print("c \""+getNodeName()+"\" "+getInfoSetNumber()+" \""+getInfoSetName()+"\" { ");

        for(int i=0;i<nextNodes.size();i++)
        {
            if(nextNodes.get(i).getMove()!=null)
                System.out.print( "\""+nextNodes.get(i).getMove()+"\" "+nextNodes.get(i).getProb()+" " );
            else
                System.out.print( "\"\" "+nextNodes.get(i).getProb()+" " );
        }

        System.out.println("} 0");

        for(int i=0;i<nextNodes.size();i++)
            nextNodes.get(i).printNode();
    }

}

