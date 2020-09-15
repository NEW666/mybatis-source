package com.my.project.mybatis;

import com.my.project.mybatis.mapper.UserMapper;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MybatisApplication {

    public static void main(String[] args) {

        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(MybatisApplication.class.getClassLoader(),new Class<?>[]{UserMapper.class}, (proxy, method, args1) -> {
            Select annotation = method.getAnnotation(Select.class);
            Map<String, Object> nameArgMap = buildMethodArgNameMap(method, args1);
            if(annotation != null) {
                String[] value = annotation.value();
                System.out.println(Arrays.toString(value));
            }
            return null;
        });
        userMapper.selectUserList(1L,"jerry");

    }

    public static String parseSQL(String sql,Map<String,Object> nameArgs){
        return null;
    }



    public static Map<String,Object> buildMethodArgNameMap(Method method, Object[] args){
        Map<String,Object> nameArgMap = new ConcurrentHashMap<>();
        Parameter[] parameters = method.getParameters();
        int[] index = {0};
        Arrays.asList(parameters).parallelStream().forEach(parameter -> {
            String name = parameter.getName();
            nameArgMap.put(name,args[index[0]++]);
        });
        return nameArgMap;
    }

}
