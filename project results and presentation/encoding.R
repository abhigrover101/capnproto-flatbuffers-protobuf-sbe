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

columns <- c("Percentile", "Time")

if (n >= 1) {
  d1 <- read.table(argv[1], header=F, col.names=columns)
  d1$Serializers = "Cap'n Proto"
  xmin <- min(d1$Time)
  xmax <- max(d1$Time)
  t<-min(tail(d1$Time,n=5))
  xmax999th <- t
  d <- d1
  c1 <- d1
}

if (n >= 2) {
  d2 <- read.table(argv[2], header=F, col.names=columns)
  d2$Serializers = "FlatBuffers"
  xmin <- min(xmin, d2$Time)
  xmax <- max(xmax, d2$Time)
  t<-min(tail(d2$Time,n=5))
  xmax999th <- max(xmax999th, t)
  d <- rbind(d1, d2)
  c2 <- d2
}

if (n >= 3) {
  d3 <- read.table(argv[3], header=F, col.names=columns)
  d3$Serializers = "Protocol Buffers"
  xmin <- min(xmin, d3$Time)
  xmax <- max(xmax, d3$Time)
  t<-min(tail(d3$Time,n=5))
  xmax999th <- max(xmax999th, t)
  d <- rbind(d1, d2, d3)
  c1 <- rbind(d1, d3)
}

if (n >= 4) {
  d4 <- read.table(argv[4], header=F, col.names=columns)
  d4$Serializers = "Simple Binary Encoding"
  xmin <- min(xmin, d4$Time)
  xmax <- max(xmax, d4$Time)
  t<-min(tail(d4$Time,n=5))
  xmax999th <- max(xmax999th, t)
  print(xmax999th)
  d <- rbind(d1, d2, d3, d4)
  c2 <- rbind(d2, d4)
}

library(ggplot2)
library(scales)
brks<-c(min(d1$Time),min(d2$Time),min(d3$Time),min(d4$Time))
png(file="encoding-cdf.png", height=800, width=800, pointsize=12)
xlabel <- expression(paste("Time to encode (ns)"))
p <- ggplot(d, aes(x=Time,y=Percentile,colour=Serializers))
p + geom_line(size=2) + 
	theme(panel.grid.minor = element_blank(),text = element_text(size=20)) +
	scale_x_log10(xlabel,breaks = c(brks,2000,5000,10000),limits=c(xmin, xmax999th)) +
	scale_y_continuous("Percentile",breaks=c(0,25,50,75,90,98,100)) +
	annotate("text", x = 6000, y = 102, label = "Extreme values-->") 
	
	#scale_x_continuous(xlabel,breaks=seq(0,xmax999th,2000),limits=c(xmin, xmax999th))
	

dev.off()

