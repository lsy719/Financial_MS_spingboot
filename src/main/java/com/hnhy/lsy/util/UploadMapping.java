package com.hnhy.lsy.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//对于上传文件的存储路径，需要定义类进行单独配置（重写） 右键-> generate -> override methods
@Configuration
public class UploadMapping implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        设置请求路径和文件物理路径。（将文件存储到e盘那个路径后，网页通过account_img这个路径去访问）
//        registry.addResourceHandler("/accountimg/**").addResourceLocations("file:e:/Financial_MS/img/account_img/");
        registry.addResourceHandler("/accountimg/**").addResourceLocations("file:/root/fms_img/account_img/");
//        D:\study\proj_img\   图片存在proj_img目录下，所以后面要加上【/】 E:\Financial_MS\img\account_img
//        registry.addResourceHandler("/bankimg/**").addResourceLocations("file:e:/Financial_MS/img/bank_img/");
        registry.addResourceHandler("/bankimg/**").addResourceLocations("file:/root/fms_img/bank_img/");
    }
}
