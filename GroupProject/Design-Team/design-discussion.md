# Design Discussion for JobCompare App
## Design 1
![Lillian's design](https://github.gatech.edu/gt-omscs-se-2020fall/6300Fall20Team030/tree/master/GroupProject/Design-Team/lilliandesign.png)
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
* Rank should not be a realtionship between Job and App class
* calculateScore() operation needs to know about the comparisonSettings values

## Design 2

## Design 3

## Design 4

## Team Design

## Summary
