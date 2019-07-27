package com.infotech.spark

import org.apache.spark.SparkContext

object BookWordCount {
  def main(args: Array[String]){
    val context = new SparkContext("local[*]", "BookWordCount")
    val book = context.textFile("in/book.txt")
    val words = book.flatMap(string => string.split("\\W+")).map(word => word.toLowerCase())
 
    
    val filteredWords = words.filter(filterFunc)
    // filter all words against the list of words like 'a, the, and, so, there, their'
       val wordTuple = filteredWords.map(word => (word, 1))
    
    val eachWordCount = wordTuple.reduceByKey((x,y) => x + y)
    //eachWordCount.foreach(println)
    
    //words.countByValue().foreach(println)
    
    eachWordCount.map(x => (x._2.toInt, x._1)).sortByKey()
    
    for(eachWord <- eachWordCount){
      val count = eachWord._1
      val word = eachWord._2
      println(s"$word : $count")
    }
    
    
  }
  
  def filterFunc(word : String) = {
    val list = List("a", "the", "and", "so", "there", "their")
    if(!list.contains(word)){
      true;
    }else{
      false;
    }
  }
}