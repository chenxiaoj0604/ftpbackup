package com.backup.utils;

import com.backup.domain.Rule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.util.Date;

/**
 * Created by duanxun on 2018-11-07.
 */
@Slf4j
public class FtpTool {

    public static FTPClient getFTPClient(String ip,String username,String password,String dir) throws IOException {
        FTPClient ftpClient = new FTPClient();
            ftpClient = new FTPClient();
            ftpClient.connect(ip, 21);// 连接FTP服务器
            ftpClient.login(username, password);// 登陆FTP服务器
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            if(dir != null){
                ftpClient.changeWorkingDirectory(dir);
            }
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                log.info("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                log.info(ip + "FTP连接成功。");
            }
        return ftpClient;
    }



    /**
     * Description: 向FTP服务器上传文件
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(Rule rule,InputStream input) throws IOException {
        boolean success = false;
        FTPClient ftpClient = null;
        try {
            int reply;
            ftpClient = getFTPClient(rule.getTargetFTPIP(),rule.getTargetFTPUsername(),rule.getTargetFTPPassword(),rule.getTargetFTPDir());
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return success;
            }

            String fileName = null;
            if(StringUtils.isNotBlank(rule.getTargetDateFormat())){
                fileName = rule.getTargetFileName() + DateUtils.getDate(rule.getTargetDateFormat()) + "." + rule.getSuffix();
            }else {
                fileName = rule.getTargetFileName() + "." + rule.getSuffix();
            }
            ftpClient.storeFile(fileName, input);

            input.close();
            ftpClient.logout();
            success = true;
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    public static boolean uploadFile(Rule rule,InputStream input,String sourceFullFileName) throws IOException {
        boolean success = false;
        FTPClient ftpClient = null;
        try {
            int reply;
            ftpClient = getFTPClient(rule.getTargetFTPIP(),rule.getTargetFTPUsername(),rule.getTargetFTPPassword(),rule.getTargetFTPDir());
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return success;
            }

            ftpClient.storeFile(sourceFullFileName, input);

            input.close();
            ftpClient.logout();
            success = true;
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }
    /**
     * 根据时间删除备份数据
     */
    public static void deleteFileByDate(Rule rule) throws IOException {
        FTPClient ftpClient = getFTPClient(rule.getTargetFTPIP(),rule.getTargetFTPUsername(),rule.getTargetFTPPassword(),rule.getTargetFTPDir());
        FTPFile[] files = ftpClient.listFiles();
        for (int i = 0; i < files.length; i++){
            Date date = files[i].getTimestamp().getTime();
            if(DateUtils.pastDays(date) > rule.getSaveDays()){
                log.info("删除文件" + files[i].getName());
                ftpClient.deleteFile(files[i].getName());
            }
        }
    }
}
