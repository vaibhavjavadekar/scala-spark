package com.infotech.spark

import org.apache.spark.SparkContext

object AvgFriendsByName {
  
  def main(args: Array[String]){
  
    val context = new SparkContext("local[*]", "AvgFriendsByName");
    
    val friendsData = context.textFile("in/fakefriends.csv");
    
    val splitLines = friendsData.map(line => (line.split(",")(1),line.split(",")(3).toLong) );
    
    val ageValueIndex = splitLines.mapValues(numOfFriends => (numOfFriends, 1));
    
    val totalNumOfFriendsByAge = ageValueIndex.reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
    
    val avgFriendsByAge = totalNumOfFriendsByAge.mapValues(x => (x._1 / x._2).toDouble).collect().sorted;
    
    avgFriendsByAge.foreach(println)
    
  }
}