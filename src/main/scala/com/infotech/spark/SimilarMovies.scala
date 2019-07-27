package com.infotech.spark

import org.apache.spark.SparkContext
import scala.io.Codec
import scala.io.Source
import java.nio.charset.CodingErrorAction

object SimilarMovies {
  
  type MovieRating = (Int, Double)
  type UserRatingPair = (Int, (MovieRating, MovieRating))
  
  def main(args: Array[String]){
    // similar movies can be found when 1 person gives same rating to 2 different movies
    val context = new SparkContext("local[*]", "SimilarMovies")  
    
    val nameDict = loadMovieNames()
    
    val movieData = context.textFile("in/u.data")
    
    val user_movie_rating = movieData
    .map(line => (line.split("\\W+")(0).toInt, (line.split("\\W+")(1).toInt, line.split("\\W+")(2).toDouble)))
    
    
    val userslefjoin = user_movie_rating.join(user_movie_rating)
    
    val uniqJoinedRatings = userslefjoin.filter(filterDuplicates)
    //.foreach(println)
    
    val moviePairs = uniqJoinedRatings.map(makePairs)
    
    val moviePairRatings = moviePairs.groupByKey()
    
    val moviePairSimilarities = moviePairRatings.mapValues(computeCosineSimilarity).cache()
    
    //if (args.length > 0) {
      val scoreThreshold = 0.97
      val coOccurenceThreshold = 50.0
      
      val movieID:Int = 50//args(0).toInt
      
      // Filter for movies with this sim that are "good" as defined by
      // our quality thresholds above     
      
      val filteredResults = moviePairSimilarities.filter( x =>
        {
          val pair = x._1
          val sim = x._2
          (pair._1 == movieID || pair._2 == movieID) && sim._1 > scoreThreshold && sim._2 > coOccurenceThreshold
        }
      )
        
      // Sort by quality score.
      val results = filteredResults.map( x => (x._2, x._1)).sortByKey(false).take(10)
      
      println("\nTop 10 similar movies for " + nameDict(movieID))
      for (result <- results) {
        val sim = result._1
        val pair = result._2
        // Display the similarity result that isn't the movie we're looking at
        var similarMovieID = pair._1
        if (similarMovieID == movieID) {
          similarMovieID = pair._2
        }
        println(nameDict(similarMovieID) + "\tscore: " + sim._1 + "\tstrength: " + sim._2)
      }
    //}
    
  }
  // Input : (userId,(movieId1, rating1), (movieId2, rating2))
  def filterDuplicates(userRating:UserRatingPair): Boolean = {
    
    //(movieId1, rating1)
    val movieRating1 = userRating._2._1
    // (movieId2, rating2)
    val movieRating2 = userRating._2._2
    
    val movie1 = movieRating1._1
    val movie2 = movieRating2._1

    return movie1 < movie2
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
  
  def makePairs (userRatings :UserRatingPair)= {
    val movieRating1 = userRatings._2._1
    val movieRating2 = userRatings._2._2
    
    val movie1 = movieRating1._1
    val rating1 = movieRating1._2
    val movie2 = movieRating2._1
    val rating2 = movieRating2._2
    
    ((movie1, movie2), (rating1, rating2))
  }
  
  type RatingPair = (Double, Double)
  type RatingPairs = Iterable[RatingPair]
  
  def computeCosineSimilarity(ratingPairs:RatingPairs): (Double, Int) = {
    var numPairs:Int = 0
    var sum_xx:Double = 0.0
    var sum_yy:Double = 0.0
    var sum_xy:Double = 0.0
    
    for (pair <- ratingPairs) {
      val ratingX = pair._1
      val ratingY = pair._2
      
      sum_xx += ratingX * ratingX
      sum_yy += ratingY * ratingY
      sum_xy += ratingX * ratingY
      numPairs += 1
    }
    
    val numerator:Double = sum_xy
    val denominator = Math.sqrt(sum_xx) * Math.sqrt(sum_yy)
    
    var score:Double = 0.0
    if (denominator != 0) {
      score = numerator / denominator
    }
    
    return (score, numPairs)
  }
}