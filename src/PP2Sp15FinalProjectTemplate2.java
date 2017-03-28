import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PP2Sp15FinalProjectTemplate2 extends Application {
	
	ComboBox<String> cboYear = new ComboBox<String>();
	RadioButton rbMale = new RadioButton("Male");
	RadioButton rbFemale = new RadioButton("Female");
	TextField tfName = new TextField();
	 ArrayList<String> AllFiveNames = new ArrayList<>();
	 ArrayList<String> z = null;
	
	public void start(Stage primaryStage){
		//a GridPane arrange nodes in a grid(matrix) formation
		GridPane mainPane = new GridPane();
		mainPane.setVgap(10);//sets the gap between rows

		//add(Node, colIndex, rowIndex)
		mainPane.add(new Label("Select a year: "),0,0);
		mainPane.add(new Label("Boy or girls? "),0,1);
		mainPane.add(new Label("Enter a name: "),0,2);
		//Place the next text field on the same row with the last label 
		//(that is column 1 (next column) and row 2 (same row))
		mainPane.add(tfName, 1, 2);
		
		ToggleGroup group = new ToggleGroup();
		rbMale.setToggleGroup(group);
		rbFemale.setToggleGroup(group);
		mainPane.add(rbMale,1,1); //Adds radio buttons to the gridpane	
		mainPane.add(rbFemale,2,1); // Adds radio buttons to the gridpane
		
		cboYear.getItems().add("All");
		for(int i=2001; i<=2010; i++)   //Adds all years from 2001-2010 to the combobox
			cboYear.getItems().add(Integer.toString(i));
			
		mainPane.add(cboYear, 1, 0); // adds the combobox to the gridpane
		
		Button btClear = new Button("Clear"); //creates the "clear" button
		Button btFindRank = new Button("Find Ranking"); // creates the "Find Ranking" button.
		Button btPrintTop5 = new Button("Print Top 5");	// creates the "Print top 5" button


		//CLEAR BUTTON
			btClear.setOnAction(e -> {
			cboYear.setValue(null);
			rbMale.setSelected(false); //clears all buttons and fields.
			rbFemale.setSelected(false);
			tfName.clear();
		});
		//PRINT TOP 5 BUTON
		btPrintTop5.setOnAction(e -> {
			
			String[] tokens = null;
			String gender = null;
			String[] boyNames = new String[1000];
			String[] BoyRankings = new String[1000];
			String[] girlNames = new String[1000];
			String[] GirlRankings = new String[1000];
			ArrayList<String> BOYNAMES;
			ArrayList<String> GIRLNAMES;
			ArrayList<String> branking;
			ArrayList<String> granking;
			String results = null;
	
			String year = cboYear.getValue();
			
			if (rbMale.isSelected()){
			
			 gender = "M";
			}
			if(rbFemale.isSelected()){
			 gender = "F";
			}
			
			
			if( year.compareTo("All") ==0){
			int  years;
			BOYNAMES = new ArrayList<>();
			branking = new ArrayList<>();
			GIRLNAMES = new ArrayList<>();
			granking = new ArrayList<>();
			for (years = 2001; years < 2011; years ++){
		try{		
			
			String fileName = "Babynamesranking"+ years+".txt";
			File f = new File(fileName);
			Scanner input = new Scanner(f);
		
			int j = 0;
		while(input.hasNext()){
				String line = input.nextLine();
				tokens = line.split("\t");
				boyNames[j] = tokens[1]; //places the boy names in an array
				BoyRankings[j] = tokens[2]; //places the rankings of the boy names in an array
				girlNames[j] = tokens[3];  // places the girl names in an array
				GirlRankings[j] = tokens[4]; //places the ranking of the girl names in an array
				j++; //increment j
			
				if(gender.toUpperCase().compareTo("M") == 0){
					if (!BOYNAMES.contains(tokens[1])){
					BOYNAMES.add(tokens[1]); // adds boys names to ArrayList BOYSNAMES that is not already in the ArrayList
					branking.add(tokens[2].trim().replace(",", "")); //adds ranking to another ArrayList branking
					}
					else {
					String rankingbefore = tokens[2].trim().replaceAll(",", ""); //adds the rankings to another ArrayList
					String rankingafter = branking.get(BOYNAMES.indexOf(tokens[1])); //takes the ranking from the new name that was a duplicate
					int addedtogether = (Integer.parseInt(rankingafter)) + (Integer.parseInt(rankingbefore)); //adds the old value and the new value together
					branking.set(BOYNAMES.indexOf(tokens[1]), Integer.toString(addedtogether)); //makes the ranking value and places it in the spot of the old one
					}
					
				}
				else if(gender.toUpperCase().compareTo("F") == 0){
					if (!GIRLNAMES.contains(tokens[3].trim())){
					GIRLNAMES.add(tokens[3].trim());
					granking.add(tokens[4].trim().replace(",", ""));
					}
					else {
					String rankingbefore = tokens[4].trim().replaceAll(",", "");
					String rankingafter = granking.get(GIRLNAMES.indexOf(tokens[3].trim()));
					int addedtogether = (Integer.parseInt(rankingafter)) + (Integer.parseInt(rankingbefore));
					granking.set(GIRLNAMES.indexOf(tokens[3].trim()), Integer.toString(addedtogether));
					}
		}
		}
		}
		catch(IOException p){
				System.out.println(p);
		}
				//for loop ends	
			}
			
			if(gender.toUpperCase().compareTo("M") == 0){//prints the top 5 male names
				 results = toString(BOYNAMES);
			}
			if(gender.toUpperCase().compareTo("F") == 0){//prints the top 5 female names
				 results = toString(GIRLNAMES);
			}
			
			
			Label lbResults = new Label(results);
			Button btOk = new Button("Ok");
			GridPane gp = new GridPane();
			gp.setAlignment(Pos.CENTER);
			gp.setVgap(10);
			gp.add(lbResults,0,0);
			gp.add(btOk,0,1);
			Scene scene = new Scene(gp, 300, 150);
			Stage resultStage = new Stage();
			resultStage.setScene(scene);
			
			resultStage.initModality(Modality.WINDOW_MODAL);
			resultStage.initOwner(primaryStage);
			resultStage.show();
			
			btOk.setOnAction(ex -> {
				resultStage.close();
			});
			
			
			}
		});
		
		
		
		//FIND RANKING BUTTON
		btFindRank.setOnAction(e -> {
			ArrayList<String> BOYANDGIRLNAMES = new ArrayList<>();
			String[] tokens = null;
			
			String year = cboYear.getValue();
			String endingresults = null;
			
			String fileName = "Babynamesranking"+ year+".txt";
			File f = new File(fileName);
			String[] boyNames = new String[1000];
			String[] girlNames = new String[1000];
			String gender = null;
			if (rbMale.isSelected()){
			
			 gender = "M";
			}
			if(rbFemale.isSelected()){
			 gender = "F";
			}
			
			
			String name = tfName.getText();
			
		try{	
			Scanner input = new Scanner(f);
		
			int j = 0;
		while(input.hasNext()){
				String line = input.nextLine();
				tokens = line.split("\t");
				boyNames[j] = tokens[1];
				girlNames[j] = tokens[3]; 
				j++; //increment j
			
				if(gender.toUpperCase().compareTo("M") == 0){
					if (tokens[1].trim().compareTo(name) == 0){
						endingresults ="Boy name "+name + " is ranked #" + //prints the boy names
						tokens[0] + "in year " + year;
						break;
					 //exit the while loop
						
						//search in boyNames
					}			
		}
				else if(gender.toUpperCase().compareTo("F") == 0)
				{
					if (tokens[3].trim().compareTo(name) == 0){
						endingresults ="Girl name " + name + " is ranked #" + //prints the girls names
						tokens[0] + "in year " + year;
						break;
					 //exit the while loop
							
					}
					}
		}
					if (tokens[1].trim().compareTo(name) != 0 && tokens[3].trim().compareTo(name) != 0 ){
						endingresults ="The name " + name + " is not ranked in year " + year; //prints this statement if the name is not in the list of names given
					}
		
		}
		catch(IOException p){
				System.out.println(p);
		}
		
			Label lbResults = new Label(endingresults);
			Button btOk = new Button("Ok");
			GridPane gp = new GridPane();
			gp.setAlignment(Pos.CENTER);
			gp.setVgap(10);
			gp.add(lbResults,0,0);
			gp.add(btOk,0,1);
			Scene scene = new Scene(gp, 300, 150);
			Stage resultStage = new Stage();
			resultStage.setScene(scene);
			
			resultStage.initModality(Modality.WINDOW_MODAL);
			resultStage.initOwner(primaryStage);
			//Modality together with initOwner disables accessing all the other components of the program 
			//(until Ok button is clicked)
			
			resultStage.show();
			
			btOk.setOnAction(ex -> {
				resultStage.close();
			});
		});
		
		//add all three buttons on the same row
		mainPane.add(btClear, 0, 3);
		mainPane.add(btFindRank, 1, 3);
		mainPane.add(btPrintTop5, 2, 3);
		
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
	
	
	public String toString(ArrayList p) {
		// TODO Auto-generated method stub
	return (String)"1: "+ p.get(0)+ "\n" + 
				   "2: "+ p.get(1)+ "\n" +
				   "3: "+ p.get(2)+ "\n" +
				   "4: "+ p.get(3)+ "\n" +
				   "5: "+ p.get(4);
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
