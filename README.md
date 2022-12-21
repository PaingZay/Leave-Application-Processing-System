# SPRINGCA:Leave Application Processing System (LAPS)

//Forked from Cyrus's Branch
221221 1934 UPDATE
*Added new field to LeaveApplication (leaveDuration)
*Necessary for deriving leaveBalance
 
## Fully Functional:
	1. Create (annual, medical, compensation)
		Fully refactored to service.
		
	2. Cannot apply for leave < half a day in duration

## Working on:

	1. Pump userId into the form
		//for combining with Paing
	2. Entity names
		change roll to empRole (mySQL reserved keyword)
		rename employee to user class(?)
  
 ## TODO:
 
  1. logical delete cascade?
  2. implementing the root hierarchy?
  3. hook up to userSession
  4. make fields mandatory
  5. in-model validation (hours) for formfield

## Fixed:
  Form bugs
  Annual + Medical Controller bugs
  
## OLD
		
  1. logic
	Fixing validators to use leaveBalance field 
  2. MVC
  3. in-model validation
