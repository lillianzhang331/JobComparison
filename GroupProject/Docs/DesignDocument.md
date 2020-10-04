# Design Document

**Author**: 6300Fall20Team030

## 1 Design Considerations

### 1.1 Assumptions
This application is designed with the following assumptions in mind:
* User is operating on an Android device.
* Utility classes will be used to represent money.
* The application will be in English.
* The device will not maintain cross session state.
* User will not have to sign in or create an account to use the application.

### 1.2 Constraints
This application is designed with the following constraints in mind:
* The application will not keep track of information from another session so if the user exits out of application, they will have to enter information again.
* There is no way to edit or delete job offers.
* The user can edit the current job multiple times, but cannot delete it.
* When adding the current job or a job offer, all fields are mandatory for the job details to be saved.
* User must have an Android phone and have knowledge about how to use it.

### 1.3 System Environment
* Operating system: Android
* Environment: Smartphone application
* Platform: Android SDK
* Hardware: Android phone
