package com.sb.stu.npoi.common.exception;

/**
 * 编  号：
 * 名  称：ExcelException
 * 描  述：
 * 完成日期：2019/01/28 23:17
 *
 * @author：felix.shao
 */
public class ExcelException extends RuntimeException {

    public ExcelException() {
        super();
    }

    /** Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public ExcelException(String message) {
        super(message);
    }

}
