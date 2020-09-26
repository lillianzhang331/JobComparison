# Design Discussion for JobCompare App
## Design 1
![Lillian's design](lilliandesign.png)
### Pros
* The design includes the main menu (User) which interfaces with the GUI and the required operations.
* Job, ComparisonSettings, CurrentJob, JobOffer classes are all in line with the requirements.
* Cardinality in the relationships between the UML classes provided.
* Job and ComparisonSettings class has all the required attributes.
* CurrentJob and JobOffer classes are types (is a kind of) of Job class as described in the requirements.
* User class contains the 4 main operations of the app.

### Cons
* No initial (default) values set for ComparisonSettings attributes.
* Some of the attributes in Job should be float/double, not integer.
* The requirement is for a single user, hence the ‘App’ class need not be there. User can be renamed to MainMenu.
* Comparison of the job, needs a class on its own, that is not part of the design.
* enterCurrentJobDetails, enter job offers, adjust the comparison settings, or compare job offers are interactions between the classes. Because these operations do not belong to only one class, instead they are a relationship between the classes. So they can be labeled on the association lines.
* Rank should not be a relationship between Job and App class.
* calculateScore() operation needs to know about the comparisonSettings values.

## Design 2
### Bharathi's Design
![Bharathi's design](bharathidesign.png)
### Pros
* I like how you separated out job list and job info.
* I like that job offer has its own class with enterAnotherOffer(), that’s a good idea to separate out the differences between job offers and current job.
* ComparisonSetting is set with relationship with User as well as a relationship association with compareJobOffers.
* Interesting use of Job List, this would be a good way to tackle efficiency so we wouldn’t have to generate a new list whenever the user wants to compare jobs.
* The design includes the main menu (User)which interfaces with the GUI.
* JobInfo, ComparisonSettings, CurrentJob, JobOffer classes are all in line with the requirements.
* The use of utility classes for Money and Date, and using Money and Date as data types.
* Cardinality in class relationships are provided.
* Descriptive text of relationships provided.
* Attributes in JobInfo and ComparisonSettings(with default values)included.
* QUESTION: What is getDate() for?[Operating System utility class]
* Save and Cancel methods are captured correctly.
* Dependencies and associations are captured correctly.

### Cons
* Is the user the same as the main menu? Is this considered the entry point to the system?
* Should commuteTime be set as a date or float?
* Why is it 1 to 0...* for joblist and job info? Does that mean there could be multiple job infos for each job? Technically if there are two jobs with the same details, they would be regarded as two separate jobs right? Would making it a 1 to 1 relationship make more sense?
* Is there supposed to be a relationship between job list and comparison table? I think the user is able to adjust the comparison table even without having entered a job so maybe the relationship should exist between comparison table and the user/main menu instead.
* There shouldn’t be any space in the Class name string, e.g: JobList (not Job List).
* Not sure of the Aggregation/Association relation between JobList and Job Info. Should it be arrowhead be on the other end?
* score in JobList is a calculated attribute with a backslash, written as: -/score.
  - Float or Integer?
* No operations in MainMenu.
* No location (state/city) attributes in JobInfo.
* Job List should not be separate class. It is list of jobs object with ranking. It should be part of ComparisonTable class.
* Not sure about usage of utility class for Money, Date and Operating System.
* Salary and Bonus attribute can be treated as a float instead of creating Money utility class.

## Design 3
### Toks' Design
![Toks' design](toksdesign.png)
### Pros
* The design includes the MainMenu which interfaces with the GUI. 
* Main Menu class contains all the 4 main operations of the app.
* Design includes all required classes: JobDetails, ComparisonSettings, CurrentJobOffers, CurrentJob, JobOffer. Does not have any extraneous classes. 
* Simple and clean design overall.
* Attributes in JobInfo and ComparisonSettings (with default values) included. 
* Descriptive text included for relationships.
* Relationships cardinality included.
* Default value for all attributes in ComparisonSettings class.
* CurrentJob and JobOffer are subclasses of JobDetails class as described in the requirements.
* CompareJobOffers class has the list of all the jobs as per the requirements.
* CompareJobOffers class correctly shows the dependency association with the JobDetails class.

### Cons
* Incorrect cardinality: CurrentJob cannot be more than 1 job and the cardinality should reflect this as [0..1].
* CompareJobOffers uses ComparisonSettings to rank jobs but this is not shown in the design.
* saveDetails() and cancelAndExit() should not be methods in the jobDetails class, they are implemented via the GUI instead since the job itself would not save/cancel.
* All three operations in JobOffer are not needed.
* Location (JobDetails attribute) not split into state and city  per requirements. 
* enterCurrentJobDetails, enter job offers, adjust the comparison settings, or compare job offers are interactions between the classes. Because these operations do not belong to only one class, instead they are a relationship between the classes. So they can be labeled on the association lines.
* performAnotherComparison(), enterAnotherOffer() operations are already defined in the design in other classes, so they need not be specified again.
* editDetails() need not be a separate operation, as the fields can be open to edit and save() would take care of storing the edited fields.
* Wrongfully depicted relationship between CurrentJob and Main as many (*) to 1. 

## Design 4
### Mitesh's Desgin
![Mitesh's design](miteshdesign.png)
### Pros
* Design includes all required classes: JobDetails, ComparisonSettings, CurrentJobOffers, User. 
* JobDetails covers all the attributes.
* Included relationships and its cardinality.
* Created generic method for the weights. One can create as many weight attributes with its weightage as required.

###Cons
* Missed relationship description between classes. 
* Unnecessary class creation: Location class.
* Better to create JobDetails as a base class.
* CurrentJob and JobOffers should be include as derived classes from JobDetails.
* There is no requirement for User class and methods hasJob() and totalJobOffer(). 
* Missing save(), cancel() and/or returnToMain() methods.
* Should have included default values for the ComparisonSettings class.
* No need of compId attribute in CompOffers class.

## Team Design

## Summary
In this assignment, as a team we are able to discuss individual take on designing an application. It reflected the views on many design methodologies used by an individual.  
We had detailed discussion on pros and cons of each team member's design. This project has provided us the opportunity to iron out the flaws in our own design and understand the requirements better. 
We were actually able to do requirement elicitation through our discussion. We were able to discuss the different aspects of UML diagrams - finalizing class diagram and their attributes, methods within a class, relationships and its cardinality. 
As a team, after finalizing the class diagram, we mapped the requirement with our diagram to identify any requirement missed  or have any extra class or attributes or operations.  

Overall this team project has provided us an opportunity to get hands on experience on requirement elicitation, application design and how to represent the application using class diagram.
