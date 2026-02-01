# Spring PetClinic Sample Application [![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml)[![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/gradle-build.yml)

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/spring-projects/spring-petclinic) [![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://github.com/codespaces/new?hide_repo_select=true&ref=main&repo=7517918)

## Understanding the Spring Petclinic application with a few diagrams

See the presentation here:  
[Spring Petclinic Sample Application (legacy slides)](https://speakerdeck.com/michaelisvy/spring-petclinic-sample-application?slide=20)

> **Note:** These slides refer to a legacy, pre–Spring Boot version of Petclinic and may not reflect the current Spring Boot–based implementation.  
> For up-to-date information, please refer to this repository and its documentation.

## Run Petclinic locally

Spring Petclinic is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/) or [Gradle](https://spring.io/guides/gs/gradle/).
Java 17 or later is required for the build, and the application can run with Java 17 or newer.

You first need to clone the project locally:

```bash
git clone https://github.com/spring-projects/spring-petclinic.git
cd spring-petclinic
```

If you are using Maven, you can start the application on the command-line as follows:

```bash
./mvnw spring-boot:run
```

With Gradle, the command is as follows:

```bash
./gradlew bootRun
```

You can then access the Petclinic at <http://localhost:8080/>.

<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/838318/19727082/2aee6d6c-9b8e-11e6-81fe-e889a5ddfded.png">

You can, of course, run Petclinic in your favorite IDE.
See below for more details.

## Building a Container

There is no `Dockerfile` in this project. You can build a container image (if you have a docker daemon) using the Spring Boot build plugin:

```bash
./mvnw spring-boot:build-image
```

## In case you find a bug/suggested improvement for Spring Petclinic

Our issue tracker is available [here](https://github.com/spring-projects/spring-petclinic/issues).

## Database configuration

In its default configuration, Petclinic uses an in-memory database (H2) which
gets populated at startup with data. The h2 console is exposed at `http://localhost:8080/h2-console`,
and it is possible to inspect the content of the database using the `jdbc:h2:mem:<uuid>` URL. The UUID is printed at startup to the console.

A similar setup is provided for MySQL and PostgreSQL if a persistent database configuration is needed. Note that whenever the database type changes, the app needs to run with a different profile: `spring.profiles.active=mysql` for MySQL or `spring.profiles.active=postgres` for PostgreSQL. See the [Spring Boot documentation](https://docs.spring.io/spring-boot/how-to/properties-and-configuration.html#howto.properties-and-configuration.set-active-spring-profiles) for more detail on how to set the active profile.

You can start MySQL or PostgreSQL locally with whatever installer works for your OS or use docker:

```bash
docker run -e MYSQL_USER=petclinic -e MYSQL_PASSWORD=petclinic -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=petclinic -p 3306:3306 mysql:9.5
```

or

```bash
docker run -e POSTGRES_USER=petclinic -e POSTGRES_PASSWORD=petclinic -e POSTGRES_DB=petclinic -p 5432:5432 postgres:18.1
```

Further documentation is provided for [MySQL](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/resources/db/mysql/petclinic_db_setup_mysql.txt)
and [PostgreSQL](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/resources/db/postgres/petclinic_db_setup_postgres.txt).

Instead of vanilla `docker` you can also use the provided `docker-compose.yml` file to start the database containers. Each one has a service named after the Spring profile:

```bash
docker compose up mysql
```

or

```bash
docker compose up postgres
```

## Test Applications

At development time we recommend you use the test applications set up as `main()` methods in `PetClinicIntegrationTests` (using the default H2 database and also adding Spring Boot Devtools), `MySqlTestApplication` and `PostgresIntegrationTests`. These are set up so that you can run the apps in your IDE to get fast feedback and also run the same classes as integration tests against the respective database. The MySql integration tests use Testcontainers to start the database in a Docker container, and the Postgres tests use Docker Compose to do the same thing.

## Compiling the CSS

There is a `petclinic.css` in `src/main/resources/static/resources/css`. It was generated from the `petclinic.scss` source, combined with the [Bootstrap](https://getbootstrap.com/) library. If you make changes to the `scss`, or upgrade Bootstrap, you will need to re-compile the CSS resources using the Maven profile "css", i.e. `./mvnw package -P css`. There is no build profile for Gradle to compile the CSS.

## Working with Petclinic in your IDE

### Prerequisites

The following items should be installed in your system:

- Java 17 or newer (full JDK, not a JRE)
- [Git command line tool](https://help.github.com/articles/set-up-git)
- Your preferred IDE
  - Eclipse with the m2e plugin. Note: when m2e is available, there is a m2 icon in `Help -> About` dialog. If m2e is
    not there, follow the installation process [here](https://www.eclipse.org/m2e/)
  - [Spring Tools Suite](https://spring.io/tools) (STS)
  - [IntelliJ IDEA](https://www.jetbrains.com/idea/)
  - [VS Code](https://code.visualstudio.com)

### Steps

1. On the command line run:

   ```bash
   git clone https://github.com/spring-projects/spring-petclinic.git
   ```

1. Inside Eclipse or STS:

   Open the project via `File -> Import -> Maven -> Existing Maven project`, then select the root directory of the cloned repo.

   Then either build on the command line `./mvnw generate-resources` or use the Eclipse launcher (right-click on project and `Run As -> Maven install`) to generate the CSS. Run the application's main method by right-clicking on it and choosing `Run As -> Java Application`.

1. Inside IntelliJ IDEA:

   In the main menu, choose `File -> Open` and select the Petclinic [pom.xml](pom.xml). Click on the `Open` button.
   - CSS files are generated from the Maven build. You can build them on the command line `./mvnw generate-resources` or right-click on the `spring-petclinic` project then `Maven -> Generates sources and Update Folders`.

   - A run configuration named `PetClinicApplication` should have been created for you if you're using a recent Ultimate version. Otherwise, run the application by right-clicking on the `PetClinicApplication` main class and choosing `Run 'PetClinicApplication'`.

1. Navigate to the Petclinic

   Visit [http://localhost:8080](http://localhost:8080) in your browser.

## Looking for something in particular?

| Spring Boot Configuration | Class or Java property files                                                                                                                                           |
| ------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| The Main Class            | [PetClinicApplication](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java)    |
| Properties Files          | [application.properties](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/resources)                                                             |
| Caching                   | [CacheConfiguration](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java) |

## Interesting Spring Petclinic branches and forks

The Spring Petclinic "main" branch in the [spring-projects](https://github.com/spring-projects/spring-petclinic)
GitHub org is the "canonical" implementation based on Spring Boot and Thymeleaf. There are
[quite a few forks](https://spring-petclinic.github.io/docs/forks.html) in the GitHub org
[spring-petclinic](https://github.com/spring-petclinic). If you are interested in using a different technology stack to implement the Pet Clinic, please join the community there.

## Interaction with other open-source projects

One of the best parts about working on the Spring Petclinic application is that we have the opportunity to work in direct contact with many Open Source projects. We found bugs/suggested improvements on various topics such as Spring, Spring Data, Bean Validation and even Eclipse! In many cases, they've been fixed/implemented in just a few days.
Here is a list of them:

| Name                                                                                          | Issue                                                                                                                                                           |
| --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Spring JDBC: simplify usage of NamedParameterJdbcTemplate                                     | [SPR-10256](https://github.com/spring-projects/spring-framework/issues/14889) and [SPR-10257](https://github.com/spring-projects/spring-framework/issues/14890) |
| Bean Validation / Hibernate Validator: simplify Maven dependencies and backward compatibility | [HV-790](https://hibernate.atlassian.net/browse/HV-790) and [HV-792](https://hibernate.atlassian.net/browse/HV-792)                                             |
| Spring Data: provide more flexibility when working with JPQL queries                          | [DATAJPA-292](https://github.com/spring-projects/spring-data-jpa/issues/704)                                                                                    |

## Contributing

The [issue tracker](https://github.com/spring-projects/spring-petclinic/issues) is the preferred channel for bug reports, feature requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](.editorconfig) for easy use in common text editors. Read more and download plugins at <https://editorconfig.org>. All commits must include a **Signed-off-by** trailer at the end of each commit message to indicate that the contributor agrees to the Developer Certificate of Origin.
For additional details, please refer to the blog post [Hello DCO, Goodbye CLA: Simplifying Contributions to Spring](https://spring.io/blog/2025/01/06/hello-dco-goodbye-cla-simplifying-contributions-to-spring).

## License

The Spring PetClinic sample application is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).

## Docker

docker compose up --build
https://hub.docker.com/_/postgres?_gl=1*poz9xb*_gcl_au*MTY0OTk4NjA0Ny4xNzY5ODM3NjU3*_ga*MTEzMjM0MTM1MS4xNzY5ODM3NjU2*_ga_XJWPQMJYHQ*czE3Njk5NzE1MjIkbzckZzEkdDE3Njk5NzE1MjMkajU5JGwwJGgw

Java spring profiles. spring-petclinic/src/resources/db/postgres/petclinic_db_setup_postgres.txt

RUN --mount=type=bind,source=pom.xml,target=pom.xml \
 --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline -DskipTests

environment: - POSTGRES_URL=jdbc:postgresql://db:5432/petclinic

Automatically update services
Use Compose Watch to automatically update your running Compose services as you edit and save your code. For more details about Compose Watch, see Use Compose Watch.

```yaml
develop:
  watch:
    - action: rebuild
      path: .
```

docker compose watch

    ports:
      - 5431:5432

Host:Guest

Next, you added a new test stage labeled test based on the base stage. In this stage you copied in the necessary source files and then specified RUN to run ./mvnw test. Instead of using CMD, you used RUN to run the tests. The reason is that the CMD instruction runs when the container runs, and the RUN instruction runs when the image is being built. When using RUN, the build will fail if the tests fail.

docker build -t java-docker-image-test --progress=plain --no-cache --target=test .

## Maven

dependency:go-offline - A Maven goal that downloads all dependencies, plugins, and other artifacts your project needs so you can build it later without an internet connection

Maven goals are specific tasks that Maven can execute. They're the fundamental units of work in Maven's build lifecycle.

## Structure

Goals follow the format: `plugin:goal` (e.g., `compiler:compile`, `dependency:tree`)

## Common Maven Goals

**Compilation & Building:**

- `compile` - Compiles source code
- `test-compile` - Compiles test source code
- `package` - Creates JAR/WAR file
- `install` - Installs package to local repository
- `deploy` - Deploys package to remote repository
- `clean` - Removes target directory

**Testing:**

- `test` - Runs unit tests
- `surefire:test` - Runs tests using Surefire plugin
- `verify` - Runs integration tests

**Dependency Management:**

- `dependency:tree` - Shows dependency hierarchy
- `dependency:analyze` - Analyzes dependencies for unused/undeclared ones
- `dependency:resolve` - Resolves all dependencies
- `dependency:go-offline` - Downloads dependencies for offline use
- `dependency:copy-dependencies` - Copies dependencies to a directory

**Information & Analysis:**

- `help:describe` - Describes plugin goals
- `versions:display-dependency-updates` - Shows available dependency updates
- `versions:display-plugin-updates` - Shows available plugin updates
- `site` - Generates project documentation

**Spring Boot Specific:**

- `spring-boot:run` - Runs Spring Boot application
- `spring-boot:build-image` - Creates container image

## Lifecycle Phases vs Goals

**Phases** are sequential stages in the build lifecycle (like `compile`, `test`, `package`). When you run a phase, Maven executes all preceding phases plus goals bound to that phase.

**Goals** are specific tasks that can be executed independently or bound to phases.

Example: `mvn clean install` runs the clean phase, then all phases up to and including install.

Maven phases are sequential stages in the build lifecycle. When you run a phase, Maven automatically executes all previous phases in order.

## Three Built-in Lifecycles

Maven has three independent lifecycles:

1. **default** - handles project deployment
2. **clean** - handles project cleaning
3. **site** - handles project documentation

## Default Lifecycle Phases (most commonly used)

Executed in this order:

1. **`validate`** - Validates project structure and configuration
2. **`compile`** - Compiles source code (src/main/java)
3. **`test`** - Runs unit tests using test framework (JUnit, TestNG)
4. **`package`** - Packages compiled code into distributable format (JAR, WAR)
5. **`verify`** - Runs checks and integration tests to verify package quality
6. **`install`** - Installs package to local Maven repository (~/.m2/repository)
7. **`deploy`** - Copies package to remote repository for sharing

**Other phases in default lifecycle:**

- `initialize` - Initializes build state
- `generate-sources` - Generates source code for compilation
- `process-sources` - Processes source code
- `generate-resources` - Generates resources for packaging
- `process-resources` - Copies and processes resources to target
- `process-classes` - Post-processes compiled classes
- `test-compile` - Compiles test source code
- `process-test-resources` - Copies and processes test resources
- `prepare-package` - Performs operations before packaging
- `integration-test` - Processes and deploys package for integration tests
- `post-integration-test` - Performs cleanup after integration tests

## Clean Lifecycle Phases

1. **`pre-clean`** - Executes processes before cleaning
2. **`clean`** - Removes files from previous builds (deletes target/ directory)
3. **`post-clean`** - Executes processes after cleaning

## Site Lifecycle Phases

1. **`pre-site`** - Executes processes before site generation
2. **`site`** - Generates project documentation
3. **`post-site`** - Executes processes after site generation
4. **`site-deploy`** - Deploys generated site to web server

## Phase Execution Examples

```bash
# Runs: validate → compile → test → package
mvn package

# Runs: clean, then validate → compile → test → package → install
mvn clean install

# Runs only: validate → compile → test
mvn test

# Runs: clean, then validate → compile → test → package → verify → install → deploy
mvn clean deploy
```

## Key Concepts

**Sequential execution**: Running `mvn install` automatically runs validate, compile, test, package, verify, and install in that order.

**Skip phases**: You can't skip intermediate phases, but you can skip goals:

```bash
mvn install -DskipTests  # Skips test execution but compiles tests
mvn install -Dmaven.test.skip=true  # Skips test compilation and execution
```

**Multiple lifecycles**: Clean and default are independent:

```bash
mvn clean package  # Runs clean lifecycle, then default up to package
```
