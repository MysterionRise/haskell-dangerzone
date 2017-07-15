package org.mystic

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
                      runesPickedUp: Int,
                      firstBloods: Int) {

}
