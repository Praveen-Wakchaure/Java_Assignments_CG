package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CheckPresenceOfKeywordInResume_DocFormat extends ConvertDocxToTxt {

	public static void main(String[] args) throws IOException {

		String inputString = readInput();
		writeOutput(inputString);

		String input = null;
		File file =new File("./resources/Book1.xlsx");
		//Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(inputStream);

		//Read sheet inside the workbook by its name

		Sheet sheet = workbook.getSheet("Sheet1");

		//Find number of rows in excel file

		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

		//Create a loop over all the rows of excel file to read it

		double wordMatchCount = 0d;
		for (int i = 0; i<=rowCount; i++) {
			//System.out.println("Row Count: "+rowCount);
			Row row = sheet.getRow(i);
			//Create a loop to print cell values in a row
			for (int j = 0; j < row.getLastCellNum(); j++) {
				input=row.getCell(0).getStringCellValue().toString();
				input = input.toLowerCase();
				System.out.println(input);
				
				File resume=new File("./resources/resume.txt"); //Creation of File Descriptor for input file
				String[] words=null;  //Intialize the word Array
				FileReader fr = new FileReader(resume);  //Creation of File Reader object

				//	      Tika tika = new Tika();
				//	      String extractedText = tika.parseToString(inputFile);

				BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
				String s;     
				//		String input="functional"; // Input word to be searched
				//		input.toLowerCase();

				int count=0;   //Intialize the word to zero
				while((s=br.readLine())!=null)   //Reading Content from the file
				{
					words=s.split(" ");  //Split the word using space
					for (String word : words) 
					{
						word = word.toLowerCase();
						//System.out.println(lowerCaseWord);
						if (word.equals(input))   //Search for the given word
						{
							count++;    //If Present increase the count by one
							//System.out.println(word);
						}
					}		
				}
				if(count!=0)  //Check for count not equal to zero
				{
					wordMatchCount++;
					System.out.println("The given word is present for "+count+ " Times in the file");
				}
				else
				{
					System.out.println("The given word is not present in the file");
				}
				fr.close();
			}		
		}
		workbook.close();
		System.out.println("=======================================");
		double inputWordCount = rowCount+1;
		System.out.println("input word count: "+inputWordCount);
		System.out.println("Word Match count: "+wordMatchCount);
		
		double percentageMatch=0d;
		
		percentageMatch = (wordMatchCount/inputWordCount)*100;
		System.out.println("Percentage Match: "+percentageMatch+"%");		
	}
}
