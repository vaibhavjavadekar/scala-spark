package com.infotech.spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext

object SmallPrograms {
  
  def main(args:Array[String]){
//    val conf = new SparkConf().setAppName("SmallPrograms").setMaster("local[*]");
////    val session = SparkSession.builder().config(conf);
//    val context = SparkContext.getOrCreate(conf);
//    
//    val list = List(1,2,5,8,12,12,1,54,67,676,22);
//    val nums = context.parallelize(list)
//    val distinctNums = nums.distinct().map(x => x*x)
//    distinctNums.foreach(println)
//    
//    val airportData = context.textFile("in/airports.text", 2);
//    airportData.sample(false, 10.0, 1L).foreach(println)
        
   val session = SparkSession.builder().
    appName("AvgFriendsByAgeDataset")
    .master("local[*]")
    .getOrCreate()
    
    val nums = session.range(10)
    //nums.groupBy('id % 2 as "group").agg(sum('id) as "sum")
    
  }
  
}