package com.xin.yxblog.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface GeneratorMapper {
    @Select("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables \n" +
            "where table_schema = (select database()) and table_name = #{tableName}")
    Map<String,String> get(String tableName);

    @Select("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns \n" +
            "where table_name = #{tableName} and table_schema = (select database()) order by ordinal_position ")
    List<Map<String,String>> listColumn(String tableName);

    @Select("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables \n" +
            "where table_schema = (select database()) limit ${offset},${limit}")
    List<Map<String,String>> listTables(Map map);

    @Select("select count(*) from information_schema.tables where table_schema = (select database())")
    int count();


}
