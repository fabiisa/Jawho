/*Designed by: Fabiana Fernandez - Nicolas Aguirre - Oscar Rodriguez 
 *Date :       04/27/2021
 *Program Name:javaGamev6.java
 *Description: This program is designed to test the 
 *				skills of a java junior developer.
 * **/

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;
public class javaGamev6 {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		//Declare variables and initialize variables
		int scoreObtained = 0;
		String userName = null;
		char selection;
		
		// Declare Constants
		final int NUMBER_OF_QUESTIONS = 10;
		
		// Declare arrays
		String[] questionArray = new String[NUMBER_OF_QUESTIONS];
		String[] answerA = new String[NUMBER_OF_QUESTIONS];
		String[] answerB = new String[NUMBER_OF_QUESTIONS];
		String[] answerC = new String[NUMBER_OF_QUESTIONS];
		String[] answerD = new String[NUMBER_OF_QUESTIONS];
		char[] correctAnswer = new char[NUMBER_OF_QUESTIONS];
		int[] pointValue = new int[NUMBER_OF_QUESTIONS];
		String[] highNames = new String[3];
		int[] highScores = new int[3];

		// If we wanted the code to reset the text file with sample data every time the code is run
		//Create highscore file
		//PrintWriter outputFile = new PrintWriter ("highscore.txt");
		//outputFile.print("John 30\nJane 20\nJoe 10");
		//outputFile.close();
		
		//Display Intro
		JOptionPane.showMessageDialog(null,"So you think you have what it takes to master java??\nWith these questions you'll go from 'jawho?' to 'JAVA!'", "Welcome!", JOptionPane.INFORMATION_MESSAGE);
		
		
		
		// Implement a do-while loop to let the user play as many times as he/she wants
		do {
		// to reset the score every time the game starts again
		scoreObtained = 0;
		
		//method prompts the user for and returns menu choice. it is then stored in selection
		selection = displayMainMenu();
		
		// Perform the decision taken by the user.
		// If user input is 1, display rules
		if (selection == '1') 
		{
			// method displays rules
			displayRules();
		}
		
		// if user input is 2, play game
		else if (selection == '2')
		{
			//Prompt for name and store
			userName = JOptionPane.showInputDialog(null,"Enter your name", "Player info:", JOptionPane.PLAIN_MESSAGE);
			
			//Open the questions file to read
			File file = new File("questions.txt");
			Scanner sc = new Scanner(file);
			
			// for loop that reads questions file 
			for (int i = 0; i< NUMBER_OF_QUESTIONS; i++ )
			{
				questionArray[i] = sc.nextLine();
				answerA[i] = sc.nextLine();
				answerB[i] = sc.nextLine();
				answerC[i] = sc.nextLine();
				answerD[i] = sc.nextLine();
				correctAnswer[i] = sc.next().charAt(0);
				pointValue[i] = sc.nextInt();
				sc.next();
				
				//method displays a question and its corresponding 4 answer. stores the score obtained
				//by the user from its answer in score
				scoreObtained += processQuestion(questionArray[i],answerA[i] ,answerB[i] ,answerC[i] ,answerD[i] , correctAnswer[i], pointValue[i], i+1);
				// displays the user current score
				displayScore(scoreObtained);
			
			}
			//Close the questions file
			sc.close();
			
			// Display the total points the user gained
			JOptionPane.showMessageDialog(null, "Your final score is " + scoreObtained);
			
			//this method opens “highscore.txt”, reads in the three names and scores 
			//from the file and stores the values in the parallel arrays highNames and highScores
			readInHighScores(highNames, highScores);
			
			//compares the user’s score to the high score that was read from the file.
			compareScore(scoreObtained, userName, highScores, highNames);
			
			//update highscores in the text file
			UpdateHighScores(highNames, highScores);
			
			//Display top three scores and names
			displayScores(highScores, highNames); 
			
			
		}
		
		
		
		// If user input is 3, display goodbye message
		else if (selection == '3')
		{
			JOptionPane.showMessageDialog(null,"Goodbye!");
		}

		}
		while (selection != '3');
	}
	
	//return type: char
	//parameters: none
	//purpose: This method prompts the user for menu choice and returns menu choice
	public static char displayMainMenu() 
	{
		String selection;
		selection = JOptionPane.showInputDialog(null, "Now you will be redirected to the main menu.\nChoose what you would like to do. \n\n"
				+ "MENU\n\n"
				+ "1. See rules.\n"
				+ "2. Play Game.\n"
				+ "3. Exit.\n"+
				"Enter choice(1-3): \n", "Ready to have fun??", JOptionPane.QUESTION_MESSAGE);
		return selection.charAt(0);
	}
	
	//return type: void
	//parameters: none
	//purpose: This method displays the rules
	public static void displayRules()
	
	{
		
	 JOptionPane.showMessageDialog(null, "1. Don't look at google.\n"
			+"2. No open book.\n"
			+"3. Once you choose your answer, that's it.\n"
			+"4. You will get 10 points for evert right answer.", "Game rules:", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	//return type: int
	//parameters: String q1, String ansA, String ansB, String ansC, String ansD, char correctAns, int PointValue
	//purpose: This method displays a question and its corresponding 4 answers, prompt for the 
	//correct answer and display correct/incorrect to the user. The function 
	// also returns the points that the user received. If the user 
	//answers incorrectly, returns a value of zero.
	public static int processQuestion(String q1, String ansA, String 
			ansB, String ansC, String ansD, char correctAns, int PointValue, int questionNumber)
	{
		String answerQ;
		do {
			answerQ = JOptionPane.showInputDialog(null, "Question " + questionNumber + "\n" + q1 +
					"\n" + ansA + "\n" + ansB + "\n" + ansC + "\n" + ansD, "Testing your knowledge!", JOptionPane.QUESTION_MESSAGE);

			// Explains the user why their input is not valid
			if (Integer.parseInt(answerQ) < 1 || Integer.parseInt(answerQ) >4)
			{
				JOptionPane.showMessageDialog(null, "Please type a number between 1 and 4", "Invalid!", JOptionPane.WARNING_MESSAGE);	
			}
		}
		// Input validation, keeps showing the question until the user types valid input
		while (Integer.parseInt(answerQ) < 1 || Integer.parseInt(answerQ) >4);
		
		// If the user types the correct answer, they receive the number of points assigned in the txt file
		if (answerQ.charAt(0) == correctAns)
		{
			JOptionPane.showMessageDialog(null, "Correct! You got " + PointValue + " for the question.", "Yay!!", JOptionPane.INFORMATION_MESSAGE);
			return PointValue;
		}
		
		// If the user types an incorrect answer, show an incorrect message 
		else 
		{
			JOptionPane.showMessageDialog(null, "Incorrect. You got 0 points for the question.", "Boo :(", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}

	//return type: void
	//parameters: String[] name, int[] score
	//purpose: This method opens “highscore.txt”, reads in the three names and scores 
	//from the file and stores the values in the parallel arrays highNames and highScores
	public static void readInHighScores(String[] name, int[] score) throws IOException
	{
		// Read from highscore file
		File readOutputFile = new File("highscore.txt");
		Scanner scannerOFile = new Scanner(readOutputFile);
		for (int i=0; i< 3; i++)
		{
			name[i] = scannerOFile.next();
			score[i] = scannerOFile.nextInt();
		}
		
		scannerOFile.close();
	}
	
	//return type: void
	//parameters: int userScore, String userName, int[] score, String[] name
	//purpose: This method compares the user’s score to the high score that was read from the file. 
	//If the user’s score is higher than any of the scores i  the list, it rearranges the scores and corresponding names 
	//in the arrays as necessary
	public static void compareScore( int userScore, String userName, int[] score, String[] name)  throws IOException
	{

		// Greater than the highest score
		if (userScore > score[0])
		{
			score[2] = score[1];
			name[2] = name[1];
			score[1] = score[0];
			name[1] = name[0];
			score[0] = userScore;
			name[0] = userName;
			JOptionPane.showMessageDialog(null, "You overpassed the highest score!");
			
		}
		else if (userScore > score[1])
		{
			score[2] = score[1];
			name[2] = name[1];
			score[1] = userScore;
			name[1] = userName;
			JOptionPane.showMessageDialog(null, "You got the second highest score!");
		}
		else if (userScore > score[2])
		{
			score[2]= userScore;
			name[2]= userName;
			JOptionPane.showMessageDialog(null, "You got the third highest score!");
		}
	
	}
		
	//return type: void
	//parameters: int[] highName, int[] highScore
	//purpose: This method updates the highscore.txt file with the scores and names in the arrays as necessary
	public static void UpdateHighScores( String[] highName, int[] highScore) throws IOException
	{
	
		PrintWriter newOutputFile = new PrintWriter ("highscore.txt");
		for (int i =0; i< 3; i++ )
		{
			
			newOutputFile.print(highName[i] + " " + highScore[i] +"\n");
			
		}
		newOutputFile.close();
		
	}



	//return type: void
	//parameters: int score
	//purpose: This method displays the user’s current total.
	public static void displayScore(int score) 
	{
		JOptionPane.showMessageDialog(null, "Your current score is " + score);
		
	}
	
	//return type: void
	//parameters: int[] scores, String[] names
	//purpose: This method Displays the top three performers 
	public static void displayScores(int[] scores, String[] names) 
	{
		JOptionPane.showMessageDialog(null, "The top three highest scores are:\n");
		for (int i =0; i< 3; i++ )
		{
			JOptionPane.showMessageDialog(null, names[i] + " " + scores[i] + "\n");
		}
	}
}
