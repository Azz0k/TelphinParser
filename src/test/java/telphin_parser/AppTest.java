package telphin_parser;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * Unit test for simple App.
 */
public class AppTest

{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */


    public void testGetAuthToken() {
    }
    @Test
    public void testValidateCallNumber() {
        boolean result=App.validateCallNumber("111");
        assertThat(result,is(false));
        result=App.validateCallNumber("11*1");
        assertThat(result,is(false));
        result=App.validateCallNumber("111111");
        assertThat(result,is(true));
    }
}
