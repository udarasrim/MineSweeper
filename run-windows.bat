@REM didn't test widows file
echo "Building project..."
mvnw.cmd clean package

echo "Running Minesweeper..."
java -jar target/MineSweeper-1.0-1.jar