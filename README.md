# Android COVID Monitor
## **Mini Project for Senior Design EC463. By Jonathan Hall and Sara Hamdy.**

This project was designed and created over 14 days, as an intro to senior design. 
The application, built using Java in Android Studio, features both SSO(signal sign on) through Google and email login through account creation. Both authentication processes are enabled and stored through Google firebase.

<img src="https://github.com/Jon-S-Hall/Android_COVID_Monitor/blob/master/images/covidapp_login.PNG" width="275" />

Once logged in, the user is able to take self administered health surveys, present their health badge to university administrators, and check live local covid statistics through the helpful API provided by https://corona.lmao.ninja/.

### Home Screen
<img src="https://github.com/Jon-S-Hall/Android_COVID_Monitor/blob/master/images/covidapp_homescreen.PNG" width="275" />

The health survey is a series of 8 questions that ask the user if they're experiencing various COVID symptoms. The questions are presented in a neat manner, with one question at a time to avoid scrolling. This allows users who feel well to breeze through their questionnaire and be incentivized to complete it regularly. The surveys are taken daily, and if the user misses theirs, a notification will be sent via banner to remind the user. There is also a helpful display in the app that outputs the last time a survey was taken. The data is stored via Firebase's Cloud Firestore.

### Survey Screen
<img src="https://github.com/Jon-S-Hall/Android_COVID_Monitor/blob/master/images/covidapp_survey.PNG" width="275" /> <img src="https://github.com/Jon-S-Hall/Android_COVID_Monitor/blob/master/images/covidapp_alert.PNG" width="275" /> 

If the user answers "yes" to any survey questions, they will be alerted that they have reported a possible COVID symptom, and their banner will turn orange to signify to university administrators that the student should be home. If the student answers "no" to all survey questions, the banner will display green signifying **all good to go!**

### Badge Screen
<img src="https://github.com/Jon-S-Hall/Android_COVID_Monitor/blob/master/images/covidapp_cleared.PNG" width="275" /> <img src="https://github.com/Jon-S-Hall/Android_COVID_Monitor/blob/master/images/covidapp_quarantine.PNG" width="275" />

Additionally, the user is able to quickly check the real time COVID reports in Massachusetts.

### API Screen
<img src="https://github.com/Jon-S-Hall/Android_COVID_Monitor/blob/master/images/covidapp_API.PNG" width="275" />


To view the data, you have to be an administrator. The administrator logs in via the same log in screen, but they are taken to the admin panel. It's important to note that you can't create an admin account through registration (important for data safety). **Note, our default admin was (User: admin@gmail.com, Password: admin1) but it will vary by implementation**. Using the Admin panel, you can view Covid Survey statistics, such as the number of surveys taken in the past day and recent positive symptom surveys.  For the safety of the user however, names are never displayed.

### Admin Panel
<img src="https://github.com/Jon-S-Hall/Android_COVID_Monitor/blob/master/images/covidapp_admin.PNG" width="275" />


## Dependencies 
We used Google Firebase for all our storage, including User (Authentication) and survey data. Here's a snapshot of our databases.

### Firebase Authentication and Firebase Cloud Firestore

<img src="https://github.com/Jon-S-Hall/Android_COVID_Monitor/blob/master/images/firebase_authentication.PNG" width="550" /><img src="https://github.com/Jon-S-Hall/Android_COVID_Monitor/blob/master/images/firebase_surveys.PNG" width="400" />

We also used https://corona.lmao.ninja/ for our COVID API, and https://unsplash.com/ for our free login screen background.

