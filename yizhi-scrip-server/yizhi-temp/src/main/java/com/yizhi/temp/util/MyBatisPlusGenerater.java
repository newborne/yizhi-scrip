package com.yizhi.temp.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * The type My batis plus generater.
 */
public class MyBatisPlusGenerater {
    /**
     * The Package name.
     */
    static String packageName = "com.yizhi.temp"; // 当前包名
    /**
     * The Author.
     */
    static String author = "newborne"; // 作者
    /**
     * The Sql url.
     */
    static String sqlUrl = "mysql://centos:3306/"; // 数据库类型及地址
    /**
     * The Sql db.
     */
    static String sqlDb = "yizhi_scrip"; // 数据库名
    /**
     * The Sql user.
     */
    static String sqlUser = "root";
    /**
     * The Sql password.
     */
    static String sqlPassword = "Bujidao0";
    /**
     * The Table.
     */
    static String table = "ap_user,ap_user_info"; // 表，用逗号隔开
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:" + sqlUrl + sqlDb + "?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(sqlUser);
        dsc.setPassword(sqlPassword);
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageName);
        pc.setEntity("pojo");
        mpg.setPackageInfo(pc);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null); // 不生成MapperXML
        mpg.setTemplate(templateConfig);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(table.split(","));
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix("ap_"); // 表前缀，如t_user，没有就注释掉此行
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}