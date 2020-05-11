import java.io.RandomAccessFile;
import java.io.File;
import java.util.*; 

public class HexEditor {
	public static final int rowCount = 16;
	public static String mode = "hex";
	public static boolean readOnly = false;
	public static Scanner scanner = new Scanner(System.in); 
	public static String prevQuery = "";
	
	public static void main(String[] args) {
		String filename;
		File file;
		RandomAccessFile raf; 
		
		if(args.length == 1){
			filename = args[0];
			file = new File(filename);		
			
			// check if file exists
			if(!file.exists()){
				System.out.println("INFO :: " + filename + " file not found in the directory. Would you like to create a new file?"
						+ "\n    - Press Y to create. \n    - press any key other than that to abort..");
				
				String decision = scanner.nextLine();
				
				if(decision.charAt(0) == 'Y' || decision.charAt(0) == 'y'){
					try {
						file.createNewFile();  // creating new file
						System.out.println("INFO :: " + filename + " is created.");
					}catch(Exception e) {
						System.out.println("ERROR :: Couldn't create " + filename);
					}
				}else{
					System.out.println("INFO :: Program aborted.");
				}
			}
			
			if(file.exists()) {
				String prevCmd = "";
				long rownumber = 0;
				long decimal_byte_address = 0;
				long prev_byte_address = 0;
				String fileAccess= "r";
				String access = "";
				
				if(file.canWrite()) {
					fileAccess= "rw";
				}else {
					readOnly = true;
					access = "[READ ONLY]";
				}
				
				try {
					raf = new RandomAccessFile(file, fileAccess);
					
					while(!prevCmd.equals("e") && !prevCmd.equals("exit")) {
						
						try {
							long byte_value = printHexTable(raf, rownumber, decimal_byte_address); // printing hex table from row 0
							
							// print file & byte information
							System.out.printf("\nByte Address: 0x%X (%d)", decimal_byte_address, decimal_byte_address );
							System.out.printf("\nByte Value:   0x%X (%d signed) (", byte_value, byte_value);
							System.out.print(Integer.parseUnsignedInt(Long.toString(byte_value)));
							System.out.print(" unsigned)");
							System.out.printf("\nFile Size:    0x%X (%d) %s %s", file.length(), file.length(), formatSize(file.length()), access);
							System.out.println("\nFile Name:    " + filename);
							
							// printing previous command
							System.out.println();
							if(!prevCmd.equals("")) {
								System.out.println(getCommand(prevCmd, decimal_byte_address, prev_byte_address));
							}
							
							System.out.print("Command: ");
							prevCmd = scanner.nextLine();
							prev_byte_address = decimal_byte_address;
							decimal_byte_address = executeCommand(prevCmd, decimal_byte_address, raf);
							rownumber = calculateRowNumber(decimal_byte_address);
							}
							catch(Exception e) {
								printValidCommands();
								System.out.print("Command: ");
								prevCmd = scanner.nextLine();
								prev_byte_address = decimal_byte_address;
								decimal_byte_address = executeCommand(prevCmd, decimal_byte_address, raf);
								rownumber = calculateRowNumber(decimal_byte_address);
							}
					}
					raf.close();  // close file
				}catch(Exception e) {
					System.out.println("ERROR:: Unable to open file.");
				}
			}	
		}else{
			System.out.println("ERROR :: Please provide filename to open the file in Hex Editor. e.g java HexEditor FILE_NAME");
		}
	}
	
	public static void printValidCommands() {
		System.out.println("ERROR :: Invalid Command. Please use a valid command. ");
		System.out.println("  List of Valid commands: ");
		System.out.println("  - Go To a Byte location: goto 1A, Shorthand: g 1A");
		System.out.println("  - Go To a Byte location: goto 1A, Shorthand: g 1A h");
		System.out.println("  - Go To a Byte location: goto 1 d, Shorthand: g 1 d");
		System.out.println("  - Jump To a Byte location: jump 1A, Shorthand: j 1A");
		System.out.println("  - Jump To a Byte location: jump 1A h, Shorthand: j 1A h");
		System.out.println("  - Jump To a Byte location: jump 1 d, Shorthand: j 1 d");
		System.out.println("  - Set a Value To a Byte location: set 1A, Shorthand: s 1A");
		System.out.println("  - Set a Value To a Byte location: set 1A h, Shorthand: s 1A h");
		System.out.println("  - Set a Value To a Byte location: set 1 d, Shorthand: s 1 d");
		System.out.println("  - Set a Value To a Byte location: set 'a' d, Shorthand: s 'a' d");
		System.out.println("  - Truncate from current location: truncate, Shorthand: t");
		System.out.println("  - Move to next page: next, Shorthand: n");
		System.out.println("  - Move to previous page: previous, Shorthand: p");
		System.out.println("  - Move to hex mode: hex, Shorthand: h");
		System.out.println("  - Move to ascii page: ascii, Shorthand: a");
		System.out.println();
	}
	
	public static long calculateRowNumber(long byte_address) {
		long row = 0;
		
		if(byte_address > 0) {
			row = byte_address/rowCount;
			
			if(row > 7) {
				row -= 7;
			}else {
				row = 0;
			}
		}
		
		return row;
	}
	
	
	public static long executeCommand(String cmd, long prev_byte_address, RandomAccessFile raf) {
		String cmd1 = cmd;
		cmd = cmd.toLowerCase();
		long decimal_byte_address = 0;
		String[] commands = cmd.split(" ");
		String[] commands1 = cmd1.split(" ");
		
		try {
			if(commands[0].equals("g") || commands[0].equals("goto")) {  // goto command
				decimal_byte_address = gotoOperation(commands);
			}
			
			else if(commands[0].equals("j") || commands[0].equals("jumo")) {  // jump command
				decimal_byte_address = jumpOperation(commands, prev_byte_address);
			}
			
			else if(commands[0].equals("s") || commands[0].equals("set")) {  // set command
				if(!readOnly) {
					boolean flag = setOperation(commands1, prev_byte_address, raf);
					if(flag) {
						decimal_byte_address = prev_byte_address + 1;
					}
				}
			}
			
			else if(commands[0].equals("t") || commands[0].equals("truncate")) {  // truncate command
				if(!readOnly) {
					truncateOperation(prev_byte_address, raf);
				}
			}
			
			else if(commands[0].equals("h") || commands[0].equals("hex")) {  // truncate command
				mode = "hex";
				decimal_byte_address = prev_byte_address;
			}
			
			else if(commands[0].equals("a") || commands[0].equals("ascii")) {  // truncate command
				mode = "ascii";
				decimal_byte_address = prev_byte_address;
			}
			
			else if(commands[0].equals("n") || commands[0].equals("next")) {  // next command
				int pageBytes = rowCount * rowCount;
				String command = "j " + pageBytes + " d";
				commands = command.split(" ");
				decimal_byte_address = jumpOperation(commands, prev_byte_address);
			}
			
			else if(commands[0].equals("p") || commands[0].equals("previous")) {  // previous command
				int pageBytes = rowCount * rowCount * -1;
				String command = "j " + pageBytes + " d";
				commands = command.split(" ");
				decimal_byte_address = jumpOperation(commands, prev_byte_address);
			}
			
			else if(commands[0].equals("f") || commands[0].equals("find")) {  // find command
				String query = "";
				
				if(commands1.length < 2) {
					if(prevQuery == "") {
						System.out.println("ERROR :: No search string provided.");
					}else {
						query = prevQuery;	
					}
				}else{
					for(int i=1; i<commands1.length; i++) {
						query += commands1[i];
					}
					prevQuery = query;
					
					
					long found_byte_address = findOperation(query, prev_byte_address, raf);
					decimal_byte_address = found_byte_address;
					if(found_byte_address == prev_byte_address) {
						System.out.println("ERROR :: Unable to find the search string.");
					}
				}
			}
			
			else if(commands[0].equals("e") || commands[0].equals("exit")) {  // exit command
				decimal_byte_address = prev_byte_address;
			}
			
			else {
				decimal_byte_address = prev_byte_address;
				printValidCommands();
			}
		}catch(Exception e){
			decimal_byte_address = prev_byte_address;
			printValidCommands();
			return decimal_byte_address;
		}		
		return decimal_byte_address;
	}
	
	
	public static long findOperation(String query, long byte_address, RandomAccessFile raf) {
		long decimal_byte_address = byte_address;
		long first_alpha = byte_address;
		boolean found = false;

		try {			
			first_alpha = decimal_byte_address;
			raf.seek(decimal_byte_address);
			int ch = raf.read();
			
			while(ch != -1 && !found) {
				first_alpha = decimal_byte_address;
				int i = 0;

				for(i=0; i < query.length(); i++) {
					int c = query.charAt(i);
					decimal_byte_address += 1;

					if(ch == -1) {
						break;
					}else if(ch != c){
						break;
					}
					ch = raf.read();
				}
				if(i == query.length()) {
					found = true;
				}

				ch = raf.read();
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		if(found) {
			return first_alpha;
		}else {
			return byte_address;
		}
	}
	
	
	public static boolean setOperation(String[] commands, long byte_address, RandomAccessFile raf) {
		int set_value = 0;
		boolean flag =  false;
		
		// length 2. means h/d not mentioned means hex
		if(commands.length == 2) {
			if(commands[1].startsWith("'")) {
				int ch = commands[1].charAt(1);
				if(ch > 0 && ch < 255) {
					set_value = ch;
				}
			}else {
				set_value = Integer.parseInt(commands[1], 16);	
			}
			flag = true;
		}
		
		// hex or decimal mentioned in the command
		else if(commands.length == 3) {
			String set_type = commands[2].toLowerCase();
			
			if(set_type.startsWith("h")) {  // jump is a hexadecimal value
				set_value = Integer.parseInt(commands[1], 16);
			}else if(set_type.startsWith("d")) {  // jump is a decimal value
				set_value = Integer.parseInt(commands[1]);
			}else {
				flag = false;
			}
			
			flag = true;
		}
		
		if(flag) {
			try {
				raf.seek(byte_address);
				raf.write(set_value);
			}catch(Exception e) {
				flag = false;
			}
		}
		
		return flag;
	}
	
	
	public static void truncateOperation(long prev_byte_address, RandomAccessFile raf) {
		
		try {
			raf.setLength(prev_byte_address+1);
		}catch(Exception e) {
			
		}
	}
	
	
	public static long jumpOperation(String[] commands, long prev_byte_address) {
		long decimal_byte_address = 0;
		
		// length 2. means h/d not mentioned means hex
		if(commands.length == 2) {
			long jump = Long.parseUnsignedLong(commands[1], 16);
			decimal_byte_address = prev_byte_address + jump; 
			
			if(decimal_byte_address < 0) {
				decimal_byte_address = 0;
			}
			
		}
		
		// hex or decimal mentioned in the command
		else if(commands.length == 3) {
			String jump_type = commands[2].toLowerCase();
			
			if(jump_type.startsWith("h")) {  // jump is a hexadecimal value
				long jump = Integer.parseInt(commands[1], 16);
				decimal_byte_address = prev_byte_address + jump; 
				
				if(decimal_byte_address < 0) {
					decimal_byte_address = 0;
				}
			}else if(jump_type.startsWith("d")) {  // jump is a decimal value
				long jump = Integer.parseInt(commands[1]);
				decimal_byte_address = prev_byte_address + jump; 
				
				if(decimal_byte_address < 0) {
					decimal_byte_address = 0;
				}
			}
		}

		return decimal_byte_address;
	}
	
	
	public static long gotoOperation(String[] commands) {
		long decimal_byte_address = 0;
		
		// length 2. means h/d not mentioned means hex
		if(commands.length == 2) {
			String goto_byte_address = commands[1];
			int last_char = goto_byte_address.charAt(goto_byte_address.length()-1);
			if(last_char >= 48 && last_char <= 57) {
				decimal_byte_address = Long.parseLong(goto_byte_address, 16);
			}else {
				String newStr = "";
				String newNumber = "";
				for(int i=0; i<goto_byte_address.length(); i++) {
					if(goto_byte_address.charAt(i) >= 48 && goto_byte_address.charAt(i) <= 57 || goto_byte_address.charAt(i) == 46) {
						newNumber += goto_byte_address.charAt(i);
					}else {
						newStr += goto_byte_address.charAt(i);
					}
				}
				
				Double new_byte_int = Double.parseDouble(newNumber);
				if(newStr.startsWith("k")) {
					decimal_byte_address = (long) (new_byte_int * 1024);
				}else if(newStr.startsWith("m")) {
					decimal_byte_address = (long) (new_byte_int * 1024 * 1024);
				}else if(newStr.startsWith("g")) {
					decimal_byte_address = (long) (new_byte_int * 1024 * 1024 * 1024);
				}if(newStr.startsWith("t")) {
					decimal_byte_address = (long) (new_byte_int * 1024 * 1024 * 1024 * 1024);
				}
			}
		}
		
		// hex or decimal mentioned in the command
		else if(commands.length == 3) {
			String addr_str = commands[1];
			String addr_type = commands[2].toLowerCase();
			
			if(addr_type.startsWith("h")) {  // byte address is a hexadecimal value
				int addr_int = Integer.parseInt(addr_str, 16); 
				if(addr_int > -1) {
					decimal_byte_address = Integer.parseInt(addr_str, 16); 
				}else {
					
				}
			}else if(addr_type.startsWith("d")) {  // byte address is a decimal value
				int addr = Integer.parseInt(commands[1]);
				if(addr > -1) {
					decimal_byte_address = addr;
				}else {
					
				}
			}
		}
		
		return decimal_byte_address;
	}
	
	
	public static String getCommand(String cmd, long byte_address, long prev_byte_address) {
		String cmd1 = cmd.toLowerCase().split(" ")[0];
		if(cmd1.equals("g") || cmd1.equals("goto")) {
			return String.format("Moved to 0x%X", byte_address);
		}
		
		else if(cmd1.equals("j") || cmd1.equals("jump")) {
			String jump = "";
			String[] commands = cmd.split(" ");
			long jump_value = 0;
			
			if(commands.length == 3 && commands[2].startsWith("d")) {
				jump_value = Integer.parseInt(commands[1]);
			}else {
				jump_value = Long.parseUnsignedLong(commands[1], 16);
			}
			
			if(prev_byte_address > byte_address) {
				jump = "backward";
			}else if(prev_byte_address < byte_address) {
				jump = "forward";
			}
			
			return String.format("Jumped to 0x%X address 0x%X bytes %s", byte_address, jump_value, jump);
		}
		
		else if(cmd1.equals("s") || cmd1.equals("set")) {
			if(!readOnly) {
				String[] commands = cmd.split(" ");
				int byte_value = 0;
				
				if(commands.length>3 && commands[2].startsWith("d")) {
					byte_value = Integer.parseInt(commands[1]);
				}else {
					if(commands[1].startsWith("'")) {
						int ch = commands[1].charAt(1);
						if(ch > 0 && ch < 255) {
							byte_value = ch;
						}
					}else {
						byte_value = Integer.parseInt(commands[1], 16);
					}
				}
				
				return String.format("Set byte to address 0x%X to hex value 0x%X", byte_address, byte_value);
			}else {
				return "File is read only.";
			}
		}
		
		else if(cmd1.equals("a") || cmd1.equals("ascii")) {
			return "Swiched to ASCII mode";
		}
		
		else if(cmd1.equals("h") || cmd1.equals("hex")) {
			return "Swiched to Hex mode";
		}
		
		else if(cmd1.equals("t") || cmd1.equals("truncate")) {
			if(!readOnly) {
				return String.format("File truncated from address 0x%X", prev_byte_address);
			}else {
				return "File is read only";
			}
		}
		
		else if(cmd1.equals("n") || cmd1.equals("next")) {
			return "Moved to Next Page";
		}
		
		else if(cmd1.equals("p") || cmd1.equals("previous")) {
			return "Moved to Previous Page";
		}
		
		else if(cmd.startsWith("f")) {
			String keyword = "";
			if(cmd.split(" ").length>1) {
				keyword = cmd.split(" ")[1];
			}
			return "Find " + keyword;
		}
		
		else {
			return "Command not Valid";
		}
	}
	
	
	public static String repeat(String str, int count) {
		String newStr = "";
		for(int i=0; i<count; i++) {
			newStr += str;
		}
		return newStr;
	}

	
	public static String formatSize(long sizeInBytes) {
		String size = "";
		long range1 = 1024;
		long range2 = range1 * 1024;
		
		if(sizeInBytes < range1) {
			size = sizeInBytes + " bytes";
		}
		
		if(sizeInBytes >= range1 && sizeInBytes < range2){
			double sizeInBytes1 = (double) sizeInBytes/range1;
			size = String.format("%.2f", sizeInBytes1) + " KiB";
		}

		range1 = range2;
		range2 = range1 * 1024;
		
		if(sizeInBytes >= range1 && sizeInBytes < range2){
			double sizeInBytes1 = (double) sizeInBytes/range1;
			size = String.format("%.2f", sizeInBytes1) + " MiB";
		}

		range1 = range2;
		range2 = range1 * 1024;
		
		if(sizeInBytes >= range1 && sizeInBytes < range2){
			double sizeInBytes1 = (double) sizeInBytes/range1;
			size = String.format("%.2f", sizeInBytes1) + " GiB";
		}

		range1 = range2;
		range2 = range1 * 1024;
		
		if(sizeInBytes >= range1 && sizeInBytes < range2){
			double sizeInBytes1 = (double) sizeInBytes/range1;
			size = String.format("%.2f", sizeInBytes1) + " TiB";
		}
		
		range1 = range2;
		range2 = range1 * 1024;
		
		if(sizeInBytes >= range1 && sizeInBytes < range2){
			double sizeInBytes1 = (double) sizeInBytes/range1;
			size = String.format("%.2f", sizeInBytes1) + " PiB";
		}
		
		return size;
	}
	
	
	public static long printHexTable(RandomAccessFile raf, long rowNumber, long decimal_byte_address) {
		long byte_addr_row_number = 2;
		long last_rowNumber = rowNumber + (rowCount*rowCount) - rowCount; 
		String last_rowHexString = Long.toHexString(last_rowNumber);
		int maxRowLength = last_rowHexString.length();
				
		if(decimal_byte_address > 0) {
			byte_addr_row_number = decimal_byte_address/ rowCount + 2;
		}
		
		long byte_addr_offset = decimal_byte_address % rowCount + 1;
		long byte_value = 0;
		
		// seek file the cursor to the address
		try {
			raf.seek(rowNumber * rowCount);
		}catch(Exception e) {
			
		}
		
		long newRow = rowNumber;
		for(long i=rowNumber; i <= rowNumber+rowCount+2; i++){
			for(int j=0; j <= rowCount; j++) {
				// first row / header row
				if(i == rowNumber) {
					drawHeader(j, maxRowLength);
				}
				
				// second row / separator row
				if(i == rowNumber+1) {
					drawSeparator(j, false, maxRowLength);
				}
				
				// inner grid for value from file
				if(i != rowNumber && i != rowNumber+1 && i <= rowNumber+rowCount+1 && j != 0) {
					if(mode.equals("hex")) {
						byte_value = printHexValuesFromFile(raf, byte_addr_row_number, byte_addr_offset, byte_value, i, j);
					}else {
						byte_value = printASCIIValuesFromFile(raf, byte_addr_row_number, byte_addr_offset, byte_value, i, j);	
					}
				}
				
				// last row border
				if(i == rowNumber+rowCount+2) {					
					drawSeparator(j, true, maxRowLength);
				}
				
				// last column border
				if(i > rowNumber+1 && j == rowCount && i < rowNumber+rowCount+2) {
					System.out.printf("\u2502");
				}
			}
			
			// row numbers
			if(i > rowNumber && i < rowNumber+rowCount+1){
				System.out.println();
				int repeat = 2;
				String newRowString = Long.toHexString(newRow);
				int newRowLength = newRowString.length();
				
				int padding = maxRowLength - newRowLength;
				
				System.out.printf("%s%X \u2502", repeat(" ", repeat+padding), newRow);
				newRow = newRow += 16; // first column value			
			}
			
			// new row for last row
			if(i == rowNumber+rowCount+1) {
				System.out.println();
			}
		}
		return byte_value;
	}
	
	
	public static long printHexValuesFromFile(RandomAccessFile raf, long byte_addr_row_number, long byte_addr_offset, long byte_value, long i, int j) {
		try {
			if(j > 16) {
				System.out.printf(" ");
			}
			int ch = raf.read();
			if(ch != -1) {
				if(ch > 15) {
					if(i == byte_addr_row_number && j == byte_addr_offset) { // if selected
						System.out.printf("[%X]", ch);
						byte_value = ch;
					}else {
						System.out.printf(" %X ", ch);
					}
				}else {
					if(i == byte_addr_row_number && j == byte_addr_offset) {  // if not selected
						System.out.printf(" [%X]", ch);
						byte_value = ch;
					}else {
						System.out.printf("  %X ", ch);
					}
				}
			}else {
				if(i == byte_addr_row_number && j == byte_addr_offset) {
					System.out.printf(" [] ");
				}else {
					System.out.printf("    ");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return byte_value;
	}
	

	public static long printASCIIValuesFromFile(RandomAccessFile raf, long byte_addr_row_number, long byte_addr_offset, long byte_value, long i, int j) {
		try {
			if(j > 16) {
				System.out.printf(" ");
			}
			int ch = raf.read();
			if(ch != -1) {
				if(i == byte_addr_row_number && j == byte_addr_offset) { // if selected
					if(ch > 32 && ch < 127) {
						System.out.printf(" [%c]", ch);
					}else {
						filterControlCharacters(ch, true);
					}
					byte_value = ch;
				}else {
					if(ch > 32 && ch < 127) {  // not selected
						System.out.printf("  %c ", ch);
					}
					else {
						filterControlCharacters(ch, false);
					}
				}
			}else {
				if(i == byte_addr_row_number && j == byte_addr_offset) {
					System.out.printf(" [] ");
				}else {
					System.out.printf("    ");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return byte_value;
	}
	
	
	public static void filterControlCharacters(int ch, boolean selected) {
		if(selected) {
			if(ch == 0) {
				System.out.printf("[\u25AC] ");
			}
			else if(ch == 10) {
				System.out.printf("[LF]");
			}
			else if(ch == 32 || ch == 13) {
				System.out.printf(" [\u25CF]");
			}
			else {
				System.out.printf(" [\u25BC]");
			}
		}else {
			if(ch == 0) {
				System.out.printf("  \u25AC ");
			}
			else if(ch == 10) {
				System.out.printf(" LF ");
			}
			else if(ch == 32 || ch ==13) {
				System.out.printf("  \u25CF ");
			}
			else {
				System.out.printf("  \u25BC ");
			}	
		}
	}
	
	
	public static void drawHeader(int j, int maxLength) {
		int padding = 0;
		if(maxLength > 2) {
			padding = maxLength-2;
		}
		
		if(j == 0) {
			System.out.printf("%sH  \u2502", repeat(" ", 2+padding));
		}else {
			System.out.printf(" %X \u2502", j-1);	
		}	
		
		if(j == rowCount) {
			System.out.println();
		}
	}
	
	
	public static void drawSeparator(int j, boolean lastRow, int maxLength) {
		int repeat = 2;
		int padding = 0;
		
		if(maxLength > 2) {
			padding = maxLength-repeat;
		}
		
		if(j-1 < 16) {
			repeat = 3;
		}else {
			repeat = 4;
		}	
		if(j == 0) {
			System.out.print(repeat("\u2500", repeat+padding));
		}else {
			System.out.print(repeat("\u2500", repeat));
		}

		
		if(j == 0) {
			if(!lastRow) {
				System.out.printf("%s\u253C", repeat("\u2500", 2));
			}else {
				System.out.printf("%s\u2534", repeat("\u2500", 2));
			}
		}else {
			if(!lastRow) {
				System.out.printf("\u2534");
			}else {
				System.out.printf("\u2500");
			}
		}

		if(j == rowCount) {
			if(!lastRow) {
				System.out.printf("\u2510");
			}else {
				System.out.printf("\u2518");
			}
		}
	}

	
	public static void drawLastRowBoarder(int j) {
		int repeat = 0;
		
		if(j-1 < 16) {
			repeat = 3;
		}else {
			repeat = 4;
		}	
		System.out.print(repeat("\u2500", repeat));
		
		if(j == 0) {
			System.out.printf("\u2500\u2500\u2534");
		}else {
			System.out.printf("\u2500");
		}
		
		if(j == rowCount) {
			System.out.printf("\u2518");
		}
	}
}
