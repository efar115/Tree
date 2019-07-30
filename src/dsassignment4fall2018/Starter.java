/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsassignment4fall2018;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version Fall2018
 * @author clatulip
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create an empty family tree
        FamilyTree tree = new FamilyTree();

        // Code below can be used to test
        // add a matriarch
        Relative matriarch = new Relative("Abigail", "Jackson", 1900, 1964);
        tree.addHeadOfFamily(matriarch);

        try {
            Relative child = new Relative("Eva", "George", 1918, 1990);
            tree.addChild(child, matriarch);
            Relative child2 = new Relative("Arthur", "Sarah", 1920, 1978);
            tree.addChild(child2, matriarch);
            Relative child3 = new Relative("Adam", null, 1922, 1923);
            tree.addChild(child3, matriarch);
            Relative grandchild1 = new Relative("Hannah", "John", 1945, 0);
            tree.addChild(grandchild1, child2);
            Relative ggc = new Relative("Joe", null, 1970, 0);
            tree.addChild(ggc, grandchild1);

            ArrayList<Relative> family = new ArrayList<Relative>() {
                {
                    add(matriarch);
                    add(child);
                    add(child2);
                    add(child3);
                    add(grandchild1);
                    add(ggc);
                }
            };

            for (Relative r : family) {
                //Uncomment the block below when getNumDescendants is complete
                /*
                System.out.println(r.getName() + " has "
                        + tree.getNumDescendants(r) + " descendants");
                 */

                //Uncomment the block below when getSortedSiblings is complete
                /*
                ArrayList<Relative> siblings = tree.getSortedSiblings(r);
                System.out.println(r.getName() + " has siblings: ");
                if (siblings != null) {
                    for (Relative s : siblings) {
                        System.out.println("-" + s.getName());
                    }
                } else {
                    System.out.println("-none");
                }
                System.out.println("----------------------");
                 */
            }
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
            Logger.getLogger(Starter.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(tree.toString());
    }

}
