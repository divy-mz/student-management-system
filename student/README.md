# Student Profile and Skill Development Management System
1. Extract ZIP and open the student folder (pom.xml) in IntelliJ IDEA as a Maven project.
2. Select JDK 25 or later and let Maven download dependencies.
3. Start MySQL and execute database.sql in MySQL Workbench.
4. Set your MySQL username/password in src/main/resources/application.properties.
5. Run StudentApplication.java. Test with requests.http (or Postman).

Assumptions: programming improvement means score below 50; common skills means at least one exact shared skill. PUT replaces both scores and the skill list; supply all three fields. No Lombok required. Project has not been compiled or tested against MySQL in this environment.
