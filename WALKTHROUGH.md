# Walkthrough

Here is my personal space to explain what I made, what I plan, what I want.

## 1. Taking ownership

### Documentation

First thing first, when a project starts or when a project is given to my team,
I like to see the project following the team convention. As for this project I
will be alone, I will chose my personal taste in this subject.

 * A `README.md` with minimum sections
 * `pom.xml` with the java version

**Next step**: looking at the code and automate test launching.

### Independent tools

As I -- sadly -- am used to manage legacy projects, I prefer to have tools
embedded with the project when available (to be able to manage different projets
with different tools). Docker (with docker-compose) is often the only
real prerequisite to start on a project I manage. As this project is not a
server, only the maven wrapper is needed.

### Test automation

If I can automate it, I automate it. Simple, isn't it?

## 2. Implements and solve tests

Now I have a project up to date. I begin by fixing known bugs. Here, the failing
tests.

As I only looking for the real test code for the first time, I can know see that
they are only sample requirements without implementation.

I step back to a real step: no implementation at all by commenting samples. In a
real project, such code will not exist and therefore will not be commented out.

### For the technical test

I will create sub-branches that I will squash in a real project. I want to
demonstrate I can take the different steps of the TDD (red green refactor) but
in a real project, only the valid commits will be pushed as to be able to use
`git bisect` if needed.

### Tests are not exhaustive

Sample tests does not cover all possibilities. And even if the tests are
implemented, the implementation code can (and therefore do) be bugged.

At least, the travis badge will be green ^^

**Next step**: add issues and implements use cases

## 3. Sonarqube

For fun, I want to test sonarcloud.io . I often use a private instance of
sonarqube but never the public one. That is the opportunity!