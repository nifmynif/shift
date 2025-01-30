@echo off
chcp 65001
java -Dfile.encoding=UTF-8 -jar shift.jar --input=in.txt --sort=name --order=asc
pause