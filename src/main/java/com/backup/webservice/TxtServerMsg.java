
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
    "addip"
})
@XmlRootElement(name = "TxtServerMsg")
public class TxtServerMsg {

    protected String serverid;
    protected String messagetext;
    @XmlElement(name = "ADDIP")
    protected String addip;

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

}
