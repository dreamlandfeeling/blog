package com.xin.yxblog.utils;

import com.xin.yxblog.model.Property;
import com.xin.yxblog.model.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GeneratorUtils {

    private Boolean autoRemovePre;

    private String tablePrefix;

    private String filePath;

    private Properties properties;

    private static final String PACKAGE_NAME = "packageName";
    private static final String TABLE_NAME = "tableName";
    private static final String TABLE_COMMENT = "tableComment";
    private static final String GENERATOR_PROPERTIES = "generator.properties";

    private static final String TEMPLATE_MODEL = "model.ftl";
    private static final String TEMPLATE_MAPPER_INTERFACE = "MapperInterface.ftl";
    private static final String TEMPLATE_MAPPER_XML = "mapperXML.ftl";
    private static final String TEMPLATE_SERVICE_INTERFACE = "serviceInterface.ftl";
    private static final String TEMPLATE_SERVICE_IMPL = "serviceImpl.ftl";
    private static final String TEMPLATE_CONTROLLER = "controller.ftl";

    private static final String COMMON_PAGE = "Page.ftl";
    private static final String COMMON_RESULT = "Result.ftl";

    public GeneratorUtils() throws IOException {
        loadPeoperties();
    }

    public void loadPeoperties() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream(GENERATOR_PROPERTIES));
        this.autoRemovePre = Boolean.valueOf(properties.getProperty("autoRemovePre"));
        this.tablePrefix = properties.getProperty("tablePrefix");
        this.filePath = properties.getProperty("filePath");
        this.properties = properties;

    }

    public void generatorCode(Map<String, String> tableInfo, List<Map<String, String>> listColumn, ZipOutputStream zip) throws IOException, TemplateException {
        Table table = generatorTabe(tableInfo, listColumn);
        generatorModel(table, zip);
        generatorMapperInterface(table, zip);
        generatorMapperXML(table, zip);
        generatorServiceInterface(table, zip);
        generatorServiceImpl(table, zip);
        generatorController(table, zip);
    }

    public Table generatorTabe() {
        Table table = new Table();
        table.setPackageName(properties.getProperty(PACKAGE_NAME));
        return table;
    }


        /**
         * 整理数据库表属性
         *
         * @param tableInfo
         * @param listColumn
         * @return
         */
    public Table generatorTabe(Map<String, String> tableInfo, List<Map<String, String>> listColumn) {
        Table table = generatorTabe();
        String tableName = tableInfo.get(TABLE_NAME);
        table.setTableName(tableName);
        if (autoRemovePre) {
            int index = tableName.indexOf(tablePrefix);
            if(index>=0){
                tableName = tableName.substring(index+tablePrefix.length());
            }
            table.setClassName((firstLetterCap(tableName)));
        } else {
            table.setClassName((firstLetterCap(tableName)));
        }
        table.setClassname(firstLetterUncap(table.getClassName()));
        table.setClassComment(tableInfo.get(TABLE_COMMENT));
        List<Property> propertiesList = new ArrayList<>();
        for (Map<String, String> map : listColumn) {
            Property property = new Property();
            String isPrimary = map.get("columnKey");
            if (checkBigText(map.get("dataType"))) {
                property.setBigText(true);
                table.setHasBigText(true);
            }
            property.setType(getTypeByProperties(map.get("dataType")));
            property.setJdbcType(getTypeByProperties("mysql_"+map.get("dataType")));
            property.setName(columnToProperty(map.get("columnName")));
            property.setColumnName(map.get("columnName"));
            property.setComment(map.get("columnComment"));
            if ("PRI".equals(isPrimary)) {
                property.setPrimaryKey(true);
                table.setPrimaryKey(property);
            }
            propertiesList.add(property);
        }
        table.setProperties(propertiesList);
        return table;
    }


    public void generatorController(Table table, ZipOutputStream zip) throws IOException, TemplateException {
        zip.putNextEntry(new ZipEntry(createZipFileDirPath(table, "controller") + table.getClassName() + "Controller.java"));
        dataWriteTemplate(table, zip, TEMPLATE_CONTROLLER);
    }

    public void generatorServiceInterface(Table table, ZipOutputStream zip) throws IOException, TemplateException {
        zip.putNextEntry(new ZipEntry(createZipFileDirPath(table, "service") + table.getClassName() + "Service.java"));
        dataWriteTemplate(table, zip, TEMPLATE_SERVICE_INTERFACE);
    }

    public void generatorServiceImpl(Table table, ZipOutputStream zip) throws IOException, TemplateException {
        zip.putNextEntry(new ZipEntry(createZipFileDirPath(table, "service/impl") + table.getClassName() + "ServiceImpl.java"));
        dataWriteTemplate(table, zip, TEMPLATE_SERVICE_IMPL);
    }

    public void generatorModel(Table table, ZipOutputStream zip) throws IOException, TemplateException {
        zip.putNextEntry(new ZipEntry(createZipFileDirPath(table, "model") + table.getClassName() + ".java"));
        dataWriteTemplate(table, zip, TEMPLATE_MODEL);
    }

    public void generatorMapperInterface(Table table, ZipOutputStream zip) throws IOException, TemplateException {
        zip.putNextEntry(new ZipEntry(createZipFileDirPath(table, "mapper") + table.getClassName() + "Mapper.java"));
        dataWriteTemplate(table, zip, TEMPLATE_MAPPER_INTERFACE);
    }

    public void generatorMapperXML(Table table, ZipOutputStream zip) throws IOException, TemplateException {
        zip.putNextEntry(new ZipEntry(createZipFileDirPath(table, "mapper") + table.getClassName() + "Mapper.xml"));
        dataWriteTemplate(table, zip, TEMPLATE_MAPPER_XML);
    }

    public void generatorCommonCode(ZipOutputStream zip) throws IOException, TemplateException {
        String path = "common/";
        Table table = generatorTabe();
        zip.putNextEntry(new ZipEntry(createZipFileDirPath(table, "dto") + COMMON_PAGE ));
        dataWriteTemplate(table, zip, path+COMMON_PAGE);
        zip.putNextEntry(new ZipEntry(createZipFileDirPath(table, "dto") + COMMON_RESULT));
        dataWriteTemplate(table, zip, path+COMMON_RESULT);
    }

    /**
     * 数据注入模板
     * @param table 数据集
     * @param zip   输出流
     * @param templateName 模板名字
     * @throws IOException
     * @throws TemplateException
     */
    public void dataWriteTemplate(Table table, ZipOutputStream zip, String templateName) throws IOException, TemplateException {
        StringWriter writer = new StringWriter();
        Template template = getTemplate(templateName);
        template.process(table, writer);
        IOUtils.write(writer.toString(), zip, "utf-8");
        IOUtils.closeQuietly(writer);
        zip.closeEntry();
    }

    /**
     * 获得freemarker模板
     *
     * @param templateName
     * @return
     * @throws IOException
     */
    public Template getTemplate(String templateName) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        String templatePath = FileUtils.getResourcePath("freemarker/templates");
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        configuration.setDefaultEncoding("utf-8");
        return configuration.getTemplate(templateName);
    }

    /**
     * 返回zip里文件路径，并且创建zip保存位置
     * @param table
     * @param codeType
     * @return
     */
    public String createZipFileDirPath(Table table, String codeType) {
        File file = null;
        String result = "";
        if (StringUtils.isNotBlank(table.getPackageName())) {
            file = new File(filePath + packageNameToPackageURL(table.getPackageName()) + "/" + codeType + "/");
            result =  packageNameToPackageURL(table.getPackageName()) + "/" + codeType + "/";
        } else {
            file = new File(filePath + codeType + "/");
            result =  codeType + "/";
        }
        if (!file.exists()) {
            file.mkdirs();
        }

        return result;
    }

    /**
     * 将数据库列名转换成java属性名
     *
     * @param column
     * @return
     */
    public static String columnToProperty(String column) {
        char[] chars = column.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == '_') {
                if (i + 1 < len) {
                    chars[i + 1] -= 32;
                }
            }
        }
        column = String.valueOf(chars);
        return column.replace("_", "");
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public String firstLetterCap(String str) {
        char[] chars = str.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }
    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public String firstLetterUncap(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public String packageNameToPackageURL(String packageName) {
        return packageName.replace(".", "/");
    }

    public Boolean checkBigText(String jdbcType) {
        if ("BLOB".equalsIgnoreCase(jdbcType)) {
            return true;
        } else if ("TEXT".equalsIgnoreCase(jdbcType)) {
            return true;
        } else if ("MEDIUMBLOB".equalsIgnoreCase(jdbcType)) {
            return true;
        } else if ("MEDIUMTEXT".equalsIgnoreCase(jdbcType)) {
            return true;
        }
        return false;
    }

    public String getTypeByProperties(String key){
        String property = properties.getProperty(key);
        if(StringUtils.isBlank(property)){
            return "Other";
        }
        return property;
    }
}
