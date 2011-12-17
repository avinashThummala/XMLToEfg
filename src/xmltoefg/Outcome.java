//Outcome class

package xmltoefg;

import java.util.ArrayList;

/**
 *
 * @author Avinash Thummala
 */
public class Outcome extends Node{

    ArrayList<String> payoffs;
    ArrayList<String> players;

    boolean printed=false;

    public Outcome(String nodeName, int infoSetNumber, String outcomeName){

        setNodeName(nodeName);
        setInfoSetNumber(infoSetNumber);
        setInfoSetName(outcomeName);

        players=new ArrayList();
        payoffs=new ArrayList();
    }

    public boolean printed(){
        return printed;
    }

    public void addPayoff(String val){
        payoffs.add(val);
    }

    public void addPlayer(String val){
        players.add(val);
    }

    public void diffPrintNode(){

        printed=true;

        System.out.print(getInfoSetNumber()+" \""+getInfoSetName()+"\" { ");

        int i=0;
        for(;i<payoffs.size()-1;i++){
            System.out.print(payoffs.get(i)+", ");
        }

        System.out.println(payoffs.get(i)+" }");
    }

    public void printNode(){

        if(!printed())
        {
            System.out.print("t \""+getNodeName()+"\" "+getInfoSetNumber()+" \""+getInfoSetName()+"\" { ");

            int i=0;
            for(;i<payoffs.size()-1;i++){
                System.out.print(payoffs.get(i)+", ");
            }

            System.out.println(payoffs.get(i)+" }");
        }
    }
}

