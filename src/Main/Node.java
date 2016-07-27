package Main;

import java.util.ArrayList;
import java.util.List;

/**
 * The basic node for the Decision tree class
 * Has a list of children nodes and children keys (pointer Strings) as well as an Attribute for the node
 */
public class Node {
    private List<Node> children;//WHAT IF WE MADE THIS A LinkedHashMap childrenKeys -> children?
    private List<String> childrenKeys;
    private Attribute att;
    
    /**
     * Default constructor
     * Initializes children and childrenKeys
     */
    public Node(){
        children = new ArrayList<>();
        childrenKeys = new ArrayList<>();
    }

    private Node(List<Node> childs){
        children=childs;
    }
    
    public void setAttribute(Attribute a){
        att=a;
        List<Node> ret = new ArrayList<>();
        List<String> values = att.getValues();
        
        for(int i=0;i<values.size();i++){
            children.add(new Node());
            childrenKeys.add(values.get(i));
        }
    }

    public Node getNode(int i){
        return children.get(i);
    }
}
