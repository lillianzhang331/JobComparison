# Design Description for JobCompare App
## Requirements

1. When the app is started, the user is presented with the main menu, which     allows the user to (1) *enter current job details*, (2) *enter job offers*, (3) *adjust the comparison settings*, or (4) *compare job offers* (disabled if no job offers were entered yet).

**To realize this requirement, we created a _MainMenu_ class which has the operations that allows the users to carry out the above functions: _enterCurrentJob_, _enterJobOffer_, _adjustComparisonSettings_, and _compareJobOffers_. The _MainMenu_ class also serves as the entry point of interface with the GUI.**

2. When choosing to enter current job details, a user will:
  - Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
    - Title
    - Company
    - Location (entered as city and state)
    - Overall cost of living in the location (expressed as an index)
    - Commute time (round-trip and measured in hours or fraction thereof)
    - Yearly salary
    - Yearly bonus
    - Retirement benefits (as percentage matched)
    - Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
  - Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

**This requirement is realized by the _MainMenu_ and the _Job_ classes. Both classes have an association relationship which is called by _enterCurrentJob_ operation in _MainMenu_. The _Job_ class has the attributes representing the job details listed above with an additional derived attribute - _jobScore_ - for ranking jobs. The attributes _salary_ and _bonus_ use the datatype Money from the _Money_ utility class.**


3. When choosing to enter job offers, a user will:
  - Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
  - Be able to either save the job offer details or cancel.
  - Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).


**The above requirement is realized by having a _JobOffer_ class that interacts with the _MainMenu_ class by association relationship. The _JobOffer_ class is a subclass and its superclass is the _Job_ class, similar to the _CurrentJob_ class. The _JobOffer_ class has a  _compareWithCurrentJob_ operation according to the requirement.**

4. When adjusting the comparison settings, the user can assign integer weights to:
  - Commute time
  - Yearly salary
  - Yearly bonus
  - Retirement benefits
  - Leave time

  If no weights are assigned, all factors are considered equal.

**This requirement is realized by having a _ComparisonSettings_ class which also uses association relationship to interact with the _MainMenu_ class. The attributes are assigned the same default initial values to satisfy the requirements.**

5. When choosing to compare job offers, a user will:
  - Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
  - Select two jobs to compare and trigger the comparison.
  - Be shown a table comparing the two jobs, displaying, for each job:
    - Title
    - Company
    - Location
    - Commute time
    - Yearly salary adjusted for cost of living
    - Yearly bonus adjusted for cost of living
    - Retirement benefits (as percentage matched)
    - Leave time
  - Be offered to perform another comparison or go back to the main menu.

**This requirement is realized by the _CompareJobs_ class. This class has a _jobs_ attribute, which is a list of jobs with attributes from the _Jobs_ class without _commuteTime_ (not shown in design). The _CompareJobs_ class is associated with the _MainMenu_ and is called by _compareJobOffers_ operation from _MainMenu_ while using the _ComparisonSettings_ class and job attributes from the _Job_ class. The _ranking_, _compareJobs_, and _cancel_ methods satisfy the action requirements for this class.**



6. When ranking jobs, a job’s score is computed as the weighted sum of:

  AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)

  where:

  AYS = yearly salary adjusted for cost of living
  AYB = yearly bonus adjusted for cost of living
  RBP = retirement benefits percentage
  LT = leave time
  CT = commute time
  The rationale for the CT subformula is:
  value of an employee hour = (AYS / 260) / 8
  commute hours per year = CT * 260
  therefore commute-time cost = CT * 260 * (AYS / 260) / 8 = CT * AYS / 8

  For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as:
  2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 (CT * AYS / 8)


**This requirement is partially represented in the _Jobs_ and _CompareJobs_ classes. The computed score is represented by _jobScore_ (a derived attribute) in _Jobs_. The _CompareJobs_ class ranks the jobs based on this score as _jobs_ list attribute. The design does not need to show the equations used for the computation of this derived attribute.**


7. The user interface must be intuitive and responsive.

**This is not represented in my design, as it will be handled entirely within the GUI implementation.**

8. The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app.

**This requirement is implicit in the design provided and the class diagram relationships.
It will be handled within the GUI implementation.**

9. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

**This is not explicitly depicted in the class design diagram, but apart from the GUI, there is no other component of the software not represented by this design. A deployment diagram will show this.**