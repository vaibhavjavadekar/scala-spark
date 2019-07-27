package com.infotech.spark

import org.apache.spark.SparkContext

object RatingsCounter {
  
  
  
  def main(args: Array[String]){
    
    val context = new SparkContext("local[*]", "RatingsCounter");
    
    val fileData = context.textFile("in/u.data");
    
    val ratings = fileData.map(string => string.toString().split("\t")(2))
    
    val ratingCount = ratings.countByValue().toSeq.sortBy(_._1);
    
    ratingCount.foreach(println)
    
    
  }
}