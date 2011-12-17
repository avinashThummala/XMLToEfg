// A generic Node class

package xmltoefg;

public abstract class Node {

    String nodeName="";
    int infoSetNumber=0;
    String infoSetName="";
    String move;
    boolean hasMove=false;
    boolean hasProb=false;
    String prob;


    Node(){
    }

    public void setNodeName(String val){
        nodeName=val;
    }

    public void setInfoSetNumber(int val){
        infoSetNumber=val;
    }

    public void setInfoSetName(String val){
        infoSetName=val;
    }

    public String getNodeName(){
        return nodeName;
    }

    public int getInfoSetNumber(){
        return infoSetNumber;
    }

    public String getInfoSetName(){
        return infoSetName;
    }

    public void setMove(String val){
        move=val;
        hasMove=true;
    }

    public String getMove(){
        return move;
    }

    public boolean hasMove(){
        return hasMove;
    }
    
    public void setProb(String val){
        prob=val;
        hasProb=true;
    }

    public String getProb(){
        return prob;
    }

    public boolean hasProb(){
        return hasProb;
    }

    public abstract void printNode();

    public void addNextNode(Node newNode){        
    }
}

