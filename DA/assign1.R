iris
nrow(iris)  #print the no of rows
ncol(iris)  #print the no of cols i.e features

#get the minimum value in each column
min(iris$Sepal.Length)
min(iris$Sepal.Width)
min(iris$Petal.Length)
min(iris$Petal.Width)

#get the maximum in each column
max(iris$Sepal.Length)
max(iris$Sepal.Width)
max(iris$Petal.Length)
max(iris$Petal.Width)

str(iris)
# to print mean of Sepal.Length column
mean(iris$Sepal.Length, na.rm = TRUE)
summary(iris$Sepal.Length)

hist(iris$Sepal.Width) 
boxplot(iris, horrizontal = TRUE) 
plot(iris)