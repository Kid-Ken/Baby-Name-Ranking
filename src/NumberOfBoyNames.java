
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;


public class NumberOfBoyNames {

	@Test
	public void test() {
		ArrayList<String> BOYNAMES = new ArrayList<>();
		String[] boyNames = new String[1000];
		String[] tokens = null;
		for (int years = 2001; years < 2011; years ++){
			try{		
				
				String fileName = "Babynamesranking"+ years+".txt";
				File f = new File(fileName);
				Scanner input = new Scanner(f);
			
				int j = 0;
			while(input.hasNext()){
					String line = input.nextLine();
					tokens = line.split("\t");
					boyNames[j] = tokens[1]; //places the boy names in an array
					 //places the rankings of the boy names in an array
					  // places the girl names in an array
					 //places the ranking of the girl names in an array
					j++; //increment j
				
	
						if (!BOYNAMES.contains(tokens[1])){
						BOYNAMES.add(tokens[1]);
	}
						}
			}
			
						catch(IOException p){
							System.out.println(p);
					}
}
	int	m = BOYNAMES.size();
	assertEquals(1277,m);
}
}