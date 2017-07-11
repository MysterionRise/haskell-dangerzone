package org.mystic

class DotaPlayerBuilder {
  var name: String = ""
  var id: String = ""
  var kills: Int = 0
  var deaths: Int = 0
  var assists: Int = 0
  var lastHits: Int = 0
  var denies: Int = 0
  var gpm: Int = 0
  var towerKill: Int = 0
  var teamFightPercentage: Double = 0.0d
  var observerWardsPlaced: Int = 0
  var stacksMade: Int = 0
  var runesPickedUp: Int = 0
  var firstBloods: Int = 0

  def withName(name: String): DotaPlayerBuilder = {
    this.name = name
    this
  }

  def withID(id: String): DotaPlayerBuilder = {
    this.id = id
    this
  }

  def withKills(kills: Int): DotaPlayerBuilder = {
    this.kills = kills
    this
  }

  override def build: DotaPlayer = new DotaPlayer(this.id)
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
