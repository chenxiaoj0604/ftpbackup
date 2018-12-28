<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://tempuri.org/" xmlns:s1="http://service.restartws.yhzn.com" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" targetNamespace="http://tempuri.org/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/">
      <s:import namespace="http://service.restartws.yhzn.com" />
      <s:element name="HelloWorld">
        <s:complexType />
      </s:element>
      <s:element name="HelloWorldResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="HelloWorldResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FileServerMsg">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="serverid" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="messagetext" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ADDIP" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="fileSize" type="s:long" />
            <s:element minOccurs="0" maxOccurs="1" name="fileName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="fileType" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="files" type="s:base64Binary" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FileServerMsgResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="FileServerMsgResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FileNameServerMsg">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="serverid" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="messagetext" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ADDIP" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="fileName" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="fileType" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FileNameServerMsgResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="FileNameServerMsgResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="TxtServerMsg">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="serverid" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="messagetext" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="ADDIP" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="TxtServerMsgResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="TxtServerMsgResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="JumpSync">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Hosptail" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="Phone" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="PhoneKey" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="JumpSyncResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="JumpSyncResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Restart_LogList">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="page" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="ipMark" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Restart_LogListResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Restart_LogListResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Restart_Restart">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="id" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Restart_RestartResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Restart_RestartResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Restart_RestartList">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="modularId" type="s:int" />
            <s:element minOccurs="0" maxOccurs="1" name="token" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Restart_RestartListResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Restart_RestartListResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Restart_FileState">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="path" type="s1:linkPath" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Restart_FileStateResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Restart_FileStateResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="DataCopy">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="HospitalId" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="DataCopyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="DataCopyResult" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
    <s:schema elementFormDefault="qualified" targetNamespace="http://service.restartws.yhzn.com">
      <s:complexType name="linkPath">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" form="unqualified" name="createDate" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" form="unqualified" name="fileName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" form="unqualified" name="id" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" form="unqualified" name="ipMark" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" form="unqualified" name="linkPath" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" form="unqualified" name="readState" type="s:int" />
        </s:sequence>
      </s:complexType>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="HelloWorldSoapIn">
    <wsdl:part name="parameters" element="tns:HelloWorld" />
  </wsdl:message>
  <wsdl:message name="HelloWorldSoapOut">
    <wsdl:part name="parameters" element="tns:HelloWorldResponse" />
  </wsdl:message>
  <wsdl:message name="FileServerMsgSoapIn">
    <wsdl:part name="parameters" element="tns:FileServerMsg" />
  </wsdl:message>
  <wsdl:message name="FileServerMsgSoapOut">
    <wsdl:part name="parameters" element="tns:FileServerMsgResponse" />
  </wsdl:message>
  <wsdl:message name="FileNameServerMsgSoapIn">
    <wsdl:part name="parameters" element="tns:FileNameServerMsg" />
  </wsdl:message>
  <wsdl:message name="FileNameServerMsgSoapOut">
    <wsdl:part name="parameters" element="tns:FileNameServerMsgResponse" />
  </wsdl:message>
  <wsdl:message name="TxtServerMsgSoapIn">
    <wsdl:part name="parameters" element="tns:TxtServerMsg" />
  </wsdl:message>
  <wsdl:message name="TxtServerMsgSoapOut">
    <wsdl:part name="parameters" element="tns:TxtServerMsgResponse" />
  </wsdl:message>
  <wsdl:message name="JumpSyncSoapIn">
    <wsdl:part name="parameters" element="tns:JumpSync" />
  </wsdl:message>
  <wsdl:message name="JumpSyncSoapOut">
    <wsdl:part name="parameters" element="tns:JumpSyncResponse" />
  </wsdl:message>
  <wsdl:message name="Restart_LogListSoapIn">
    <wsdl:part name="parameters" element="tns:Restart_LogList" />
  </wsdl:message>
  <wsdl:message name="Restart_LogListSoapOut">
    <wsdl:part name="parameters" element="tns:Restart_LogListResponse" />
  </wsdl:message>
  <wsdl:message name="Restart_RestartSoapIn">
    <wsdl:part name="parameters" element="tns:Restart_Restart" />
  </wsdl:message>
  <wsdl:message name="Restart_RestartSoapOut">
    <wsdl:part name="parameters" element="tns:Restart_RestartResponse" />
  </wsdl:message>
  <wsdl:message name="Restart_RestartListSoapIn">
    <wsdl:part name="parameters" element="tns:Restart_RestartList" />
  </wsdl:message>
  <wsdl:message name="Restart_RestartListSoapOut">
    <wsdl:part name="parameters" element="tns:Restart_RestartListResponse" />
  </wsdl:message>
  <wsdl:message name="Restart_FileStateSoapIn">
    <wsdl:part name="parameters" element="tns:Restart_FileState" />
  </wsdl:message>
  <wsdl:message name="Restart_FileStateSoapOut">
    <wsdl:part name="parameters" element="tns:Restart_FileStateResponse" />
  </wsdl:message>
  <wsdl:message name="DataCopySoapIn">
    <wsdl:part name="parameters" element="tns:DataCopy" />
  </wsdl:message>
  <wsdl:message name="DataCopySoapOut">
    <wsdl:part name="parameters" element="tns:DataCopyResponse" />
  </wsdl:message>
  <wsdl:portType name="WebService1Soap">
    <wsdl:operation name="HelloWorld">
      <wsdl:input message="tns:HelloWorldSoapIn" />
      <wsdl:output message="tns:HelloWorldSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="FileServerMsg">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">string *serverid  功能名称, string messagetext 提醒信息 , string ADDIP IP 地址, long *fileSize 文件大小, string *fileName 文件名称, string *fileType 文件类型, byte[] files 文件二进制数据</wsdl:documentation>
      <wsdl:input message="tns:FileServerMsgSoapIn" />
      <wsdl:output message="tns:FileServerMsgSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="FileNameServerMsg">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">string *serverid  功能名称, string messagetext 提醒信息 , string ADDIP IP 地址, string *fileName 文件名称, string *fileType 文件类型</wsdl:documentation>
      <wsdl:input message="tns:FileNameServerMsgSoapIn" />
      <wsdl:output message="tns:FileNameServerMsgSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="TxtServerMsg">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">string serverid  功能名称, string messagetext 提醒信息 , string ADDIP IP地址</wsdl:documentation>
      <wsdl:input message="tns:TxtServerMsgSoapIn" />
      <wsdl:output message="tns:TxtServerMsgSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="JumpSync">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Hosptail   0本院 1 南院，key  手机验证码</wsdl:documentation>
      <wsdl:input message="tns:JumpSyncSoapIn" />
      <wsdl:output message="tns:JumpSyncSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Restart_LogList">
      <wsdl:input message="tns:Restart_LogListSoapIn" />
      <wsdl:output message="tns:Restart_LogListSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Restart_Restart">
      <wsdl:input message="tns:Restart_RestartSoapIn" />
      <wsdl:output message="tns:Restart_RestartSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Restart_RestartList">
      <wsdl:input message="tns:Restart_RestartListSoapIn" />
      <wsdl:output message="tns:Restart_RestartListSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Restart_FileState">
      <wsdl:input message="tns:Restart_FileStateSoapIn" />
      <wsdl:output message="tns:Restart_FileStateSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="DataCopy">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Hosptail   0本院 1 南院</wsdl:documentation>
      <wsdl:input message="tns:DataCopySoapIn" />
      <wsdl:output message="tns:DataCopySoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="WebService1Soap" type="tns:WebService1Soap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="HelloWorld">
      <soap:operation soapAction="http://tempuri.org/HelloWorld" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FileServerMsg">
      <soap:operation soapAction="http://tempuri.org/FileServerMsg" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FileNameServerMsg">
      <soap:operation soapAction="http://tempuri.org/FileNameServerMsg" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="TxtServerMsg">
      <soap:operation soapAction="http://tempuri.org/TxtServerMsg" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="JumpSync">
      <soap:operation soapAction="http://tempuri.org/JumpSync" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Restart_LogList">
      <soap:operation soapAction="http://tempuri.org/Restart_LogList" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Restart_Restart">
      <soap:operation soapAction="http://tempuri.org/Restart_Restart" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Restart_RestartList">
      <soap:operation soapAction="http://tempuri.org/Restart_RestartList" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Restart_FileState">
      <soap:operation soapAction="http://tempuri.org/Restart_FileState" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="DataCopy">
      <soap:operation soapAction="http://tempuri.org/DataCopy" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="WebService1Soap12" type="tns:WebService1Soap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="HelloWorld">
      <soap12:operation soapAction="http://tempuri.org/HelloWorld" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FileServerMsg">
      <soap12:operation soapAction="http://tempuri.org/FileServerMsg" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FileNameServerMsg">
      <soap12:operation soapAction="http://tempuri.org/FileNameServerMsg" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="TxtServerMsg">
      <soap12:operation soapAction="http://tempuri.org/TxtServerMsg" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="JumpSync">
      <soap12:operation soapAction="http://tempuri.org/JumpSync" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Restart_LogList">
      <soap12:operation soapAction="http://tempuri.org/Restart_LogList" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Restart_Restart">
      <soap12:operation soapAction="http://tempuri.org/Restart_Restart" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Restart_RestartList">
      <soap12:operation soapAction="http://tempuri.org/Restart_RestartList" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Restart_FileState">
      <soap12:operation soapAction="http://tempuri.org/Restart_FileState" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="DataCopy">
      <soap12:operation soapAction="http://tempuri.org/DataCopy" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="WebService1">
    <wsdl:port name="WebService1Soap" binding="tns:WebService1Soap">
      <soap:address location="http://192.168.3.102:8899/WebService1.asmx" />
    </wsdl:port>
    <wsdl:port name="WebService1Soap12" binding="tns:WebService1Soap12">
      <soap12:address location="http://192.168.3.102:8899/WebService1.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>