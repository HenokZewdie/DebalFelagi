package NET.webserviceX.www;

public class SendSMSWorldSoapProxy implements NET.webserviceX.www.SendSMSWorldSoap {
  private String _endpoint = null;
  private NET.webserviceX.www.SendSMSWorldSoap sendSMSWorldSoap = null;
  
  public SendSMSWorldSoapProxy() {
    _initSendSMSWorldSoapProxy();
  }
  
  public SendSMSWorldSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSendSMSWorldSoapProxy();
  }
  
  private void _initSendSMSWorldSoapProxy() {
    try {
      sendSMSWorldSoap = (new NET.webserviceX.www.SendSMSWorldLocator()).getSendSMSWorldSoap();
      if (sendSMSWorldSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sendSMSWorldSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sendSMSWorldSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sendSMSWorldSoap != null)
      ((javax.xml.rpc.Stub)sendSMSWorldSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public NET.webserviceX.www.SendSMSWorldSoap getSendSMSWorldSoap() {
    if (sendSMSWorldSoap == null)
      _initSendSMSWorldSoapProxy();
    return sendSMSWorldSoap;
  }
  
  public java.lang.String sendSMS(java.lang.String fromEmailAddress, java.lang.String countryCode, java.lang.String mobileNumber, java.lang.String message) throws java.rmi.RemoteException{
    if (sendSMSWorldSoap == null)
      _initSendSMSWorldSoapProxy();
    return sendSMSWorldSoap.sendSMS(fromEmailAddress, countryCode, mobileNumber, message);
  }
  
  
}