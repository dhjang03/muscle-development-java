# SWEN90004-2024-ASMT3

### Run original model
1. Navigate to the root directory of the project.
2. Compile Java files with the following command:
```
javac ./src/original/*.java
```
3. Run the model with the following command:
```
java -cp ./src original.Global --output=./output/original/output.csv --intensity=95 --hoursOfSleep=8.0 --daysBwWorkouts=5 --slowTwitchFibersPercentage=50 --lift=true
```
- Adjust the parameters as required
    - --output=path/to/output/filename.csv
    - --intensity=(int between 50 to 100)
    - --hoursOfSleep=(double between 0.0 to 12.0)
    - --daysBwWorkouts=(int between 1 to 30)
    - --slowTwitchFiberPercentage=(int between 0 to 100)
    - --lift=(boolean value; true or false)
- Make sure the directory to save the output CSV file exists. Otherwise, an error will be thrown.

### Run extended model
1. Navigate to the root directory of the project
2. Compile Java files with the following command:
```
javac ./src/extended/*.java
```
3. Run the model with the following command:
```
java -cp ./src extended.Global --output=./output/extended/output.csv --intensity=95 --hoursOfSleep=8.0 --daysBwWorkouts=5 --slowTwitchFibersPercentage=50 --lift=true --nutritionQuality=0.5
```
- Adjust the parameters as required
    - --output=path/to/output/filename.csv
    - --intensity=(int between 50 to 100)
    - --hoursOfSleep=(double between 0.0 to 12.0)
    - --daysBwWorkouts=(int between 1 to 30)
    - --slowTwitchFiberPercentage=(int between 0 to 100)
    - --lift=(boolean value; true or false)
    - --nutritionQuality=(double between 0.0 to 1.0)
- Nutrition quality with 0.5 means average quality of nutrition intake. Value above 0.5 will represent good quality and below 0.5 will depict poor nutrition quality.
- Make sure the directory to save the output CSV file exists. Otherwise, an error will be thrown.
