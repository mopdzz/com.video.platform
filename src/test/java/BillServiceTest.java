import com.video.platform.entity.Bill;
import com.video.platform.entity.BillCondition;
import com.video.platform.service.BillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * User: yangl
 * Date: 13-7-29 下午10:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class BillServiceTest {
    @Autowired
    private BillService billService;

    @Test
    public void billTest() throws IOException {
        billService.process("2013-09-01", "2013-09-31");
    }

    @Test
    public void find(){
        BillCondition condition = new BillCondition();
        condition.setCpId(0);
        condition.setBtime("2013-07-01");
        condition.setEtime("2013-08-09");
        List<Bill> list = billService.find(condition);
        for(Bill bill : list){
            System.out.println(bill);
        }
    }
}
