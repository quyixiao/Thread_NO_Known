package com.example.thread_no_known.contoller;

import com.example.thread_no_known.resp.ResponseResult;
import com.example.thread_no_known.utils.BeanUtils;
import com.example.thread_no_known.utils.Constant;
import com.example.thread_no_known.utils.t.TupleUtils;
import com.example.thread_no_known.utils.t.TwoTuple;
import com.linzi.RenderArgs;
import com.linzi.classloading.enhancers.LocalvariablesNamesEnhancer;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
public class BaseController {

    public ResponseResult responseSuccess(Object... args) {
        Map<String, Object> result = new HashMap();
        try {
            result = getResParam(args);
        } catch (Exception var12) {
            System.out.println("ServletUtils->toMap is exception");
        } finally {
            if (RenderArgs.current != null && RenderArgs.current.get() != null) {
                ((Map) RenderArgs.current.get()).clear();
            }
        }
        ResponseResult res = null;
        if (!result.isEmpty()) {
            result = voToMap(result);
            res = new ResponseResult(ResponseResult.success, ResponseResult.success_msg, result);
        } else {
            res = new ResponseResult(ResponseResult.success, ResponseResult.success_msg, null);
        }
        return res;
    }


    private Map getResParam(Object... args) {
        Map<String, Object> result = new HashMap();
        result = new HashMap(16);
        Object[] var6 = args;
        int var5 = args.length;
        for (int var4 = 0; var4 < var5; ++var4) {
            Object o = var6[var4];
            if (o instanceof TwoTuple) {
                Map<String, Object> mo = TupleUtils.tupleToMap((TwoTuple) o);
                for (Map.Entry<String, Object> entry : mo.entrySet()) {
                    result.put(entry.getKey(), entry.getValue());
                }
            } else {
                List<String> names = LocalvariablesNamesEnhancer.LocalVariablesNamesTracer.getAllLocalVariableNames(o);
                Iterator var9 = names.iterator();
                while (var9.hasNext()) {
                    String name = (String) var9.next();
                    result.put(name, o);
                }
            }
        }
        if (RenderArgs.current != null && RenderArgs.current.get() != null && !((Map) RenderArgs.current.get()).isEmpty()) {
            Iterator var14 = ((Map) RenderArgs.current.get()).entrySet().iterator();
            while (var14.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) var14.next();
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }



    private Map voToMap(Map<String, Object> result) {
        if (result.size() == 1) {
            String key = result.keySet().toArray()[0].toString();
            Object value = result.get(key);
            try {
                if (!(value instanceof java.util.List) && !value.getClass().isArray()) {
                    HashMap<String, Object> beanToMap = BeanUtils.beanToMap(value);
                    if (beanToMap.size() != 0 && !beanToMap.containsKey(Constant.EMPTY)) {
                        result = beanToMap;
                    }
                }
            } catch (Exception e) {
                log.error("======>返回参数异常");
            }
        }
        return result;
    }
}
