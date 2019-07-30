/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsassignment4fall2018;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * @version Fall 2018
 * @author Kyle
 * @author Mariah
 */
public class FamilyTreeTest {

    /**
     * test instance
     */
    FamilyTree instance;

    //FamMembers that will be added into the collection at the beginning of each test
    private final Relative a;//12 Descendants
    private final Relative b;
    private final Relative c;
    private final Relative d;
    private final Relative e;
    private final Relative f;
    private final Relative g;
    private final Relative h;
    private final Relative i;
    private final Relative j;
    private final Relative k;
    private final Relative l;
    private final Relative m;

    //Family member not added
    //Use to test ElementNotFoundException
    private final Relative lloyd;

    /**
     *
     */
    public FamilyTreeTest() {
        instance = new FamilyTree();
        a = new Relative("a", "z", 101, 200);
        b = new Relative("b", "y", 102, 200);
        c = new Relative("c", "x", 103, 200);
        d = new Relative("d", "w", 104, 200);
        e = new Relative("e", "v", 105, 200);
        f = new Relative("f", "u", 106, 200);
        g = new Relative("g", "t", 107, 200);
        h = new Relative("h", "s", 108, 200);
        i = new Relative("i", "r", 109, 200);
        j = new Relative("j", "q", 110, 200);
        k = new Relative("k", "p", 111, 200);
        l = new Relative("l", "o", 112, 200);
        m = new Relative("m", "n", 113, 200);
        lloyd = new Relative("Lloyd", null, 0, 1000);
    }

    /**
     * Fills the instance Must be called manually through each method
     */
    @Before
    public void setUp() {

        instance.addHeadOfFamily(a);

        try {
            instance.addChild(c, a);
            instance.addChild(b, a);
            instance.addChild(d, a);
            instance.addChild(e, b);
            instance.addChild(f, b);
            instance.addChild(g, e);
            instance.addChild(h, e);
            instance.addChild(i, b);
            instance.addChild(j, f);
            instance.addChild(k, f);
            instance.addChild(l, b);
            instance.addChild(m, l);
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
            fail("Shouldn't throw exception");
        }

    }

    /**
     * Test of findMember method, of class FamilyTreeBase.
     *
     * @throws Exceptions.ElementNotFoundException
     * @throws Exceptions.EmptyCollectionException
     */
    @Test
    public void testFindMember() throws ElementNotFoundException, EmptyCollectionException {
        System.out.println("findMember");

        assertEquals(13, instance.size());

        try {
            instance.findMember(lloyd);
        } catch (ElementNotFoundException ex) {
            assertTrue(ex instanceof ElementNotFoundException);
        }

        assertEquals(a, instance.findMember(a).getElement());
        assertEquals(b, instance.findMember(b).getElement());
        assertEquals(c, instance.findMember(c).getElement());
        assertEquals(d, instance.findMember(d).getElement());
        assertEquals(e, instance.findMember(e).getElement());
        assertEquals(f, instance.findMember(f).getElement());
        assertEquals(k, instance.findMember(k).getElement());
        assertEquals(i, instance.findMember(i).getElement());

        System.out.println(instance.toString());

        instance = new FamilyTree();

        assertTrue(instance.isEmpty());

        try {
            instance.findMember(a);
        } catch (EmptyCollectionException ex) {
            assertTrue(ex instanceof EmptyCollectionException);
        }

    }

    /**
     * Test of addHeadOfFamily method, of class FamilyTreeBase.
     *
     * @throws Exceptions.EmptyCollectionException
     */
    @Test
    public void testAddHeadOfFamily() throws EmptyCollectionException {
        System.out.println("addHeadOfFamily");
        
        assertEquals(13, instance.size());
        Relative x = new Relative("x", "y", 99, 200);
        boolean headFamily = instance.addHeadOfFamily(x);
        assertEquals(false, headFamily);
        
        assertEquals(13, instance.size());
        
        
        assertEquals(a , instance.getHeadOfFamily());

        
    }

    /**
     * Test of addChild method, of class FamilyTreeBase.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddChild() throws Exception {
        System.out.println("addChild");
        
        assertEquals(13, instance.size());
        Relative x = new Relative("x", "x", 98, 157);
        Relative y = new Relative("y", "b", 100, 202);
        Relative z = new Relative("z", "x", 103, 201);
        instance.addChild(x, c);
        assertEquals(14, instance.size());
        try {
            instance.addChild(y,z );
        } catch (ElementNotFoundException ex) {
            assertTrue(ex instanceof ElementNotFoundException );
        }

    }

    /**
     * Test of getChildren method, of class FamilyTreeBase.
     *
     * @throws Exceptions.ElementNotFoundException
     * @throws Exceptions.EmptyCollectionException
     */
    @Test
    public void testGetChildren()
            throws ElementNotFoundException, EmptyCollectionException {
        System.out.println("getChildren");

        try {
            instance.getChildren(lloyd);
            fail("Should throw ElementNotFoundException");
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
            assertTrue(ex instanceof ElementNotFoundException);
        }

        ArrayList<Relative> result = instance.getChildren(a);
        assertTrue(result.contains(c));
        assertTrue(result.contains(b));
        assertTrue(result.contains(d));
        assertEquals(3, result.size());

        result = instance.getChildren(e);
        assertTrue(result.contains(g));
        assertTrue(result.contains(h));
        assertEquals(2, result.size());

        result = instance.getChildren(f);
        assertTrue(result.contains(j));
        assertTrue(result.contains(k));
        assertEquals(2, result.size());

        result = instance.getChildren(g);
        assertNull(result);

        result = instance.getChildren(h);
        assertNull(result);

        instance = new FamilyTree();

        assertTrue(instance.isEmpty());

        try {
            instance.getChildren(a);
            fail("Should throw EmptyCollectionException");
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
            assertTrue(ex instanceof EmptyCollectionException);
        }
    }


    /**
     * Test of getHeadOfFamily method, of class FamilyTreeBase.
     *
     * @throws Exceptions.EmptyCollectionException
     * @throws Exceptions.ElementNotFoundException
     */
    @Test
    public void testGetHeadOfFamily() throws EmptyCollectionException, ElementNotFoundException {
        System.out.println("getHeadOfFamily");
        
        assertEquals(13, instance.size());
        try {
            instance.getHeadOfFamily();
        } catch (EmptyCollectionException ex) {
            assertTrue(ex instanceof EmptyCollectionException);
        }
        
        assertEquals(a, instance.getHeadOfFamily());

    }

    /**
     * Test of getNumDescendants method, of class FamilyTreeBase.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetNumDescendants() throws EmptyCollectionException, ElementNotFoundException {
        System.out.println("getNumDescendants");
        
        assertEquals(13, instance.size());
        
        int numDes = instance.getNumDescendants(a);
        assertEquals(12, numDes);
        
        int aDes = instance.getNumDescendants(l);
        assertEquals(1, aDes);
        
        int mDes = instance.getNumDescendants(m);
        assertEquals(0, mDes);

    }

    /**
     * Test of getSortedSiblings method, of class FamilyTreeBase.
     *
     * @throws Exceptions.EmptyCollectionException
     * @throws Exceptions.ElementNotFoundException
     */
    @Test
    public void testGetSortedSiblings() throws EmptyCollectionException,
            ElementNotFoundException {
        System.out.println("getSortedSiblings");

        try {
            instance.getSortedSiblings(lloyd);
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
            assertTrue(ex instanceof ElementNotFoundException);
        }

        System.out.println(instance.toString());

        assertNull(instance.getSortedSiblings(a));

        ArrayList<Relative> siblings = instance.getSortedSiblings(b);

        assertEquals(siblings.get(0), c);
        assertEquals(siblings.get(1), d);

        siblings = instance.getSortedSiblings(e);
        assertEquals(siblings.get(0), f);
        assertEquals(siblings.get(1), i);
        assertEquals(siblings.get(2), l);

        siblings = instance.getSortedSiblings(k);
        assertEquals(siblings.get(0), j);

        assertNull(instance.getSortedSiblings(m));

        instance = new FamilyTree();

        assertTrue(instance.isEmpty());

        try {
            instance.getSortedSiblings(e);
            fail("Should Throw ECE");
        } catch (ElementNotFoundException | EmptyCollectionException ex) {
            assertTrue(ex instanceof EmptyCollectionException);
        }
    }

    
    /**
     * Test of toString method, of class FamilyTreeBase.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertNotNull(instance.toString());
    }

}