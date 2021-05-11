import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class LexiconTester {
	
	public static void main(String[] args) {
		String inputFile1Name = "";

		try {		
			if(args.length > 0) {
				// first command argument is indicating the file name
				inputFile1Name = args[0]; 
			}

			// read file as string, manipulate and process string, get all words into an array
			String[] words = new Scanner(new File(inputFile1Name)).useDelimiter("\\Z").next().toLowerCase().replace("\n", " ").replaceAll("[^a-z ]", "").split(" ");
			
			// sort all input words
			words = mergeSort(words, 0, words.length-1);
			
			// initialize variables
			// initialized only once to make sure there is no extra variable
			String word = "";
			String[] words_list = new String[words.length];
			int[] uniqueWordFrequency = new int[words_list.length];
			String[] lengthArray = new String[50];
			int index = -1;
			int i = 0;
			int ind = 0;
			String output_string = "";
			String neighbors = "";
			int count = 0;
			int k = 0;
			int j = 0;
			String prev_word = "";
			String LengthList = "";
			String[] potentialNeighbors;

			// loop through all the words
			for(i=0; i < words.length; i++) {
				word = words[i].trim();

				// check if word has minimum length 1
				if(word.length() > 0) {

					// checking with previous word
					// as the list is alphabetically sorted if current word matches with the previous one
					// we can count word frequency
					// otherwise its a new word
					if(prev_word.equals(word)){
						uniqueWordFrequency[index] += 1;
					}else{
						prev_word = words[i];
						uniqueWordFrequency[++index] = 1;
						words_list[index] = words[i];

						// storing words which have same length as they are potential neighbors
						// as comma separated string
						if(lengthArray[prev_word.length()] != null)
							lengthArray[prev_word.length()] += "," + words[i];
						else
							lengthArray[prev_word.length()] = words[i];
					}
				}
			}

			// remove extra spaces from the arrays
			// as we removed duplicate words
			words_list = Arrays.copyOfRange(words_list, 0, index);
			uniqueWordFrequency = Arrays.copyOfRange(uniqueWordFrequency, 0, index);

			// loop through all unique words
			for(i=0; i < index; i++) {
				neighbors = "";
				ind = words_list[i].length();

				// get potential neighbors, words who have the same length
				LengthList = lengthArray[ind];

				// String to array by spliting with comma
				potentialNeighbors = LengthList.split(",");
				
				// loop through potential neighbors
				for(k=0; k < potentialNeighbors.length; k++) {	
					count = 0;
						
					// match each character and count differences
					for(j=0; j < ind; j++) {
						if(potentialNeighbors[k].charAt(j) != words_list[i].charAt(j)) {	
							// if more than 1 characters are different
							// they are not neighbors
							// break loop, check for next potential neighbor							
							if(++count > 1)
								break;
							
						}
					}

					// if only one character is different they are neighbor
					// create neighbor string
					if(count == 1){
						if(neighbors.compareTo("") == 0){
							neighbors += potentialNeighbors[k];
						}else{
							neighbors += ", " + potentialNeighbors[k];
						}
					}
				}

				// store current word, its frequency and neighbor string to output later
				output_string += words_list[i] + " " + uniqueWordFrequency[i] + " [" + neighbors + "]\n";
			}
			
			// create output file and write output string
			FileWriter writer = new FileWriter("out.txt");
			writer.write(output_string);
			writer.close();
		}catch(Exception e) {
			System.out.println("Error: " + e.toString());
		}		
	}

	// merge sort algorithm
	public static String[] mergeSort(String[] words, int begin_index, int end_index)  
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
	public static String[] merge(String[] words, int begin_index, int mid_index, int end_index){  
		int left_node = mid_index - begin_index + 1;  
		int right_node = end_index - mid_index;  

		String[] LeftWordArray = new String [left_node];  
		String[] RightWordArray  = new String[right_node];  
		  
		for (int i=0; i < left_node; ++i)  
			LeftWordArray[i] = words[begin_index + i];  
		  
		for (int j=0; j < right_node; ++j)  
			RightWordArray[j] = words[mid_index + 1 + j];  
		  
		int i = 0, j = 0;  
		int k = begin_index;  
		while (i < left_node && j < right_node){  
			if (LeftWordArray[i].compareTo(RightWordArray[j]) < 0){  
				words[k] = LeftWordArray[i];  
				i++;  
			}else{  
				words[k] = RightWordArray[j];
				j++;  
			}  
			k++;  
		}  
		
		while (i < left_node){  
			words[k] = LeftWordArray[i];
			i++;  
			k++;  
		}  
		  
		while (j < right_node){  
			words[k] = RightWordArray[j];  
			j++;  
			k++;  
		}  
	
		return words;
	}  
}
