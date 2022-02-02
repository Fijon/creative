appPath=${HOME}/app/
musicAppPath=${appPath}/musicApp
stockAppPath=${appPath}/stockApp
if [ ! -d ${musicAppPath} ]; then
  mkdir -p ${musicAppPath}
fi
if [ ! -d ${stockAppPath} ]; then
  mkdir -p ${stockAppPath}
fi
rm -rf ${musicAppPath}/*
rm -rf ${stockAppPath}/*
mvn clean package -Dmaven.test.skip=true
cp music/target/*.jar ${musicAppPath}
cp -r music/target/lib ${musicAppPath}
cp stock/target/*.jar ${stockAppPath}
cp -r stock/target/lib ${stockAppPath}
