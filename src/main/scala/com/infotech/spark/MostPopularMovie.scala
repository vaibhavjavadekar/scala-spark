package com.infotech.spark

import org.apache.spark.SparkContext
import scala.io.Source
import scala.io.Codec
import java.nio.charset.CodingErrorAction

object MostPopularMovie {
  
  
  
  def main (args: Array[String]){
    
    val context = new SparkContext("local[1]", "MostPopularMovie")
    val movieFact = context.textFile("in/u.data")
    val movieDict = context.broadcast(loadMovieNames)
    
    val sortedpopularMovie = movieFact.map(text => (text.split("\t")(1).toInt, 1)).reduceByKey((x,y) => x+y).map(text => (text._2, text._1)).sortByKey(true)
    val result = sortedpopularMovie.map(x => (movieDict.value(x._2), x._1)).collect()
    
    result.foreach(println)
  }
  
  def loadMovieNames() : Map[Int, String] = {
    
    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)
    
    var movieNames: Map[Int, String] = Map()
    val movieData = Source.fromFile("in/u.item").getLines()
    for(item <- movieData){
      val fields = item.split("\\|")
      if(fields.length > 1){
        movieNames += (fields(0).toInt -> fields(1))  
      }
    }
    
    return movieNames
  }
}