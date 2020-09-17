# Android_COVID_Monitor
Mini Project for Senior Design EC463. By Jonathan Hall and Sara Hamdy.

The application, built using Java in android studio, features both SSO(signal sign on) through Google and email login through account creation. Both authentication processes are enabled and stored through Google firebase.

Once logged in, the use is able to take self administered health surveys and check live local covid statistics through the helpful API by https://corona.lmao.ninja/ 

The surveys are taken daily, and if the user misses theirs, a notification will be send via banner to remind the user. There is also a helpful display in the app that outputs the last time a survey was taken. The data is stored via Firebase's Cloud Firestore. 

To view the data, you have to be an administrator. The administrator logs in via the same log in screen, but they are taken to the admin panel. It's important to note that you can't create an admin account through registration (important for data safety). Here they can view Covid Survey statisticse, such as the number of surveys taken in the past day and recent symptom positive surveys.  For the safety of the user however, names are never displayed.
