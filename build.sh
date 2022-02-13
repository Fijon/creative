appPath=${HOME}/app
musicAppPath=${appPath}/musicApp
stockAppPath=${appPath}/stockApp
taskPath==${appPath}/task
if [ ! -d ${musicAppPath} ]; then
  mkdir -p ${musicAppPath}
fi
if [ ! -d ${stockAppPath} ]; then
  mkdir -p ${stockAppPath}
fi
if [ ! -d ${taskPath} ]; then
  mkdir -p ${taskPath}
fi
rm -rf ${musicAppPath}/*
rm -rf ${stockAppPath}/*
rm -rf ${taskPath}/*
mvn clean package -Dmaven.test.skip=true
cp music/target/*.jar ${musicAppPath}
cp -r music/target/lib ${musicAppPath}
cp stock/target/*.jar ${stockAppPath}
cp -r stock/target/lib ${stockAppPath}
cp task/target/*.jar ${taskPath}
cp -r task/target/lib ${taskPath}