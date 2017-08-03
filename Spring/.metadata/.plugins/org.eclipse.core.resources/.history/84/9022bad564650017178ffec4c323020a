package IPFinderPackage;

import java.util.Scanner;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import net.webservicex.GeoIPServiceSoap;

public class IPFinderSOAP {

	public static void main(String[] args) throws Exception { 
		
		Scanner inout = new Scanner(System.in);
		System.out.println("Enter the IP Address:  ");
		String ip= inout.nextLine();
			GeoIPService geoIPService = new GeoIPService();
			GeoIPServiceSoap geoIPServiceSoap= geoIPService.getGeoIPServiceSoap();
			GeoIP geoip = geoIPServiceSoap.getGeoIP(ip);
			System.out.println(geoip.getCountryName());
			inout.close();
		}
	

}
