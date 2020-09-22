# Design Description

> When the app is started, the user is presented with the main menu, which allows the user to (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).

I created a user class that includes 4 methods that reflect the actions that a user can take: editCurrentJob(), addJobOffer(), editJobOffer(jobID), editComparisonSettings(), and compareJobs(). The app can be used by any user without an account so the user class does not have any attributes associated with them. There is essentially a single user for the owner of the app that is created by the app itself.

> When choosing to enter current job details, a user will:
> Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
> * Title
> * Company
> * Location (entered as city and state)
> * Overall cost of living in the location (expressed as an index)
> * Commute time (round-trip and measured in hours or fraction thereof)
> * Yearly salary
> * Yearly bonus
> * Retirement benefits (as percentage matched)
> Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)

The user class has a method called editCurrentJob() that will allow the user to fill in the details of their current job. Prior to the user filling out the details, a currentJob class has unassigned attributes to indicate that there is no "current job" to be compared against (in the case that the user is unemployed). The CurrentJob class inherits from the Job class which has attributes: jobID, title, company, city, state, costOfLiving, yearlySalary, yearlyBonus, retirementMatching, and leaveTime.

> Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

This is not represented in my design as it will be handled within the GUI flow implementation with a "save" button and a "cancel" button.

> When choosing to enter job offers, a user will:
> Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.

This requirement is represented by the addJobOffer() and editJobOffer(jobID) methods in the user class. I separated the two methods to allow users to add a completely new offer or edit an existing one since there can be multiple offers. This is in contrast to the current job, which I have limited to one current job. The jobOffers class allows multiple job offers per each user and is inherited from the Job class. A jobID will used to differentiate between job offers when selecting which offer to edit.

> Be able to either save the job offer details or cancel.

This is not represented in my design as it will be handled within the GUI flow implementation with a "save" button and a "cancel" button.

> Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).

This is represented by the editJobOffer(jobID) and compareJobs() method in the user class.

> When adjusting the comparison settings, the user can assign integer weights to:
> * Commute time
> * Yearly salary
> * Yearly bonus
> * Retirement benefits
> * Leave time
> If no weights are assigned, all factors are considered equal.

To realize this requirement, I added a comparisonSettings class to represent the weight settings that the user can edit. The user class also has an editComparisonSettings() method which allows the user to adjust the weights for comparison. The comparisonSettings class has integer attributes: CTWeight, YSWeight, YBWeight, RBWeight, and LTWeight. This class is automatically created with equal weights in case the user does not want to edit the weights.

> When choosing to compare job offers, a user will:
> Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.

This requirement is handled by the App class (entry point into the system) that interacts with the Job class by ranking each job based on the calculated score.

> Select two jobs to compare and trigger the comparison.

This is represented by the compareJobs() method in the user class. When that method is called, the App will prompt the user to select two jobs for comparison (this is not represented in my design as it will be handled within the GUI implementation).

> Be shown a table comparing the two jobs, displaying, for each job:
> * Title
> * Company
> * Location
> * Commute time
> * Yearly salary adjusted for cost of living
> * Yearly bonus adjusted for cost of living
> * Retirement benefits (as percentage matched)
> * Leave time

This will be represented within the compareJobs method and the details from each job offer will be displayed once 2 jobs are selected for comparison. The displaying of each job will be handled in the GUI implementation.

> Be offered to perform another comparison or go back to the main menu.

A "make another comparison" button will trigger the user's compareJobs() method. Going back to the main menu is not represented in my design as it will be handled within the GUI flow implementation by a "go back" button.

> When ranking jobs, a job’s score is computed as the weighted sum of:
> AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)
> where:
> * AYS = yearly salary adjusted for cost of living
> * AYB = yearly bonus adjusted for cost of living
> * RBP = retirement benefits percentage
> * LT = leave time
> * CT = commute time
> * The rationale for the CT subformula is:
> * value of an employee hour = (AYS / 260) / 8
> * commute hours per year = CT * 260
> * therefore commute-time cost = CT * 260 * (AYS / 260) / 8 = CT * AYS / 8
> * For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:
> * 2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 (CT * AYS / 8)

To realize this requirement, I added a calculateScore() method to the Job class that returns an integer score when called. This method calculates the score of each job (current and offers) based on the formula given in the requirements.

> The user interface must be intuitive and responsive.

This is not represented in my design as it will be handled within the GUI implementation.

> The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app.

This is not represented in my design as it is dependent on the efficiency of the app and system.

> For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

This is represented in my design by the single user class instantiated by the app.
