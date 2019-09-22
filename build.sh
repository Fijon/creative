mkdir musicApp stockApp
mvn clean package
cp music/target/*.jar ./musicApp/
cp -r music/target/lib ./musicApp/
cp stock/target/*.jar ./stockApp
cp -r stock/target/lib ./stockApp
