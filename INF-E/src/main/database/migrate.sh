java -classpath "dbmigrator.jar:postgresql-9.1-901-1.jdbc4.jar" org.jarbframework.migrations.liquibase.LiquibaseMigratorMain -changeLogBaseDir "." -changeLogPath "changelog.groovy" -dbUrl "jdbc:postgresql://<<host>>:<<port>>/<<name>>" -dbUser "<<user>>" -dbPassword "<<password>>" -driverClass "org.postgresql.Driver" -sqlOutputPath "./generated.sql"