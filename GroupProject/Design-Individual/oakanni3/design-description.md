## Assignment 5: Design Description
This document describes how the software requirements are realized in the accompanying design. The requirements not represented in the design are explained here.

_**1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).**_

To realize this requirement, I created a Main class which has the operations that allows the users to carry out the above functions: *addCurrentJobDetails, addJobOffers, adjustCOmparisonSettings,* and *compareJobOffers*. The main class also serves as the entry point of interface with the GUI.

**_2. When choosing to enter current job details, a user will:_**
* **_a. Be shown a user interface to enter (if it is the first time) or edit all of the details of
their current job, which consist of:_**
  * _**i Title**_
  * _**ii. Company**_
  * _**iii. Location (entered as city and state)**_
  * _**iv. Overall cost of living in the location (expressed as an index )**_
  * _**v. Commute time (round-trip and measured in hours or fraction thereof)**_
  * _**vi. Yearly salary**_
  * _**vii. Yearly bonus**_
  * _**viii. Retirement benefits (as percentage matched)**_
  * _**ix. Leave time (vacation days and holiday and/or sick leave, as a single
overall number of days)**_
* _**b. Be able to either save the job details or cancel and exit without saving, returning
in both cases to the main menu.**_

To realize the above requirement, I created a JobDetails class and a CurrentJob class. The JobDetails class has the following attributes representing the job details listed above and an additional derived attribute for ranking jobs:
 * title: String
 * company: String
 * location: String
 * costOfLiving: Integer
 * commuteTime: Float
 * yearlySalary: Float
 * yearlyBonus: Float
 * retirementBenefits: Float
 * leaveTime: Integer
 * jobScore (derived): Float

The JobDetails class has the operations saveDetails, editDetails, and cancelAndExit to fulfill the action requirements. The CurrentJob class is a generalization of the JobDetails class, they has the same attributes and methods.

_**3. When choosing to enter job offers, a user will:**_
* **_a. Be shown a user interface to enter all of the details of the offer, which are the
same ones listed above for the current job._**
* **_b. Be able to either save the job offer details or cancel._**
* **_c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the
offer with the current job details (if present)._**

The above requirement is realized by the JobDetails class (above) and the JobOffers class. JobOffers class is a generalization of the JobDetails class so they has the same attributes and methods, and additional operations enterAnotherOffer, compareOfferWithCurrentJob and returnToMain.


**_4. When adjusting the comparison settings, the user can assign integer weights to:_**

* **_a. Commute time_**
* **_b. Yearly salary_**
* **_c. Yearly bonus_**
* **_d. Retirement benefits_**
* **_e. Leave time_**

_**If no weights are assigned, all factors are considered equal.**_

This requirement is handled by the ComparisonSettings class. This class has the attributes commuteTimeWeight, yearlySalaryWeight, yearlyBonusWeight, retirementBenefitsWeight, and leaveTimeWeight all with integer datatypes. The weights are considered as percentages in the design, so these factors are all initialized at the same value of 20. This class has the adjustComparisonSettings to satisfy the action requirement.


**_5. When choosing to compare job offers, a user will:_**

a. Be shown a list of job offers, displayed as Title and Company, ranked from best
to worst (see below for details), and including the current job (if present), clearly
indicated.
b. Select two jobs to compare and trigger the comparison.
c. Be shown a table comparing the two jobs, displaying, for each job:
i. Title
ii. Company
iii. Location
iv. Commute time
v. Yearly salary adjusted for cost of living
vi. Yearly bonus adjusted for cost of living
vii. Retirement benefits (as percentage matched)
viii. Leave time
d. Be offered to perform another comparison or go back to the main menu. 

This requirement is realized by the CompareJobOffers class. This class has a jobs attribute, which is a list of jobs with attributes from the JobDetails class without commuteTime (not shown in design). The showRandkedJobOffers(jobs),  selectAndCompareJobs(jobs), performAnotherComparison(), and andreturnToMain() methods satisfy the actions for this requirement.

**_6. When ranking jobs, a job’s score is computed as the weighted sum of:_**

_AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)_

**_where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
RBP = retirement benefits percentage
LT = leave time
CT = commute time
The rationale for the CT subformula is:_**

**_The rationale for the CT subformula is:_**

 * **_a. value of an employee hour_** = _(AYS / 260) / 8_
 * **_b. commute hours per year_** = _CT * 260_
 * **_c. therefore commute-time cost_** = _CT * 260 * (AYS / 260) / 8 = CT * AYS / 8_
**_For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and
1 for all other factors, the score would be computed as:_**
_2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 (CT * AYS / 8)_


This requirement is partially realized in the JobDetails and CompareJobOffers classes. The computed score is represented by jobScore (a derived attribute) in JobDetails. The CompareJobOffers attibute ranks the jobs based on this and displays. The design does not need to show the equations used for the computation of this derived attribute.

**_7.The user interface must be intuitive and responsive._**

This is not represented in my design, as it will be handled entirely within the GUI implementation.

**_8. The performance of the app should be such that users do not experience any
considerable lag between their actions and the response of the app._**

This requirement is implicit in the design provided and the class diagram relationships.

**_9.For simplicity, you may assume there is a single system running the app (no
communication or saving between devices is necessary)._**

This is not explicitly depicted in the class design diagram, but apart from the GUI, there is no other component of the software not represented by this design. A deployment diagram will show this.