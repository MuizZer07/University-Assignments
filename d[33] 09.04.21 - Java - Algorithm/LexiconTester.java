import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LexiconTester {
	static String inputFile1Name = "in1.txt";
	static String inputFile2Name = "in2.txt";
	static String outputFileName = "out.txt";
	static File inputFile1;
	static File inputFile2;
	static FileWriter writer;
	static HashMap<String, Integer> lexicon;
	static ArrayList<String> unique_words;
	static String sorting_algorithm = "";
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		try {
			inputFile1 = new File(inputFile1Name);	
			inputFile2 = new File(inputFile2Name);
			writer = new FileWriter(outputFileName);
			
			if(!inputFile1.exists() || !inputFile2.exists()) {
				print("Input files doesn't exist!");
				return;
			}
			
			if(args.length > 0) {
				sorting_algorithm = args[0];
			}
			
			String input_string = readFile(inputFile1);
			input_string = input_string + readFile(inputFile2);
			ArrayList<String> words = processInput(input_string);
			prepareLexicon(words);
			unique_words = sort(sorting_algorithm, unique_words);
			writeWordsFromLexiconToFile();
		}catch(Exception e) {
			print(e.toString());
		}finally {
			long endTime = System.currentTimeMillis();
			long timeElapsed = endTime - startTime;
			 
//	        print("Execution time in milliseconds: " + timeElapsed);
		}
		
	}
	
	public static String getNeighbourString(ArrayList<String> neighbours) {
		String str = "";
		for(int i=0; i < neighbours.size(); i++) {
			String word = neighbours.get(i);
			str += word + ", ";
		}
		
		if(str.length() > 0)
			str = str.substring(0, str.length()-2);
		
		str = "[" + str + "]";
		return str;
	}
	
	public static ArrayList<String> getNeighbours(String word) {
		int len = word.length();
		ArrayList<String> neighbours = new ArrayList<String>();
		
		for(int i=0; i < unique_words.size(); i++) {
			String new_word = unique_words.get(i);
			if(new_word.length() == len) {
				int count = 0;
				for(int j=0; j < new_word.length(); j++) {
					if(new_word.charAt(j) != word.charAt(j)) {
						count++;
					}
				}
				
				if(count == 1) {
					neighbours.add(new_word);
				}
			}
		}
		
		return neighbours;
	}

	public static void prepareLexicon(ArrayList<String> words) {
		lexicon = new HashMap<String, Integer>();
		unique_words = new ArrayList<String>();
		
		for(int i=0; i < words.size(); i++) {
			String word = words.get(i);
			if(lexicon.get(word) == null) {
				lexicon.put(word, 1);
			}else {
				int frequency = lexicon.get(word) + 1;
				lexicon.put(word, frequency);
			}
		}
		
		for(String key: lexicon.keySet()){
			unique_words.add(key);
		}
	}
	
	public static ArrayList<String> processInput(String input_string) {
		ArrayList<String> words_list = new ArrayList<String>();
		
		input_string = input_string.toLowerCase();
		String[] words = input_string.split(" ");
		
		for(int i=0; i < words.length; i++) {
			String word = words[i].replaceAll("[0-9.,;!?'\\-\"+]", "");
			
			if(word.length() > 0) {
				words_list.add(word);
			}
		}
		
		return words_list;
	}

	public static ArrayList<String> sort(String sorting_algorithm, ArrayList<String> words) {
		switch(sorting_algorithm) {
			case "Y":
				return mergeSort(words, 0, words.size()-1);
			default:
				return bubbleSort(words);
		}
	}
	
 	public static ArrayList<String> mergeSort(ArrayList<String> words, int begin_index, int end_index)  
	{  
		if (begin_index < end_index){  
			int mid_index = (begin_index + end_index)/2;  
			words = mergeSort(words, begin_index, mid_index);  
			words = mergeSort(words , mid_index + 1, end_index);  
			words = merge(words, begin_index, mid_index, end_index);  
		}  
		
		return words;
	}  
	
	public static ArrayList<String> merge(ArrayList<String> words, int begin_index, int mid_index, int end_index){  
		int left_node = mid_index - begin_index + 1;  
		int right_node = end_index - mid_index;  
		  
		String[] LeftWordArray = new String [left_node];  
		String[] RightWordArray  = new String[right_node];  
		  
		for (int i=0; i < left_node; ++i)  
			LeftWordArray[i] = words.get(begin_index + i);  
		  
		for (int j=0; j < right_node; ++j)  
			RightWordArray[j] = words.get(mid_index + 1+ j);  
		  
		  
		int i = 0, j = 0;  
		int k = begin_index;  
		while (i < left_node && j < right_node){  
			if (LeftWordArray[i].compareTo(RightWordArray[j]) < 0){  
				words.set(k, LeftWordArray[i]);  
				i++;  
			}else{  
				words.set(k, RightWordArray[j]);
				j++;  
			}  
			k++;  
		}  
		
		while (i < left_node){  
			words.set(k, LeftWordArray[i]);
			i++;  
			k++;  
		}  
		  
		while (j < right_node){  
			words.set(k, RightWordArray[j]);  
			j++;  
			k++;  
		}  
		
		return words;
	}  
	
	public static ArrayList<String> bubbleSort(ArrayList<String> words) {  
        int n = words.size();  
        String tempString = "";  
        
         for(int i=0; i < n; i++){  
             for(int j=1; j < (n-i); j++){  
                  if(words.get(j-1).compareTo(words.get(j)) > 0){  
                	 tempString = words.get(j-1);
                	 words.set(j-1, words.get(j));
                	 words.set(j, tempString);
                 }      
             }  
         }  
  
         return words;
    }  
	
	public static void writeWordsFromLexiconToFile() {
		String output_string = "";
		for(int i=0; i < unique_words.size(); i++) {
			String word = unique_words.get(i);
			ArrayList<String> neighbours = getNeighbours(word);
			neighbours = sort(sorting_algorithm, neighbours);;
			output_string += word + " " + lexicon.get(word) + " " + getNeighbourString(neighbours) + "\n";
		}
		
		writeStringToFile(output_string);
	}
	
	public static String readFile(File file) {
		String str = "";
		
		try {
			Scanner reader = new Scanner(file);
			
		    while (reader.hasNextLine()) {
		       String data = reader.nextLine();
		       str += data + " ";
	        }
		    reader.close();
		}catch(Exception e) {
		
		}
		
		return str;
	}
	
	public static void print(String str) {
		System.out.println(str);
	}
	
	public static void writeStringToFile(String str) {
		try {
			writer.write(str);
			writer.close();
		}catch(Exception e) {
		
		}
	}
}
