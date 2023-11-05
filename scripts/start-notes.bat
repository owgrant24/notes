chcp 1251
java -version
java -jar -Dspring.datasource.url="jdbc:postgresql://localhost:5432/notes-db" -Dspring.datasource.username="postgres" -Dspring.datasource.password="postgres" ./notes.jar
