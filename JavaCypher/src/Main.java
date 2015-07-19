
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		boolean READ = false;
		if(READ){
			String myData = "{id:1, Lucía, age:25, country:España}";
			ToolsCipher.saveData(myData);
		}
		else{
			String data = ToolsCipher.getData();
			System.out.println(data);
			
		}
		
		

	}

}
