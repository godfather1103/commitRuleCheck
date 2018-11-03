package com.godfather1103.app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ga.rugal.maven.plugin.RuleChecker;
import com.godfather1103.error.FailureException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * <p>Title:        Godfather1103's Github</p>
 * <p>Copyright:    Copyright (c) 2018</p>
 * <p>Company:      https://github.com/godfather1103</p>
 *
 * @author 作者: godfa E-mail: chuchuanbao@gmail.com
 * 创建时间：2018/11/3 22:55
 * @version 1.0
 * @since 检测工具的入口
 */
public class RuleCheckApp {

    ArrayList<RuleChecker> ruleCheckers;

    /**
     * 无参的构造函数（缺省加载内置的rule.json）<BR>
     *
     * @throws FailureException 相关构造函数的异常
     * @author 作者: godfa E-mail: chuchuanbao@gmail.com
     * 创建时间：2018/11/3 22:54
     */
    public RuleCheckApp() throws FailureException {
        try {
            String s = getStrFromSteam(RuleCheckApp.class.getResourceAsStream("/rule.json"), "UTF-8");
            Type type = new TypeToken<ArrayList<RuleChecker>>() {
            }.getType();
            this.ruleCheckers = new Gson().fromJson(s, type);
        } catch (IOException ex) {
            throw new FailureException(ex.getMessage());
        }
    }

    /**
     * 读取输入流中的数据转成字符串返回<BR>
     *
     * @param in      输入流
     * @param charset 字符串的编码方式
     * @return 从输入流中读取到的字符串数据
     * @throws IOException IO相关的异常
     * @author 作者: godfa E-mail: chuchuanbao@gmail.com
     * 创建时间：2018/11/3 22:52
     */
    public String getStrFromSteam(InputStream in, String charset) throws IOException {
        StringBuffer sb = new StringBuffer();
        byte[] bytes = new byte[1024];
        if (in != null) {
            int len = in.read(bytes);
            sb.append(new String(bytes, 0, len, charset));
        }
        return sb.toString();
    }

    /**
     * 构造函数（加载传入的配置文件地址）<BR>
     *
     * @param configFilePath 配置文件路径
     * @throws FailureException 相关构造函数的异常
     * @author 作者: godfa E-mail: chuchuanbao@gmail.com
     * 创建时间：2018/11/3 22:56
     */
    public RuleCheckApp(String configFilePath) throws FailureException {
        try {
            String s = FileUtils.readFileToString(new File(configFilePath), "UTF-8");
            Type type = new TypeToken<ArrayList<RuleChecker>>() {
            }.getType();
            this.ruleCheckers = new Gson().fromJson(s, type);
        } catch (IOException ex) {
            throw new FailureException(ex.getMessage());
        }
    }

    /**
     * 检测方法<BR>
     *
     * @param text 被检测的结果
     * @throws FailureException 相关检测的异常
     * @author 作者: godfa E-mail: chuchuanbao@gmail.com
     * 创建时间：2018/11/3 22:57
     */
    public void check(String text) throws FailureException {
        if (this.ruleCheckers == null) {
            throw new FailureException("相关规则配置文件不存在！");
        } else {
            for (RuleChecker ruleChecker : this.ruleCheckers) {
                ruleChecker.check(text);
            }
        }
    }
}
