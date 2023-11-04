# SELENIUM-E2E-TESTS

Repository with examples of automation using Selenium + TestNG in java

> Selenium is a portable software-testing framework for web applications. Selenium provides a playback tool for authoring tests without the need to learn a test scripting language.

For more information, see the documentation: [SELENIUM](https://www.selenium.dev/)

---

## Table of Contents

> Index `README`.

- [Prerequisites](#prerequisites)
- [Configuration](#configuration)
- [Installation](#installation)
- [Features](#features)
- [How to run the tests](#how-to-run-the-tests)
- [Report](#report)
- [Support](#support)

---

## Prerequisites

- [Chocolatey or Scoop](https://chocolatey.org/install)
- [JDK +11](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [IntelliJ IDE](https://www.jetbrains.com/idea/download/?section=windows)
- [Maven](https://maven.apache.org/install.html)
- [Allure](https://docs.qameta.io/allure/#_installing_a_commandline)

---

## Configuration

Environment variables and bash_profile

> Tips: Use **chocolatey or scoop** for installation.

### Example-JDK:

Configuration and Install JDK: https://community.chocolatey.org/packages/oracle17jdk

```bash
choco install openjdk
```

### Example-Maven

Configuration and Install Maven: https://community.chocolatey.org/packages/maven

```bash
scoop install maven
```

## Installation

Clone this project: https://github.com/ulucasfraga/selenium-e2e-tests.git


```bash
# Install all dependencies (via pom.xml):
cd /selenium-e2e-tests
mvn install
```

> Tips:

- Use *pom.xml* to install or remove the project dependencies.

## How to run the tests

- via maven:

```bash
mvn clean test
```

- run test choosing browser:

```bash
mvn clean test -Dbrowsers=chrome
```

- run tests with IntelliJ (or another IDE):

Right click on tests class > Run 'Test'.

---

## Report

- run to generate an allure report

```bash
mvn allure:serve
```

---

## Support

- LinkedIn at <a href="https://www.linkedin.com/in/ulucasfraga" target="_blank">Lucas Fraga</a>
- E-mail: <ulucasfraga@gmail.com>

---

## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- **[MIT license](http://opensource.org/licenses/mit-license.php)**
- Copyright 2023 © <a href="https://bolttech.io/" target="_blank">BoltTech</a>.