package com.lrn.educative.crust;

import java.util.ArrayList;
import java.util.List;

public class NTreeNode {
    public Integer data;
    public List<NTreeNode> children = new ArrayList<>();

    public NTreeNode(Integer data) {
        this.data = data;
    }
    public List<NTreeNode> addChild(NTreeNode node) {
        children.add(node);
        return children;
    }

    public String getStringRepresentation(NTreeNode nTreeNode) {
        String str = " [" + nTreeNode.data+"] ";
        if(!nTreeNode.children.isEmpty()) {
            String childStr = "";
            for(NTreeNode child: nTreeNode.children) {
                if(child != null) {
                    childStr = childStr + getStringRepresentation(child);
                }
            }
            str = str + " -> {" + childStr + "}";
        }
        return str;
    }

    @Override
    public String toString() {
        return "["+data+"]";
    }
}
