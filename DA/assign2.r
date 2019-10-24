library(caTools)
library(caret)
library(e1071)


data <- read.csv("/home/avinash/LP1/DA/diabetes.csv", header=TRUE, sep=",")
str(data)
summary(data)  # summarize the dataset
lapply(data, class)  # type of attribute in thedata

data$Outcome = factor(data$Outcome, levels = c(0,1), labels = c(0,1))

# data partition
split_data <- sample.split(data, SplitRatio = 0.7)
train <- subset(data, split_data==TRUE)
test <- subset(data, split_data == FALSE)

str(train)
str(test)

#use of naive bayes algorithm for classification
classifier <- naiveBayes(x = train[-9], y = train$Outcome)
classifier

predicted_data <- predict(classifier, newdata = test[-9])
predicted_data

cf <- confusionMatrix(predicted_data, test$Outcome)
cf
