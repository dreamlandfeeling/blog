package ${packageName}.mapper;

import ${packageName}.model.${className};
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ${className}Mapper {
    List<${className}> list(Map map);

    int count(Map map);

    int deleteByPrimaryKey(Integer id);

    int insert(${className} ${classname});

    int insertSelective(${className} ${classname});

    ${className} selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(${className} ${classname});

    int updateByPrimaryKeyWithBLOBs(${className} ${classname});

    int updateByPrimaryKey(${className} ${classname});

    int batchRemove(int[] ids);
}