package com.xin.yxblog.mapper;

import com.xin.yxblog.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface StudentMapper {
    List<Student> list(Map map);

    int count(Map map);

    int deleteByPrimaryKey(Integer id);

    int insert(Student student);

    int insertSelective(Student student);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student student);

    int updateByPrimaryKeyWithBLOBs(Student student);

    int updateByPrimaryKey(Student student);

    int batchRemove(int[] ids);
}