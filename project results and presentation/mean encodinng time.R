#!/usr/bin/env Rscript

argv <- commandArgs(TRUE)

usage <- function() {
	print("Usage: plot* <data>+")
	quit()
}

if (length(argv) < 1) {
	usage()
}

argv <- commandArgs(TRUE)
n <- length(argv)

columns <- c("Type","Time")

if (n >= 1) {
  d1 <- read.table(argv[1],header=F, colClasses=c("character", "double"),col.names=columns)
  d <- d1
}

library(ggplot2)
library(scales)

png(file="Bar chart for mean encoding time.png", height=800, width=800, pointsize=12)

xlabel <- expression(paste("Serializer"))
p <- ggplot(d, aes(x=Type,y=Time,fill=Type))+
	theme(text = element_text(size=20))+
	scale_fill_discrete(name = "Serializers") +
	xlab(xlabel) + 
	ylab("Mean encoding time (ns)")
p + geom_bar(stat = "identity")

dev.off()

