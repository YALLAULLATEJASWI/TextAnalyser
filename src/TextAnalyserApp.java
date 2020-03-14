		import java.io.BufferedReader;
		import java.io.IOException;
		import java.io.InputStreamReader;

		public class TextAnalyserApp {

		    private void start() throws Exception {
		        System.out.println("--------------------------------------TEXT STATISTICAL ANALYZER---------------------------------");
		        System.out.println("Rule1: The input string should end with #. If not it is considered as invalid input");
		        System.out.println("Rule2: Words should be separated by consecutive 2 non-alphanumeric character.");
		        System.out.println("Note: Remember Rule 1 when applying Rule 2");
		        boolean repeat = true;
		        while(repeat) {
		            loadData();
		            run();
		            System.out.println("Do you want to continue thsi program: (Yes/YES/Y/y or No/NO/N/n)");
		            String userInput = reader.readLine().toLowerCase();
		            if(userInput.equals("no") || userInput.equals("n") )
		                repeat = false;
		        }
		    }

		    private void loadData() throws Exception {
		        System.out.println("Enter the separators count between words");
		        separatorCountBetweenWords = Integer.valueOf(reader.readLine());
		        if(separatorCountBetweenWords < 0) {
		            System.out.println("Error: Please enter valid positive integer");
		            return;
		        }
		        System.out.println("Enter the input text:");
		        input = reader.readLine();
		        if(!input.endsWith("#")) {
		            System.out.println("Error: Please follow rules to enter valid input");
		            System.exit(1);;
		        }
		        if(separatorCountBetweenWords > input.length()) {
		            System.out.println("Separator cannot be more than input data.");
		            System.exit(1);;
		        }
		        if(separatorCountBetweenWords + 1 == input.length()) {
		            System.out.println("No data to analyse.");
		            System.exit(1);
		        }
		        System.out.println("Enter the substring to be matched in words");
		        matchSubstring = reader.readLine();
		    }

		    public static void main(String[] args) throws Exception {
		        TextAnalyserApp app = new TextAnalyserApp();
		        app.start();
		    }

		    private void run() {
		        TextAnalyser analyser = new TextAnalyser(separatorCountBetweenWords);
		        analyser.generateReport(input, matchSubstring);
		    }

		    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    private StringBuilder sb;
		    private String input;
		    private String matchSubstring;
		    private int separatorCountBetweenWords;
	}
