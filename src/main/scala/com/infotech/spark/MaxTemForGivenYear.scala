package com.infotech.spark

import org.apache.spark.SparkContext

object MaxTemForGivenYear {
  def main(args: Array[String]){
    
    val context = new SparkContext("local[*]", "MinTemForGivenYear")
    
    val tempData = context.textFile("in/1800.csv").map(parseLine).filter(text => text._2 == "TMAX").map(text => (text._1, text._3.toFloat))
    
    val result = tempData.reduceByKey((x,y) => Math.max(x,y))
    
    result.foreach(println)
  }
  
  def parseLine(line:String) = {
    val fields = line.split(",")
    val stationId = fields(0)
    val entryType = fields(2)
    val temp = fields(3).toFloat * 0.1f * (9.0f/5.0f) + 32.0f
    (stationId, entryType, temp)
  }
  
}