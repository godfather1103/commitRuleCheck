package ga.rugal.maven.plugin;

import com.godfather1103.app.RuleCheckApp;
import com.godfather1103.error.FailureException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RuleCheckerTest {

    @Test
    public void check() {
        try{
            String path = RuleCheckerTest.class.getClassLoader().getResource("rule.json").getPath();
            String s =  FileUtils.readFileToString(new File(path),"UTF-8");
            Type type = new TypeToken<ArrayList<RuleChecker>>(){}.getType();
            ArrayList<RuleChecker> ruleCheckers = new Gson().fromJson(s,type);
            for (RuleChecker ruleChecker:ruleCheckers){
                ruleChecker.check("chore(): 做了些修改");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void testApp(){
        try {
            new RuleCheckApp().check("做了些修改");
        } catch (FailureException e) {
            e.printStackTrace();
        }
    }
}