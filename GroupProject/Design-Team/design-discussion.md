# Design Discussion for JobCompare App
## Design 1
![Lillian's design](./Design-Team/lilliandesign.png)
### Pros
* The design includes the main menu (User) which interfaces with the GUI and the required operations.
* Job, ComparisonSettings, CurrentJob, JobOffer classes are all in line with the requirements
* Cardinality in the relationships between the UML classes provided
* Job and ComparisonSettings class has all the required attributes
* CurrentJob and JobOffer classes are types (is a kind of) of Job class as described in the requirements.
* User class contains the 4 main operations of the app.

### Cons
* No initial (default) values set for ComaprisonSettings attributes
* Some of the attributes in Job should be float/double, not integer
* The requirement is for a single user, hence the ‘App’ class need not be there. User can be renamed to MainMenu
* Comparison of the job, needs a class on its own, that is not part of the design.
* enterCurrentJobDetails, enter job offers, adjust the comparison settings, or compare job offers are interactions between the classes. Because these operations do not belong to only one class, instead they are a relationship between the classes. So they can be labeled on the association lines.
* Rank should not be a relationship between Job and App class
* calculateScore() operation needs to know about the comparisonSettings values

## Design 2

## Design 3

## Design 4

## Team Design
![Team Design](./Design-Team/teamdesign.png)
### General classes
The main commonalities between the team design and the individual designs are the existence of the four main classes: MainMenu, CurrentJob, JobOffer, and ComparisonSettings. These were the classes that were explicitly stated in the requirements along with a set of necessary attributes (such as company name and location). While UML designs may vary from team to team or iteration to iteration, these four classes are the key actors in the system and each class's attributes and methods should remain relatively consistent through various designs.
### Job Inheritance
We decided to have the CurrentJob and JobOffer classes inherit the general Job class. This is largely because a current job and a job offer differs only in that there can be a maximum of one current job at any time whereas a user can enter multiple job offers and the user has the option to compare any job offer with the current job (if present) upon creating a new job offer. Other than these, the attributes of both classes are the same and thus, should not be implemented more than once to avoid redundancy. The Job inheritance is common among all but one of the individual designs.
###

## Summary
