package com.chuchuanbao.error;

/**
 * <p>Title:        TRS WCM</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company:      www.trs.com.cn</p>
 *
 * @author 作者: admin E-mail: chu.chuanbao@trs.com.cn
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
