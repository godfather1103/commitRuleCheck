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
 */
public class FailureException extends Exception{
    public FailureException() {
        super("Commit Lint failed, please check rules");
    }

    public FailureException(String message) {
        super(message);
    }
}
