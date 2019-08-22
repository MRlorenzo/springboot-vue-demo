package org.spmul.web.log.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrUtil {

    public static String errToString(Throwable e){

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
