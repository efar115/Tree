/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsassignment4fall2018;

import DataStructures.*;
import Exceptions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @version Fall 2018
 * @author clatulip
 */
public class FamilyTree {

    /**
     * link to parent node at root of family tree
     */
    protected MultiTreeNode<Relative> fTree;
    /**
     * number of people in the family (not including spouses)
     */
    protected int size;

    /**
     * Constructor - just creates the initial empty family tree
     */
    public FamilyTree() {
        fTree = null;
        size = 0;
    }

    /**
     * Constructor - creates initial tree with head relative
     *
     * @param head of family
     */
    public FamilyTree(Relative head) {
        fTree = new MultiTreeNode<>();
        fTree.setElement(head);
        size++;
    }

    /**
     * Searches through the family tree to find the node that contains member.
     *
     * Sets up find to begin searching from the root Checks for exceptions
     * before and after calling find method
     *
     * @param rel element to be found
     * @return the node for the specified family member, or null if not found
     * @throws ElementNotFoundException if null is returned after searching
     * @throws EmptyCollectionException empty
     */
    public MultiTreeNode<Relative> findMember(Relative rel)
            throws ElementNotFoundException, EmptyCollectionException {

        if (isEmpty()) {
            throw new EmptyCollectionException("ECE - findMember");
        }
        MultiTreeNode<Relative> temp = find(fTree, rel);
        if (temp == null) {
            throw new ElementNotFoundException("ENF - findMember");
        }
        return temp;
    }

    /**
     * Set up and called through findMember.
     *
     * @param node currently being looked at
     * @param rel node being searched for
     * @return MultiTreeNode<Relative> node when found
     */
    private MultiTreeNode<Relative> find(MultiTreeNode<Relative> node, Relative rel) {

        //Base case 1: null node
        if (node == null) {
            return null;
        }

        //Base case 2: found element
        if (node.getElement().equals(rel)) {
            return node;
        }

        //Run again on each child node
        for (MultiTreeNode<Relative> n : node.getChildren()) {

            //test each child node
            MultiTreeNode<Relative> temp = find(n, rel);
            if (temp != null) {
                return temp;
            }
        }

        return null;
    }

    /**
     * Adds the first person to the root of the family tree
     *
     * @param head of family
     * @return true if added successfully, false if there is already a head
     */
    public boolean addHeadOfFamily(Relative head) {
        if (fTree == null) {
            fTree = new MultiTreeNode<>();
            fTree.setElement(head);
            size++;
            return true;
        }
        return false;

    }

    /**
     * Adds a new Relative to the family tree, as a child of the specified
     * parent
     *
     * @param child new Relative to add
     * @param parent the parent node that this child should be added to,
     * @throws ElementNotFoundException if parent is not in FamilyTree
     * @throws Exceptions.EmptyCollectionException
     */
    public void addChild(Relative child, Relative parent)
            throws ElementNotFoundException, EmptyCollectionException {

        MultiTreeNode<Relative> mNode = findMember(parent);
        if (mNode == null) {
            throw new ElementNotFoundException("addChild");
        }
        mNode.addChild(new MultiTreeNode<>(child));
        size++;
    }

    /**
     * Gets an array containing all the children of the specified family member
     *
     * To get array of children the MultiTreeNode<Relative> containing parent
     * must be found. Each child in the associated ArrayList must be placed in a
     * new ArrayList and then returned.
     *
     * @param parent the Relative whose children we want to return
     * @return the array of Relative objects that are the children of specified
     * parent
     * @throws ElementNotFoundException if parent is not in FamilyTree
     * @throws Exceptions.EmptyCollectionException
     */
    public ArrayList<Relative> getChildren(Relative parent)
            throws ElementNotFoundException, EmptyCollectionException {

        ArrayList<Relative> children = new ArrayList<>();

        if (isEmpty()) {
            throw new EmptyCollectionException("ECE - findMember");

        }
        MultiTreeNode<Relative> temp = findMember(parent);
        if (temp == null) {
            throw new ElementNotFoundException("ENF - findMember");
        } else {
            int c = temp.getNumChildren();
            if (c == 0) {
                return null;
            } else {
                for (int i = 0; i < c; i++) {
                    Relative g = temp.getChild(i).getElement();
                    children.add(g);
                }
            }

        }

        return children;
    }

    /**
     * Gets the head at the root of this FamilyTree
     *
     * @return Relative at root
     * @throws EmptyCollectionException
     */
    public Relative getHeadOfFamily() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty Tree");
        }
        return fTree.getElement();
    }

    /**
     * Find out how many descendants a given family rel has Sets up descendants
     * method.
     *
     * @param rel element
     * @return int number of descendants of specified family rel
     * @throws ElementNotFoundException if rel not in tree
     * @throws Exceptions.EmptyCollectionException
     */
    public int getNumDescendants(Relative rel)
            throws ElementNotFoundException, EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("ECE - findMember");
        }
        MultiTreeNode<Relative> temp = findMember(rel);
        if (temp == null) {
            throw new ElementNotFoundException("ENF - findMember");
        }

        return getNumDescendants(temp) - 1;
    }

    /**
     *
     * @param rel
     * @return int number of children
     */
    private int getNumDescendants(MultiTreeNode<Relative> rel) {
        if (rel == null) {
            return 0;
        }
        int temp = 1;
        for (MultiTreeNode<Relative> n : rel.getChildren()) {

            temp += getNumDescendants(n);
        }

        return temp;
    }

    /**
     * Return all siblings of given member in ascending order by birth year
     *
     * @param rel will be found and sibilings that share it's parent will be
     * returned in a new ArrayList
     * @return ArrayList a sorted list of siblings from earliest birth year to
     * latest
     * @throws ElementNotFoundException if member not in tree
     * @throws Exceptions.EmptyCollectionException
     */
    public ArrayList<Relative> getSortedSiblings(Relative rel)
            throws ElementNotFoundException, EmptyCollectionException {
        ArrayList<Relative> siblings = new ArrayList<>();
        if (isEmpty()) {
            throw new EmptyCollectionException("ECE - findMember");
        }

        MultiTreeNode<Relative> temp = findMember(rel);

        if (temp == null) {
            throw new ElementNotFoundException("ENF - findMember");
        }
        MultiTreeNode<Relative> t = getSortedSiblings(fTree, rel);

        if (t == null) {
            return null;
        }

        for (int i = 0; i < t.getNumChildren(); i++) {
            Relative g = t.getChild(i).getElement();
            siblings.add(g);
        }
        siblings.remove(rel);
        if (siblings.isEmpty()) {
            return null;
        }
        Collections.sort(siblings);

        return siblings;
    }

    /**
     *
     * @param node
     * @param rel
     * @return parent of rel
     */
    private MultiTreeNode<Relative> getSortedSiblings(MultiTreeNode<Relative> node, Relative rel) {
        //Base case 1: null node
        if (node == null) {
            return null;
        }

        //Base case 2: found element
        for (MultiTreeNode<Relative> n : node.getChildren()) {
            if ( n.getElement().equals(rel) ) { 
                return node;
            }
        }

        //Run again on each child node
        for (MultiTreeNode<Relative> n : node.getChildren()) {

            //test each child node
            MultiTreeNode<Relative> temp = getSortedSiblings(n, rel);
            if (temp != null) {
                return temp;
            }
        }

        return null;
    }

    /**
     * @return collection size
     */
    public int size() {
        return size;
    }

    /**
     * @return true if collection is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (this.size == 0) {
            return "Tree is Empty.";
        }
        int generation = 1;
        String result = "";
        Map<String, ArrayList<MultiTreeNode<Relative>>> thisGen
                = new HashMap<>();
        Map<String, ArrayList<MultiTreeNode<Relative>>> nextGen
                = new HashMap<>();
        // record original parent
        result += "gen" + generation + ": " + this.fTree.getElement().getName();
        // add first generation
        thisGen.put(this.fTree.getElement().getName(), fTree.getChildren());
        generation++;
        result += "\ngen" + generation + ": ";
        // add the rest of the generations
        while (!thisGen.isEmpty()) {
            String parent = (String) thisGen.keySet().toArray()[0];
            ArrayList<MultiTreeNode<Relative>> children = thisGen.remove(parent);
            // record all members of the current generation
            result += parent + " -> [";
            for (MultiTreeNode<Relative> child : children) {
                result = result + child.getElement().getName() + ", ";
                nextGen.put(child.getElement().getName(), child.getChildren());
            }
            if (!result.endsWith("[")) {
                result = result.substring(0, result.length() - 2);
            }
            result = result + "], ";
            // if all members of the current generation are added, move to next gen
            if (thisGen.isEmpty() && !nextGen.isEmpty()) {
                thisGen = nextGen;
                generation++;
                nextGen = new HashMap<>();
                result += "\ngen" + generation + ": ";
            }
        }
        return result;
    }

}
