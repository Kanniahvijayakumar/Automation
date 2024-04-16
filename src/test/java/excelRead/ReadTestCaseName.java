package excelRead;

import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ReadTestCaseName {
	
	public ArrayList<String> getTestCaseDetails(String testCaseName) throws IOException {
	
	ReadExcel file = new ReadExcel();
	
	Sheet guru99Sheet = file.readExcel("C:\\Users\\kanni\\OneDrive\\Desktop\\Interview\\FrameWork\\src\\main\\resources\\ExcelData"+"\\","TestCase.xlsx" , "KeywordFramework");
	
	ArrayList<String> cars = new ArrayList<String>();
	
	cars.clear();
	
	//Find number of rows in excel file
    int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();
    
    boolean flag = false;
    
    
    
    String testcasename ="";
    
    String actualTestcasename ="";
    
	  //Create a loop over all the rows of excel file to read it
  for (int i = 1; i < rowCount+1; i++) {
      //Loop over all the rows
      Row row = guru99Sheet.getRow(i);
      //Check if the first cell contain a value, if yes, That means it is the new testcase name
      
   
      
      if(row.getCell(0).toString().length()!=0){
      	
      	System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
      	
      	testcasename = row.getCell(0).toString(); 
      	
      	System.out.print(testcasename);
          	

       
      	
      }

      else{
          //Print the new testcase name when it started
      	if(testcasename.equals(testCaseName)){
      		
      		
      		
      		System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
      		
      				if(actualTestcasename.isEmpty()) {
      					
      					actualTestcasename = testcasename;
      					
      					System.out.print("DDDDDDDDDDDDDDDDDDDDDDDD");
      					
      					cars.add(testcasename);
      					
      				}
              	
	    	        if((row.getCell(0).toString().length()==0) && (testcasename.equalsIgnoreCase(testCaseName))){
	    		        //Print testcase detail on console
	    		            System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+
	    		            row.getCell(3).toString()+"----"+ row.getCell(4).toString());
	    		            
	    		            System.out.print("Hi");
	    		            
	    		            
	    		            cars.add(row.getCell(1).toString());
	    		            cars.add(row.getCell(2).toString());
	    		            cars.add(row.getCell(3).toString());
	    		            cars.add(row.getCell(4).toString());
	    		            
	    		      
	    		            
	    		        }
              	
              	

              

  }
  }
    
    
    
    
    


}
return cars;
}
	
}
