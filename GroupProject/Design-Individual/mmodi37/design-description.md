<h1>Design Description</h1>

**This document provides design related information about Assignment 5**

Assignment 5 includes creation of UML class diagram  and related documentation for job management system as a part of group project.

<h1>Design Consideration:</h1>

    * Created 6 different classes:
        * User 
        * JobDetails
        * Location
        * CompSetting
        * CompSettingColumns
        * CompOffers
    
    * User:
      This class will contain user related information who is going to use the system.
      One can create and retrieve user using this class.
      It provides information if user has any active job or how many total job offers.
    
    * JobDetail:
        This class will hold all job related inforamation.
        It constinas current job and job offers information for each user.
        So many to many relationship between user and job. 
        It has dependency on Location class as each job need inforamtion about location.
       
    * CompSetting:
        Comparation setting class includes information about columns used for comparision.
        Each comparision column has its own weights.
        If there is no weights set then we will divide 1 into equal weitght for each column.
    
    * CompSettingColumns:
        One can remove and add columns to be part of comparision
        
    * CompOffers:
        Comparision Offers class will helpful to create comparision between different job offers.
        It will mainly require for comparision report creation.

<h1>Requirement matrix with design</h1>

    * Requirement 1:
        When the app is started, the user is presented with the main menu, which allows the user to 
        (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or 
        (4) compare job offers (disabled if no job offers were entered yet).
      
      Explaination:
        This requirement mainly handled through UI. 
        But the required details will be taken care by JobDetails, Users, CompSetting and CompOffers classes
     
    * Requirement 2:
        When choosing to enter current job details, a user will:
        a. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job.
        b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
      
      Explaination:
        This requirement will be handled through SaveJobDetails method of JobDetail class.
        An user has to select the location based on Location class. 
        It is reason JobDetails is fully dependent on Location class. 
    
    * Requirement 3:        
        When choosing to enter job offers, a user will:
        a. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
        b. Be able to either save the job offer details or cancel.
        c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).
        
      Explaination:
        This requirement will be handled through SaveJobDetails method of JobDetail class.
        For this entry, IsCurrentJob flag will be false as they are job offeres.
        For Comparision, createComparision function will be called with required job ids. 

    * Requirement 4: 
        When adjusting the comparison settings, the user can assign integer weights to:
        If no weights are assigned, all factors are considered equal.

      Explaination:
        CompSetting and CompSettingColumns classes will take care of this requirement.
        One should need to add all comparision settings columns through addColumnInCompSettings.
        These columns and their weights are mapped thorugh CompSetting class.
        One can change the weights using adjustCompSetting method.
         
    * Requirement 5:
        When choosing to compare job offers, a user will:
        a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
        b. Select two jobs to compare and trigger the comparison.
        c. Be shown a table comparing the two jobs, displaying, for each job:
        d. Be offered to perform another comparison or go back to the main menu.

    Explaination:
        One can rertrieve job details using selected jobid from JobDetail class.
        CompOffers class will create the required comparision with selected job ids for the comparision based on CompSettings. 

    * Requirement 6:
        When ranking jobs, a job’s score is computed as the weighted sum of:
            AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)

       Explaination:
        JobDetail class has calculateJobRanking method which will calculate the reqiured ranking based on above formula. 

    * Requirement 7:
        The user interface must be intuitive and responsive.
      
      Explaination:
        This is not represented in my design, as it will be handled entirely within the GUI implementation.

    * Requirement 8:
        The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app.

      Explaination:
        This is not represented in my design, as it will be handled entirely within the implementation.

    * Requirement 9:
        For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

      Explaination:
        This is not represented in my design, as it is an assumption for usage of the app.
        
    


