
package com.backup.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serverid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="messagetext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ADDIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="files" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serverid",
    "messagetext",
    "addip",
    "fileSize",
    "fileName",
    "fileType",
    "files"
})
@XmlRootElement(name = "FileServerMsg")
public class FileServerMsg {

    protected String serverid;
    protected String messagetext;
    @XmlElement(name = "ADDIP")
    protected String addip;
    protected long fileSize;
    protected String fileName;
    protected String fileType;
    protected byte[] files;

    /**
     * 获取serverid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerid() {
        return serverid;
    }

    /**
     * 设置serverid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerid(String value) {
        this.serverid = value;
    }

    /**
     * 获取messagetext属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessagetext() {
        return messagetext;
    }

    /**
     * 设置messagetext属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessagetext(String value) {
        this.messagetext = value;
    }

    /**
     * 获取addip属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADDIP() {
        return addip;
    }

    /**
     * 设置addip属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADDIP(String value) {
        this.addip = value;
    }

    /**
     * 获取fileSize属性的值。
     * 
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * 设置fileSize属性的值。
     * 
     */
    public void setFileSize(long value) {
        this.fileSize = value;
    }

    /**
     * 获取fileName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置fileName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * 获取fileType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置fileType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileType(String value) {
        this.fileType = value;
    }

    /**
     * 获取files属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFiles() {
        return files;
    }

    /**
     * 设置files属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFiles(byte[] value) {
        this.files = value;
    }

}
