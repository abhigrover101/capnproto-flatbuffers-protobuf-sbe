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

columns <- c("Type","Size")

if (n >= 1) {
  d1 <- read.table(argv[1],header=F, colClasses=c("character", "numeric"),col.names=columns)
  xmin <- min(d1$Size)
  xmax <- max(d1$Size)
  xmax999th <- c(6)
  d <- d1
}

library(ggplot2)
library(scales)

png(file="Bar chart for size.png", height=800, width=800, pointsize=12)

xlabel <- expression(paste("Serializer"))
p <- ggplot(d, aes(x=Type,y=Size,fill=Type))+
	theme(text = element_text(size=20))+
	ylab("Size (bytes)") + 
	scale_fill_discrete(name = "Serializers") +
	xlab(xlabel)
p + geom_bar(stat = "identity")

dev.off()

