package assignment1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class CheckPresenceOfKeywordInResume_PDFFormat {

	public static void main(String[] args) throws IOException {

		File file=new File("./resources/resume.pdf");
		FileInputStream fis= new FileInputStream(file);
		PDDocument doc= PDDocument.load(fis);
		System.out.println("Number of pages in resume file is: "+doc.getPages().getCount());
		PDFTextStripper pdfTextStripper =new PDFTextStripper();
		String resumeText=pdfTextStripper.getText(doc);

		Scanner s = new Scanner(System.in);
		System.out.print("Enter no. of keywords to search:");
		int n = s.nextInt();
		String keywords[] = new String[n];
		System.out.println("Enter the keywords:");
		for(int i = 0; i < n ; i++)
		{
			keywords[i] = s.next();
		}
		s.close();

		int i=0;
		double percentage=0, sum=0;

		for(i = 0; i < keywords.length ; i++) {
			resumeText = resumeText.toLowerCase();
			if(resumeText.contains(keywords[i].toLowerCase())) {
				System.out.println("keyword found-->"+keywords[i]);
				sum=sum+1;
				percentage=(sum/n)*100;
			}
			else {
				System.out.println("keyword not found-->"+keywords[i]);
			}
		}
		System.out.println("the match is "+ percentage+"%");
	}
}
