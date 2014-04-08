import org.joda.time.DateTime;
import org.junit.Test;

/**
 * User: yangl
 * Date: 13-8-9 下午9:23
 */
public class CommonTest {

    @Test
    public void dateTime(){
        DateTime dateTime = new DateTime().minusDays(1);
        System.out.println(dateTime.toString("yyyy-MM-dd"));
    }
}
