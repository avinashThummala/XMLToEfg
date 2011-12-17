//A PlayerNode class extending Node class

package xmltoefg;

import java.util.ArrayList;
/**
 *
 * @author Avinash Thummala
 */
public class PlayerNode extends Node{
   
    ArrayList<Node> nextNodes;

    int playerId;

    public PlayerNode(String nodeName, int playerId, int infoSetId, String infoSetName){

        setNodeName(nodeName);
        setInfoSetNumber(infoSetId);
        setInfoSetName(infoSetName);

        nextNodes=new ArrayList();

        this.playerId=playerId;
    }

    public void addNextNode(Node val){
        nextNodes.add(val);
    }

    public void printNode(){


        System.out.print("p \""+getNodeName()+"\" "+playerId+" "+getInfoSetNumber()+" \""+getInfoSetName()+"\" { ");

        int i=0;
        for(;i<nextNodes.size()-1;i++)
            System.out.print( "\""+nextNodes.get(i).getMove()+"\" ");

        if(nextNodes.get(i).getMove()!=null)
        {
            System.out.println("\""+nextNodes.get(i).getMove()+"\" } 0");
            
            for(i=0;i<nextNodes.size();i++)
                nextNodes.get(i).printNode();
        }
        else
        {
            System.out.print("} ");
            ((Outcome)nextNodes.get(i)).diffPrintNode();

            for(i=0;i<nextNodes.size()-1;i++)
                nextNodes.get(i).printNode();
        }
    }

}

