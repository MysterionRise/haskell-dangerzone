package org.mystic

class DotaPlayerBuilder {
  var name: String = ""
  var sauce: String = ""
  var topping: String = ""
}


case class DotaPlayer(name: String,
                      id: String,
                      kills: Int,
                      deaths: Int,
                      assists: Int,
                      lastHits: Int,
                      denies: Int,
                      gpm: Int,
                      towerKill: Int,
                      teamFightPercentage: Double,
                      observerWardsPlaced: Int,
                      stacksMade: Int,
                      runesPickedUp: Int,
                      firstBloods: Int) {

}
