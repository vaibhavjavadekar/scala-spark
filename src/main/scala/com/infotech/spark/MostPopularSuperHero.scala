package com.infotech.spark

import org.apache.spark.SparkContext
import scala.io.Source
import scala.io.Codec
import java.nio.charset.Charset
import java.nio.charset.CodingErrorAction

object MostPopularSuperHero {
  def main (args : Array[String]){
    
    val context = new SparkContext("local[1]", "MostPopularSuperHero")
    
    val marvelGraph = context.textFile("in/Marvel-graph.txt")
    val marvelNames = context.textFile("in/Marvel-names.txt").flatMap(loadMarvelNames)
    
    val countOfSuperHeroMostPopular = marvelGraph
    .map(line => (line.split("\\W+")(0).toLong, line.split("\\W+").length - 1.toLong))
    .reduceByKey((x,y) => x+y)
    .map(tuple => (tuple._2, tuple._1))
    .max()
    
    val mostPopularNames = marvelNames.lookup(countOfSuperHeroMostPopular._2)
    
    val mostPopular = mostPopularNames(0);
    println(mostPopular)
    
  }
  
  
  def loadMarvelNames(line: String) : Option[(Long, String)] = {
      val fields = line.split("\"")
      if(fields.length > 1){
        return Some(fields(0).trim().toLong -> fields(1))  
      }else{
        return None
      }
  }
  
  
  
}