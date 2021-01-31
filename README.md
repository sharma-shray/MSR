# MSR

MetaData:
A reproduction as a part of MSR course at MSR course2020/21 at UNIKO,CS department,SoftLang team

Paper Title: On the Effectiveness of Manual and Automatic Unit Test Generation: Ten Years Later
DBLP: https://dblp.org/rec/conf/msr/SerraGPFGB19

Hardware Requirements:
1. RAM	2 GB RAM minimum, 8 GB RAM recommended
2. Disk space	2.5 GB and another 1 GB for caches minimum, solid-state drive with at least 5 GB of free space recommended
3. Screen resolution	1024×768 minimum screen resolution. 1920×1080 is a recommended screen resolution.

Software requirements:
1. OS Version	64 Bit Microsoft Windows 8 or later	macOS 10.13 or later	Any Linux distribution that supports Gnome, KDE, or Unity DE
2. JDk: 11
3. JRE Version	JRE 11 
4. IntelliJ
5. Diffblue "AI based Unit test automation tool" (https://medium.com/better-programming/this-ai-tool-write-great-unit-tests-acfbfab53c09) 
6. Jacoco test  coverage tool(personal preference)

Process:
Important links and information: 
1. Git hub rep for source code: https://github.com/freenet/fred/commit/fc532a10a6f3090e1a09a62d804466bfeac70c5e
2. The refernce paper was having incomplete list of commits so a finalized list of commits has been placed in the process folder along with the java files for the commits just for reference.
3. Steps to install unit test automation tool: (https://medium.com/better-programming/this-ai-tool-write-great-unit-tests-acfbfab53c09).

Steps to reproduce:
1. Install intelliJ and Diffblue with JDK 11.
2. Pull the git repo in the system.
3. Open the code in intelliJ.
4. Set up Diffblue in intelliJ.
5. As there are often compatibility issues, i have proceeded with using the current commit as a baseline for generating tests.
6. Let the indexing for the diffblue finish and then generate test cases for the chosen classes.
7. After the generation of the test cases navigate to the test cases and run them for code coverage( This option is provided as a part of inltellij)
8. Notice that the tool takes into consideration the private classes and does not generate test cases for it.
9. Once the run is finished you can match the code coverage numbers in the final observation.
10. Also note the number of issues found during the test run which will match to the  faults found section of the observation.
11. For each class 10 random errors were introduced one by one and checked if the test cases generated catches the issues.which gives us mutation score.

Data: 
Input:
1. Source code link given in the process section.
2. Commit ID with class names placed in the process folder with the commits for reference.

The Process folder consists of classes under discussion and thier commit ID's.
Data folder consist of orignal data plus data from the new automation tools for comparison.

We see that the tools have gotten more compatible and mould themselves according to the code, small issues like possible integer size issues are also caught. The Line coverage can be low at times depending on the access modifiers.The mutation score and code coverage has significantly improved.But still needs human intervention and can be considered as an addition to human created unit test cases.


Delta:
Process Delta: As the reference paper was a reproduction of an older paper for tools which were very old , choosing the same tools again does not actually compare how much the automation testing capabilities have evolved and hence an AI base automation tool was compared with the older tools and manual efforts to get a clearer picture of devlopment.

The comparison matrix remains the same, and so does the code base. But the automation tools used have been changed to see the actual growth.

Data Delta: the table of clases mentione din the reference paper contains15 classes but the commit ID shared by the reference paper has only 6 unique commits mapping to 6 classes and hence the redundant data has been refined to leave us with 6 unique classes to be compared . 

