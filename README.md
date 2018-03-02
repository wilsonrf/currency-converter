# Currency Converter

[![CircleCI](https://circleci.com/gh/wilsonrf/currency-converter/tree/master.svg?style=svg)](https://circleci.com/gh/wilsonrf/currency-converter/tree/master)

## Running localhost, using a local mongodb and a wiremock of openexchangerate.org API
To run localhost you will need a MongoDB running on port 27017
```
gradle startWiremocks
gradle bootRun -Dspring.profiles.active=production
```

## Running on production, using openExchangeRate.org and mLab
```
gradle bootRun -Dspring.profiles.active=production
```