@echo off
chcp 65001
java -Dfile.encoding=UTF-8 -jar shift.jar --sort=name --order=asc --output=file --path=out.txt
java -Dfile.encoding=UTF-8 -jar shift.jar --input=in.txt --sort=salary --order=desc
pause