package com.xin.yxblog.test;

public class ResourceFactory {

    private ResourceFactory() {
    }

    private static class ResourceHolder {
        public static ResourceFactory resource = new ResourceFactory();
    }
    public static ResourceFactory getResource() {
        return ResourceHolder.resource ;
    }
}
