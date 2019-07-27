package com.infotech.spark

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

object AvgFriendsByAgeDataset {
  
  case class Person(srno:Int, name:String, age:Int, noOfFriends:Int)
  
  def main(args: Array[String]){
  
    val session = SparkSession.builder().
    appName("AvgFriendsByAgeDataset")
    .master("local[*]")
    .getOrCreate()
    
    val friendsData = session.sparkContext.textFile("in/fakefriends.csv");
    
    val persons = friendsData.map(line => Person(line.split(",")(0).toInt, line.split(",")(1).toString(), line.split(",")(2).toInt, line.split(",")(3).toInt));
    
    //import session.implicits._
    //val personsDataset = persons.toDS();
    
    //personsDataset.groupBy("age").avg("noOfFriends").orderBy("age").show()
    
  }
}