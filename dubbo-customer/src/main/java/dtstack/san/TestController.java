package dtstack.san;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 33
 * @time: 2019/10/23 23:32
 */

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public void sayDubbo(){
        testService.test();
    }

    @RequestMapping("/tests")
    @ResponseBody
    public String testString(String str){
        String s = testService.testString(str);
        return s;
    }

    @RequestMapping("/user")
    @ResponseBody
    public String getUser(@RequestParam("id") int id) {
       return testService.getUser(id);
    }

    @RequestMapping("/user/withschema")
    @ResponseBody
    public String getUsersWithSchema(@RequestParam("id") int id) {
        return testService.getUsersWithSchema(id);
    }
    @RequestMapping("/user/withoutschema")
    @ResponseBody
    public String getUsersWithoutSchema(@RequestParam("id") int id) {
        return testService.getUsersWithoutSchema(id);
    }

    @RequestMapping("/acm")
    @ResponseBody
    public String acmTestString(String str){
        String s = ACMTest.testAcm();
        return s;
    }

    @RequestMapping("/jvm/heaptest")
    @ResponseBody
    public String jvmTest() {
        class OOMObject{
            private static final int _1M = 1024 * 1024;
        }
        String s = "jvm processed,check the logs";
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
     }

    @RequestMapping("/jvm/stack/softest")
    @ResponseBody
    public String stackTest() {
        try {
            stackLeak();
        } catch (Throwable e) {
            System.out.println("obj stackLength:"+ this.stackLength);
        }
        return "jvm processed,check the logs";
    }

    private int stackLength = 1;
    private void stackLeak() {
        stackLength ++;
        stackLeak();
    }

    @RequestMapping("/jvm/stack/oomtest")
    @ResponseBody
    public String stackOOMTest() {
        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            }).start();
        }
    }

    private void dontStop() {
        while (true) {
        }
    }
}
