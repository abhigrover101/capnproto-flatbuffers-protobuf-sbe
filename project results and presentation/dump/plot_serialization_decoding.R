#!/usr/bin/env Rscript
#require(data.table)

argv <- commandArgs(TRUE)

png(file="serialization-decode-cdf.png", height=1000, width=1000, pointsize=12)

n <- length(argv)

columns <- c("Percentile", "Time")

if (n >= 1) {
  d1 <- read.table(argv[1], header=F, col.names=columns)
  d1$type = "Cap'n Proto"
  d <- d1
}

if (n >= 2) {
  d2 <- read.table(argv[2], header=F, col.names=columns)
  d2$type = "Flatbuffers"
  d <- rbind(d1, d2)
}

if (n >= 3) {
  d3 <- read.table(argv[3], header=F, col.names=columns)
  d3$type = "Protocol Buffers"
  d <- rbind(d1, d2, d3)
}

if (n >= 4) {
  d4 <- read.table(argv[4], header=F, col.names=columns)
  d4$type = "Simple Binary Encoding"
  d <- rbind(d1, d2, d3, d4)
}


library(ggplot2)
p <- ggplot(d, aes(x=Time, y=Percentile, colour=type))
p + geom_line(lwd=2) + scale_x_log10("Time to Decode (ns)")

dev.off()
