package xmltoefg;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*;
import java.util.Stack;

public class Main extends DefaultHandler{

    Node presentNode=null;
    int outcomeCounter=1;
    int playerCounter=0;
    Stack<Node> stack =null;


    public void buildTree(String fileName){

        try{

		SAXParserFactory spf = SAXParserFactory.newInstance();
                SAXParser sp = spf.newSAXParser();

                sp.parse(new File(fileName), this);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if(qName.equals("extensiveForm"))
            stack=new Stack();

        if(qName.equals("node") || qName.equals("outcome"))
        {
            Node newNode=readNode(qName, attributes);

            if(stack.empty())
                stack.push(newNode);
            else
            {
                Node tempNode=stack.pop();
                tempNode.addNextNode(newNode);

                stack.push(tempNode);
                stack.push(newNode);
            }
        }

        if(qName.equals("payoff")){

            Outcome tempNode=(Outcome)stack.pop();

            tempNode.addPlayer(attributes.getValue("player"));
            tempNode.addPayoff(attributes.getValue("value"));

            stack.push(tempNode);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(qName.equals("node") || qName.equals("outcome"))
            presentNode=stack.pop();

    }


    public Node readPlayerNode(Attributes attributes){

        int playerId=Integer.parseInt(attributes.getValue("player"));

        if(playerId>playerCounter)
            playerCounter++;

        int infoSetId=1;
        String move=null, prob=null;

        if(attributes.getValue("iset")!=null)
            infoSetId=Integer.parseInt(attributes.getValue("iset"));

        PlayerNode playerNode=new PlayerNode("",playerId,infoSetId,"" );

        if(attributes.getValue("move")!=null){

            move=attributes.getValue("move");
            playerNode.setMove(move);
        }

        if(attributes.getValue("prob")!=null){

            prob=attributes.getValue("prob");
            playerNode.setProb(prob);
        }
                               
        return playerNode;
    }

    public Node readOutcome(Attributes attributes){
        
        String move=null, prob=null;

        Outcome outcome=new Outcome("", outcomeCounter++, "");

        if(attributes.getValue("move")!=null){

            move=attributes.getValue("move");
            outcome.setMove(move);
        }

        if(attributes.getValue("prob")!=null){

            prob=attributes.getValue("prob");
            outcome.setProb(prob);
        }
        
        return outcome;
    }

    public Node readChoiceNode(Attributes attributes){
       
        int infoSetId=1;
        String move=null, prob=null;

        if(attributes.getValue("iset")!=null)
            infoSetId=Integer.parseInt(attributes.getValue("iset"));

        ChoiceNode choiceNode=new ChoiceNode("", infoSetId, "");

        if(attributes.getValue("move")!=null){

            move=attributes.getValue("move");
            choiceNode.setMove(move);
        }

        if(attributes.getValue("prob")!=null){

            prob=attributes.getValue("prob");
            choiceNode.setProb(prob);
        }
        
        return choiceNode;
    }

    public Node readNode(String tagName, Attributes attributes){

        Node returnNode=null;

        if(tagName.equals("node"))
        {
            if(attributes.getValue("player")!=null)
                returnNode=readPlayerNode(attributes);
            else
                returnNode=readChoiceNode(attributes);
        }
        else if(tagName.equals("outcome"))
            returnNode=readOutcome(attributes);

        return returnNode;
    }

    Main(String[] args){

        if(args.length!=1)
        {
            System.out.println("Bad usage: Have to provide \"xml\" file");
            return;
        }
        
        buildTree(args[0]);

        System.out.print("EFG 2 R \"A simple test\" { ");

        for(int i=1;i<=playerCounter;i++)
            System.out.print("\""+i+"\" ");

        System.out.println("}");
        System.out.println("\"\"");
        System.out.println();

        presentNode.printNode();
    }

    public static void main(String[] args) {

        new Main(args);

    }

}
