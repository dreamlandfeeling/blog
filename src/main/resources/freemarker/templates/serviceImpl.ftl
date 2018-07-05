package ${packageName}.service.impl;

import ${packageName}.mapper.${className}Mapper;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class ${className}ServiceImpl implements ${className}Service{
    @Autowired
    private ${className}Mapper ${classname}Mapper;


    @Override
    public ${className} get(int ${primaryKey.name}) {
        return ${classname}Mapper.selectByPrimaryKey(${primaryKey.name});
    }

    @Override
    public int add(${className} ${classname}) {
        return ${classname}Mapper.insert(${classname});
    }

    @Override
    public List<${className}> list(Map map) {
        return ${classname}Mapper.list(map);
    }

    @Override
    public int count(Map map) {
        return ${classname}Mapper.count(map);
    }

    @Override
    public int delete(int ${primaryKey.name}) {
        return ${classname}Mapper.deleteByPrimaryKey(${primaryKey.name});
    }

    @Override
    public int update(${className} ${classname}) {
        return ${classname}Mapper.updateByPrimaryKeySelective(${classname});
    }

    @Override
    public int batchDelete(int[] ${primaryKey.name}s) {
        return ${classname}Mapper.batchRemove(${primaryKey.name}s);
    }
}