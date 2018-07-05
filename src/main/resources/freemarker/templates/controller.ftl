package ${packageName}.controller;

import ${packageName}.dto.Page;
import ${packageName}.dto.Result;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("")
@Controller
public class ${className}Controller {
    @Autowired
    private ${className}Service ${classname}Service;

    @ResponseBody
    @GetMapping("/${classname}/{${primaryKey.name}}")
    public Result get${classname}ToEdit(@PathVariable int ${primaryKey.name},Model model){
        ${className} ${classname} = ${classname}Service.get(${primaryKey.name});
        return Result.ok(${classname});
    }

    @ResponseBody
    @GetMapping("/${classname}/list")
    public Page list(@RequestParam Map<String,Object> map){
        List<${className}> list = ${classname}Service.list(map);
        int total = ${classname}Service.count(map);
        Page<${className}> page = new Page<>(total, list);
        return page;
    }

    @ResponseBody
    @PostMapping("/${classname}")
    public Result save(${className} ${classname}){
        int result = ${classname}Service.add(${classname});
        if(result >0){
            return Result.ok();
        }
        return Result.error("保存失败");
    }

    @ResponseBody
    @PutMapping("/${classname}/{${primaryKey.name}}")
    public Result update(${className} ${classname}){
        int result = ${classname}Service.update(${classname});
        if(result >0){
            return Result.ok();
        }
        return Result.error("更新失败");
    }

    @ResponseBody
    @DeleteMapping("/${classname}/{${primaryKey.name}}")
    public Result delete(@PathVariable int ${primaryKey.name}){
        if(${classname}Service.delete(${primaryKey.name})>0){
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    @ResponseBody
    @DeleteMapping("/${classname}/batchRemove")
    public Result batchDelete(@RequestParam(value = "${primaryKey.name}s[]") int[] ${primaryKey.name}s){
        if(${classname}Service.batchDelete(${primaryKey.name}s)==${primaryKey.name}s.length){
            return Result.ok();
        }
        return Result.error("批量删除失败");
    }

}
