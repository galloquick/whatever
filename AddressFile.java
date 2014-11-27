/*
 * Programmierung 1 HS14
 * Serie 5 
 *  
 * Luis Aguilar 14-122-840
 * 
 */ 

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class AddressFile {
	protected String filename;

	AddressFile(String zuOeffnendeDatei) {
		this.filename = zuOeffnendeDatei;
	}
	
	protected String toLine(Address addr) {
		String output = addr.toString();
		
		return output;
	}
	
	protected Address parseLine(String line) throws AddressFileException {
		int newId, newZipCode;
		String newStreet, newName, newCity;
		Scanner scanner = new Scanner(line).useDelimiter(",");

		try {
			newId = Integer.parseInt(scanner.next().trim());
			newName = scanner.next().trim();
			newStreet = scanner.next().trim();
			newZipCode = Integer.parseInt(scanner.next().trim());
			newCity = scanner.next().trim();
		} catch (Exception except) {
			throw new AddressFileException ("Input value is wrong (format or out of range)!");
		}
		
		Address outputFromAddress = new Address(newId, newName, newStreet, newZipCode, newCity);
		return outputFromAddress;
	}
	
	public void save(ArrayList<Address> addresses) throws IOException {
		FileWriter filew = new FileWriter (filename);
	    BufferedWriter bufferedw = new BufferedWriter (filew);
	    PrintWriter outputFromFile = new PrintWriter (bufferedw);
	    
		for(Address a : addresses){
			outputFromFile.print(toLine(a));
			outputFromFile.println();
		}
		
		outputFromFile.close();
	}
	
	public ArrayList<Address> load() throws AddressFileException, FileNotFoundException {
		ArrayList<Address> tmpAds = new ArrayList<Address>();

		Scanner docReader = new Scanner(new File(filename));
		
		while (docReader.hasNextLine()) {
			tmpAds.add(parseLine(docReader.nextLine()));
		}
		
		return tmpAds;
	}
}