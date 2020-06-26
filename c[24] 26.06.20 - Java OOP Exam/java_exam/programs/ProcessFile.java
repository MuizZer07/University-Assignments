import java.io.File;

public class ProcessFile {
	
	public static void main(String[] args) throws Exception {
		File file = new File("../demo");
		
		if(file.exists() && file.isDirectory()) {
			File[] files = file.listFiles();
			
			for(int i=0; i < files.length; i++) {
				System.out.println(files[i].getName());
			}
		}else {
			System.out.println("folder directory doesnt exist ");
		}
	}
	
}
