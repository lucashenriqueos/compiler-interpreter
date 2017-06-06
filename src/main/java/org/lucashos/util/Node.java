package org.lucashos.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 04/06/17.
 */
public class Node {
    Token token;
    String type;
    Node parent;
    List<Node> children;

    public Node(String type) {
        this.type = type;
        children = new ArrayList<>();
    }

    public Node(Token token) {
        this.token = token;
        children = new ArrayList<>();
    }

    public void addChild(Node node) {
        this.children.add(node);
        node.setParent(this);
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChild(int index) {
        return this.children.get(index);
    }

    public List<Node> getChildren() {
        return children;
    }

    public Token getToken() {
        return token;
    }

    public String getChildType(int index) {
        return this.children.get(index).type;
    }

    @Override
    public String toString() {
        if (token == null) {
            return type;
        }
        return token.toString();
    }
}
