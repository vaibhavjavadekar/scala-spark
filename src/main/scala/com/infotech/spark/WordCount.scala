package com.infotech.spark


import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
object Wordcount {
  def main(args: Array[String]) {
    System.setProperty("hadoop.home.dir", "C:\\hadoop");
    //Create conf object
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
    //create spark context object
    val sc = new SparkContext(conf)
    
    //Read file and create RDD
    val rawData = sc.textFile("in/word_count.text")
    //convert the lines into words using flatMap operation
    val words = rawData.flatMap(line => line.split(" "))
    //count the individual words using map and reduceByKey operation
    val wordCount = words.map(word => (word.toLowerCase().trim(), 1)).reduceByKey(_ + _)
    //Save the result
    //wordCount.saveAsTextFile("out/word_count_result.text")
    wordCount.foreach(println)
    //stop the spark context
    sc.stop
  }
}