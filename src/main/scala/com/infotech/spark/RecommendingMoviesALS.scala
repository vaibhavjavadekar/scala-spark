package com.infotech.spark

import org.apache.spark.SparkContext
import scala.io.Codec
import scala.io.Source
import java.nio.charset.CodingErrorAction
import org.apache.spark.mllib.recommendation._
import org.apache.log4j._

object RecommendingMoviesALS {
  
  //type Rating(userId: Int, movieId: Int, rating:Int)
  def main(args: Array[String]){
    
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
  
    val context = new SparkContext("local[*]", "RecommendingMoviesALS")
    val nameDict = loadMovieNames()
    val data = context.textFile("in/u.data")
    
    val ratings = data.map(x => x.split("\t")).map(x => Rating(x(0).toInt, x(1).toInt, x(2).toDouble))
    
    // Build the recommendation model using Alternating Least Squares
    println("\nTraining recommendation model...")
    
    val rank = 8
    val numIterations = 20
    
    val model = ALS.train(ratings, rank, numIterations)
    
    val userID = args(0).toInt
    
    println("\nRatings for user ID " + userID + ":")

    val userRatings = ratings.filter(x => x.user == userID)
    
    val myRatings = userRatings.collect()
    
    for (rating <- myRatings) {
      println(nameDict(rating.product.toInt) + ": " + rating.rating.toString)
    }
    
    println("\nTop 10 recommendations:")
    
    val recommendations = model.recommendProducts(userID, 10)
    for (recommendation <- recommendations) {
      println( nameDict(recommendation.product.toInt) + " score " + recommendation.rating )
    }
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