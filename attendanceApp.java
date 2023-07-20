package application;

//import java.io.*;
import java.util.*;

//import java.io.File;
import java.util.Collection;
import java.util.ArrayList;

public class attendanceApp {
	String rosterFilename;
	String swipeDataFilename;
	public attendanceApp(String rosterFilename, String swipeDataFilename) {
		super();
		this.rosterFilename = rosterFilename;
		this.swipeDataFilename = swipeDataFilename;
	}
		
	public String getRosterFilename() {
		return rosterFilename;
	}

	public void setRosterFilename(String rosterFilename) {
		this.rosterFilename = rosterFilename;
	}

	public String getSwipeDataFilename() {
		return swipeDataFilename;
	}

	public void setSwipeDataFilename(String swipeDataFilename) {
		this.swipeDataFilename = swipeDataFilename;
	}



	public Collection<String>  list_students_not_in_class(Collection<String> roster, Collection<String> swipeData) {
		
		Collection<String> tempSwipeData = new ArrayList<>();
		//loops through the swipe data
		for (String record : swipeData) {
			//splits the data
			String [] parts = record.split(", ");
			String firstName = parts[0];
			String lastName = parts[1];
			tempSwipeData.add(firstName + ", " + lastName);
		}

		Collection<String> tempRoster = new ArrayList<>();
		//loops through the roster
		for (String record2 : roster) {
			//splits the data
			String [] parts = record2.split(", ");
			String firstName2 = parts[0];
			String lastName2 = parts[1];
			tempRoster.add(firstName2 + ", " + lastName2);
		}
		
		Collection<String> studentsNotInClass = new ArrayList<>();
		for (String student : tempRoster) {
			//compares the roster and swipe data
			if (!tempSwipeData.contains(student)) {
				studentsNotInClass.add(student);
			}
		}
		return studentsNotInClass;
	}
	
	
	public void list_all_times_checking_in_and_out(Collection<String> swipeData, String name) {
		//splits the student name on space
	    String[] newName = name.split(" ");
	    
	    System.out.println("****** List all swipe in and out for a student *******");
	    //loops through the swipeData and checks if the student is contained in the swipeData
	    for (String record2 : swipeData) {
	    		if (record2.contains(newName[0]) && record2.contains(newName[1])) {
	    			System.out.println(record2);
	    		}
	    }
	    
	}

	
	public void list_all_times_checked_in(Collection<String> swipeData) {
		System.out.println("****** Check in times for all students who attended***\n");
		//creates map and collection data structures
	    Map<String, String> firstSwipes = new HashMap<>();
	    Collection<String> results = new ArrayList<>();
	    
	    //loops through the swipeData
	    for (String record : swipeData) {
	    	//splits the data on comma
	        String[] parts = record.split(", ");
	        String name = parts[0] + ", " + parts[1];
	        String time = parts[2];
	        //checks if the map contains the name if not puts it in it 
	        if (!firstSwipes.containsKey(name)) {
	            firstSwipes.put(name, time);
	            results.add(record);
	        }
	    }
	    for (String ans : results) {
	    	//prints the list
	    	System.out.println(ans);
	    }
	}
	
	
	public void list_students_late_to_class(Collection<String> swipeData, String timeStamp) {
		//create variables
		boolean myBool = false;
		String[] givenTime = timeStamp.split(":");
		int givenHour = Integer.parseInt(givenTime[0]);
		int givenMinute = Integer.parseInt(givenTime[1]);
		int givenSecond = Integer.parseInt(givenTime[2]);
		
		//loop over the log data
		for (String record : swipeData) {
			//splits the log and time 
			String[] parts = record.split(", ");
			String[] logTime = parts[2].split(":"); 
			int logHour = Integer.parseInt(logTime[0]);
			int logMinute = Integer.parseInt(logTime[1]);
			int logSecond = Integer.parseInt(logTime[2]);
			//checks if the given time is less than the checked in time for a particular student
			if (givenHour < logHour) {
				myBool = true;
			}
			else if (givenMinute < logMinute) {
				myBool = true;
			}
			else if (givenSecond < logSecond) {
				myBool = true;
			}
			//prints students late to class
			if (myBool == true) {
				System.out.println(record);
			}
		}
	}
	
	
	public void get_first_student_to_enter(Collection<String> swipeData){
		//creates string variables
		String previousDate = "";
	    String firstStudent = "";
	    String firstTime = "";
	    
	    //loops through the swipe data
	    for (String record : swipeData) {
	    	//splits the data
	        String[] parts = record.split(", ");
	        String studentName = parts[0] + ", " + parts[1];
	        String time = parts[2];
	        String date = parts[3];
	        
	        //checks if the previous date does not equal the date
	        if (!date.equals(previousDate)) {
	        	//checks if the first student is not empty
	            if (!firstStudent.isEmpty()) {
	                System.out.println("**** First student to enter on " + previousDate + "****");
	                System.out.println(firstStudent);
	            }
	            //sets the date, student and time
	            previousDate = date;
	            firstStudent = studentName;
	            firstTime = time;
	        } else {
	        	//splits the time and converts to int
	            String[] firstTimeParts = firstTime.split(":");
	            int firstHour = Integer.parseInt(firstTimeParts[0]);
	            int firstMinute = Integer.parseInt(firstTimeParts[1]);
	            int firstSecond = Integer.parseInt(firstTimeParts[2]);
	            
	            //splits the time and converts to int
	            String[] currentTimeParts = time.split(":");
	            int currentHour = Integer.parseInt(currentTimeParts[0]);
	            int currentMinute = Integer.parseInt(currentTimeParts[1]);
	            int currentSecond = Integer.parseInt(currentTimeParts[2]);
	            //checks if the currentHout is less than firstHour
	            if (currentHour < firstHour) {
	            	firstStudent = studentName;
	                firstTime = time;
	            }
	            else if (currentHour == firstHour && currentMinute < firstMinute) {
	            	firstStudent = studentName;
	                firstTime = time;
	            }
	            else if (currentHour == firstHour && currentMinute == firstMinute && currentSecond < firstSecond) {
	                firstStudent = studentName;
	                firstTime = time;
	            }
	        }
	    }
	   //Print the first student for the last date
	    if (!firstStudent.isEmpty()) {
	    	System.out.println("**** First student to enter on " + previousDate + "****");
            System.out.println(firstStudent);	    }
	}
	        
	    
	public void print_attendance_data_for_student(Collection<String> swipeData, String name) {
		//creates variables and lists
		String[] newName = name.split(" ");
		Collection<String> results = new ArrayList<>();		
		
	    System.out.println("****** List all swipe in and out for a student *******");
	    //loops over the log
	    for (String record : swipeData) {
	    	//splits the record
	    	String[] parts = record.split(", ");
	    		//checks if the name equals the given name and adds it to the results arrayList
	    		if (parts[0].equals(newName[0]) && parts[1].equals(newName[1])) {
	    			results.add(parts[2] + ", " + parts[3]);
	    		}
	    }
		System.out.println(newName[0] + ", " + newName[1] + " "+ results);

	}
	
	
	public boolean is_present(Collection<String> swipeData, String name, String date) {
		boolean ans = false;
		
		//loops through the swipeData
			for (String records : swipeData) {
				String[] parts = records.split(", ");
				String curr_name = parts[0] + " " + parts[1];
				//check if the dates equals 
				if (parts[3].equals(date)) {
					//checks if the names equal
					if (curr_name.equals(name)) {
						ans = true;
					}
				}
			}
		
		return ans;
	}
	
	
	public void list_all_students_checked_in(Collection<String> swipeData, String date) {
		Collection<String> result = new ArrayList<>();	
		//loops through the swipeData
		for (String records : swipeData) {
			String[] parts = records.split(", ");
			//check if the dates equals 
			if (parts[3].equals(date)) {
				result.add(parts[0] + ", " + parts[1]);
			}
		}
		System.out.println("**** Students present on this date ****\n");
		for (String record2 : result) {
			//prints the result
			System.out.println(record2);
		}
	}
	
	
	public void list_all_students_checked_in_before(Collection<String> swipeData, String date, String time) {
		//create variables
		Collection<String> results = new ArrayList<>();
		
		String[] givenTime = time.split(":");
		int givenHour = Integer.parseInt(givenTime[0]);
		int givenMinute = Integer.parseInt(givenTime[1]);
		int givenSecond = Integer.parseInt(givenTime[2]);
		
		//loop over the log data
		for (String record : swipeData) {
			//splits the log and time 
			String[] parts = record.split(", ");
			String[] logTime = parts[2].split(":"); 
			
			int logHour = Integer.parseInt(logTime[0]);
			int logMinute = Integer.parseInt(logTime[1]);
			int logSecond = Integer.parseInt(logTime[2]);
			
			//checks if the date matches
			if (parts[3].equals(date)) {
				//checks if the given time is less than the checked in time for a particular student
				//adds the student to the list
				if (logHour < givenHour) {
					results.add(parts[0] + ", " + parts[1]);
				}
				else if (logHour == givenHour && logMinute < givenMinute) {
					results.add(parts[0] + ", " + parts[1]);
				}
				else if (logHour == givenHour && logMinute == givenMinute && logSecond < givenSecond) {
					results.add(parts[0] + ", " + parts[1]);
				}
			}
			
		}
		//prints the names
		System.out.println("**** Those present on date & before a time assigned ****\n");
		for (String record2 : results) {
			System.out.println(record2);
		}
		print_count(results);
		
	}
		
	
	
	public void list_students_attendance_count(Collection<String> swipeData, int days) {
		List<String> results = new ArrayList<>();
	    String previousDate = "";
	    String currStudent = "";
	    int count = 0;

	    // Loop through the swipe data
	    for (String record : swipeData) {
	        // Split the data
	        String[] parts = record.split(", ");
	        String studentName = parts[0] + ", " + parts[1];
	        String time = parts[2];
	        String date = parts[3];

	        // Check if the previous date does not equal the current date
	        if (!date.equals(previousDate)) {
	            if (!currStudent.isEmpty() && count >= days) {
	                // Add the current student to the results list
	                results.add(currStudent);
	            }
	            // Reset the student count and current student name for the new date
	            count = 0;
	            previousDate = date;
	            currStudent = studentName;
	        }

	        // Increment the attendance count for the current student
	        count++;
	    }

	    // Check the attendance count for the last student in the swipe data
	    if (!currStudent.isEmpty() && count >= days) {
	        results.add(currStudent);
	    }

	    System.out.println("**** Those who attended " + days + " classes ****");
	    for (String record2 : results) {
	        System.out.println(record2);
	    }
	}
	

	
	public void print_query_list(Collection<String> query_list) {
		System.out.println("*** Query list ***");
		//loops over the query
		for (String record : query_list) {
			//prints the list
			System.out.println(record);
		}
	}
	
	
	public void print_count(Collection<String> swipeData) {
		int answer = 0;
		//loops over the data
		for (String record : swipeData) {
			//increments the count
			answer++;
		}
		//prints the count
		System.out.println("There were "+ answer + " records for this query");
	}
	
	
}
