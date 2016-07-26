
package Main;

import java.util.*;

public class Node {
    List<Node> children;
    List<String> childrenKeys;
    Attribute att;
    
    
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
