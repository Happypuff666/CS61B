package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> Expect = new AListNoResizing<>();
        BuggyAList<Integer> Actual = new BuggyAList<>();

        for (int i = 0; i < 3; i++) {
            Expect.addLast(i + 3);
            Actual.addLast(i + 3);

        }

        assertEquals(Expect.size(), Actual.size());

        for (int i = 0; i < 3; i++) {
            assertEquals(Expect.removeLast(), Actual.removeLast());
        }
    }

        @Test
        public void randomizedTest(){
            AListNoResizing<Integer> L = new AListNoResizing<>();
            BuggyAList<Integer>L_bug=new BuggyAList<>();

            int N = 5000;
            for (int i = 0; i < N; i += 1) {
                int operationNumber = StdRandom.uniform(0, 4);
                if (operationNumber == 0) {
                    // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    L.addLast(randVal);
                    L_bug.addLast(randVal);
                    //System.out.println("addLast(" + randVal + ")");
                } else if (operationNumber == 1) {
                    // getLast
                    if(L.size()!=0) {
                        //System.out.println("getLast:" + L.getLast());
                        assertEquals(L.getLast(), L_bug.getLast());
                    }
                }else if (operationNumber == 2) {
                    // removeLast
                    if(L.size()!=0) {
                        int temp=L.removeLast();
                        int temp1=L_bug.removeLast();
                        //System.out.println("removeLast:" + temp);
                        assertEquals(temp, temp1);
                    }
                }else if (operationNumber == 3) {
                    // size
                    int size = L.size();
                    int size_bug = L_bug.size();
                    //System.out.println("size: " + size);
                    assertEquals(size, size_bug);
                }
            }




    }
}
