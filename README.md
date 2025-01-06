# SWEN90004-2024-ASMT3

# Table of Contents

1. [Introduction](#introduction)
2. [Model Design](#model-design)
    1. [Global](#1-global)
    2. [Muscle](#2-muscle)
    3. [Patch](#3-patch)
    4. [MuscleFiber](#4-musclefiber)
    5. [Configuration](#5-configuration)
3. [Model Features](#model-features)
4. [Run Original Model](#run-original-model)
5. [Run Extended Model](#run-extended-model)


### Introduction

The project is a Java implementation of a muscle development model originally created in NetLogo. The project includes analyzing and generating graphs from experimental data to validate the accuracy of the Java implementation.

The original muscle model is complex, incorporating multiple factors that influence muscle development, including:

- Exercise intensity
- Sleep duration
- Workout frequency
- Slow-Twitch Fiber Percentage
- Lift performance

These factors primarily affect anabolic and catabolic hormone levels, which in turn determine muscle development. The muscle system's development and core mechanisms function as follows:

- Base properties (including intensity, sleep duration, workout frequency, etc.) influence the production and regulation of anabolic and catabolic hormones within each patch.
- Each patch influences neighboring patches based on its parameters.
- Muscle fiber size changes in response to the relative levels of anabolic and catabolic hormones.

The system outputs the following metrics:

- Muscle development: calculated as the total size of muscle fibers divided by 100
- Average hormone levels across patches:

    - Anabolic hormone level
    - Catabolic hormone level


### Model Design

The **Muscle Development Simulation Model** simulates muscle development by abstracting complex biological processes into manageable components. While actual muscle growth involves intricate interactions between genetics, hormones, nutrition, and exercise, this model focuses on key elements: the interplay between anabolic and catabolic hormones and their response to daily activities.

The simulation extends the base model by incorporating nutrition quality effects using sigmoid functions to model hormone responses. The system is structured into five main classes:

#### 1. Global
- **Responsibilities**:
  - Initializes configuration from command line arguments.
  - Manages simulation setup and execution.
  - Handles results export to CSV format.

#### 2. Muscle
- **Description**: Serves as the primary functional unit.
- **Responsibilities**:
  - Contains methods for:
    - Activity triggering.
    - Muscle development regulation.
    - Hormonal change management.
  - Maintains a collection of `Patch` objects.

#### 3. Patch
- **Description**: Represents a segment of muscle tissue.
- **Responsibilities**:
  - Manages local hormone levels (anabolic and catabolic).
  - Contains a `MuscleFiber` object.
  - Implements methods for:
    - Hormone level modification.
    - Muscle fiber development control.

#### 4. MuscleFiber
- **Description**: Models individual muscle fibers.
- **Responsibilities**:
  - Tracks fiber size.
  - Provides methods for:
    - Growth initiation.
    - Size regulation.

#### 5. Configuration
- **Description**: Stores simulation parameters.
- **Contents**:
  - System constants.
  - User-defined variables.


### Model Features

- **Abstraction of Complex Processes**: Simplifies intricate biological interactions into manageable components.
- **Hormone Dynamics Simulation**: Models the interplay between anabolic and catabolic hormones and their effects on muscle growth.
- **Nutrition Quality Influence**: Incorporates the impact of nutrition quality using sigmoid functions to model hormone responses.
- **Daily Activity Impact**: Simulates how daily activities influence muscle development.
- **Data Export Capabilities**: Provides functionality to export simulation results to CSV format for further analysis.


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
