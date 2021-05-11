import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LexiconTester {
	// declare static variables
	static String inputFile1Name = "in1.txt";
	static String inputFile2Name = "in2.txt";
	static String outputFileName = "out.txt";
	static File inputFile1;
	static File inputFile2;
	static FileWriter writer;
	static ArrayList<Integer> uniqueWordFrequency;
	static ArrayList<String> unique_words;
	static String sorting_algorithm = "";
	
	public static void main(String[] args) {
		try {
			// initialize file readers
			inputFile1 = new File(inputFile1Name);	
			inputFile2 = new File(inputFile2Name);
			
			// initialize file writer
			writer = new FileWriter(outputFileName);
			
			// check if read files exist
			if(!inputFile1.exists() || !inputFile2.exists()) {
				print("Input files doesn't exist!");
				return;
			}
			
			// check if any command argument given
			if(args.length > 0) {
				// first command argument is indicating the alternative sorting algorithm
				// in this assignment, default is bubble sort
				// alternate algorithm is merge sort
				sorting_algorithm = args[0]; 
			}
			
			// read first file and store as string
			String input_string = readFile(inputFile1);
			
			// read second file and store as string
			input_string = input_string + readFile(inputFile2);
			
			// process input string and get list of words
			ArrayList<String> words = processInput(input_string);
			
			// sort all words
			words = sort(sorting_algorithm, words);
			
			// remove duplicate words and count frequency
			prepareUniqueWords(words);
			
			// write to output file
			writeWordsFromLexiconToFile();
		}catch(Exception e) {
			print(e.toString());
		}		
	}

	// get neighbour word string for output 
	public static String getNeighbourString(ArrayList<String> neighbours) {
		String str = "";
		
		// loop through the word list and add in a single string
		for(int i=0; i < neighbours.size(); i++) {
			String word = neighbours.get(i);
			str += word + ", ";
		}
		
		// remove comma and space from the last word
		if(str.length() > 0)
			str = str.substring(0, str.length()-2);
		
		// add brackets
		str = "[" + str + "]";
		
		// return string
		return str;
	}
	
	// find neighbour words for a single word
	public static ArrayList<String> getNeighbours(String word) {
		// get length
		int len = word.length();
		ArrayList<String> neighbours = new ArrayList<String>();
		
		// loop through unique words
		for(int i=0; i < unique_words.size(); i++) {
			String new_word = unique_words.get(i);
			
			// check if the word length matches
			if(new_word.length() == len) {
				int count = 0;
				
				// match each character and count differences
				for(int j=0; j < new_word.length(); j++) {
					if(new_word.charAt(j) != word.charAt(j)) {
						count++;
					}
				}
				
				// if only one character mismatches, it is a neighbour
				if(count == 1) {
					// add into the array
					neighbours.add(new_word);
				}
			}
		}
		
		return neighbours;
	}

	// prepare unique word list by removing duplicates and counting frequency for each word
	public static void prepareUniqueWords(ArrayList<String> words) {
		unique_words = new ArrayList<String>();
		uniqueWordFrequency = new ArrayList<Integer>();
		
		// loop through eacg word
		for(int i=0; i < words.size(); i++) {
			String word = words.get(i);
			
			// if already in the unique word list
			if(unique_words.contains(word) == true) {
				// if word already exists in unique word list
				// get the index 
				int index = unique_words.indexOf(word);
				
				// add one to the value of that index in the frequency array
				int value = uniqueWordFrequency.get(index) + 1;
				
				// set the value
				uniqueWordFrequency.set(index, value);
			}else {
				// if word doesn't exist
				// add the word to unique list
				unique_words.add(word);
				
				// add frequency 1
				uniqueWordFrequency.add(1);
			}
		}
	}
	
	// process input string
	public static ArrayList<String> processInput(String input_string) {
		ArrayList<String> words_list = new ArrayList<String>();
		
		// convert whole string to lower case
		input_string = input_string.toLowerCase();
		
		// split the words from the string
		String[] words = input_string.split(" ");
		
		// loop through the words
		for(int i=0; i < words.length; i++) {
			// remove digits & symbols from each word
			String word = words[i].replaceAll("[0-9.,;!?'\\-\"+]", "");
			
			// check if word has minimum one length
			if(word.length() > 0) {
				// add to word list
				words_list.add(word);
			}
		}
		
		return words_list;
	}

	// sort list of words
	public static ArrayList<String> sort(String sorting_algorithm, ArrayList<String> words) {
		// check for sorting algorithm
		switch(sorting_algorithm) {
			case "Y":
				// alternative algorithm
				return mergeSort(words, 0, words.size()-1);
			default:
				// default sorting algorithm
				return bubbleSort(words);
		}
	}

	// merge sort algorithm
 	public static ArrayList<String> mergeSort(ArrayList<String> words, int begin_index, int end_index)  
	{  
 		// split array
		if (begin_index < end_index){  
			// find mid point to split
			int mid_index = (begin_index + end_index)/2; 
			
			// sort first half
			words = mergeSort(words, begin_index, mid_index); 
			
			// sort second half
			words = mergeSort(words , mid_index + 1, end_index); 
			
			// merge two halves
			words = merge(words, begin_index, mid_index, end_index);  
		}  
		
		return words;
	}  
	
 	// merge sort algorithm
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
	
	// bubble sort algorithm
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
	
	// write final word list with neighbors and frequency
	public static void writeWordsFromLexiconToFile() {
		String output_string = "";
		for(int i=0; i < unique_words.size(); i++) {
			String word = unique_words.get(i);
			ArrayList<String> neighbours = getNeighbours(word);
			neighbours = sort(sorting_algorithm, neighbours);
			output_string += word + " " + uniqueWordFrequency.get(i) + " " + getNeighbourString(neighbours) + "\n";
		}
		
		writeStringToFile(output_string);
	}
	
	// read file as string
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
	
	// print string 
	public static void print(String str) {
		System.out.println(str);
	}
	
	// write string to file
	public static void writeStringToFile(String str) {
		try {
			writer.write(str);
			writer.close();
		}catch(Exception e) {
		
		}
	}
}
