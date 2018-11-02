package com.chuchuanbao.App;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ga.rugal.maven.plugin.RuleChecker;
import com.chuchuanbao.error.FailureException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RuleCheckApp {

    ArrayList<RuleChecker> ruleCheckers = null;

    public RuleCheckApp() throws FailureException {
        try {
            String s = getStrFromSteam(RuleCheckApp.class.getResourceAsStream("/rule.json"),"UTF-8");
            Type type = new TypeToken<ArrayList<RuleChecker>>(){}.getType();
            this.ruleCheckers = new Gson().fromJson(s,type);
        }catch (IOException ex){
            throw new FailureException(ex.getMessage());
        }
    }

    public String getStrFromSteam(InputStream in,String charset) throws IOException {
        StringBuffer sb = new StringBuffer();
        byte [] bytes = new byte[1024];
        if (in!=null){
            int len = in.read(bytes);
            sb.append(new String(bytes,0,len,charset));
        }
        return sb.toString();
    }

    public RuleCheckApp(String configFilePath) throws FailureException {
        try {
            String s =  FileUtils.readFileToString(new File(configFilePath),"UTF-8");
            Type type = new TypeToken<ArrayList<RuleChecker>>(){}.getType();
            this.ruleCheckers = new Gson().fromJson(s,type);
        }catch (IOException ex){
            throw new FailureException(ex.getMessage());
        }
    }

    public void check(String text) throws FailureException {
        if (this.ruleCheckers==null){
            throw new FailureException("相关规则配置文件不存在！");
        }else{
            for (RuleChecker ruleChecker:this.ruleCheckers){
                ruleChecker.check(text);
            }
        }
    }
}
