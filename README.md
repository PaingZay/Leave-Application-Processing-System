# SPRINGCA:Leave Application Processing System (LAPS)

//Forked from Cyrus's Branch
221221 0017 UPDATE
*Added new field to LeaveApplication (leaveDuration)
*Necessary for deriving leaveBalance
 
## Fully Functional:
 1. Create for Annual and Medical are functional
 2. 221220 fixed bug relating to retrieving userId for leaveAppFormValidator
 3. OvertimeChit, OTC services,repo
 4. fixed bugs for create (annual, medical, compensation)

## Working on:

  1. logic
	Fixing validators to use leaveBalance field 
  2. MVC
  3. in-model validation
  
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