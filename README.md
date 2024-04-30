# SWEN90004-2024-ASMT3

### Run original model
1. Navigate to the root directory of the project.
2. Compile java file with following command:
```
javac ./src/original/*.java
```
3. Run the model with following command:
```
java -cp ./src original.Global --output=./output/original/output.csv --intensity=95 --hoursOfSleep=6.5 --daysBwWorkouts=5 --slowTwitchFibersPercentage=50 --lift=true
```
- Adjust the parameters as required
    - --output="path/to/output.csv"
    - --intensity=(int between 50 to 100)
    - --hoursOfSleep=(double between 0.0 to 12.0)
    - --daysBwWorkouts=(int between 1 to 30)
    - --slowTwitchFiberPercentage=(int between 0 to 100)
    - --lift=(boolean value; true or false)
- Make sure the directory to save output csv file exists. Otherwise error will be thrown.