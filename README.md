# Warehouse-Management
Supply Chain Management project for inbound and outbound orders in warehouses


** Setup **
1. Install java 8.
2. Install Maven
3. Download lombok jar(in case of eclipse) , install lombok plugin(in case of Intellij.
4. Install and setup  MariaDB.
https://mariadb.com/kb/en/library/installing-mariadb-on-macos-using-homebrew/

5. clone the project in your local machine.
6. Install DBeaver and make a new connection with MariaDB.
7. Create database warehouse.
8. Go to ./src/main/resources/ and run the createTable.sql script in DBeaver.
9. Go to ./src/main/resources/ and set the password of root in application.properties
10. Build and Run the application.
11. Go to browser and open localhost:8080/swagger-ui.html
