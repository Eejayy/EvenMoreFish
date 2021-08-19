package com.oheers.fish.competition;

import com.oheers.fish.fishing.items.Fish;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeaderboardTree {

    Node root;
    HashMap<Player, Node> playerRegister = new HashMap<>();
    List<Node> topEntrants = new ArrayList<>();

    private boolean shouldAdd(Player fisher, Float length) {
        if (!playerRegister.containsKey(fisher)) {
            System.out.println("uncontained");
            return true;
        } else {
            System.out.println("contained");
            if (playerRegister.get(fisher).getLength() < length) {
                System.out.println("<<<");
                deleteOldScore(playerRegister.get(fisher));
                return true;
            } else return false;
        }
    }

    private void addToRegister(Player fisher, Node node) {
        System.out.println("adding to register");
        playerRegister.put(fisher, node);
    }

    public void addNode(Fish fish, Player fisher) {
        if (root != null) System.out.println("current root: " + root.getFish().getName() + ", " + root.getFish().getLength() + "cm");
        System.out.println("+++++++++++++++++++++++ adding " + fish.getName());
        if (shouldAdd(fisher, fish.getLength())) {
            System.out.println("should add");
            Node newNode = new Node(fish.getLength(), fish, fisher);

            if (root == null) {
                root = newNode;
                addToRegister(fisher, root);
            }
            else {
                System.out.println("root not null");
                Node focusNode = root;
                Node parentNode;

                while(true) {
                    parentNode = focusNode;
                    if (newNode.length < focusNode.length) {

                        focusNode = focusNode.leftChild;
                        if (focusNode == null) {
                            parentNode.leftChild = newNode;
                            newNode.parent = parentNode;
                            newNode.isLeft = true;
                            addToRegister(fisher, newNode);
                            return;
                        }
                    } else {
                        focusNode = focusNode.rightChild;

                        if (focusNode == null) {
                            parentNode.rightChild = newNode;
                            newNode.parent = parentNode;
                            newNode.isLeft = false;
                            addToRegister(fisher, newNode);
                            return;
                        }
                    }
                }
            }
        }
    }

    private void deleteOldScore(Node worthlessNode) {
        System.out.println("deleting old score");

        Node parentNode = worthlessNode.getParent();

        if (worthlessNode.leftChild == null && worthlessNode.rightChild == null) {
            System.out.println("both empty");
            if (worthlessNode == root) {
                System.out.println("focusnode==root 1");
                root = null;
            } else if (worthlessNode.isLeftChild()) {
                System.out.println("left null 1");
                parentNode.leftChild = null;
            } else {
                System.out.println("right null 1");
                parentNode.rightChild = null;
            }
        }

        else if (worthlessNode.rightChild == null) {
            System.out.println("right null");
            if (worthlessNode == root) {
                System.out.println("focusnode==root 2");
                root = worthlessNode.leftChild;
            } else if (worthlessNode.isLeftChild()) {
                System.out.println("parent's left is child's left");
                parentNode.leftChild = worthlessNode.leftChild;
                worthlessNode.leftChild.parent = parentNode;
                worthlessNode.leftChild.isLeft = true;
            } else {
                System.out.println("parent's right is child's left");
                parentNode.rightChild = worthlessNode.leftChild;
                worthlessNode.leftChild.parent = parentNode;
                worthlessNode.leftChild.isLeft = false;
            }
        }

        else if (worthlessNode.leftChild == null) {
            System.out.println("left null");
            if (worthlessNode == root) {
                System.out.println("fnr");
                root = worthlessNode.rightChild;
            } else if (worthlessNode.isLeftChild()) {
                System.out.println("iwer 1");
                parentNode.leftChild = worthlessNode.rightChild;
                worthlessNode.rightChild.parent = parentNode;
                worthlessNode.rightChild.isLeft = true;
            } else {
                System.out.println("iwer 2");
                parentNode.rightChild = worthlessNode.rightChild;
                worthlessNode.rightChild.parent = parentNode;
                worthlessNode.rightChild.isLeft = false;
            }
        }

        else {
            System.out.println("or else");
            Node replacement = getReplacementNode(worthlessNode);

            if (worthlessNode == root) {
                System.out.println("f=r");
                root = replacement;
            } else if (worthlessNode.isLeftChild()) {
                System.out.println("l=r");
                parentNode.leftChild = replacement;
                replacement.parent = parentNode;
                replacement.isLeft = true;
            } else {
                System.out.println("r=r");
                parentNode.rightChild = replacement;
                replacement.parent = parentNode;
                replacement.isLeft = false;
            }

            replacement.leftChild = worthlessNode.leftChild;
            worthlessNode.leftChild.parent = replacement;
            worthlessNode.isLeft = true;
        }
    }

    private Node getReplacementNode(Node replacingNode) {
        Node replaceParent = replacingNode;
        Node replacement = replacingNode;

        Node focusNode = replacingNode.rightChild;

        while(focusNode != null) {
            replaceParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftChild;
        }

        if (replacement != replacingNode.rightChild) {
            replaceParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacingNode.rightChild;
        }

        return replacement;
    }

    public List<Node> getTopEntrants(Node focusNode, int goTo) {
        if (topEntrants.size() <= goTo) {
            if (focusNode != null) {

                getTopEntrants(focusNode.rightChild, goTo);
                topEntrants.add(focusNode);
                System.out.println("getTopEntrants: " + focusNode.getFish().getLength());
                getTopEntrants(focusNode.leftChild, goTo);

            }
        }

        return topEntrants;
    }

    public int size() {
        return playerRegister.size();
    }

    public void resetLeaderboard() {
        topEntrants.clear();
    }

    public Node get(int index) {
        topEntrants.clear();
        getTopEntrants(root, index);
        return topEntrants.get(index);
    }
}

class Node {

    float length;
    boolean isLeft;
    Fish fish;
    Player fisher;

    Node leftChild;
    Node rightChild;
    Node parent;

    Node(float fishLength, Fish fish, Player fisher) {
        this.length = fishLength;
        this.fish = fish;
        this.fisher = fisher;
    }

    public float getLength() {
        return length;
    }

    public Fish getFish() {
        return fish;
    }

    public Player getFisher() {
        return fisher;
    }

    public boolean isLeftChild() {
        return this.isLeft;
    }

    public Node getParent() {
        return this.parent;
    }
}