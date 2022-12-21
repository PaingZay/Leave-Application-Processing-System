# SPRINGCA:Leave Application Processing System (LAPS)

//Forked from Cyrus's Branch
221221 1934 UPDATE
*Added new field to LeaveApplication (leaveDuration)
*Necessary for deriving leaveBalance
 
## Fully Functional:
 1. Create (annual, medical, compensation)

## Working on:

	1. Refactor to service
		employee model repo (DONE),
		public holiday,
		leaveAppForm

	2. minGranularity flat half day for everyone
	3. check apply for current year


	4. pump userId into the form
		//for combining with Paing
  
 ## TODO:
 
  1. logical delete cascade?
  2. implementing the root hierarchy?
  3. hook up to userSession
  4. make fields mandatory
  5. in-model validation (hours) for formfield

## Fixed:
  Form bugs
  Annual + Medical Controller bugs
  

## Upload

	//BACKUP BEFORE REFACTORING METHODS IN LEAVEAPPLICATION
	
## OLD
		
  1. logic
	Fixing validators to use leaveBalance field 
  2. MVC
  3. in-model validation