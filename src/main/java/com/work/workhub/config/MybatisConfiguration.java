package com.work.workhub.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.work.workhub", annotationClass = Mapper.class)
public class MybatisConfiguration {

}
