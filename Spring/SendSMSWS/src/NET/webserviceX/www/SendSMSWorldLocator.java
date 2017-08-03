/**
 * SendSMSWorldLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NET.webserviceX.www;

public class SendSMSWorldLocator extends org.apache.axis.client.Service implements NET.webserviceX.www.SendSMSWorld {

    public SendSMSWorldLocator() {
    }


    public SendSMSWorldLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SendSMSWorldLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SendSMSWorldSoap
    private java.lang.String SendSMSWorldSoap_address = "http://www.webservicex.net/sendsmsworld.asmx";

    public java.lang.String getSendSMSWorldSoapAddress() {
        return SendSMSWorldSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SendSMSWorldSoapWSDDServiceName = "SendSMSWorldSoap";

    public java.lang.String getSendSMSWorldSoapWSDDServiceName() {
        return SendSMSWorldSoapWSDDServiceName;
    }

    public void setSendSMSWorldSoapWSDDServiceName(java.lang.String name) {
        SendSMSWorldSoapWSDDServiceName = name;
    }

    public NET.webserviceX.www.SendSMSWorldSoap getSendSMSWorldSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SendSMSWorldSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSendSMSWorldSoap(endpoint);
    }

    public NET.webserviceX.www.SendSMSWorldSoap getSendSMSWorldSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            NET.webserviceX.www.SendSMSWorldSoapStub _stub = new NET.webserviceX.www.SendSMSWorldSoapStub(portAddress, this);
            _stub.setPortName(getSendSMSWorldSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSendSMSWorldSoapEndpointAddress(java.lang.String address) {
        SendSMSWorldSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (NET.webserviceX.www.SendSMSWorldSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                NET.webserviceX.www.SendSMSWorldSoapStub _stub = new NET.webserviceX.www.SendSMSWorldSoapStub(new java.net.URL(SendSMSWorldSoap_address), this);
                _stub.setPortName(getSendSMSWorldSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SendSMSWorldSoap".equals(inputPortName)) {
            return getSendSMSWorldSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.webserviceX.NET", "SendSMSWorld");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.webserviceX.NET", "SendSMSWorldSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SendSMSWorldSoap".equals(portName)) {
            setSendSMSWorldSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
