package ga.rugal.maven.plugin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CaptureGroupTest {

    @Test
    public void testGson(){
        String json = "[{\"caseFormat\":\"UPPERCASE\",\"max\":200,\"min\":10},{\"caseFormat\":\"LOWERCASE\",\"min\":10}]";
        Type type = new TypeToken<ArrayList<CaptureGroup>>(){}.getType();
        ArrayList<CaptureGroup> list = new Gson().fromJson(json,type);
        for (CaptureGroup a:list){
            System.out.println(a.getCaseFormat().name());
            System.out.println(a.getMax());
            System.out.println(a.getMin());
            System.out.println("======================");
        }
    }

}