dataset = read.csv('iris.csv')
str(dataset)
lapply(dataset,class)
ncol(dataset)
summary(dataset)

mean(dataset$Sepal.Length)
min(dataset$Sepal.Length)
max(dataset$Sepal.Length)
sum(dataset$Sepal.Length)
sd(dataset$Sepal.Length)
var(dataset$Sepal.Length)
range(dataset$Sepal.Length)


hist(dataset$Sepal.Length,border='red',xlab = 'petal length',main='hist')
hist(dataset$Sepal.Width)
st= as.numeric(dataset$Species)
hist(st)

boxplot(dataset$Sepal.Length)$out
boxplot(dataset$Sepal.Width)$out
boxplot(dataset$Petal.Length)$out
boxplot(dataset$Petal.Width)$o
boxplot(dataset)$out