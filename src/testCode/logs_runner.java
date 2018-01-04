package testCode;

public class logs_runner {
	public static void main(String[] args) {
		log.system.update("hey");
		log.test.test2.test3.test4.test5.method("YAY");
		log.manuUpdate("hey hey hey");
	}
}



/*

back end
	logs
		system
		startup
		entry (sign in/out) [we need to find a better name than "entry"]
		etc.
	email
		pdf
			create pdf
				header/footer
		get password
		email it
	config
		global vars
		files
			pull
			push
	database
		check
			check student ID db (make sure it exists, columns are set up correctly, table name is correct. If incorrect, send error)
			check logs db (exists?, if not. create it)
				create logs db
		student
			pull student name
			in time
			out time
front end
	window
		content
		menu bar
		preferences
			preferences constructor
	
---------
INTERFACES:

	

*/