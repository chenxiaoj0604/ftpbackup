package com.backup.services.impl;

import com.backup.domain.Rule;
import com.backup.services.BackupService;
import com.backup.utils.DateUtils;
import com.backup.utils.FtpTool;
import com.backup.utils.HttpClientUtil;
import com.backup.utils.R;
import com.backup.webservice.WebService1Soap;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.backup.utils.FileUtils.DeleteFolder;
import static com.backup.utils.FtpTool.*;

/**
 * Created by duanxun on 2018-12-26.
 */
@Service
@Slf4j
public class BackupServiceImpl implements BackupService {

    @Autowired
    private WebService1Soap webService1Soap;

    @Value("${url.backupURL}")
    private String url;

    @Override
    public R backupByFileName(Rule rule) {

        String sourceFullFileName = null;

        //sourceFullFileName 文件全名
        if(StringUtils.isNotBlank(rule.getSourceDateFormat())){
            sourceFullFileName = rule.getSourceFileName() +
                    DateUtils.getDate(rule.getSourceDateFormat()) + "." + rule.getSuffix();
        }else{
            sourceFullFileName = rule.getSourceFileName() + "." + rule.getSuffix();
        }

        List<String> sourceFullFileNames = new ArrayList<>();
        sourceFullFileNames.add(sourceFullFileName);

        return backup(rule,sourceFullFileNames);
    }

    @Override
    public R backupByTime(Rule rule) {
        String sourceAddress = rule.getSourceFTPIP() + "\\" + rule.getSourceFTPDir(); //源地址

        try {
            FTPClient ftpClient = FtpTool.getFTPClient(rule.getSourceFTPIP(),rule.getSourceFTPUsername(),rule.getSourceFTPPassword(),rule.getSourceFTPDir());
            FTPFile[] files = ftpClient.listFiles();
            List<String> sourceFullFileNames = new ArrayList<>();
            for (int i = 0; i < files.length; i++){
                Date date = DateUtils.parseDate(ftpClient.getModificationTime(files[i].getName()));
                long pastDays = DateUtils.pastDays(date);
                if(pastDays < rule.getGetDays()){
                    sourceFullFileNames.add(files[i].getName());
                }
            }
            if(sourceFullFileNames.size() == 0){
                return R.error(sourceAddress + "_" + "ftp上没有当天的文件");
            }else if(sourceFullFileNames.size() != rule.getFileNum()){
                return R.error(sourceAddress + "_" + "ftp上的文件数量为:" + sourceFullFileNames.size() + ",应为" + rule.getFileNum());
            }
            log.info("**共" + sourceFullFileNames.size() + "个文件");
            return backup(rule,sourceFullFileNames);
        }catch (IOException e) {
            return R.error(sourceAddress + "连接此ftp或读取该ftp内文件列表时出错");
        }
    }

    @Override
    public R backupByRegex(Rule rule){
        String sourceAddress = rule.getSourceFTPIP() + "\\" + rule.getSourceFTPDir(); //源地址

        try {
            String regex = rule.getRegex();

            FTPClient ftpClient = FtpTool.getFTPClient(rule.getSourceFTPIP(),rule.getSourceFTPUsername(),rule.getSourceFTPPassword(),rule.getSourceFTPDir());
            FTPFile[] files = ftpClient.listFiles();
            List<String> sourceFullFileNames = new ArrayList<>();
            for (int i = 0; i < files.length; i++){
                Date date = DateUtils.parseDate(ftpClient.getModificationTime(files[i].getName()));
                long pastDays = DateUtils.pastDays(date);
                if(pastDays < rule.getGetDays()){
                    if(files[i].getName().matches(regex)){
                        sourceFullFileNames.add(files[i].getName());
                    }
                }
            }

            if(sourceFullFileNames.size() == 0){
                return R.error(sourceAddress + "_" + "ftp上没有当天的文件");
            }else if(sourceFullFileNames.size() != rule.getFileNum()){
                return R.error(sourceAddress + "_" + "ftp上的文件数量为:" + sourceFullFileNames.size() + ",应为" + rule.getFileNum());
            }

            log.info("**共" + sourceFullFileNames.size() + "个文件");
            return backup(rule,sourceFullFileNames);
        } catch (IOException e) {
            return R.error(sourceAddress + "连接此ftp或读取该ftp内文件列表时出错");
        }
    }

    @Override
    public void resultDeal(R r,Rule rule){
        String sourceAddress = rule.getSourceFTPIP() + "_" + rule.getSourceFTPDir(); //源地址
        String targetAddress = rule.getTargetFTPIP() + "_" + rule.getTargetFTPDir(); //目的地址

        if (String.valueOf(r.get("code")).equals("0")) {
            createFile(sourceAddress + "_success.txt",String.valueOf(r.get("msg")),targetAddress);
            String result = uploadForm(sourceAddress + "_success.txt",sourceAddress + "备份成功",url);
            log.info("log.info(result);" + result + "!!");
        } else {
            createFile(sourceAddress + "_error.txt",String.valueOf(r.get("msg")),targetAddress);
            String result = uploadForm(sourceAddress + "_error.txt",sourceAddress + "备份失败",url);
            log.info("result:" + result + "!!");
            JsonObject json= new JsonParser().parse(result).getAsJsonObject();
            result = json.get("msg").getAsString();
            sendMsg("数据库备份异常监控",result);
        }

    }

    private R backup(Rule rule,List<String> sourceFullFileNames) {
        String sourceAddress = rule.getSourceFTPIP() + "\\" + rule.getSourceFTPDir(); //源地址
        String targetAddress = rule.getTargetFTPIP() + "\\" + rule.getTargetFTPDir(); //目的地址

        //删除目的ftp上的过期文件
        log.info("删除目的ftp上的过期文件");
        try {
            deleteFileByDate(rule);
        } catch (IOException e) {
            return R.error("删除_" + sourceAddress + "_过期文件时读取错误。");
        }

        for (String sourceFullFileName : sourceFullFileNames) {
            try {
                //创建源ftpClient
                log.info("创建" + sourceAddress + " ftpClient");
                FTPClient sourceFTPClient = getFTPClient(rule.getSourceFTPIP(), rule.getSourceFTPUsername(), rule.getSourceFTPPassword(), rule.getSourceFTPDir());

                //判断文件是否存在,大小是否为0
                log.info("判断文件是否存在,大小是否为0:" + sourceFullFileName);
                FTPFile[] ff = sourceFTPClient.listFiles(sourceFullFileName);
                if (ff.length == 0) {
                    return R.error(sourceAddress + "_" + sourceFullFileName + "_找不到当天的备份文件");
                }
                if (ff[0].getSize() == 0) {
                    return R.error(sourceAddress + "_" + sourceFullFileName + "_文件大小为0");
                }

                //判断文件是否为当天生成
                log.info("判断文件是否为当天生成:" + sourceFullFileName);
                Date date = DateUtils.parseDate(sourceFTPClient.getModificationTime(sourceFullFileName));
                long pastDays = DateUtils.pastDays(date);
                if (pastDays >= rule.getGetDays()) {
                    return R.error(sourceAddress + "_" + sourceFullFileName + "_文件不是当天生成");
                }

                //开始备份
                log.info("开始备份" + sourceFullFileName);
                InputStream is = sourceFTPClient.retrieveFileStream(sourceFullFileName);
                if(rule.getType().equals("0")){
                    FtpTool.uploadFile(rule, is);
                }else if(rule.getType().equals("1")||rule.getType().equals("2")){
                    FtpTool.uploadFile(rule, is,sourceFullFileName);
                }
                sourceFTPClient.disconnect();
                log.info("结束上传" + sourceFullFileName);

            } catch (FileNotFoundException e) {
                return R.error(sourceAddress + "_没有找到" + sourceFullFileName + "文件");
            } catch (SocketException e) {
                return R.error(sourceAddress + "_连接FTP失败.");
            } catch (IOException e) {
                e.printStackTrace();
                return R.error(sourceAddress + "_" + sourceFullFileName + "备份过程中文件读取错误。");
            }
        }
        return R.ok("备份成功:" + sourceAddress + "上的" + sourceFullFileNames.size() + "个文件已经成功备份到" + targetAddress + "上");
    }

    private Boolean createFile(String fileName,String msg,String targetAddress){
        try{
            log.info("fileName!!!!!!!!!"+fileName);
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(msg);
            ps.println("时间:" + DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            ps.println("目标服务器:" + targetAddress);
            ps.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            log.info(e.getMessage());
            return false;
        }
    }

    private void sendMsg(String type,String msg){
        webService1Soap.txtServerMsg(type,msg,null);
    }

    /**
     * 上传表单
     * @param fileName
     * @return
     */
    private String uploadForm(String fileName,String errorType,String url){
        HttpClientUtil httpClient = new HttpClientUtil();
        HttpClientUtil.MultipartForm form = httpClient.new MultipartForm();
//        MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);

        //设置form属性、参数
        form.setAction(url);
        form.addNormalField("errorType", errorType);

        List<File> fileList=new ArrayList<>();

        form.addNormalField("yq", "0");

        File file = new File(fileName);
        fileList.add(file);
        form.addFileField("files", fileList);

        //提交表单
        String urlResponse= HttpClientUtil.submitForm(form);

        DeleteFolder(fileName);

        log.info("返回结果:" + urlResponse);
        return  urlResponse;
    }
}
