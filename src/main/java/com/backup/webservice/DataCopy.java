
package com.backup.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HospitalId" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "hospitalId"
})
@XmlRootElement(name = "DataCopy")
public class DataCopy {

    @XmlElement(name = "HospitalId")
    protected int hospitalId;

    /**
     * ��ȡhospitalId���Ե�ֵ��
     * 
     */
    public int getHospitalId() {
        return hospitalId;
    }

    /**
     * ����hospitalId���Ե�ֵ��
     * 
     */
    public void setHospitalId(int value) {
        this.hospitalId = value;
    }

}
