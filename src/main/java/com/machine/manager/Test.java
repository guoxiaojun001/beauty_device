package com.machine.manager;


import com.machine.manager.json.JsonRootBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 *     author : guoxiaojun5
 *     e-mail : guoxiaojun5@jd.com
 *     time   : 2021/07/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class Test {
    public static void main(String[] args) {
        List<JsonRootBean> list = new ArrayList<>();
        JsonRootBean d;

        for(int a = 0; a< 5; a++){
            d = new JsonRootBean();
            System.out.println("d== " + d);
            if(!list.contains(d)){
                list.add(d);
            }

        }
        System.out.println("list== " + list.size());
    }
}
