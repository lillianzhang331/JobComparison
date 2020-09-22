# Design Description for JobCompare App
## Requirements

1. When the app is started, the user is presented with the main menu, which     allows the user to (1) *enter current job details*, (2) *enter job offers*, (3) *adjust the comparison settings*, or (4) *compare job offers* (disabled if no job offers were entered yet).

This is not represented in my design, as it will be handled entirely within the GUI implementation.

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

This requirement is realized by having a **User (Main Menu)** class and **Current Job** class. Both these classes have an association relationship called the *enterCurrentJob* which calls to create or modify the current job details. The **Current Job** class 'is a' kind of a higher level **Job Info** class.

3. When choosing to enter job offers, a user will:
  - Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
  - Be able to either save the job offer details or cancel.
  - Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).


This requirement is realized by having a **Job Offer** class that interacts with the **User (Main Menu)** class by association relationship. The **Job Offer** class 'is a' subclass and its superclass is **Job Info** class, similar to **Current Job** class.

4. When adjusting the comparison settings, the user can assign integer weights to:
  - Commute time
  - Yearly salary
  - Yearly bonus
  - Retirement benefits
  - Leave time

  If no weights are assigned, all factors are considered equal.

This requirement is realized by having a **Comparison Settings** class which also uses association relationship to interact with the **User (Main Menu)** class.

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

For this requirement, **Job Info** class is aggregated to form a **Job List** class. The **Job List** class is associated to the **User (Main Menu)** class with the label 'compareJobOffers'. At this time, we also make the **Comparison Settings** class as an 'association class' that is associated to a specific association, which is 'compareJobOffers'. That is how the weighted values are retrieved / gathered, before making a call to ranking() routine and list the jobs from best to worst. Further, an internal compareJob() routine is executed and the resultant data are stored in the **Comparison Table** class.

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

This part of the requirement is represented as the ranking() routine but the detailed working of this is not provided in this design.

7. The user interface must be intuitive and responsive.

This is not represented in my design, as it will be handled entirely within the GUI implementation.

8. The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app.

This is not represented in my design, as it will be handled entirely within the GUI implementation.

9. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

This requirement is considered in this design and the 'User' details are not part of the scope of this design.
