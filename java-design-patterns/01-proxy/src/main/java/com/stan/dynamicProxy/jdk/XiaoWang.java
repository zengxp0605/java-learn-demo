package com.stan.dynamicProxy.jdk;

import com.stan.dynamicProxy.Person;

/**
 * 被代理人
 */
public class XiaoWang implements Person {
    public Object findLover() {
        System.out.println("===我是小王，要找个女朋友。");
        return null;
    }
}
