package com.godfather1103.error;

/**
 * <p>Title:        Godfather1103's GitHub</p>
 * <p>Copyright:    Copyright (c) 2018</p>
 * <p>Company:      https://github.com/godfather1103</p>
 *
 * @author 作者: admin E-mail: chuchuanbao@gmail.com
 * 创建时间：2018/10/31 15:44
 * @version 1.0
 * @since
 * 检测时根据情况向上抛出的异常类
 */
public class FailureException extends Exception{

    /**
     * 无参的构造函数<BR>
     * @author  作者: godfa E-mail: chuchuanbao@gmail.com
     * 创建时间：2018/11/3 22:58
     * @param
     * @return
     * @throws
     */
    public FailureException() {
        super("Commit Lint failed, please check rules");
    }

    /**
     * 带参数的构造函数<BR>
     * @author  作者: godfa E-mail: chuchuanbao@gmail.com
     * 创建时间：2018/11/3 22:59
     * @param  message 异常的文字说明
     * @return
     * @throws
     */
    public FailureException(String message) {
        super(message);
    }
}
