package ${packageName}.service;

import ${packageName}.model.${className};

import java.util.List;
import java.util.Map;

public interface ${className}Service {

    ${className} get(int ${primaryKey.name});

    int add(${className} ${classname});

    List<${className}> list(Map map);

    int count(Map map);

    int delete(int ${primaryKey.name});

    int update(${className} ${classname});

    int batchDelete(int[] ${primaryKey.name}s);
}