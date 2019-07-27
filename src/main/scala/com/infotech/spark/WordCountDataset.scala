package com.infotech.spark


import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions

object WordcountDataset {
  
  case class Word(word: String)
  
  def main(args: Array[String]) {
    System.setProperty("hadoop.home.dir", "C:\\hadoop");
    //Create conf object
    val session = SparkSession
    .builder()
    .appName("WordCountDataset")
    .master("local[*]")
    .getOrCreate()
    
    
    //Read file and create RDD
    val rawDataDataset = session.sparkContext.textFile("in/word_count.text")
    //convert the lines into words using flatMap operation
    val words = rawDataDataset.flatMap(line => line.split("\\W+"))
    .map(line => Word(line.toLowerCase().trim()))
    //words.foreach(println)
    
    import session.implicits._
    val wordsDataset = words.toDS();
    
    wordsDataset.groupBy("word").count().orderBy(functions.desc("count")).show();
    
    session.stop
  }
  
}