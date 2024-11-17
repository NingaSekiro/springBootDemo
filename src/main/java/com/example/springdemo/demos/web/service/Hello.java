package com.example.springdemo.demos.web.service;


import javassist.*;

import java.io.IOException;

public class Hello {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath("instrumented-MyBatisCodeHelper-Pro241-3.3.5+2321");
//        pool.appendClassPath("./instrumented-MyBatisCodeHelper-Pro241-3.3.5+2321.jar");

        CtClass ctClass = pool.get("com.ccnode.codegenerator.af.f.e");
        CtMethod parseLicenseMethod = ctClass.getDeclaredMethod("a", new CtClass[] {pool.get("java.lang.String")});
        StringBuffer bodyBuff = new StringBuffer();
        bodyBuff.append("{")
                .append("	com.google.gson.Gson gson = new com.google.gson.Gson();")
                .append("	com.ccnode.codegenerator.af.d.f e = (com.ccnode.codegenerator.af.d.f)gson.fromJson($1,com.ccnode.codegenerator.af.d.f.class);")
                .append("	return e;")
                .append("}");
        parseLicenseMethod.setBody(bodyBuff.toString());

        // 获取 valid 字段
//        CtField field = ctClass.getDeclaredField("valid");

        // 移除原有的字段
//        ctClass.removeField(field);

        // 创建新的字段，并设置初始值为 true
//        CtField newField = new CtField(CtClass.booleanType, "valid", ctClass);
//        newField.setModifiers(Modifier.PRIVATE);
//        ctClass.addField(newField, "true");

        // 将修改后的类写入文件
        ctClass.writeFile("javassistout");
    }
}
