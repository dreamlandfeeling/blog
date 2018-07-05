package ${packageName}.model;

<#list properties as property>
    <#if property.type=='Date'>
import java.util.Date;
        <#break >
    </#if>
</#list>

<#if classComment?? && classComment != "" >
/**
 * ${classComment}
 */
</#if>
public class ${className} {
    <#list properties as property>

    <#if property.comment?? && property.comment!="" >
    /**
     * ${property.comment}
     */
    </#if>
    private ${property.type} ${property.name};
    </#list>

    public ${className}(){}

    <#list properties as property>
    public ${property.type} get${property.name?cap_first}(){
        return ${property.name};
    }

    public void set${property.name?cap_first}(${property.type} ${property.name}){
        this.${property.name} = ${property.name};
    }

    </#list>

}