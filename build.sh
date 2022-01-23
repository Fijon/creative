if [ ! -d "musicApp" ]; then
  mkdir musicApp
fi
if [ ! -d "stockApp" ]; then
  mkdir stockApp
fi
rm -rf ./musicApp/*
rm -rf ./stockApp/*
mvn clean package -Dmaven.test.skip=true
cp music/target/*.jar ./musicApp/
cp -r music/target/lib ./musicApp/
cp stock/target/*.jar ./stockApp
cp -r stock/target/lib ./stockApp
