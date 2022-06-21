package assignment2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import au.com.bytecode.opencsv.CSVReader;

public class FindingMatchesAndMismatchesCSVFile {

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {

		//Reading 1st csv file

		ArrayList<String> array1 = new ArrayList<String>(); 
		CSVReader reader1 = null;  
		reader1 = new CSVReader(new FileReader("./resources/DataSourceFile1.csv"));    

		String [] nextLine1;  
		//read one line at a time  
		while ((nextLine1 = reader1.readNext())!= null)  
		{  
			for(int i=0; i<nextLine1.length;i++) {
				array1.add(nextLine1[i]);
			}
		} 
		System.out.println(array1);
		//Reading 2nd csv file

		ArrayList<String> array2 = new ArrayList<String>(); 
		CSVReader reader2 = null;
		reader2 = new CSVReader(new FileReader("./resources/DataSourceFile2.csv"));  

		String [] nextLine2;  

		//read one line at a time  
		while ((nextLine2 = reader2.readNext()) != null)  
		{  
			for(int i=0; i<nextLine2.length;i++) {
				array2.add(nextLine2[i]);
			}
		}  
		System.out.println(array2);

		//Listing the difference
		ArrayList<String> array3 = new ArrayList<String>(); 
		for(int i=0; i<array1.size();i++) {

			if(isNumeric(array1.get(i)) && isNumeric(array2.get(i))) {
				double a = Double.parseDouble(array1.get(i));
				double b = Double.parseDouble(array2.get(i));

				String result = String.valueOf(a-b);

				if((a-b)==0) {
					array3.add("-");
				}
				else {
					array3.add(result);
				}	
			}	
			else {
				String difference =StringUtils.difference(array1.get(i),array2.get(i));
				if(difference.isEmpty()) {
					array3.add("-");
				}
				else {
					array3.add(StringUtils.difference(array1.get(i),array2.get(i)));
				}
			}	
		}
		System.out.println(array3);

		//Creating result file
		File file = new File("./results/difference.csv");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);

		for(int i=0;i<array1.size();i++)
		{
			bw.write(array1.get(i)+","+array2.get(i)+","+array3.get(i)+",");
			bw.write(array1.get(i+1)+","+array2.get(i+1)+","+array3.get(i+1)+",");
			bw.write(array1.get(i+2)+","+array2.get(i+2)+","+array3.get(i+2)+",");
			i=i+2;
			bw.newLine();
		}

		bw.close();
		fw.close();
	}
}
