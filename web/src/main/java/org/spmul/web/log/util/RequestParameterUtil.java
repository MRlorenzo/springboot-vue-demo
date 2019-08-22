package org.spmul.web.log.util;


import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class RequestParameterUtil {

    /**
     * HttpServletRequest & HttpServletResponse 对象处理
     * @param obj 方法参数
     * @return String
     */
    public static String getObjectJsonString(Object obj){
        if( obj instanceof HttpServletRequest){
            HttpServletRequest req = (HttpServletRequest)obj;
            Map<String, String[]> map = req.getParameterMap();
            return JSON.toJSONString(map);
        }else if(obj instanceof HttpServletResponse){
            return "{}";
        }else {
            return  JSON.toJSONString(obj);
        }
    }

    /**
     * HttpServletRequest & HttpServletResponse 对象处理
     * @param args 方法参数
     * @return String
     */
    public static String getJsonArrayString( Object[] args){
        if(args.length < 2 ){
            return getObjectJsonString(args);
        }
        StringBuilder sb = new StringBuilder("[");
        for (Object obj:args){
            sb.append(getObjectJsonString(obj)).append(",");
        }
        return sb.substring(0, sb.length() - 1) + "]";
    }


    /**
     * 根据请求方法参数获取请求参数json字符串
     * @param args 请求参数
     * @return String
     */
    public static String getParams(Object[] args){
        try {
            String params;
            if(args == null){
                params = "";
            }else if (args.length == 1){
                params = RequestParameterUtil.getObjectJsonString(args[0]);
            }else {
                params = RequestParameterUtil.getJsonArrayString(args);
            }
            return params;
        }catch (Exception e2){
            return ErrUtil.errToString(e2);
        }
    }

}
