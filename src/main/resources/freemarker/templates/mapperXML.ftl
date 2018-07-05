<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packageName}.mapper.${className}Mapper">
    <resultMap id="BaseResultMap" type="${packageName}.model.${className}">
        <#list properties as property>
            <#if property.primaryKey??>
        <id column="${property.columnName}" property="${property.name}" jdbcType="${property.jdbcType}"/>
            <#else>
                <#if !property.bigText??>
        <result column="${property.columnName}" property="${property.name}" jdbcType="${property.jdbcType}"/>
                </#if>
            </#if>
        </#list>
    </resultMap>
    <resultMap id="ResultMapWithBLOBs" type="${packageName}.model.${className}" extends="BaseResultMap">
        <#list properties as property>
            <#if property.bigText??>
    <result column="${property.columnName}" property="${property.name}" jdbcType="${property.jdbcType}"/>
            </#if>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <trim suffixOverrides=",">
            <#list properties as property>
                <#if !property.bigText??>
            ${property.columnName},
                </#if>
            </#list>
        </trim>
    </sql>

    <#if hasBigText??>
    <sql id="Blob_Column_List">
        <trim suffixOverrides=",">
    <#list properties as property>
        <#if property.bigText?? >
            ${property.columnName},
        </#if>
    </#list>
        </trim>
  </sql>
    </#if>
    <select id="count" resultType="int">
        select
        count(*)
        from ${tableName}
        <where>
            <#list properties as property>
                <#if !property.bigText??>
            <if test="${property.name}!=null and ${property.name}!=''">and ${property.columnName} = <#noparse>#</#noparse>{${property.name},jdbcType=${property.jdbcType}}</if>
                </#if>
            </#list>
        </where>
    </select>

    <select id="selectByPrimaryKey" resultMap="ResultMapWithBLOBs" parameterType="java.lang.Integer">
        select
        <include refid="Base_Column_List"/>
        <#if hasBigText??>
        ,
        <include refid="Blob_Column_List"/>
        </#if>
        from ${tableName}
        where ${primaryKey.columnName} = <#noparse>#</#noparse>{${primaryKey.name},jdbcType=${primaryKey.jdbcType}}
    </select>
    <select id="list" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${tableName}
        <where>
            <#list properties as property>
                <#if !property.bigText??>
            <if test="${property.name}!=null and ${property.name}!=''">and ${property.columnName} = <#noparse>#</#noparse>{${property.name},jdbcType=${property.jdbcType}}</if>
                </#if>
            </#list>
        </where>
        <choose>
            <when test="sort != null and sort.trim() !=''">
                order by <#noparse>$</#noparse>{${primaryKey.columnName}} <#noparse>$</#noparse>{order}
            </when>
            <otherwise>
                order by ${primaryKey.columnName} desc
            </otherwise>
        </choose>
        <if test="offset!=null and limit!=null">
            limit <#noparse>$</#noparse>{offset} , <#noparse>$</#noparse>{limit}
        </if>
    </select>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer">
        delete from ${tableName}
        where ${primaryKey.columnName} = <#noparse>#</#noparse>{${primaryKey.name},jdbcType=${primaryKey.jdbcType}}
    </delete>
    <delete id="batchRemove" parameterType="java.lang.Integer">
        delete from ${tableName}
        where ${primaryKey.columnName} in
        <foreach collection="array" item="${primaryKey.name}" open="(" separator=","  close=")">
            <#noparse>#</#noparse>{${primaryKey.name}}
        </foreach>
    </delete>
    <insert id="insert" parameterType="${packageName}.model.${className}" useGeneratedKeys="true" keyProperty="id">
    insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
         <#list properties as property>
            ${property.columnName},
         </#list>
        </trim>
        <trim prefix="values ("  suffix=")" suffixOverrides=",">
        <#list properties as property>
            <#noparse>#</#noparse>{${property.name},jdbcType=${property.jdbcType}},
        </#list>
        </trim>
  </insert>
    <insert id="insertSelective" parameterType="${packageName}.model.${className}" useGeneratedKeys="true" keyProperty="id">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list properties as property>
            <if test="${property.name}!=null ">${property.columnName},</if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list properties as property>
            <if test="${property.name}!=null "><#noparse>#</#noparse>{${property.name},jdbcType=${property.jdbcType}},</if>
            </#list>
        </trim>
    </insert>
    <update id="updateByPrimaryKeySelective" parameterType="${packageName}.model.${className}" useGeneratedKeys="true" keyProperty="id">
        update ${tableName}
        <set>
            <#list properties as property>
            <#if !property.primaryKey??>
            <if test="${property.name}!=null ">${property.columnName} = <#noparse>#</#noparse>{${property.name},jdbcType=${property.jdbcType}},</if>
            </#if>
            </#list>
        </set>
        where ${primaryKey.columnName} = <#noparse>#</#noparse>{${primaryKey.name},jdbcType=${primaryKey.jdbcType}}
    </update>
    <update id="updateByPrimaryKeyWithBLOBs" parameterType="${packageName}.model.${className}" useGeneratedKeys="true" keyProperty="id">
    update ${tableName}
    <set>
        <#list properties as property>
            <#if !property.primaryKey??>
        ${property.columnName} = <#noparse>#</#noparse>{${property.name},jdbcType=${property.jdbcType}},
            </#if>
        </#list>
    </set>
    where ${primaryKey.columnName} = <#noparse>#</#noparse>{${primaryKey.name},jdbcType=${primaryKey.jdbcType}}
  </update>
    <update id="updateByPrimaryKey" parameterType="${packageName}.model.${className}" useGeneratedKeys="true" keyProperty="${primaryKey.columnName}">
    update ${tableName}
    <set>
      <#list properties as property>
        <#if !property.primaryKey?? && !property.bigText??>
        ${property.columnName} = <#noparse>#</#noparse>{${property.name},jdbcType=${property.jdbcType}},
        </#if>
      </#list>
    </set>
    where ${primaryKey.columnName} = <#noparse>#</#noparse>{${primaryKey.name},jdbcType=${primaryKey.jdbcType}}
  </update>

</mapper>
