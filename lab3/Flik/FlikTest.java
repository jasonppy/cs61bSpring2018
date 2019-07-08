import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {
    /** Test the Flik library
     *
     */

    @Test
    public void TestFlik() {
        int i = 128;
        int j = 128;
        assertTrue(Flik.isSameNumber(i,j));
    }


    public static void main(String[] args) {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (i != j) {
                break; // break exits the for loop!
            }
        }
        System.out.println("i is " + i);
    }

}


