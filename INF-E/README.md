De in-memory-db versies moet je gelijk kunnen runnen. Het nadeel hiervan is dat bij elke
run je database leeg is. Mocht je postgres willen gebruiken, volg dan de stappen onder 
het kopje postgres.

Opstart commands:
    - In memory db: mvn clean test-compile tomcat7:run
    - Postgres: mvn clean test-compile tomcat7:run -Dspring.profiles=test,postgres-db

Postgres:
- Download postgres: http://www.postgresql.org/download/
- Zorg dat je password 'root' invult voor de superuser
- Stel de poort 5432 in (Default)
- Selecteer locale 'dutch'
- Start PgAdmin op en connect naar PostgreSQL
- Maak een database 'koekoek'aan.
- Als je nu koekoek rund met het postgres command maak je gebruik van postgres sql.

URL:
http://localhost:8080/koekoek

Inloggen:
Standaard ben je ingelogd met de user 'admin' met wachtwoord 'admin'. Mocht je opnieuw moeten inloggen kan dat met deze credentials.

