import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class LuckyGameApplet extends Frame implements ActionListener{
	TextField userInputs;  
	TextArea resultText;
    static Button playButton;  
    static Label avaialbleGames, UserInput;
    static Choice availableGameOptions;
    int currentGameIndex = 0;
    final int ALIGN_X = 75;
    final int ALIGN_Y = 75;
    final int DEFAULT_H = 15;
    final int DEFAULT_W = 100;
   	final String[] availableGamesName = {
   			"Saturday Tattslotto", "Tuesday OZ Lotto", "Thursday PowerBall"
   	};
   	
   	static LuckyGame[] games;
   	
    LuckyGameApplet(){  
    	
    	// select available games
    	avaialbleGames = new Label();
    	availableGameOptions = new Choice();

	   	addLabel(avaialbleGames, "Available Games: ", ALIGN_X, ALIGN_Y, DEFAULT_W, DEFAULT_H);
	   	addDropDown(availableGameOptions, availableGamesName, ALIGN_X + 100, ALIGN_Y, DEFAULT_W + 50, DEFAULT_H);
	   	
	   	// enter user input
    	UserInput = new Label();
    	userInputs = new TextField();
	   	addLabel(UserInput, "Give Inputs: ", ALIGN_X, ALIGN_Y + 25, DEFAULT_W, DEFAULT_H);
	   	addTextField(userInputs, ALIGN_X + 100, ALIGN_Y  + 25, DEFAULT_W + 50, DEFAULT_H + 10);
	   	
	   	// play button
    	playButton = new Button("Play");
	   	addButton(playButton, ALIGN_X + 100, ALIGN_Y + 50, DEFAULT_W, DEFAULT_H + 10);
	   	
	   	// result text field
	   	resultText = new TextArea();
	   	addTextArea(resultText, ALIGN_X, ALIGN_Y + 100, DEFAULT_W + 300, DEFAULT_H + 250);
	   	
        addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });  
        
        setTitle("Welcome to Lucky Game!");
        setSize(500, 500);  
        setLayout(null);  
        setVisible(true);  
    }   
    
    public void addLabel(Label label, String labelString, int x, int y, int h, int w) {
    	label.setText(labelString); 
    	label.setBounds(x, y, h, w);  
	   	add(label);
    }
    
    public void addDropDown(Choice choice, String[] choiceOptions, int x, int y, int h, int w) {
      choice.setBounds(x, y, h, w);  

      for(int i=0; i<choiceOptions.length; i++) {
    	  choice.add(choiceOptions[i]);
      } 
      
      add(choice);  
    }
    
    public void addButton(Button button, int x, int y, int h, int w) {
	  button.setBounds(x, y, h, w);  
	  button.addActionListener(this);  
      add(button);
    }
    
    public void addTextArea(TextArea textArea, int x, int y, int h, int w) {
    	textArea.setBounds(x, y, h, w);  
    	textArea.setEditable(false);
    	add(textArea);
    }
    
    public void addTextField(TextField textField, int x, int y, int h, int w) {
    	textField.setBounds(x, y, h, w);  
    	add(textField);
    }
    
    public void actionPerformed(ActionEvent e) {   
    	try {
        	currentGameIndex = availableGameOptions.getSelectedIndex();
        	String inputFromUser = userInputs.getText();
        	games[currentGameIndex].collectUserInput(inputFromUser);
        	resultText.setText(games[currentGameIndex].toString());
        	writeToFile("report.txt", games[currentGameIndex].toString());
    	}catch(Exception ex) {
    		resultText.setText("Invalid Input!");
    	}
    }  
    
    private void writeToFile(String filename, String text) {
 	   try {
 		   FileWriter fileWriter = new FileWriter(filename);		   
 		   fileWriter.write(text);
 		   fileWriter.close();   
 	   }catch(IOException e) {
 		   System.out.println(e.getMessage());
 	   }	   
    }
    
    public static void main(String[] args) {  
    	games = new LuckyGame[3];
    	games[0] = new TattslottoGame("Tattslotto", "Saturday", 1 , 45);
    	games[1] = new TattslottoGame("OZ Lotto", "Tuesday", 1, 45);
    	games[2] = new PowerBallGame("PowerBall", "Thursday", 1 , 45);
   
        new LuckyGameApplet();  
    }  
}