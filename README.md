# NewsApp

NewsApp is a JavaFX application that allows users to retrieve and explore news articles and sources from various providers. It provides a user-friendly interface for interacting with the [News API](https://newsapi.org/) to fetch real-time news data.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)

## Features

- View top headlines from different news sources.
- Search for news articles based on keywords, dates, and other criteria.
- Explore and discover news sources based on country, category, and language.

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java JDK (version 20.0.X)
- Gradle (version 8.4)
- (News API Key (get it from [News API](https://newsapi.org/)))

### Installation

1. Clone the repository:

```bash
git clone https://github.com/Pierre-Gei/Kotlin-S5.git
```

2. Navigate to the project directory:
   
```bash
cd Kotlin-S5
```
 3. Build the project using Gradle:
    
```bash
gradle build
```

### Usage
1. Obtain a News API Key from News API.
2. Open the App.kt file in your preferred Kotlin IDE.
3. Replace the API_KEY constant with your News API Key (window comming soon):
   
```kotlin
val API_KEY = "your-api-key-here"
```
4. Run the application.

```bash
.\gradlew run
```
