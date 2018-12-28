package com.backup.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HttpClient工具类
 *
 * @author 曾繁添
 * @version 1.0
 */
@Slf4j
public class HttpClientUtil
{
    public final static String Method_POST = "POST";
    public final static String Method_GET = "GET";
    public final  static RequestConfig config = RequestConfig.custom().setConnectTimeout(90000).build();
    /**
     * multipart/form-data类型的表单提交
     *
     * @param form
     *  表单数据
     */
    public static String submitForm(MultipartForm form)
    {
        // 返回字符串
        String responseStr = "";
        // 创建HttpClient实例
        HttpClient httpClient = new DefaultHttpClient();
        try
        {
            // 实例化提交请求
            HttpPost httpPost = new HttpPost(form.getAction());
            // 创建MultipartEntityBuilder
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            // 追加普通表单字段
            Map<String, String> normalFieldMap = form.getNormalField();
            for (Iterator<Entry<String, String>> iterator = normalFieldMap.entrySet().iterator(); iterator.hasNext();)
            {
                Entry<String, String> entity = iterator.next();
                entityBuilder.addPart(entity.getKey(), new StringBody(entity.getValue(), ContentType.create("text/plain", Consts.UTF_8)));
            }
            // 追加文件字段
            Map<String,  List<File>> fileFieldMap = form.getFileField();
            for (Iterator<Entry<String,  List<File>>> iterator = fileFieldMap.entrySet().iterator(); iterator.hasNext();)
            {
                Entry<String,  List<File>> entity = iterator.next();
                for ( File item:entity.getValue())
               {
                   entityBuilder.addPart(entity.getKey(), new FileBody(item));
                }
            }
            // 设置请求实体
            httpPost.setEntity(entityBuilder.build());
            // 发送请求
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            // 取得响应数据
            HttpEntity resEntity = response.getEntity();
            if (200 == statusCode)
            {
                if (resEntity != null)
                {
                    responseStr = EntityUtils.toString(resEntity);
                }
            }
        } catch (Exception e)
        {
           log.error ("提交表单失败，原因：" + e.getMessage());
        } finally
        {
            httpClient.getConnectionManager().shutdown();
        }
        return responseStr;
    }
    /** 表单字段Bean */
    public class MultipartForm implements Serializable
    {
        /** 序列号 */
        private static final long serialVersionUID = -2138044819190537198L;
        /** 提交URL **/
        private String action = "";
        /** 提交方式：POST/GET **/
        private String method = "POST";
        /** 普通表单字段 **/
        private Map<String, String> normalField = new LinkedHashMap<String, String>();
        /** 文件字段 **/
        private Map<String, List<File>> fileField = new LinkedHashMap<String, List<File>>();
        public String getAction()
        {
            return action;
        }
        public void setAction(String action)
        {
            this.action = action;
        }
        public String getMethod()
        {
            return method;
        }
        public void setMethod(String method)
        {
            this.method = method;
        }
        public Map<String, String> getNormalField()
        {
            return normalField;
        }
        public void setNormalField(Map<String, String> normalField)
        {
            this.normalField = normalField;
        }
        public Map<String,  List<File>> getFileField()
        {
            return fileField;
        }
        public void setFileField(Map<String,  List<File>> fileField)
        {
            this.fileField = fileField;
        }

        public void addFileField(String key,  List<File> value)
        {
            fileField.put(key, value);
        }
        public void addNormalField(String key, String value)
        {
            normalField.put(key, value);
        }
    }

    public static boolean sendGet(String url) throws ParseException, IOException {

        boolean result = false;
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        log.info("状态码：" + statusCode);
        if(statusCode == 200){
            log.info("访问成功");
            result = true;
        }

        httpClient.close();
        return result;
    }
}

