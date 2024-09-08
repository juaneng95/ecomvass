## Summary
GitHub: https://github.com/juaneng95/ecomvass

## Technology

- Spring boot framework 3.2.4.
- Java 21.0.2.
- OpenApi 3.
- Maven 3.9.6.
- Jacoco 0.8.11.
- Mockito 5.11.0.
- Flyway Core 10.10.0.
- H2 Database 2.2.224.

## Execution
### Clean Install

Configure the project in `File | Project Structure | Project Settings | Project:`
- SDK: 21.
- Language Level: LTS 21

Configure your maven `Maven` (I have a custom maven path) in: `File | Settings | Build, Execution, Deployment | Build Tools | Maven:`
- Maven Home Path: Use your Maven path (C:/apache-maven-3.9.6 or Bundle)
- User Setting File: Use your Maven settings.xml path (C:\apache-maven-3.9.6\conf\settings.xml or .m2, etc...)

Execute clean install in configuration dialog.

### Ecommerce Run
 - Java version: `21`
 - Cp: `boot`
 - MainClass: `com.vass.inditex.boot.BootApplication`
 - Environment Variables: `BBDD_USER=admin;BBDD_PWD=admin`

### Test
To see all coverage:
 - Jacoco report.
 - Unit Testing Run (`Pattern:` ^(?!.*com\.vass\.inditex\.boot\.*).*$)

## Usage
- H2 Console (admin/admin): **http://localhost:8080/h2-console/**
- Swagger: **http://localhost:8080/swagger-ui/index.html**
- Jacoco Coverage result: **http://localhost:63342/ecomvass/boot/target/site/jacoco-aggregate/index.html**
- There is a POSTMAN collection can be imported: `VASS.postman_collection.json`.
