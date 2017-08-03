package NET.webserviceX.www;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class MainCountry {

	public static void main(String[] args) throws ServiceException, RemoteException {
		
		CountryLocator countryLocator = new CountryLocator();
		CountrySoap country = countryLocator.getcountrySoap();
		System.out.println(country.getCountryByCurrencyCode("ETB"));
			
		
		
		
	}
}