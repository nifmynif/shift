@echo off
chcp 65001
java -jar shift.jar --sort=name --order=asc --output=file --path=out.txt
java -jar shift.jar --input=in.txt --sort=salary --order=desc
pause